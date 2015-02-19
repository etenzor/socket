package com.sample.dto;


public class SimpleDto {

    private String data;
    private Integer num;

    public static SimpleDto sample() {
        SimpleDto simpleDto = new SimpleDto();
        simpleDto.data = "someData";
        simpleDto.num = 5;

        return simpleDto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
