/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.Interfaces;

import java.io.File;

/**
 *
 * @author Stevo
 */
public interface IEncrypt {

    /**
     * <p>
     * Encrypts a specified file based on the cipher type specified, 
     * outputting the result to a specified location.
     * </p>
     * @param fileToEncrypt The File object to be encrypted.
     * @param outputFileName The File object containing the Folder path to 
     * output the encrypted file and secret key to.
     * @param cipherType The cipherType that should be used for encrypting 
     * the specified File.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html">See here for more information on 
     * the standard format for the cipherType parameter.</a>
     * @since 1.0
     */
    void encryptFile(File fileToEncrypt, File outputFileName, String cipherType);

    /**
     * <p>
     * Encrypts a specified file based on the cipher type specified, 
     * outputting the result to a specified location.
     * </p>
     * @param fileToEncrypt The File object to be encrypted.
     * @param outputFileName The File object containing the Folder path to 
     * output the encrypted file and secret key to.
     * @param cipherType The cipherType that should be used for encrypting 
     * @param progressListener The OnProgressListener functional interface to update 
     * the called of progress made during decryption.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html">See here for more information on 
     * the standard format for the cipherType parameter.</a>
     * @since 1.0
     */
    void encryptFile(File fileToEncrypt, File outputFileName, String cipherType, OnProgressListener progressListener);
}
