package ExportImport;

/**
 * This is an abstract Handler for files/databases etc
 * that is only possible to export to, not import from.<br>
 * An example of this is the PostscriptHandler @link ExportImport.PSHandler
 * @author Martin Andersson
 * @version 1.0
 * @see ExportImport.ExportImportHandler
 */
abstract public class ExportHandler {
    protected String location;

    public ExportHandler(){
    }

    public ExportHandler(String location){
        this.location = location;
    }

    /**
     * This function saves a character to the database.
     *
     * @param characterName Name of the character to save.
     * @param character The data to be saved.
     * @return Returns true if it was successfully saved.
     */
    abstract public boolean writeCharacter(String characterName, String character);

     /**
     * This function saves a campaign setting to the database.
     *
     * @param campaignName Name of the campaign to save.
     * @param campaign The data to be saved.
     * @return Returns true if it was successfully saved.
     */
    abstract public boolean writeSettings(String campaignName, String campaign);

}
