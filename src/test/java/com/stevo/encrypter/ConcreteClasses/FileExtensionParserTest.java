/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stevo
 */
public class FileExtensionParserTest {
    
    public FileExtensionParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parseFileExtension method, of class FileExtensionParser.
     */
    @Test
    public void testParseFileExtensionCorrectReturn() {
        System.out.println("parseFileExtension");
        String fileName = "test.txt";
        FileExtensionParser instance = new FileExtensionParser();
        String expResult = ".txt";
        String result = instance.parseFileExtension(fileName);
        assertEquals(expResult, result);
    }
    
    /**
    * Test of parseFileExtension method, of class FileExtensionParser.
    */
    @Test
    public void testParseFileExtensionNoExtensionReturnsNull() {
        System.out.println("parseFileExtension");
        String fileName = "test";
        FileExtensionParser instance = new FileExtensionParser();
        String expResult = null;
        String result = instance.parseFileExtension(fileName);
        assertEquals(expResult, result);
    }
}
