package com.zen.smi.security;

import java.security.MessageDigest;
import java.util.EnumSet;
import java.util.Set;

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
        	password = encrypt(password);
        	Set<SMIAuthority> roles = EnumSet.noneOf(SMIAuthority.class);
        	UserBO userBO = userService.authenticate(userName, password);
        	if(userBO == null)
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
    
    private String encrypt(String password) {
    	 String encryptedPasswd = null;
    	 try {
             MessageDigest md = MessageDigest.getInstance("MD5");
             md.update(password.getBytes()); 
          	 byte[] output = md.digest();
          	encryptedPasswd = bytesToHex(output);
          } catch (Exception e) {
             e.printStackTrace();
          }
    	 return encryptedPasswd;
    }
    private String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                           '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j=0; j<b.length; j++) {
           buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
           buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
     }
}