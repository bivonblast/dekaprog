package character.data;

/**
 * Class for keeping track on points.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class PointHandler extends TraitHandler<PointTrait> {
	//variabler
	private int start;
	private int experience;
	private int spent;
	private int veteran;

	/**
     * Initiates a new {@code PointHandler} with the specified number of starting points.
	 *
	 * @param  start	The number of starting points for the character.
     */
	public PointHandler(int start) {
            this.start = start;
            experience = 0;
            spent = 0;
            veteran = 0;
	}

	/**
     * Initiates a new {@code PointHandler} with the specified number of starting 
         * points and experience.
	 *
	 * Veteran points will automatically be calculated.
	 *
	 * @param  start	The number of starting points for the character.
	 *
	 * @param  experience The current number of experience points the character has.
     */
	public PointHandler(int start, int experience) {
            this.start = start;
            this.experience = experience;
            spent = 0;
            updateVeteran();
	}

	/**
	 * Adds experience to the character.
	 *
	 * Veteran points will recalculated.
	 *
	 * @param experience	The number of experience points to add.
	 */
	public void addExperiencePoints(int experience) {
		this.experience += experience;
		updateVeteran();
	}

	/**
	 * Adds an additional point trait for the character, with the specified value.
	 *
	 * @param name	The name of the points.
	 *
	 * @param value The number of points the character has.
	 *
	 * @return {@code true} if the type was added, {@code false} if it already existed.
	 */
	public boolean addPointTrait(String traitName, int traitValue) {
            for (PointTrait pt : this) {
                if (pt.getName().equalsIgnoreCase(traitName)) return false;
            }
            add(new PointTrait(traitName, traitValue));
            return true;
        }

        public boolean removePointTrait(String traitName) {
            for (int i = 0; i < size(); i++) {
                if (get(i).getName().equalsIgnoreCase(traitName)) {
                    remove(i);
                    return true;
                }
            }
            return false;
        }

	/**
     * Sets a value to the {@code PointTrait} with the specified name.
     *
     * @param name	The name of the point type.
	 *
	 * @param value	The value to be set to the point type.
	 *
	 * @return	{@code true} if the {@code PointTrait} existed; {@code false} otherwise.
     */
	public boolean setConceptTraitValue(String traitName, int traitValue) {
            for (PointTrait pt : this) {
                if (pt.getName().equalsIgnoreCase(traitName)) {
                    pt.setValue(traitValue);
                    return true;
                }
            }
            return false;
        }

	/**
     * Adds a value to the {@code PointTrait} with the specified name.
     *
     * @param name	The name of the point type.
	 *
	 * @param value	The value to be added to the point type's current value.
	 *
	 * @return	{@code true} if the {@code PointTrait} existed; {@code false} otherwise.
     */
	public boolean addToConceptTraitValue(String traitName, int traitValue) {
            for (PointTrait pt : this) {
                if (pt.getName().equalsIgnoreCase(traitName)) {
                    pt.setValue(pt.getValue().intValue() + traitValue);
                    return true;
                }
            }
            return false;
        }

	/**
     * Returns the number of starting points assigned to the character.
	 *
	 * @return the number of starting points assigned to the character.
     */
	public int getStartingPoints() {
		return start;
	}

	/**
     * Returns the number of current experience points the character has.
	 *
	 * @return the number of current experience points the character has.
     */
	public int getExperiencePoints() {
		return experience;
	}

	/**
     * Returns the number of spent experience points the character has.
	 *
	 * @return the number of spent experience points the character has.
     */
	public int getSpentExperiencePoints() {
		return spent;
	}

	/**
     * Returns the number of veterean points the character has.
	 *
	 * @return the number of veteran points the character has.
     */
	public int getVeteranPoints() {
		return veteran;
	}

	/**
     * Sets the number of starting points for the character.
	 *
	 * Veteran points will be recalculated.
     *
     * @param start	The number of starting points for the character.
     */
	public void setStartingPoints(int start) {
		this.start = start;
		updateVeteran();
	}

	/**
     * Sets the number of current experience points the character has.
	 *
	 * Veteran points will be recalculated.
     *
     * @param experience	The number of current experience points the character has.
     */
	public void setExperiencePoints(int experience) {
		this.experience = experience;
		updateVeteran();
	}

	/**
     * Sets the number of spent experience points the character has.
	 *
	 * Veteran points will be recalculated.
     *
     * @param spent	The number of spent experience points the character has.
     */
	public void setSpentExperiencePoints(int spent) {
		this.spent = spent;
	}

	/**
     * Spend experience points for the character.
	 *
	 * When spending experience points for the character, the current experience points
	 * will be lowered while the spent experience points will be increased.
	 * In case there are any points used for increasing a stat of some kind, that
	 * is not payed for by the player ("Villkorliga poäng" or "Nischpoäng" for example),
	 * those extra points should be represented by the second parameter.
	 *
	 * Veteran points will be recalculated.
     *
     * @param experience	The number of the experience points the character spend.
	 *
	 * @param freeExperience The number of experience points gained in other ways.
     */
	public void spendExperiencePoints(int experience, int freeExperience) {
		this.experience -= experience;
		spent += experience + freeExperience;
		updateVeteran();
	}

        /*private void calculateSpentPoints() {
            //dosomethinglater
        }*/

	private void updateVeteran() {
            veteran = experience + spent - start;
	}
}