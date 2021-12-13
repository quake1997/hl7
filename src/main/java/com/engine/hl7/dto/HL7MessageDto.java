package com.engine.hl7.dto;

public class HL7MessageDto {
    String hl7Message;

    public HL7MessageDto() {
    }

    public HL7MessageDto(String hl7Message) {
        this.hl7Message = hl7Message;
    }

    public String getHl7Message() {
        return hl7Message;
    }

    public void setHl7Message(String hl7Message) {
        this.hl7Message = hl7Message;
    }
}
