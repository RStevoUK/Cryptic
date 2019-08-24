/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Interfaces;

import javax.crypto.SecretKey;

/**
 *
 * @author Stevo
 */
public interface IKeyGenerator {
    byte[] generateKey(String encryptionType, int keySize);
    SecretKey generateKey(String encryptionType);
}
