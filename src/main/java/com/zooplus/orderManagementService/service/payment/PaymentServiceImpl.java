package com.zooplus.orderManagementService.service.payment;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.dto.PaymentDTO;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.mapper.PaymentMapper;
import com.zooplus.orderManagementService.service.customer.CustomerService;
import com.zooplus.orderManagementService.service.order.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService
{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public PaymentEntity makePaymentForOrder(Long orderId, PaymentDTO paymentDTO) throws EntityNotFoundException
    {
        OrderEntity order = orderService.find(orderId);
        CustomerEntity customer = customerService.find(order.getOrderCustomerEntity().getId());
        PaymentEntity payment = PaymentMapper.makePaymentEntity(paymentDTO);

        customer.setCustomerBalance(customer.getCustomerBalance() + paymentDTO.getPaymentAmount());
        order.setOrderBalance(order.getOrderBalance() + paymentDTO.getPaymentAmount());

        updatePaymentRelationship(customer, orderId, payment);
        return payment;
    }

    private void updatePaymentRelationship(CustomerEntity customer, Long orderId, PaymentEntity payment)
    {
        payment.setOrderId(orderId);
        payment.setPaymentCustomerEntity(customer);

        List<PaymentEntity> customerPayments = customer.getPaymentList();
        customerPayments.add(payment);

        customer.setPaymentList(customerPayments);
    }


}
