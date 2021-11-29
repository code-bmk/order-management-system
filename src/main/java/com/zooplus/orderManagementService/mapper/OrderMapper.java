package com.zooplus.orderManagementService.mapper;

import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper
{
    public static OrderEntity makeOrderEntity(OrderDTO orderDTO)
    {
        return new OrderEntity(orderDTO.getOrderAmount(), orderDTO.getOrderCurrency());
    }


    public static OrderDTO makeOrderDTO(OrderEntity order)
    {
        return OrderDTO.builder()
            .id(order.getId())
            .orderBalance(order.getOrderBalance())
            .orderAmount(order.getOrderAmount())
            .orderCurrency(order.getOrderCurrency())
            .build();
    }


    public static List<OrderDTO> makeOrderDTOList(List<OrderEntity> orders)
    {
        return orders.stream()
            .map(OrderMapper::makeOrderDTO)
            .collect(Collectors.toList());
    }
}
