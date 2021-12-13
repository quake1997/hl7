package com.engine.hl7.service.parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.GenericModelClassFactory;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HL7ParserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HL7ParserService.class);

    private Message hapiMsg;
    private Terser terser;
    private PipeParser parser;

    public HL7ParserService() {
        setupPipeParser();
    }

    public String getFieldValue(String msg, String fieldIdentifier) {
        try {
            hapiMsg = parser.parse(msg);

            terser = new Terser(hapiMsg);

            return terser.get("/." + fieldIdentifier);
        } catch (HL7Exception e) {
            LOGGER.info("HL7Exception: {}", e.getMessage());

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception during parsing field " + fieldIdentifier);
        }
    }

    public String getFieldValueForGeneralPatientInfos(String msg, String fieldIdentifier) {
        String value = "";

        try {
            hapiMsg = parser.parse(msg);

            terser = new Terser(hapiMsg);

            value = terser.get("/." + fieldIdentifier);
        } catch (HL7Exception e) {
            LOGGER.info("HL7Exception: {}", e.getMessage());
        }

        return value;
    }

    private void setupPipeParser() {
        PipeParser parser = new PipeParser(new GenericModelClassFactory());
        parser.getParserConfiguration().setAllowUnknownVersions(true);

        this.parser = parser;
    }
}
