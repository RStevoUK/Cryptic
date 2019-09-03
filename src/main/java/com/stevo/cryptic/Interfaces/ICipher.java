/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.Interfaces;

import javax.crypto.Cipher;

/**
 *
 * @author Stevo
 */
public interface ICipher {

    /**
     * <p>
     * Accepts a cipher type defined as a String and returns an instance 
     * of javax.crypto.Cipher.
     * </p>
     * @param cipherType The type of cipher that should be instantiated.
     * @return 
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html">See here for more information on 
     * the Cipher class and a standard format for the cipherType parameter.</a>
     * @since 1.0
     */
    Cipher getCipherInstance(String cipherType);
}
