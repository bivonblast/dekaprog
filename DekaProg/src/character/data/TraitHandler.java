/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package character.data;

import java.util.Vector;

/**
 *
 * @author Jonas Frogvall
 */
public abstract class TraitHandler<E extends Trait> extends Vector<E> {
    protected TraitHandler() {
        super();
    }

    public E getTrait(String traitName) {
        for (E e : this) {
            if (e.getName().equalsIgnoreCase(traitName)) return e;
        }
        return null;
    }

    public Vector<E> getTraits() {
        return this;
    }
}
