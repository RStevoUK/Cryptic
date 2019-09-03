/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.IFileExtensionParser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author Stevo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class FileExtensionParserTest {
    
    @Autowired
    private IFileExtensionParser instance;
    
    /**
     *
     */
    public FileExtensionParserTest() { }

    /**
     * Test of parseFileExtension method, of class FileExtensionParser.
     */
    @Test
    public void testParseFileExtensionCorrectReturn() {
        System.out.println("parseFileExtension");
        String fileName = "test.txt";
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
        String expResult = null;
        String result = instance.parseFileExtension(fileName);
        assertEquals(expResult, result);
    }
}
