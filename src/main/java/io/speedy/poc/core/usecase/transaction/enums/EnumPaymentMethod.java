package io.speedy.poc.core.usecase.transaction.enums;

import io.speedy.poc.infra.exceptions.ResourceNotFoundException;

public enum EnumPaymentMethod {
    CREDITCARD("CREDITCARD"),
    CUP("CUP"),
    IDEAL("IDEAL"),
    GIROPAY("GIROPAY"),
    STORED("STORED"),
    PAYTOCARD("PAYTOCARD"),
    CEPBANK("CEPBANK"),
    CITADEL("CITADEL"),
    MISTERCASH("MISTERCASH");

    private String description;

    EnumPaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static EnumPaymentMethod valueOfLabel(String label) {
        for (EnumPaymentMethod e : values()) {
            if (e.description.equals(label)) {
                return e;
            }
        }
        throw new ResourceNotFoundException("Payment Method is not valid");
    }
}
