package com.letsdobigdata;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class RipperDriver extends Configured implements Tool, ActionListener
{
	public static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
	
	JButton btnPickDataset,btnApplyRipper,btnExit;
	public static String fileName;
	public static String out_path;
	public static boolean running = false;
	
	public int run(String[] args) throws Exception
	{
		//Setup the output path
		out_path = "output/output";
		File f = new File(out_path);
		if(f.exists())
		{
			deleteDirectory(f);
		}
		
		JFrame frame = new JFrame("Welcome to Hadoop Based Processing");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4,1));
		
		JLabel lbl = new JLabel("Welcome to Ripper based Classifier using Hadoop");
		frame.add(lbl);
		
		btnPickDataset = new JButton("Pick dataset file");
		btnPickDataset.addActionListener(this);
		frame.add(btnPickDataset);
		btnApplyRipper = new JButton("Apply Sequential Ripper");
		btnApplyRipper.addActionListener(this);
		frame.add(btnApplyRipper);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		frame.add(btnExit);
		
		frame.setVisible(true);
		running = true;
		
		while(running == true);
		
		Runtime.getRuntime().exec("write.exe output/output/part-r-00000");
		
		return 1;
	}

	public static void main(String[] args) throws Exception {
		RipperDriver driver = new RipperDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(btnPickDataset))
		{
			JFileChooser jfc = new JFileChooser(".");
			if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				fileName = jfc.getSelectedFile().getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Selected file:" + fileName);
			}
		}
		else if(e.getSource().equals(btnApplyRipper))
		{
			try
			{
				executeJobs(fileName);
			}
			catch(Exception ex)
			{
				
			}			
		}
		else if(e.getSource().equals(btnExit))
		{
			System.exit(1);
		}
	}
	
	public static void executeJobs(String filename) {
		try {
			String out_path_wamp = "output/output";
			File f = new File(out_path_wamp);
			if(f.exists())
			{
				deleteDirectory(f);
			}
			
			Job job = new Job();
			job.setJarByClass(RipperDriver.class);
			job.setJobName("Data Classification");
			
			FileInputFormat.addInputPath(job, new Path(filename));
			FileOutputFormat.setOutputPath(job,new Path(out_path_wamp));
			
			job.setMapperClass(RipperMapper.class);
			job.setReducerClass(RipperReducer.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			job.waitForCompletion(true);	
			running = false;
		} catch(Exception ex) {
			
		}
		
	}
}
