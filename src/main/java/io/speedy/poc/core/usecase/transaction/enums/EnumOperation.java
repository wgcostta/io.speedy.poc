package io.speedy.poc.core.usecase.transaction.enums;

import io.speedy.poc.infra.exceptions.ResourceNotFoundException;

public enum EnumOperation {
    DIRECT("DIRECT"),
    REFUND("REFUND"),
    STORED("STORED"),
    THREESD("3D"),
    THREEDAUTH("3DAUTH");

    private String description;

    EnumOperation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static EnumOperation valueOfLabel(String label) {
        for (EnumOperation e : values()) {
            if (e.description.equals(label)) {
                return e;
            }
        }
        throw new ResourceNotFoundException("Operation is not valid");
    }
}
