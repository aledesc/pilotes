package com.tui.pilotes.order;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrderValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clientId", "clientId.empty", "Client Id is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "productId.empty", "Product Id is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "quantity.empty", "Quantity is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressId", "addressId.empty", "Address Id is required.");
    }
}