package com.engine.hl7.dto.patient;

import com.engine.hl7.dto.patient.segment.PatientNK1InfoDto;

public class PatientInfosDto {
    private PatientNK1InfoDto patientNK1InfoDto;

    public PatientInfosDto() {
    }

    public PatientInfosDto(PatientNK1InfoDto patientNK1InfoDto) {
        this.patientNK1InfoDto = patientNK1InfoDto;
    }

    public PatientNK1InfoDto getPatientNK1InfoDto() {
        return patientNK1InfoDto;
    }
}
