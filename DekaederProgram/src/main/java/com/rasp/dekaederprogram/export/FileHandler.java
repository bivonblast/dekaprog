/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.DekaederCharacter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private String settings;

    public FileHandler(){
        super(Setup.getPath());  //Ska vara %appdata%\DekaProg
    }

    public FileHandler(String location){
        super(location);
    }

    @Override
    public DekaederCharacter readCharacter(String characterName) {
        String filename = location + characterName + ".ser";
        try {
            currentFile = new File(filename);
            FileInputStream fileIn = new FileInputStream(currentFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object newChar = in.readObject();
            in.close();
            fileIn.close();
            return (DekaederCharacter)newChar;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in file: " + filename);
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String readCharacter(String characterName, boolean test) {
        try {
            System.out.println(characterName);
            StringBuilder buildCharacter = new StringBuilder();
            currentFile = new File(characterName);
//            FileInputStream fileOut = new FileInputStream(currentFile);
//            ObjectInputStream out = new ObjectInputStream(fileOut);
//            
            currentFileReader = new BufferedReader(new FileReader(currentFile));
            buildCharacter.append(currentFileReader.readLine());
            String tmpLine;
            while (( tmpLine = currentFileReader.readLine()) != null) {
                buildCharacter.append("\n").append(tmpLine);
            }
            currentFileReader.close();
            character = buildCharacter.toString();
            return character;
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String readSettings(String campaignName) {
        try {
            System.out.println(campaignName);
            StringBuilder buildSettings = new StringBuilder();
            currentFile = new File(campaignName);
//            FileInputStream fileOut = new FileInputStream(currentFile);
//            ObjectInputStream out = new ObjectInputStream(fileOut);
//
            currentFileReader = new BufferedReader(new FileReader(currentFile));
            buildSettings.append(currentFileReader.readLine());
            String tmpLine;
            while (( tmpLine = currentFileReader.readLine()) != null) {
                buildSettings.append("\n").append(tmpLine);
            }
            currentFileReader.close();
            settings = buildSettings.toString();
            return settings;
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean writeCharacter(DekaederCharacter character, String username, boolean showValue) {
        try {
            currentFile = new File(location + character.getConceptHandler().getTrait("Namn").valueToString() + ".ser");
            FileOutputStream fileOut = new FileOutputStream(currentFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(character);
            out.close();
            fileOut.close();
            return true;
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
