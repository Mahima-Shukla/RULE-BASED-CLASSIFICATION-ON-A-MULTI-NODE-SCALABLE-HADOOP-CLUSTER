package com.letsdobigdata;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SVMMapper extends
		Mapper<LongWritable, Text, Text, Text> {

	static int counter = 0;
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		context.write(new Text(line), new Text(counter+""));
		counter++;
	}
}
