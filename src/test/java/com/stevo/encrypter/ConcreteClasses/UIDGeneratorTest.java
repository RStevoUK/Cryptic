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
public class UIDGeneratorTest {
    
    public UIDGeneratorTest() {
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
     * Test of generateUID method, of class UIDGenerator.
     */
    @Test
    public void testGenerateUID() {
        System.out.println("generateUID");
        UIDGenerator instance = new UIDGenerator();
        String result = instance.generateUID();
        
        int expResult = 36;
        
        assertEquals(expResult, result.length());
    }
    
}
