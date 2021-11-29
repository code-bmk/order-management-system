package com.zooplus.orderManagementService.service.customer;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dto.CustomerDTO;
import com.zooplus.orderManagementService.exception.ConstraintsViolationException;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import java.util.List;

public interface CustomerService
{
    CustomerEntity create(CustomerDTO customerDTO) throws ConstraintsViolationException;

    List<CustomerEntity> getAll();

    CustomerEntity find(Long customerId) throws EntityNotFoundException;


}
