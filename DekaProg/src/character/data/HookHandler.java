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
    }

   public boolean addHookTrait(String hookName, HookType hookType) {
        HookTrait hook = new HookTrait(hookName, hookType);
        for (HookTrait ht : this) {
            if (ht.equals(hook)) return false;
        }
        add(hook);
        return true;
    }

    public boolean addHookTrait(HookTrait hook) {
        for (HookTrait ht : this) {
            if (ht.equals(hook)) return false;
        }
        add(hook);
        return true;
    }


    /**
     * Removes the {@code PointTrait} with the specified name from the
     * {@code PointHandler}, given that it exists.
     * @param traitName The name of the {@code PointTrait} to be removed.
     * @return {@code true} if the {@code PointTrait} was succesfully removed,
     * {@code false} if it didn't exist.
     */
        public boolean removeHookTrait(String traitName) {
            for (int i = 0; i < size(); i++) {
                if (get(i).getName().equalsIgnoreCase(traitName)) {
                    remove(i);
                    return true;
                }
            }
            return false;
        }

        public HookTrait getHook(String traitName) {
            for (HookTrait ht : this) {
                if (ht.getName().equalsIgnoreCase(traitName)) return ht;
            }
            return null;
        }

        public Vector<HookTrait> getIncentiveHooks() {
            Vector<HookTrait> ret = new Vector<HookTrait>();
            for (HookTrait ht : this) {
                if (ht.isIncentive()) ret.add(ht);
            }
            return ret;
        }

        public Vector<HookTrait> getAppearanceHooks() {
            Vector<HookTrait> ret = new Vector<HookTrait>();
            for (HookTrait ht : this) {
                if (ht.isAppearance()) ret.add(ht);
            }
            return ret;
        }

        public Vector<HookTrait> getWeaknessHooks() {
            Vector<HookTrait> ret = new Vector<HookTrait>();
            for (HookTrait ht : this) {
                if (ht.isWeakness()) ret.add(ht);
            }
            return ret;
        }

}
