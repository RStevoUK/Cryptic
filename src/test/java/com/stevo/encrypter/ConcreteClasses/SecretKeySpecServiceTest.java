/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;
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
public class SecretKeySpecServiceTest {
    
    public SecretKeySpecServiceTest() {
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
     * Test of getSecretKeySpec method, of class SecretKeySpecService.
     */
    @Test
    public void testGetSecretKeySpec() {
        System.out.println("getSecretKeySpec");
        
        SecureRandom random = new SecureRandom();
        byte[] testKey = new byte[16];
        random.nextBytes(testKey);
        
        String cipherType = "AES/CBC/PKCS5Padding";
        SecretKeySpecService instance = new SecretKeySpecService();
        String expResult = "AES";
        
        SecretKeySpec result = instance.getSecretKeySpec(testKey, cipherType);
        
        
        assertEquals(expResult, result.getAlgorithm());
        Assert.assertArrayEquals(testKey, result.getEncoded());
    }
    
}
