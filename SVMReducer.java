package com.letsdobigdata;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SVMReducer
extends Reducer<Text, Text, Text, Text> {

@Override
public void reduce(Text key, Iterable<Text> values,
    Context context)
    throws IOException, InterruptedException {
		String data_vals[] = key.toString().split(",");
		String class_val = data_vals[data_vals.length-1];
		
		context.write(new Text(class_val + " : "), new Text((key.toString())));
		System.out.println(class_val + " : " + key.toString());
	
	}
}
