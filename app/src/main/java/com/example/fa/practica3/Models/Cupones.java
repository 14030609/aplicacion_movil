package com.example.fa.practica3.Models;

/**
 * Created by susa on 10/12/17.
 */

public class Cupones
{
    private int id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private int percentage;

    /**
     * No args constructor for use in serialization
     *
     */
    public Cupones() {
    }

    /**
     *
     * @param id
     * @param startDate
     * @param percentage
     * @param description
     * @param name
     * @param endDate
     */
    public Cupones(int id, String name, String description, String startDate, String endDate, int percentage) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
