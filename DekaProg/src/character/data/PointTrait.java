package character.data;

/**
 * Class for comparing different point types.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class PointTrait extends Trait implements Comparable<PointTrait> {

	/**
     * Initiates a new {@code PointTrait} with the specified name and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code PointTrait}.
     */
	public PointTrait(String name) {
		super(name);
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
     * Compares this {@code PointTrait} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code PointTrait} against.
     *
     * @return  {@code true} if the given object represents a {@code PointTrait}
     *          equivalent to this {@code PointTrait}; {@code false} otherwise.
     */
	public boolean equals(Object anObject) {
		return getName().equals(((PointTrait)anObject).getName());
	}

	/**
	 * Compares two point types by name, lexicographically.
	 *
	 * @param	pointTrait	The {@code PointTrait} to be compared.
	 *
	 * @return	the value 0 if the argument's name is equal to this string;
	 * a value less than 0 if this trait's name is lexicographically less than
	 * the argument's name; and a value greater than 0 if this trait's name is
	 * lexicographically greater than the argument's name.
	 */

	public int compareTo(PointTrait pointTrait) {
		 return getName().compareTo(pointTrait.getName());
	}
}