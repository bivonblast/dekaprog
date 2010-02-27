
import ExportImport.ExportHandler;
import ExportImport.WikiHandler;
import ExportImport.ExportImportHandler;
import ExportImport.FileHandler;
import ExportImport.PDFHandler;

/**
 *
 * @author Martin Andersson
 */
public class TestImport {
    public static void main(String[] args) {
        ExportHandler psTest = new PDFHandler("file:///C:/DekaProg/");
        psTest.writeCharacter("test", "test");
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