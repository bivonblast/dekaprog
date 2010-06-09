package com.rasp.dekaederprogram.character.data;

/**
 * Class for skill speciality representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class SpecialityTrait extends Trait<Integer> implements Comparable<SpecialityTrait> {
	//variabler
	private SkillTrait skillTrait;

	/**
     * Initiates a new {@code SpecialityTrait} with the specified name and parent {@code SkillTrait}, and the value {@code 0}.
	 * The {@code SpecialityTrait} will automatically be added to the {@code SkillTrait}.
	 *
	 * @param  name		The name of the {@code SpecialityTrait}.
	 *
	 * @param  skillTrait	The {@code SkillTrait} that is the parent of this {@code Specialty}.
     */
	public SpecialityTrait(String name, SkillTrait skillTrait) {
		super(name, 0);
		this.skillTrait = skillTrait;
		skillTrait.addSpeciality(this);
	}


	/**
     * Initiates a new {@code SpecialityTrait} with the specified name, value and parent {@code SkillTrait}.
	 * The {@code SpecialityTrait} will automatically be added to the {@code SkillTrait}.
	 *
	 * @param  name		The name of the {@code SpecialityTrait}.
	 *
	 * @param  skillTrait	The {@code SkillTrait} that is the parent of this {@code Specialty}.
         *
	 * @param  value	The value of the {@code SpecialityTrait}.
     */
	public SpecialityTrait(String name, SkillTrait skillTrait, int value) {
		super(name, value);
		this.skillTrait = skillTrait;
		skillTrait.addSpeciality(this);
	}

	/**
     * Returns the parent {@code SkillTrait} of this {@code SpecialityTrait}.
	 *
	 * @return the parent {@code SkillTrait} of this {@code SpecialityTrait}.
     */
	public SkillTrait getSkill() {
		return skillTrait;
	}

	/**
     * Changes parent {@code SkillTrait} of this {@code SpecialityTrait}. The function will
	 * automatically inform its old parent that it is no longer a member
	 * of its collection of specialities, and inform its new parent that
	 * it now belong to its.
     *
     * @param skillTrait		The new parent{@code SkillTrait} of this {@code SpecialityTrait}.
     */
	public void changeSkill(SkillTrait skillTrait) {
		this.skillTrait.removeSpeciality(this);
		this.skillTrait = skillTrait;
		this.skillTrait.addSpeciality(this);
	}

    @Override
        public String valueToString() {
            return getValue().toString();
        }

	/**
     * Compares this {@code SpecialityTrait} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code SpecialityTrait} against.
     *
     * @return  {@code true} if the given object represents a {@code SpecialityTrait}
     *          equivalent to this {@code SpecialityTrait}, {@code false} otherwise.
     */
    @Override
	public boolean equals(Object anObject) {
            if (this == anObject) return true;
            if (anObject instanceof SpecialityTrait)
		return ((getName().equals(((SpecialityTrait)anObject).getName()))
                        && (getSkill().equals(((SpecialityTrait)anObject).getSkill())));
            return false;
	}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (getName() != null ? getName().hashCode() : 0);
        return hash;
    }

	/**
	 * Compares two specialities lexicographically.
	 *
	 * @param	spec	The {@code SpecialityTrait} to be compared.
	 *
	 * @return	the value {@code 0} if the argument {@code SpecialityTrait}
	 *			has the same name as this {@code SpecialityTrait}; a value
	 *			less than {@code 0} if the name of the argument
	 *			{@code SpecialityTrait} is lexicographically less than the
	 *			name of this {@code SpecialityTrait}; a value greater than
	 *			{@code 0} if the name of the argument {@code SpecialityTrait}
	 *			is lexicographically greater than the name of this
	 *			{@code SpecialityTrait}.
	 */

	public int compareTo(SpecialityTrait spec) {
		return getName().compareTo(spec.getName());
	}
}
