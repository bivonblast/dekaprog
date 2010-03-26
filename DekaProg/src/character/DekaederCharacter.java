package character;

import character.data.*;

public class DekaederCharacter {
	//variabler
	private ConceptHandler      conceptHandler;
	private PointHandler        pointHandler;
        private SkillHandler        skillHandler;
        private SpecialityHandler   specialityHandler;

	public DekaederCharacter(ConceptHandler conceptHandler, 
            PointHandler pointHandler, SkillHandler skillHandler,
            SpecialityHandler specialityHandler) {
            this.conceptHandler = conceptHandler;
            this.pointHandler = pointHandler;
            this.skillHandler = skillHandler;
            this.specialityHandler = specialityHandler;
	}

	public ConceptHandler getConceptHandler() {
            return conceptHandler;
	}

	public PointHandler getPointHandler() {
            return pointHandler;
	}

        public SkillHandler getSkillHandler() {
            return skillHandler;
        }

        public SpecialityHandler getSpecialityHandler() {
            return specialityHandler;
        }
}