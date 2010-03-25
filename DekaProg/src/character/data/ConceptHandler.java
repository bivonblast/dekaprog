package character.data;

/**
 * Class for keeping track of the character concept.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class ConceptHandler extends TraitHandler<ConceptTrait>{
    /**
     * Initiates a new, empty {@code ConceptHandler}.
     */
    public ConceptHandler() {
	super();
    }

    /**
     * Adds a new {@code ConceptTrait} to the {@code ConceptHandler}, given
     * that is deosn't already exist.
     * @param traitName The name of the new {@code ConceptTrait}.
     * @param traitValue The {@code String} value of the new {@code ConceptTrait}.
     * @return {@code true} if the {@code ConceptTrait} was added, {@code false}
     * if it already existed.
     */
    public boolean addConceptTrait(String traitName, String traitValue) {
        for (ConceptTrait ct : this) {
            if (ct.getName().equalsIgnoreCase(traitName)) return false;
        }
        add(new ConceptTrait(traitName, traitValue));
        return true;
    }

    /**
     * Removes the {@code ConceptTrait} with the specified name from the
     * {@code ConceptHandler}, given that it exists.
     * @param traitName The name of the {@code ConceptTrait} to be removed.
     * @return {@code true} if the {@code ConceptTrait} was succesfully removed,
     * {@code false} if it didn't exist.
     */
    public boolean removeConceptTrait(String traitName) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getName().equalsIgnoreCase(traitName)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Sets a value to the {@code ConceptTrait} with the specified name, given
     * that it exists.
     * @param traitName The name of the {@code ConceptTrait} to be altered.
     * @param traitValue The new value of the {@code ConceptTrait}.
     * @return @return {@code true} if the {@code ConceptTrait} was succesfully
     * altered, {@code false} if it didn't exist.
     */
    public boolean setConceptTraitValue(String traitName, String traitValue) {
        for (ConceptTrait ct : this) {
            if (ct.getName().equalsIgnoreCase(traitName)) {
                ct.setValue(traitValue);
                return true;
            }
        }
        return false;
    }
}