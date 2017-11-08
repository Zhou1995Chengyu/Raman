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
import android.widget.ProgressBar;

public class QueryChart extends Activity implements OnClickListener {

	private Button bt1;
	private Button bt2;
	private Button bt3;
	private EditText et1;
	private EditText et2;
	private EditText et3;
	private Intent intent;
	private LinearLayout linearLayout;
	private MyChartServiceA chart;
	private GraphicalView mView;
	private List<Double> list ;
	private List<Double> xList=new ArrayList<Double>();
	private int x=0;
	private Data data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.query_chart);
		
		bt1=(Button)findViewById(R.id.query_chart_button1);
		bt2=(Button)findViewById(R.id.query_chart_button2);
		bt3=(Button)findViewById(R.id.query_chart_button3);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		
		linearLayout=(LinearLayout)findViewById(R.id.query_chart_linearLayout);
		
		chart=new MyChartServiceA(QueryChart.this);
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
		
		et1=(EditText)findViewById(R.id.query_chart_et1);
		et2=(EditText)findViewById(R.id.query_chart_et2);
		et3=(EditText)findViewById(R.id.query_chart_et3);

		Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
		String number=intent.getStringExtra("number");
		List<Data> datas= DataSupport.where("number=?",number).find(Data.class);
		data=datas.get(0);
		et1.setText(number);
		et2.setText(data.getType());
		et3.setText(data.getConcentration());

		list=readTxt("aaa");
		for (int j = 0; j <list.size(); j++) {
			chart.flourdata.add(xList.get(j),list.get(j));
			chart.updateChart();
		}
	}

//按钮点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

			case R.id.query_chart_button1://删除
				//pb.set
				showNormalDialog();
				break;
			case R.id.query_chart_button2:

				break;
			case R.id.query_chart_button3:
				intent = new Intent(QueryChart.this, Query.class);
				this.finish();
				startActivity(intent);
				break;
			default:
				break;
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
						DataSupport.deleteAll(Data.class, "number=?", et1.getText().toString());
						intent = new Intent(QueryChart.this, Query.class);
						startActivity(intent);
						onResume();
					}
				});
		normalDialog.show();
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

