/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Main;

import com.stevo.encrypter.ConcreteClasses.AESEncryption;
import com.stevo.encrypter.ConcreteClasses.FileToBytes;
import com.stevo.encrypter.InjectionContainer.Config;
import com.stevo.encrypter.Interfaces.ICrypt;
import com.stevo.encrypter.Interfaces.IEncryptedObject;
import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Stevo
 */
public class Main {
    
    public static void main(String args[])
    {
        File file = new File("C:\\Users\\Stevo\\Desktop\\test.jpg");
        
        FileToBytes toBytes = new FileToBytes();
        
        byte[] fileBytes = toBytes.fileToBytes(file);
        
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        String textToEncrypt = "Once upon a time I had a line of text.";
        
        System.out.println("Text Before Encryption : " + textToEncrypt);
        
        ICrypt crypter = context.getBean(AESEncryption.class);
        
        IEncryptedObject result = crypter.encryptByteArray(fileBytes, "AES/CBC/PKCS5Padding");
        
        byte[] key1 = crypter.encryptFile("C:\\Users\\Stevo\\Desktop\\test.jpg", "C:\\Users\\Stevo\\Desktop\\test-encrypted.jpg", "AES/CBC/PKCS5Padding");
        
        System.out.println("Key Used For Encryption : " + Base64.getEncoder().encodeToString(result.getKey()));
        
        System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(result.getResult()));
        
        byte[] decryptResult = crypter.decryptByteArray(result);
        
        crypter.decryptFile("C:\\Users\\Stevo\\Desktop\\test-encrypted.jpg", key1);
        
        System.out.println("Decrypted Text : " + new String(decryptResult));
        
        Arrays.equals(fileBytes, decryptResult);
        
        if(Arrays.equals(fileBytes, decryptResult))
        {
            System.out.println("I'm equal");
        }
    }
}
