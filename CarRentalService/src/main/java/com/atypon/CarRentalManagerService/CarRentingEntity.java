package com.atypon.CarRentalManagerService;


import jakarta.persistence.*;

@Entity
@Table(name = "car_rental")
public class CarRentingEntity {

    @SequenceGenerator(
            name = "car_rental_sequence",
            sequenceName = "car_rental_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_rental_sequence"
    )
    @Id
    private Long id;

    @Column
    private Long customerSsn;
    @Column
    private String customerName;
    @Column
    private String rentedCarModel;

    public CarRentingEntity() {
    }

    public CarRentingEntity(Long customerSsn, String customerName, String rentedCarModel) {
        this.customerSsn = customerSsn;
        this.customerName = customerName;
        this.rentedCarModel = rentedCarModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerSsn() {
        return customerSsn;
    }

    public void setCustomerSsn(Long customerSsn) {
        this.customerSsn = customerSsn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRentedCarModel() {
        return rentedCarModel;
    }

    public void setRentedCarModel(String rentedCarModel) {
        this.rentedCarModel = rentedCarModel;
    }
}