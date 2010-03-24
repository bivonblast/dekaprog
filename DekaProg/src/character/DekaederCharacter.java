package character;

import character.data.*;

public class DekaederCharacter {
	//variabler
	private ConceptHandler conceptHandler;
	private PointHandler	pointHandler;

	public DekaederCharacter(ConceptHandler conceptHandler, PointHandler pointHandler) {
		this.conceptHandler = conceptHandler;
		this.pointHandler = pointHandler;
	}

	public ConceptHandler getConceptHandler() {
		return conceptHandler;
	}

	public PointHandler getPointHandler() {
		return pointHandler;
	}
}