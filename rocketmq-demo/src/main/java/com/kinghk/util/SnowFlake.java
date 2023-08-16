package com.kinghk.util;

public class SnowFlake {

    // 起始的时间戳
    private final static long START_TIMESTAMP = 1480166465631L;

    // 每一部分占用的位数，符号位不算在内
    private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
    private final static long MACHINE_BIT = 5; // 机器标识占用的位数
    private final static long DATACENTER_BIT = 5; // 数据中心占用的位数

    // 每一部分的最大值
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);

    // 每一部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId; // 数据中心
    private long machineId; // 机器标识
    private long sequence = 0L; // 序列号
    private long lastTimestamp = -1L;// 上一次时间戳

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("Datacenter ID can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("Machine ID can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return 下一个ID
     */
    public synchronized long nextId() {
        long currTimestamp = getTimestamp();
        if (currTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (currTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 序列号已经达到最大值，下一个毫秒
            if (sequence == 0L) {
                currTimestamp = getNextTimestamp();
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = currTimestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return (currTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT // 时间戳部分
                | datacenterId << DATACENTER_LEFT // 数据中心部分
                | machineId << MACHINE_LEFT // 机器标识部分
                | sequence; // 序列号部分
    }

    /**
     * 获取下一个毫秒数
     *
     * @return 下一个毫秒数
     */
    private long getNextTimestamp() {
        long timestamp = getTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getTimestamp();
        }
        return timestamp;
    }

    /**
     * 获取当前的时间戳
     *
     * @return 当前的时间戳
     */
    private long getTimestamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(1, 1);

        long startTime = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
            System.out.println(snowFlake.nextId());
        }

        long endTime = System.nanoTime();

        System.out.println("用时：" + (endTime - startTime) / 1000000 + "ms");
    }
}