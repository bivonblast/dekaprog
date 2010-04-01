
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
        skillHandler.addSkillTrait("Världsvana: Angriparkunskap", SkillType.newSocialSkillType(), 3);
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