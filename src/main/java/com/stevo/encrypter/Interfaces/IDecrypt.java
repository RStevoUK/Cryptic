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
public interface IDecrypt {
    byte[] decryptByteArray(IEncryptedObject iEncryptedObject);
    void decryptFile(String filePathToDecrypt, byte[] secretKey);
}
