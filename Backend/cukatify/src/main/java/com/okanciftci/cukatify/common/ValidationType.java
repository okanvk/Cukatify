package com.okanciftci.cukatify.common;

public enum ValidationType {
    SELECT		(0),
    INSERT		(1),
    UPDATE	(2),
    DELETE		(3);

    private int value;

    private ValidationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}


