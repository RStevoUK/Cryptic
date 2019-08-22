/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IEncryptedObject;

/**
 *
 * @author Stevo
 */
public class EncryptedObject implements IEncryptedObject {
    
    private final String encryptionMethod;
    
    private final byte[] key;
    
    private final byte[] initVect;
    
    private final byte[] result;

    public EncryptedObject(String encryptionMethod, byte[] key, byte[] initVect, byte[] result)
    {
        this.encryptionMethod = encryptionMethod;
        this.key = key;
        this.initVect = initVect;
        this.result = result;
    }

    /**
     * @return the encryptionMethod
     */
    @Override
    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    /**
     * @return the key
     */
    @Override
    public byte[] getKey() {
        return key;
    }

    /**
     * @return the initVect
     */
    @Override
    public byte[] getInitVect() {
        return initVect;
    }

    /**
     * @return the result
     */
    @Override
    public byte[] getResult() {
        return result;
    }
    
    
}
