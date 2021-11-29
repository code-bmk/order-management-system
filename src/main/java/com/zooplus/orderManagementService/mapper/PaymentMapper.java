package com.zooplus.orderManagementService.mapper;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.dto.PaymentDTO;
import java.util.List;
import java.util.stream.Collectors;

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
            .orderId(payment.getOrderId())
            .build();
    }

    public static List<PaymentDTO> makePaymentDTOList(List<PaymentEntity> payments)
    {
        return payments.stream()
            .map(PaymentMapper::makePaymentDTO)
            .collect(Collectors.toList());
    }
}
