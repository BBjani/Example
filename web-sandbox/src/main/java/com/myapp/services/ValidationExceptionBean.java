package com.myapp.services;

public class ValidationExceptionBean {

    private String text;
    private Integer number;

    public ValidationExceptionBean(String text) {
        this.text = text;
    }

    public ValidationExceptionBean(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
