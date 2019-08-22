/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.InjectionContainer;

import com.stevo.encrypter.ConcreteClasses.CipherSelector;
import com.stevo.encrypter.ConcreteClasses.SecretKeyGenerator;
import com.stevo.encrypter.ConcreteClasses.SecretKeySpecService;
import com.stevo.encrypter.ConcreteClasses.VectorGenerator;
import com.stevo.encrypter.Interfaces.ICipher;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import com.stevo.encrypter.Interfaces.ISecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Stevo
 */
@Configuration
@ComponentScan("com.stevo.encrypter")
public class Config {
    
    @Bean
    public ICipher cipherSelector()
    {
        return new CipherSelector();
    }
    
    @Bean
    public IInitVectGen initVectGenerator()
    {
        return new VectorGenerator();
    }
    
    @Bean
    public ISecretKeySpec getSecretKeySpec()
    {
        return new SecretKeySpecService();
    }
    
    @Bean
    public IKeyGenerator getKeyGenerator()
    {
        return new SecretKeyGenerator();
    }
    
    @Bean
    public byte[] generateAES128()
    {
        SecretKeyGenerator keyGen = new SecretKeyGenerator();
        return keyGen.generateKey("AES", 128);
    }
    
    @Bean
    public SecretKeySpec getAESKey(byte[] generateAES128)
    {
        return new SecretKeySpec(generateAES128, "AES");
    }
    
    @Bean
    public byte[] getSixteenBytes()
    {
        return new byte[16];
    }
    
    @Bean
    public IvParameterSpec getIvSpec(byte[] getSixteenBytes)
    {
        SecureRandom random = new SecureRandom();
        random.nextBytes(getSixteenBytes);
        
        return new IvParameterSpec(getSixteenBytes);
    }
}
