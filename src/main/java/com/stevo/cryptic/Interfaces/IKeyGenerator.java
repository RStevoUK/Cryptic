/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.Interfaces;

import javax.crypto.SecretKey;

/**
 *
 * @author Stevo
 */
public interface IKeyGenerator {

    /**
     * <p>
     * Generates a SecretKey object based on the provided 
     * encryption type and key size.
     * </p>
     * @param encryptionType The type of encryption the key will be 
     * used for.
     * @param keySize The size of the key to be generated.
     * @return The SecretKey object to be used for Encryption/Decryption.
     * @since 1.0
     */
    SecretKey generateKey(String encryptionType, int keySize);

    /**
     * <p>
     * Generates a SecretKey object based on the provided 
     * encryption type.
     * </p>
     * @param encryptionType The type of encryption the key will be 
     * used for.
     * @return The SecretKey object to be used for Encryption/Decryption.
     * @since 1.0
     */
    SecretKey generateKey(String encryptionType);
}
