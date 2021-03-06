/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.ICipher;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Stevo
 */

class CipherSelector implements ICipher {

    @Override
    public Cipher getCipherInstance(String cipherType) {
        
        try {
            return Cipher.getInstance(cipherType);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException ex) {
            Logger.getLogger(CipherSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
