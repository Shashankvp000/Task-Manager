package com.uttara.tasks.util;

import java.util.Comparator;

public class AlphabeticalOrderComparator implements Comparator<TaskBean> 
{

	
	public int compare(TaskBean o1, TaskBean o2)
	{
		return o1.getTaskName().toLowerCase().compareTo(o2.getTaskName().toLowerCase());
	}

}
