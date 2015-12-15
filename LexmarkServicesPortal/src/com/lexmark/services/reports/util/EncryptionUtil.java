package com.lexmark.services.reports.util;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil {
      static byte[] key = "TzRP1a3ragNAQRt7".getBytes();

  
      public static String encryptData(String string) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
              String inputString = string;
              byte[] dataToSend = inputString.getBytes();
              Cipher c = Cipher.getInstance("AES");
              SecretKeySpec k = new SecretKeySpec(key, "AES");
              c.init(Cipher.ENCRYPT_MODE, k);
              byte[] encryptedData = c.doFinal(dataToSend);
              String encryptedDataStr = Base64.encodeBase64URLSafeString(encryptedData);
              return encryptedDataStr;

      }
}

