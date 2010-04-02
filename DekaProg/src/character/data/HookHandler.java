/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package character.data;

import java.util.Vector;

/**
 *
 * @author Jonas
 */
public class HookHandler extends TraitHandler<HookTrait> {

    public HookHandler() {
        super();
        add(new HookTrait("Drivkraft"));
        add(new HookTrait("Uppträdande"));
        add(new HookTrait("Svaghet"));
    }

    public HookHandler(String incentiveValue, String behaviourValue,
            String weaknessValue) {
        super();
        add(new HookTrait("Drivkraft", incentiveValue));
        add(new HookTrait("Uppträdande", behaviourValue));
        add(new HookTrait("Svaghet", weaknessValue));
    }

    public void setIncetiveValue(String incentiveValue) {
        for (HookTrait ht : this) {
            if (ht.getName().equals("Drivkraft")) ht.setValue(incentiveValue);
        }
    }

    public void setBehaviourValue(String behaviourValue) {
        for (HookTrait ht : this) {
            if (ht.getName().equals("Uppträdande")) ht.setValue(behaviourValue);
        }
    }

    public void setWeaknessalue(String weaknessValue) {
        for (HookTrait ht : this) {
            if (ht.getName().equals("Svaghet")) ht.setValue(weaknessValue);
        }
    }
}
