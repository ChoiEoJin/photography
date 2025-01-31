package com.collabo.photography.common.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;

/**
 * AES256 encode and decode
 * @author hykim
 *
 */
public class AES256Util {

	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	public static final String org = "+";
	public static final String rep = "%2b";
	public static final String key = "abcdefghabcdefghabcdefghabcdefgh";
	
		 
	/**
	  * 일반 문자열을 지정된 키를 이용하여 AES256 으로 암호화
	  * @param  String - 암호화 대상 문자열
	  * @param  String - 문자열 암호화에 사용될 키
	  * @return String - key 로 암호화된  문자열 
	  * @exception 
	  */
	 
	 public static String aesEncode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	  byte[] textBytes = str.getBytes("UTF-8");
	  AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	  SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	  Cipher cipher = null;
	  cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	  cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
	  String byteString = Base64.encodeBase64String(cipher.doFinal(textBytes));
	  return byteString.replace(org, rep);
	 }	 

	 /**
	  * 암호화된 문자열을 지정된 키를 이용하여 AES256 으로 복호화
	  * @param  String - 복호화 대상 문자열
	  * @param  String - 문자열 복호화에 사용될 키
	  * @return String - key 로 복호화된  문자열 
	  * @exception 
	  */ 
	 public static String aesDecode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	  String convertText = str.replace(rep, org);
	  byte[] textBytes = Base64.decodeBase64(convertText);
	  //byte[] textBytes = str.getBytes("UTF-8");
	  AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	  SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	  Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	  cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
	  return new String(cipher.doFinal(textBytes), "UTF-8");
	 }
	 
}	 
		 
		 
