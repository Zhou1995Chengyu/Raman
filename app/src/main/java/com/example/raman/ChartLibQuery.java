package com.example.raman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.litepal.crud.DataSupport;

import zjgy.raman.domain.ChartData;
import zjgy.raman.domain.Data;
import zjgy.raman.view.MyChartServiceA;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ChartLibQuery extends Activity implements OnClickListener {

	private Button bt1;
	private Button bt2;
	private Button bt3;
	
	private EditText et1;
	private EditText et2;
	
	private Intent intent;
	private LinearLayout linearLayout;
	
	private MyChartServiceA chart;
	private GraphicalView mView;
	private List<Double> list ;
	private List<Double> xList=new ArrayList<Double>();
	private int x=0;
	private  List<ChartData> charts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.chart_lib_query);
		
		bt1=(Button)findViewById(R.id.chart_lib_query_button1);
		bt2=(Button)findViewById(R.id.chart_lib_query_button2);
		bt3=(Button)findViewById(R.id.chart_lib_query_button3);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		
		linearLayout=(LinearLayout)findViewById(R.id.chart_lib_query_linearLayout);
		
		chart=new MyChartServiceA(ChartLibQuery.this);
		chart.setXYMultipleSeriesDataset();
		chart.setXYMultipleSeriesRenderer();
		mView = chart.getGraphicalView();
		// 将图表添加到布局容器中
		linearLayout.addView(mView);
		MyChartServiceA.flourdata.add(0, 0);
		// 开机时画一次图表 更新坐标轴55p
		MyChartServiceA.mGraphicalView.invalidate();
		// 清所有图标相关数据
		MyChartServiceA.flourdata.clear();
		
		et1=(EditText)findViewById(R.id.chart_lib_query_et1);
		et2=(EditText)findViewById(R.id.chart_lib_query_et2);
		Intent intent=getIntent();
		String number=intent.getStringExtra("number");
		charts=DataSupport.where("number=?",number).find(ChartData.class);
		et1.setText(charts.get(0).getName());
		et2.setText(charts.get(0).getType());

		list=readTxt("aaa");
		for (int j = 0; j <list.size(); j++) {
			chart.flourdata.add(xList.get(j),list.get(j));
			chart.updateChart();
		}
	}
	//删除提示框
	private void showNormalDialog(){
		final AlertDialog.Builder normalDialog =
				new AlertDialog.Builder(this);
		normalDialog.setTitle("是否要删除");
		normalDialog.setPositiveButton("取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		normalDialog.setNegativeButton("确定",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DataSupport.deleteAll(ChartData.class, "number=?", et1.getText().toString());
						intent = new Intent(ChartLibQuery.this, ChartLib.class);
						startActivity(intent);
						onResume();
					}
				});
		normalDialog.show();
	}
//按钮点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.chart_lib_query_button1://删除
				showNormalDialog();
				break;
			case R.id.chart_lib_query_button2://导出

				break;
			case R.id.chart_lib_query_button3:
				intent=new Intent(ChartLibQuery.this,ChartLib.class);
				this.finish();
				startActivity(intent);
				break;
			default:break;
		}
	}
	//读取数据点
	public List<Double> readTxt(String fileTxt){
	    List<Double> receives=new ArrayList<Double>();
	    BufferedReader br=null;
	    InputStreamReader isr=null;
	    InputStream is=null;
	    try {
	    	File file=new File("/data/data/com.example.raman/files/"+fileTxt+".txt");
			is=new FileInputStream(file);
			isr=new InputStreamReader(is);
			br=new BufferedReader(isr);
			String str;
			while((str=br.readLine())!=null){
				str.trim();
				String[] st=str.split(",");
				for(int i=0;i<st.length;i++){
					double d=Double.parseDouble(st[0].trim());
					double d1=Double.parseDouble(st[1].trim());//0.05
					double d2=Double.parseDouble(st[2].trim());//0.1
					double d3=Double.parseDouble(st[3].trim());//0.2ppb
					double d4=Double.parseDouble(st[4].trim());//0.4
					double d5=Double.parseDouble(st[5].trim());//0.8
					receives.add(d5);//y
					xList.add(d);//x
				}
			}
	    }catch (Exception e) {
			e.printStackTrace();
	    }finally{
	    	if(br!=null){
	    		try {
					br.close();
				} catch (Exception e) {}
	    	}if(isr!=null){
	    		try {
	    			isr.close();
				} catch (Exception e) {}
	    	}if(is!=null){
	    		try {
					is.close();
				} catch (Exception e) {}
	    	}
	    }
	    return receives;
	}
		

		
}
