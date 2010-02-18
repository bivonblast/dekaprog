package ExportImport;

/**
 * This is the abstract handler that is equal for all databases.
 * Every handler can read and write character and settings.
 *
 * One example of the use:
 *
 * ExportImportHandler database = new WikiHandler("http://wiki.raspare.se");
 *
 * or
 *
 * ExportImportHandler database = new FileHandler("file:///C:/DekaProg/");
 *
 * @author Martin Andersson
 * @version 1.0
 * @see DekaProg.ExportImport.WikiHandler
 * @see DekaProg.ExportImport.FileHandler
 */
public abstract class ExportImportHandler {

    String location;

    public ExportImportHandler(){

    }

    /**
     * @param location The location of the database.
     */
    public ExportImportHandler(String location){
        this.location = location;
    }

    /**
     * This function retrieves a character from the database.
     * @author Martin Andersson
     * @param characterName Name of the character to be read.
     * @return Returns the character read.
     */
    abstract public String readCharacter(String characterName);

    /**
     * This function retrieves a campaign setting from the database.
     *
     * @author Martin Andersson
     * @param campaignName Name of the campaign to be read.
     * @return Returns the campaign settings read.
     */
    abstract public String readSettings(String campaignName);

    /**
     * This function saves a character to the database.
     *
     * @author Martin Andersson
     * @param characterName Name of the character to save.
     * @param character The data to be saved.
     * @return Returns true if it was successfully saved.
     */
    abstract public boolean writeCharacter(String characterName, String character);

    /**
     * This function saves a campaign setting to the database.
     *
     * @author Martin Andersson
     * @param campaignName Name of the campaign to save.
     * @param campaign The data to be saved.
     * @return Returns true if it was successfully saved.
     */
    abstract public boolean writeSettings(String campaignName, String campaign);
}
