/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Interfaces;

import java.io.File;

/**
 *
 * @author Stevo
 */
public interface IEncrypt {
    IEncryptedObject encryptByteArray(byte[] plaintext, String cipherType);
    void encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType);
    void encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType, OnProgressListener progress);
    void encryptFile(File fileToEncrypt, File outputFileName, String cipherType);
    void encryptFile(File fileToEncrypt, File outputFileName, String cipherType, OnProgressListener progressListener);
}
