package com.zooplus.orderManagementService.service.payment;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import com.zooplus.orderManagementService.dao.repo.PaymentRepository;
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
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;
    private final CustomerService customerService;


    @Autowired
    public PaymentServiceImpl(final PaymentRepository paymentRepository, final OrderService orderService, final CustomerService customerService)
    {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
        this.customerService = customerService;
    }


    @Override
    public List<PaymentEntity> getPayments(Long customerId)
    {
        return paymentRepository.findByCustomerId(customerId);
    }


    @Override
    @Transactional
    public PaymentEntity makePaymentForOrder(Long orderId, PaymentDTO paymentDTO) throws EntityNotFoundException
    {
        OrderEntity order = orderService.find(orderId);
        CustomerEntity customer = customerService.find(order.getOrderCustomerEntity().getId());
        PaymentEntity payment = PaymentMapper.makePaymentEntity(paymentDTO);

        //Update the customer balance during payment of an order
        customer.setCustomerBalance(customer.getCustomerBalance() + paymentDTO.getPaymentAmount());
        //Update the order balance during payment of an order
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
