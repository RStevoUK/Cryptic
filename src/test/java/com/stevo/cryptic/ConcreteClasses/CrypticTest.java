/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.ICrypt;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;
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
public class CrypticTest {
    
    @Autowired
    private ICrypt iCrypt;
    
    /**
     *
     */
    public CrypticTest() { }

    /**
     * Test of encryptFile method, of class Cryptic.
     * @throws java.io.IOException
     */
    @Test
    public void testEncryptDecryptFile_3args() throws IOException {
        System.out.println("encryptFile");
        String cipherType = "AES/CBC/PKCS5Padding";
        
        File f = new File("test-folder");
        
        Files.createDirectories(f.toPath());
        
        iCrypt.encryptFile(new File("test-encryption-file.txt"), f, cipherType);
        
        ArrayList<File> fileList = getListOfFiles(f);
        
        File crypticFile = null;
        
        File crypticKey = null;
        
        for (File file : fileList)
        {
            if(FilenameUtils.getExtension(file.getPath()).equals("cryptic"))
            {
                crypticFile = file;
            }
            else if(FilenameUtils.getExtension(file.getPath()).equals("cryptickey"))
            {
                crypticKey = file;
            }
        }
        
        iCrypt.decryptFile(crypticFile, f, crypticKey);
        
        fileList = getListOfFiles(f);
        
        File unencryptedFile = null;
        
        for (File file : fileList)
        {
            if(file.getName().contains("unencrypted"))
            {
                unencryptedFile = file;
            }
        }
        
        byte[] encryptedFileByteArray = Files.readAllBytes(new File("test-encryption-file.txt").toPath());
        
        byte[] decryptedFileByteArray = Files.readAllBytes(unencryptedFile.toPath());
        
        Assert.assertArrayEquals(encryptedFileByteArray, decryptedFileByteArray);
        
        getListOfFiles(f).forEach((file) -> {
            file.delete();
        });
        
        f.delete();
    }
    
        /**
     * Test of encryptFile method, of class Cryptic.
     * @throws java.io.IOException
     */
    @Test
    public void testEncryptDecryptFile_4args() throws IOException {
        System.out.println("encryptFile");
        String cipherType = "AES/CBC/PKCS5Padding";
        
        File f = new File("test-folder");
        
        Files.createDirectories(f.toPath());
        
        iCrypt.encryptFile(new File("test-encryption-file.txt"), f, cipherType, (String progressDescription, double progress) -> {
            
            assertTrue(progressDescription.equalsIgnoreCase("encrypting"));
            assertTrue(progress >= 0 && progress <= 100);
        });
        
        ArrayList<File> fileList = getListOfFiles(f);
        
        File crypticFile = null;
        
        File crypticKey = null;
        
        for (File file : fileList)
        {
            if(FilenameUtils.getExtension(file.getPath()).equals("cryptic"))
            {
                crypticFile = file;
            }
            else if(FilenameUtils.getExtension(file.getPath()).equals("cryptickey"))
            {
                crypticKey = file;
            }
        }
        
        iCrypt.decryptFile(crypticFile, f, crypticKey, (String progressDescription, double progress) -> {
            
            assertTrue(progressDescription.equalsIgnoreCase("decrypting"));
            assertTrue(progress >= 0 && progress <= 100);
        });
        
        fileList = getListOfFiles(f);
        
        File unencryptedFile = null;
        
        for (File file : fileList)
        {
            if(file.getName().contains("unencrypted"))
            {
                unencryptedFile = file;
            }
        }
        
        byte[] encryptedFileByteArray = Files.readAllBytes(new File("test-encryption-file.txt").toPath());
        
        byte[] decryptedFileByteArray = Files.readAllBytes(unencryptedFile.toPath());
        
        Assert.assertArrayEquals(encryptedFileByteArray, decryptedFileByteArray);
        
        getListOfFiles(f).forEach((file) -> {
            file.delete();
        });
        
        f.delete();
    }
    
    private ArrayList<File> getListOfFiles(File folderToSearch)
    {
        File[] listOfFiles = folderToSearch.listFiles();
        
        ArrayList<File> fileList = new ArrayList<>();
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileList.add(file);
            }
        }
        
        return fileList;
    }
    
}
