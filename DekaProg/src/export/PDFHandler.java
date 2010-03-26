package export;

import character.DekaederCharacter;
import character.data.ConceptHandler;
import character.data.PointHandler;
import character.data.Trait;
import character.data.TraitHandler;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Andersson
 */
public class PDFHandler extends ExportHandler {
    /** The source PDF. */
    public URI source;
    /** The resulting PDF. */
    public URI result;
    /** The movie poster. */
    //public URI character;
    /** Temp poster. */
    public URI temp;

    private float yIncrease = 0.0f;
    private float valueIncrease = 0.0f;
    private float x = 0.0f;
    private float y = 0.0f;
    private float columnLength = 0.0f;
    private String username;
    private DekaederCharacter character;


    public PDFHandler(String location, float x, float y, float yIncrease, float valueIncrease, float columnLength){
        super(location);
        this.x = x;
        this.y = y;
        this.valueIncrease = valueIncrease;
        this.yIncrease = yIncrease;
        this.valueIncrease = valueIncrease;
        this.columnLength = columnLength;
        this.character = new DekaederCharacter(new ConceptHandler(), new PointHandler(0));
    }

    @Override
    public boolean writeCharacter(DekaederCharacter character, String username) {
        try {
            this.character = character;
            source = new URI(location + "rollf.pdf");
            result = new URI(location + character.getConceptHandler().getTrait("Namn").valueToString().replaceAll(" ", "%20") + ".pdf");
            temp = new URI(location + "temp.pdf");
            this.username = username;
            //this.character = new URI(location + "BenGrape.jpg");
            return createFile();

        } catch (URISyntaxException ex) {
            //Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private String readFile(File currentFile) throws IOException {
        BufferedReader currentFileReader = new BufferedReader(new FileReader(currentFile));
        String bufFile = currentFileReader.readLine();
        String tmpLine;
        while (( tmpLine = currentFileReader.readLine()) != null) {
            bufFile = bufFile + "\n" + tmpLine;
        }
        currentFileReader.close();
        return bufFile;
    }

    @Override
    public boolean writeSettings(String campaignName, String campaign) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean createFile(){
        try{
            System.out.println("Starting creating \"" + result.getPath() + "\".");

            createPdf(source.getPath());

            // Create a reader
            PdfReader reader = new PdfReader(temp.getPath());
            // Create a document
            Document document = new Document(PageSize.A4);
            // Create a writer
            PdfWriter writer
                    = PdfWriter.getInstance(document, new FileOutputStream(result.getPath()));
            //Start writing to the document
            document.open();
            PdfContentByte canvas = writer.getDirectContent();
            PdfImportedPage page;
            BaseFont bf
                = BaseFont.createFont(BaseFont.ZAPFDINGBATS, "", BaseFont.EMBEDDED);
            for (int i = 0; i < reader.getNumberOfPages(); ) {
                page = writer.getImportedPage(reader, ++i);
                canvas.addTemplate(page,  1f, 0, 0f, 1f, 0, 0);
            }
            document.close();
            System.out.println("Finished creating \"" + result.getPath() + "\".");
            return true;
        } catch (DocumentException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage() + " in the specified directory.");
            return false;
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException
     * @throws    IOException
     */
    public void createPdf(String filename)
        throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(temp.getPath()));
        // Layer 1: Rollformulär
        PdfReader reader = new PdfReader(source.getPath());
        document.open();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        PdfContentByte under = writer.getDirectContent();
        under.addTemplate(page, 1f, 0, 0f, 1f, 0, 0);
        document.newPage();
        // Layer 2: Text
        Vector<TextPositionInPdf> allText = new Vector<TextPositionInPdf>();
        // Koncept
        allText.addAll(getTraitToPdf(character.getConceptHandler()));
        // Poäng
        allText.addAll(getTraitToPdf(character.getPointHandler()));

        // Färdigheter
        BaseFont bf
                = BaseFont.createFont(BaseFont.HELVETICA, "", BaseFont.EMBEDDED);
        under.beginText();
        under.setFontAndSize(bf, 10);
        for(TextPositionInPdf curLine : allText){
            under.showTextAligned(curLine.getAlignment(), curLine.getText(), curLine.getX(), curLine.getY(), 0);
        }
        for(int column = 0 ; column < 3 ; column++){
            for(int row = 0 ; row < 26 ; row++){
              under.showTextAligned(Element.ALIGN_LEFT, printAbility("Akrobatik"), x+column*columnLength, y - row*yIncrease, 0);
              under.showTextAligned(Element.ALIGN_CENTER, printValue("8+3"), x+column*columnLength+valueIncrease, y - row*yIncrease, 0);
            }
        }
        // Användare
        under.setFontAndSize(bf, 12);
        under.showTextAligned(Element.ALIGN_LEFT, username, 452, 794, 0);
        under.endText();
        document.close();
    }

