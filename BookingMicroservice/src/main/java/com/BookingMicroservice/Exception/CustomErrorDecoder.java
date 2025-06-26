package com.BookingMicroservice.Exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new FlightNotFoundException("No Flights with the given flightId.");
        }

        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            return new FlightHandlingException("Invalid request: Please check the input parameters.");
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
