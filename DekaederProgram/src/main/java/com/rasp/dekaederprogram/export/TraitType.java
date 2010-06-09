/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 *
 * @author MARTIN
 */
public class TraitType implements Enumeration{

    public static final int TRAIT = 0;
    public static final int PHYSICAL_SKILL = 1;
    public static final int MENTAL_SKILL = 2;
    public static final int SOCIAL_SKILL = 3;
    public static final int ADVANTAGE = 4;
    public static final int HOOK = 5;
    private static int it = HOOK+1;
    private static final String[] allNames = {"namn", "fysisk", "mental", "social", "fördel", "krok"};

    static int getTrait(String name) {
        for(int i = 0; i<allNames.length ; i++){
            if(allNames[i].equalsIgnoreCase(name.substring(0, allNames[i].length()-1))){
                return i;
            }
        }
        return 0;
    }

    private int currentObject;
    ArrayList completeList;

    public TraitType(){
        super();
        currentObject = 0;
        completeList = new ArrayList();
        for(int i = 0; i<it ; i++){
            completeList.add(i);
        }
    }

    @Override
    public boolean hasMoreElements() {
        if(currentObject > 0 && currentObject <= it-2){
            return true;
        }else 
            return false;
        }

    @Override
    public Object nextElement() {
        if(hasMoreElements()){
            return currentObject;
        }else{
            throw new IndexOutOfBoundsException("TraitType only has " + it + " objects.");
        }
    }
}
