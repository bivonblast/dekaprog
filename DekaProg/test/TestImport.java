
import character.DekaederCharacter;
import character.data.ConceptHandler;
import character.data.PointHandler;
import export.ExportHandler;
import export.WikiHandler;
import export.ExportImportHandler;
import export.FileHandler;
import export.PDFHandler;

/**
 *
 * @author Martin Andersson
 */
public class TestImport {
    public static void main(String[] args) {
        ExportHandler pdfTest = new PDFHandler("file:///C:/DekaProg/",
                45, 532, 11.3f, 87, 114f);
        ConceptHandler conceptHandler = new ConceptHandler("Levan Darish", 
                "Karisma",
                "Upprorets Piloter",
                ConceptHandler.GENDER_MALE,
                21,
                "Cool kille",
                "One down! Two to go...");
        PointHandler pointHandler = new PointHandler(50, 10, 0, 165, 29, 2);
        DekaederCharacter test = new DekaederCharacter(conceptHandler, pointHandler);
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