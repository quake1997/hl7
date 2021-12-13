package com.engine.hl7.api.segment;

import com.engine.hl7.api.annotation.HL7Controller;
import com.engine.hl7.db.segment.HL7NK1Values;
import com.engine.hl7.dto.patient.PatientInfoDto;
import com.engine.hl7.service.segment.HL7NK1Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@HL7Controller
public class HL7NK1Controller {
    private final HL7NK1Service hl7NK1Service;

    public HL7NK1Controller(HL7NK1Service hl7NK1Service) {
        this.hl7NK1Service = hl7NK1Service;
    }

    @GetMapping("/nk1/familyName/{patientId}/{messageControlId}")
    public ResponseEntity<PatientInfoDto> getNK1PatientFamilyName(@PathVariable String patientId, @PathVariable String messageControlId) {
        return ResponseEntity.ok(hl7NK1Service.getNK1PatientInfo(patientId, messageControlId, HL7NK1Values.FAMILY_NAME.getValue()));
    }

    @GetMapping("/nk1/givenName/{patientId}/{messageControlId}")
    public ResponseEntity<PatientInfoDto> getNK1PatientGivenName(@PathVariable String patientId, @PathVariable String messageControlId) {
        return ResponseEntity.ok(hl7NK1Service.getNK1PatientInfo(patientId, messageControlId, HL7NK1Values.GIVEN_NAME.getValue()));
    }

    @GetMapping("/nk1/initialsThereof/{patientId}/{messageControlId}")
    public ResponseEntity<PatientInfoDto> getNK1PatientInitialsThereof(@PathVariable String patientId, @PathVariable String messageControlId) {
        return ResponseEntity.ok(hl7NK1Service.getNK1PatientInfo(patientId, messageControlId, HL7NK1Values.INITIALS_THEREOF.getValue()));
    }

    @GetMapping("/nk1/relationship/{patientId}/{messageControlId}")
    public ResponseEntity<PatientInfoDto> getNK1PatientRelationship(@PathVariable String patientId, @PathVariable String messageControlId) {
        return ResponseEntity.ok(hl7NK1Service.getNK1PatientInfo(patientId, messageControlId, HL7NK1Values.RELATIONSHIP.getValue()));
    }

    @GetMapping("/nk1/streetAddress/{patientId}/{messageControlId}")
    public ResponseEntity<PatientInfoDto> getNK1PatientStreetAddress(@PathVariable String patientId, @PathVariable String messageControlId) {
        return ResponseEntity.ok(hl7NK1Service.getNK1PatientInfo(patientId, messageControlId, HL7NK1Values.STREET_ADDRESS.getValue()));
    }
}