        /**
     * Draws a rectangle
     * @param content the direct content layer
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public static void drawRectangle(PdfContentByte content, float width, float height) {
        content.saveState();
        PdfGState state = new PdfGState();
        state.setFillOpacity(0.6f);
        content.setGState(state);
        content.setRGBColorFill(0xFF, 0xFF, 0xFF);
        content.setLineWidth(3);
        content.rectangle(0, 0, width, height);
        content.fillStroke();
        content.restoreState();
    }

    /**
     * prints the value of the ability. In place eas long as DekaederCharacter isn't developed properly.
     * @param fardighet name of the ability
     */
    public String printValue(String fardighet){
        Random test = new Random();
        int bonus = test.nextInt(4);
        int grund = test.nextInt(15)+1;
        String returString = "" + grund;
        //om bonus större än 0 skriv ut +<bonusvarde>
        if(bonus > 0){
            returString = returString + "+" + bonus;
        }
        return returString;
    }


    /**
     * prints the name of the ability. Calculates the space the name will take and change it if needed.
     * @param fardighet name of the ability
     */
    public String printAbility(String fardighet){
        String[] abilities = {"Akrobatik", "Fällor", "Fångst", "Frita", "Garderob", "Mysticism", "Markering", "Enhandsvapen"};
        Random test = new Random();
        int ability = test.nextInt(8);
        return abilities[ability];
    }

