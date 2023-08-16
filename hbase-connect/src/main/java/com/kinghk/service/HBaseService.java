package com.kinghk.service;

import com.kinghk.config.HBaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

@Slf4j
@Component
@DependsOn("HBaseConfig")
public class HBaseService {

    @Resource
    private HBaseConfig config;

    private static Admin admin = null;

    public static Configuration conf=null;

    private static Connection connection = null;

    private final ThreadLocal<List<Put>> threadLocal = new ThreadLocal<>();

    private static final int CACHE_LIST_SIZE = 1000;



    @PostConstruct
    private void init() {
        if (connection != null) {
            return;
        }

        try {
            connection = ConnectionFactory.createConnection(config.configuration());
            admin = connection.getAdmin();
        } catch (IOException e) {
            log.error("HBase create connection failed: {}","异常");
        }
    }

    /**
     * 创建表
     * expression : create 'tableName','[Column Family 1]','[Column Family 2]'
     * @param tableName 	  表名
     * @param columnFamilies 列族名
     * @throws IOException io异常
     */
    public void createTable(String tableName, String... columnFamilies) throws IOException {
        TableName name = TableName.valueOf(tableName);
        boolean isExists = this.tableExists(tableName);

        if (isExists) {
            throw new TableExistsException(tableName + "is exists!");
        }

        TableDescriptorBuilder descriptorBuilder = TableDescriptorBuilder.newBuilder(name);
        List<ColumnFamilyDescriptor> columnFamilyList = new ArrayList<>();

        for (String columnFamily : columnFamilies) {
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder
                    .newBuilder(columnFamily.getBytes()).build();
            columnFamilyList.add(columnFamilyDescriptor);
        }

        descriptorBuilder.setColumnFamilies(columnFamilyList);
        TableDescriptor tableDescriptor = descriptorBuilder.build();
        admin.createTable(tableDescriptor);
    }

    /**
     * 插入或更新
     *  expression : put <tableName>,<rowKey>,<family:column>,<value>,<timestamp>
     * @param tableName 	表名
     * @param rowKey		行id
     * @param columnFamily  列族名
     * @param column 		列
     * @param value 		值
     * @throws IOException io异常
     */
    public void insertOrUpdate(String tableName, String rowKey, String columnFamily, String column, String value)
            throws IOException {
        this.insertOrUpdate(tableName, rowKey, columnFamily, new String[]{column}, new String[]{value});
    }

    /**
     *   插入或更新多个字段
     * expression : put <tableName>,<rowKey>,<family:column>,<value>,<timestamp>
     * @param tableName 	表名
     * @param rowKey        行id
     * @param columnFamily  列族名
     * @param columns		列
     * @param values		值
     * @throws IOException io异常
     */
    public void insertOrUpdate(String tableName, String rowKey, String columnFamily, String[] columns, String[] values)
            throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put(Bytes.toBytes(rowKey));

