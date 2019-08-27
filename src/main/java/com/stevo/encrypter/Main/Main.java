/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Main;

import com.stevo.encrypter.ConcreteClasses.Cryptic;
import com.stevo.encrypter.ConcreteClasses.Config;
import com.stevo.encrypter.Interfaces.ICrypt;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Stevo
 */
public class Main {
    
    public static void main(String args[]) throws IOException
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        ICrypt crypter = context.getBean(Cryptic.class);
        
        JFileChooser outputDirectory = new JFileChooser();
        outputDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        outputDirectory.showOpenDialog(null);
        
        Files.createDirectories(outputDirectory.getSelectedFile().toPath());
        
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            
            crypter.encryptFile(chooser.getSelectedFile(), outputDirectory.getSelectedFile(), "AES/CBC/PKCS5Padding", (String description, double progress) -> {
                
                System.out.println(description + " " + progress);
            });
            
            JFileChooser fileToDecryptChooser = new JFileChooser();
            fileToDecryptChooser.showOpenDialog(null);
            
            JFileChooser directoryChooser = new JFileChooser();
            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            directoryChooser.showOpenDialog(null);
            
            JFileChooser keyFileChooser = new JFileChooser();
            keyFileChooser.showOpenDialog(null);
            
            crypter.decryptFile(fileToDecryptChooser.getSelectedFile(), directoryChooser.getSelectedFile(), keyFileChooser.getSelectedFile(), (String description, double progress) -> {
                
                System.out.println(description + " " + progress);
            });
        }
    }
}
