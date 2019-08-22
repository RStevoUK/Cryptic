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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    public IEncryptedObject encrypt(byte[] byteArray, String cipherType) {
        
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
    public byte[] decrypt(IEncryptedObject iEncryptedObject) {
        
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
}
