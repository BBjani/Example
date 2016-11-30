package com.myapp.services;

/**
 *
 *
 */
public class ValidationExceptionBean {

    private String text;
    private Integer number;

    /**
     *
     * @param text
     */
    public ValidationExceptionBean(final String text) {
        this.text = text;
    }

    /**
     *
     * @param number
     */
    public ValidationExceptionBean(final Integer number) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(final Integer number) {
        this.number = number;
    }
}
