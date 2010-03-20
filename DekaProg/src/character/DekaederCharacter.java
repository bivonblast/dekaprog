package character;

import character.data.*;

public class DekaederCharacter {
	//variabler
	private Concept concept;
	private Points	points;

	public DekaederCharacter(Concept concept, Points points) {
		this.concept = concept;
		this.points = points;
	}

	public Concept getConcept() {
		return concept;
	}

	public Points getPoints() {
		return points;
	}
}