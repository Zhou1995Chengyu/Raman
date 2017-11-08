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
import zjgy.raman.fileIo.FileHelper;
import zjgy.raman.view.MyChartServiceA;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class CheckChart extends Activity implements OnClickListener {

	private Button bt1;
	private Button bt2;
	private Button bt3;
	
	private EditText et1;
	private EditText et2;
	private EditText et3;
	private String time;
	private Intent intent;
	private LinearLayout linearLayout;
	
	private MyChartServiceA chart;
	private GraphicalView mView;
	private List<Double> list ;


	private int x=0;
	
	private List<Double> xList=new ArrayList<Double>();
	private List<Double> yList;
	private List<Integer> listParameter=new ArrayList<Integer>();//计算参数
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.check_chart);
		
		bt1=(Button)findViewById(R.id.check_chart_button1);
		bt2=(Button)findViewById(R.id.check_chart_button2);
		bt3=(Button)findViewById(R.id.check_chart_button3);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		
		linearLayout=(LinearLayout)findViewById(R.id.check_chart_linearLayout);
		
		chart=new MyChartServiceA(CheckChart.this);
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

		Intent intent=getIntent();//获取上个页面传来的数据
		String number=intent.getStringExtra("number");
		time=intent.getStringExtra("time");
		et1=(EditText)findViewById(R.id.check_chart_et1);
		et2=(EditText)findViewById(R.id.check_chart_et2);
		et3=(EditText)findViewById(R.id.check_chart_et3);
		et1.setText(number);
		et2.setText("");
		et3.setText("0.8ppb");
		list=readTxt(et1.getText().toString());
		
		for (int j = 0; j <list.size(); j++) {
			chart.flourdata.add(xList.get(j),list.get(j));
			chart.updateChart();
		}
		System.out.println(list);
		System.out.println(xList);

		listParameter= FileHelper.readTxt("app");//计算参数 可能 listParameter.size()=0

	}

//按钮点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.check_chart_button1://保存
				Data data=new Data();
				data.setNumber(et1.getText().toString());
				data.setType(et2.getText().toString());
				data.setConcentration(et3.getText().toString());
				data.setTime(time);
				// TODO Auto-generated method stub
				//保存数据点未完成

				data.save();
				break;
			case R.id.check_chart_button2:
				intent=new Intent(CheckChart.this, SetParameter.class);
				this.finish();
				startActivity(intent);
				break;
			case R.id.check_chart_button3:
				intent=new Intent(CheckChart.this, Check.class);
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
