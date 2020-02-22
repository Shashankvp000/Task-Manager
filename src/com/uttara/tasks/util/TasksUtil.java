package com.uttara.tasks.util;

public class TasksUtil 
{
	
	public static boolean validateCategoryName(String str)
	{
		if(str== null || str.trim().equals(""))
			return false;
		if(!Character.isLetter(str.charAt(0)))
			return false;
		if(!(str.split("").length>1))
			return false;
		for(int i =1;i<str.length();i++)
		{
			char c = str.charAt(i);
			if(!(Character.isDigit(c)|| Character.isLetter(c)))
				return false;
		}
			return true;
	}
	public static boolean validateTaskName(String taskName)
	{
		if(taskName == null || taskName.trim().equals(""))
			return false;
		return true;
	}
	public static boolean validateDesc(String desc)
	{
		if(desc == null || desc.trim().equals(""))
			return false;
		return true;
	}
	public static boolean validateTags(String tags)
	{
		if(tags == null || tags.trim().equals(""))
			return false;
		return true;
	}
	public static boolean validatePriority(int priority)
	{
		if(priority >10 || priority<=0)
			return false;
		return true;
	}
}
		




