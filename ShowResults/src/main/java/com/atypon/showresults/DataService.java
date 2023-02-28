package com.atypon.showresults;

import com.atypon.showresults.secondaryClasses.AnalyticsDocument;
import com.atypon.showresults.secondaryClasses.AnalyticsEntity;
import com.atypon.showresults.secondaryClasses.CarRentingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataService {

    private final MongoTemplate mongoTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    public DataService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<CarRentingEntity> getAllData() {
        Query query = entityManager.createNativeQuery(
                "SELECT * " +
                        "FROM car_rental "
        );
        List<Object[]> rows = query.getResultList();
        List<CarRentingEntity> carRentingRecordslist = new ArrayList<>();
        for (Object[] row : rows) {
            carRentingRecordslist.add(new CarRentingEntity((Long) row[2], (String) row[1], (String) row[3]));
        }
        return carRentingRecordslist;
    }

    public List<AnalyticsEntity> getAnalyticsEntities() {

        List<AnalyticsDocument> analyticsDocuments = mongoTemplate.findAll(AnalyticsDocument.class);
        List<AnalyticsEntity> analyticsEntities = new ArrayList<>();
        for (AnalyticsDocument analyticsDocument : analyticsDocuments) {
            analyticsEntities.add(new AnalyticsEntity(analyticsDocument.getMostFrequentCustomer(),
                    analyticsDocument.getMostRentedCarModel(), analyticsDocument.getDate().toString()));
        }
        return analyticsEntities;
    }

}
