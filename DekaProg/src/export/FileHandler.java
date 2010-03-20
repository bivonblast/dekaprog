/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An ExportImportHandler that sends the data to files.
 *
 * @version 0.6
 * @author Martin Andersson
 * @see export.ExportImportHandler
 * @see export.WikiHandler
 */
public class FileHandler extends ExportImportHandler{
    private File currentFile;
    private BufferedReader currentFileReader = null;
    private BufferedWriter currentFileWriter;
    private String character;

    public FileHandler(){
        super("file:///C:/Users/Martin/AppData/Roaming/DekaProg/");  //Ska vara %appdata%\DekaProg
    }

    public FileHandler(String location){
        super(location);
    }

    @Override
    public String readCharacter(String characterName) {
        try {
            currentFile = new File(new URI(location + characterName.replaceAll(" ", "%20") + ".dkp"));
            currentFileReader = new BufferedReader(new FileReader(currentFile));
            character = currentFileReader.readLine();
            String tmpLine;
            while (( tmpLine = currentFileReader.readLine()) != null) {
                character = character + "\n" + tmpLine;
            }
            currentFileReader.close();
            return character;
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String readSettings(String campaignName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean writeCharacter(String characterName, String character) {
        try {
            currentFile = new File(new URI(location + characterName.replaceAll(" ", "%20") + ".dkp"));
            currentFileWriter = new BufferedWriter(new FileWriter(currentFile));

            currentFileWriter.write(character);
            currentFileWriter.flush();
            currentFileWriter.close();
            return true;
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean writeSettings(String campaignName, String campaign) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
