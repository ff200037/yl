package com.yile.micro.order.exception;

public class PriceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public PriceException() {
    }

    public PriceException(String message) {
        super(message);
    }
}
