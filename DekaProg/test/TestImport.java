
import character.DekaederCharacter;
import character.data.ConceptHandler;
import character.data.PointHandler;
import character.data.SkillHandler;
import character.data.SkillType;
import character.data.SpecialityHandler;
import export.ExportHandler;
import export.PDFHandler;

/**
 *
 * @author Martin Andersson
 */
public class TestImport {
    public static void main(String[] args) {
        ExportHandler pdfTest = new PDFHandler("file:///C:/DekaProg/");
        ConceptHandler conceptHandler = new ConceptHandler();
        conceptHandler.addConceptTrait("Namn", "Levan Darish");
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
        SpecialityHandler specialityHandler = new SpecialityHandler();
        specialityHandler.addSpecialityTrait("Imperiet", skillHandler.getSkill("Auktoritet"));
        specialityHandler.addSpecialityTrait("Kirurgi", skillHandler.getSkill("Fingerfärdighet"));
        specialityHandler.addSpecialityTrait("Hygien", skillHandler.getSkill("Fingerfärdighet"));
        specialityHandler.addSpecialityTrait("TIE", skillHandler.getSkill("Skepp: Jakt"));
        specialityHandler.addSpecialityTrait("Z-95", skillHandler.getSkill("Skepp: Jakt"));
        specialityHandler.addSpecialityTrait("Y-vinge", skillHandler.getSkill("Skepp: Jakt"));
        specialityHandler.addSpecialityTrait("Cloakshape", skillHandler.getSkill("Skepp: Jakt"));
        specialityHandler.addSpecialityTrait("Kuatisk Korvett", skillHandler.getSkill("Skepp: Kryssare"));
        specialityHandler.addSpecialityTrait("Skybird", skillHandler.getSkill("Fordon: Swoop"));
        specialityHandler.addSpecialityTrait("Imitation", skillHandler.getSkill("Skådespeleri"));
        specialityHandler.addSpecialityTrait("G-tålighet", skillHandler.getSkill("Uthållighet"));
        specialityHandler.addSpecialityTrait("DL-18", skillHandler.getSkill("Skytte: Handvapen"));
        specialityHandler.addSpecialityTrait("Dogfight", skillHandler.getSkill("Taktik: Rymdstrid"));
        specialityHandler.addSpecialityTrait("Z-95", skillHandler.getSkill("Teknik: Jaktskepp"));
        System.out.println(SkillType.newPhysicalSkillType().toString());
        pointHandler.spendExperiencePoints(219, 0);
        DekaederCharacter test = new DekaederCharacter(conceptHandler, pointHandler, skillHandler, specialityHandler);
        pdfTest.writeCharacter(test, "Myggdräpe");
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