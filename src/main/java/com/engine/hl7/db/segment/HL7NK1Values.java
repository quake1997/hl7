package com.engine.hl7.db.segment;

public enum HL7NK1Values {
    FAMILY_NAME("NK1-2-1"),
    GIVEN_NAME("NK1-2-2"),
    INITIALS_THEREOF("NK1-2-3"),
    RELATIONSHIP("NK1-3"),
    STREET_ADDRESS("NK1-4-1");

    private String value;

    HL7NK1Values(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
