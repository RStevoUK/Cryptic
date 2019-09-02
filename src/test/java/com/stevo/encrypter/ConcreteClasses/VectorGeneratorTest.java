/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stevo
 */
public class VectorGeneratorTest {
    
    public VectorGeneratorTest() {
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
     * Test of generateInitialisationVector method, of class VectorGenerator.
     */
    @Test
    public void testGenerateInitialisationVector() {
        System.out.println("generateInitialisationVector");
        int size = 128;
        VectorGenerator instance = new VectorGenerator();
        
        IvParameterSpec result = instance.generateInitialisationVector(size);
        
        assertEquals(size, result.getIV().length);
    }

    /**
     * Test of getInitVectSpec method, of class VectorGenerator.
     */
    @Test
    public void testGetInitVectSpec() {
        System.out.println("getInitVectSpec");
        
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        
        VectorGenerator instance = new VectorGenerator();
        
        IvParameterSpec result = instance.getInitVectSpec(iv);
        
        Assert.assertArrayEquals(iv, result.getIV());
    }
    
}
