package com.kinghk.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.kinghk.service.MinioService;
import com.kinghk.util.FileTypeUtils;
import com.kinghk.util.MinioProperties;
import com.kinghk.util.MinioUtil;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

    private final MinioUtil minioUtil;
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public MinioServiceImpl(MinioUtil minioUtil, MinioClient minioClient, MinioProperties minioProperties) {
        this.minioUtil = minioUtil;
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    public boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }


    @Override
    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    @Override
    public List<String> listBucketName() {
        return minioUtil.listBucketNames();
    }

    @Override
    public List<Bucket> listBuckets() {
        return minioUtil.listBuckets();
    }

    @Override
    public boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }


    @Override
    public List<String> listObjectNames(String bucketName) {
        return minioUtil.listObjectNames(bucketName);
    }

    @Value("${linux.upload.path}")
    private String linuxUploadPath;

    @Value("${visit.path}")
    private String visitPath;

    @Override
    public String putObject(MultipartFile file, String bucketName,String fileType) {
        try {
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename();

            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            minioUtil.putObject(bucketName, file, objectName,fileType);
            return minioProperties.getEndpoint()+"/"+bucketName+"/"+objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    @Override
    public String compressUploadFile(MultipartFile file,String bucketName,String fileType) {
        try {
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename();

            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            FileTypeUtils typeUtils = new FileTypeUtils();
            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            /**
             * 判断是否是图片
             * 判断是否超过了 100K
             */
            if (typeUtils.isPicture(suffix) && (1024 * 1024 * 0.1) <= file.getSize()) {

                //System.out.println(ClassUtils.getDefaultClassLoader().getResource("upload").getPath());
                File newFile = new File(linuxUploadPath + objectName + "." + suffix);
                // 小于 1M 的
                if ((1024 * 1024 * 0.1) <= file.getSize() && file.getSize() <= (1024 * 1024)) {
                    Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.3f).toFile(newFile);
                }
                // 1 - 2M 的
                else if ((1024 * 1024) < file.getSize() && file.getSize() <= (1024 * 1024 * 2)) {
                    Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.2f).toFile(newFile);
                }
                // 2M 以上的
                else if ((1024 * 1024 * 2) < file.getSize()) {
                    Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.1f).toFile(newFile);
                }
                // 获取输入流
                FileInputStream input = new FileInputStream(newFile);
                // 转为 MultipartFile
                MultipartFile multipartFile = new MockMultipartFile("file", newFile.getName(), "text/plain", input);
                // 开始上传
                minioUtil.putObject(bucketName, multipartFile, fileName,fileType);
                System.out.println("---删除"+newFile.isFile()+newFile.exists());
                if (newFile.isFile() && newFile.exists()) {
                    newFile.delete();
                }
                System.out.println("==="+minioUtil.getObjectUrl(bucketName,objectName));
                return visitPath+"/"+bucketName+"/"+objectName;
            }else{
                minioUtil.putObject(bucketName, file, objectName,fileType);
                return visitPath+"/"+bucketName+"/"+objectName;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        return minioUtil.getObject(bucketName,objectName);
    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }

    @Override
    public boolean removeListObject(String bucketName, List<String> objectNameList) {
        return minioUtil.removeObject(bucketName,objectNameList);
    }

    @Override
    public String getObjectUrl(String bucketName,String objectName) {
        return minioUtil.getObjectUrl(bucketName, objectName);
    }
}