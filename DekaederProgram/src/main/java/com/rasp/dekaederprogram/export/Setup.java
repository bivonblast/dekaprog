/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import java.io.File;


/**
 *
 * @author martin
 */
public class Setup {
    private static String path = "";
    private static String applicationName = "DekaProg";

    public static String getPath(){
        if(path.equals("")){ //Do this the first time the program calls getPath
            final String userHome = System.getProperty("user.home", ".");
            final File workingDirectory;
            final String sysName = System.getProperty("os.name").toLowerCase();
            if (sysName.contains("windows")){   //Directory path for Windows
                final String applicationData = System.getenv("APPDATA");
                if (applicationData != null){
                    workingDirectory = new File(applicationData, applicationName);
                }else{
                    workingDirectory = new File(userHome, applicationName);
                }
            }else if (sysName.contains("mac")){ //Directory path for MacOS
                workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
            }else if (sysName.contains("linux") || sysName.contains("solaris")){    //Directory path for linux
                workingDirectory = new File(userHome, applicationName);
            }else{
                workingDirectory = new File(".");  //No information about the operating System
            }
            if (!workingDirectory.exists())
                if (!workingDirectory.mkdirs())
                    throw new RuntimeException("The working directory could not be created: " + workingDirectory);
            path = workingDirectory.getPath() + "/";
            System.out.println("Path is set to: " + path);
        }
        return path;

        
        //return System.getProperty("user.home") + "/DekaProg/";
    }
    //public static String PATH = "file:///C:/DekaProg";
    //public static String PATH = "file:///C:/Users/Martin/AppData/Roaming/DekaProg/";
}
