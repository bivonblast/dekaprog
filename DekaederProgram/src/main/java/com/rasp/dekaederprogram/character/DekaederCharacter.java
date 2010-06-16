package com.rasp.dekaederprogram.character;

import com.rasp.dekaederprogram.character.data.HookHandler;
import com.rasp.dekaederprogram.character.data.PointHandler;
import com.rasp.dekaederprogram.character.data.SkillHandler;
import com.rasp.dekaederprogram.character.data.SpecialityHandler;
import com.rasp.dekaederprogram.character.data.ConceptHandler;
import com.rasp.dekaederprogram.character.data.AdvantageHandler;
import com.rasp.dekaederprogram.character.data.Trait;
import com.rasp.dekaederprogram.character.data.TraitHandler;
import java.io.Serializable;

public class DekaederCharacter implements Serializable {
	//variabler
        private TraitHandler<? extends Trait>[]      traitHandlers;
	private ConceptHandler      conceptHandler;
	private PointHandler        pointHandler;
        private SkillHandler        skillHandler;
        private SpecialityHandler   specialityHandler;
        private HookHandler         hookHandler;
        private AdvantageHandler    advantageHandler;

	/*public DekaederCharacter(ConceptHandler conceptHandler,
            PointHandler pointHandler, SkillHandler skillHandler,
            SpecialityHandler specialityHandler, HookHandler hookHandler,
            AdvantageHandler advantageHandler) {
            this.conceptHandler = conceptHandler;
            this.pointHandler = pointHandler;
            this.skillHandler = skillHandler;
            this.specialityHandler = specialityHandler;
            this.hookHandler = hookHandler;
            this.advantageHandler = advantageHandler; 
	}*/

        public DekaederCharacter(TraitHandler<? extends Trait>... traitHandlers) {
            this.traitHandlers = traitHandlers;
            for (TraitHandler th : traitHandlers) {
                if (th instanceof ConceptHandler)
                    conceptHandler = (ConceptHandler)th;
                if (th instanceof PointHandler)
                    pointHandler = (PointHandler)th;
                if (th instanceof SkillHandler)
                    skillHandler = (SkillHandler)th;
                if (th instanceof SpecialityHandler)
                    specialityHandler = (SpecialityHandler)th;
                if (th instanceof HookHandler)
                    hookHandler = (HookHandler)th;
                if (th instanceof AdvantageHandler)
                    advantageHandler = (AdvantageHandler)th;
            }
        }

        public TraitHandler[] getTraitHandlers(){
            return traitHandlers;
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

        public AdvantageHandler getAdvantageHandler() {
            return advantageHandler;
        }
}
