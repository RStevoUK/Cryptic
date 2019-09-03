/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.ICrypt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Stevo
 */
public class Crypter {
    
    private final ICrypt crypter;
    
    /**
     * <p>
     * Constructs a new Crypter object, initialises spring dependencies 
     * and initialises an object of type ICrypt.
     * </p>
     */
    public Crypter()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        crypter = context.getBean(Cryptic.class);
    }
    
    /**
     * <p>
     * Gets the ICrypt object stored within this object.
     * </p>
     * @return The ICrypt object.
     */
    public ICrypt getCrypter()
    {
        return crypter;
    }
}
