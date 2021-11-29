package com.zooplus.orderManagementService.dao.repo;

import com.zooplus.orderManagementService.dao.entities.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long>
{
    @Query(value = "SELECT * FROM customer_order where customer_order.customer_id = :customerId",nativeQuery = true)
    List<OrderEntity> findByCustomerId(Long customerId);

    List<OrderEntity> findAll();
}
