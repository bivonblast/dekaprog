package ExportImport;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class PDFHandler extends ExportHandler {
    private File baseFile;
    private BufferedReader baseFileReader = null;
    private File epsFile;
    private BufferedReader epsReader = null;
    private File outputFile;
    private BufferedWriter outputFileWriter;

    /** The source PDF. */
    private URI SOURCE;
    /** The resulting PDF. */
    public URI RESULT;
    /** The movie poster. */
    public URI CHARACTER;
    /** Temp poster. */
    public URI TEMP;

    private float yIncrease = 0.0f;
    private float valueIncrease = 0.0f;
    private float x = 0.0f;
    private float y = 0.0f;
    private float columnLength = 0.0f;


    public PDFHandler(String location, float x, float y, float yIncrease, float valueIncrease, float columnLength){
        super(location);
        this.x = x;
        this.y = y;
        this.valueIncrease = valueIncrease;
        this.yIncrease = yIncrease;
        this.valueIncrease = valueIncrease;
        this.columnLength = columnLength;
    }
    
    @Override
    public boolean writeCharacter(String characterName, String character) {
        try {
            character = "\n /abilityX 43 def \n /valueX 121 def \n /startY 530 def \n /spaceY 11 def \n (3) (Akrobatik) printAbility (1+2) (Fingerfärdighet) printAbility (Fällor) printOnlyAbility \n showpage \n";//43 530";
            SOURCE = new URI(location + "rollf.pdf");
            RESULT = new URI(location + "test2.pdf");
            TEMP = new URI(location + "temp.pdf");
            CHARACTER = new URI(location + "BenGrape.jpg");
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
            System.out.println("SOURCE.getPath() = " + SOURCE.getPath());
            System.out.println("RESULT.getPath() = " + RESULT.getPath());
            System.out.println("CHARACTER.getPath() = " + CHARACTER.getPath());
            System.out.println("TEMP.getPath() = " + TEMP.getPath());

            createPdf(SOURCE.getPath());

            // Create a reader
            PdfReader reader = new PdfReader(TEMP.getPath());
            // Create a document
            Document document = new Document(PageSize.A4);
            // Create a writer
            PdfWriter writer
                    = PdfWriter.getInstance(document, new FileOutputStream(RESULT.getPath()));
            //Start writing to the document
            document.open();
            PdfContentByte canvas = writer.getDirectContent();
            PdfImportedPage page;
            BaseFont bf
                = BaseFont.createFont(BaseFont.ZAPFDINGBATS, "", BaseFont.EMBEDDED);
            for (int i = 0; i < reader.getNumberOfPages(); ) {
                page = writer.getImportedPage(reader, ++i);
                //canvas.addTemplate(page, 1f, 0, 0.4f, 0.4f, 72, 50 * i);
                canvas.addTemplate(page,  1f, 0, 0f, 1f, 0, 0);
                //canvas.addTemplate(page, new AffineTransform(AffineTransform.TYPE_IDENTITY) );
//                canvas.beginText();
//                AffineTransform hej;
//                canvas.setFontAndSize(bf, 20);
//                canvas.showTextAligned(Element.ALIGN_CENTER,
//                    String.valueOf((char)(181 + i)), 496, 150 + 50, 0);
//                canvas.endText();
            }
            // step 5
            document.close();
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

    private void createPDF(URI dtFile) throws IOException {
        //DistillerServiceClient distiller = new DistillerServiceClient(new ServiceClientFactory());
        Runtime rt = Runtime.getRuntime();
        //Process pr = rt.exec("cmd /c dir");
        String run = "c:\\DekaProg\\pstopdf.bat C:\\DekaProg\\test.pdf";
        System.out.println("run = " + run);
        Process pr = rt.exec(run);
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        String line=null;

        while((line=input.readLine()) != null) {
            System.out.println(line);
        }
        try {
            int exitVal = pr.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                new FileOutputStream(TEMP.getPath()));
        PdfReader reader = new PdfReader(SOURCE.getPath());
        document.open();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        PdfContentByte under = writer.getDirectContent();
        under.addTemplate(page, 1f, 0, 0f, 1f, 0, 0);
        document.newPage();
        // Page 1: a rectangle
        BaseFont bf
                = BaseFont.createFont(BaseFont.HELVETICA, "", BaseFont.EMBEDDED);
        under.beginText();
        under.setFontAndSize(bf, 10);
        for(int column = 0 ; column < 3 ; column++){
            for(int row = 0 ; row < 26 ; row++){
              under.showTextAligned(Element.ALIGN_LEFT, printAbility("Akrobatik"), x+column*columnLength, y - row*yIncrease, 0);
              under.showTextAligned(Element.ALIGN_CENTER, printValue("8+3"), x+column*columnLength+valueIncrease, y - row*yIncrease, 0);
            }
        }
        
        under.endText();//        drawRectangle(under, PageSize.A4.getHeight(), PageSize.A4.getWidth());
//        under.setRGBColorFill(0xFF, 0xD7, 0x00);
//        under.rectangle(5, 5, PageSize.A4.getHeight() - 10, PageSize.A4.getWidth() - 10);
//        under.fill();
//        document.newPage();
        // Page 2: an image
//        drawRectangle(under, PageSize.A4.getHeight(), PageSize.A4.getWidth());
//        Image img = Image.getInstance(CHARACTER.getPath());
//        img.setAbsolutePosition((PageSize.A4.getHeight() - img.getScaledHeight()) / 2,
//                (PageSize.A4.getWidth() - img.getScaledWidth()) / 2);
//        document.add(img);
//        document.newPage();
        // Page 3: the words "Foobar Film Festival"
//        drawRectangle(under, PageSize.A4.getHeight(), PageSize.A4.getWidth());
        Paragraph p = new Paragraph("Myggdräpe                ", new Font(FontFamily.HELVETICA, 12));
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);
        document.newPage();

        
        // Page 4: the words "SOLD OUT"
//        drawRectangle(under, PageSize.A4.getHeight(), PageSize.A4.getWidth());
//        PdfContentByte over = writer.getDirectContent();
//        over.saveState();
//        float sinus = (float)Math.sin(Math.PI / 60);
//        float cosinus = (float)Math.cos(Math.PI / 60);
//        BaseFont bf = BaseFont.createFont();
//        over.beginText();
//        over.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
//        over.setLineWidth(1.5f);
//        over.setRGBColorStroke(0xFF, 0x00, 0x00);
//        over.setRGBColorFill(0xFF, 0xFF, 0xFF);
//        over.setFontAndSize(bf, 36);
//        over.setTextMatrix(cosinus, sinus, -sinus, cosinus, 50, 324);
//        over.showText("Levan Darish");
//        over.setTextMatrix(0, 0);
//        over.endText();
//        over.restoreState();
        // step 5
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
    public String printAbility(String fardighet){
        String[] Abilities = {"Akrobatik", "Fällor", "Fångst", "Frita", "Garderob", "Mysticism", "Markering", "Enhandsvapen"};
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
}

