package com.zooplus.orderManagementService.controller;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.dto.PaymentDTO;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.mapper.PaymentMapper;
import com.zooplus.orderManagementService.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with an order will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/orders")
public class OrderController
{

    private final PaymentService paymentService;

    @Autowired
    public OrderController(final PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO createPaymentForOrder(@PathVariable Long orderId, @RequestBody PaymentDTO paymentDTO) throws EntityNotFoundException
    {
        PaymentEntity paymentEntity = paymentService.makePaymentForOrder(orderId, paymentDTO);
        return PaymentMapper.makePaymentDTO(paymentEntity);
    }

}
