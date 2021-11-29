package com.zooplus.orderManagementService.controller;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import com.zooplus.orderManagementService.dto.CustomerDTO;
import com.zooplus.orderManagementService.dto.OrderDTO;
import com.zooplus.orderManagementService.exception.ConstraintsViolationException;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.service.customer.CustomerService;
import com.zooplus.orderManagementService.mapper.CustomerMapper;
import com.zooplus.orderManagementService.mapper.OrderMapper;
import com.zooplus.orderManagementService.service.order.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a customer will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/customers")
public class CustomerController
{

    private final CustomerService customerService;

    private final OrderService orderService;


    @Autowired
    public CustomerController(final CustomerService customerService, final OrderService orderService)
    {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return CustomerMapper.makeCustomerDTOList(customerService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) throws ConstraintsViolationException
    {
        CustomerEntity customerEntity = customerService.create(customerDTO);
        return CustomerMapper.makeCustomerDTO(customerEntity);
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable Long customerId) throws EntityNotFoundException
    {
        CustomerEntity customerEntity = customerService.find(customerId);
        return CustomerMapper.makeCustomerDTO(customerEntity);
    }


    @GetMapping("/{customerId}/orders")
    public List<OrderDTO> getCustomerOrders(@PathVariable Long customerId) throws EntityNotFoundException
    {
        List<OrderEntity> orders = orderService.getOrders(customerId);
        return OrderMapper.makeOrderDTOList(orders);
    }

    @PostMapping("/{customerId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createCustomerOrder(@PathVariable Long customerId, @RequestBody OrderDTO orderDTO) throws EntityNotFoundException
    {
        OrderEntity orderEntity = orderService.createOrder(customerId, orderDTO);
        return OrderMapper.makeOrderDTO(orderEntity);
    }


}
