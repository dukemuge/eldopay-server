package com.eldopay.companyservice.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternalLNMRequest{

	@JsonProperty("CheckoutRequestID")
	private String checkoutRequestID;
}