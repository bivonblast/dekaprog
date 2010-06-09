/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.character.data;

/**
 *
 * @author Jonas
 */
public class DuplicateSkillException extends Exception {

    public DuplicateSkillException() {
        super("One or more sets of two or more identical skills were found." +
                "Only the first occurance of each set was added to the program.");
    }
}
