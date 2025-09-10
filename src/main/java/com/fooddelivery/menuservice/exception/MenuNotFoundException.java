package com.fooddelivery.menuservice.exception;

public class MenuNotFoundException extends Exception {
	public MenuNotFoundException(String message) {
		super(message);
	}
}