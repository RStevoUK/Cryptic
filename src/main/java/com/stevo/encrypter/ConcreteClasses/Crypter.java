/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.ICrypt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Stevo
 */
public class Crypter {
    
    private final ICrypt crypter;
    
    public Crypter()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        crypter = context.getBean(Cryptic.class);
    }
    
    public ICrypt getCrypter()
    {
        return crypter;
    }
}
