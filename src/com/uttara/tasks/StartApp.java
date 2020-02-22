package com.uttara.tasks;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.uttara.tasks.util.Constants;
import com.uttara.tasks.util.Logger;
import com.uttara.tasks.util.TaskBean;
import com.uttara.tasks.util.TaskModel;
import com.uttara.tasks.util.TasksUtil;

public class StartApp
{
	public static void main(String[] args)
	{
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		try 
		{
			int ch1=0,ch2=0, ch3=0,priority;
			String catName;
			String taskName,desc,tags,pldate;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TaskModel model = new TaskModel();

			Logger.getInstance().log("Starting task manager");
			while(ch1!=5)
			{//outer while
				System.out.println("Press 1 to Create category");
				System.out.println("Press 2 to Load category");
				System.out.println("Press 3 to Search category");
				System.out.println("Press 4 to List category");
				System.out.println("Press 5 to Exit");

				ch1 = sc1.nextInt();
				switch (ch1)
				{
				case 1://Creating category
					System.out.println("Creating category");
					Logger.getInstance().log("Creating category");
					System.out.println("Enter category name");
					catName = sc2.nextLine();
					while(!TasksUtil.validateCategoryName(catName))
					{
						System.out.println("Category name should start with a letter, should be alphanumeric, should be single word");
						System.out.println("Enter another category name");
						catName = sc2.nextLine();
					}
					while( model.checkCategoryExists(catName))
					{
						System.out.println("Give unique category name");
						catName = sc2.nextLine();
					}
					while(ch2 !=6)
					{//inner while
						System.out.println("Press 1 to add a task");
						System.out.println("Press 2 to edit a task");
						System.out.println("Press 3 to remove a task");
						System.out.println("Press 4 to list the tasks");
						System.out.println("Press 5 to search ");
						System.out.println("Press 6 to go back");
						ch2 = sc1.nextInt();
						switch (ch2)
						{
						case 1://Add a task
							Logger.getInstance().log("creating task");
							System.out.println("Enter the task name");
							taskName = sc2.nextLine();
							while(!TasksUtil.validateTaskName(taskName))
							{
								System.out.println("Give proper task name..Task name should not be empty or null !!!");
								System.out.println("Enter another task name");
								taskName = sc2.nextLine();
							}

							System.out.println("Enter the description");
							desc = sc2.nextLine();
							while(!TasksUtil.validateDesc(desc))
							{
								System.out.println("Description cannot be empty");
								System.out.println("Enter proper description");
								desc = sc2.nextLine();
							}

							System.out.println("Enter the tags");
							tags = sc2.nextLine();

							while(!TasksUtil.validateTags(tags))
							{
								System.out.println("Tags cannot be empty");
								System.out.println("Enter proper tag");
								tags = sc2.nextLine();
							}

							System.out.println("Enter the priority : 1(Low)-10(High)");
							priority = sc1.nextInt();
							while(!TasksUtil.validatePriority(priority))
							{
								System.out.println("Priority should be within 1-10 only");
								System.out.println("Enter priority");
								priority = sc1.nextInt();
							}

							System.out.println("Enter the due date in dd/mm/yyyy format");
							pldate = sc2.nextLine();
							Date date = sdf.parse(pldate);
							Date creationDate = new Date();


							TaskBean taskbean = new TaskBean(taskName,desc,tags,priority,date,creationDate);
							String result = model.add(taskbean, catName);

							if(result.equals(Constants.SUCCESS))
							{
								Logger.getInstance().log("Task is successfully added");

								System.out.println("Task is successfully added");
							}
							else
								System.out.println("Failed to add the task "+result);

							break ;


						case 4 ://List tasks
							Logger.getInstance().log("Listing tasks");

							System.out.println("Press 1 list the tasks Alphabetically");
							System.out.println("Press 2 list the tasks based on priority");
							System.out.println("Press 3 list the tasks based on due date");
							System.out.println("Press 4 list the tasks based on creation date");


							ch3= sc1.nextInt();
							switch (ch3) 
							{
							case 1: System.out.println("Listing the tasks alphabetically");

							List<TaskBean> task1 = model.getTasksBasedOnAlphabeticalOrder(catName+".toDo");
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");


							if(task1 != null)
							{
								for (TaskBean taskBean2 : task1)
								{	
									String plannedDate1 = sdf1.format(taskBean2.getPlannedDate());
									String creationDate1 = sdf1.format(taskBean2.getCreationDate());
									System.out.println("TaskName :"+taskBean2.getTaskName()+ " : Description :" +taskBean2.getDescription()+" : Priority:"+taskBean2.getPriority()+" : Tags :"+ taskBean2.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
								}
							}
							else
								System.out.println("There are no tasks");


							break ;

							case 2 : 	System.out.println("listing tasks based on priority");
							List<TaskBean> task3 = model.getTasksBasedOnPriority(catName+".toDo");
							SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
							if(task3!= null)
							{
								for (TaskBean taskBean4 : task3)
								{	
									String plannedDate1 = sdf2.format(taskBean4.getPlannedDate());
									String creationDate1 = sdf2.format(taskBean4.getCreationDate());
									System.out.println("TaskName :"+taskBean4.getTaskName()+ " : Description :" +taskBean4.getDescription()+" : Priority:"+taskBean4.getPriority()+" : Tags :"+ taskBean4.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
								}
							}
							else
								System.out.println("There are no tasks");


							break ;
							case 3 : 	System.out.println("listing tasks based on planned date");
							List<TaskBean> task2 = model.getTasksBasedOnPlannedDate(catName+".toDo");
							SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
							if(task2 != null)
							{
								for (TaskBean taskBean3 : task2)
								{
									String plannedDate1 = sdf3.format(taskBean3.getPlannedDate());
									String creationDate1 = sdf3.format(taskBean3.getCreationDate());
									System.out.println("TaskName :"+taskBean3.getTaskName()+ " : Description :" +taskBean3.getDescription()+" : Priority:"+taskBean3.getPriority()+" : Tags :"+ taskBean3.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
								}
							}
							else
								System.out.println("There are no tasks");

							break;
							//Listing based on creation date
							case 4: System.out.println("Listing the tasks based on created date");

							List<TaskBean> task4 = model.getTasksBasedOnCreationDate(catName+".toDo");
							
							SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");


							if(task4 != null)
							{
								for (TaskBean taskBean5 : task4)
								{	
									String plannedDate1 = sdf4.format(taskBean5.getPlannedDate());
									String creationDate1 = sdf4.format(taskBean5.getCreationDate());
									System.out.println("TaskName :"+taskBean5.getTaskName()+ " : Description :" +taskBean5.getDescription()+" : Priority:"+taskBean5.getPriority()+" : Tags :"+ taskBean5.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
								}
							}
							else
								System.out.println("There are zero tasks");


							break ;
							}
							break;
						case 6://Go back to main menu
							Logger.getInstance().log("going back to main menu");
							System.out.println("Going back");
							break;
						case 5: //Search task
							
							System.out.println("Enter the name to searched");
							String search = sc2.nextLine();
							model.searchTask(catName+".toDo",search);
								break;
						case 3: //Remove task
							Logger.getInstance().log("removing task");
							List<TaskBean>  list = new ArrayList<TaskBean>();
							list = model.getTasks(catName+".toDo");
							//System.out.println(list);
							for(int i =0;i<list.size();i++)
							{
								System.out.println(i + " - "+list.get(i) );

							}

							System.out.println("Enter the task number to be removed");
							int taskNum= sc1.nextInt();
							list.remove(taskNum);
							System.out.println("Removing task "+taskNum);
							
							String res = model.removeTask(list, catName);
							if(res.equals(Constants.SUCCESS))
							{
								Logger.getInstance().log("Task is successfully removed");

								System.out.println("Task is successfully removed");
							}
							else
								System.out.println("Failed to remove the task "+res);

							break ;

						case 2 ://edit
							Logger.getInstance().log("editing the task");
							List<TaskBean> tasks= model.getTasks(catName+".toDo");

							for(int i=0; i<tasks.size(); i++)
							{
								System.out.println(i+" "+tasks.get(i).getTaskName());
							}
							System.out.println(" enter  Serial no of task to edit ");
							int sl=sc1.nextInt();

							TaskBean old = tasks.get(sl);
							tasks.remove(old);

							int ch4=0;
							while(ch4!=6)
							{

								System.out.println("enter 1  to edit name");

								System.out.println("enter 2  to  edit desc");

								System.out.println("enter 3  to  edit tags");

								System.out.println("enter 4  to  edit date");

								System.out.println("enter 5  to  edit priority");
								System.out.println("enter 6  to  goback ");

								ch4=sc1.nextInt();
								System.out.println("-------");
								switch (ch4) 
								{

								case 1:
									System.out.println(" enter new name ");
									String newName=sc2.nextLine();
									old.setTaskName(newName);

									tasks.add(old);
									model.editTask(tasks,catName);

									break;
								case 2:
									System.out.println(" enter new desc ");
									String newDesc=sc2.nextLine();
									old.setDescription(newDesc);

									tasks.add(old);
									model.editTask(tasks,catName);
									break;
								case 3:
									System.out.println(" enter new tags");
									String newtags=sc2.nextLine();
									old.setTags(newtags);

									tasks.add(old);
									model.editTask(tasks,catName);
									break;
								case 4:
									System.out.println(" enter new date");
									String dt= sc2.nextLine();
									Date plannedDate = sdf.parse(dt);
									old.setPlannedDate(plannedDate);
									tasks.add(old);
									model.editTask(tasks,catName);
									break;
								case 5:
									System.out.println(" enter new priority");
									int newpriority=sc1.nextInt();
									old.setPriority(newpriority);

									tasks.add(old);
									model.editTask(tasks,catName);
									break;

								case 6:
									System.out.println("enter 6  to  go back ");
									break;
								default:
									break;
								}

							}
							break;
						}
					}

				case 3 ://Search the category
					Logger.getInstance().log("searching category");
					System.out.println("Enter the category name to search");
					catName = sc2.nextLine();
					while(!TasksUtil.validateCategoryName(catName))
					{
						System.out.println("Category name should start with a letter, should be alphanumeric, should be single word !!!");
						System.out.println("Enter another category name");
						catName = sc2.nextLine();
					}

					if(model.searchCategory(catName))
					{
						System.out.println("The searched category exists");
					}
					else
					{
						System.out.println("The searched category does not exists");
					}
					break;
				case 4 :	//outer while--list category
					List<File> files = model.listCategory(Constants.PATH);
					if(files!=null)
					{	
						System.out.println("The existing categories are :");
						for (File file : files) 
						{	
							if(file.getName().contains(".toDo"))
								System.out.println(file.getName());
						}
					}
					else
						System.out.println("The category list is empty");
					break;
				case 5://outer while---exit
					Logger.getInstance().log("exiting");
					System.out.println("Exiting");

					break ;
				case 2: //Outer while Load category
					Logger.getInstance().log("Loading category");
					System.out.println("Enter the category name to load ");
					catName = sc2.nextLine();
					while(!TasksUtil.validateCategoryName(catName))
					{
						System.out.println("Category name should start with a letter, should be alphanumeric, should be single word !!!");
						System.out.println("Enter another category name");
						catName = sc2.nextLine();
					}
					if( model.checkCategoryExists(catName))
					{
						System.out.println("The requested category name is loaded");


						while(ch2 !=7)
						{//inner while
							System.out.println("Press 1 to add a task");
							System.out.println("Press 2 to edit a task");
							System.out.println("Press 3 to remove a task");
							System.out.println("Press 4 to list the tasks");
							System.out.println("Press 5 to search ");
							System.out.println("Press 6 to go back");
							ch2 = sc1.nextInt();
							switch (ch2)
							{
							case 1://Add a task
								Logger.getInstance().log("creating task");
								System.out.println("Enter the task name");
								taskName = sc2.nextLine();
								while(!TasksUtil.validateTaskName(taskName))
								{
									System.out.println("Give proper task name..Task name should not be empty or null !!!");
									System.out.println("Enter another task name");
									taskName = sc2.nextLine();
								}

								System.out.println("Enter the description");
								desc = sc2.nextLine();
								while(!TasksUtil.validateDesc(desc))
								{
									System.out.println("Description should not be empty or null");
									System.out.println("Enter proper description");
									desc = sc2.nextLine();
								}

								System.out.println("Enter the tags");
								tags = sc2.nextLine();

								while(!TasksUtil.validateTags(tags))
								{
									System.out.println("Tags should not be empty or null");
									System.out.println("Enter proper tag");
									tags = sc2.nextLine();
								}

								System.out.println("Enter the priority : 1(Low)-10(High)");
								priority = sc1.nextInt();
								while(!TasksUtil.validatePriority(priority))
								{
									System.out.println("Priority should be a nummber within 1-10 only");
									System.out.println("Enter priority");
									priority = sc1.nextInt();
								}

								System.out.println("Enter the due date in dd/mm/yyyy format");
								pldate = sc2.nextLine();
								Date date = sdf.parse(pldate);
								Date creationDate = new Date();


								TaskBean taskbean = new TaskBean(taskName,desc,tags,priority,date,creationDate);
								String result = model.add(taskbean, catName);

								if(result.equals(Constants.SUCCESS))
								{
									Logger.getInstance().log("Task is successfully added");

									System.out.println("Task is successfully added");
								}
								else
									System.out.println("Failed to add the task "+result);

								break ;

							case 4 ://List tasks
								Logger.getInstance().log("listing tasks");
								System.out.println("Press 1 list the tasks alphabetically");
								System.out.println("Press 2 list the tasks based on priority");
								System.out.println("Press 3 list the tasks based on due date");
								System.out.println("Press 4 list the tasks based on creation date");


								ch3= sc1.nextInt();
								switch (ch3) 
								{		//Listing based on alphabetical order
								case 1: System.out.println("Listing the tasks alphabetically");

								List<TaskBean> task1 = model.getTasksBasedOnAlphabeticalOrder(catName+".toDo");
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");


								if(task1 != null)
								{
									for (TaskBean taskBean2 : task1)
									{	
										String plannedDate1 = sdf1.format(taskBean2.getPlannedDate());
										String creationDate1 = sdf1.format(taskBean2.getCreationDate());
										System.out.println("TaskName :"+taskBean2.getTaskName()+ " : Description :" +taskBean2.getDescription()+" : Priority:"+taskBean2.getPriority()+" : Tags :"+ taskBean2.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
									}
								}
								else
									System.out.println("There are no tasks");


								break ;

								//Listing based on priority
								case 2 : 	System.out.println("listing tasks based on priority");
								List<TaskBean> task3 = model.getTasksBasedOnPriority(catName+".toDo");
								SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
								if(task3!= null)
								{
									for (TaskBean taskBean4 : task3)
									{	
										String plannedDate1 = sdf2.format(taskBean4.getPlannedDate());
										String creationDate1 = sdf2.format(taskBean4.getCreationDate());
										System.out.println("TaskName :"+taskBean4.getTaskName()+ " : Description :" +taskBean4.getDescription()+" : Priority:"+taskBean4.getPriority()+" : Tags :"+ taskBean4.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
									}
								}
								else
									System.out.println("There are no tasks");


								break ;
								//Listing based on due/planned date
								case 3 : 	System.out.println("listing tasks based on planned date");
								List<TaskBean> task2 = model.getTasksBasedOnPlannedDate(catName+".toDo");
								SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
								if(task2 != null)
								{
									for (TaskBean taskBean3 : task2)
									{
										String plannedDate1 = sdf3.format(taskBean3.getPlannedDate());
										String creationDate1 = sdf3.format(taskBean3.getCreationDate());
										System.out.println("TaskName :"+taskBean3.getTaskName()+ " : Description :" +taskBean3.getDescription()+" : Priority:"+taskBean3.getPriority()+" : Tags :"+ taskBean3.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
									}
								}
								else
									System.out.println("There are no tasks");

								break;
								//Listing based on creation date
								case 4: System.out.println("Listing the tasks based on creation date");

								List<TaskBean> task4 = model.getTasksBasedOnCreationDate(catName+".toDo");
								
								SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");


								if(task4 != null)
								{
									for (TaskBean taskBean5 : task4)
									{	
										String plannedDate1 = sdf4.format(taskBean5.getPlannedDate());
										String creationDate1 = sdf4.format(taskBean5.getCreationDate());
										System.out.println("TaskName :"+taskBean5.getTaskName()+ " : Description :" +taskBean5.getDescription()+" : Priority:"+taskBean5.getPriority()+" : Tags :"+ taskBean5.getTags()+":Planned Date:"+ plannedDate1+": Creation Date:"+creationDate1);
									}
								}
								else
									System.out.println("There are no tasks");


								break ;
								}

								break;
								
							case 3: //Remove task
								Logger.getInstance().log("removing task");
								List<TaskBean>  list = new ArrayList<TaskBean>();
								list = model.getTasks(catName+".toDo");
								
								for(int i =0;i<list.size();i++)
								{
									System.out.println(i + " - "+list.get(i) );
									
								}
								
								System.out.println("Enter the task number to be removed");
								int taskNum= sc1.nextInt();
								list.remove(taskNum);
								
								String res = model.removeTask(list, catName);
								if(res.equals(Constants.SUCCESS))
								{
									Logger.getInstance().log("Task is successfully removed");

									System.out.println("Task  is successfully removed");
								}
								else
									System.out.println("Failed to remove the task "+res);

								break ;
						
							case 7:
								System.out.println("Going back");
								break;
							case 5: //Search task
									System.out.println("Enter the name to searched");
									String search = sc2.nextLine();
										model.searchTask(catName+".toDo",search);
										break;

							case 2 : //edit
								Logger.getInstance().log("editing the  task");
								List<TaskBean> tasks = model.getTasksBasedOnCreationDate(catName+".toDo");
								
								for(int i=0; i<tasks.size(); i++)
								{
									System.out.println(i+" "+tasks.get(i));
								}
								System.out.println(" enter  Serial no of task to edit ");
								int sl=sc1.nextInt();

								TaskBean oldTask = tasks.get(sl);
								tasks.remove(oldTask);

								int ch4=0;
								while(ch4!=6)
								{

									System.out.println("enter 1  to edit name");

									System.out.println("enter 2  to  edit desc");

									System.out.println("enter 3  to  edit tags");

									System.out.println("enter 4  to  edit date");

									System.out.println("enter 5  to  edit priority");
									System.out.println("enter 6  to  goback ");

									ch4=sc1.nextInt();
									System.out.println("-------");
									switch (ch4) 
									{

									case 1:
										System.out.println(" enter new name ");
										String newName=sc2.nextLine();
										oldTask.setTaskName(newName);

										tasks.add(oldTask);
										model.editTask(tasks,catName);

										break;
									case 2:
										System.out.println(" enter new desc ");
										String newDesc=sc2.nextLine();
										oldTask.setDescription(newDesc);

										tasks.add(oldTask);
										model.editTask(tasks,catName);
										break;
									case 3:
										System.out.println(" enter new tags");
										String newtags=sc2.nextLine();
										oldTask.setTags(newtags);

										tasks.add(oldTask);
										model.editTask(tasks,catName);
										break;
									case 4:
										System.out.println(" enter new date");
										String dt= sc2.nextLine();
										Date plannedDate = sdf.parse(dt);
										oldTask.setPlannedDate(plannedDate);
										tasks.add(oldTask);
										model.editTask(tasks,catName);
										break;
									case 5:
										System.out.println(" enter new priority");
										int newpriority=sc1.nextInt();
										oldTask.setPriority(newpriority);

										tasks.add(oldTask);
										model.editTask(tasks,catName);
										break;

									case 6:
										System.out.println("enter 6  to  goback ");
										break;
									default:
										break;
									}
								}



								break;	 
							}


						}
					}
					else 
						System.out.println("Oops!!!!This category does not exist..Try loading the existing category or create a new category");
					break;
				}

			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		finally
		{
			sc1.close();
			sc2.close();
		}
	}

}

