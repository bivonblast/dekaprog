package character.data;

/**
 * Class for skill representation.
 * 
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class Speciality extends Trait implements Comparable<Speciality> {
	//variabler
	private Skill skill;

	/**
     * Initiates a new {@code Speciality} with the specified name and parent skill, and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code Speciality}.
	 *
	 * @param  skill	The {@code Skill} that is the parent of this {@code Specialty}.
     */
	public Speciality(String name, Skill skill) {
		super(name);
		this.skill = skill;
		skill.addSpeciality(this);
	}


	/**
     * Initiates a new {@code Speciality} with the specified name, value and parent skill.
	 *
	 * @param  name		The name of the {@code Speciality}.
	 *
	 * @param  value	The value of the {@code Speciality}.
	 *
	 * @param  skill	The {@code Skill} that is the parent of this {@code Specialty}.
     */
	public Speciality(String name, int value, Skill skill) {
		super(name, value);
		this.skill = skill;
		skill.addSpeciality(this);
	}

	/**
     * Returns the parent skill of this {@code Speciality}.
	 *
	 * @return the parent {@code Skill} of this {@code Speciality}.
     */
	public Skill getSkill() {
		return skill;
	}

	/**
     * Changes parent skill of this {@code Speciality}. The function will
	 * automatically inform its old parent that it is no longer a member
	 * of its collection of specialities, and inform its new parent that
	 * it now belong to its.
     *
     * @param skill		The new parent{@code Skill} of this {@code Speciality}.
     */
	public void changeSkill(Skill skill) {
		this.skill.removeSpeciality(this);
		this.skill = skill;
		this.skill.addSpeciality(this);
	}

	/**
     * Compares this {@code Speciality} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code Speciality} against.
     *
     * @return  {@code true} if the given object represents a {@code Speciality}
     *          equivalent to this {@code Speciality}, {@code false} otherwise.
     */
	public boolean equals(Object anObject) {
		return getName().equals(((Speciality)anObject).getName());
	} 

	/**
	 * Compares two specialities lexicographically.
	 *
	 * @param	spec	The {@code Speciality} to be compared.
	 *
	 * @return	the value {@code 0} if the argument {@code Speciality}
	 *			has the same name as this {@code Speciality}; a value
	 *			less than {@code 0} if the name of the argument 
	 *			{@code Speciality} is lexicographically less than the
	 *			name of this {@code Speciality}; a value greater than
	 *			{@code 0} if the name of the argument {@code Speciality}
	 *			is lexicographically greater than the name of this
	 *			{@code Speciality}.
	 */

	public int compareTo(Speciality spec) {
		return getName().compareTo(spec.getName());
	}
}
