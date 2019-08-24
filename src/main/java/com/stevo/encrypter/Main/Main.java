/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.Main;

import com.stevo.encrypter.ConcreteClasses.Cryptic;
import com.stevo.encrypter.InjectionContainer.Config;
import com.stevo.encrypter.Interfaces.ICrypt;
import javax.swing.JFileChooser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Stevo
 */
public class Main {
    
    public static void main(String args[])
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        
        ICrypt crypter = context.getBean(Cryptic.class);
        
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            
            byte[] key1 = crypter.encryptFile(chooser.getSelectedFile(), "C:\\Users\\Stevo\\Desktop\\test-encrypted.cryptic", "AES/CBC/PKCS5Padding");
        
            crypter.decryptFile("C:\\Users\\Stevo\\Desktop\\test-encrypted.cryptic", "C:\\Users\\Stevo\\Desktop\\unencrypted.zip", key1);
        }
    }
}
