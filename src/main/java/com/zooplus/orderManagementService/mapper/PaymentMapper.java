package com.zooplus.orderManagementService.mapper;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.dto.PaymentDTO;

public class PaymentMapper
{
    public static PaymentEntity makePaymentEntity(PaymentDTO paymentDTO)
    {
        return new PaymentEntity(paymentDTO.getOrderId(), paymentDTO.getPaymentAmount(), paymentDTO.getPaymentCurrency());
    }


    public static PaymentDTO makePaymentDTO(PaymentEntity payment)
    {
        return PaymentDTO.builder()
            .id(payment.getId())
            .paymentAmount(payment.getPaymentAmount())
            .paymentCurrency(payment.getPaymentCurrency())
            .build();
    }

}
