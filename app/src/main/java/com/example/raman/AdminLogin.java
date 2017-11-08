package com.example.raman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import zjgy.raman.domain.Data;

public class AdminLogin extends Activity implements OnClickListener{
	
	private Button bt1;
	private Button bt2;
	private static ImageView imageView;
	private static TextView textView;
	private static Toast toast;
	private static LayoutInflater layoutInflater;
	private static View view;
	private Intent intent;

	public static final String ACTION_REBOOT ="android.intent.action.REBOOT";
	public static final String ACTION_REQUEST_SHUTDOWN = "android.intent.action.ACTION_REQUEST_SHUTDOWN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.admin_login);
		toast=new Toast(this);
		layoutInflater=getLayoutInflater();
		//它的作用类似于findViewById()。不同点是LayoutInflater是用来找res/layout/下的xml布局文件
		//对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；
		//在这用于获取toast.xml布局文件
		view=layoutInflater.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.toast_LinearLayout));
		imageView=(ImageView)view.findViewById(R.id.toast_image);
		textView=(TextView)view.findViewById(R.id.toast_text);
		//imageView与textView都属于view且都未被载入
		
		bt1=(Button)findViewById(R.id.login_button1);
		bt2=(Button)findViewById(R.id.login_button2);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);

		LitePal.getDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.login_button1:
				intent=new Intent();
				intent.setClass(AdminLogin.this,Main.class);
				startActivity(intent);
				break;
			case R.id.login_button2://退出
				Data data=new Data();
				data.setNumber("001");
				data.setTime("11/1");
				data.setType("乙醇");
				data.setConcentration("0.5ppm");
				data.save();
				/*退出应用程序
				//android.os.Process.killProcess(android.os.Process.myPid());//获取PID
				System.exit(0);
				*/

				break;
			default:break;
		}
	}

	public static void showToast(String str){

		imageView.setImageResource(R.drawable.toast_warning);
		toast.setGravity(Gravity.CENTER,0,0);
		textView.setText(str);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(view);
		toast.show();
	}

	public static void showToastYes(String str){

		imageView.setImageResource(R.drawable.yes);
		toast.setGravity(Gravity.CENTER,0,0);
		textView.setText(str);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(view);
		toast.show();
	}
}



