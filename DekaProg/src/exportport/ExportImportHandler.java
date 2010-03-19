package exportport;

/**
 * This is the abstract handler that is equal for all databases.
 * Every handler can read and write character and settings.<br>
 * <br>
 * One example of the use:<br>
 * <br>
 * ExportImportHandler database = new WikiHandler("http://wiki.raspare.se");<br>
 *<br>
 * or<br>
 *<br>
 * ExportImportHandler database = new FileHandler("file:///C:/DekaProg/");<br>
 *<br>
 * @author Martin Andersson
 * @version 1.0
 * @see ExportImport.WikiHandler
 * @see ExportImport.FileHandler
 */
public abstract class ExportImportHandler extends ExportHandler{


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
     *
     * @param characterName Name of the character to be read.
     * @return Returns the character read.
     */
    abstract public String readCharacter(String characterName);

    /**
     * This function retrieves a campaign setting from the database.
     *
     * @param campaignName Name of the campaign to be read.
     * @return Returns the campaign settings read.
     */
    abstract public String readSettings(String campaignName);
}
