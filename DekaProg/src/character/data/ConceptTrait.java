package character.data;

/**
 * Class for concept representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class ConceptTrait extends Trait<String> {

    /**
     * Initiates a new {@code ConceptTrait} with the specified name and value.
     * @param name The name of the trait.
     * @param value The value of the the trait.
     */
    public ConceptTrait(String name, String value) {
        super(name, value);
    }

    /**
     * Initiates a new {@code ConceptTrait} with the specified name.
     * @param name The name of the trait.
     */
    public ConceptTrait(String name) {
        super(name,"");
    }

    /**
     * Returns the string representation of this trait's value.
     * @return the value as a String.
     */
    @Override
    public String valueToString() {
        return getValue();
    }
}
