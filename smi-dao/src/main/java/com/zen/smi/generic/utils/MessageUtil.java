package com.zen.smi.generic.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zen.smi.dao.entities.Messages;
import com.zen.smi.dao.exception.GenericDAOException;
import com.zen.smi.dao.impl.MessagesDAOImpl;

public class MessageUtil {
		
	private static final Logger LOGGER = Logger.getLogger(MessageUtil .class);

	/**
	 * Instance of MessageUtil.
	 */
	private static MessageUtil instance;
	
	private static List<MessageBO> messages;

	public static MessageUtil getInstance() {
		if (instance == null) {
			instance = new MessageUtil();
			initialize();
		}
		return instance;
	}

	private static void initialize() {
		List<Messages> messageList;
		try {
			    messageList = new MessagesDAOImpl().getAllMessages();
			
				if(messageList != null && !messageList.isEmpty())
				{
					instance.populateMessages(messageList);
				} else {
					LOGGER.error("No messages in database.. ");
				}
			
			} catch (GenericDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private MessageUtil() {
		
	}
	public static void main(String[] args) {
		MessageBO messageBO = MessageUtil.getInstance().getMessage("TW_BE_SUCCESS", new Object[] {"500"});
		System.out.println(messageBO.getTextEn());
	}
	public void populateMessages(List<Messages> messagesEntities) {
		LOGGER.debug("Starts populateMessages..");
		messages = new ArrayList<MessageBO>();
		for(Messages messageEntity: messagesEntities)
		{
			MessageBO messageBO = new MessageBO();
			messages.add(messageBO);
			String[] familyProperties = {"messageId","name","type",
					"code","textEn"};
	        BeanCopyUtil.copyBeanProperties(messageEntity, messageBO, familyProperties);
		}
		LOGGER.debug("Ends populateMessages..");
	}
	
	public MessageBO getMessage(String messageName, Object[] params) {
		MessageBO matchedBO = new MessageBO();
		boolean isMatched = false;
	    for(MessageBO messageBO: messages)
	    {
	    	if(messageName.equalsIgnoreCase(messageBO.getName()))
	    	{
	    		//Create a copy and send to avoid update by reference
	    		String[] familyProperties = {"messageId","name","type",
						"code","textEn"};
		        BeanCopyUtil.copyBeanProperties(messageBO, matchedBO, familyProperties);
		        String textMsg = matchedBO.getTextEn();
		        if(params != null && params.length > 0)
		        {
		        	textMsg = MessageFormat.format(textMsg, params);
		        }
		        matchedBO.setTextEn(textMsg);
		        isMatched = true;
		        break;
	    	}
	    }
		if(!isMatched)
		{
			matchedBO.setName("UNKNOWN_MESSAGE");
			matchedBO.setCode("ERR-404");
			matchedBO.setTextEn("Unknown Message (Message not available in the database)");
			matchedBO.setType("ERROR");
		}
		
		return matchedBO;
	}
}
