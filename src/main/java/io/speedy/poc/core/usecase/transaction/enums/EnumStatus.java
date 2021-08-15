package io.speedy.poc.core.usecase.transaction.enums;

import io.speedy.poc.infra.exceptions.ResourceNotFoundException;

public enum EnumStatus {
    APPROVED("APPROVED"),
    WAITING("WAITING"),
    DECLINED("DECLINED"),
    ERROR("ERROR");

    private String description;

    EnumStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static EnumStatus valueOfLabel(String label) {
        for (EnumStatus e : values()) {
            if (e.description.equals(label)) {
                return e;
            }
        }
        throw new ResourceNotFoundException("Status is not valid");
    }
}
