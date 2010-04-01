package export;

import character.DekaederCharacter;
import character.data.ConceptHandler;
import character.data.PointHandler;
import character.data.SkillHandler;
import character.data.SkillTrait;
import character.data.SkillType;
import character.data.SpecialityHandler;
import character.data.Trait;
import character.data.TraitHandler;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
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
    private String username;
    private DekaederCharacter character;


    /**
     * Creates a Handler for PDF-generating
     * @param location Where all the property file are placed
     */
    public PDFHandler(String location){
        super(location);
        this.yIncrease = 11.3f;  //Cheating! Should be handled in configfile!
        this.character = new DekaederCharacter(new ConceptHandler(), new PointHandler(0), new SkillHandler(), new SpecialityHandler());
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

    /**
     * Creates a complete pdf-document.<br>
     * This is the first step in creating a new PDF.
     * @return true if created successfully
     */
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
        allText.addAll(getTraitToPdf(character.getSkillHandler()));

        // Färdigheter
        BaseFont bf
                = BaseFont.createFont(BaseFont.HELVETICA, "", BaseFont.EMBEDDED);
        under.beginText();
        under.setFontAndSize(bf, 10);
        for(TextPositionInPdf curLine : allText){
            under.setFontAndSize(bf, getProperSize(curLine, bf, 10));
            under.showTextAligned(curLine.getAlignment(), curLine.getText(), (float)curLine.getX(), (float)curLine.getY(), 0);
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
     * Extract all traits from curHandler and converts them<br>
     * to @link TextPositionInPdf and put into a Vector.
     * @param curHandler the current @link TraitHandler keping all the Traits.
     * @return Vector with elements of @link TextPositionInPdf
     */
    private Vector<TextPositionInPdf> getTraitToPdf(TraitHandler<? extends Trait> curHandler){
        Vector<? extends Trait> allTraits = curHandler.getTraits();
        Vector<TextPositionInPdf> resultingText = new Vector<TextPositionInPdf>();
        BufferedReader currentFileReader = null;
        try {
            File conceptFile = new File(new URI(location + "rollf.cfg"));
            for (Trait curTrait : allTraits) {
                if(!(curTrait.getClass().isInstance(new SkillTrait("Dummy", SkillType.newPhysicalSkillType(), 3)))){
                    System.out.println("Ingen Skill: " + curTrait.getName());
                }else{
                    System.out.println("Är en Skill: " + curTrait.getName());
                }
                currentFileReader = new BufferedReader(new FileReader(conceptFile));
                String bufLine;
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

    /**
     * Extract all skills from curHandler and converts them<br>
     * to @link TextPositionInPdf and put into a Vector.
     * @param curHandler the current @link SkillHandler keping all the Skills.
     * @return Vector with elements of @link TextPositionInPdf
     */
    private Vector<TextPositionInPdf> getTraitToPdf(SkillHandler curHandler){
        Vector<? extends SkillTrait> allTraits = curHandler.getTraits();
        Vector<TextPositionInPdf> resultingText = new Vector<TextPositionInPdf>();
        BufferedReader currentFileReader = null;
        try {
            File conceptFile = new File(new URI(location + "rollf.cfg"));
            currentFileReader = new BufferedReader(new FileReader(conceptFile));
            String bufLine, mentala = null, fysiska = null, sociala = null, mentalaV = null, fysiskaV = null, socialaV = null;
            while ((bufLine = currentFileReader.readLine()) != null) {
                if(bufLine.contains("MentalaFärdigheter")){
                    mentala = bufLine;
                }
                if(bufLine.contains("MentalaVärden")){
                    mentalaV = bufLine;
                }
                if(bufLine.contains("FysiskaFärdigheter")){
                    fysiska = bufLine;
                }
                if(bufLine.contains("FysiskaVärden")){
                    fysiskaV = bufLine;
                }
                if(bufLine.contains("SocialaFärdigheter")){
                    sociala = bufLine;
                }
                if(bufLine.contains("SocialaVärden")){
                    socialaV = bufLine;
                }
            }
            currentFileReader.close();
            if(mentala.isEmpty() || sociala.isEmpty() || fysiska.isEmpty()){
                throw new NullPointerException("Didn't contain anything.");
            }
            TextPositionInPdf menBas = new TextPositionInPdf(mentala, "MentalaFärdigheter");
            TextPositionInPdf menValueBas = new TextPositionInPdf(mentalaV, "MentalaVärden");
            double maxMental = menValueBas.getLength();
            TextPositionInPdf fysBas = new TextPositionInPdf(fysiska, "FysiskaFärdigheter");
            TextPositionInPdf fysValueBas = new TextPositionInPdf(fysiskaV, "FysiskaVärden");
            double maxFysisk = menValueBas.getLength();
            TextPositionInPdf socBas = new TextPositionInPdf(sociala, "SocialaFärdigheter");
            TextPositionInPdf socValueBas = new TextPositionInPdf(socialaV, "SocialaVärden");
            double maxSocial = menValueBas.getLength();
            Vector<SkillTrait> menSkills = curHandler.getMentalSkills();
            Vector<SkillTrait> fysSkills = curHandler.getPhysicalSkills();
            Vector<SkillTrait> socSkills = curHandler.getSocialSkills();
            resultingText.addAll( extractAllLines(menSkills, menBas, menValueBas, maxMental));
            resultingText.addAll( extractAllLines(fysSkills, fysBas, fysValueBas, maxFysisk));
            resultingText.addAll( extractAllLines(socSkills, socBas, socValueBas, maxSocial));

            return resultingText;
        } catch (IOException ex) {
                Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    /**
     * Extract all @Points from curHandler and converts them<br>
     * to @link TextPositionInPdf and put into a Vector.
     * @param curHandler the current @link PointHandler keeping all the @link Points.
     * @return Vector with elements of @link TextPositionInPdf
     */
    private Vector<TextPositionInPdf> getTraitToPdf(PointHandler curHandler){
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
        return null;
    }

    /**
     * Extract all the Lines needed for the Skills. Add Input for Skills and Skillvalues.<br>
     * Also creates extra rows for Common Skill-categories (Teknik: Vapen).
     * @param allTraits allTraits to be extracted.
     * @param curBas the information about where to put the skills
     * @param curValueBas the information where to put the value of the skill
     * @param maxImports Maximum number of placements
     * @return Vector containing all the elements to be added to the PDF.
     */
    private Vector<TextPositionInPdf> extractAllLines(Vector<SkillTrait> allTraits, TextPositionInPdf curBas, TextPositionInPdf curValueBas, double maxImports) {
        Vector<TextPositionInPdf> resultingText = new Vector<TextPositionInPdf>();
        int i = 0;
        String curName;
        for (SkillTrait curTrait : allTraits) {
            curName = curTrait.getName();
            if (curName.contains(":")){
                String name = curName.substring(0, curName.indexOf(":"));
                curName = "   " + curName.substring(curName.indexOf(":")+1, curName.length());
                boolean entry = false;
                for(TextPositionInPdf curText : resultingText){
                    if(curText.getText().equals(name)){
                        entry = true;
                    }
                }
                if(!entry){
                    resultingText.add(new TextPositionInPdf(name, curBas.getX(), curBas.getY()-(i++)*yIncrease, curBas.getLength(), curBas.getAlignment()));
                }

            }
            resultingText.add(new TextPositionInPdf(curName, curBas.x, curBas.getY()-(i++)*yIncrease, curBas.getLength(), curBas.getAlignment()));
            resultingText.add(new TextPositionInPdf(curTrait.valueToString().trim(), curValueBas.x, curValueBas.getY()-(i-1)*yIncrease, curValueBas.getLength(), curValueBas.getAlignment()));
            if(i >= maxImports){
                System.out.println("Not enough space!");
                break;
            }
        }
        return resultingText;
    }

    /**
     * Convert BaseFont to Font and then executes<br>
     * getProperSize(TextPositionInPdf, Font, float);
     *
     * @param curLine
     * @param bf
     * @param size
     */
    private float getProperSize(TextPositionInPdf curLine, BaseFont bf, float size) {
         return getProperSize(curLine, new Font(bf, size), size);
    }


    /**
     * Calculates the size of the text. If the text won't fit into the length<br>
     * of the object this function scales it down until it fits.
     * @param curLine the line of code to be scaled
     * @param font the font to be used
     * @param size the default size. The return will be this size if the text fits from the beginning
     * @return new size of the object
     */
    private float getProperSize(TextPositionInPdf curLine, Font font, float size) {
        if(curLine.getLength() > 0 && size > 0){
            font.setSize(size);
            Chunk curText = new Chunk(curLine.getText(), font);
            while(curText.getWidthPoint() > curLine.getLength()){
                size=size-0.1f;
                font.setSize(size);
                curText.setFont(font);
            }
            if(size>0){
                return size;
            }else{
                return -1;
            }
        }else{
            return 10;
        }
    }
        
     /**
      * Keeps all interesting data for the Text to be put into the PDF except font.
      */
     public class TextPositionInPdf extends Object{
        private String text;
        private double x;
        private double y;
        private double length;
        private int alignment;

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
         * @param alignment left, center or right adjusted text
         */
        public TextPositionInPdf(String text, double x, double y, double length, int alignment) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.length = length;
            this.alignment = alignment;
        }

        /**
         * Empty Constructor
         */
        public TextPositionInPdf(){
            x = 0;
            y = 0;
            length = 0;
            alignment = Element.ALIGN_LEFT;
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
        public double getLength() {
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
        public double getX() {
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
        public double getY() {
            return y;
        }

        /**
         * Sets the value of variable y
         * @param y
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

