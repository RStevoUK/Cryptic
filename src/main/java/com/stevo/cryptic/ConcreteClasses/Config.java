/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.ICipher;
import com.stevo.cryptic.Interfaces.IFileExtensionParser;
import com.stevo.cryptic.Interfaces.IFileNameGenerator;
import com.stevo.cryptic.Interfaces.IInitVectGen;
import com.stevo.cryptic.Interfaces.IKeyGenerator;
import com.stevo.cryptic.Interfaces.ISecretKeySpec;
import com.stevo.cryptic.Interfaces.IUIDGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Stevo
 */
@Configuration
@ComponentScan("com.stevo.cryptic")
public class Config {
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any ICipher dependencies.
     * </p>
     * @return An initialised ICipher object.
     */
    @Bean
    public ICipher cipherSelector()
    {
        return new CipherSelector();
    }
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any IInitVectGen dependencies.
     * </p>
     * @return An initialised IInitVectGen object.
     */
    @Bean
    public IInitVectGen initVectGenerator()
    {
        return new VectorGenerator();
    }
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any ISecretKeySpec dependencies.
     * </p>
     * @return An initialised ISecretKeySpec object.
     */
    @Bean
    public ISecretKeySpec getSecretKeySpec()
    {
        return new SecretKeySpecService();
    }
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any IKeyGenerator dependencies.
     * </p>
     * @return An initialised IKeyGenerator object.
     */
    @Bean
    public IKeyGenerator getKeyGenerator()
    {
        return new SecretKeyGenerator();
    }
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any IUIDGenerator dependencies.
     * </p>
     * @return An initialised IUIDGenerator object.
     */
    @Bean
    public IUIDGenerator getUIDGenerator()
    {
        return new UIDGenerator();
    }
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any IFileExtensionParser dependencies.
     * </p>
     * @return An initialised IFileExtensionParser object.
     */
    @Bean
    public IFileExtensionParser getFileExtensionParser()
    {
        return new FileExtensionParser();
    }
    
    /**
     * <p>
     * A Spring Framework Bean to resolve any IFileNameGenerator dependencies.
     * </p>
     * @param getUIDGenerator An object of type IUIDGenerator.
     * @return An initialised IFileNameGenerator object.
     */
    @Bean
    public IFileNameGenerator getFileNameGenerator(IUIDGenerator getUIDGenerator)
    {
        return new FileNameGenerator(getUIDGenerator);
    }
}
