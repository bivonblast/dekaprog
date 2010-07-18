/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.DekaederCharacter;
import junit.framework.TestCase;

/**
 *
 * @author MARTIN
 */
public class WikiHandlerTest extends TestCase {
    
    public WikiHandlerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of connect method, of class WikiHandler.
     */
    public void testConnect_0args() {
//        System.out.println("connect");
//        WikiHandler instance = null;
//        boolean expResult = false;
//        boolean result = instance.connect();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class WikiHandler.
     */
    public void testConnect_String_String() {
//        System.out.println("connect");
//        String username = "DekaProg";
//        String password = "tester";
//        WikiHandler instance = null;
//        boolean expResult = false;
//        boolean result = instance.connect(username, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of readCharacter method, of class WikiHandler.
     */
    public void testReadCharacter_String() {
//        System.out.println("readCharacter");
//        String characterName = "";
//        boolean expResult = false;
//        DekaederCharacter result = null;
//        try {
//            WikiHandler instance = new WikiHandler("http://wiki.raspare.se/");
//            expResult = true;
//            result = instance.readCharacter(characterName);
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }finally{
//            System.out.println("Done");
//        }
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of readCharacter method, of class WikiHandler.
     */
    public void testReadCharacter_String_String() {
            String location = Setup.getPath();
            WikiHandler instance = new WikiHandler("http://wiki.raspare.se/");
            DekaederCharacter expResult = new DekaederCharacter(null, null, null, null, null, null);
            DekaederCharacter result = instance.readCharacter(location + "rasp.dkp", location + "rasp_MALL.map");
            //assertNotSame(expResult, result);
            //assertEquals(expResult, result);
    }

    /**
     * Test of writeCharacter method, of class WikiHandler.
     */
    public void testWriteCharacter() {
//        System.out.println("writeCharacter");
//        DekaederCharacter character = null;
//        String username = "";
//        boolean showValue = false;
//        WikiHandler instance = null;
//        boolean expResult = false;
//        boolean result = instance.writeCharacter(character, username, showValue);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of readSettings method, of class WikiHandler.
     */
    public void testReadSettings() {
//        System.out.println("readSettings");
//        String campaignName = "";
//        WikiHandler instance = null;
//        String expResult = "";
//        String result = instance.readSettings(campaignName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of writeSettings method, of class WikiHandler.
     */
    public void testWriteSettings() {
//        System.out.println("writeSettings");
//        String campaignName = "";
//        String campaign = "";
//        WikiHandler instance = null;
//        boolean expResult = false;
//        boolean result = instance.writeSettings(campaignName, campaign);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillsFromString method, of class WikiHandler.
     */
    public void testGetSkillsFromString_String() {
//        System.out.println("getSkillsFromString");
//        String curBuffer = "";
//        WikiHandler instance = null;
//        SkillHandler expResult = null;
//        SkillHandler result = instance.getSkillsFromString(curBuffer);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillsFromString method, of class WikiHandler.
     */
    public void testGetSkillsFromString_3args() {
//        System.out.println("getSkillsFromString");
//        String curBuffer = "";
//        String[] decisionmarks = null;
//        Vector<SkillType> allSkillTypes = null;
//        WikiHandler instance = null;
//        SkillHandler expResult = null;
//        SkillHandler result = instance.getSkillsFromString(curBuffer, decisionmarks, allSkillTypes);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of parseWikiCategories method, of class WikiHandler.
     */
    public void testParseWikiCategories() {
//        System.out.println("parseWikiCategories");
//        Article article = null;
//        WikiHandler instance = null;
//        String[] expResult = null;
//        String[] result = instance.parseWikiCategories(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
