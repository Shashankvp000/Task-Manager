package com.uttara.tasks.util;

import java.util.Date;

public class TaskBean  implements Comparable<Object>
{
	private String taskName;
	private String description;
	private String tags;
	private int priority;
	private Date plannedDate;
	private Date creationDate;
	public TaskBean()
	{
		
	}	
	public TaskBean(String taskName, String description, String tags, int priority, Date plannedDate,Date creationDate) 
	{
		super();
		this.taskName = taskName;
		this.description = description;
		this.tags = tags;
		this.priority = priority;
		this.plannedDate = plannedDate;
		this.creationDate = creationDate;	
	}
	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}

	public int hashCode() {
		
		return (taskName+description+tags+priority+plannedDate+creationDate).hashCode();
	}
	public boolean equals(Object obj) 
	{
		if(obj instanceof TaskBean)
		{
			TaskBean t = (TaskBean) obj;
			if((this.taskName.equals(t.taskName)) && (this.priority==t.priority ) && this.plannedDate.equals(t.plannedDate)&& this.tags.equals(t.tags)&& this.description.equals(t.description)&& this.creationDate.equals(t.creationDate))
			{
				return true;
			}
			else
				return false;
		}
		else
			throw new IllegalArgumentException("compare only comparable type objects");
	}
	
	public String toString() 
	{
		return "TaskBean [taskName=" + taskName + ", description=" + description + ", tags=" + tags + ", priority="
				+ priority + ", plannedDate=" + plannedDate + ", creationDate="+ creationDate +"]";
	}

	@Override
	public int compareTo(Object o)
	{
		if(o instanceof TaskBean)
		{
			TaskBean t = (TaskBean) o;
			return this.priority - t.priority;
		}
		else
			throw new IllegalArgumentException("give comparable objects");
	}
		

}
