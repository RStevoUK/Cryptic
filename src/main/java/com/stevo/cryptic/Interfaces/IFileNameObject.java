/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.Interfaces;

/**
 *
 * @author Stevo
 */
public interface IFileNameObject {

    /**
     * <p>
     * Gets the file name of the key stored within the object 
     * implementing this interface.
     * </p>
     * @return The file name of the key stored within the object implementing 
     * this interface.
     * @since 1.0
     */
    String getKeyFileName();

    /**
     * <p>
     * Gets the file name of the crypted object stored within the object 
     * implementing this interface.
     * </p>
     * @return The file name of the crypted object stored within the 
     * object implementing this interface.
     * @since 1.0
     */
    String getCryptedFileName();
}
