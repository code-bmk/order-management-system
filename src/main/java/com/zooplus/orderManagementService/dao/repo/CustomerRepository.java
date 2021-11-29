package com.zooplus.orderManagementService.dao.repo;

import com.zooplus.orderManagementService.dao.entities.CustomerEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>
{
    List<CustomerEntity> findAll();
}
