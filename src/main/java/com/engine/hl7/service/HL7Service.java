package com.engine.hl7.service;

import com.engine.hl7.db.segment.HL7NK1Values;
import com.engine.hl7.db.InMemoryDBService;
import com.engine.hl7.dto.patient.PatientInfoDto;
import com.engine.hl7.dto.patient.PatientInfosDto;
import com.engine.hl7.dto.patient.segment.PatientNK1InfoDto;
import com.engine.hl7.service.parser.HL7ParserService;
import org.springframework.stereotype.Service;

@Service
public class HL7Service {
    private final HL7ParserService hl7ParserService;
    private final InMemoryDBService inMemoryDBService;

    public HL7Service(HL7ParserService hl7ParserService, InMemoryDBService inMemoryDBService) {
        this.hl7ParserService = hl7ParserService;
        this.inMemoryDBService = inMemoryDBService;
    }

    public PatientInfosDto savePatientInfos(String hl7Message) {
        String patientId = hl7ParserService.getFieldValue(hl7Message, "PID-3-1");
        String messageControlId = hl7ParserService.getFieldValue(hl7Message, "MSH-10");

        inMemoryDBService.saveHL7Message(patientId, messageControlId, hl7Message);

        return getPatientInfos(hl7Message);
    }

    public PatientInfoDto decodePatientInfo(String msg, String segmentCode) {
        return new PatientInfoDto(hl7ParserService.getFieldValue(msg, segmentCode));
    }

    private PatientInfosDto getPatientInfos(String hl7Message) {
        PatientNK1InfoDto patientNK1InfoDto = PatientNK1InfoDto.builder()
                .familyName(hl7ParserService.getFieldValueForGeneralPatientInfos(hl7Message, HL7NK1Values.FAMILY_NAME.getValue()))
                .givenName(hl7ParserService.getFieldValueForGeneralPatientInfos(hl7Message, HL7NK1Values.GIVEN_NAME.getValue()))
                .initialsThereof(hl7ParserService.getFieldValueForGeneralPatientInfos(hl7Message, HL7NK1Values.INITIALS_THEREOF.getValue()))
                .relationship(hl7ParserService.getFieldValueForGeneralPatientInfos(hl7Message, HL7NK1Values.RELATIONSHIP.getValue()))
                .streetAddress(hl7ParserService.getFieldValueForGeneralPatientInfos(hl7Message, HL7NK1Values.STREET_ADDRESS.getValue()))
                .build();

        return new PatientInfosDto(patientNK1InfoDto);
    }
}