    private Vector<TextPositionInPdf> getTraitToPdf(TraitHandler<? extends Trait> curHandler){
        Vector<? extends Trait> allTraits = curHandler.getTraits();
        Vector<TextPositionInPdf> resultingText = new Vector<TextPositionInPdf>();
        BufferedReader currentFileReader = null;
        try {
            File conceptFile = new File(new URI(location + "rollf.cfg"));
            for (Trait curTrait : allTraits) {
                currentFileReader = new BufferedReader(new FileReader(conceptFile));
                String bufLine;
                //Vector<Trait> traits = character.get
                while ((bufLine = currentFileReader.readLine()) != null) {
                    if(bufLine.contains(curTrait.getName())){
                        resultingText.add(new TextPositionInPdf(bufLine, curTrait.valueToString()));
                        break;
                    }
                }
                currentFileReader.close();
            }
            return resultingText;
        } catch (IOException ex) {
                Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
        private Vector<TextPositionInPdf> getTraitToPdf(PointHandler curHandler){
        {
            String[] stringsToRead = {"Karma", "Rollpoäng", "VeteranPoäng", "VeteranÄventyr", "Stilpoäng", "Startpoäng"};
            BufferedReader currentFileReader = null;
            Vector<TextPositionInPdf> parsedText = new Vector<TextPositionInPdf>();

            try {
                File conceptFile = new File(new URI(location + "rollf.cfg"));
                currentFileReader = new BufferedReader(new FileReader(conceptFile));
                String bufLine;
                while ((bufLine = currentFileReader.readLine()) != null) {
                    if(bufLine.contains(stringsToRead[0])){
                        parsedText.add(new TextPositionInPdf(bufLine, "" + curHandler.getTrait(stringsToRead[0]).valueToString()));
                    }else if(bufLine.contains(stringsToRead[1])){
                        parsedText.add(new TextPositionInPdf(bufLine, "" + curHandler.getExperiencePoints()));
                    }else if(bufLine.contains(stringsToRead[2])){
                        parsedText.add(new TextPositionInPdf(bufLine, "" + curHandler.getVeteranPoints()));
                    }else if(bufLine.contains(stringsToRead[3])){
                        parsedText.add(new TextPositionInPdf(bufLine, "" + curHandler.getTrait(stringsToRead[3]).valueToString()));
                    }else if(bufLine.contains(stringsToRead[4])){
                        parsedText.add(new TextPositionInPdf(bufLine, "" + curHandler.getTrait(stringsToRead[4]).valueToString()));
                    }else if(bufLine.contains(stringsToRead[5])){
                        parsedText.add(new TextPositionInPdf(bufLine, "" + curHandler.getStartingPoints()));
                    }
                }

                currentFileReader.close();
                return parsedText;
            } catch (URISyntaxException ex) {
                Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    currentFileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
        
    public class TextPositionInPdf extends Object{
        private String text;
        private int x;
        private int y;
        private int length;
        private int alignment = Element.ALIGN_LEFT;

        /**
         * The extract-constructor, creating a TextPositionInPdf from a string from a config-file
         * @param line The read line in a config-file
         * @param label The label that i ssupposed to be set at the coordinates
         */
        public TextPositionInPdf(String line, String label){
            this.text = label;
            alignment = Integer.decode(line.substring(line.lastIndexOf("#")+1, line.length())).intValue();
            line = line.substring(0, line.lastIndexOf("#"));
            length = Integer.decode(line.substring(line.lastIndexOf("#")+1, line.length())).intValue();
            line = line.substring(0, line.lastIndexOf("#"));
            y = Integer.decode(line.substring(line.lastIndexOf("#")+1, line.length()));
            line = line.substring(0, line.lastIndexOf("#"));
            x = Integer.decode(line.substring(line.lastIndexOf("#")+1, line.length()));
        }

        /**
         * Copy-constructor
         * @param text The Label for the object
         * @param x X-coordinate in the Pdf
         * @param y Y-coordinate in the Pdf
         * @param length Maximum length in Pdf
         */
        public TextPositionInPdf(String text, int x, int y, int length) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.length = length;
        }

        /**
         * Empty Constructor
         */
        public TextPositionInPdf(){
            x = 0;
            y = 0;
            length = 0;
            text = "";
        }

        /**
         * Returns value of variable text
         * @return text
         */
        public String getText() {
            return text;
        }

        /**
         * Sets the value of variable text
         * @param text new value of text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * Returns value of variable text
         * @return alignment
         */public int getAlignment() {
            return alignment;
        }

        /**
         * Sets the value of variable text
         * @param alignment new value of text
         */
        public void setAlignment(int alignment) {
            this.alignment = alignment;
        }

        /**
         * Returns value of variable length
         * @return length
         */
        public int getLength() {
            return length;
        }

        /**
         * Sets the value of variable length
         * @param length new value of length
         */
        public void setLength(int length) {
            this.length = length;
        }

        /**
         * Returns value of variable x
         * @return x
         */
        public int getX() {
            if(alignment == Element.ALIGN_LEFT){
                return x;
            }else if(alignment == Element.ALIGN_CENTER){
                return x + length/2;
            }else if(alignment == Element.ALIGN_RIGHT){
                return x + length;
            }else{
                return x;
            }
        }

        /**
         * Sets the value of variable x
         * @param x new value of x
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Returns value of variable y
         * @return y
         */
        public int getY() {
            return y;
        }

        /**
         * Sets the value of variable y
         * @param text y value of y
         */
        public void setY(int y) {
            this.y = y;
        }

        /**
         * @return A string in this format "{text}" ({x}, {y}, {length})
         */
        @Override
        public String toString(){
            return "\"" + text + "\" (" + x + ", " + y + ", " + length + ")";
        }
    }
}

