package io.speedy.poc.core.usecase.transaction.enums;

import io.speedy.poc.infra.exceptions.ResourceNotFoundException;

public enum EnumErrorCode {
    DONOTHONOR("Do not honor"),
    INVALIDTRANSACTION("Invalid Transaction"),
    INVALIDCARD("Invalid Card"),
    NOTSUFFICIENTFOUNDS("Not sufficient funds"),
    INCORRECTPIN("Incorrect PIN"),
    INVALIDCOUNTRYASSOCIATION("Invalid country association"),
    CURRENCYNOTALLOWED("Currency not allowed"),
    THREEDSECURETRANSPORTERROR("3-D Secure Transport Error"),
    TRANSACTIONNOTPERMITTEDTOCARDHOLDER("Transaction not permitted to cardholder");

    private String description;

    EnumErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static EnumErrorCode valueOfLabel(String label) {
        for (EnumErrorCode e : values()) {
            if (e.description.equals(label)) {
                return e;
            }
        }
        throw new ResourceNotFoundException("Error Code is not valid");
    }
}
