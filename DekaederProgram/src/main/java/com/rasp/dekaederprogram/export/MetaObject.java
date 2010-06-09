package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.data.AdvantageTrait;
import com.rasp.dekaederprogram.character.data.HookTrait;
import com.rasp.dekaederprogram.character.data.SkillTrait;
import com.rasp.dekaederprogram.character.data.SkillType;
import com.rasp.dekaederprogram.character.data.Trait;
import com.rasp.dekaederprogram.character.data.TraitHandler;
import java.util.ArrayList;

/**
 *
 * @author MARTIN
 */
public class MetaObject extends DataObject{
    private String otherName;
    private String preText;
    private String postText;
    private String headLine;
    private ArrayList<DataObject> allObjects;
    private int value;
    private boolean hasAValue = false;
    private TraitHandler trait;
    public static String STARTSLINGA = "{StartSlinga}";
    public static String STARTSLINGAEND = "{/StartSlinga}";
    public static String STOPSLINGA = "{StopSlinga}";
    public static String STOPSLINGAEND = "{/StopSlinga}";

    public MetaObject(String chars, String name) {
        super(chars, name);
//        this.otherName = name;
        init();
    }

    public MetaObject(String otherName, String chars, String name) {
        super(chars, name);
        this.otherName = otherName;
        init();
    }

    public MetaObject(String otherName, String chars, String name, int value) {
        super(chars, name);
        this.otherName = otherName;
        this.value = value;
        init();
    }
    
    public MetaObject(String otherName, String chars, String name, String preText, String postText) {
        super(chars, name);
        this.otherName = otherName;
        this.preText = preText;
        this.postText = postText;
        init();
    }

    public MetaObject(String otherName, String chars, String name, String preText, String postText, int value) {
        super(chars, name);
        this.otherName = otherName;
        this.preText = preText;
        this.postText = postText;
        this.value = value;
        this.hasAValue = true;
        init();
    }

    public boolean hasValue(){
        return hasAValue;
    }

    public Trait createTrait(){
        
        switch(TraitType.getTrait(getName())){
            case TraitType.PHYSICAL_SKILL:
                return new SkillTrait(otherName, SkillType.newPhysicalSkillType(), value);
            case TraitType.SOCIAL_SKILL:
                return new SkillTrait(otherName, SkillType.newSocialSkillType(), value);
            case TraitType.MENTAL_SKILL:
                return new SkillTrait(otherName, SkillType.newMentalSkillType(), value);
            case TraitType.ADVANTAGE:
                return new AdvantageTrait(otherName, value);
            case TraitType.HOOK:
                return new HookTrait(otherName, "" + value);
            default:
                return new SkillTrait(otherName, SkillType.newMentalSkillType(), 0);
        }
    }

    private void init() {
//        trait = decideTrait();
        createParser();
    }

//    private TraitHandler decideTrait(){
//        if(name.equals("Koncept")){
//            return new SkillTrait()
//        }else if(name.equals("Färdigheter")){
//        }else if(name.equals("Fördelar")){
//        }else if(name.equals("Krokar")){
//        }else if(name.equals("Poäng")){
//        }else if(name.equals("Övrigt")){
//        }
//    }

    private void createParser() {
        String tmpChars = chars;
        String tmpPart;
        headLine = chars.substring(0, chars.indexOf("{")).trim();
        tmpChars = tmpChars.substring(tmpChars.indexOf("{"));
        while(!tmpChars.equals("")){
            tmpChars = tmpChars.trim();
            if(chars.indexOf("{startSlinga}") >= 0){
                String startSlinga = chars.substring(chars.indexOf(STARTSLINGA)+STARTSLINGA.length(), chars.indexOf(STARTSLINGAEND));
                String stopSlinga = chars.substring(chars.indexOf(STOPSLINGA)+STOPSLINGA.length(), chars.indexOf(STOPSLINGAEND));
                String insideLoop = chars.substring(chars.indexOf(STARTSLINGAEND)+STARTSLINGAEND.length(), chars.indexOf(STOPSLINGA));
                SlingaObject addSlinga = new SlingaObject(startSlinga, stopSlinga, insideLoop);
                //Skapa slinga
                //Red ut begreppen med hur man läser av en färdighet i en slinga.
            }else{
                MetaObject newObject = createNextMetaObject(tmpChars);
                allObjects.add(newObject);
                tmpChars = tmpChars.substring(newObject.getFullLength()).trim();
            }

            tmpChars = tmpChars.trim();
        }
        tmpChars = tmpChars.trim();
        tmpChars = tmpChars.substring(getBeginSearchNameLength());
        tmpChars = tmpChars.trim();
        //tmpPart = tmpChars.substring(type)
    }
}
