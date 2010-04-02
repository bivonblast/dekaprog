package character.data;

/**
 * Class for determing hook type.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class HookType implements Comparable<HookType> {
	//variabler
	/**
	* The {@code int} representing a incentive hook.
	*/
	private static final int INCENTIVE	= 1010;

	/**
	* The {@code int} representing a weakness hook.
	*/
	private static final int APPEARANCE		= 1011;

	/**
	* The {@code int} representing a appearance hook.
	*/
	private static final int WEAKNESS		= 1012;

	private int type;
        private static HookType incentive, appearance, weakness;

	/**
     * Initiates a new {@code HookType} with the specified type.
	 *
	 * @param  type		The type that this {@code HookType} represents.
     */
	private HookType(int type) {
		this.type = type;
	}


	/**
	 * Returns an incentive {@code HookType} singleton.
	 *
	 * @return  an incentive {@code HookType} singleton.
	 */
	public static synchronized HookType newIncentiveHookType() {
            if (incentive == null) {
		incentive = new HookType(INCENTIVE);
            }
            return incentive;
	}

	/**
	 * Returns a weakness {@code HookType} singleton.
	 *
	 * @return  a weakness {@code HookType} singleton.
	 */
	public static synchronized HookType newWeaknessHookType() {
            if (weakness == null) {
		weakness = new HookType(WEAKNESS);
            }
            return weakness;
	}

	/**
	 * Returns an appearance {@code HookType} singleton.
	 *
	 * @return  an appearance {@code HookType} singleton.
	 */
	public static synchronized HookType newAppearanceHookType() {
            if (appearance == null) {
		appearance = new HookType(APPEARANCE);
            }
            return appearance;
	}

	/**
     * Returns the type of this {@code HookType}.
	 *
	 * @return the type of this {@code HookType}.
     */
	public int getType() {
		return type;
	}

	/**
     * Checks whether this {@code HookType} is an incentive or not.
	 *
	 * @return {@code true} if this {@code HookType} represents an incentive hook, {@code false} otherwise.
     */
	protected boolean isIncentive() {
		return type == INCENTIVE;
	}

	/**
     * Checks whether this {@code HookType} is a weakness or not.
	 *
	 * @return {@code true} if this {@code HookType} represents a weakness hook, {@code false} otherwise.
     */
	protected boolean isWeakness() {
		return type == WEAKNESS;
	}

	/**
     * Checks whether this {@code HookType} is an appearance or not.
	 *
	 * @return {@code true} if this {@code HookType} represents an appearance hook, {@code false} otherwise.
     */
	protected boolean isAppearance() {
		return type == APPEARANCE;
	}

        /**
         * Overrides {@code Object.clone()} to prevent cloning.
         * @return Nothing. This method will throw an CloneNotSupportedException
         * if anyone tries to clone it.
         * @throws CloneNotSupportedException Always.
         * @see Object#clone()
         */
    @Override
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

	/**
     * Compares this {@code HookType} to the specified object.
	 *
	 * @param  anObject
     *         The object to compare this {@code HookType} against.
     *
     * @return  {@code true} if the given object represents a {@code HookType}
     *          equivalent to this {@code HookType}, {@code false} otherwise.

     */
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject instanceof HookType)
            return type == ((HookType)anObject).getType();
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.type;
        return hash;
    }

	/**
	 * Compares two hook types.
	 * <p>
	 * Priority: Incentive > Appearance > Weakness.
	 *
	 * @param	hookType	The {@code HookType} to be compared.
	 *
	 * @return	the value {@code 0} if the argument {@code HookType} equals this
	 *			{@code HookType}; a value less than {@code 0} if this {@code HookType}
	 *			has higher priority than the argument {@code HookType}; a value greater
	 *			than {@code 0} if this {@code HookType} has a lower priority than the
	 *			argument {@code HookType}.
	 */

	public int compareTo(HookType hookType) {
		return type - hookType.getType();
	}
}
