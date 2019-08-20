/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Main;

import com.stevo.encrypter.ConcreteClasses.AESEncryption;
import com.stevo.encrypter.ConcreteClasses.SecretKeyGenerator;
import com.stevo.encrypter.ConcreteClasses.VectorGenerator;
import com.stevo.encrypter.Interfaces.ICrypt;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import java.util.Base64;

/**
 *
 * @author Stevo
 */
public class Main {
    
    public static void main(String args[])
    {
        IKeyGenerator keyGen = new SecretKeyGenerator();
        byte[] key = keyGen.generateKey("AES", 128);
        
        IInitVectGen vecGen = new VectorGenerator();
        byte[] initVect = vecGen.generateInitialisationVector();
        
        String textToEncrypt = "Once upon a time I had a line of text.";
        
        System.out.println("Key Used For Encryption : " + Base64.getEncoder().encodeToString(key));
        
        System.out.println("Initialisation Vector Used For Encryption : " + Base64.getEncoder().encodeToString(initVect));
        
        System.out.println("Text Before Encryption : " + textToEncrypt);
        
        ICrypt crypter = new AESEncryption();
        byte[] result = crypter.encrypt(textToEncrypt.getBytes(), key, initVect);
        
        System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(result));
        
        byte[] decryptResult = crypter.decrypt(result, key, initVect);
        
        System.out.println("Decrypted Test : " + new String(decryptResult));
    }
}
