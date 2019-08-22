/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IInitVectGen;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author Stevo
 */
public class VectorGenerator implements IInitVectGen {

    @Override
    public IvParameterSpec generateInitialisationVector(int size) {
        
        byte[] initVect = new byte[size];
        SecureRandom random = new SecureRandom();
        random.nextBytes(initVect);
        
        return new IvParameterSpec(initVect);
    }

    @Override
    public IvParameterSpec getInitVectSpec(byte[] iv) {
        
        return new IvParameterSpec(iv);
    }
    
}
