package com.zooplus.orderManagementService.mapper;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dto.CustomerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper
{
    public static CustomerEntity makeCustomerEntity(CustomerDTO customerDTO)
    {
        return new CustomerEntity(customerDTO.getCustomerName());
    }


    public static CustomerDTO makeCustomerDTO(CustomerEntity customerEntity)
    {
        return CustomerDTO.builder()
            .id(customerEntity.getId())
            .customerName(customerEntity.getCustomerName())
            .customerBalance(customerEntity.getCustomerBalance())
            .customerCurrency(customerEntity.getCustomerCurrency())
            .build();

    }


    public static List<CustomerDTO> makeCustomerDTOList(List<CustomerEntity> customers)
    {
        return customers.stream()
            .map(CustomerMapper::makeCustomerDTO)
            .collect(Collectors.toList());
    }
}
