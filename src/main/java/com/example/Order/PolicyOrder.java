package com.example.Order;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//@Service
public class PolicyOrder {

    @StreamListener(Processor.INPUT)
    public void onEventByObject(@Payload OutOfStock outOfStock){

        System.out.println(outOfStock.getEventType());
        //  if문으로 주문생성일때만 작업 진행
        if("OutOfStock".equals(outOfStock.getEventType()) ){
            System.out.println(outOfStock.toString());
        }
    }

}
