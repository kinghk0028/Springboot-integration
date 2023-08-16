package com.kinghk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private String number;
    private String actionHistory;
    private String diseaseType;
    private String systemtime;
    private String phone;
    private String location;
    private String action;
    private String carNo;
    private String institution;
    private String sendTime;
    private String taskResult;
    private String name;
    private String age;
    private String goOutTime;
    private String cureCount;
}

