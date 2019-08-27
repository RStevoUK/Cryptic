/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IFileNameGenerator;
import com.stevo.encrypter.Interfaces.IFileNameObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stevo
 */
@Component
public class FileNameGeneratorTest {
    
    @Autowired
    private IFileNameGenerator instance;
    
    public FileNameGeneratorTest() {
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
     * Test of generateFileNameObject method, of class FileNameGenerator.
     */
    @Test
    public void testGenerateKeyFileNamePrefix() {
        System.out.println("testGenerateKeyFileNamePrefix");
        IFileNameObject result = instance.generateFileNameObject();
        assertTrue(result.getKeyFileName().contains("Cryptic-Key-"));
    }
    
    /**
    * Test of generateFileNameObject method, of class FileNameGenerator.
    */
    @Test
    public void testGenerateKeyFileNameSuffix() {
        System.out.println("testGenerateKeyFileNameSuffix");
        IFileNameObject result = instance.generateFileNameObject();
        assertTrue(result.getKeyFileName().contains(".cryptickey"));
    }
    
}
