/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IInitVectGen;
import java.security.SecureRandom;

/**
 *
 * @author Stevo
 */
public class VectorGenerator implements IInitVectGen {

    @Override
    public byte[] generateInitialisationVector() {
        
        byte[] initVect = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(initVect);
        
        return initVect;
    }
    
}
