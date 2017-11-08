package com.example.raman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Set extends Activity implements OnClickListener {
	
	private Button bt1;
	private Button bt2;
	private Button bt3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.set);
		
		bt1=(Button)findViewById(R.id.set_button1);
		bt2=(Button)findViewById(R.id.set_button2);
		bt3=(Button)findViewById(R.id.set_button3);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.set_button1:
				
				break;
			case R.id.set_button2:
				
				break;
			case R.id.set_button3:
				
				break;
			default:break;
		}
	}
}
