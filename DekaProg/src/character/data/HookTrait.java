package character.data;

/**
 * Class for skill representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class HookTrait extends Trait implements Comparable<HookTrait> {
	//variabler
	private HookType type;

	/**
     * Initiates a new {@code SkillTrait} with the specified name and type, and the value {@code 0}.
	 *
	 * @param  name		The name of the {@code SkillTrait}.
	 *
	 * @param  type		The type of the {@code SkillTrait}.
     */
	public HookTrait(String name, HookType type) {
		super(name);
		this.type = type;
	}

	/**
     * Returns the skill type of this {@code SkillTrait}.
	 *
	 * @return the {@code SkillType} of this {@code SkillTrait}.
     */
	public HookType getHookType() {
		return type;
	}

	/**
     * Sets the skill type of this {@code SkillTrait}.
     *
     * @param type		The {@code SkillType} to be set to this {@code SkillTrait}.
     */
	public void setHookType(HookType type) {
		this.type = type;
	}


        public boolean isIncentive() {
            return type.isIncentive();
        }

        public boolean isAppearance() {
            return type.isAppearance();
        }

        public boolean isWeakness() {
            return type.isWeakness();
        }


    /**
     * Returns {@code null} since there is no value.
     * @return {@code null}.
     */
    @Override
        public String valueToString() {
            return null;
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
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject instanceof HookTrait) {
            HookTrait hookTrait = (HookTrait)anObject;
            return getName().equals(hookTrait.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (getName() != null ? getName().hashCode() : 0);
        return hash;
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

    @Override
    public int compareTo(HookTrait hookTrait) {
        if (type.equals(hookTrait.getHookType())) return getName().compareTo(hookTrait.getName());
	return type.compareTo(hookTrait.getHookType());
    }
}
