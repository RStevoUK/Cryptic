/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevo.encrypter.ConcreteClasses;

import com.stevo.encrypter.Interfaces.ICipher;
import com.stevo.encrypter.Interfaces.ICrypt;
import com.stevo.encrypter.Interfaces.IEncryptedObject;
import com.stevo.encrypter.Interfaces.IFileExtensionParser;
import com.stevo.encrypter.Interfaces.IFileNameGenerator;
import com.stevo.encrypter.Interfaces.IFileNameObject;
import com.stevo.encrypter.Interfaces.IInitVectGen;
import com.stevo.encrypter.Interfaces.IKeyGenerator;
import com.stevo.encrypter.Interfaces.ISecretKeySpec;
import com.stevo.encrypter.Interfaces.OnProgressListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Stevo
 */

@Component
class Cryptic implements ICrypt {

    private final ICipher iCipher;
    
    private final IInitVectGen initVect;
    
    private final ISecretKeySpec iSecretKeySpec;
    
    private final IKeyGenerator iKeyGenerator;
    
    private final IFileExtensionParser iFileExtensionParser;
    
    private final IFileNameGenerator iFileNameGenerator;
    
    @Autowired
    private Cryptic(ICipher iCipher, 
                   IInitVectGen initVect, 
                   ISecretKeySpec iSecretKeySpec, 
                   IKeyGenerator iKeyGenerator, 
                   IFileExtensionParser iFileExtensionParser, 
                   IFileNameGenerator iFileNameGenerator)
    {
        this.iCipher = iCipher;
        this.initVect = initVect;
        this.iSecretKeySpec = iSecretKeySpec;
        this.iKeyGenerator = iKeyGenerator;
        this.iFileExtensionParser = iFileExtensionParser;
        this.iFileNameGenerator = iFileNameGenerator;
    }
    
