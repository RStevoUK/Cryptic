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
public interface IDecrypt {

    /**
     * <p>
     * Decrypts a specified file, outputting the result to a specified 
     * location.
     * </p>
     * @param fileToDecrypt The File object to be decrypted.
     * @param filePathResult The File object containing the Folder path to 
     * output the decrypted file to.
     * @param secretKeyFile The File containing the secret key needed 
     * to decrypt the File passed in the first parameter.
     * @since 1.0
     */
    void decryptFile(File fileToDecrypt, File filePathResult, File secretKeyFile);

    /**
     * <p>
     * Decrypts a specified file, outputting the result to a specified 
     * location whilst updating the user on progress made throughout 
     * the decryption process.
     * </p>
     * @param fileToDecrypt The File object to be decrypted.
     * @param filePathResult The File object containing the Folder path to 
     * output the decrypted file to.
     * @param secretKeyFile The File containing the secret key needed 
     * to decrypt the File passed in the first parameter.
     * @param progress The OnProgressListener functional interface to update 
     * the called of progress made during decryption.
     * @since 1.0
     */
    void decryptFile(File fileToDecrypt, File filePathResult, File secretKeyFile, OnProgressListener progress);
}
