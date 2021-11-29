package com.zooplus.orderManagementService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Constraints are being violated")
public class ConstraintsViolationException extends Exception
{

    static final long serialVersionUID = 1170023242504459651L;


    public ConstraintsViolationException(String message)
    {
        super(message);
    }

}
