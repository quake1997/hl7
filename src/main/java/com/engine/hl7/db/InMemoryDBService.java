package com.engine.hl7.db;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class InMemoryDBService {
    private static Map<String, List<String>> HL7_MSG_DB;

    static {
        HL7_MSG_DB = new HashMap<>();
    }

    public void saveHL7Message(String patientId, String messageControlId, String hl7Message) {
        Optional<List<String>> hl7Messages = Optional.ofNullable(HL7_MSG_DB.get(patientId));

        if (hl7Messages.isPresent()) {
            addHL7MsgIfNotPresent(messageControlId, hl7Message, hl7Messages.get());
        } else {
            HL7_MSG_DB.put(patientId, new ArrayList<>(List.of(hl7Message)));
        }
    }

    public String getHL7Message(String patientId, String messageControlId) {
        List<String> hl7Messages = Optional.ofNullable(HL7_MSG_DB.get(patientId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "patient_id: " + patientId + " not_found"));

        return hl7Messages.stream().filter(res -> res.contains(messageControlId)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "message_control_id: " + messageControlId + " not_found"));
    }

    public void clearDB() {
        HL7_MSG_DB = new HashMap<>();
    }

    private void addHL7MsgIfNotPresent(String msgControlId, String hl7Message, List<String> hl7Messages) {
        boolean isPresent = hl7Messages.stream().anyMatch(res -> res.contains(msgControlId));

        if (isPresent) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "hl7_message_already_exist");
        }

        hl7Messages.add(hl7Message);
    }
}