    @Override
    public IEncryptedObject encryptByteArray(byte[] byteArray, String cipherType) {
        
        String[] cipherTypeParsed = cipherType.split("/");
        
        int keySize = selectKeySize(cipherTypeParsed[0]);
        
        try {
            //Get Cipher Instance
            Cipher cipher = iCipher.getCipherInstance(cipherType);
            
            byte[] key = iKeyGenerator.generateKey(cipherTypeParsed[0], keySize).getEncoded();
            
            //Create SecretKeySpec
            SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(key, cipherTypeParsed[0]);
            
            //Create IvParameterSpec
            IvParameterSpec ivSpec = initVect.generateInitialisationVector(selectIVSize(cipherTypeParsed[0]));
            
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
    public void encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType) {
        
        encryptFile(new File(fileNameToEncrypt), new File(outputFileName), cipherType);
    }

    @Override
    public void decryptFile(String filePathToDecrypt, File filePathResult, File secretKeyFile) {
        
        decryptFile(new File(filePathToDecrypt), filePathResult, secretKeyFile);
    }

    @Override
    public void encryptFile(File fileToEncrypt, File outputFileName, String cipherType) {
        
        encryptFile(fileToEncrypt, outputFileName, cipherType, null);
    }

    private double calculateProgress(double bytesRead, double fileSize)
    {
        double progress = (bytesRead / fileSize) * 100;
        
        DecimalFormat df = new DecimalFormat("00.00");
        
        progress = Double.valueOf(df.format(progress));
        
        if(progress > 100)
            return 100;
        else
            return progress;
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
    public void decryptFile(File fileToDecrypt, File filePathResult, File secretKeyFile) {
        
        decryptFile(fileToDecrypt, filePathResult, secretKeyFile, null);
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
    
    private byte[] parseFileExtension(FileInputStream fileIn) throws IOException
    {
        byte[] fileExt = new byte[8];

        fileIn.read(fileExt);
        
        return fileExt;
    }
    
    private Cipher initialiseCipherInstance(String encryptionType, byte[] secretKey, byte[] fileIv, int mode) throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        Cipher cipher = iCipher.getCipherInstance(encryptionType);

        SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(secretKey, encryptionType.split("/")[0]);

        cipher.init(mode, keySpec, new IvParameterSpec(fileIv));
        
        return cipher;
    }

    @Override
    public void encryptFile(File fileToEncrypt, File outputFileName, String cipherType, OnProgressListener progressListener) {
        
        String fileExtension = iFileExtensionParser.parseFileExtension(fileToEncrypt.getAbsolutePath());
        
        IFileNameObject fileNameObject = iFileNameGenerator.generateFileNameObject();
        
        //Split the cipher String into it's corresponding sections
        String[] cipherTypeParsed = cipherType.split("/");
            
        try {
            int keySize = selectKeySize(cipherTypeParsed[0]);
            
            byte[] key = iKeyGenerator.generateKey(cipherTypeParsed[0], keySize).getEncoded();
            
            //Create SecretKeySpec
            SecretKeySpec keySpec = iSecretKeySpec.getSecretKeySpec(key, cipherTypeParsed[0]);
            
            //Create IvParameterSpec
            IvParameterSpec ivSpec = initVect.generateInitialisationVector(selectIVSize(cipherTypeParsed[0]));
            
            //Get Cipher Instance
            Cipher cipher = initialiseCipherInstance(cipherType, keySpec.getEncoded(), ivSpec.getIV(), Cipher.ENCRYPT_MODE);
            
            //Create a 64 bit byte array to be used as a header 
            //for the encrypted file to store the encryption type used
            byte[] enType = new byte[64];
            
            //Convert the String representation of the chosen 
            //encryption type and copy to the newly created byte array
            System.arraycopy(cipherType.getBytes(), 0, enType, 0, cipherType.getBytes().length);
            
            byte[] fileExt = new byte[8];
            
            System.arraycopy(fileExtension.getBytes(), 0, fileExt, 0, fileExtension.getBytes().length);
            
            //Get the initialisation vector used for this encryption, 
            //to be stored as a file header
            byte[] iv = cipher.getIV();
            
            //Initialise the File Input/Output streams to be used to read 
            //and write data along with the CipherInputStream 
            //convenience method to encrypt on reading
            try (   FileInputStream fis = new FileInputStream(fileToEncrypt);
                    FileOutputStream cryptedFileOut = new FileOutputStream(outputFileName + "\\" + fileNameObject.getCryptedFileName());
                    FileOutputStream keyFileOut = new FileOutputStream(outputFileName + "\\" + fileNameObject.getKeyFileName());
                    CipherInputStream cis = new CipherInputStream(fis, cipher);) {
                
                keyFileOut.write(key);
                
                //Write the encryption type header to the file
                cryptedFileOut.write(enType);
                
                cryptedFileOut.write(fileExt);
                
                //Write the initilisation vector header to the file
                cryptedFileOut.write(iv);
                
                //Initialise variable to be used during buffering
                int bytes;
                
                //Initalise byte array for buffer to read 4096 byte blocks
                byte[] buffer = new byte[4096];
                
                double fileSize = fileToEncrypt.length();
                
                double count = 0;
                
                //Keep reading 4096 bytes from the input stream 
                //encrypting them during input and writing them 
                //to the output stream
                while((bytes = cis.read(buffer)) != -1) {
                  cryptedFileOut.write(buffer, 0, bytes);
                  count += 512;
                  
                  if(progressListener != null)
                    progressListener.onProgress("Encrypting", calculateProgress(count, fileSize));
                }
                
                //Clean up just for safekeeping
                cryptedFileOut.flush();
                cryptedFileOut.close();
                cis.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }   catch (InvalidKeyException
                | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void encryptFile(String fileNameToEncrypt, String outputFileName, String cipherType, OnProgressListener progress) {
        
        encryptFile(new File(fileNameToEncrypt), new File(outputFileName), cipherType, progress);
    }

    @Override
    public void decryptFile(File fileToDecrypt, File filePathResult, File secretKeyFile, OnProgressListener progress) {
        
        byte[] secretKey = null;
        
        try {
            secretKey = Files.readAllBytes(secretKeyFile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Cryptic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Initialise the file byte stream
        try (FileInputStream fileIn = new FileInputStream(fileToDecrypt)) {
            
            //Parse the encryption header to get the encryption type 
            //that the file was encrypted with
            String encryptionType = parseEncryptionType(fileIn);
            
            byte[] fileExt = parseFileExtension(fileIn);
            
            String fileExtString = new String(fileExt);
            
            //Parse the initialisation vector header to get 
            //the vector that the file was encrypted with
            byte[] fileIv = parseInitialisationVector(fileIn, encryptionType);
            
            //Get the fully initialisaed cipher
            Cipher cipher = initialiseCipherInstance(encryptionType, secretKey, fileIv, Cipher.DECRYPT_MODE);

            //Initialise the cipher input stream for decrypting bytes as 
            //they are read, passing the input stream to be decrypted and 
            //the cipher to be used.
            //Initialise the buffered output stream so that big files can be 
            //successfully written.
            
            String testPath = filePathResult.getPath() + "\\" + "unencrypted" + fileExtString;
            
            testPath = testPath.replaceAll("[^\\p{Graph}\n\r\t ]", "");
            
            //String testingPath = "C:\\Users\\Stevo\\Desktop\\unencrypted.zip";
            
            //boolean b = testPath.equals(testingPath);
            
            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(testPath));   
                ) {

                //Initialise variable to be used during buffering
                int bytes;
                
                //Initalise byte array for buffer to read 4096 byte blocks
                byte[] buffer = new byte[4096];
                
                double fileSize = fileToDecrypt.length();
                
                double count = 0;
                
                //Keep reading 4096 bytes from the input stream 
                //decrypting them during input and writing them 
                //to the output stream
                while ((bytes = cipherIn.read(buffer, 0, buffer.length)) != -1) {
                    os.write(buffer, 0, bytes);
                    
                    count += 512;
                    
                    if(progress != null)
                        progress.onProgress("Decrypting", calculateProgress(count, fileSize));
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
}
