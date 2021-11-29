package com.zooplus.orderManagementService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot find an entity with given id")
public class EntityNotFoundException extends Exception
{
    static final long serialVersionUID = -8006347130927564843L;


    public EntityNotFoundException(String message)
    {
        super(message);
    }

}
