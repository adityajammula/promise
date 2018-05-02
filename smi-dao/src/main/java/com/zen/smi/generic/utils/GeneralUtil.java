package com.zen.smi.generic.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class GeneralUtil {
		
	private static final Logger LOGGER = Logger.getLogger(GeneralUtil .class);

	static List<String> HASHTAGS_LIST = SmiConstants.AvailableHashtags.getAllValues();

	/**
	 * Instance of MessageUtil.
	 */
	private static GeneralUtil instance;
	
	public static GeneralUtil getInstance() {
		if (instance == null) {
			instance = new GeneralUtil();
		}
		return instance;
	}

	private GeneralUtil() {
		
	}
	public static void main(String[] args) {
		String hashTag  = GeneralUtil.getInstance().checkTwitterHashtag("This is #test Message", false);
		System.out.println("hashTag : "+hashTag);
	}
	
	
	public boolean isAccountQuery(String accountType, String jsonString, boolean isPrivateMsg)
	{
		boolean isAccountQuery = false;
		if("TWITTER".equalsIgnoreCase(accountType))
		{
			String hashTag = checkTwitterHashtag(jsonString, isPrivateMsg);
			if(SmiConstants.AvailableHashtags.BALANCE.toString().equalsIgnoreCase(hashTag))
			{
				isAccountQuery = true;
			}
		}
		return isAccountQuery;
	}
	
	public boolean isAccountTransfer(String accountType, String jsonString, boolean isPrivateMsg)
	{
		boolean isAccountQuery = false;
		if("TWITTER".equalsIgnoreCase(accountType))
		{
			String hashTag = checkTwitterHashtag(jsonString, isPrivateMsg);
			if(SmiConstants.AvailableHashtags.TRANSFER.toString().equalsIgnoreCase(hashTag))
			{
				isAccountQuery = true;
			}
		}
		return isAccountQuery;
	}
	
	private String checkTwitterHashtag(String jsonString, boolean isPrivateMsg) {
		LOGGER.debug("Starts checkHashtag..");
		String hashTag = null;
		try {
			if(jsonString != null)
			{
				JSONObject jsonObj = new JSONObject(jsonString);
				if(!jsonObj.has("entities")) return hashTag;
				
				JSONObject entityObj = jsonObj.getJSONObject("entities");
				if(entityObj.has("hashtags"))
				{
					JSONArray hashtagsArray = entityObj.getJSONArray("hashtags");
					for(int i =0;i<hashtagsArray.length();i++)
					{
						JSONObject hashObj = hashtagsArray.getJSONObject(i);
						if(hashObj.has("text"))
						{
							String hashText = "#"+hashObj.getString("text").trim();
							if(hashText != null && HASHTAGS_LIST.contains(hashText))
							{
								hashTag = hashText.trim();
								break;
								
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.debug("Ends checkHashtag..");
		
		return hashTag;
	}
	
	
}
