/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Interfaces;

/**
 *
 * @author Stevo
 */
public interface IEncrypt {
    IEncryptedObject encryptByteArray(byte[] plaintext, String cipherType);
    byte[] encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType);
}
