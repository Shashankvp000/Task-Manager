package com.uttara.tasks.util;

import java.util.Comparator;

public class PlannedDateComparator implements Comparator<TaskBean> 
{
	public int compare(TaskBean s1, TaskBean s2)
	{
		return s1.getPlannedDate().compareTo(s2.getPlannedDate());
	}
	
}
