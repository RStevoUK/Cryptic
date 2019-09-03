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
@FunctionalInterface
public interface OnProgressListener {

    /**
     * <p>
     * Provides updates on the encryption/decryption progress.
     * </p>
     * @param progressDescription A description of the current state of 
     * progress of encryption/decryption.
     * @param progress The current progress of the encryption/decryption 
     * as a percentage.
     */
    void onProgress(String progressDescription, double progress);
}
