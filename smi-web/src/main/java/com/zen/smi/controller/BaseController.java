package com.zen.smi.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/*
 * This controller is to call Employee related services.
 *
 */
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.zen.smi.exception.BusinessException;
import com.zen.smi.exception.FormulaException;
import com.zen.smi.generic.utils.MessageBO;
import com.zen.smi.response.AppError;
import com.zen.smi.response.AppSuccess;

/**
 * Base Controller class with common methods for all controllers
 *
 * @author amantri
 */
public class BaseController {

	/**
	 * Logger for BaseController class.
	 */
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);
    private static final Pattern USER_AGENT_MATCHING_PATTERN=Pattern.compile("(opera|chrome|safari|firefox|msie|trident(?=\\/))\\/?\\s*([\\d\\.]+)");
	private static final Pattern TRIDENT_MATCHING_PATTERN=Pattern.compile("\\brv[ :]+(\\d+(\\.\\d+)?)");

	@Autowired
	public Gson gson;
	
	
	/**
	 * Handles exception and populate response.
	 * @param exception Exception
	 * @param responseMap Map<String, Object>
	 * @throws ServletException
	 */
	protected void handleException(Exception exception, Map<String, Object> responseMap) throws ServletException {
		exception.printStackTrace();
		LOGGER.error(exception);
	}
	/**
	 * This method creates response and sends it to the browser
	 * @param response HttpServletResponse
	 * @param responseMap Map<String, String>
	 * @param responseJson JSONObject
	 * @throws ServletException
	 */
	protected void handleJSONResponse(HttpServletResponse response, Map<String, Object> responseMap,
			JSONObject responseJson) throws ServletException {
		try {
			if(responseJson == null){
				responseJson = new JSONObject();
			}
			responseJson.put("responseType", responseMap);
			response.setContentType("application/json");
			if(LOGGER.isDebugEnabled() && responseJson != null)
			{ 
				//LOGGER.debug("Response data: "+responseJson.toString());
			}
			response.getWriter().write(responseJson.toString());
			response.flushBuffer();
		} catch (Exception e) {
            throw new ServletException("Unable to parse JSON response at the server.", e);
		}
	}
	
	public boolean checkBrowserCompatibility(String userAgent) {
		userAgent= userAgent.toLowerCase();
	    String name = "unknown";
	    String version = "0.0";
	    Matcher userAgentMatcher = USER_AGENT_MATCHING_PATTERN.matcher(userAgent);
	    if (userAgentMatcher.find()) {
	      name = userAgentMatcher.group(1);
	      version = userAgentMatcher.group(2);
	      if ("trident".equals(name)) {
	        name = "msie";
	        Matcher tridentVersionMatcher = TRIDENT_MATCHING_PATTERN.matcher(userAgent);
	        if (tridentVersionMatcher.find()) {
	          version = tridentVersionMatcher.group(1);
	        }
	      }
	    }
	   return checkBrowserVersion(name, version);
    }
	
	public boolean checkBrowserVersion(String name, String version) {
		boolean isCompatible = true;
		Integer versionInt = null;
	    if(name != null && version != null)
	    {
	    	if(version.indexOf(".") >= 0)
	    	{
	    		version = version.substring(0, version.indexOf("."));
	    	}
	    	try {
	    		versionInt = Integer.parseInt(version);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	//chrome|safari|firefox
	    	if(versionInt != null)
	    	{
	    		if("msie".equalsIgnoreCase(name) && versionInt.intValue() < 10)
		    	{
		    		isCompatible = false;
		    	}else if("chrome".equalsIgnoreCase(name) && versionInt.intValue() < 32)
		    	{
		    		isCompatible = false;
		    	}else if("safari".equalsIgnoreCase(name) && versionInt.intValue() < 5)
		    	{
		    		isCompatible = false;
		    	}else if("firefox".equalsIgnoreCase(name) && versionInt.intValue() < 32)
		    	{
		    		isCompatible = false;
		    	}
	    	}
	    	
	    }
	   // return name + " " + version;
	    return isCompatible;
    }
	
	
	public String handleSuccess(String jsonData, MessageBO messageBO){
		AppSuccess success = new AppSuccess();
		success.setStatus("SUCCESS");
		if(StringUtils.isNotBlank(jsonData))
		{
			success.setData(new JsonParser().parse(jsonData).getAsJsonObject());
		}
		if(messageBO != null)
		{
			success.setMessage(messageBO.getTextEn());	
		}
		String result = gson.toJson(success, AppSuccess.class);
	  
	    return result;
	}
	
	public String handleBusinessError(BusinessException be){
		AppError error = new AppError();
		error.setStatus("ERROR");
		
		String errorText = be.getErrorText();
		if(errorText == null || errorText == "")
		{
			errorText = be.getMessage();
		}
		error.setMessage(errorText);
		error.setCode(be.getErrorCode());
		
	   return gson.toJson(error, AppError.class);
	}
	
	public String handleOtherError(Throwable th){
		AppError error = new AppError();
		error.setStatus("ERROR");
		error.setMessage(th.getMessage());
		error.setCode("GEN-500");
		
	   return gson.toJson(error, AppError.class);
	}
	
	
/**
 * 
 * @param fe
 * @return
 */
	public String handleFormulaError(FormulaException fe){
		AppError error = new AppError();
		error.setStatus("FORMULA_ERROR");
		
		String errorText = fe.getErrorText();
		if(errorText == null || errorText == "")
		{
			errorText = fe.getMessage();
		}
		error.setMessage(errorText);
		error.setCode(fe.getErrorCode());
		
	   return gson.toJson(error, AppError.class);
	}
    
}
