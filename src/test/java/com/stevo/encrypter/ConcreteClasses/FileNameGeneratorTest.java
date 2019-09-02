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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
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
     * Test of getKeyFileName method, of injected type IFileNameGenerator.
     */
    @Test
    public void testGenerateKeyFileNamePrefix() {
        System.out.println("testGenerateKeyFileNamePrefix");
        IFileNameObject result = instance.generateFileNameObject();
        assertTrue(result.getKeyFileName().contains("Cryptic-Key-"));
    }
    
    /**
    * Test of getKeyFileName method, of injected type IFileNameGenerator.
    */
    @Test
    public void testGenerateKeyFileNameSuffix() {
        System.out.println("testGenerateKeyFileNameSuffix");
        IFileNameObject result = instance.generateFileNameObject();
        assertTrue(result.getKeyFileName().contains(".cryptickey"));
    }
    
    /**
    * Test of getCryptedFileName method, of injected type IFileNameGenerator.
    */
    @Test
    public void testGenerateContentFileNamePrefix() {
        System.out.println("testGenerateContentFileNamePrefix");
        IFileNameObject result = instance.generateFileNameObject();
        assertTrue(result.getCryptedFileName().contains("Cryptic-File-"));
    }
    
    /**
    * Test of getCryptedFileName method, of injected type IFileNameGenerator.
    */
    @Test
    public void testGenerateContentFileNameSuffix() {
        System.out.println("testGenerateContentFileNameSuffix");
        IFileNameObject result = instance.generateFileNameObject();
        assertTrue(result.getCryptedFileName().contains(".cryptic"));
    }
    
}
