package com.engine.hl7.api;

import com.engine.hl7.HL7ApplicationTests;
import com.engine.hl7.db.InMemoryDBService;
import com.engine.hl7.dto.HL7MessageDto;
import com.engine.hl7.dto.patient.PatientInfoDto;
import com.engine.hl7.dto.patient.PatientInfosDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class HL7BaseControllerTest extends HL7ApplicationTests {
    private TestRestTemplate testRestTemplate;
    private InMemoryDBService inMemoryDBService;
    private String hl7V22Message;
    private String hl7V25Message;

    @Autowired
    public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Autowired
    public void setInMemoryDBService(InMemoryDBService inMemoryDBService) {
        this.inMemoryDBService = inMemoryDBService;
    }

    @BeforeAll
    private void prepareTestData() {
        hl7V22Message = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01|20169838|P|2.2\r" +
                "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r" +
                "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r" +
                "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r" +
                "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r" +
                "AL1||SEV|001^POLLEN\r" +
                "AL1||SEV|003^DUST\r" +
                "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r" +
                "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";

        hl7V25Message = "MSH|^~\\&|MegaReg|XYZHospC|SuperOE|XYZImgCtr|20060529090131-0500||ADT^A01^ADT_A01|01052901|P|2.5\n" +
                "EVN||200605290901||||200605290900\n" +
                "PID|||56782445^^^UAReg^PI||KLEINSAMPLE^BARRY^Q^JR||19620910|M||2028-9^^HL70005^RA99113^^XYZ|260 GOODWIN CREST DRIVE^^BIRMINGHAM^AL^35209^^M~NICKELL’S PICKLES^10000 W 100TH AVE^BIRMINGHAM^AL^35200^^O|||||||0105I30001^^^99DEF^AN\n" +
                "PV1||I|W^389^1^UABH^^^^3||||12345^MORGAN^REX^J^^^MD^0010^UAMC^L||67890^GRAINGER^LUCY^X^^^MD^0010^UAMC^L|MED|||||A0||13579^POTTER^SHERMAN^T^^^MD^0010^UAMC^L|||||||||||||||||||||||||||200605290900\n" +
                "OBX|1|NM|^Body Height||1.80|m^Meter^ISO+|||||F\n" +
                "OBX|2|NM|^Body Weight||79|kg^Kilogram^ISO+|||||F\n" +
                "AL1|1||^ASPIRIN\n" +
                "DG1|1||786.50^CHEST PAIN, UNSPECIFIED^I9|||A";

    }

    @Test
    @Order(1)
    public void tc_01_savePatientInfos() {
        HttpEntity<HL7MessageDto> httpEntity = new HttpEntity<>(new HL7MessageDto(hl7V22Message));
        ResponseEntity<PatientInfosDto> result = this.testRestTemplate.postForEntity("/hl7", httpEntity, PatientInfosDto.class);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("NOTREAL", Objects.requireNonNull(result.getBody()).getPatientNK1InfoDto().getFamilyName());
        Assertions.assertEquals("JAMES", Objects.requireNonNull(result.getBody()).getPatientNK1InfoDto().getGivenName());
        Assertions.assertEquals("R", Objects.requireNonNull(result.getBody()).getPatientNK1InfoDto().getInitialsThereof());
        Assertions.assertEquals("FA", Objects.requireNonNull(result.getBody()).getPatientNK1InfoDto().getRelationship());
        Assertions.assertEquals("STREET", Objects.requireNonNull(result.getBody()).getPatientNK1InfoDto().getStreetAddress());
    }

    @Test
    @Order(2)
    public void tc_02_decodePatientInfo() {
        HttpEntity<HL7MessageDto> httpEntity = new HttpEntity<>(new HL7MessageDto(hl7V25Message));
        ResponseEntity<PatientInfoDto> result = this.testRestTemplate.postForEntity("/hl7/decodePatientInfo/OBX-1", httpEntity, PatientInfoDto.class);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("1", Objects.requireNonNull(result.getBody()).getInfo());
    }

    @Test
    @Order(3)
    public void tc_03_decodePatientInfo() {
        HttpEntity<HL7MessageDto> httpEntity = new HttpEntity<>(new HL7MessageDto(hl7V25Message));
        ResponseEntity<PatientInfoDto> result = this.testRestTemplate.postForEntity("/hl7/decodePatientInfo/DG1-3-2", httpEntity, PatientInfoDto.class);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("CHEST PAIN, UNSPECIFIED", Objects.requireNonNull(result.getBody()).getInfo());
    }

    @AfterAll
    public void clear() {
        inMemoryDBService.clearDB();
    }
}
