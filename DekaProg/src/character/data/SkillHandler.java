/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package character.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Jonas
 */
public class SkillHandler extends TraitHandler<SkillTrait> {

    public SkillHandler() {
        super();
    }

    public SkillHandler(String[] physicalNames, String[] mentalNames,
            String [] socialNames) throws DuplicateSkillException {
        super();
        boolean error = false;
        for (int i = 0; i < physicalNames.length; i++)
            if (!addSkillTrait(physicalNames[i], SkillType.newPhysicalSkillType()))
                error = true;
        for (int i = 0; i < mentalNames.length; i++)
            if (!addSkillTrait(mentalNames[i], SkillType.newMentalSkillType()))
                error = true;
        for (int i = 0; i < socialNames.length; i++)
            if (!addSkillTrait(socialNames[i], SkillType.newSocialSkillType()))
                error = true;
        if (error) throw new DuplicateSkillException();
    }

    public SkillHandler(Collection<SkillTrait> c) throws DuplicateSkillException {
        super();
        boolean error = false;
        for (SkillTrait st : c) {
            if (!addSkillTrait(st)) error = true;
        }
        if (error) throw new DuplicateSkillException();
    }

    public boolean addSkillTrait(String skillName, SkillType skillType,
            int value, int bonusValue) {
        SkillTrait skill = new SkillTrait(skillName, skillType,
                value, bonusValue);
        for (SkillTrait st : this) {
            if (st.equals(skill)) return false;
        }
        add(skill);
        return true;
    }

    public boolean addSkillTrait(String skillName, SkillType skillType, int value) {
        SkillTrait skill = new SkillTrait(skillName, skillType, value);
        for (SkillTrait st : this) {
            if (st.equals(skill)) return false;
        }
        add(skill);
        return true;
    }

    public boolean addSkillTrait(String skillName, SkillType skillType) {
        SkillTrait skill = new SkillTrait(skillName, skillType);
        for (SkillTrait st : this) {
            if (st.equals(skill)) return false;
        }
        add(skill);
        return true;
    }

    public boolean addSkillTrait(SkillTrait skill) {
        for (SkillTrait st : this) {
            if (st.equals(skill)) return false;
        }
        add(skill);
        return true;
    }


    /**
     * Removes the {@code PointTrait} with the specified name from the
     * {@code PointHandler}, given that it exists.
     * @param traitName The name of the {@code PointTrait} to be removed.
     * @return {@code true} if the {@code PointTrait} was succesfully removed,
     * {@code false} if it didn't exist.
     */
        public boolean removeSkillTrait(String traitName) {
            for (int i = 0; i < size(); i++) {
                if (get(i).getName().equalsIgnoreCase(traitName)) {
                    remove(i);
                    return true;
                }
            }
            return false;
        }

    /**
     * Sets a value to the {@code PointTrait} with the specified name, given
     * that it exists.
     * @param traitName The name of the {@code PointTrait} to be altered.
     * @param traitValue The new value of the {@code PointTrait}.
     * @return @return {@code true} if the {@code PointTrait} was succesfully
     * altered, {@code false} if it didn't exist.
     */
	public boolean setSkillTraitValue(String traitName, int traitValue) {
            for (SkillTrait st : this) {
                if (st.getName().equalsIgnoreCase(traitName)) {
                    st.setValue(traitValue);
                    return true;
                }
            }
            return false;
        }

        public boolean setSkillTraitBonusValue(String traitName, int bonusValue) {
            for (SkillTrait st : this) {
                if (st.getName().equalsIgnoreCase(traitName)) {
                    st.setBonusValue(bonusValue);
                    return true;
                }
            }
            return false;
        }

    /**
     * Adds a value to the existing value of {@code PointTrait} with the
     * specified name, given that it exists.
     * @param traitName The name of the {@code PointTrait} to be altered.
     * @param traitValue The value to add to the value of the {@code PointTrait}.
     * @return @return {@code true} if the {@code PointTrait} was succesfully
     * altered, {@code false} if it didn't exist.
     */
	public boolean addToSkillTraitValue(String traitName, int traitValue) {
            for (SkillTrait st : this) {
                if (st.getName().equalsIgnoreCase(traitName)) {
                    st.setValue(st.getValue().intValue() + traitValue);
                    return true;
                }
            }
            return false;
        }

        public boolean addToSkillTraitBonusValue(String traitName, int bonusValue) {
            for (SkillTrait st : this) {
                if (st.getName().equalsIgnoreCase(traitName)) {
                    st.setBonusValue(st.getBonusValue() + bonusValue);
                    return true;
                }
            }
            return false;
        }

        public SkillTrait getSkill(String traitName) {
            for (SkillTrait st : this) {
                if (st.getName().equalsIgnoreCase(traitName)) return st;
            }
            return null;
        }

        public Vector<SkillTrait> getPhysicalSkills() {
            Vector<SkillTrait> ret = new Vector<SkillTrait>();
            for (SkillTrait st : this) {
                if (st.isPhysical()) ret.add(st);
            }
            return ret;
        }

        public Vector<SkillTrait> getMentalSkills() {
            Vector<SkillTrait> ret = new Vector<SkillTrait>();
            for (SkillTrait st : this) {
                if (st.isMental()) ret.add(st);
            }
            return ret;
        }

        public Vector<SkillTrait> getSocialSkills() {
            Vector<SkillTrait> ret = new Vector<SkillTrait>();
            for (SkillTrait st : this) {
                if (st.isSocial()) ret.add(st);
            }
            return ret;
        }

}
