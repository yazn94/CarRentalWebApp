package com.atypon.showresults.secondaryClasses;

public class AnalyticsEntity {
    private String mostRentedCarModel;
    private String mostFrequentCustomer;
    private String date;

    public AnalyticsEntity(String mostFrequentCustomer, String mostRentedCarModel, String date) {
        this.mostRentedCarModel = mostRentedCarModel;
        this.mostFrequentCustomer = mostFrequentCustomer;
        this.date = date;
    }

    public AnalyticsEntity() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMostRentedCarModel() {
        return mostRentedCarModel;
    }

    public void setMostRentedCarModel(String mostRentedCarModel) {
        this.mostRentedCarModel = mostRentedCarModel;
    }

    public String getMostFrequentCustomer() {
        return mostFrequentCustomer;
    }

    public void setMostFrequentCustomer(String mostFrequentCustomer) {
        this.mostFrequentCustomer = mostFrequentCustomer;
    }
}
