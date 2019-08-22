/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.ICipher;
import com.stevo.encrypter.Interfaces.ICrypt;
import com.stevo.encrypter.Interfaces.IEncryptedObject;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import com.stevo.encrypter.Interfaces.ISecretKeySpec;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stevo
 */

@Component
public class AESEncryption implements ICrypt {

    private final ICipher iCipher;
    
    private final IInitVectGen initVect;
    
    private final ISecretKeySpec iSecretKeySpec;
    
    private final IKeyGenerator iKeyGenerator;
    
    @Autowired
    public AESEncryption(ICipher iCipher, IInitVectGen initVect, 
                         ISecretKeySpec iSecretKeySpec, IKeyGenerator iKeyGenerator)
    {
        this.iCipher = iCipher;
        this.initVect = initVect;
        this.iSecretKeySpec = iSecretKeySpec;
        this.iKeyGenerator = iKeyGenerator;
    }
    
    @Override
    public IEncryptedObject encryptByteArray(byte[] byteArray, String cipherType) {
        
        String[] cipherTypeParsed = cipherType.split("/");
        
        int keySize = selectKeySize(cipherTypeParsed[0]);
        
        try {
            //Get Cipher Instance
            Cipher cipher = iCipher.getCipherInstance(cipherType);
            
            byte[] key = iKeyGenerator.generateKey(cipherTypeParsed[0], keySize);
            
            //Create SecretKeySpec
            SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(key, cipherTypeParsed[0]);
            
            //Create IvParameterSpec
            IvParameterSpec ivSpec = initVect.generateInitialisationVector(16);
            
            //Initialize Cipher for ENCRYPT_MODE
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            
            //Perform Encryption
            byte[] result = cipher.doFinal(byteArray);
            
            return new EncryptedObject(cipherType, key, ivSpec.getIV(), result);
            
        } catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public byte[] decryptByteArray(IEncryptedObject iEncryptedObject) {
        
        try {
            //Get Cipher Instance
            Cipher cipher = iCipher.getCipherInstance(iEncryptedObject.getEncryptionMethod());
            
            //Create SecretKeySpec
            SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(iEncryptedObject.getKey(), iEncryptedObject.getEncryptionMethod().split("/")[0]);
            
            //Create IvParameterSpec
            IvParameterSpec ivSpec = initVect.getInitVectSpec(iEncryptedObject.getInitVect());
            
            //Initialize Cipher for DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            
            //Perform Decryption
            return cipher.doFinal(iEncryptedObject.getResult());
            
        } catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private int selectKeySize(String algorithmUsed)
    {
        switch (algorithmUsed)
        {
            case "AES":
                return 128;
            case "DES":
                return 56;
            default:
                return -1;
        }
    }

    @Override
    public byte[] encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType) {
        
        SecretKey secretKey;
        
        try {
            
            String[] cipherTypeParsed = cipherType.split("/");
            
            int keySize = selectKeySize(cipherTypeParsed[0]);
            
            File file = new File(fileNameToEncrypt);
            
            FileToBytes toBytes = new FileToBytes();
            
            byte[] fileBytes = toBytes.fileToBytes(file);
            
            secretKey = KeyGenerator.getInstance(cipherTypeParsed[0]).generateKey();
            
            Cipher cipher = iCipher.getCipherInstance(cipherType);
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] iv = cipher.getIV();
            
            try (FileOutputStream fileOut = new FileOutputStream(outputFileName);
                    CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
                fileOut.write(iv);
                cipherOut.write(fileBytes);
            } catch (IOException ex) {
                Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return secretKey.getEncoded();
            
        }   catch (NoSuchAlgorithmException
                | InvalidKeyException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public void decryptFile(String filePathToDecrypt, byte[] secretKey) {
        
        String content;
 
        Cipher cipher = iCipher.getCipherInstance("AES/CBC/PKCS5Padding");
        
        SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(secretKey, "AES");
        
        try (FileInputStream fileIn = new FileInputStream(filePathToDecrypt)) {
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    
                ) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                
                int len;
                byte[] buffer = new byte[4096];
                while ((len = cipherIn.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, len);
                }
                
                baos.flush();
                
                byte[] cipherByteArray = baos.toByteArray(); // get the byte array
                
                writeBytesToFile(cipherByteArray);
                
                }
            } catch (FileNotFoundException excep) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, excep);
        } catch (IOException | InvalidKeyException | InvalidAlgorithmParameterException exception) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    private void writeBytesToFile(byte[] bytesToWrite)
    {
        File file = new File("C:\\Users\\Stevo\\Desktop\\unencrypted.jpg");
         
        FileOutputStream fos = null;
 
        try {
             
            fos = new FileOutputStream(file);
             
            // Writes bytes from the specified byte array to this file output stream 
            fos.write(bytesToWrite);
 
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        finally {
            // close the streams using close method
            try {
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }
    }
}
