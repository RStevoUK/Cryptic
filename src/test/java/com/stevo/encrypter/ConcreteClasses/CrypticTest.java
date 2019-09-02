/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.OnProgressListener;
import java.io.File;
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
public class CrypticTest {
    
    public CrypticTest() {
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
     * Test of encryptFile method, of class Cryptic.
     */
    @Test
    public void testEncryptDecryptFile_3args() {
        System.out.println("encryptFile");
        File fileToEncrypt = null;
        File outputFileName = null;
        String cipherType = "";
        Cryptic instance = null;
        instance.encryptFile(fileToEncrypt, outputFileName, cipherType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptFile method, of class Cryptic.
     */
    @Test
    public void testEncryptDecryptFile_4args() {
        System.out.println("encryptFile");
        File fileToEncrypt = null;
        File outputFileName = null;
        String cipherType = "";
        OnProgressListener progressListener = null;
        Cryptic instance = null;
        instance.encryptFile(fileToEncrypt, outputFileName, cipherType, progressListener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
