/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.character.data;

/**
 *
 * @author Jonas
 */
public class SpecialityHandler extends TraitHandler<SpecialityTrait> {

    public SpecialityHandler() {
        super();
    }

    public boolean addSpecialityTrait(String traitName, SkillTrait skill,
            int traitValue) {
        SpecialityTrait spec = new SpecialityTrait(traitName, skill, traitValue);
        for (SpecialityTrait st : this) {
            if (st.equals(spec)) return false;
        }
        add(spec);
        return true;
    }

    public boolean addSpecialityTrait(String traitName, SkillTrait skill) {
        SpecialityTrait spec = new SpecialityTrait(traitName, skill);
        for (SpecialityTrait st : this) {
            if (st.equals(spec)) return false;
        }
        add(spec);
        return true;
    }
    
    public boolean removeSpecialityTrait(String traitName, SkillTrait skill) {
        for (int i = 0; i < size(); i++) {
            SpecialityTrait spec = get(i);
            if ((spec.getName().equalsIgnoreCase(traitName))
            && (spec.getSkill().getName().equalsIgnoreCase(skill.getName()))){
                remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean setSpecialityTraitValue(String traitName, SkillTrait skill,
            int traitValue) {
        SpecialityTrait spec = new SpecialityTrait(traitName, skill);
        for (SpecialityTrait st : this) {
            if (st.equals(spec)) {
                st.setValue(traitValue);
                return true;
            }
        }
        return false;
    }

    public boolean addToSpecialityTraitValue(String traitName, SkillTrait skill,
            int traitValue) {
        SpecialityTrait spec = new SpecialityTrait(traitName, skill);
        for (SpecialityTrait st : this) {
            if (st.equals(spec)) {
                st.setValue(st.getValue().intValue() + traitValue);
                return true;
            }
        }
        return false;
    }

    public boolean changeSpecialityTraitParentSkill(String traitName,
            SkillTrait oldSkill, SkillTrait newSkill) {
        SpecialityTrait spec = new SpecialityTrait(traitName, oldSkill);
        for (SpecialityTrait st : this) {
            if (st.equals(spec)) {
                st.changeSkill(newSkill);
                return true;
            }
        }
        return false;
    }
}
