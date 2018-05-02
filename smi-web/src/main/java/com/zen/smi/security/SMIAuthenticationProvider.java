package com.zen.smi.security;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.EnumSet;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.zen.smi.bo.UserBO;
import com.zen.smi.exception.InvalidLoginException;
import com.zen.smi.service.UserService;



/**
 * User roles are retrieved from database and the authority is granted if 
 * authentication is successful. This class acts as a delegator for AuthenticationProvider.
 * The business logic is implemented in authenticate method and it is forwarded to standard
 * ldap provider.
 * 
 * @author amantri
 */
public class SMIAuthenticationProvider implements AuthenticationProvider {


	/**
	 * Logger for EportalAuthenticationProvider class.
	 */
	private static final Logger LOGGER = Logger.getLogger(SMIAuthenticationProvider.class);

	@Autowired
	private UserService userService;
	    
	
	public static String decrypt(String encryptedText, SecretKey secretKey,Cipher cipher)
			throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
	
	
	 public static String encrypt(String plainText, SecretKey secretKey,Cipher cipher)
				throws Exception {
			byte[] plainTextByte = plainText.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedByte = cipher.doFinal(plainTextByte);
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedText = encoder.encodeToString(encryptedByte);
			return encryptedText;
		}

	
    /* (non-Javadoc)
     * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
     */
    public Authentication authenticate(Authentication authentication) {
        LOGGER.debug("Start EportalAuthenticationProvider... ");
        
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication newAuth = null;
        try {
        	if(userName == null || userName == "")
        	{
        		throw new InvalidLoginException(null, null, "Invalid userName.");
        	}
        	if(password == null || password == "")
        	{
        		throw new InvalidLoginException(null, null, "Invalid password.");
        	}
        	try {
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(128);
				SecretKey secretKey = keyGenerator.generateKey();
				Cipher cipher = Cipher.getInstance("AES");
				//password=decrypt(password,secretKey,cipher);
				System.out.println("encrypted password:################"+password);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	;
        	Set<SMIAuthority> roles = EnumSet.noneOf(SMIAuthority.class);
        	UserBO userBO = userService.authenticate(userName, password);
        	if(userBO == null||null==userBO)
        	{
        		throw new InvalidLoginException(null, null, "Invalid credential.");
        	}
        	System.out.println("userBO: "+userBO);
        	if(userBO != null && userBO.getUserId() > 0)
            {
            	roles.add(SMIAuthority.ROLE_USER);
            }else 
            {
            	throw new InvalidLoginException(null, null, "Invalid login or password entered.");
            }
       	    newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), roles);
		}  catch (Exception e) {
    		throw new RuntimeException(e);
		}

        LOGGER.debug("Ends EportalAuthenticationProvider... ");

        return newAuth;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
     */
    public boolean supports(Class<?> authentication) {
    	 return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
  
}