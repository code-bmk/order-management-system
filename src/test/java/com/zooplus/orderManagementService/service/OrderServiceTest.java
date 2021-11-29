package com.zooplus.orderManagementService.service;

import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dao.repo.OrderRepository;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.service.customer.CustomerService;
import com.zooplus.orderManagementService.service.order.OrderServiceImpl;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.zooplus.orderManagementService.util.MockUtility.getMockOrderDTO;
import static com.zooplus.orderManagementService.util.MockUtility.getMockCustomerEntity;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest
{
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private CustomerService customerService;

    @Test
    public void testCreateOrder() throws EntityNotFoundException
    {
        Long customerId = 1L;
        Long orderId = 1L;
        Mockito.when(customerService.find(customerId)).thenReturn(getMockCustomerEntity(orderId, customerId));
        Integer orderAmount = 100;
        OrderEntity orderEntity = orderService.createOrder(customerId, getMockOrderDTO(orderAmount));
        Assert.assertNotNull(orderEntity);
        Integer actualOrderBalance = -orderAmount;
        Assert.assertEquals(orderEntity.getOrderBalance(), actualOrderBalance);
    }

}
