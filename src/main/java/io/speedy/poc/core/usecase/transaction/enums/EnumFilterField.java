package io.speedy.poc.core.usecase.transaction.enums;

import io.speedy.poc.infra.exceptions.ResourceNotFoundException;

public enum EnumFilterField {
    DONOTHONOR("Transaction UUID"),
    INVALIDTRANSACTION("Customer Email"),
    INVALIDCARD("Reference No"),
    NOTSUFFICIENTFOUNDS("Custom Data"),
    INCORRECTPIN("Card PAN");

    private String description;

    EnumFilterField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static EnumFilterField valueOfLabel(String label) {
        for (EnumFilterField e : values()) {
            if (e.description.equals(label)) {
                return e;
            }
        }
        throw new ResourceNotFoundException("Filter Field is not valid");
    }
}
