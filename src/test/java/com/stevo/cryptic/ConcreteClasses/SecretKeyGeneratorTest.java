/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.IKeyGenerator;
import java.security.InvalidParameterException;
import javax.crypto.SecretKey;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
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
public class SecretKeyGeneratorTest {
    
    @Autowired
    private IKeyGenerator instance;
    
    /**
     *
     */
    public SecretKeyGeneratorTest() { }

    /**
     * Test of generateKey method, of class SecretKeyGenerator.
     */
    @Test
    public void testGenerateKey_Key_Size_AES() {
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
    public void testGenerateKey_Key_Size_DES() {
        System.out.println("generateKey");
        String encryptionType = "DES";
        int keySize = 56;
        int expResult = 8;
        SecretKey result = instance.generateKey(encryptionType, keySize);
        assertEquals(expResult, result.getEncoded().length);
    }
    
    /**
    * Test of generateKey method, of class SecretKeyGenerator.
    */
    @Test
    public void testGenerateKey_Key_Size_DESede() {
        System.out.println("generateKey");
        String encryptionType = "DESede";
        int keySize = 168;
        int expResult = 24;
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

    /**
     * Test of generateKey method, of class SecretKeyGenerator.
     */
    @Test(expected = InvalidParameterException.class)
    public void testGenerateKey_String_int() {
        System.out.println("generateKey");
        String encryptionType = "AES";
        int keySize = 0;
        instance = new SecretKeyGenerator();
        instance.generateKey(encryptionType, keySize);
    }

    /**
     * Test of generateKey method, of class SecretKeyGenerator.
     */
    @Test
    public void testGenerateKey_String() {
        System.out.println("generateKey");
        String encryptionType = "";
        instance = new SecretKeyGenerator();
        SecretKey expResult = null;
        SecretKey result = instance.generateKey(encryptionType);
        assertEquals(expResult, result);
    }
    
    /**
    * Test of generateKey method, of class SecretKeyGenerator.
    */
    @Test
    public void testGenerateKeyLength_AES() {
        System.out.println("generateKey");
        String encryptionType = "AES";
        instance = new SecretKeyGenerator();
        int expKeyLength = 16;
        SecretKey result = instance.generateKey(encryptionType);
        int length = result.getEncoded().length;
        assertEquals(expKeyLength, length);
    }
    
    /**
    * Test of generateKey method, of class SecretKeyGenerator.
    */
    @Test
    public void testGenerateKeyLength_DES() {
        System.out.println("generateKey");
        String encryptionType = "DES";
        instance = new SecretKeyGenerator();
        int expKeyLength = 8;
        SecretKey result = instance.generateKey(encryptionType);
        int length = result.getEncoded().length;
        assertEquals(expKeyLength, length);
    }
    
    /**
    * Test of generateKey method, of class SecretKeyGenerator.
    */
    @Test
    public void testGenerateKeyLength_AESede() {
        System.out.println("generateKey");
        String encryptionType = "DESede";
        instance = new SecretKeyGenerator();
        int expKeyLength = 24;
        SecretKey result = instance.generateKey(encryptionType);
        int length = result.getEncoded().length;
        assertEquals(expKeyLength, length);
    }
    
}
