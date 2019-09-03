/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.IFileExtensionParser;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Stevo
 */
class FileExtensionParser implements IFileExtensionParser {

    @Override
    public String parseFileExtension(String fileName) {
        
        String ext = FilenameUtils.getExtension(fileName);
        
        if(!ext.isEmpty())
            return "." + FilenameUtils.getExtension(fileName);
        else
            return null;
    }
    
}
