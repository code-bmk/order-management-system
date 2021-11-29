package com.zooplus.orderManagementService.dao.repo;

import com.zooplus.orderManagementService.dao.entities.PaymentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long>
{
    @Query(value = "SELECT * FROM customer_payment where customer_payment.customer_id = :customerId",nativeQuery = true)
    List<PaymentEntity> findByCustomerId(Long customerId);

}

