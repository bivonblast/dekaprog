package ExportImport;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jwbf.actions.util.ActionException;
import net.sourceforge.jwbf.actions.util.ProcessException;
import net.sourceforge.jwbf.bots.MediaWikiBot;

/**
 * An ExportImportHandler that sends the data to a mediaWiki using jwbf bot program.
 *
 * @version 0.5A
 * @author Martin Andersson
 * @see ExportImport.ExportImportHandler
 * @see ExportImport.FileHandler
*/
public class WikiHandler extends ExportImportHandler{
    private MediaWikiBot wikiConnection;    //Kopplingen till mediaWikin
    private String username;
    private String password;

    public WikiHandler(String location){
        super(location);
        if (connect()){
            this.username = "";
            this.password = "";
        }
    }

    public WikiHandler(String location, String username, String password){
        super(location);
        if (connect(username, password)){
            this.username = username;
            this.password = password;
        }
    }

    /**
     * Login as a user with no write privilieges.
     * NOTE: The user DekaProg has write privilieges at the moment to simplify testing of
     * writing to wiki-part.
     * @return Returns true if login was successful, false otherwise.
     */
    public boolean connect() {
        return connect("DekaProg", "tester");
    }

    /**
     * Login as a user with username and password.
     * @param username The users username
     * @param password The users password
     * @return Returns true if login was successful, false otherwise.
     */
    public boolean connect(String username, String password) {
        try {
            wikiConnection = new MediaWikiBot(new URL(location));
            wikiConnection.login(username, password);
            return true;
        } catch (ActionException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String readCharacter(String characterName) {
        try {
            return wikiConnection.readData(characterName).getText().toString();
        } catch (ActionException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProcessException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean writeCharacter(String characterName, String character) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String readSettings(String campaignName) {
        try {
            return wikiConnection.readData(campaignName).getText().toString();
        } catch (ActionException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProcessException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean writeSettings(String campaignName, String campaign) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}