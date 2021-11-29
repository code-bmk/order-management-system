package com.zooplus.orderManagementService.dao.entities;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(
    name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name = "order_balance", nullable = false)
    private Integer orderBalance = 0;

    @Column(name = "order_amount", nullable = false)
    private Integer orderAmount;

    @Column(name = "order_currency", nullable = false)
    private String orderCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity orderCustomerEntity;


    public OrderEntity(Integer orderAmount, String orderCurrency)
    {
        this.orderAmount = orderAmount;
        this.orderCurrency = orderCurrency;
    }
}
