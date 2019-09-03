/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.ConcreteClasses;

import com.stevo.cryptic.Interfaces.IFileNameGenerator;
import com.stevo.cryptic.Interfaces.IFileNameObject;
import com.stevo.cryptic.Interfaces.IUIDGenerator;

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
