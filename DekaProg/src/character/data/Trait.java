package character.data;

/**
 * General stat representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class Trait {
	//variabler
	private String	name;
	private int		value;

	/**
     * Initiates a new {@code Trait} with no name and the value {@code 0}.
     */
	public Trait() {
		this.name = "";
		this.value = 0;
	}

	/**
     * Initiates a new {@code Trait} with the specified name and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code Trait}.
     */
	public Trait(String name) {
		this.name = name;
		this.value = 0;
	}

	/**
     * Initiates a new {@code Trait} with no name and the specified value.
	 *
	 * @param  value	The value of the {@code Trait}.
     */
	public Trait(int value) {
		this.name = "";
		this.value = value;
	}

	/**
     * Initiates a new {@code Trait} with the specified name and the specified value.
	 *
	 * @param  name		The name of the {@code Trait}.
	 *
	 * @param  value	The value of the {@code Trait}.
     */
	public Trait(String name, int value) {
		this.name = name;
		this.value = value;
	}

	/**
     * Returns the name of this {@code Trait}.
	 *
	 * @return the name of this {@code Trait}.
     */
	public String getName() {
		return name;
	}

	/**
     * Returns the value of this {@code Trait}.
	 *
	 * @return the value of this {@code Trait}.
     */
	public int getValue() {
		return value;
	}

	/**
     * Sets the name of this {@code Trait}.
     *
     * @param name		The name to be set to this {@code Trait}.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Sets the value of this {@code Trait}.
     *
     * @param value		The value to be set to this {@code Trait}.
     */
	public void setValue(int value) {
		this.value = value;
	}
}
