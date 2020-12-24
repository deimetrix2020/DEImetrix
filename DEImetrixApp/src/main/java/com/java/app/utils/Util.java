package com.java.app.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Repeated or common.properties values are process throw handling this controller.
 * 
 */
@SuppressWarnings("restriction")
public class Util 
{
	
	/** The logger. */
	public static Logger logger = Logger.getLogger(Util.class);
	
	private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'$', 'D', 'E', 'I', 'Q', 'u', 'b', 'e', '$', 'v', 'e', 'r', 'i', 'f', 'y', '!'};

    /** The common prop bundle. */
	public static ResourceBundle properties = ResourceBundle.getBundle("common");
    
	/**
	 * Checking MD5 or SH5 password based on set the values(MD5 or SHA5) based on algorithmIndex.
	 *
	 * @param password
	 * @param algorithmIndex
	 * @return string
	 */
	public String encrpyt(String password, Integer algorithmIndex) 
	{
		logger.info("Starting to check MD5 or SHA5 password encrypt...");
		String hash = "";
		if (algorithmIndex == 1) 
		{
			logger.info("MD5 password encrypt...");
			hash = MD5(password);
		}else if (algorithmIndex == 2) 
		{
			logger.info("SHA5 password encrypt...");
			hash = SHA5(password);
		}
		return hash;
	}

	/**
	 * Encrypt using MD5
	 *
	 * @param passwordToHash
	 * @return string
	 */
	public static String MD5(String passwordToHash) 
	{
		String generatedPassword = null;
		try 
		{
			logger.info("Starting to MD5 password encrypt...");
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder strBuilder = new StringBuilder();
			for (int i = 0; i < bytes.length; i++)
			{
				strBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = strBuilder.toString();
		}catch (NoSuchAlgorithmException excp) 
		{
			logger.info("MD5 password encryption excp: " + excp.getMessage());
		}
		logger.info("Sucessfully generated MD5 password.");
		return generatedPassword;
	}

	/**
	 * Encrypt using SHA5 .
	 *
	 * @param passwordToHash
	 * @return string
	 */
	public String SHA5(String passwordToHash) 
	{
		String salt = "";
		try 
		{
			logger.info("Starting to prepare salt security password encrypt...");
			salt = getSalt();
		} 
		catch (Exception excp) 
		{
			logger.info("Salt password encryption excp: "+excp.getMessage());
		}
		return get_SHA_512_SecurePassword(passwordToHash, salt);
	}

	/**
	 * Prepare SH5 Encrypt with slat.
	 *
	 * @param passwordToHash
	 * @param salt
	 * @return password
	 */
	public static String get_SHA_512_SecurePassword(String passwordToHash,String salt) 
	{
		String generatedPassword = null;
		try 
		{
			logger.info("Starting to SHA5 password encrypt...");
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.update(salt.getBytes());
			byte[] bytes = digest.digest(passwordToHash.getBytes());
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) 
			{
				builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = builder.toString();
		}catch (NoSuchAlgorithmException excp) 
		{
			logger.info("SHA5 password encription excp: "+excp.getMessage());
		}
		logger.info("Sucessfully generated SHA5 password.");
		return generatedPassword;
	}

	/**
	 * Add to salt with more security.
	 *
	 * @return salt
	 */
	private static String getSalt()
	{
		byte[] salt = null;
		try
		{
			SecureRandom random = SecureRandom.getInstance("DEI360");
			salt = new byte[16];
			random.nextBytes(salt);
		}catch(NoSuchAlgorithmException excp)
		{
			logger.info("Salt password encryption excp: "+excp.getMessage());
		}
		return salt.toString();
	}
	
	/**
	 * Rrmove multiple space
	 */
	public static String replaceWithPattern(String str,String replace)
	{
        Pattern ptn = Pattern.compile("\\s+");
        Matcher mtch = ptn.matcher(str);
        return mtch.replaceAll(replace);
    }

	/**
	 * AES Encryption
	 * 
	 * @param strToEncrypt
	 * @return
	 */
	public static String encrypt(String data)
	{
		String encrypt_val = "";
		try
		{
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(data.getBytes());
			encrypt_val = new BASE64Encoder().encode(encVal);
		}catch(Exception excp)
		{
			logger.info("encryption excp: "+excp.getMessage());
		}
		return encrypt_val;
    }

	/**
     * Generate a new encryption key.
     */
    private static Key generateKey() throws Exception {
    	SecretKeySpec encrypt_val = null;
    	try
    	{
    		encrypt_val = new SecretKeySpec(keyValue, ALGO);
    	} catch (Exception excp) 
    	{
    		logger.info("decryption excp: "+excp.getMessage());
		}
		return encrypt_val;
    }
	
	/**
	 * AES Decrypt
	 * 
	 * @param strToDecrypt
	 * @param secret
	 * @return
	 */
	public static String decrypt(String encryptedData)
    {
    	String decrypt_val = "";
    	try
    	{
    		Key key = generateKey();
    		Cipher c = Cipher.getInstance(ALGO);
    		c.init(Cipher.DECRYPT_MODE, key);
    		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
    		byte[] decValue = c.doFinal(decordedValue);
    		decrypt_val = new String(decValue);
    	} catch (Exception excp) 
    	{
    		logger.info("decryption excp: "+excp.getMessage());
		}
		return decrypt_val;
    }
	
	/* ########################################## Date Format conversion  ############################################ */
	
	/* ##################### Session Checking function  ##################### */
	
	/**
	 * Check the user session is valid.
	 *
	 * @param request
	 * @return {@link Boolean}
	 */
	public static boolean checkSessionExist_user(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(AppConstants.USER) == null) 
		{
			logger.info("No user");
			return false;
		} else 
		{
			logger.info("user is there");
			return true;
		}
	}
	
	/**
	 * Check the Admin session is valid.
	 *
	 * @param request
	 * @return {@link Boolean}
	 */
	public static boolean checkSessionExist(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(AppConstants.ADMIN) == null) 
		{
			logger.info("No user");
			return false;
		} else 
		{
			logger.info("user is there");
			return true;
		}
	}
	
    /**
	 * Convert date to string based on date formats. The date formats are declared in common.properties
	 *
	 * @param inputDate
	 * @param inputDateFormat
	 * @param outputDateFormat
	 * @return {@link String}
	 */
	public String dateFormatCovertion(String inputDate, String inputDateFormat, String outputDateFormat)
	{
		String dateStr = "";
		DateFormat inputFormat = null;
		DateFormat outputFormat = null;
		try
		{
			inputFormat = new SimpleDateFormat(""+inputDateFormat+"");
			outputFormat = new SimpleDateFormat(""+outputDateFormat+"");
			Date validDate = inputFormat.parse(inputDate);
			dateStr = outputFormat.format(validDate);
		}catch(ParseException excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return dateStr;
	}
	
	public Double round(Double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = checknull(value) * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public Double round_bigdecimal(Double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(checknull(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public Double checknull(Double value)
	{
		if(null == value || value.isInfinite())
		{
			value = 0.0;
		}
		return value;
	}
	
	/********************************* Common properites ***************************************/
	public static String formatfile()
    {
    	String loginFile = properties.getString("ques_upload_format");
		return loginFile;
    }
	
	public static String formatuserfile()
    {
    	String loginFile = properties.getString("user_upload_format");
		return loginFile;
    }
	
	public String report_logo()
	{
		String image_val = properties.getString("report_logo");
		return image_val;
	}
	
	public String banner_img()
	{
		String image_val = properties.getString("banner_image");
		return image_val;
	}
	
	public String arrow_img()
	{
		String image_val = properties.getString("arrow_img");
		return image_val;
	}
	
	//various Total imgae
	public String total_20()
	{
		String image_val = properties.getString("total_20");
		return image_val;
	}
	
	public String total_40()
	{
		String image_val = properties.getString("total_40");
		return image_val;
	}
	
	public String total_60()
	{
		String image_val = properties.getString("total_60");
		return image_val;
	}
	
	public String total_80()
	{
		String image_val = properties.getString("total_80");
		return image_val;
	}
	
	public String total_100()
	{
		String image_val = properties.getString("total_100");
		return image_val;
	}
	
	//various diversity imgae
	public String diversity_20()
	{
		String image_val = properties.getString("diversity_20");
		return image_val;
	}
	
	public String diversity_40()
	{
		String image_val = properties.getString("diversity_40");
		return image_val;
	}
	
	public String diversity_60()
	{
		String image_val = properties.getString("diversity_60");
		return image_val;
	}
	
	public String diversity_80()
	{
		String image_val = properties.getString("diversity_80");
		return image_val;
	}
	
	public String diversity_100()
	{
		String image_val = properties.getString("diversity_100");
		return image_val;
	}
	
	//various equity imgae
	public String equity_20()
	{
		String image_val = properties.getString("equity_20");
		return image_val;
	}
	
	public String equity_40()
	{
		String image_val = properties.getString("equity_40");
		return image_val;
	}
	
	public String equity_60()
	{
		String image_val = properties.getString("equity_60");
		return image_val;
	}
	
	public String equity_80()
	{
		String image_val = properties.getString("equity_80");
		return image_val;
	}
	
	public String equity_100()
	{
		String image_val = properties.getString("equity_100");
		return image_val;
	}
	
	//various inclusion imgae
	public String inclusion_20()
	{
		String image_val = properties.getString("inclusion_20");
		return image_val;
	}
	
	public String inclusion_40()
	{
		String image_val = properties.getString("inclusion_40");
		return image_val;
	}
	
	public String inclusion_60()
	{
		String image_val = properties.getString("inclusion_60");
		return image_val;
	}
	
	public String inclusion_80()
	{
		String image_val = properties.getString("inclusion_80");
		return image_val;
	}
	
	public String inclusion_100()
	{
		String image_val = properties.getString("inclusion_100");
		return image_val;
	}
	
	public static class NullHostnameVerifier implements HostnameVerifier {
	    public boolean verify(String hostname, SSLSession session) {
	        return true;
	    }
	}
}