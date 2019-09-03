/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.cryptic.Interfaces;

import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author Stevo
 */
public interface IInitVectGen {

    /**
     * <p>
     * Generates an initialisation vector of a specified size.
     * </p>
     * @param size The size of the initialisation vector in bytes that 
     * should be generated.
     * @return The initialisation vector specification.
     * @since 1.0
     */
    IvParameterSpec generateInitialisationVector(int size);

    /**
     * <p>
     * Generates an initialisation vector specification using the 
     * byte[] provided.
     * </p>
     * @param iv The initialisation vector to be processed in to 
     * an initialisation vector specification
     * @return The initialisation vector specification.
     * @since 1.0
     */
    IvParameterSpec getInitVectSpec(byte[] iv);
}
