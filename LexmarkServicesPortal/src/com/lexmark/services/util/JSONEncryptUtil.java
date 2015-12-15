package com.lexmark.services.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JSONEncryptUtil {


	
	    private static Logger LOGGER = LogManager.getLogger(JSONEncryptUtil.class);
	    
	    private static final String KEY_GEN_ALGORITHM = "AES";
        private static final String CIPHER_INSTANCE = "AES/ECB/PKCS5Padding";
        private static final String KEY="13d2d67436ce74d6debc871d4d897c9d626572da0780ce6d7414ead08051088d";//HEX key 
        
        
        /**
         * @param key 
         * @param encryptTarget 
         * @return byte [] 
         * @throws NoSuchAlgorithmException 
         * @throws NoSuchPaddingException 
         * @throws InvalidKeyException 
         * @throws IllegalBlockSizeException 
         * @throws BadPaddingException 
         * @throws InvalidAlgorithmParameterException 
         */
        private static byte [] runEncryption(byte [] key, byte [] encryptTarget) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
            SecretKeySpec keySpec = new SecretKeySpec(key, KEY_GEN_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(encryptTarget);
        }

        /**
         * @param key 
         * @param decryptTarget 
         * @return byte [] 
         * @throws NoSuchAlgorithmException 
         * @throws NoSuchPaddingException 
         * @throws InvalidKeyException 
         * @throws IllegalBlockSizeException 
         * @throws BadPaddingException 
         * @throws InvalidAlgorithmParameterException 
         */
        private static byte [] runDecryption(byte [] key, byte [] decryptTarget) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
            SecretKeySpec keySpec = new SecretKeySpec(key, KEY_GEN_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return cipher.doFinal(decryptTarget);
        }
        /**
         * @param msg 
         * @return String 
         * @throws InvalidKeyException 
         * @throws NoSuchAlgorithmException 
         * @throws NoSuchPaddingException 
         * @throws IllegalBlockSizeException 
         * @throws BadPaddingException 
         * @throws UnsupportedEncodingException 
         * @throws InvalidAlgorithmParameterException 
         */
        public static String encrypt(String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException{
        	byte []keyBytes=DatatypeConverter.parseHexBinary(KEY);
        	byte encryptArr[]=msg.getBytes("utf-8");
			
			StringBuffer encryptString=new StringBuffer();
	        encryptString.append(DatatypeConverter.printBase64Binary((runEncryption(keyBytes, encryptArr))));
	        
	        StringBuffer sbDe=new StringBuffer();
	        
            sbDe.append(new String(runDecryption(keyBytes, DatatypeConverter.parseBase64Binary(encryptString.toString()))));
            LOGGER.debug("Decrypted =["+sbDe.toString()+"]");
            
	        return encryptString.toString();       
	        
        }
        
        	
        	       	
        	
        	    

           
            
           
           
         
          
            
            
            
            
        }
	

