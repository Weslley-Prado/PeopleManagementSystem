package com.digitalinovationone.peoplemanagementsystem.enums;

public enum PhoneType {
    HOME("home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

    PhoneType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}