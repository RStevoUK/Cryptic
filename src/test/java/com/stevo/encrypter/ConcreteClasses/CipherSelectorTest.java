/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.ICipher;
import javax.crypto.Cipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class CipherSelectorTest {
    
    @Autowired
    private ICipher instance;
    
    public CipherSelectorTest() { }
    
    /**
     * Test of getCipherInstance method, of class CipherSelector.
     */
    @Test
    public void cipher_Instance_Null_Return_If_Incorrect_Cipher_Specified() {
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
