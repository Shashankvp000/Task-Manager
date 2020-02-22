package com.uttara.tasks.util;
import java.io.*;
import java.text.*;
import java.util.*;

public class TaskModel 
{
	//Check if category exists
	public boolean checkCategoryExists(String catName)
	{
		return new File(catName+".toDo").exists();
	}
	public String add(TaskBean task, String catName)
	{
		BufferedWriter bw = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String plannedDate = sdf.format(task.getPlannedDate());
		String creationDate = sdf.format(task.getCreationDate());
		try
		{
			bw = new BufferedWriter(new FileWriter(catName+".toDo",true));
			bw.write(task.getTaskName()+":" + task.getDescription()+ ":"+task.getPriority() +":"+ plannedDate+":"+task.getTags()+":" +creationDate);
			bw.newLine();
			return Constants.SUCCESS;

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "error  "+ e.getMessage();

		}
		finally
		{
			try
			{
				if(bw!= null)
					bw.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

	}
	//List Category
	public List<File> listCategory(String path)
	{
		List<File> list = new ArrayList<File>();
		File f = new File(path);
		File [] fa =null;
		try
		{
			if(f.exists() && f.isDirectory())
			{	

				fa = f.listFiles();
				Collections.addAll(list, fa);
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<TaskBean> getTasks(String catName)
	{
		BufferedReader br = null;
		String line;

		try 
		{
			br = new BufferedReader(new FileReader(catName));
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TaskBean task ;

			while((line = br.readLine())!=null)
			{

				String [] sa = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[4],Integer.parseInt(sa[2]),sdf.parse(sa[3]),sdf.parse(sa[5]));
				tasks.add(task);

			}

			return tasks;

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (NumberFormatException e)
		{

			e.printStackTrace();
			return null;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(br!=null)
					br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();

			}
		}
	}
	//List Tasks based on priority
	public List<TaskBean> getTasksBasedOnPriority(String catName)
	{
		BufferedReader br = null;
		String line;
		PriorityComparator p = new PriorityComparator();
		try 
		{
			br = new BufferedReader(new FileReader(catName));
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TaskBean task ;

			while((line = br.readLine())!=null)
			{

				String [] sa = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[4],Integer.parseInt(sa[2]),sdf.parse(sa[3]),sdf.parse(sa[5]));
				tasks.add(task);

			}
			Collections.sort(tasks,p);
			return tasks;

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (NumberFormatException e)
		{

			e.printStackTrace();
			return null;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(br!=null)
					br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();

			}
		}
	}

	//List tasks based on planned date
	public List<TaskBean> getTasksBasedOnPlannedDate(String catName)
	{

		BufferedReader br = null;
		String line;
		PlannedDateComparator plDate = new PlannedDateComparator();

		try 
		{
			br = new BufferedReader(new FileReader(catName));
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TaskBean task ;

			while((line = br.readLine())!=null)
			{

				String [] sa = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[4],Integer.parseInt(sa[2]),sdf.parse(sa[3]),sdf.parse(sa[5]));
				tasks.add(task);

			}
			Collections.sort(tasks, plDate);

			return tasks;

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (NumberFormatException e)
		{

			e.printStackTrace();
			return null;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(br!=null)
					br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();

			}
		}

	}
	//list tasks based on Alphabetical order
	public List<TaskBean>  getTasksBasedOnAlphabeticalOrder(String catName) 
	{
		BufferedReader br = null;
		String line;
		AlphabeticalOrderComparator alpha = new AlphabeticalOrderComparator();

		try 
		{
			br = new BufferedReader(new FileReader(catName));
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TaskBean task ;

			while((line = br.readLine())!=null)
			{

				String [] sa = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[4],Integer.parseInt(sa[2]),sdf.parse(sa[3]),sdf.parse(sa[5]));
				tasks.add(task);
			}
			Collections.sort(tasks, alpha);
			return tasks;

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (NumberFormatException e)
		{

			e.printStackTrace();
			return null;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(br!=null)
					br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();

			}
		}

	}
	//Listing based on creation date
	public List<TaskBean> getTasksBasedOnCreationDate(String catName) 
	{
		BufferedReader br = null;
		String line;
		CreationDateComparator crDate = new CreationDateComparator();

		try 
		{
			br = new BufferedReader(new FileReader(catName));
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TaskBean task ;

			while((line = br.readLine())!=null)
			{

				String [] sa = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[4],Integer.parseInt(sa[2]),sdf.parse(sa[3]),sdf.parse(sa[5]));
				tasks.add(task);
			}
			Collections.sort(tasks,crDate);
			return tasks;

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (NumberFormatException e)
		{

			e.printStackTrace();
			return null;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if(br!=null)
					br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();

			}
		}

	}
	public boolean searchCategory(String catName)
	{
		File f = new File(catName+".toDo");
		if(f.exists() && f.isFile() && f.getName().contains(catName) && f.getName().contains(".toDo")) 
		{
			return true;
		}

		else
			return false;


	}
	//Edit task
	public  void modifyFile(String catName, String oldString, String newString)
	{
		File fileToBeModified = new File(catName+".toDo");
		String oldContent = "";

		BufferedReader br = null;

		FileWriter bw = null;
		try
		{
			if(fileToBeModified.exists())
			{  	

				br = new BufferedReader(new FileReader(fileToBeModified));
				String line = br.readLine();

				while (line != null) 
				{
					oldContent = oldContent + line + System.lineSeparator();

					line = br.readLine();
				} 
				String newContent = oldContent.replaceAll(oldString, newString);


				bw = new FileWriter(fileToBeModified);

				bw.write(newContent);
				System.out.println("Modified successfully");
			}

			else
			{
				System.out.println("the file does not exist...Create a category and add the task first");

			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{	
				if(br!=null)
					br.close();
			}    

			catch (IOException e) 
			{
				e.printStackTrace();
			}
			try
			{
				if(bw!=null)
					bw.close();

			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public String removeTask(List<TaskBean> list, String catName) 
	{	
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(catName+".toDo"));
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			String crdate = sdf.format(date);

			for(TaskBean bean :list)
			{
				bw.write(bean.getTaskName()+":" + bean.getDescription()+ ":"+bean.getPriority() +":"+ sdf.format(bean.getPlannedDate())+":"+bean.getTags()+":" +crdate);
				bw.newLine();

			}
			return Constants.SUCCESS;
		}

		catch(Exception e)
		{
			e.printStackTrace();
			return "error"+e.getMessage();
		}
		finally
		{

			try
			{
				if(bw!= null)
					bw.close();

			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

		}
	}
	//Edit task
	public void editTask(List<TaskBean> tasks2, String catName) 
	{

		BufferedWriter bw=null;

		try {


			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			bw=new BufferedWriter(new FileWriter(catName+".toDo"));

			for(TaskBean task:tasks2)
			{
				//bw = new BufferedWriter(new FileWriter(catName+".toDo",true));
				bw.write(task.getTaskName()+":" + task.getDescription()+ ":"+task.getPriority() +":"+ sdf.format(task.getPlannedDate())+":"+task.getTags()+":" +sdf.format(task.getCreationDate()));
				bw.newLine();
			}


		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}
		finally 
		{
			if(bw!=null)
				try
				{
					bw.close();
				} 
				catch (Exception e2)
				{
					e2.printStackTrace();
				}

		}

	}
	public void searchTask(String catName, String find)
	{
		File fa = new File(catName);
		BufferedReader br = null;
		List<String> words = new ArrayList<String>();
		int occ= 0;
		try
		{
			if(fa.exists() && fa.isFile() && fa.getName().contains(".toDo"))
			{
					br = new BufferedReader( new FileReader(fa));
					String s ;
					while((s = br.readLine())!=null)
					{
	
	
						String [] word = s.split(":");
						for (String string : word)
						{
							words.add(string);
							 occ = Collections.frequency(words, find);
						}
					}
					System.out.println("No. of occurences of  "+find+" = "+ occ);	
			}
			else
				System.out.println("Give proper path");
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(br!=null)
					br.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}	
	}
}
