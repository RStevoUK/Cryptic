/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.ISecretKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Stevo
 */
public class SecretKeySpecService implements ISecretKeySpec {

    @Override
    public SecretKeySpec getSecretKeySpec(byte[] key, String cipherType) {
        
        return new SecretKeySpec(key, cipherType.split("/")[0]);
    }
    
    
}
