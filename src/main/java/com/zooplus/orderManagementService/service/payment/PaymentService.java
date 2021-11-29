package com.zooplus.orderManagementService.service.payment;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.dto.PaymentDTO;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import java.util.List;

public interface PaymentService
{
    List<PaymentEntity> getPayments(Long customerId);

    PaymentEntity makePaymentForOrder(Long orderId, PaymentDTO paymentDTO) throws EntityNotFoundException;

}
