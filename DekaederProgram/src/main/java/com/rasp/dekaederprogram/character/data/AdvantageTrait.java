/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.character.data;

/**
 *
 * @author Jonas
 */
public class AdvantageTrait extends Trait<Integer> {
    private String description;
    
    public AdvantageTrait(String name) {
        super(name, 0);
        description = "";
    }
    
    public AdvantageTrait(String name, int value) {
        super(name, value);
        description = "";
    }

    public AdvantageTrait(String name, String description) {
        super(name, 0);
        this.description = description;
    }

    public AdvantageTrait(String name, int value, String description) {
        super(name, value);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String valueToString() {
        return "" + getValue();
    }
}
