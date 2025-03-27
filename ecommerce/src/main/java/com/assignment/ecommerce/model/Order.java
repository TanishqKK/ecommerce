package com.assignment.ecommerce.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private Date orderDate;
    private String Address ;
    private double totalCost;



    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName){this.customerName = customerName;}
    public Date getOrderDate() {return orderDate;}
    public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}
    public String getAddress() { return Address; }
    public void setAddress(String Address) { this.Address= Address; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }


}
