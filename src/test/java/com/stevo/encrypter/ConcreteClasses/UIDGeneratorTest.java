/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IUIDGenerator;
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
public class UIDGeneratorTest {
    
    @Autowired
    private IUIDGenerator instance;
    
    public UIDGeneratorTest() { }

    /**
     * Test of generateUID method, of class UIDGenerator.
     */
    @Test
    public void testGenerateUID() {
        System.out.println("generateUID");
        String result = instance.generateUID();
        
        int expResult = 36;
        
        assertEquals(expResult, result.length());
    }
    
}
