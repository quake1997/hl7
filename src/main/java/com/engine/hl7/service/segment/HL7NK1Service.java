package com.engine.hl7.service.segment;

import com.engine.hl7.db.InMemoryDBService;
import com.engine.hl7.dto.patient.PatientInfoDto;
import com.engine.hl7.service.parser.HL7ParserService;
import org.springframework.stereotype.Service;

@Service
public class HL7NK1Service {
    private final HL7ParserService hl7ParserService;
    private final InMemoryDBService inMemoryDBService;

    public HL7NK1Service(HL7ParserService hl7ParserService, InMemoryDBService inMemoryDBService) {
        this.hl7ParserService = hl7ParserService;
        this.inMemoryDBService = inMemoryDBService;
    }

    public PatientInfoDto getNK1PatientInfo(String patientId, String messageControlId, String patientInfo) {
        String hl7Message = inMemoryDBService.getHL7Message(patientId, messageControlId);

        return new PatientInfoDto(hl7ParserService.getFieldValue(hl7Message, patientInfo));
    }
}
