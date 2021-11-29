package com.zooplus.orderManagementService.util;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dto.CustomerDTO;
import com.zooplus.orderManagementService.dto.OrderDTO;
import com.zooplus.orderManagementService.dto.PaymentDTO;
import java.util.ArrayList;
import java.util.List;

public class MockUtility
{
    public static PaymentDTO getMockPaymentDTO(Integer paymentAmount){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentAmount(paymentAmount);
        paymentDTO.setPaymentCurrency("EUR");
        return paymentDTO;
    }

    public static OrderDTO getMockOrderDTO(Integer orderAmount){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderCurrency("EUR");
        return orderDTO;
    }
    public static CustomerDTO getMockCustomerDTO(String customerName){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customerName);
        return customerDTO;
    }

    public static OrderEntity getMockOrderEntity(Long orderId, Long customerId){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setOrderBalance(0);
        orderEntity.setOrderAmount(-100);
        orderEntity.setOrderCurrency("EUR");
        orderEntity.setOrderCustomerEntity(getMockCustomerEntity(orderId, customerId));
        return orderEntity;
    }

    public static OrderEntity getMockOrderEntity(Long orderId){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setOrderBalance(0);
        orderEntity.setOrderAmount(-100);
        orderEntity.setOrderCurrency("EUR");
        return orderEntity;
    }
    public static CustomerEntity getMockCustomerEntity(Long orderId, Long customerId){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setCustomerName("rakesh");
        customerEntity.setCustomerBalance(0);
        customerEntity.setCustomerCurrency("EUR");
        customerEntity.setOrderList(getMockOrders(orderId));
        return customerEntity;
    }

    private static List<OrderEntity> getMockOrders(Long orderId){
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(getMockOrderEntity(orderId));
        return orders;
    }
}
