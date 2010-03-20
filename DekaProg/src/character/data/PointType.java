package character.data;

/**
 * Class for comparing different point types.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class PointType extends Trait implements Comparable<PointType> {

	/**
     * Initiates a new {@code PointType} with the specified name and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code PointType}.
     */
	public PointType(String name) {
		super(name);
	}

	/**
     * Initiates a new {@code PointType} with the specified name and the specified value.
	 *
	 * @param  name		The name of the {@code PointType}.
	 *
	 * @param  value	The value of the {@code PointType}.
     */
	public PointType(String name, int value) {
		super(name, value);
	}

	/**
     * Compares this {@code PointType} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code PointType} against.
     *
     * @return  {@code true} if the given object represents a {@code PointType}
     *          equivalent to this {@code PointType}; {@code false} otherwise.
     */
	public boolean equals(Object anObject) {
		return getName().equals(((PointType)anObject).getName());
	}

	/**
	 * Compares two point types by name, lexicographically.
	 *
	 * @param	pointType	The {@code PointType} to be compared.
	 *
	 * @return	the value 0 if the argument's name is equal to this string;
	 * a value less than 0 if this trait's name is lexicographically less than
	 * the argument's name; and a value greater than 0 if this trait's name is
	 * lexicographically greater than the argument's name.
	 */

	public int compareTo(PointType pointType) {
		 return getName().compareTo(pointType.getName());
	}
}