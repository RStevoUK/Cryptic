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
public interface IFileExtensionParser {

    /**
     * <p>
     * Parses a file extension from a String object.
     * </p>
     * @param fileName The String to be parsed for it's extension.
     * @return The String object containing the parsed extension.
     * @since 1.0
     */
    String parseFileExtension(String fileName);
}
