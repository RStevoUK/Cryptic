/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.ConcreteClasses.CipherSelector;
import com.stevo.encrypter.ConcreteClasses.FileExtensionParser;
import com.stevo.encrypter.ConcreteClasses.FileNameGenerator;
import com.stevo.encrypter.ConcreteClasses.SecretKeyGenerator;
import com.stevo.encrypter.ConcreteClasses.SecretKeySpecService;
import com.stevo.encrypter.ConcreteClasses.UIDGenerator;
import com.stevo.encrypter.ConcreteClasses.VectorGenerator;
import com.stevo.encrypter.Interfaces.ICipher;
import com.stevo.encrypter.Interfaces.IFileExtensionParser;
import com.stevo.encrypter.Interfaces.IFileNameGenerator;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import com.stevo.encrypter.Interfaces.ISecretKeySpec;
import com.stevo.encrypter.Interfaces.IUIDGenerator;
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
    public IUIDGenerator getUIDGenerator()
    {
        return new UIDGenerator();
    }
    
    @Bean
    public IFileExtensionParser getFileExtensionParser()
    {
        return new FileExtensionParser();
    }
    
    @Bean
    public IFileNameGenerator getFileNameGenerator(IUIDGenerator getUIDGenerator)
    {
        return new FileNameGenerator(getUIDGenerator);
    }
}
