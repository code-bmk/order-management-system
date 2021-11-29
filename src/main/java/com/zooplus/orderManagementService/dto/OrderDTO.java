package com.zooplus.orderManagementService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO
{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "order_balance", access = JsonProperty.Access.READ_ONLY)
    private Integer orderBalance;

    @JsonProperty(value = "order_amount", access = JsonProperty.Access.WRITE_ONLY)
    private Integer orderAmount;

    @JsonProperty("order_currency")
    private String orderCurrency;

}
