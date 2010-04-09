/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package character;

import character.data.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jonas
 */
public class DekaederCampaign {
    //variabler
    private ConceptHandler      conceptHandler;
    private PointHandler        pointHandler;
    private SkillHandler        skillHandler;
    private AdvantageHandler    advantageHandler;

    public DekaederCampaign(ConceptHandler conceptHandler,
            PointHandler pointHandler, SkillHandler skillHandler,
            AdvantageHandler advantageHandler) {
        this.conceptHandler = conceptHandler;
        this.pointHandler = pointHandler;
        this.skillHandler = skillHandler;
        this.advantageHandler = advantageHandler;
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

    public AdvantageHandler getAdvantageHandler() {
        return advantageHandler;
    }

    public DekaederCharacter loadDekaederCharacter(File f) throws IOException,
            ClassNotFoundException {
      ObjectInputStream in =  new ObjectInputStream(new FileInputStream(f));
      DekaederCharacter dc = (DekaederCharacter)(in.readObject());
      in.close ();
      return dc;
    }

    public void saveDekaederCharacter(DekaederCharacter dc, File f) throws IOException {
      ObjectOutputStream out =  new ObjectOutputStream(new FileOutputStream(f));
      out.writeObject(dc);
      out.flush ();
      out.close ();
    }
}
