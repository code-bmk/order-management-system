package com.zooplus.orderManagementService.service.customer;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import com.zooplus.orderManagementService.dao.repo.CustomerRepository;
import com.zooplus.orderManagementService.dto.CustomerDTO;
import com.zooplus.orderManagementService.exception.ConstraintsViolationException;
import com.zooplus.orderManagementService.exception.EntityNotFoundException;
import com.zooplus.orderManagementService.mapper.CustomerMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.zooplus.orderManagementService.constants.ExceptionMessageConstants.CONSTRAINTS_VIOLATION_EXCEPTION_MESSAGE;
import static com.zooplus.orderManagementService.constants.ExceptionMessageConstants.CUSTOMER_NOT_FOUND;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerRepository customerRepository;


    /**
     * Constructor based injection
     *
     * @param customerRepository CustomerRepository
     */
    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }


    /**
     * Find a customer by customerId
     *
     * @param customerId Long
     * @return CustomerEntity
     * @throws EntityNotFoundException if no customer with the given id was found.
     */
    @Override
    public CustomerEntity find(Long customerId) throws EntityNotFoundException
    {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND + customerId));
    }


    /**
     * Creates a new customer
     *
     * @param customerDTO CustomerDTO
     * @return CustomerEntity
     * @throws ConstraintsViolationException if a customer with given customer_name already exists
     */
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
            log.warn(CONSTRAINTS_VIOLATION_EXCEPTION_MESSAGE, customerDTO);
            throw new ConstraintsViolationException(exception.getMessage());
        }
    }

    /**
     * Find all customers
     *
     * @return List<CustomerEntity>
     */
    @Override
    public List<CustomerEntity> findAll()
    {
        return customerRepository.findAll();
    }


}
