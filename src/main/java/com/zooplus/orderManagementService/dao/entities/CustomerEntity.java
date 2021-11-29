package com.zooplus.orderManagementService.dao.entities;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "customer",
    uniqueConstraints = @UniqueConstraint(name = "uc_customer_name", columnNames = {"customer_name"})
)
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_balance", nullable = false)
    private Integer customerBalance = 0;

    @Column(name = "customer_currency", nullable = false)
    private String customerCurrency = "EUR";

    @OneToMany(mappedBy = "orderCustomerEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orderList  = new ArrayList<>();

    @OneToMany(mappedBy = "paymentCustomerEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentEntity> paymentList = new ArrayList<>();

    public CustomerEntity(String customerName){
        this.customerName = customerName;
    }

}
