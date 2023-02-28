package com.atypon.showresults.secondaryClasses;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "analytics")
public class AnalyticsDocument {
    private String mostFrequentCustomer;
    private String mostRentedCarModel;
    private String date;

    public AnalyticsDocument(String mostFrequentCustomer, String mostRentedCarModel, String date) {
        this.mostFrequentCustomer = mostFrequentCustomer;
        this.mostRentedCarModel = mostRentedCarModel;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMostFrequentCustomer() {
        return mostFrequentCustomer;
    }

    public void setMostFrequentCustomer(String mostFrequentCustomer) {
        this.mostFrequentCustomer = mostFrequentCustomer;
    }

    public String getMostRentedCarModel() {
        return mostRentedCarModel;
    }

    public void setMostRentedCarModel(String mostRentedCarModel) {
        this.mostRentedCarModel = mostRentedCarModel;
    }
}