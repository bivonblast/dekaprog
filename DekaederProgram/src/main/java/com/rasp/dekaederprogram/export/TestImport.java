package com.rasp.dekaederprogram.export;


import com.rasp.dekaederprogram.character.DekaederCharacter;
import com.rasp.dekaederprogram.character.data.AdvantageHandler;
import com.rasp.dekaederprogram.character.data.ConceptHandler;
import com.rasp.dekaederprogram.character.data.HookHandler;
import com.rasp.dekaederprogram.character.data.HookTrait;
import com.rasp.dekaederprogram.character.data.PointHandler;
import com.rasp.dekaederprogram.character.data.SkillHandler;
import com.rasp.dekaederprogram.character.data.SpecialityHandler;
import com.rasp.dekaederprogram.export.ExportHandler;
import com.rasp.dekaederprogram.export.FileHandler;
import com.rasp.dekaederprogram.export.PDFHandler;
import com.rasp.dekaederprogram.export.WikiHandler;

/**
 *
 * @author Martin Andersson
 */
public class TestImport {
    public static void main(String[] args) {
//        MediaWikiBot b;
//        try {
//            b = new MediaWikiBot("http://wiki.raspare.se/");
//            b.login("DekaProg", "tester");
//            Article sa = b.readContent("No");
//            sa.addTextnl("TEST DEKAPROG Editorers15");
//            //sa.save();
//            b.writeContent(sa);
//        } catch (ActionException ex) {
//            Logger.getLogger(TestImport.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("FELFELFELFEL!");
//        } catch (ProcessException ex) {
//            Logger.getLogger(TestImport.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("FELFELFELFEL!");
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(TestImport.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("FELFELFELFEL!");
//        }
        
        ExportHandler pdfTest = new PDFHandler("file:///C:/DekaProg/");
        ConceptHandler conceptHandler = new ConceptHandler();
        conceptHandler.addConceptTrait("Namn", "Levan 'Face' Darish");
        conceptHandler.addConceptTrait("Kön", "Man");
        conceptHandler.addConceptTrait("Ålder", "21 år");
        conceptHandler.addConceptTrait("Koncept", "Karisma");
        conceptHandler.addConceptTrait("Kampanj", "Upprorets Piloter");
        conceptHandler.addConceptTrait("Utseende", "Cool kille");
        conceptHandler.addConceptTrait("Citat", "One down! Two to go...");
        PointHandler pointHandler = new PointHandler(50);
        pointHandler.addPointTrait("Karma", 10);
        pointHandler.addPointTrait("Stilpoäng", 2);
        pointHandler.addPointTrait("VeteranÄventyr", 29);
        pointHandler.addExperiencePoints(169);
        SkillHandler skillHandler = new SkillHandler();
        WikiHandler wiki = new WikiHandler("http://wiki.raspare.se");
        FileHandler file = new FileHandler("file:///C:/DekaProg/");
        System.out.println(file.readCharacter("mall", true));
        skillHandler.addAll(wiki.getSkillsFromString(file.readCharacter("mall", true)));
//        skillHandler.addSkillTrait("Akrobatik", SkillType.newPhysicalSkillType(), 3);
//        skillHandler.addSkillTrait("Fingerfärdighet", SkillType.newPhysicalSkillType(), 1);
//        skillHandler.addSkillTrait("Fordon: Swoop", SkillType.newPhysicalSkillType(), 2);
//        skillHandler.addSkillTrait("Fordon: Svävare", SkillType.newPhysicalSkillType(), 1);
//        skillHandler.addSkillTrait("Fällor", SkillType.newPhysicalSkillType(), 1);
//        skillHandler.addSkillTrait("Närkamp: Slagsmål", SkillType.newPhysicalSkillType(), 2);
//        skillHandler.addSkillTrait("Råstyrka", SkillType.newPhysicalSkillType(), 1);
//        skillHandler.addSkillTrait("Skepp: Jakt", SkillType.newPhysicalSkillType(), 8);
//        skillHandler.addSkillTrait("Skepp: Kryssare", SkillType.newPhysicalSkillType(), 2);
//        skillHandler.addSkillTrait("Skepp: Transport", SkillType.newPhysicalSkillType(), 1);
//        skillHandler.addSkillTrait("Skytte: Kanoner", SkillType.newPhysicalSkillType(), 2);
//        skillHandler.addSkillTrait("Skytte: Handvapen", SkillType.newPhysicalSkillType(), 3);
//        skillHandler.addSkillTrait("Smygning", SkillType.newPhysicalSkillType(), 3);
//        skillHandler.addSkillTrait("Uthållighet", SkillType.newPhysicalSkillType(), 6);
//        skillHandler.addSkillTrait("Massage", SkillType.newPhysicalSkillType(), 5);
//        skillHandler.addSkillTrait("Attraktion", SkillType.newSocialSkillType(), 6);
//        skillHandler.addSkillTrait("Auktoritet", SkillType.newSocialSkillType(), 9);
//        skillHandler.addSkillTrait("Empati", SkillType.newSocialSkillType(), 5);
//        skillHandler.addSkillTrait("Förtroende", SkillType.newSocialSkillType(), 9);
//        skillHandler.addSkillTrait("Integritet", SkillType.newSocialSkillType(), 5);
//        skillHandler.addSkillTrait("Mod", SkillType.newSocialSkillType(), 5, 5);
//        skillHandler.addSkillTrait("Skådespeleri", SkillType.newSocialSkillType(), 2);
//        skillHandler.addSkillTrait("Språk: Basic", SkillType.newSocialSkillType(), 10);
//        skillHandler.addSkillTrait("Språk: Devaron", SkillType.newSocialSkillType(), 1);
//        skillHandler.addSkillTrait("Språk: Sullust", SkillType.newSocialSkillType(), 3);
//        skillHandler.addSkillTrait("Världsvana: Kuat", SkillType.newSocialSkillType(), 5);
//        skillHandler.addSkillTrait("Världsvana: Imperiet", SkillType.newSocialSkillType(), 3);
//        skillHandler.addSkillTrait("Världsvana: KSF Obeveklig", SkillType.newSocialSkillType(), 3);
//        skillHandler.addSkillTrait("Världsvana: Rebellalliansen", SkillType.newSocialSkillType(), 5);
//        skillHandler.addSkillTrait("Världsvana: Pirater", SkillType.newSocialSkillType(), 2);
//        skillHandler.addSkillTrait("Världsvana: Koriska flottan", SkillType.newSocialSkillType(), 5);
//        skillHandler.addSkillTrait("Världsvana: Kori", SkillType.newSocialSkillType(), 2);
//        skillHandler.addSkillTrait("Världsvana: Sepan", SkillType.newSocialSkillType(), 1);
//        skillHandler.addSkillTrait("Världsvana: Sepanska Flottan", SkillType.newSocialSkillType(), 2);
//        skillHandler.addSkillTrait("Akademisk Kunskap: Astrogation", SkillType.newMentalSkillType(), 4);
//        skillHandler.addSkillTrait("Akademisk Kunskap: Galaktografi", SkillType.newMentalSkillType(), 2);
//        skillHandler.addSkillTrait("Akademisk Kunskap: Medicin", SkillType.newMentalSkillType(), 1);
//        skillHandler.addSkillTrait("Första Hjälpen", SkillType.newMentalSkillType(), 5);
//        skillHandler.addSkillTrait("Sensor och Samband", SkillType.newMentalSkillType(), 8);
//        skillHandler.addSkillTrait("Stridsteknik", SkillType.newMentalSkillType(), 3);
//        skillHandler.addSkillTrait("Teknik: Elektronik", SkillType.newMentalSkillType(), 4);
//        skillHandler.addSkillTrait("Teknik: Jaktskepp", SkillType.newMentalSkillType(), 4);
//        skillHandler.addSkillTrait("Teknik: Kryssare", SkillType.newMentalSkillType(), 7, 3);
//        skillHandler.addSkillTrait("Teknik: Vapen", SkillType.newMentalSkillType(), 2);
//        skillHandler.addSkillTrait("Angriparkunskap", SkillType.newMentalSkillType(), 4);
//        skillHandler.addSkillTrait("Taktik: Rymdstrid", SkillType.newMentalSkillType(), 5);
//        skillHandler.addSkillTrait("Överlevnad", SkillType.newMentalSkillType(), 3);
        SpecialityHandler specialityHandler = new SpecialityHandler();
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
        HookHandler hookHandler = new HookHandler();
        hookHandler.add(new HookTrait("DrivKraft", "Bli divisionschef för en division jaktskepp"));
        hookHandler.add(new HookTrait("Svaghet", "Vad av typen: Det klarar du aldrig, Flickor, Kuat"));
        hookHandler.add(new HookTrait("Uppträdande", "Social, utåtriktad, lätt att prata för sig"));
        pointHandler.spendExperiencePoints(219, 0);
        DekaederCharacter test = new DekaederCharacter(conceptHandler, pointHandler, skillHandler, specialityHandler, hookHandler, new AdvantageHandler());
        FileHandler sparaChar = new FileHandler("file:///C:/DekaProg/");
        sparaChar.writeCharacter(test, "Myggdräpe", ExportHandler.SHOWVALUE);
        DekaederCharacter kalle = sparaChar.readCharacter("Levan 'Face' Darish");
        pdfTest.writeCharacter(kalle, "Myggdräpe", ExportHandler.SHOWVALUE);
    }
}


/*
 *
 * unused code

     //Article sa = b.readContent("No");
      //sa.setEditor("Myggdräpe");
      System.out.println(sa.getText());
      //String test = b.getPage("index.php?title=No");
      //sa.addTextnl("TEST DEKAPROG Editor");
      //sa.save();

      //b.performAction();
      //b.writeContent(sa);
        /*Article a = new Article(b, "Shedinan");
        String text = "TEST DEKAPROG Editor";
        a.addText(text);
        a.save();
        assertTrue("should contains the article", true);//c.containsKey("Shedinan"));
        assertEquals(text, a.getText());
        //BasicTest artikel = new BasicTest();
        //artikel.articleTest();
 */