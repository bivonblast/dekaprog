package character.data;

/**
 * Class for skill representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public class HookTrait extends Trait<String> {

	public HookTrait(String name, String value) {
		super(name, value);
	}

        public HookTrait(String name) {
            super(name, "");
        }


    @Override
        public String valueToString() {
            return getValue();
        }
}
