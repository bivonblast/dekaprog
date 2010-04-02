package character;

import character.data.*;

public class DekaederCharacter {
	//variabler
	private ConceptHandler      conceptHandler;
	private PointHandler        pointHandler;
        private SkillHandler        skillHandler;
        private SpecialityHandler   specialityHandler;
        private HookHandler         hookHandler;

	public DekaederCharacter(ConceptHandler conceptHandler, 
            PointHandler pointHandler, SkillHandler skillHandler,
            SpecialityHandler specialityHandler, HookHandler hookHandler) {
            this.conceptHandler = conceptHandler;
            this.pointHandler = pointHandler;
            this.skillHandler = skillHandler;
            this.specialityHandler = specialityHandler;
            this.hookHandler = hookHandler;
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

        public HookHandler getHookHandler() {
            return hookHandler;
        }
}