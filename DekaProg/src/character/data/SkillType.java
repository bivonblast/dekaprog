package character.data;

/**
 * Class for determing skill type.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class SkillType implements Comparable<SkillType> {
	//variabler
	/**
	* The {@code int} representing a physical skill.
	*/
	public static final int PHYSICAL	= 1000;

	/**
	* The {@code int} representing a social skill.
	*/
	public static final int MENTAL		= 1001;

	/**
	* The {@code int} representing a mental skill.
	*/
	public static final int SOCIAL		= 1002;

	private int type;

	/**
     * Initiates a new {@code SkillType} with the specified type.
	 *
	 * @param  type		The type that this {@code SkillType} represents.
     */
	private SkillType(int type) {
		this.type = type;
	}


	/**
	 * Returns a new instance of a physical {@code SkillType}.
	 *
	 * @return	a new instance of {@code SkillType}, with its type set to {@code PHYSICAL}.
	 */
	public SkillType newPhysicalSkillType() {
		return new SkillType(PHYSICAL);
	}

	/**
	 * Returns a new instance of a social {@code SkillType}.
	 *
	 * @return	a new instance of {@code SkillType}, with its type set to {@code SOCIAL}.
	 */
	public SkillType newSocialSkillType() {
		return new SkillType(SOCIAL);
	}

	/**
	 * Returns a new instance of a mental {@code SkillType}.
	 *
	 * @return	a new instance of {@code SkillType}, with its type set to {@code MENTAL}.
	 */
	public SkillType newMentalSkillType() {
		return new SkillType(MENTAL);
	}

	/**
     * Returns the type of this {@code SkillType}.
	 *
	 * @return the type of this {@code SkillType}.
     */
	public int getType() {
		return type;
	}

	/**
     * Checks whether this {@code SkillType} is physical or not.
	 *
	 * @return {@code true} if this {@code SkillType} represents a physical skill, {@code false} otherwise.
     */
	public boolean isPhysical() {
		return type == PHYSICAL;
	}

	/**
     * Checks whether this {@code SkillType} is social or not.
	 *
	 * @return {@code true} if this {@code SkillType} represents a social skill, {@code false} otherwise.
     */
	public boolean isSocial() {
		return type == SOCIAL;
	}

	/**
     * Checks whether this {@code SkillType} is mental or not.
	 *
	 * @return {@code true} if this {@code SkillType} represents a mental skill, {@code false} otherwise.
     */
	public boolean isMental() {
		return type == MENTAL;
	}

	/**
     * Compares this {@code SkillType} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code SkillType} against.
     *
     * @return  {@code true} if the given object represents a {@code SkillType}
     *          equivalent to this {@code SkillType}, {@code false} otherwise.

     */
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject instanceof SkillType)
            return type == ((SkillType)anObject).getType();
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.type;
        return hash;
    }

	/**
	 * Compares two skill types.
	 * <p>
	 * Priority: Physical > Mental > Social.
	 *
	 * @param	skillType	The {@code SkillType} to be compared.
	 *
	 * @return	the value {@code 0} if the argument {@code SkillType} equals this
	 *			{@code SkillType}; a value less than {@code 0} if this {@code SkillType}
	 *			has higher priority than the argument {@code SkillType}; a value greater
	 *			than {@code 0} if this {@code SkillType} has a lower priority than the
	 *			argument {@code SkillType}.
	 */

	public int compareTo(SkillType skillType) {
		return type - skillType.getType();
	}
}
