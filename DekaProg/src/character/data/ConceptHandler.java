package character.data;

/**
 * Class for concept representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class ConceptHandler {
	/**
	* The {@code boolean} representing the male gender.
	*/
	public static final boolean GENDER_MALE	= true;

	/**
	* The {@code boolean} representing the female gender.
	*/
	public static final boolean GENDER_FEMALE = false;

	//variabler
	private String	name;
	private String	concept;
	private String	campaign;
	private boolean	gender;
	private int		age;
	private String	appearance;
	private String	quote;

	/**
     * Initiates a new {@code ConceptHandler} with the specified character name, concept, campaign name, gender, age, appearance and quote.
	 *
	 * @param  name			The name of the character.
	 *
	 * @param  concept		The concept for the character.
	 *
	 * @param  campaign		The name for the campaign in which the character will be played.
	 *
	 * @param  gender		The gender of the character.
	 *
	 * @param  age			The age of the character.
	 *
	 * @param  appearance	The description of the character's appearance.
	 *
	 * @param  quote		The quote of the character.
     */
	public ConceptHandler(String name, String concept, String campaign, boolean gender, int age, String appearance, String quote) {
		this.name		= name;
		this.concept	= concept;
		this.campaign	= campaign;
		this.gender		= gender;
		this.age		= age;
		this.appearance	= appearance;
		this.quote		= quote;
	}

	/**
     * Initiates a new {@code ConceptHandler} with the gender set to female, the age set to {@code 0}, and the rest of the concept left blank.
     */
	public ConceptHandler() {
		name		= "";
		concept		= "";
		campaign	= "";
		gender		= GENDER_FEMALE;
		age			= 0;
		appearance	= "";
		quote		= "";
	}

	/**
     * Returns the character's name.
	 *
	 * @return the character's name.
     */
	public String getName() {
		return name;
	}

	/**
     * Returns the character's concept.
	 *
	 * @return the character's concept.
     */
	public String getConcept() {
		return concept;
	}

	/**
     * Returns the name of the campaign the character is participating in.
	 *
	 * @return the name of the campaign the character is participating in.
     */
	public String getCampaign() {
		return campaign;
	}

	/**
     * Returns the character's gender.
	 *
	 * @return the character's gender as a boolean.
     */
	public boolean getGender() {
		return gender;
	}

	/**
     * Returns the character's age.
	 *
	 * @return the character's age.
     */
	public int getAge() {
		return age;
	}

	/**
     * Returns the description of the character's appearance.
	 *
	 * @return the description of the character's appearance.
     */
	public String getApperance() {
		return appearance;
	}

	/**
     * Returns the quote of the character.
	 *
	 * @return the quote of the character.
     */
	public String getQuote() {
		return quote;
	}

	/**
     * Sets the name of the character.
     *
     * @param name  The name of the character.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Sets the concept of the character.
     *
     * @param concept  The concept of the character.
     */
	public void setConcept(String concept) {
		this.concept = concept;
	}

	/**
     * Sets the name of the campaign the character is participating in.
     *
     * @param campaign  The name of the campaign the character is participating in.
     */
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	/**
     * Sets the gender of the character.
     *
     * @param gender  The gender of the character.
     */
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	/**
     * Sets the age of the character.
     *
     * @param age  The age of the character.
     */
	public void setAge(int age) {
		this.age = age;
	}

	/**
     * Sets the description of of the character's appearance.
     *
     * @param appearance  The description of of the character's appearance.
     */
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	/**
     * Sets the quote of the character.
     *
     * @param quote  The quote of the character.
     */
	public void setQuote(String quote) {
		this.quote = quote;
	}
}