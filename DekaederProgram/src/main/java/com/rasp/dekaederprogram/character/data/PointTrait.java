package com.rasp.dekaederprogram.character.data;

/**
 * Class for comparing different point types.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class PointTrait extends Trait<Integer> {

	/**
     * Initiates a new {@code PointTrait} with the specified name and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code PointTrait}.
     */
	public PointTrait(String name) {
            super(name, 0);
	}

	/**
     * Initiates a new {@code PointTrait} with the specified name and the specified value.
	 *
	 * @param  name		The name of the {@code PointTrait}.
	 *
	 * @param  value	The value of the {@code PointTrait}.
     */
	public PointTrait(String name, int value) {
            super(name, value);
	}

    /**
     * Returns the string representation of this trait's value.
     * @return the value as a String.
     */
    @Override
        public String valueToString() {
            return getValue().toString();
        }
}