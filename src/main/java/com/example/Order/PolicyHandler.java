package com.example.Order;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {

    @StreamListener(KafkaProcessor.productChanged_INPUT)
    public void onEventByProductChanged(@Payload ProductChanged productChanged){
        //  orderPlaced 데이터를 json -> 객체로 파싱
        System.out.println("onEventByProductChanged");
        System.out.println(productChanged.getEventType());
        //  if문으로 주문생성일때만 작업 진행
        if("ProductChanged".equals(productChanged.getEventType()) ){
            System.out.println("productId="+productChanged.getProductId());
            System.out.println("productName="+productChanged.getProductName());
            System.out.println("stock="+productChanged.getProductStock());
        }
    }

    @StreamListener(KafkaProcessor.productExcept_INPUT)
    public void onEventByProductExcept(@Payload ProductExcept outOfStock){
        System.out.println("onEventByProductExcept");
        System.out.println(outOfStock.getEventType());
        //  if문으로 주문생성일때만 작업 진행
        if("OutOfStock".equals(outOfStock.getEventType()) ){
            System.out.println("orderId="+outOfStock.getOrderId());
            System.out.println("productId="+outOfStock.getProductId());
        }
    }

}
