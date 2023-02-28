package com.atypon.analyticsservice;

import com.atypon.analyticsservice.MongoRelated.AnalyticsDocument;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Service
@Transactional(readOnly = true)
@RestController

public class AnalyticsService {
    private final MongoTemplate mongoTemplate;
    private String mostFrequentCustomer, mostRentedCarModel;
    @PersistenceContext
    private EntityManager entityManager;

    public AnalyticsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/update-analytics")
    public void updateAnalytics() {
        mostRentedCarModel = getMostRentedCar();
        mostFrequentCustomer = getMostFrequentCustomer();
        AnalyticsDocument document = new AnalyticsDocument(
                mostFrequentCustomer, mostRentedCarModel, String.valueOf(new Date())
        );

        mongoTemplate.insert(document);
    }

    public String getMostFrequentCustomer() {
        Query query = entityManager.createNativeQuery(
                "SELECT customer_name " +
                        "FROM car_rental " +
                        "GROUP BY customer_name " +
                        "ORDER BY COUNT(*) DESC " +
                        "LIMIT 1"
        );
        Object result = query.getSingleResult();
        return result != null ? result.toString() : null;
    }

    public String getMostRentedCar() {
        Query query = entityManager.createNativeQuery(
                "SELECT rented_car_model " +
                        "FROM car_rental " +
                        "GROUP BY rented_car_model " +
                        "ORDER BY COUNT(*) DESC " +
                        "LIMIT 1"
        );
        Object result = query.getSingleResult();
        return result != null ? result.toString() : null;
    }
}
