/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.Interfaces;

import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Stevo
 */
public interface ISecretKeySpec {
    
    /**
     * <p>
     * Gets a new SecretKeySpec object based on the key and cipher type 
     * specified.
     * </p>
     * @param key The key used to create a SecretKeySpec.
     * @param cipherType The cipher type used to create a SecretKeySpec.
     * @return The SecretKeySpec object to be used for Encryption/Decryption.
     * @since 1.0
     */
    SecretKeySpec getSecretKeySpec(byte[] key, String cipherType);
}
