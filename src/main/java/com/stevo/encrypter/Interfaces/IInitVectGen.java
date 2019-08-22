/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Interfaces;

import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author Stevo
 */
public interface IInitVectGen {
    IvParameterSpec generateInitialisationVector(int size);
    IvParameterSpec getInitVectSpec(byte[] iv);
}
