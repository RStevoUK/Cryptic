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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.filechooser.FileSystemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stevo
 */

@Component
public class Cryptic implements ICrypt {

    private final ICipher iCipher;
    
    private final IInitVectGen initVect;
    
    private final ISecretKeySpec iSecretKeySpec;
    
    private final IKeyGenerator iKeyGenerator;
    
    @Autowired
    public Cryptic(ICipher iCipher, IInitVectGen initVect, 
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
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
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
            case "DESede":
                return 168;
            default:
                return -1;
        }
    }

    @Override
    public byte[] encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType) {
        
        return encryptFile(new File(fileNameToEncrypt), outputFileName, cipherType);
    }

    @Override
    public void decryptFile(String filePathToDecrypt, String filePathResult, byte[] secretKey) {
        
        decryptFile(new File(filePathToDecrypt), filePathResult, secretKey);
    }
    
    private String getFilePathToWrite()
    {
        String s = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        
        return s += "\\" + UUID.randomUUID();
    }

    @Override
    public byte[] encryptFile(File fileToEncrypt, String outputFileName, String cipherType) {
        
        SecretKey secretKey;
        
        try {
            
            //Split the cipher String into it's corresponding sections
            String[] cipherTypeParsed = cipherType.split("/");
            
            //Generate a key based on the specified algo
            secretKey = iKeyGenerator.generateKey(cipherTypeParsed[0]);
            
            //Get the cipher to be used for encryption
            Cipher cipher = iCipher.getCipherInstance(cipherType);
            
            //Set the encryption mode for the cypher, 
            //passing the private key to be encrypted with
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            //Create a 64 bit byte array to be used as a header 
            //for the encrypted file to store the encryption type used
            byte[] enType = new byte[64];
            
            //Convert the String representation of the chosen 
            //encryption type and copy to the newly created byte array
            System.arraycopy(cipherType.getBytes(), 0, enType, 0, cipherType.getBytes().length);
            
            //Get the initialisation vector used for this encryption, 
            //to be stored as a file header
            byte[] iv = cipher.getIV();
            
            //Initialise the File Input/Output streams to be used to read 
            //and write data along with the CipherInputStream 
            //convenience method to encrypt on reading
            try (   FileInputStream fis = new FileInputStream(fileToEncrypt);
                    FileOutputStream fileOut = new FileOutputStream(outputFileName);
                    CipherInputStream cis = new CipherInputStream(fis, cipher);) {
                
                //Write the encryption type header to the file
                fileOut.write(enType);
                
                //Write the initilisation vector header to the file
                fileOut.write(iv);
                
                //Initialise variable to be used during buffering
                int bytes;
                
                //Initalise byte array for buffer to read 4096 byte blocks
                byte[] buffer = new byte[4096];
                
                //Keep reading 4096 bytes from the input stream 
                //encrypting them during input and writing them 
                //to the output stream
                while((bytes = cis.read(buffer)) != -1) {
                  fileOut.write(buffer, 0, bytes);
                }
                
                //Clean up just for safekeeping
                fileOut.flush();
                fileOut.close();
                cis.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return secretKey.getEncoded();
            
        }   catch (InvalidKeyException ex) {
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private int selectIVSize(String algorithmUsed)
    {
        switch (algorithmUsed)
        {
            case "AES":
                return 16;
            case "DES":
                return 8;
            case "DESede":
                return 8;
            default:
                return -1;
        }
    }
        
    @Override
    public void decryptFile(File fileToDecrypt, String filePathResult, byte[] secretKey) {
        
        //Initialise the file byte stream
        try (FileInputStream fileIn = new FileInputStream(fileToDecrypt)) {
            
            //Parse the encryption header to get the encryption type 
            //that the file was encrypted with
            String encryptionType = parseEncryptionType(fileIn);
            
            //Parse the initialisation vector header to get 
            //the vector that the file was encrypted with
            byte[] fileIv = parseInitialisationVector(fileIn, encryptionType);
            
            //Get the fully initialisaed cipher
            Cipher cipher = initialiseCipherInstance(encryptionType, secretKey, fileIv);

            //Initialise the cipher input stream for decrypting bytes as 
            //they are read, passing the input stream to be decrypted and 
            //the cipher to be used.
            //Initialise the buffered output stream so that big files can be 
            //successfully written.
            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(filePathResult));   
                ) {

                //Initialise variable to be used during buffering
                int bytes;
                
                //Initalise byte array for buffer to read 4096 byte blocks
                byte[] buffer = new byte[4096];
                
                //Keep reading 4096 bytes from the input stream 
                //decrypting them during input and writing them 
                //to the output stream
                while ((bytes = cipherIn.read(buffer, 0, buffer.length)) != -1) {
                    os.write(buffer, 0, bytes);
                }
                
                //Clean up just for safekeeping
                os.close();
                
                }
            } catch (FileNotFoundException excep) {
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, excep);
        } catch (IOException | InvalidKeyException | InvalidAlgorithmParameterException exception) {
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    private String parseEncryptionType(FileInputStream fileIn) throws IOException
    {
        byte[] enType = new byte[64];

        fileIn.read(enType);

        return new String(enType);
    }
    
    private byte[] parseInitialisationVector(FileInputStream fileIn, String encryptionType) throws IOException
    {
        byte[] fileIv = new byte[selectIVSize(encryptionType.split("/")[0])];

        fileIn.read(fileIv);
        
        return fileIv;
    }
    
    private Cipher initialiseCipherInstance(String encryptionType, byte[] secretKey, byte[] fileIv) throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        Cipher cipher = iCipher.getCipherInstance(encryptionType);

        SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(secretKey, encryptionType.split("/")[0]);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(fileIv));
        
        return cipher;
    }
}
