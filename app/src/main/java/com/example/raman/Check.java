package com.example.raman;


import zjgy.raman.adapter.SpinnerAdapter;
import zjgy.raman.domain.ChartData;
import zjgy.raman.domain.Data;
import zjgy.raman.fileIo.FileHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Check extends Activity implements OnClickListener {

	private Button bt1;
	private Button bt2;
	private Button bt3;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private EditText et1;
	private EditText et2;
	private List<Integer> list=new ArrayList<Integer>();
	private Intent intent;
	private ProgressBar pb;
	private static String[] spinner_str1=new String[]{"Unknown","百草枯","三聚氰胺","乙醇","正丁醇","孔雀石绿"};
	private static List<String> spinner_str;
	private Spinner spinner;
	private SpinnerAdapter adapter_spinner;
	private String sp;
	private int sp_index;
	private int status=0;
	private int hasData = 0;
	private int[] data = new int[100];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.check);
		
		bt1=(Button)findViewById(R.id.check_button1);
		bt2=(Button)findViewById(R.id.check_button2);
		bt3=(Button)findViewById(R.id.check_button3);
		
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
	
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		setProgress(100);

		tv1=(TextView)findViewById(R.id.check_tv1);
		tv2=(TextView)findViewById(R.id.check_tv2);
		tv3=(TextView)findViewById(R.id.check_tv3);
		et1=(EditText)findViewById(R.id.check_et1);
		et1=(EditText)findViewById(R.id.check_et2);

		list= FileHelper.readTxt("app");
		if(list.size()!=0) {
			tv1.setText(list.get(0)+"");
			tv2.setText(list.get(1)+"");
			tv3.setText(list.get(2)+"");
		}
		spinner_str=getSpinnerStr();
		spinner=(Spinner)findViewById(R.id.check_spinner1);
		adapter_spinner=new SpinnerAdapter(this, android.R.layout.simple_list_item_1,spinner_str);
		adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter_spinner);	
		spinner.setSelection(0);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				sp=spinner_str1[position];//获取下拉列表选中值;
				sp_index=position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
//listView		
	}
	
//按钮点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.check_button1:
				//pb.set
									
					final Handler mHandler=new Handler(){

						@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub

							if(msg.what==0x111){
								pb.setProgress(status);
								//pb.incrementProgressBy(1);
							}
						}
						
					};
					new Thread(){
						public void run(){
							while(status<100){
								status=doWork();
								Message m=new Message();
								m.what=0x111;
								mHandler.sendMessage(m);
							}
						};
					}.start();
				String number=et1.getText().toString();
				if(number==null||number.equals("")){
					AdminLogin.showToast("请填写编号");
					break;
				}else if(isExsit(number)){
					AdminLogin.showToast("编号已存在");
					break;
				}

				SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date curDate =  new Date(System.currentTimeMillis());
				String time=formatter.format(curDate);
				intent=new Intent(Check.this,CheckChart.class);
				intent.putExtra("number",et1.getText().toString());
				intent.putExtra("time",time);
				intent.putExtra("type",sp);
				//this.finish();
				startActivity(intent);


				break;
			case R.id.check_button2:
				intent=new Intent(Check.this,SetApp.class);
				//this.finish();
				startActivity(intent);
				//startActivityForResult(intent, R.id.set_app_button2);
				break;
			case R.id.check_button3:
				intent=new Intent(Check.this,Main.class);
				this.finish();
				startActivity(intent);
				break;
			default:break;
		}
	}
	
	private int doWork(){
		data[hasData++]=(int)(Math.random()*100);
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return hasData;
	}

	//判断该编号是否已经存在数据库Data表中
	public boolean isExsit(String number){
		List<Data> dates= DataSupport.findAll(Data.class);//查询到Data表中所有记录
		for(Data date:dates){
			if(date.getNumber().equals(number))
				return true;
		}
		return false;
	}
//获取下拉列表值
	public List<String> getSpinnerStr(){
		List<String> strs=new ArrayList<String>();
		List<ChartData> charts= DataSupport.findAll(ChartData.class);
		for(ChartData chart:charts){
			strs.add(chart.getName());
		}
		return strs;
	}
}
