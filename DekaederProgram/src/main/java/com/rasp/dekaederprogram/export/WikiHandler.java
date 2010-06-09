package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.DekaederCharacter;
import com.rasp.dekaederprogram.character.data.SkillHandler;
import com.rasp.dekaederprogram.character.data.SkillTrait;
import com.rasp.dekaederprogram.character.data.SkillType;
import com.rasp.dekaederprogram.character.data.TraitHandler;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jwbf.core.actions.util.ActionException;
import net.sourceforge.jwbf.core.actions.util.ProcessException;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.core.contentRep.SimpleArticle;



/**
 * An ExportImportHandler that sends the data to a mediaWiki using jwbf bot program.
 *
 * @version 0.5A
 * @author Martin Andersson
 * @see export.ExportImportHandler
 * @see export.FileHandler
*/
public class WikiHandler extends ExportImportHandler{
    private MediaWikiBot wikiConnection;    //Kopplingen till mediaWikin
    private String username;
    private String password;
    private Article curArticle;
    private Article curTemplate;

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
            wikiConnection = new MediaWikiBot("http://wiki.raspare.se/");//new URL(location));
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
    public DekaederCharacter readCharacter(String characterName){
        return readCharacter(characterName, characterName+"_MALL");
    }

    public DekaederCharacter readCharacter(String characterName, String characterTemplate) {
        try {
            curTemplate = wikiConnection.readContent(characterTemplate);
            curArticle = wikiConnection.readContent(characterName);
            ArrayList<MetaObject> allAreas = dividePageIntoAreas(curTemplate);
//            ArrayList<TraitHandler> allTraits = new ArrayList<TraitHandler>();
            for(MetaObject curArea : allAreas){
                //.createParsers();
                //Skapa uppsättning av parsningen för det objektet
//                allTraits.add(createTraitHandler(curArea));
            }
            //Starta parsningen av den riktiga artikeln efter mallen vi just skapat i arraylisten
            //skapa en tom dekaedercharacter och börja sen fylla den med saker
            //Sök igenom artikeln och försök finna alla passande objekt utifrån de skapade objeketen i allAreas
            
            return new DekaederCharacter(null, null, null, null, null, null);
        } catch (ActionException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProcessException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean writeCharacter(DekaederCharacter character, String username, boolean showValue) {
        try {
            curArticle.setText(character.getConceptHandler().getTrait("Namn").valueToString());
            //wikiConnection.performAction(new PostModifyContent(wikiConnection, curArticle));
            wikiConnection.writeContent(new SimpleArticle(curArticle));
            return true;
        } catch (ActionException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProcessException ex) {
            Logger.getLogger(WikiHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String readSettings(String campaignName) {
        try {
            curArticle = wikiConnection.readContent(campaignName);

            return curArticle.getText().toString();
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

    public SkillHandler getSkillsFromString(String curBuffer){
        String[] stdBuffer =  {"{|", "|", "|", "|", "|-", "\n|}"};
        Vector<SkillType> allSkillTypes = new Vector<SkillType>();
        allSkillTypes.add(SkillType.newPhysicalSkillType());
        allSkillTypes.add(SkillType.newSocialSkillType());
        allSkillTypes.add(SkillType.newMentalSkillType());
        return getSkillsFromString(curBuffer, stdBuffer, allSkillTypes);
    }


    public SkillHandler getSkillsFromString(String curBuffer, String[] decisionmarks, Vector<SkillType> allSkillTypes){
        SkillHandler allSkills = new SkillHandler();
        String newRow = decisionmarks[1];
        String changeToValue = decisionmarks[2];
        String nextTrait = decisionmarks[3];
        String endOfTrait = decisionmarks[4];
        String endOfTraitBuffer = decisionmarks[5];
        curBuffer = curBuffer.substring(curBuffer.indexOf(decisionmarks[0])+decisionmarks[0].length()+1);
        while(!curBuffer.startsWith(endOfTraitBuffer)){
            curBuffer = curBuffer.substring(curBuffer.indexOf(newRow)+1);
            int nextStop = 0;
            for(SkillType curSkillType : allSkillTypes){
                nextStop = curBuffer.indexOf(changeToValue);
                String name = curBuffer.substring(0, nextStop).replaceAll("\n", "");
                curBuffer = curBuffer.substring(nextStop + changeToValue.length());
                nextStop = curBuffer.indexOf(nextTrait);
                String stringValue = curBuffer.substring(0, nextStop);
                stringValue = stringValue.replaceAll("\n", "").replaceAll("|-", "").replaceAll("|", "").replaceAll(endOfTrait, "").replaceAll(newRow, "");
                nextStop = curBuffer.indexOf(nextTrait);
                int bonusValue = 0;
                if(stringValue.contains("+")){
                    bonusValue = Integer.parseInt(stringValue.substring(stringValue.indexOf("+")+1));
                    stringValue = stringValue.substring(0, stringValue.indexOf("+"));
                }
                int value = 0;
                try{
                    value = Integer.parseInt(stringValue);
                }catch(NumberFormatException e){
                    value = 0;
                }catch(IndexOutOfBoundsException e){
                    value = 0;
                }
                if(nextStop != -1){
                    System.out.println("nextStop: " + nextStop);
                    if(curBuffer.indexOf(nextTrait) == curBuffer.indexOf(endOfTrait)){
                        curBuffer = curBuffer.substring(nextStop + endOfTrait.length());
                    }else{
                        curBuffer = curBuffer.substring(nextStop + nextTrait.length());
                    }
                }
                if(!name.isEmpty() && value != 0){
                    SkillTrait cur = new SkillTrait(name, curSkillType, value, bonusValue);
                    System.out.println(cur.getName() + " " + cur.valueToString());
                    allSkills.add(cur);
                }
            }
        }
        return allSkills;
    }

    public String[] parseWikiCategories(Article article){
        //for(int i = 0 ; i < character.antalfärdigheter() ; i++)
        //gå igenom de sata färdigheterna hos karaktären och kolla om det finns i WikiArtikeln
        //
        return null;
    }

    private TraitHandler createTraitHandler(DataObject curArea) {
        

        return null;
    }

//    private String stripName(String curStringArea, String nextAreaName) {
//        return curStringArea.substring(curStringArea.indexOf("{/" + nextAreaName + "}")+3+nextAreaName.length());
//    }

    private class Area extends Object{
        public String name;
        public String value;

        public Area(String name, String value) {
            this.name = name;
            this.value = value;
        }

        private Area() {
            this.name = "";
            this.value = "";
        }

    }
    private ArrayList<MetaObject> dividePageIntoAreas(Article curArticle) {
        ArrayList<MetaObject> allAreas = new ArrayList<MetaObject>();
        String articleString = curArticle.getText();
        while(articleString.equals("")){
            if(MetaObject.hasArea(articleString)){
                articleString = articleString.trim();
                MetaObject next = DataObject.createNextMetaObject(articleString);
                allAreas.add(next);
                articleString = articleString.substring(next.getFullLength());
                articleString = articleString.trim();
            }else{
                allAreas.add(new MetaObject("Övrigt", articleString));
                articleString = "";
            }
        }
        return allAreas;
    }

    public class ParseSign{
        private String beginArea;
        private String beginHeadline;
        private String endHeadline;
        private String beginAllAttributes;
        private String beginAttribute;
        private String splitAttribute;
        private String endAttribute;
        private String endAllAttribute;
        private String endArea;

        public ParseSign(String beginArea, String beginHeadline, String endHeadline, String beginAllAttributes, String beginAttribute, String splitAttribute, String endAttribute, String endAllAttribute, String endArea) {
            this.beginArea = beginArea;
            this.beginHeadline = beginHeadline;
            this.endHeadline = endHeadline;
            this.beginAllAttributes = beginAllAttributes;
            this.beginAttribute = beginAttribute;
            this.splitAttribute = splitAttribute;
            this.endAttribute = endAttribute;
            this.endAllAttribute = endAllAttribute;
            this.endArea = endArea;
        }

        public ParseSign() {
            this.beginArea = "\n===";
            this.beginHeadline = "===";
            this.endHeadline = "===";
            this.beginAllAttributes = "\n{|";
            this.beginAttribute = "|";
            this.splitAttribute = "|";
            this.endAttribute = "|-";
            this.endAllAttribute = "|}";
            this.endArea = "<br>";
        }

        public String getBeginAllAttributes() {
            return beginAllAttributes;
        }

        public String getBeginArea() {
            return beginArea;
        }

        public String getBeginAttribute() {
            return beginAttribute;
        }

        public String getBeginHeadline() {
            return beginHeadline;
        }

        public String getEndAllAttribute() {
            return endAllAttribute;
        }

        public String getEndArea() {
            return endArea;
        }

        public String getEndAttribute() {
            return endAttribute;
        }

        public String getEndHeadline() {
            return endHeadline;
        }

        public String getSplitAttribute() {
            return splitAttribute;
        }
    }
}
