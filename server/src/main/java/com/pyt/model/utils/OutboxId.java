package com.pyt.model.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OutboxId implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 int messageId;
	 int senderId;
	 
	 @Override
	 public int hashCode() {
		 try {
			 return new BigInteger(1, MessageDigest.getInstance("MD5").digest((messageId+ "" +senderId).getBytes("UTF-8"))).intValue();			 
		 }catch(UnsupportedEncodingException e) {
			 return 0;
		 } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	 }
	 
	 @Override
	 public boolean equals(Object o){
		 if(o instanceof OutboxId){
			 return hashCode() == o.hashCode();
		 }else
			 return false;
	 }
}
