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
public class FileNameObjectTest {
    
    public FileNameObjectTest() { }

    /**
     * Test of getKeyFileName method, of class FileNameObject.
     */
    @Test
    public void testGetKeyFileName() {
        System.out.println("getKeyFileName");
        FileNameObject instance = new FileNameObject("test-parameter-1", "test-parameter-2");
        String expResult = "test-parameter-1";
        String result = instance.getKeyFileName();
        assertEquals(expResult, result);
    }
    
    /**
    * Test of testGetCryptedFileName method, of class FileNameObject.
    */
    @Test
    public void testGetCryptedFileName() {
        System.out.println("getCryptedFileName");
        FileNameObject instance = new FileNameObject("test-parameter-1", "test-parameter-2");
        String expResult = "test-parameter-2";
        String result = instance.getCryptedFileName();
        assertEquals(expResult, result);
    }
    
}
