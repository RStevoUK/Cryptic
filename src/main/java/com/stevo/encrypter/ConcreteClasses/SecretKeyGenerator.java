/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IKeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Stevo
 */
public class SecretKeyGenerator implements IKeyGenerator {

    @Override
    public byte[] generateKey(String encryptionType, int keySize) {
        
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
            keyGenerator.init(keySize);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecretKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public SecretKey generateKey(String encryptionType) {
        
        try {
            return KeyGenerator.getInstance(encryptionType).generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecretKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
