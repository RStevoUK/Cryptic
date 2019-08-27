/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
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
public class CipherSelectorTest {
    
    private CipherSelector instance;
    
    public CipherSelectorTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        instance = new CipherSelector();
    }
    
    @After
    public void tearDown() {
        
        instance = null;
    }

    /**
     * Test of getCipherInstance method, of class CipherSelector.
     */
    @Test
    public void testGetCipherInstance() {
        String cipherType = "";
        Cipher expResult = null;
        Cipher result = instance.getCipherInstance(cipherType);
        assertEquals(expResult, result);
    }
    
    /**
    * Test of getCipherInstance method, of class CipherSelector.
    */
    @Test
    public void testGetCipherInstanceAESCBCPadding() {
        String cipherType = "AES/CBC/PKCS5Padding";
        
        String expResult = "AES/CBC/PKCS5Padding";
        
        Cipher result = instance.getCipherInstance(cipherType);
        assertEquals(expResult, result.getAlgorithm());
    }
    
    /**
    * Test of getCipherInstance method, of class CipherSelector.
    */
    @Test
    public void testGetCipherInstanceDESCBCPadding() {
        String cipherType = "DES/CBC/PKCS5Padding";
        
        String expResult = "DES/CBC/PKCS5Padding";
        
        Cipher result = instance.getCipherInstance(cipherType);
        assertEquals(expResult, result.getAlgorithm());
    }
    
    /**
    * Test of getCipherInstance method, of class CipherSelector.
    */
    @Test
    public void testGetCipherInstanceDESedeCBCPadding() {
        String cipherType = "DESede/CBC/PKCS5Padding";
        
        String expResult = "DESede/CBC/PKCS5Padding";
        
        Cipher result = instance.getCipherInstance(cipherType);
        assertEquals(expResult, result.getAlgorithm());
    }
    
    @Test
    public void CheckIncorrectCipherReturnsNull() {

            Cipher cipher = instance.getCipherInstance("NULL");
            
            assertNull(cipher);
    }
}
