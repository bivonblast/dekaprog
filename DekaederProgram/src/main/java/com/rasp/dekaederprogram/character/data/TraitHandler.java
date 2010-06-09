package com.rasp.dekaederprogram.character.data;

import java.util.Vector;

/**
 * Class for keeping track of the character traits.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */
public abstract class TraitHandler<E extends Trait> extends Vector<E> {

    /**
     * Returns a generic {@code Trait} represented by the specific name, stored
     * in this handler.
     * @param traitName The name of the desired Trait.
     * @return The {@code Trait} with the specified name.
     */
    public E getTrait(String traitName) {
        for (E e : this) {
            if (e.getName().equalsIgnoreCase(traitName)) return e;
        }
        return null;
    }

    /**
     * Returns all the {@code Trait}s in this handler.
     * @return A Vector of generic {@code Trait}s, containing all the traits store in
     * this handler.
     */
    public Vector<E> getTraits() {
        return this;
    }
}
