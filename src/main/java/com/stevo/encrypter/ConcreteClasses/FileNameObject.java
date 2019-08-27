/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IFileNameObject;

/**
 *
 * @author Stevo
 */
class FileNameObject implements IFileNameObject {

    private final String keyFileName;
    
    private final String cryptedFileName;
    
    public FileNameObject(String keyName, String cryptedName)
    {
        this.keyFileName = keyName;
        this.cryptedFileName = cryptedName;
    }
    
    @Override
    public String getKeyFileName() {
        return keyFileName;
    }

    @Override
    public String getCryptedFileName() {
        return cryptedFileName;
    }
}
