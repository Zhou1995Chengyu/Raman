package com.example.raman;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {
	
	
	private Intent intent;
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.main);
		
		bt1=(Button)findViewById(R.id.main_button1);
		bt2=(Button)findViewById(R.id.main_button2);
		bt3=(Button)findViewById(R.id.main_button3);
		bt4=(Button)findViewById(R.id.main_button4);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.main_button1:
				intent=new Intent(Main.this,Check.class);
				this.finish();
				startActivity(intent);
				break;
			case R.id.main_button2:
				intent=new Intent(Main.this,ChartLib.class);
				this.finish();
				startActivity(intent);
				break;
			case R.id.main_button3:
				intent=new Intent(Main.this, Query.class);
				this.finish();
				startActivity(intent);		
				break;
			case R.id.main_button4:
				intent=new Intent(Main.this, AdminLogin.class);
				ActivityCollector.finishAll();
				startActivity(intent);
				break;
			default:break;
	
		}
	}	
}
