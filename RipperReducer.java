package com.letsdobigdata;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



public class RipperReducer
extends Reducer<Text, Text, Text, Text> {

int counter_val = 0;

public static ArrayList<String> lstTrainData = new ArrayList<String>();
public static ArrayList<String> lstTestData = new ArrayList<String>();
public static ArrayList<Integer> lstOutClasses = new ArrayList<Integer>();



@Override
public void reduce(Text key, Iterable<Text> values,
    Context context)
    throws IOException, InterruptedException {
	
	
	
	if(counter_val < 100)
	{
		//Add the first 100 entries as training set
		lstTrainData.add(key.toString());
	}
	else if(counter_val >= 100)
	{
		//We need to apply Ripper Classifier here
		lstTestData.add(key.toString());
		
		//Split the data by comma
		String data_vals[] = key.toString().split(",");
		
		//Best match
		int best_match = 0;
		int best_score = Integer.MAX_VALUE;
		for(int count=0;count<lstTrainData.size();count++)
		{
			//Get the current training value
			String[] train_vals = lstTrainData.get(count).split(",");
			
			//Initialize score
			int score = 0;
			for(int count2=1;count2<train_vals.length-1;count2++)
			{
				try {
					//Get the difference between train_vals and data_vals (testing)
					int data_val = Integer.parseInt(data_vals[count2].trim());
					int train_val = Integer.parseInt(train_vals[count2].trim());
					score = score + Math.abs(data_val-train_val);	
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			//Find the best score, which is minimum
			if(score<best_score)
			{
				best_score = score;
				best_match = count;
			}
		}
		
		String class_val = lstTrainData.get(best_match).split(",")[10];
		if(class_val.equals("1"))
			class_val = "Class 1";
		else if(class_val.equals("2"))
			class_val = "Class 2";
		else
			class_val = "Class " + class_val;
		
		context.write(new Text(key.toString() + " into class "), new Text((class_val)));
		System.out.println(key.toString() + " into class " + class_val);
	
	}
	counter_val++;
	
}
	


}


