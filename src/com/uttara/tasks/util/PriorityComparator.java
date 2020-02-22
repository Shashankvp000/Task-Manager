package com.uttara.tasks.util;

import java.util.Comparator;

public class PriorityComparator implements Comparator<TaskBean> {

	@Override
	public int compare(TaskBean s1, TaskBean s2)
	{
		return s2.getPriority()-s1.getPriority();
	}

}