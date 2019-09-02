/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Main;

import com.stevo.encrypter.ConcreteClasses.Cryptic;
import com.stevo.encrypter.ConcreteClasses.Config;
import com.stevo.encrypter.Interfaces.ICrypt;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
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
        outputDirectory.setDialogTitle("Choose Encrypted File Output Folder");
        outputDirectory.showOpenDialog(null);
        
        Files.createDirectories(outputDirectory.getSelectedFile().toPath());
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose file to encrypt");
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            
            crypter.encryptFile(chooser.getSelectedFile(), outputDirectory.getSelectedFile(), "AES/CBC/PKCS5Padding", (String description, double progress) -> {
                
                System.out.println(description + " " + progress);
            });
            
            JFileChooser fileToDecryptChooser = new JFileChooser();
            fileToDecryptChooser.setDialogTitle("Choose file to decrypt");
            fileToDecryptChooser.showOpenDialog(null);
            
            JFileChooser directoryChooser = new JFileChooser();
            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            directoryChooser.setDialogTitle("Choose output folder for decrypted file");
            directoryChooser.showOpenDialog(null);
            
            JFileChooser keyFileChooser = new JFileChooser();
            keyFileChooser.setDialogTitle("Choose key to decrypt file");
            keyFileChooser.showOpenDialog(null);
            
            crypter.decryptFile(fileToDecryptChooser.getSelectedFile(), directoryChooser.getSelectedFile(), keyFileChooser.getSelectedFile(), (String description, double progress) -> {
                
                System.out.println(description + " " + progress);
            });
        }
    }
}
