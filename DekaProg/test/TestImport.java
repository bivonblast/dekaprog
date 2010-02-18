
import ExportImport.WikiHandler;
import ExportImport.ExportImportHandler;
import ExportImport.FileHandler;

/**
 *
 * @author Martin Andersson
 */
public class TestImport {
    public static void main(String[] args) {
        ExportImportHandler koppling = new WikiHandler("http://wiki.raspare.se/");
        String roll = koppling.readCharacter("Levan Darish");
        ExportImportHandler koppling2 = new FileHandler("file:///C:/Users/Martin/AppData/Roaming/DekaProg/");
        koppling2.writeCharacter("Levan Darish", roll);
        String roll2 = koppling2.readCharacter("Levan Darish");
        //System.out.println(roll2.trim());
        //System.out.println(roll2);

        if(!roll2.isEmpty() && roll.equals(roll2) ){
            System.out.println("Både fil och wiki överensstämmer");
        }else if(roll2.isEmpty()){
            System.out.println("roll2 är tom");
            if(roll.isEmpty()){
                System.out.println("roll är tom");
            }
        }else if(roll.isEmpty()){
            System.out.println("roll är tom");
        }else{
            System.out.println("Fil och Wiki skiljer sig");
        }
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