/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.ISecretKeySpec;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Assert;
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
public class SecretKeySpecServiceTest {
    
    @Autowired
    private ISecretKeySpec instance;
    
    /**
     *
     */
    public SecretKeySpecServiceTest() { }

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
        String expResult = "AES";
        
        SecretKeySpec result = instance.getSecretKeySpec(testKey, cipherType);
        
        
        assertEquals(expResult, result.getAlgorithm());
        Assert.assertArrayEquals(testKey, result.getEncoded());
    }
    
}
