package com.zooplus.orderManagementService.service.customer;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.repo.CustomerRepository;
import com.zooplus.orderManagementService.dto.CustomerDTO;
import com.zooplus.orderManagementService.exception.ConstraintsViolationException;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.mapper.CustomerMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.zooplus.orderManagementService.constants.ExceptionMessageConstants.CUSTOMER_NOT_FOUND;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerEntity create(CustomerDTO customerDTO) throws ConstraintsViolationException
    {
        CustomerEntity customer = CustomerMapper.makeCustomerEntity(customerDTO);
        try
        {
            return customerRepository.save(customer);
        }
        catch (DataIntegrityViolationException exception)
        {
            throw new ConstraintsViolationException(exception.getMessage());
        }
    }


    @Override
    public List<CustomerEntity> getAll()
    {
        return customerRepository.findAll();
    }


    @Override
    public CustomerEntity find(Long customerId) throws EntityNotFoundException
    {
        return findCustomer(customerId);
    }


    private CustomerEntity findCustomer(Long customerId) throws EntityNotFoundException
    {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND + customerId));

    }


}
