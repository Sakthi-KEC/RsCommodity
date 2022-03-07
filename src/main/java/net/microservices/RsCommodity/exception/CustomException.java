package net.microservices.RsCommodity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomException extends Exception
{
    public CustomException(String message)
    {
        super(message);
    }
}