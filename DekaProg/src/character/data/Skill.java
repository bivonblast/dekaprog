package character.data;

import java.util.Vector;
import java.util.Collections;

/**
 * Class for skill representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class Skill extends Trait implements Comparable<Skill> {
	//variabler
	private SkillType type;
	private int bonusValue;
	private Vector<Speciality> specialities;

	/**
     * Initiates a new {@code Skill} with the specified name and type, and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code Skill}.
	 *
	 * @param  type		The type of the {@code Skill}.
     */
	public Skill(String name, SkillType type) {
		super(name);
		this.type = type;
		this.bonusValue = 0;
		specialities = new Vector<Speciality>();
	}

	/**
     * Initiates a new {@code Skill} with the specified name, value and type.
	 *
	 * @param  name		The name of the {@code Skill}.
	 *
	 * @param  value	The value of the {@code Skill}.
	 *
	 * @param  type		The type of the {@code Skill}.
     */
	public Skill(String name, int value, SkillType type) {
		super(name, value);
		this.type = type;
		this.bonusValue = 0;
		specialities = new Vector<Speciality>();
	}

	/**
     * Returns the skill type of this {@code Skill}.
	 *
	 * @return the {@code SkillType} of this {@code Skill}.
     */
	public SkillType getSkillType() {
		return type;
	}

	/**
     * Sets the skill type of this {@code Skill}.
     *
     * @param type		The {@code SkillType} to be set to this {@code Skill}.
     */
	public void setSkillType(SkillType type) {
		this.type = type;
	}

	/**
     * Returns the bonus value of this {@code Skill}.
	 *
	 * @return the bonus value of this {@code Skill}.
     */
	public int getBonusValue() {
		return bonusValue;
	}

	/**
     * Sets the bonus value of this {@code Skill}.
     *
     * @param bonusValue	The bonus value to be set to this{@code Skill}.
     */
	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}

	/**
     * Adds to the current bonus value of this {@code Skill}.
     *
     * @param bonusValue	The value to be added to this {@code Skill}.
	 *
	 * @return the bonus value of this {@code Skill}, after it has been updated.
     */
	public int addBonusValue(int bonusValue) {
		return (this.bonusValue += bonusValue);
	}

	/**
     * Substracts from the current bonus value of this {@code Skill}.
     *
     * @param bonusValue	The value to be substracted from this {@code Skill}.
	 *
	 * @return the bonus value of this {@code Skill}, after it has been updated.
     */
	public int substractBonusValue(int bonusValue) {
		return (this.bonusValue -= bonusValue);
	}


	/**
     * Adds a speciality to this {@code Skill}, unless it already exists. Also sorts the
	 * {@code Vector} of {@code Speciality}, according to the <i>natural ordering</i>, defined
	 * by {@link Speciality#compareTo(Speciality)}
     *
     * @param spec	The {@code Speciality} to be added.
	 *
	 * @return {@code true} if the element was added, {@code false} if it already existed.
     */

	public boolean addSpeciality(Speciality spec) {
		if (specialities.contains(spec)) return false;
		specialities.add(spec);
		Collections.sort(specialities);
		return true;
	}

	/**
     * Removes a speciality to this {@code Skill}, or does nothing if it doesn't exists
	 * in its collection of specialities.
     *
     * @param spec	The {@code Speciality} to be removed.
	 *
	 * @return {@code true} if the element was removed, {@code false} if it didn't exist.
     */

	public boolean removeSpeciality(Speciality spec) {
		return specialities.remove(spec);
	}


	/**
     * Returns the {@code Vector} of {@code Speciality} of this {@code Skill}.
	 *
	 * @return the {@code Vector<Speciality>} of this {@code Skill}.
     */
	public Vector<Speciality> getSpecialities() {
		return specialities;
	}

	/**
     * Compares this {@code Skill} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code Skill} against.
     *
     * @return  {@code true} if the given object represents a {@code Skill}
     *          equivalent to this {@code Skill}, {@code false} otherwise.
     */
	public boolean equals(Object anObject) {
		Skill skill = (Skill)anObject;
		return type.equals(skill.getSkillType()) && getName().equals(skill.getName());
	}

	/**
	 * Compares two skills.
	 * First the types of the skills will be compared, and if they are the same,
	 * the names of the skills will be compared lexicographically.
	 *
	 * @param	skill	The {@code Skill} to be compared.
	 *
	 * @return	the value {@code 0} if the argument {@code Skill} has the same
	 *			{@code SkillType} and name as this {@code Skill}; a value less than {@code 0}
	 *			if this {@code Skill} has a {@code SkillType} of higher priority than the
	 *			{@code SkillType} of the argument {@code Skill}, or if the priorities are the
	 *			same and the name of this {@code Skill} is lexicographically less than the
	 *			name of the argument {@code Skill}; and a value greater than {@code 0} if
	 *			this {@code Skill} has a {@code SkillType} of lower priority than the
	 *			{@code SkillType} of the argument {@code Skill}, or if the priorities are the
	 *			same and the name of this {@code Skill} is lexicographically greater than the
	 *			name of the argument {@code Skill}.
	 *
	 * @see		SkillType#compareTo(SkillType)
	 */

	public int compareTo(Skill skill) {
		if (type.equals(skill.getSkillType())) return getName().compareTo(skill.getName());
		return type.compareTo(skill.getSkillType());
	}
}
