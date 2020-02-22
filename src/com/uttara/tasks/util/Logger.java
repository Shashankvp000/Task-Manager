package com.uttara.tasks.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Logger 
{
	private String path = Constants.LOGGERPATH;
	private Logger()
	{
		
	}
	private static Logger obj = null;
	public static Logger getInstance()
	{
		if(obj==null)
			obj = new Logger();
		return obj;
	}
	public void log(final String data)
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				BufferedWriter bw = null;
				try 
				{
				 bw = new BufferedWriter(new FileWriter(path,true));
				bw.write(new Date()+":"+data);
				bw.newLine();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						if(bw!=null)
							bw.close();
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			
			}
		}).start();
	}
}
