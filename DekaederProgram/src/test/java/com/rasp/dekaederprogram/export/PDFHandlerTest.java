/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import com.lowagie.text.pdf.PdfContentByte;
import com.rasp.dekaederprogram.character.DekaederCharacter;
import com.rasp.dekaederprogram.character.data.AdvantageHandler;
import com.rasp.dekaederprogram.character.data.ConceptHandler;
import com.rasp.dekaederprogram.character.data.HookHandler;
import com.rasp.dekaederprogram.character.data.HookTrait;
import com.rasp.dekaederprogram.character.data.PointHandler;
import com.rasp.dekaederprogram.character.data.SkillHandler;
import com.rasp.dekaederprogram.character.data.SkillType;
import com.rasp.dekaederprogram.character.data.SpecialityHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author MARTIN
 */
public class PDFHandlerTest extends TestCase {
    private ConceptHandler conceptHandler;
    private PointHandler pointHandler;
    private SkillHandler skillHandler;
    private SpecialityHandler specialityHandler;
    private HookHandler hookHandler;
    private DekaederCharacter dekaederCharacter;
    //private WikiHandler wiki;
    
    public PDFHandlerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();


        conceptHandler = new ConceptHandler();
        conceptHandler.addConceptTrait("Namn", "Levan 'Face' Darish");
        conceptHandler.addConceptTrait("Kön", "Man");
        conceptHandler.addConceptTrait("Ålder", "21 år");
        conceptHandler.addConceptTrait("Koncept", "Karisma");
        conceptHandler.addConceptTrait("Kampanj", "Upprorets Piloter");
        conceptHandler.addConceptTrait("Utseende", "Cool kille");
        conceptHandler.addConceptTrait("Citat", "One down! Two to go...");
        pointHandler = new PointHandler(50);
        pointHandler.addPointTrait("Karma", 10);
        pointHandler.addPointTrait("Stilpoäng", 2);
        pointHandler.addPointTrait("VeteranÄventyr", 29);
        pointHandler.addExperiencePoints(169);
        skillHandler = new SkillHandler();
        //wiki = new WikiHandler("http://wiki.raspare.se");
        FileHandler file = new FileHandler("file:///C:/DekaProg/");
        //System.out.println(file.readCharacter("mall", true));
        //skillHandler.addAll(wiki.getSkillsFromString(file.readCharacter("mall", true)));
        skillHandler.addSkillTrait("Akrobatik", SkillType.newPhysicalSkillType(), 3);
        skillHandler.addSkillTrait("Fingerfärdighet", SkillType.newPhysicalSkillType(), 1);
        skillHandler.addSkillTrait("Fordon: Swoop", SkillType.newPhysicalSkillType(), 2);
        skillHandler.addSkillTrait("Fordon: Svävare", SkillType.newPhysicalSkillType(), 1);
        skillHandler.addSkillTrait("Fällor", SkillType.newPhysicalSkillType(), 1);
        skillHandler.addSkillTrait("Närkamp: Slagsmål", SkillType.newPhysicalSkillType(), 2);
        skillHandler.addSkillTrait("Råstyrka", SkillType.newPhysicalSkillType(), 1);
        skillHandler.addSkillTrait("Skepp: Jakt", SkillType.newPhysicalSkillType(), 8);
        skillHandler.addSkillTrait("Skepp: Kryssare", SkillType.newPhysicalSkillType(), 2);
        skillHandler.addSkillTrait("Skepp: Transport", SkillType.newPhysicalSkillType(), 1);
        skillHandler.addSkillTrait("Skytte: Kanoner", SkillType.newPhysicalSkillType(), 2);
        skillHandler.addSkillTrait("Skytte: Handvapen", SkillType.newPhysicalSkillType(), 3);
        skillHandler.addSkillTrait("Smygning", SkillType.newPhysicalSkillType(), 3);
        skillHandler.addSkillTrait("Uthållighet", SkillType.newPhysicalSkillType(), 6);
        skillHandler.addSkillTrait("Massage", SkillType.newPhysicalSkillType(), 5);
        skillHandler.addSkillTrait("Attraktion", SkillType.newSocialSkillType(), 6);
        skillHandler.addSkillTrait("Auktoritet", SkillType.newSocialSkillType(), 9);
        skillHandler.addSkillTrait("Empati", SkillType.newSocialSkillType(), 5);
        skillHandler.addSkillTrait("Förtroende", SkillType.newSocialSkillType(), 9);
        skillHandler.addSkillTrait("Integritet", SkillType.newSocialSkillType(), 5);
        skillHandler.addSkillTrait("Mod", SkillType.newSocialSkillType(), 5, 5);
        skillHandler.addSkillTrait("Skådespeleri", SkillType.newSocialSkillType(), 2);
        skillHandler.addSkillTrait("Språk: Basic", SkillType.newSocialSkillType(), 10);
        skillHandler.addSkillTrait("Språk: Devaron", SkillType.newSocialSkillType(), 1);
        skillHandler.addSkillTrait("Språk: Sullust", SkillType.newSocialSkillType(), 3);
        skillHandler.addSkillTrait("Världsvana: Kuat", SkillType.newSocialSkillType(), 5);
        skillHandler.addSkillTrait("Världsvana: Imperiet", SkillType.newSocialSkillType(), 3);
        skillHandler.addSkillTrait("Världsvana: KSF Obeveklig", SkillType.newSocialSkillType(), 3);
        skillHandler.addSkillTrait("Världsvana: Rebellalliansen", SkillType.newSocialSkillType(), 5);
        skillHandler.addSkillTrait("Världsvana: Pirater", SkillType.newSocialSkillType(), 2);
        skillHandler.addSkillTrait("Världsvana: Koriska flottan", SkillType.newSocialSkillType(), 5);
        skillHandler.addSkillTrait("Världsvana: Kori", SkillType.newSocialSkillType(), 2);
        skillHandler.addSkillTrait("Världsvana: Sepan", SkillType.newSocialSkillType(), 1);
        skillHandler.addSkillTrait("Världsvana: Sepanska Flottan", SkillType.newSocialSkillType(), 2);
        skillHandler.addSkillTrait("Akademisk Kunskap: Astrogation", SkillType.newMentalSkillType(), 4);
        skillHandler.addSkillTrait("Akademisk Kunskap: Galaktografi", SkillType.newMentalSkillType(), 2);
        skillHandler.addSkillTrait("Akademisk Kunskap: Medicin", SkillType.newMentalSkillType(), 1);
        skillHandler.addSkillTrait("Första Hjälpen", SkillType.newMentalSkillType(), 5);
        skillHandler.addSkillTrait("Sensor och Samband", SkillType.newMentalSkillType(), 8);
        skillHandler.addSkillTrait("Stridsteknik", SkillType.newMentalSkillType(), 3);
        skillHandler.addSkillTrait("Teknik: Elektronik", SkillType.newMentalSkillType(), 4);
        skillHandler.addSkillTrait("Teknik: Jaktskepp", SkillType.newMentalSkillType(), 4);
        skillHandler.addSkillTrait("Teknik: Kryssare", SkillType.newMentalSkillType(), 7, 3);
        skillHandler.addSkillTrait("Teknik: Vapen", SkillType.newMentalSkillType(), 2);
        skillHandler.addSkillTrait("Angriparkunskap", SkillType.newMentalSkillType(), 4);
        skillHandler.addSkillTrait("Taktik: Rymdstrid", SkillType.newMentalSkillType(), 5);
        skillHandler.addSkillTrait("Överlevnad", SkillType.newMentalSkillType(), 3);
        specialityHandler = new SpecialityHandler();
        specialityHandler.addSpecialityTrait("Imperiet", skillHandler.getSkill("Auktoritet"), 2);
        specialityHandler.addSpecialityTrait("Kirurgi", skillHandler.getSkill("Fingerfärdighet"), 2);
        specialityHandler.addSpecialityTrait("Hygien", skillHandler.getSkill("Fingerfärdighet"), 2);
        specialityHandler.addSpecialityTrait("TIE", skillHandler.getSkill("Skepp: Jakt"), 2);
        specialityHandler.addSpecialityTrait("Z-95", skillHandler.getSkill("Skepp: Jakt"), 2);
        specialityHandler.addSpecialityTrait("Y-vinge", skillHandler.getSkill("Skepp: Jakt"), 2);
        specialityHandler.addSpecialityTrait("Cloakshape", skillHandler.getSkill("Skepp: Jakt"), 2);
        specialityHandler.addSpecialityTrait("Kuatisk Korvett", skillHandler.getSkill("Skepp: Kryssare"), 2);
        specialityHandler.addSpecialityTrait("Skybird", skillHandler.getSkill("Fordon: Swoop"), 2);
        specialityHandler.addSpecialityTrait("Imitation", skillHandler.getSkill("Skådespeleri"), 2);
        specialityHandler.addSpecialityTrait("G-tålighet", skillHandler.getSkill("Uthållighet"), 2);
        specialityHandler.addSpecialityTrait("DL-18", skillHandler.getSkill("Skytte: Handvapen"), 2);
        specialityHandler.addSpecialityTrait("Dogfight", skillHandler.getSkill("Taktik: Rymdstrid"), 2);
        specialityHandler.addSpecialityTrait("Z-95", skillHandler.getSkill("Teknik: Jaktskepp"), 2);
        hookHandler = new HookHandler();
        hookHandler.add(new HookTrait("DrivKraft", "Bli divisionschef för en division jaktskepp"));
        hookHandler.add(new HookTrait("Svaghet", "Vad av typen: Det klarar du aldrig, Flickor, Kuat"));
        hookHandler.add(new HookTrait("Uppträdande", "Social, utåtriktad, lätt att prata för sig"));
        pointHandler.spendExperiencePoints(219, 0);
        dekaederCharacter = new DekaederCharacter(conceptHandler, pointHandler, skillHandler, specialityHandler, hookHandler, new AdvantageHandler());

       
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of writeCharacter method, of class PDFHandler.
     */
    public void testWriteCharacter() {

        System.out.println("writeCharacter");
        String filePosition = "file:///C:/DekaProg/";
        ExportHandler pdfTest = new PDFHandler(filePosition);
        boolean expResult = true;
        boolean result = pdfTest.writeCharacter(dekaederCharacter, "Testnamn", true);
        assertEquals(expResult, result);
        result = pdfTest.writeCharacter(dekaederCharacter, "Testnamn", false);
        assertEquals(expResult, result);

    }

