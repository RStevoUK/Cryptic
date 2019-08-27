/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.ICipher;
import com.stevo.encrypter.Interfaces.IFileExtensionParser;
import com.stevo.encrypter.Interfaces.IFileNameGenerator;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import com.stevo.encrypter.Interfaces.ISecretKeySpec;
import com.stevo.encrypter.Interfaces.IUIDGenerator;
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
