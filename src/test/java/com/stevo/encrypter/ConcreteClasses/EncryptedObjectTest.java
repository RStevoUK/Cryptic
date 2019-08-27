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
public class EncryptedObjectTest {
    
    private EncryptedObject instance;
    
    public EncryptedObjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        byte[] key = ("key").getBytes();
        
        byte[] vector = ("vector").getBytes();
        
        byte[] result = ("result").getBytes();
        
        instance = new EncryptedObject("DESede/CBC/PKCS5Padding", key, vector, result);
    }
    
    @After
    public void tearDown() {
        
        instance = null;
    }

    /**
     * Test of getEncryptionMethod method, of class EncryptedObject.
     */
    @Test
    public void testGetEncryptionMethod() {
        System.out.println("getEncryptionMethod");
        
        String expResult = "DESede/CBC/PKCS5Padding";
        String result = instance.getEncryptionMethod();
        assertEquals(expResult, result);
    }
    
    /**
    * Test of getKeyMethod method, of class EncryptedObject.
    */
    @Test
    public void testGetKeyMethod() {
        System.out.println("getKeyMethod");
        
        byte[] expResult = ("key").getBytes();
        
        byte[] result = instance.getKey();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getInitVect method, of class EncryptedObject.
     */
    @Test
    public void testGetInitVect() {
        System.out.println("getInitVect");
        byte[] expResult = ("vector").getBytes();
        byte[] result = instance.getInitVect();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getResult method, of class EncryptedObject.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        byte[] expResult = ("result").getBytes();
        byte[] result = instance.getResult();
        assertArrayEquals(expResult, result);
    }
    
}
