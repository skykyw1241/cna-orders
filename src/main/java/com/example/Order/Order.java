package com.example.Order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.*;

@Entity
@Table(name="ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue
    long id;
    long productId;
    String productName;
    int qty;
    int price;
    String customerName;
    String customerAddr;

    @PostPersist @PostUpdate
    public void onPostPersist(){
        OrderCreated orderCreated = new OrderCreated();
        orderCreated.setProductId(this.getProductId());
        orderCreated.setProductName(this.getProductName());
        orderCreated.setQty(this.qty);
        orderCreated.setPrice(this.price);
        orderCreated.setId(this.id);

        try{
            orderCreated.publish();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("*********************** sending Order Created ***********************");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int quantity) {
        this.qty = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }
}