    /**
     * Test of writeSettings method, of class PDFHandler.
     */
    public void testWriteSettings() {
        System.out.println("writeSettings");
        String campaignName = "";
        String campaign = "";
        PDFHandler instance = null;
        boolean expResult = false;
        boolean result = false; //= instance.writeSettings(campaignName, campaign);
        assertEquals(expResult, result);
        // TODO review the generated dekaederCharacter code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of createFile method, of class PDFHandler.
     */
//    public void testCreateFile() {
//        System.out.println("createFile");
//        boolean showValue = false;
//        PDFHandler instance = null;
//        boolean expResult = false;
//        boolean result = instance.createFile(showValue);
//        assertEquals(expResult, result);
//        // TODO review the generated dekaederCharacter code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of createPdf method, of class PDFHandler.
     */
//    public void testCreatePdf() throws Exception {
//        System.out.println("createPdf");
//        String filename = "";
//        boolean showValue = false;
//        PDFHandler instance = null;
//        instance.createPdf(filename, showValue);
//        // TODO review the generated dekaederCharacter code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of drawRectangle method, of class PDFHandler.
     */
//    private void testDrawRectangle() {
//        System.out.println("drawRectangle");
//        PdfContentByte content = null;
//        float width = 0.0F;
//        float height = 0.0F;
//        PDFHandler.drawRectangle(content, width, height);
//        // TODO review the generated dekaederCharacter code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
