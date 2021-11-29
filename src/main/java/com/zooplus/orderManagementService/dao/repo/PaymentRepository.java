package com.zooplus.orderManagementService.dao.repo;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long>
{

}

