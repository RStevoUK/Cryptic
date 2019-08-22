/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Main;

import com.stevo.encrypter.ConcreteClasses.AESEncryption;
import com.stevo.encrypter.ConcreteClasses.SecretKeyGenerator;
import com.stevo.encrypter.ConcreteClasses.VectorGenerator;
import com.stevo.encrypter.InjectionContainer.Config;
import com.stevo.encrypter.Interfaces.ICrypt;
import com.stevo.encrypter.Interfaces.IEncryptedObject;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import java.util.Base64;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Stevo
 */
public class Main {
    
    @Autowired
    public BeanFactory factory;
    
    public static void main(String args[])
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        String textToEncrypt = "Once upon a time I had a line of text.";
        
        System.out.println("Text Before Encryption : " + textToEncrypt);
        
        ICrypt crypter = context.getBean(AESEncryption.class);
        
        IEncryptedObject result = crypter.encrypt(textToEncrypt.getBytes(), "AES/CBC/PKCS5Padding");
        
        System.out.println("Key Used For Encryption : " + Base64.getEncoder().encodeToString(result.getKey()));
        
        System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(result.getResult()));
        
        byte[] decryptResult = crypter.decrypt(result);
        
        System.out.println("Decrypted Text : " + new String(decryptResult));
    }
}
