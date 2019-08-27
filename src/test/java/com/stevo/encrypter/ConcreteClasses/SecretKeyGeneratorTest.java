/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import javax.crypto.SecretKey;
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
public class SecretKeyGeneratorTest {
    
    private SecretKeyGenerator instance;
    
    public SecretKeyGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        instance = new SecretKeyGenerator();
    }
    
    @After
    public void tearDown() {
        
        instance = null;
    }

    /**
     * Test of generateKey method, of class SecretKeyGenerator.
     */
    @Test
    public void testGenerateKey_Key_Size() {
        System.out.println("generateKey");
        String encryptionType = "AES";
        int keySize = 128;
        int expResult = 16;
        SecretKey result = instance.generateKey(encryptionType, keySize);
        assertEquals(expResult, result.getEncoded().length);
    }

    /**
     * Test of generateKey method, of class SecretKeyGenerator.
     */
    @Test
    public void testGenerateKey_Correct_Encryption_Used() {
        System.out.println("generateKey");
        String encryptionType = "AES";
        int keySize = 128;
        String expResult = "AES";
        SecretKey result = instance.generateKey(encryptionType, keySize);
        assertEquals(expResult, result.getAlgorithm());
    }
    
}
