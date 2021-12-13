package com.engine.hl7.db;

import com.engine.hl7.HL7ApplicationTests;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

public class InMemoryDBServiceTests extends HL7ApplicationTests {
    private InMemoryDBService inMemoryDBService;
    private String hl7Message01;
    private String hl7Message02;

    @Autowired
    public void setInMemoryDBService(InMemoryDBService inMemoryDBService) {
        this.inMemoryDBService = inMemoryDBService;
    }

    @BeforeAll
    private void prepareTestData() {
        hl7Message01 = "MSH|^~\\&|ULTRA|TML|OLIS|OLIS|200905011130||ORU^R01|20169838-v25|T|2.5\r"
                + "PID|||7005728^^^TML^MR||TEST^RACHEL^DIAMOND||19310313|F|||200 ANYWHERE ST^^TORONTO^ON^M6G 2T9||(416)888-8888||||||1014071185^KR\r"
                + "PV1|1||OLIS||||OLIST^BLAKE^DONALD^THOR^^^^^921379^^^^OLIST\r"
                + "ORC|RE||T09-100442-RET-0^^OLIS_Site_ID^ISO|||||||||OLIST^BLAKE^DONALD^THOR^^^^L^921379\r"
                + "OBR|0||T09-100442-RET-0^^OLIS_Site_ID^ISO|RET^RETICULOCYTE COUNT^HL79901 literal|||200905011106|||||||200905011106||OLIST^BLAKE^DONALD^THOR^^^^L^921379||7870279|7870279|T09-100442|MOHLTC|200905011130||B7|F||1^^^200905011106^^R\r"
                + "OBX|1|ST|||Test Value";

        hl7Message02 = "MSH|^~\\&|ULTRA|TML|OLIS|OLIS|200905011130||ORU^R01|20169837-v25|T|2.5\r"
                + "PID|||7005728^^^TML^MR||TEST^RACHEL^DIAMOND||19310313|F|||200 ANYWHERE ST^^TORONTO^ON^M6G 2T9||(416)888-8888||||||1014071185^KR\r"
                + "PV1|1||OLIS||||OLIST^BLAKE^DONALD^THOR^^^^^921379^^^^OLIST\r"
                + "ORC|RE||T09-100442-RET-0^^OLIS_Site_ID^ISO|||||||||OLIST^BLAKE^DONALD^THOR^^^^L^921379\r"
                + "OBR|0||T09-100442-RET-0^^OLIS_Site_ID^ISO|RET^RETICULOCYTE COUNT^HL79901 literal|||200905011106|||||||200905011106||OLIST^BLAKE^DONALD^THOR^^^^L^921379||7870279|7870279|T09-100442|MOHLTC|200905011130||B7|F||1^^^200905011106^^R\r"
                + "OBX|1|ST|||Test Value";
    }

    @Test
    @Order(1)
    public void tc_01_saveHL7MessageIfNotExist() {
        Assertions.assertThrows(ResponseStatusException.class, () -> inMemoryDBService.getHL7Message("7005728", "20169838-v25"));
        Assertions.assertThrows(ResponseStatusException.class, () -> inMemoryDBService.getHL7Message("7005728", "20169837-v25"));

        inMemoryDBService.saveHL7Message("7005728", "20169838-v25", hl7Message01);
        inMemoryDBService.saveHL7Message("7005728", "20169837-v25", hl7Message02);

        Assertions.assertEquals(hl7Message01, inMemoryDBService.getHL7Message("7005728", "20169838-v25"));
        Assertions.assertEquals(hl7Message02, inMemoryDBService.getHL7Message("7005728", "20169837-v25"));
    }

    @Test
    @Order(2)
    public void tc_02_preventSavingSameHL7Messages() {
        Assertions.assertThrows(ResponseStatusException.class, () -> inMemoryDBService.saveHL7Message("7005728", "20169838-v25", hl7Message01));
    }

    @AfterAll
    public void clear() {
        inMemoryDBService.clearDB();
    }
}
