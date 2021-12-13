package com.engine.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.GenericModelClassFactory;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HL7Application {

    public static void main(String[] args) throws HL7Exception {
        String v25message = "MSH|^~\\&|ULTRA|TML|OLIS|OLIS|200905011130||ORU^R01|20169838-v25|T|2.5\r"
                + "PID|||7005728^^^TML^MR||TEST^RACHEL^DIAMOND||19310313|F|||200 ANYWHERE ST^^TORONTO^ON^M6G 2T9||(416)888-8888||||||1014071185^KR\r"
                + "PV1|1||OLIS||||OLIST^BLAKE^DONALD^THOR^^^^^921379^^^^OLIST\r"
                + "ORC|RE||T09-100442-RET-0^^OLIS_Site_ID^ISO|||||||||OLIST^BLAKE^DONALD^THOR^^^^L^921379\r"
                + "OBR|0||T09-100442-RET-0^^OLIS_Site_ID^ISO|RET^RETICULOCYTE COUNT^HL79901 literal|||200905011106|||||||200905011106||OLIST^BLAKE^DONALD^THOR^^^^L^921379||7870279|7870279|T09-100442|MOHLTC|200905011130||B7|F||1^^^200905011106^^R\r"
                + "OBX|1|ST|||Test Value";
//
        String v23message = "MSH|^~\\&|AccMgr|1|||20050110045504||ADT^A08|599102|P|2.3|||\r" +
                "EVN|A01|20050110045502|||||\r" +
                "PID|1||10006579^^^1^MRN^1||DUCK^DONALD^D||19241010|M||1|111 DUCK ST^^FOWL^CA^999990000^^M|1|||1|2||40007716^^^AccMgr^VN^1|123121234|||||||||||NO\n" +
                "NK1|1|DUCK^HUEY|SO|3583 DUCK RD^^FOWL^CA^999990000|8885552222||Y||||||||||||||\r" +
                "PV1|1|I|PREOP^101^1^1^^^S|3|||37^DISNEY^WALT^^^^^^AccMgr^^^^CI|||01||||1|||37^DISNEY^WALT^^^^^^AccMgr^^^^CI|2|40007716^^^AccMgr^VN|4|||||||||||||||||||1||G|||20050110045253||||||\n" +
                "GT1|1|8291|DUCK^DONALD^D||111^DUCK ST^^FOWL^CA^999990000|8885551212||19241010|M||1|123121234||||#Cartoon Ducks Inc|111^DUCK ST^^FOWL^CA^999990000|8885551212||PT|\n" +
                "DG1|1|I9|71596^OSTEOARTHROS NOS-L/LEG ^I9|OSTEOARTHROS NOS-L/LEG ||A|\r" +
                "IN1|1|MEDICARE|3|MEDICARE|||||||Cartoon Ducks Inc|19891001|||4|DUCK^DONALD^D|1|19241010|111^DUCK ST^^FOWL^CA^999990000|||||||||||||||||123121234A||||||PT|M|111 DUCK ST^^FOWL^CA^999990000|||||8291\n" +
                "IN2|1||123121234|Cartoon Ducks Inc|||123121234A|||||||||||||||||||||||||||||||||||||||||||||||||||||||||8885551212\r" +
                "IN1|2|NON-PRIMARY|9|MEDICAL MUTUAL CALIF.|PO BOX 94776^^HOLLYWOOD^CA^441414776||8003621279|PUBSUMB|||Cartoon Ducks Inc||||7|DUCK^DONALD^D|1|19241010|111 DUCK ST^^FOWL^CA^999990000|||||||||||||||||056269770||||||PT|M|111^DUCK ST^^FOWL^CA^999990000|||||8291\n" +
                "IN2|2||123121234|Cartoon Ducks Inc||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||8885551212\r" +
                "IN1|3|SELF PAY|1|SELF PAY|||||||||||5||1";


//        PipeParser parser = new PipeParser(new GenericModelClassFactory());
//        parser.getParserConfiguration()
//                .setAllowUnknownVersions(true);
//
        //  HapiContext context = new DefaultHapiContext();

        //  context.setValidationContext(ValidationContextFactory.defaultValidation());

        //  Message hapiMsg = context.getPipeParser().parse(v23message);

        PipeParser parser = new PipeParser(new GenericModelClassFactory());
        parser.getParserConfiguration().setAllowUnknownVersions(true);

        Message hapiMsg = parser.parse(v23message);

        Terser terser = new Terser(hapiMsg);

        System.out.println(terser.get("/PID-1"));

        //  System.out.println(context.getPipeParser().parse(v25message).getVersion());

        // ADT_A08 adtMsg = (ADT_A08) hapiMsg;

        //   ORU_R01 oru_r01 = (ORU_R01) hapiMsg;

        // System.out.println(oru_r01.get("PID"));

        //  Segment pid = (Segment) hapiMsg.get("PID");

        //   PID pid = adtMsg.getPID();

        //   System.out.println(Arrays.toString(pid.getField(8)));

        // Terser terser = new Terser(hapiMsg);

        // String sendingApplication = terser.get("PID-3-1");
        // System.out.println(adtMsg.getPID().getSEX());


//        String messageText = "MSH|^~\\&|IRIS|SANTER|AMB_R|SANTER|200803051508||ADT^A01|263206|P|2.5\r"
//                + "EVN||200803051509||||200803031508\r"
//                + "PID|||5520255^^^PK^PK~ZZZZZZ83M64Z148R^^^CF^CF~ZZZZZZ83M64Z148R^^^SSN^SSN^^20070103^99991231~^^^^TEAM||ZZZ^ZZZ||19830824|F||||||||||||||||||||||N\r"
//                + "ZPI|Fido~Fred|13\r"
//                + "PV1||I|6402DH^^^^^^^^MED. 1 - ONCOLOGIA^^OSPEDALE MAGGIORE DI LODI&LODI|||^^^^^^^^^^OSPEDALE MAGGIORE DI LODI&LODI|13936^TEST^TEST||||||||||5068^TEST2^TEST2||2008003369||||||||||||||||||||||||||200803031508\r"
//                + "PR1|1||1111^Mastoplastica|Protesi|20090224|02|";
//
//        // HAPI will still parse this message fine
//        HapiContext context = new DefaultHapiContext();
//        Parser parser = context.getPipeParser();
//        System.out.println(context.getPipeParser().getVersion(messageText));
//        System.out.println(parser.parse(messageText) instanceof ADT_A01);
//
//        ADT_A01 message = (ADT_A01) parser.parse(messageText);
//
//        // If we want to access the data in the ZPI segment, it's pretty easy
//        Segment zpiGenericSegment = (Segment) message.get("ZPI");
//
//        String firstPetName  = zpiGenericSegment.getField(1, 0).encode();
//        String secondPetName = zpiGenericSegment.getField(1, 1).encode();
//        System.out.println(firstPetName); // Fido
//        System.out.println(secondPetName); // Fred
//
//        String shoeSize = zpiGenericSegment.getField(2, 0).encode();
//        System.out.println(shoeSize); // 13

        SpringApplication.run(HL7Application.class, args);

    }
}
