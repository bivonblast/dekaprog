package ExportImport;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
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
    private String character;

    public PDFHandler(String location){
        super(location);
    }

    @Override
    public boolean writeCharacter(String characterName, String character) {
        try {
            character = "\n /abilityX 43 def \n /valueX 121 def \n /startY 530 def \n /spaceY 11 def \n (3) (Akrobatik) printAbility (1+2) (Fingerfärdighet) printAbility (Fällor) printOnlyAbility \n showpage \n";//43 530";
            return copyFile(new URI(location + "Psbase"), new URI(location + "rollf.eps"), character, new URI(location + characterName.replaceAll(" ", "%20") + ".ps"));
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

    public boolean copyFile(URI srcFile1, URI srcFile2, String characterString, URI dtFile){
        try{
            Document doc = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\DekaProg\\test.pdf"));
            doc.open();
            doc.add(Image.getInstance("C:\\DekaProg\\rollf.png"));
            doc.close();
            /*File src1 = new File(srcFile1);
            File src2 = new File(srcFile2);
            File dt = new File(dtFile);
            InputStream in = new FileInputStream(src1);
            OutputStream out = new FileOutputStream(dt,true);
            byte[] buf = new byte[8192];
            int len;
            while ((len = in.read(buf)) > 0){
                    out.write(buf, 0, len);
            }
            in.close();
            in = new FileInputStream(src2);
            while ((len = in.read(buf)) > 0){
                    out.write(buf, 0, len);
            }
            in.close();
            out.write(characterString.getBytes());
            out.close();
            createPDF(new URI("file:///C:/DekaProg/test.pdf"));*/
            return true;
        } catch (DocumentException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }/* catch (URISyntaxException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } */catch(FileNotFoundException ex){
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
}

