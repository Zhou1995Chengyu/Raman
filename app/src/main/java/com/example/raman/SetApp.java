package com.example.raman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import zjgy.raman.fileIo.FileHelper;

public class SetApp extends Activity implements OnClickListener {
	
	private Button bt1;
	private Button bt2;
	private Intent intent;

	private EditText et1;
	private EditText et2;
	private EditText et3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.set_app);
		
		bt1=(Button)findViewById(R.id.set_app_button1);
		bt2=(Button)findViewById(R.id.set_app_button2);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);

		et1=(EditText)findViewById(R.id.set_app_et1);
		et2=(EditText)findViewById(R.id.set_app_et2);
		et3=(EditText)findViewById(R.id.set_app_et3);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.set_app_button1:

				try{
					List<Integer> list=new ArrayList<Integer>();
					list.add(Integer.parseInt(et1.getText().toString()));
					list.add(Integer.parseInt(et2.getText().toString()));
					list.add(Integer.parseInt(et3.getText().toString()));
					FileHelper fh=new FileHelper();
					fh.save("app",list);
					AdminLogin.showToastYes("保存成功");
					intent=new Intent(SetApp.this,Check.class);
					this.finish();
					startActivity(intent);
				}catch(NumberFormatException e){//数字格式异常
					AdminLogin.showToast("请填写整数");
				}catch(Exception e){
					AdminLogin.showToast("数据保存出错");
				}
				break;
			case R.id.set_app_button2:
				intent=new Intent(SetApp.this,Check.class);
				this.finish();
				startActivity(intent);
				break;
			default:break;
		}
	}
}
