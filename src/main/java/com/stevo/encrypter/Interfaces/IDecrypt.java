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
public interface IDecrypt {
    void decryptFile(File fileToDecrypt, File filePathResult, File secretKeyFile);
    void decryptFile(File fileToDecrypt, File filePathResult, File secretKeyFile, OnProgressListener progress);
}
