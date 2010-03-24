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

public class SkillTrait extends Trait implements Comparable<SkillTrait> {
	//variabler
	private SkillType type;
	private int bonusValue;
	private Vector<SpecialityTrait> specialities;

	/**
     * Initiates a new {@code SkillTrait} with the specified name and type, and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code SkillTrait}.
	 *
	 * @param  type		The type of the {@code SkillTrait}.
     */
	public SkillTrait(String name, SkillType type) {
		super(name);
		this.type = type;
		this.bonusValue = 0;
		specialities = new Vector<SpecialityTrait>();
	}

	/**
     * Initiates a new {@code SkillTrait} with the specified name, value and type.
	 *
	 * @param  name		The name of the {@code SkillTrait}.
	 *
	 * @param  value	The value of the {@code SkillTrait}.
	 *
	 * @param  type		The type of the {@code SkillTrait}.
     */
	public SkillTrait(String name, int value, SkillType type) {
		super(name, value);
		this.type = type;
		this.bonusValue = 0;
		specialities = new Vector<SpecialityTrait>();
	}

	/**
     * Returns the skill type of this {@code SkillTrait}.
	 *
	 * @return the {@code SkillType} of this {@code SkillTrait}.
     */
	public SkillType getSkillType() {
		return type;
	}

	/**
     * Sets the skill type of this {@code SkillTrait}.
     *
     * @param type		The {@code SkillType} to be set to this {@code SkillTrait}.
     */
	public void setSkillType(SkillType type) {
		this.type = type;
	}

	/**
     * Returns the bonus value of this {@code SkillTrait}.
	 *
	 * @return the bonus value of this {@code SkillTrait}.
     */
	public int getBonusValue() {
		return bonusValue;
	}

	/**
     * Sets the bonus value of this {@code SkillTrait}.
     *
     * @param bonusValue	The bonus value to be set to this{@code SkillTrait}.
     */
	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}

	/**
     * Adds to the current bonus value of this {@code SkillTrait}.
     *
     * @param bonusValue	The value to be added to this {@code SkillTrait}.
	 *
	 * @return the bonus value of this {@code SkillTrait}, after it has been updated.
     */
	public int addBonusValue(int bonusValue) {
		return (this.bonusValue += bonusValue);
	}

	/**
     * Substracts from the current bonus value of this {@code SkillTrait}.
     *
     * @param bonusValue	The value to be substracted from this {@code SkillTrait}.
	 *
	 * @return the bonus value of this {@code SkillTrait}, after it has been updated.
     */
	public int substractBonusValue(int bonusValue) {
		return (this.bonusValue -= bonusValue);
	}


	/**
     * Adds a speciality to this {@code SkillTrait}, unless it already exists. Also sorts the
	 * {@code Vector} of {@code SpecialityTrait}, according to the <i>natural ordering</i>, defined
	 * by {@link SpecialityTrait#compareTo(SpecialityTrait)}
     *
     * @param spec	The {@code SpecialityTrait} to be added.
	 *
	 * @return {@code true} if the element was added, {@code false} if it already existed.
     */

	public boolean addSpeciality(SpecialityTrait spec) {
		if (specialities.contains(spec)) return false;
		specialities.add(spec);
		Collections.sort(specialities);
		return true;
	}

	/**
     * Removes a speciality to this {@code SkillTrait}, or does nothing if it doesn't exists
	 * in its collection of specialities.
     *
     * @param spec	The {@code SpecialityTrait} to be removed.
	 *
	 * @return {@code true} if the element was removed, {@code false} if it didn't exist.
     */

	public boolean removeSpeciality(SpecialityTrait spec) {
		return specialities.remove(spec);
	}


	/**
     * Returns the {@code Vector} of {@code SpecialityTrait} of this {@code SkillTrait}.
	 *
	 * @return the {@code Vector<SpecialityTrait>} of this {@code SkillTrait}.
     */
	public Vector<SpecialityTrait> getSpecialities() {
		return specialities;
	}

	/**
     * Compares this {@code SkillTrait} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code SkillTrait} against.
     *
     * @return  {@code true} if the given object represents a {@code SkillTrait}
     *          equivalent to this {@code SkillTrait}, {@code false} otherwise.
     */
	public boolean equals(Object anObject) {
		SkillTrait skillTrait = (SkillTrait)anObject;
		return type.equals(skillTrait.getSkillType()) && getName().equals(skillTrait.getName());
	}

	/**
	 * Compares two skills.
	 * First the types of the skills will be compared, and if they are the same,
	 * the names of the skills will be compared lexicographically.
	 *
	 * @param	skillTrait	The {@code SkillTrait} to be compared.
	 *
	 * @return	the value {@code 0} if the argument {@code SkillTrait} has the same
	 *			{@code SkillType} and name as this {@code SkillTrait}; a value less than {@code 0}
	 *			if this {@code SkillTrait} has a {@code SkillType} of higher priority than the
	 *			{@code SkillType} of the argument {@code SkillTrait}, or if the priorities are the
	 *			same and the name of this {@code SkillTrait} is lexicographically less than the
	 *			name of the argument {@code SkillTrait}; and a value greater than {@code 0} if
	 *			this {@code SkillTrait} has a {@code SkillType} of lower priority than the
	 *			{@code SkillType} of the argument {@code SkillTrait}, or if the priorities are the
	 *			same and the name of this {@code SkillTrait} is lexicographically greater than the
	 *			name of the argument {@code SkillTrait}.
	 *
	 * @see		SkillType#compareTo(SkillType)
	 */

	public int compareTo(SkillTrait skillTrait) {
		if (type.equals(skillTrait.getSkillType())) return getName().compareTo(skillTrait.getName());
		return type.compareTo(skillTrait.getSkillType());
	}
}
