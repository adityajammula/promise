package com.zen.smi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zen.smi.bo.UserBO;
import com.zen.smi.exception.BusinessException;
import com.zen.smi.exception.InvalidLoginException;
import com.zen.smi.service.UserService;

/**
 * HomePageController class manage Timesheet related data. Data from screens
 * is transferred to the controller and passed to services/dao.
 * 
 * @author 
 */
@Controller
public class HomePageController extends BaseController {
	
	/**
	 * Logger for HomePageController class.
	 */
	private static final Logger LOGGER = Logger.getLogger(HomePageController.class);
	
	@Autowired 
	UserService userService;
	
	@RequestMapping("/")
    public String getIndexPage() {
        return "login";
    }
	
	
	
	
	@RequestMapping("/home")
    public String getHomePage() {
        return "home";
    }
	@RequestMapping("/arms_symptoms")
    public String getArms() {
        return "arms_symptoms";
    }
	
	@RequestMapping("/feet_symptoms")
    public String getFeet() {
        return "feet_symptoms";
    }
	@RequestMapping("/legs_symptoms")
    public String getLeg() {
        return "legs_symptoms";
    }
	@RequestMapping("/hands_symptoms")
    public String getHands() {
        return "hands_symptoms";
    }
	
	@RequestMapping(value = "/current_user", method = RequestMethod.GET)
	public @ResponseBody String getCurrentUser(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		String resultJson = null;
		try {
			String  user = SecurityContextHolder.getContext().getAuthentication().getName();
			UserBO userBO= new UserBO();
			userBO.setUserName(user);
			resultJson = gson.toJson(userBO, UserBO.class);
			resultJson = handleSuccess(resultJson, null);
		} catch (Throwable th) {
			th.printStackTrace();
			resultJson = handleOtherError(th);
		}
		return resultJson;
	}
	
	@RequestMapping(value = "/create_user", method = RequestMethod.POST)
	public @ResponseBody String createUser(@RequestBody String userJson,HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		String resultJson = null;
		try {
			
			UserBO userBO= gson.fromJson(userJson, UserBO.class);
			resultJson=	userService.createUser(userBO);
			
		} catch (Throwable th) {
			th.printStackTrace();
			resultJson = handleOtherError(th);
		}
		return resultJson;
	}
	
	@RequestMapping(value = "/validate_date/{date}", method = RequestMethod.GET)
	public @ResponseBody String validateDate(@PathVariable("date") String date,HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		String resultJson = null;
		try {
			
			
			//resultJson=	dateValidationService.validateDate(date);
			
		} catch (Throwable th) {
			th.printStackTrace();
			resultJson = th.getMessage();
		}
		return resultJson;
	}
	@RequestMapping(value = "/user_profile/{userName}", method = RequestMethod.GET)
	public @ResponseBody String getUser(@PathVariable("userName") String userName,HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
		String resultJson = null;
		try {
			
			UserBO userBO= 	userService.getUserByUserName(userName);
			resultJson=gson.toJson(userBO,UserBO.class);
			
		} catch (Throwable th) {
			th.printStackTrace();
			resultJson = handleOtherError(th);
		}
		return resultJson;
	}
	
	
	@RequestMapping("/header")
    public String getHeaderPage() {
        return "header";
    }
	@RequestMapping("/items")
    public String getNav() {
        return "items";
    }
	@RequestMapping("/profile")
    public String getRightBar() {
        return "profile";
    }
	
	@RequestMapping("/signup")
    public String getDashboard() {
        return "signup";
    }
	
	
	
	
	
	
	
	@RequestMapping("/registerUser")
    public String getregistration() {
        return "register_user";
    }
	
	@RequestMapping("/index")
    public String getRegister() {
        return "index";
    }
	
	@RequestMapping("/myprofile")
    public String getMyProfile() {
        return "my_profile";
    }
	
	
    /**
	 * @return logout
	 */
	@RequestMapping("/logout")
    public String getLogout(HttpServletRequest request,
			HttpServletResponse response) {
		 
		request.getSession().invalidate();
		
        return "login";
    }
	
    
    /**
     * @return footer
     */
    @RequestMapping("/footer")
    public String getFooterPage() {
        return "footer";
    }
    
    @RequestMapping("/errbrowser")
    public String getBrowserErrorPage() {
        return "errbrowser";
    }
    
    /**
     * @return globalerror
     */
    @RequestMapping("/globalerror")
    public String getErrorPage(HttpServletRequest request,
			HttpServletResponse response) {
    	Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
    	if(throwable instanceof RuntimeException)
    	{
    		RuntimeException appRuntime = (RuntimeException)throwable;
    		if(appRuntime.getCause() instanceof BadCredentialsException)
    		{
    			return "redirect:/login?error=5";
    		}else if(appRuntime.getCause() instanceof InvalidLoginException)
    		{
    			return "redirect:/login?error=1";
    		}
     	}
    		      
        return "globalerror";
    }
	
}
