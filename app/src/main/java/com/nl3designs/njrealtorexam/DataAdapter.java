package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends ArrayAdapter {
    List<TestScoreData> myData;

    public DataAdapter(Context context, List<TestScoreData> myData){
        super(context,0,myData);
        this.myData = myData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView name = convertView.findViewById(R.id.name);
        TextView tries = convertView.findViewById(R.id.tries);
        TextView correct = convertView.findViewById(R.id.correct);
        TextView passTest = convertView.findViewById(R.id.passTest);  //  ??

        TestScoreData info = myData.get(position);
        name.setText(info.name);  // position is index
        tries.setText(String.valueOf(info.tries));  // position is index
        correct.setText(String.valueOf(info.correct));  // position is index
        passTest.setText(String.valueOf(info.passTest));  //  ??

        if(position%2==0){
            //  name.setBackgroundColor(0xffff0000);  // set backround color to red for odd index num
            name.setBackgroundColor(Color.parseColor("#33FFD7"));
            convertView.setBackgroundColor(Color.parseColor("#3498DB"));
        }else{
            //  name.setBackgroundColor(0xff0000ff);  // set backround color to blue for even index num
            name.setBackgroundColor(Color.parseColor("#A0DB8E"));
            convertView.setBackgroundColor(Color.parseColor("#66CCFF"));
        }
        return convertView;
    }
}