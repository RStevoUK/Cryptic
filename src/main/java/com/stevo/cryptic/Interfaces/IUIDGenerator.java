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
public interface IUIDGenerator {

    /**
     * <p>
     * Generates a unique identifier.
     * </p>
     * @return A newly generated unique identifier contained within 
     * a String object.
     */
    String generateUID();
}
