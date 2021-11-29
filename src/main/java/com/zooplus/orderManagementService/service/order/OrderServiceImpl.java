package com.zooplus.orderManagementService.service.order;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dao.repo.OrderRepository;
import com.zooplus.orderManagementService.dto.OrderDTO;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.mapper.OrderMapper;
import com.zooplus.orderManagementService.service.customer.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zooplus.orderManagementService.constants.ExceptionMessageConstants.ORDER_NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService
{

    private final OrderRepository orderRepository;
    private final CustomerService customerService;


    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository, final CustomerService customerService)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }


    @Override
    public OrderEntity find(Long orderId) throws EntityNotFoundException
    {
        return findOrder(orderId);
    }


    @Override
    public List<OrderEntity> getOrders(Long customerId)
    {
        return orderRepository.findByCustomerId(customerId);
    }


    @Override
    @Transactional
    public OrderEntity createOrder(Long customerId, OrderDTO orderDTO) throws EntityNotFoundException
    {
        CustomerEntity customer = customerService.find(customerId);
        //Update the customer balance during creation of an order
        customer.setCustomerBalance(customer.getCustomerBalance() - orderDTO.getOrderAmount());

        OrderEntity order = OrderMapper.makeOrderEntity(orderDTO);
        //Update the order balance during creation of an order
        order.setOrderBalance(order.getOrderBalance() - orderDTO.getOrderAmount());
        order.setOrderCustomerEntity(customer);

        List<OrderEntity> customerOrders = customer.getOrderList();
        customerOrders.add(order);
        customer.setOrderList(customerOrders);

        return order;
    }


    private OrderEntity findOrder(Long orderId) throws EntityNotFoundException
    {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException(ORDER_NOT_FOUND + orderId));

    }

}
