package com.zooplus.orderManagementService.service.order;

import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dto.OrderDTO;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import java.util.List;

public interface OrderService
{

    OrderEntity find(Long orderId) throws EntityNotFoundException;

    List<OrderEntity> getOrders(Long customerId);

    OrderEntity createOrder(Long customerId, OrderDTO orderDTO) throws EntityNotFoundException;

}
