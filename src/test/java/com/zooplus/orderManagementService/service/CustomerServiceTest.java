package com.zooplus.orderManagementService.service;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.repo.CustomerRepository;
import com.zooplus.orderManagementService.exception.ConstraintsViolationException;
import com.zooplus.orderManagementService.service.customer.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.zooplus.orderManagementService.util.MockUtility.getMockCustomerDTO;
import static com.zooplus.orderManagementService.util.MockUtility.getMockCustomerEntity;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest
{
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer() throws ConstraintsViolationException
    {
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(getMockCustomerEntity(1L, 1L));
        CustomerEntity customerEntity = customerService.create(getMockCustomerDTO("rakesh"));
        Assert.assertNotNull(customerEntity);
    }
}