        for (int i = 0; i < columns.length; i++) {
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
            table.put(put);
        }
    }

    /**
     * 删除行
     * @param tableName		表名
     * @param rowKey		行id
     * @throws IOException io异常
     */
    public void deleteRow(String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(rowKey.getBytes());

        table.delete(delete);
    }

    /**
     * 删除列族
     * @param tableName		表名
     * @param rowKey		行id
     * @param columnFamily	列族名
     * @throws IOException io异常
     */
    public void deleteColumnFamily(String tableName, String rowKey, String columnFamily) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(rowKey.getBytes());
        delete.addFamily(Bytes.toBytes(columnFamily));

        table.delete(delete);
    }

    /**
     * 删除列
     * experssion : delete 'tableName','rowKey','columnFamily:column'
     * @param tableName		表名
     * @param rowKey		行id
     * @param columnFamily	列族名
     * @param column		列名
     * @throws IOException io异常
     */
    public void deleteColumn(String tableName, String rowKey, String columnFamily, String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(rowKey.getBytes());
        delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));

        table.delete(delete);
    }

    /**
     * 删除表
     * expression : disable 'tableName' 之后 drop 'tableName'
     * @param tableName 	表名
     * @throws IOException io异常
     */
    public void deleteTable(String tableName) throws IOException {
        boolean isExists = this.tableExists(tableName);

        if (!isExists) {
            return;
        }

        TableName name = TableName.valueOf(tableName);
        admin.disableTable(name);
        admin.deleteTable(name);
    }

    /**
     * 获取值
     * expression : get 'tableName','rowkey','family:column'
     * @param tableName		表名
     * @param rowKey		行id
     * @param family		列族名
     * @param column		列名
     * @return string
     */
    public String getValue(String tableName, String rowKey, String family, String column) {
        Table table = null;
        String value = "";

        if (StringUtils.isBlank(tableName) || StringUtils.isBlank(family) || StringUtils.isBlank(rowKey) || StringUtils
                .isBlank(column)) {
            return null;
        }

        try {
            table = connection.getTable(TableName.valueOf(tableName));
            Get g = new Get(rowKey.getBytes());
            g.addColumn(family.getBytes(), column.getBytes());
            Result result = table.get(g);
            List<Cell> ceList = result.listCells();
            if (ceList != null && ceList.size() > 0) {
                for (Cell cell : ceList) {
                    value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert table != null;
                table.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 查询指定行
     * experssion : get 'tableName','rowKey'
     * @param tableName		表名
     * @param rowKey		行id
     * @return String
     * @throws IOException io异常
     */
    public String selectOneRow(String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        Result result = table.get(get);
        NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result.getMap();

        for (Cell cell : result.rawCells()) {
            String row = Bytes.toString(cell.getRowArray());
            String columnFamily = Bytes.toString(cell.getFamilyArray());
            String column = Bytes.toString(cell.getQualifierArray());
            String value = Bytes.toString(cell.getValueArray());
            // 可以通过反射封装成对象(列名和Java属性保持一致)
            System.out.println(row);
            System.out.println(columnFamily);
            System.out.println(column);
            System.out.println(value);
        }
        return null;
    }


    /**
     *   根据条件取出点位指定时间内的所有记录
     * @param tableName		表名("OPC_TEST")
     * @param family 列簇名("OPC_COLUMNS")
     * @param column 列名("site")
     * @param value 值(采集点标识)
     * @param startMillis 开始时间毫秒值(建议传递当前时间前一小时的毫秒值，在保证查询效率的前提下获取到点位最新的记录
     * @return  Map<String,String>
     * @throws IOException io异常
     */
    public Map<String,String> scanBatchOfTable(String tableName,String family,String [] column,String [] value,Long startMillis,Long endMillis) throws IOException {

        if(Objects.isNull(column) || column.length != value.length) {
            return null;
        }

        FilterList filterList = new FilterList();

        for (int i = 0; i < column.length; i++) {
            SingleColumnValueFilter filter =  new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column[i]), CompareOperator.EQUAL, Bytes.toBytes(value[i]));
            filterList.addFilter(filter);
        }

        Table table = connection.getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();
        scan.setFilter(filterList);

        if(startMillis != null && endMillis != null) {
            scan.setTimeRange(startMillis,endMillis);
        }

        return getStringStringMap(table, scan);
    }

    /**
     *   根据条件取出点位最近时间的一条记录
     * expressions : scan 't1',{FILTER=>"PrefixFilter('2015')"}
     * @param tableName		表名("OPC_TEST")
     * @param family 列簇名("OPC_COLUMNS")
     * @param column 列名("site")
     * @param value 值(采集点标识)
     * @param startMillis 开始时间毫秒值(建议传递当前时间前一小时的毫秒值，在保证查询效率的前提下获取到点位最新的记录)
     * @return Map<String,String>
     * @throws IOException io异常
     */
    public Map<String,String> scanOneOfTable(String tableName, String family, String column, String value, Long startMillis, Long endMillis) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();
        scan.setReversed(true);

        PageFilter pageFilter = new PageFilter(1);
        scan.setFilter(pageFilter);

        if(startMillis != null && endMillis != null) {
            scan.setTimeRange(startMillis,endMillis);
        }

        if (StringUtils.isNotBlank(column)) {
            SingleColumnValueFilter filter =  new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column), CompareOperator.EQUAL, Bytes.toBytes(value));
            scan.setFilter(filter);
        }

        return getStringStringMap(table, scan);
    }

    private Map<String, String> getStringStringMap(Table table, Scan scan) throws IOException {
        ResultScanner scanner = table.getScanner(scan);
        Map<String,String> resultMap = new HashMap<>();
        try {
            for(Result result:scanner){
                for(Cell cell:result.rawCells()){
                    String values= Bytes.toString(CellUtil.cloneValue(cell));
                    String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell));

                    resultMap.put(qualifier, values);
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return resultMap;
    }


    /**
     * 判断表是否已经存在，这里使用间接的方式来实现
     * @param tableName 表名
     * @return boolean
     * @throws IOException io异常
     */
    public boolean tableExists(String tableName) throws IOException {
        TableName[] tableNames = admin.listTableNames();
        if (tableNames != null && tableNames.length > 0) {
            for (TableName name : tableNames) {
                if (tableName.equals(name.getNameAsString())) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     *  批量添加
     *
     * @param tableName HBase表名
     * @param rowKey    HBase表的rowkey
     * @param values     写入HBase表的值value
     * @param flag      提交标识符号。需要立即提交时，传递，值为 “end”
     */
    public void bulkPut(String tableName, String rowKey, String columnFamily, String [] columns, String [] values,String flag) {
        try {
            List<Put> list = threadLocal.get();
            if (list == null) {
                list = new ArrayList<Put>();
            }

            Put put = new Put(Bytes.toBytes(rowKey));

            for (int i = 0; i < columns.length; i++) {
                put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
                list.add(put);
            }

            if (list.size() >= HBaseService.CACHE_LIST_SIZE || "end".equals(flag)) {

                Table table = connection.getTable(TableName.valueOf(tableName));
                table.put(list);

                list.clear();
            } else {
                threadLocal.set(list);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            threadLocal.remove();
        }
    }

    /**
     * 根据表名获取table
     */
    private Table getTable(String tableName) throws IOException {
        return connection.getTable(TableName.valueOf(tableName));
    }

    /**
     * 查询所有表的表名
     */
    public List<String> getAllTableNames() {
        List<String> result = new ArrayList<>();
        try {
            TableName[] tableNames = admin.listTableNames();
            for (TableName tableName : tableNames) {
                result.add(tableName.getNameAsString());
            }
        } catch (IOException e) {
            log.error("获取所有表的表名失败", e);
        } finally {
            close(admin,null,null);
        }
        return result;
    }

    /**
     * 遍历查询指定表中的所有数据
     */
    public Map<String, Map<String, String>> getResultScanner(String tableName) {
        Scan scan = new Scan();
        return this.queryData(tableName, scan);
    }

    /**
     * 通过表名及过滤条件查询数据
     */
    private Map<String, Map<String, String>> queryData(String tableName, Scan scan) {
        // <rowKey,对应的行数据>
        Map<String, Map<String, String>> result = new HashMap<>();
        ResultScanner rs = null;
        //获取表
        Table table = null;
        try {
            table = getTable(tableName);
            rs = table.getScanner(scan);
            for (Result r : rs) {
                // 每一行数据
                Map<String, String> columnMap = new HashMap<>();
                String rowKey = null;
                // 行键，列族和列限定符一起确定一个单元（Cell）
                for (Cell cell : r.listCells()) {
                    if (rowKey == null) {
                        rowKey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    }
                    columnMap.put(
                            //列限定符
                            Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),
                            //列族
                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                }
                if (rowKey != null) {
                    result.put(rowKey, columnMap);
                }
            }
        } catch (IOException e) {
            log.error(MessageFormat.format("遍历查询指定表中的所有数据失败,tableName:{0}", tableName), e);
        } finally {
            close(null, rs, table);
        }
        return result;
    }

    /**
     * 关闭流
     */
    private void close(Admin admin, ResultScanner rs, Table table) {
        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                log.error("关闭Admin失败", e);
            }
            if (rs != null) {
                rs.close();
            }
            if (table != null) {
                assert rs != null;
                rs.close();
            }
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    log.error("关闭Table失败", e);
                }
            }
        }
    }
}
