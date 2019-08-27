/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IFileNameGenerator;
import com.stevo.encrypter.Interfaces.IFileNameObject;
import com.stevo.encrypter.Interfaces.IUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stevo
 */
class FileNameGenerator implements IFileNameGenerator {

    private final IUIDGenerator iUIDGenerator;
    
    public FileNameGenerator(IUIDGenerator iUIDGenerator)
    {
        this.iUIDGenerator = iUIDGenerator;
    }

    @Override
    public IFileNameObject generateFileNameObject() {
        String UID = iUIDGenerator.generateUID();
        String keyName = "Cryptic-Key-" + UID + ".cryptickey";
        String crypticFileName = "Cryptic-File-" + UID + ".cryptic";
        
        return new FileNameObject(keyName, crypticFileName);
    }
}
