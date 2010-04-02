/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package character.data;

/**
 *
 * @author Jonas
 */
public class SkillValue {
    private int skillValue;
    private int bonusValue;

    public SkillValue() {
        skillValue = 0;
        bonusValue = 0;
    }

    public SkillValue(int skillValue) {
        this.skillValue = skillValue;
        bonusValue = 0;
    }

    public SkillValue(int skillValue, int bonusValue) {
        this.skillValue = skillValue;
        this.bonusValue = bonusValue;
    }

    public int getSkillValue() {
        return skillValue;
    }

    public int getBonusValue() {
        return bonusValue;
    }

    public void setSkillValue(int skillValue) {
        this.skillValue = skillValue;
    }

    public void setBonusValue(int bonusValue) {
        this.bonusValue = bonusValue;
    }
    
    public String valueToString() {
        if (bonusValue != 0)
            return skillValue + " + " +bonusValue;
        return "" + skillValue;
    }
}
