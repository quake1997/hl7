package com.engine.hl7.api;

import com.engine.hl7.api.annotation.HL7Controller;
import com.engine.hl7.dto.HL7MessageDto;
import com.engine.hl7.dto.patient.PatientInfoDto;
import com.engine.hl7.dto.patient.PatientInfosDto;
import com.engine.hl7.service.HL7Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@HL7Controller
public class HL7BaseController {
    private final HL7Service hl7Service;

    public HL7BaseController(HL7Service hl7Service) {
        this.hl7Service = hl7Service;
    }

    @PostMapping
    public ResponseEntity<PatientInfosDto> savePatientInfos(@RequestBody HL7MessageDto hl7MessageDto) {
        hl7MessageDto.setHl7Message(hl7MessageDto.getHl7Message().replaceAll("\n", "\r"));

        return ResponseEntity.ok(hl7Service.savePatientInfos(hl7MessageDto.getHl7Message()));
    }

    @PostMapping("/decodePatientInfo/{segmentCode}")
    public ResponseEntity<PatientInfoDto> decodePatientInfo(@PathVariable String segmentCode, @RequestBody HL7MessageDto hl7MessageDto) {
        hl7MessageDto.setHl7Message(hl7MessageDto.getHl7Message().replaceAll("\n", "\r"));

        return ResponseEntity.ok(hl7Service.decodePatientInfo(hl7MessageDto.getHl7Message(), segmentCode));
    }
}
