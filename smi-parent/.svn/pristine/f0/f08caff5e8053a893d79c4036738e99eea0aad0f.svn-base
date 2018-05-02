package com.zen.smi.generic.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanCopyUtil {
		
	public static void copyBeanProperties(
		    final Object source,
		    final Object target,
		    final String[] properties){

		    if(source != null && target != null)
		    {
		    	final BeanWrapper src = new BeanWrapperImpl(source);
			    final BeanWrapper trg = new BeanWrapperImpl(target);

				for(final String propertyName : properties){
				        trg.setPropertyValue(
				            propertyName,
				            src.getPropertyValue(propertyName)
				        );
				    }
	
				}
		    }
		    
	
}
