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

    /**
     * Constructor based injection
     *
     * @param orderRepository OrderRepository
     * @param customerService CustomerService
     */
    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository, final CustomerService customerService)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    /**
     * Find an order by orderId
     *
     * @param orderId Long
     * @return OrderEntity
     * @throws EntityNotFoundException if no customer with the given id was found.
     */
    @Override
    public OrderEntity find(Long orderId) throws EntityNotFoundException
    {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException(ORDER_NOT_FOUND + orderId));
    }

    /**
     * Gets all orders for a given customerId
     *
     * @param customerId Long
     * @return List<OrderEntity>
     */
    @Override
    public List<OrderEntity> getOrders(Long customerId)
    {
        return orderRepository.findByCustomerId(customerId);
    }

    /**
     * Creates a new order for a given customerId
     *
     * @param customerId Long
     * @param orderDTO OrderDTO
     * @return OrderEntity
     * @throws EntityNotFoundException if a customer with given customerId is not found
     */
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


}
