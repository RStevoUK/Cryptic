/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IInitVectGen;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;
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
public class VectorGeneratorTest {
    
    @Autowired
    private IInitVectGen instance;
    
    public VectorGeneratorTest() { }

    /**
     * Test of generateInitialisationVector method, of class VectorGenerator.
     */
    @Test
    public void testGenerateInitialisationVector() {
        System.out.println("generateInitialisationVector");
        int size = 128;
        
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
        
        IvParameterSpec result = instance.getInitVectSpec(iv);
        
        Assert.assertArrayEquals(iv, result.getIV());
    }
    
}
