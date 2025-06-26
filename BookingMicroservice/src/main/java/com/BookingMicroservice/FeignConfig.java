package com.BookingMicroservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.BookingMicroservice.Exception.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
}
