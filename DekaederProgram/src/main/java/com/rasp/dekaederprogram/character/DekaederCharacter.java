package com.rasp.dekaederprogram.character;

import com.rasp.dekaederprogram.character.data.HookHandler;
import com.rasp.dekaederprogram.character.data.PointHandler;
import com.rasp.dekaederprogram.character.data.SkillHandler;
import com.rasp.dekaederprogram.character.data.SpecialityHandler;
import com.rasp.dekaederprogram.character.data.ConceptHandler;
import com.rasp.dekaederprogram.character.data.AdvantageHandler;
import com.rasp.dekaederprogram.character.data.AdvantageTrait;
import com.rasp.dekaederprogram.character.data.ConceptTrait;
import com.rasp.dekaederprogram.character.data.HookTrait;
import com.rasp.dekaederprogram.character.data.PointTrait;
import com.rasp.dekaederprogram.character.data.SkillTrait;
import com.rasp.dekaederprogram.character.data.SpecialityTrait;
import com.rasp.dekaederprogram.character.data.Trait;
import com.rasp.dekaederprogram.character.data.TraitHandler;
import java.io.Serializable;
import java.util.ArrayList;

public class DekaederCharacter implements Serializable {
	//variabler
        private TraitHandler<? extends Trait>[]      traitHandlers;
	private int conceptIndex = -1;
	private int pointIndex = -1;
        private int skillIndex = -1;
        private int specialityIndex = -1;
        private int hookIndex = -1;
        private int advantageIndex = -1;

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
            for (int i = 0; i < traitHandlers.length; i++) {
                TraitHandler th = traitHandlers[i];
                if (th instanceof ConceptHandler)
                    conceptIndex = i;
                else if (th instanceof PointHandler)
                    pointIndex = i;
                else if (th instanceof SkillHandler)
                    skillIndex = i;
                else if (th instanceof SpecialityHandler)
                    specialityIndex = i;
                else if (th instanceof HookHandler)
                    hookIndex = i;
                else if (th instanceof AdvantageHandler)
                    advantageIndex = i;
            }
        }

        public TraitHandler[] getTraitHandlers(){
            return traitHandlers;
        }
        
	public ConceptHandler getConceptHandler() {
            if (conceptIndex == -1) return null;
            return (ConceptHandler)(traitHandlers[conceptIndex]);
	}

	public PointHandler getPointHandler() {
             if (pointIndex == -1) return null;
            return (PointHandler)(traitHandlers[pointIndex]);
	}

        public SkillHandler getSkillHandler() {
             if (skillIndex == -1) return null;
            return (SkillHandler)(traitHandlers[skillIndex]);
        }

        public SpecialityHandler getSpecialityHandler() {
            if (specialityIndex == -1) return null;
            return (SpecialityHandler)(traitHandlers[specialityIndex]);
        }

        public HookHandler getHookHandler() {
            if (hookIndex == -1) return null;
            return (HookHandler)(traitHandlers[hookIndex]);
        }

        public AdvantageHandler getAdvantageHandler() {
            if (advantageIndex == -1) return null;
            return (AdvantageHandler)(traitHandlers[advantageIndex]);
        }

        public void addTraits(ArrayList<Trait> allTraits){
            for(Trait curTrait : allTraits){
                if (curTrait instanceof AdvantageTrait && advantageIndex >= 0)
                    ((AdvantageHandler)traitHandlers[advantageIndex]).add((AdvantageTrait)curTrait);
                else if (curTrait instanceof ConceptTrait && conceptIndex >= 0)
                    ((ConceptHandler)traitHandlers[conceptIndex]).add((ConceptTrait)curTrait);
                else if (curTrait instanceof HookTrait && hookIndex >= 0)
                    ((HookHandler)traitHandlers[hookIndex]).add((HookTrait)curTrait);
                else if (curTrait instanceof PointTrait && pointIndex >= 0)
                    ((PointHandler)traitHandlers[pointIndex]).add((PointTrait)curTrait);
                else if (curTrait instanceof SkillTrait && skillIndex >= 0)
                    ((SkillHandler)traitHandlers[skillIndex]).add((SkillTrait)curTrait);
                else if (curTrait instanceof SpecialityTrait && specialityIndex >= 0)
                    ((SpecialityHandler)traitHandlers[specialityIndex]).add((SpecialityTrait)curTrait);
            }
        }
}
