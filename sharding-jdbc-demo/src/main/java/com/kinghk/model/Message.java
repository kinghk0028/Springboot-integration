package com.kinghk.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Data
public class Message {

    @TableId
    private Integer id;

    /**
     * 预检分诊其他状态
     */
    private Integer restState;

    /**
     * 其他备注
     */
    private String restRemark;



    /**
     * 出车时间
     */
    private String goOutTime;

    /**
     * 治疗人数
     */
    private String cureCount;

    public String getCureCount() {
        return cureCount;
    }

    public void setCureCount(String cureCount) {
        this.cureCount = cureCount;
    }

    /**
     * 派车时间
     */
    private String sendTime;

    public String getGoOutTime() {
        return goOutTime;
    }

    public void setGoOutTime(String goOutTime) {
        this.goOutTime = goOutTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    private Integer isPush;

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    /**
     * 主诉病史
     */
    private String actionHistory;

    /**
     * 病症种类
     */
    private String diseaseType;

    /**
     * 主诉
     */
    private String action;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 序号
     */
    private String number;

    public String getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(String actionHistory) {
        this.actionHistory = actionHistory;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 年龄
     */
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 出车单位
     */
    private String institution;
    /**
     * 任务结果
     */
    private String taskResult;

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    /**
     * 民族
     */
    private String nation;

    /**
     * 性别
     */
    private String sex;

    /**
     * 家庭住址
     */
    private String familyAddress;

    /**
     * 出生年月
     */
    private String birthTime;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    /**
     * 六大中心id
     */
    private String centerId;
    /**
     * 救护车发布视频时间1
     */
    private String ambulanceVideoTime1;

    /**
     * 救护车发布视频时间2
     */
    private String ambulanceVideoTime2;

    /**
     * 救护车发布视频时间3
     */
    private String ambulanceVideoTime3;

    /**
     * 救护车发布视频时间4
     */
    private String ambulanceVideoTime4;

    public String getAmbulanceVideoTime1() {
        return ambulanceVideoTime1;
    }

    public void setAmbulanceVideoTime1(String ambulanceVideoTime1) {
        this.ambulanceVideoTime1 = ambulanceVideoTime1;
    }

    public String getAmbulanceVideoTime2() {
        return ambulanceVideoTime2;
    }

    public void setAmbulanceVideoTime2(String ambulanceVideoTime2) {
        this.ambulanceVideoTime2 = ambulanceVideoTime2;
    }

    public String getAmbulanceVideoTime3() {
        return ambulanceVideoTime3;
    }

    public void setAmbulanceVideoTime3(String ambulanceVideoTime3) {
        this.ambulanceVideoTime3 = ambulanceVideoTime3;
    }

    public String getAmbulanceVideoTime4() {
        return ambulanceVideoTime4;
    }

    public void setAmbulanceVideoTime4(String ambulanceVideoTime4) {
        this.ambulanceVideoTime4 = ambulanceVideoTime4;
    }

    /**
     * 志愿者推送时间
     */
    private Date pushVolunteer;

    /**
     * 救护车推送时间
     */
    private Date pushAmbulance;


    public Date getPushVolunteer() {
        return pushVolunteer;
    }

    public void setPushVolunteer(Date pushVolunteer) {
        this.pushVolunteer = pushVolunteer;
    }

    public Date getPushAmbulance() {
        return pushAmbulance;
    }

    public void setPushAmbulance(Date pushAmbulance) {
        this.pushAmbulance = pushAmbulance;
    }

    /**
     * 自愿者接收时间
     */
    private Date volunteerTime;

    public Date getVolunteerTime() {
        return volunteerTime;
    }

    public void setVolunteerTime(Date volunteerTime) {
        this.volunteerTime = volunteerTime;
    }

    /**
     * 他人id
     */
    private String othersId;

    public String getOthersId() {
        return othersId;
    }

    public void setOthersId(String othersId) {
        this.othersId = othersId;
    }

    /**
     * 身份证号
     */

    private String idNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 一级分诊类型
     */
    private String oneTriage;

    /**
     * 二级级分诊类型
     */
    private String twoTriage;

    /**
     * 三级分诊类型
     */
    private String threeTriage;

    public String getOneTriage() {
        return oneTriage;
    }

    public void setOneTriage(String oneTriage) {
        this.oneTriage = oneTriage;
    }

    public String getTwoTriage() {
        return twoTriage;
    }

    public void setTwoTriage(String twoTriage) {
        this.twoTriage = twoTriage;
    }

    public String getThreeTriage() {
        return threeTriage;
    }

    public void setThreeTriage(String threeTriage) {
        this.threeTriage = threeTriage;
    }


    /**
     * 详细地址
     */
    private String detailedAddress;

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    private Integer hospitalid;//救助医院id
    private Integer firstaidid;//指派救护车急救中心Id
    private String ambulancepic1;//救护车发送图片1
    private String ambulancepic2;//救护车发送图片2
    private String ambulancepic3;//救护车发送图片3
    private String ambulancepic4;//救护车发送图片4
    private String ambulancepic5;//救护车发送图片5

    private String ambulancevideo1;//救护车发送视频1
    private String ambulancevideo2;//救护车发送视频2
    private String ambulancevideo3;//救护车发送视频3
    private String ambulancevideo4;//救护车发送视频4

    private Integer hosendstate;//医院是否已经处理（0:未处理 1:已经处理）

    private String ambulancedepict;//救护车内描述

    private String areacode;//地区编码

    private Integer voluntee;

    private Integer frequency;

    private String hzqx;
    private String kb1;
    private String yingyang1;
    private String tem;
    private String xl;
    private String hx;


    public Integer getVoluntee() {
        return voluntee;
    }

    public void setVoluntee(Integer voluntee) {
        this.voluntee = voluntee;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }



    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }


    private String citycode;//城市编码

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    /**
     * 用户ID
     */
    private Integer uid;

    private Integer kid;

    /**
     * 状况
     */
    private String situation;

    /**
     * 位置
     */
    private String location;

    /**
     * 经度
     */
    private Double x;

    /**
     * 纬度
     */
    private Double y;

    /**
     * 是否发送健康信息
     */
    private Integer infoflag;

    private Integer kininfoflag;

    private String pic;

    private String video;

    /**
     * 时间
     */
    private Date time;

    private String type;

    private Integer accept;//医生是否接受救助

    private Integer doctorid;//救助医生id

    private Integer rescue;//救护车是否接受救助

    private Integer ambulance;//救助救护车id

    /**
     * 状态（0：未处理，1：未处理）
     */
    private Integer status;

    public Integer getFirstaidid() {

        return firstaidid;
    }
    public Integer getRestState() {
        return restState;
    }

    public void setRestState(Integer restState) {
        this.restState = restState;
    }

    public String getRestRemark() {
        return restRemark;
    }

    public void setRestRemark(String restRemark) {
        this.restRemark = restRemark;
    }
    public void setFirstaidid(Integer firstaidid) {
        this.firstaidid = firstaidid;
    }

    /**
     * 系统时间
     */
    private Date systemtime;


    private String photo;

    private String phone;

    private String areaname;


    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    @Transient
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getKininfoflag() {
        return kininfoflag;
    }

    public void setKininfoflag(Integer kininfoflag) {
        this.kininfoflag = kininfoflag;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Transient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    /**
     * 获取用户ID
     *
     * @return uid - 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户ID
     *
     * @param uid 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取位置
     *
     * @return location - 位置
     */
    public String getLocation() {
        return location;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    /**
     * 设置位置
     *
     * @param location 位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取经度
     *
     * @return x - 经度
     */
    public Double getX() {
        return x;
    }

    /**
     * 设置经度
     *
     * @param x 经度
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * 获取纬度
     *
     * @return y - 纬度
     */
    public Double getY() {
        return y;
    }


    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 设置纬度
     *
     * @param y 纬度
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * 获取是否发送健康信息
     *
     * @return infoflag - 是否发送健康信息
     */
    public Integer getInfoflag() {
        return infoflag;
    }

    /**
     * 设置是否发送健康信息
     *
     * @param infoflag 是否发送健康信息
     */
    public void setInfoflag(Integer infoflag) {
        this.infoflag = infoflag;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取状态（0：未处理，1：未处理）
     *
     * @return status - 状态（0：未处理，1：未处理）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（0：未处理，1：未处理）
     *
     * @param status 状态（0：未处理，1：未处理）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取系统时间
     *
     * @return systemtime - 系统时间
     */
    public Date getSystemtime() {
        return systemtime;
    }

    /**
     * 设置系统时间
     *
     * @param systemtime 系统时间
     */
    public void setSystemtime(Date systemtime) {
        this.systemtime = systemtime;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public Integer getRescue() {
        return rescue;
    }

    public void setRescue(Integer rescue) {
        this.rescue = rescue;
    }

    public Integer getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Integer ambulance) {
        this.ambulance = ambulance;
    }

    public Integer getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(Integer hospitalid) {
        this.hospitalid = hospitalid;
    }

    public String getAmbulancedepict() { return ambulancedepict; }

    public void setAmbulancedepict(String ambulancedepict) { this.ambulancedepict = ambulancedepict; }

    public String getAmbulancepic1() {
        return ambulancepic1;
    }

    public void setAmbulancepic1(String ambulancepic1) {
        this.ambulancepic1 = ambulancepic1;
    }

    public String getAmbulancepic2() {
        return ambulancepic2;
    }

    public void setAmbulancepic2(String ambulancepic2) {
        this.ambulancepic2 = ambulancepic2;
    }

    public String getAmbulancepic3() {
        return ambulancepic3;
    }

    public void setAmbulancepic3(String ambulancepic3) {
        this.ambulancepic3 = ambulancepic3;
    }

    public String getAmbulancepic4() {
        return ambulancepic4;
    }

    public void setAmbulancepic4(String ambulancepic4) {
        this.ambulancepic4 = ambulancepic4;
    }

    public String getAmbulancepic5() {
        return ambulancepic5;
    }

    public void setAmbulancepic5(String ambulancepic5) {
        this.ambulancepic5 = ambulancepic5;
    }

    public String getAmbulancevideo1() {
        return ambulancevideo1;
    }

    public void setAmbulancevideo1(String ambulancevideo1) {
        this.ambulancevideo1 = ambulancevideo1;
    }

    public String getAmbulancevideo2() {
        return ambulancevideo2;
    }

    public void setAmbulancevideo2(String ambulancevideo2) {
        this.ambulancevideo2 = ambulancevideo2;
    }

    public String getAmbulancevideo3() {
        return ambulancevideo3;
    }

    public void setAmbulancevideo3(String ambulancevideo3) {
        this.ambulancevideo3 = ambulancevideo3;
    }

    public String getAmbulancevideo4() {
        return ambulancevideo4;
    }

    public void setAmbulancevideo4(String ambulancevideo4) {
        this.ambulancevideo4 = ambulancevideo4;
    }

    public Integer getHosendstate() {
        return hosendstate;
    }

    public void setHosendstate(Integer hosendstate) {
        this.hosendstate = hosendstate;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getHzqx() {
        return hzqx;
    }

    public void setHzqx(String hzqx) {
        this.hzqx = hzqx;
    }

    public String getKb1() {
        return kb1;
    }

    public void setKb1(String kb1) {
        this.kb1 = kb1;
    }

    public String getYingyang1() {
        return yingyang1;
    }

    public void setYingyang1(String yingyang1) {
        this.yingyang1 = yingyang1;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getHx() {
        return hx;
    }

    public void setHx(String hx) {
        this.hx = hx;
    }

}
