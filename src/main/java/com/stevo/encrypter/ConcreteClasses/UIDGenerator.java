/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.IUIDGenerator;
import java.util.UUID;

/**
 *
 * @author Stevo
 */
class UIDGenerator implements IUIDGenerator {

    @Override
    public String generateUID() {
        return UUID.randomUUID().toString();
    }
    
}
