/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package character.data;

/**
 *
 * @author Jonas
 */
public class AdvantageHandler extends TraitHandler<AdvantageTrait> {

    public AdvantageHandler() {
        super();
    }
    
    public boolean addAdvantageTrait(String traitName, int traitValue,
            String traitDescription) {
        for (AdvantageTrait at : this) {
            if (at.getName().equalsIgnoreCase(traitName)) return false;
        }
        add(new AdvantageTrait(traitName, traitValue, traitDescription));
        return true;
    }

    public boolean addAdvantageTrait(String traitName, String traitDescription) {
        for (AdvantageTrait at : this) {
            if (at.getName().equalsIgnoreCase(traitName)) return false;
        }
        add(new AdvantageTrait(traitName, traitDescription));
        return true;
    }

    public boolean addAdvantageTrait(String traitName, int traitValue) {
        for (AdvantageTrait at : this) {
            if (at.getName().equalsIgnoreCase(traitName)) return false;
        }
        add(new AdvantageTrait(traitName, traitValue));
        return true;
    }

    public boolean addAdvantageTrait(String traitName) {
        for (AdvantageTrait at : this) {
            if (at.getName().equalsIgnoreCase(traitName)) return false;
        }
        add(new AdvantageTrait(traitName));
        return true;
    }

    public boolean removeAdvantageTrait(String traitName) {
        for (int i = 0; i < size(); i++) {
                if (get(i).getName().equalsIgnoreCase(traitName)) {
                    remove(i);
                    return true;
                }
            }
            return false;
    }

    public boolean setAdvantageTraitValue(String traitName, int traitValue) {
        for (AdvantageTrait at : this) {
            if (at.getName().equalsIgnoreCase(traitName)) {
                at.setValue(traitValue);
                return true;
            }
        }
        return false;
    }

    public boolean setAdvantageTraitDescription(String traitName,
            String traitDescription) {
        for (AdvantageTrait at : this) {
            if (at.getName().equalsIgnoreCase(traitName)) {
                at.setDescription(traitDescription);
                return true;
            }
        }
        return false;
    }
}
