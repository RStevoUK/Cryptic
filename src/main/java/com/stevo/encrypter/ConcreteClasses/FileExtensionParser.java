/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IFileExtensionParser;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Stevo
 */
class FileExtensionParser implements IFileExtensionParser {

    @Override
    public String parseFileExtension(String fileName) {
        return "." + FilenameUtils.getExtension(fileName);
    }
    
}
