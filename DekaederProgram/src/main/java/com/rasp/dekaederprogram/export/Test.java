/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.data.SkillTrait;
import com.rasp.dekaederprogram.character.data.SkillType;
import com.rasp.dekaederprogram.character.data.Trait;
import java.util.Vector;

/**
 *
 * @author MARTIN
 */
public class Test {
    Trait bubbalplast = new SkillTrait("test", SkillType.newMentalSkillType());
    Vector<SkillTrait> vector = new Vector<SkillTrait>();
    public Test(){

        System.out.println(vector.getClass().getName() + "   ");
        System.out.println(vector.firstElement().getClass() + "");
    }

    public static void main(String[] args) {
        new Test();
    }
//    if(test instanceof SkillTrait){
//            System.out.println("TRUE");
//    }else{
//            System.out.println("FALSE");
//    }
}
