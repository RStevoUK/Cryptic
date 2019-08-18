/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteClasses;

import com.stevo.passwordencrypter.Interfaces.IEncrypt;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Stevo
 */
public class AESEncryption implements IEncrypt {

    private byte[] cipherText;
    
    @Override
    public byte[] Encrypt(byte[] byteArray, SecretKey key, byte[] IV) {
        
        try {
            //Get Cipher Instance
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
            //Create SecretKeySpec
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            
            //Create IvParameterSpec
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            
            //Initialize Cipher for ENCRYPT_MODE
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            
            //Perform Encryption
            cipherText = cipher.doFinal(byteArray);
            
            return cipherText;
            
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
