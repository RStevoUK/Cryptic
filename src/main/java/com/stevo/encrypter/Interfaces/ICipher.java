/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Interfaces;

import javax.crypto.Cipher;

/**
 *
 * @author Stevo
 */
public interface ICipher {
    Cipher getCipherInstance(String cipherType);
}
