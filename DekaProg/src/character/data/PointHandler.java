package character.data;

import java.util.Vector;

/**
 * Class for keeping track on points.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class PointHandler {
	//variabler
	private int start;
	private int karma;
	private int experience;
	private int spent;
	private int veteran;
	private int sessions;
	private int style;
	private Vector<PointTrait> other;

	/**
     * Initiates a new {@code PointHandler} with the specified number of starting points.
	 *
	 * @param  start	The number of starting points for the character.
     */
	public PointHandler(int start) {
		this.start = start;
		karma = 0;
		experience = 0;
		spent = 0;
		veteran = 0;
		sessions = 0;
		style = 0;
		other = new Vector<PointTrait>();
	}

	/**
     * Initiates a new {@code PointHandler} with the specified number of starting points, karma, current experience,
	 * spent experience, sessions played and style points.
	 *
	 * Veteran points will automatically be calculated.
	 *
	 * @param  start	The number of starting points for the character.
	 *
	 * @param  karma	The number of karma the character currently has.
	 *
	 * @param  experience The current number of experience points the character has.
	 *
	 * @param  spent	The number of experience points the character has already spent.
	 *
	 * @param  sessions	The number of sessions the character has participated in.
	 *
	 * @param  style	The number of style points the character currently has.
     */
	public PointHandler(int start, int karma, int experience, int spent, int sessions, int style) {
		this.start = start;
		this.karma = karma;
		this.experience = experience;
		this.spent = spent;
		veteran = 0;
		this.sessions = sessions;
		this.style = style;
		other = new Vector<PointTrait>();
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
	public boolean addOtherPointTrait(String name, int value) {
		PointTrait pointTrait = new PointTrait(name, value);
		if (other.contains(pointTrait)) return false;
		other.add(pointTrait);
		return true;
	}

	/**
	 * Adds an additional point trait for the character, with the value {@code 0}.
	 *
	 * @param name	The name of the points.
	 *
	 * @return {@code true} if the type was added, {@code false} if it already existed.
	 */
	public boolean addOtherPointTrait(String name) {
		PointTrait pointTrait = new PointTrait(name);
		if (other.contains(pointTrait)) return false;
		other.add(pointTrait);
		return true;
	}

	/**
     * Returns the {@code Vector} of {@code PointTrait} that represent the extra point types and their values.
	 *
	 * @return the {@code Vector<PointTrait>} that represent the extra point types and their values.
     */
	public Vector<PointTrait> getOtherPointTraits() {
		return other;
	}

	/**
     * Returns the {@code PointTrait} that represent the extra point type with the specified name.
	 *
	 * @param name	The name of the point type.
	 *
	 * @return the {@code PointTrait} that represent the extra point type; returns {@code null}
	 * if the {@code PointTrait} doesn't exist.
     */
	public PointTrait getValueOfOtherPoint(String name) {
		PointTrait pointTrait = new PointTrait(name);
		for (PointTrait pt: other) {
			if (pt.equals(pointTrait)) return pt;
		}
		return null;
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
	public boolean setValueOfOtherPoint(String name, int value) {
		PointTrait pointTrait = new PointTrait(name);
		for (PointTrait pt: other) {
			if (pt.equals(pointTrait)) {
				pt.setValue(value);
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
	public boolean addToValueOfOtherPoint(String name, int value) {
		PointTrait pointTrait = new PointTrait(name);
		for (PointTrait pt: other) {
			if (pt.equals(pointTrait)) {
				pt.setValue(pt.getValue() + value);
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
     * Returns the number of karma the character has.
	 *
	 * @return the number of karma the character has.
     */
	public int getKarma() {
		return karma;
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
     * Returns the number of sessions the character has participated in.
	 *
	 * @return the number of sessions the character has participated in.
     */
	public int getSessions() {
		return sessions;
	}

	/**
     * Returns the number of style points the character has.
	 *
	 * @return the number of style points the character has.
     */
	public int getStylePoints() {
		return style;
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
     * Sets the number of karma the character has.
     *
     * @param karma	The number of karma the character has.
     */
	public void setKarma(int karma) {
		this.karma = karma;
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
     * Sets the number of sessions the character has participated in.
     *
     * @param sessions	The number of sessions the character has participated in.
     */
	public void setSessions(int sessions) {
		this.sessions = sessions;
	}

	/**
     * Sets the number of style points the character has.
     *
     * @param style	The number of style points the character has.
     */
	public void setStylePoints(int style) {
		this.style = style;
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

	private void updateVeteran() {
		veteran = experience + spent - start;
	}
}