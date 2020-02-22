package com.uttara.tasks.util;

import java.util.Comparator;

public class CreationDateComparator implements Comparator<TaskBean> {

	
	public int compare(TaskBean s1, TaskBean s2)
	{
		return s1.getCreationDate().compareTo(s2.getCreationDate());
	}

}
