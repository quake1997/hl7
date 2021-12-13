package com.engine.hl7.dto.patient;

public class PatientInfoDto {
    String info;

    public PatientInfoDto() {
    }

    public PatientInfoDto(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
