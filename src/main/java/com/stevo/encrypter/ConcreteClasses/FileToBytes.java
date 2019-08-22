/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IFileToBytes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stevo
 */
public class FileToBytes implements IFileToBytes {

    @Override
    public byte[] fileToBytes(File file) {
        
        try {
            return Files.readAllBytes(file.toPath());
                    } catch (IOException ex) {
            Logger.getLogger(FileToBytes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
