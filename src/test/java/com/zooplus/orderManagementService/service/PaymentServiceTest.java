package com.zooplus.orderManagementService.service;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.service.customer.CustomerService;
import com.zooplus.orderManagementService.service.order.OrderService;
import com.zooplus.orderManagementService.service.payment.PaymentServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.zooplus.orderManagementService.util.MockUtility.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest
{
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    private final Long orderId = 1L;


    @Test
    public void testMakePaymentForOrder() throws EntityNotFoundException
    {
        Integer paymentAmount = 100;
        Long customerId = 1L;
        Mockito.when(orderService.find(orderId)).thenReturn(getMockOrderEntity(orderId, customerId));
        Mockito.when(customerService.find(customerId)).thenReturn(getMockCustomerEntity(orderId, customerId));
        PaymentEntity paymentEntity = paymentService.makePaymentForOrder(orderId, getMockPaymentDTO(paymentAmount));
        Assert.assertNotNull(paymentEntity);
        Assert.assertEquals(paymentEntity.getOrderId(), orderId);
        Assert.assertEquals(paymentEntity.getPaymentCustomerEntity().getId(), customerId);
        Assert.assertEquals(paymentEntity.getPaymentAmount(), paymentAmount);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testMakePaymentForOrderWithException() throws EntityNotFoundException
    {
        Mockito.when(orderService.find(orderId)).thenThrow(EntityNotFoundException.class);
        paymentService.makePaymentForOrder(orderId, Mockito.any());

    }

}
