package com.zooplus.orderManagementService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
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
public class CustomerDTO
{

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("customer_name")
    @NotNull
    private String customerName;

    @JsonProperty(value = "customer_balance", access = JsonProperty.Access.READ_ONLY)
    private Integer customerBalance;

    @JsonProperty(value = "customer_currency", access = JsonProperty.Access.READ_ONLY)
    private String customerCurrency;
}
