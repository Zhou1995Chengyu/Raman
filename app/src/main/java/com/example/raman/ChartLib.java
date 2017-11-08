package com.example.raman;

import java.util.ArrayList;
import java.util.List;



import zjgy.raman.adapter.ChartLibAdapter;
import zjgy.raman.domain.ChartData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

public class ChartLib extends Activity implements OnClickListener {
	
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private EditText et1;
	private ListView listView;
	private Intent intent;
	
	private ChartLibAdapter alc;
	private List<ChartData> charts=new ArrayList<ChartData>();
	public static int index=-1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.chart_lib);
		
		bt1=(Button)findViewById(R.id.chart_lib_button1);
		bt2=(Button)findViewById(R.id.chart_lib_button2);
		bt3=(Button)findViewById(R.id.chart_lib_button3);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);

		et1=(EditText)findViewById(R.id.chart_lib_query_et1);


		listView=(ListView)findViewById(R.id.list_chart_view);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
				public void onItemClick(AdapterView<?> parent,View v,int position,long id){
					index=position;
					alc.notifyDataSetInvalidated();
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.chart_lib_button1://查询S
				charts=query(et1.getText().toString());
				alc=new ChartLibAdapter(v.getContext(),R.layout.listchartview,charts);
				listView.setAdapter(alc);
				
				break;
			case R.id.chart_lib_button2://详情
				if(index==-1){
					AdminLogin.showToast("请先选择数据");
					break;
				}
				intent=new Intent(ChartLib.this,ChartLibQuery.class);
				intent.putExtra("number",charts.get(index).getNumber());
				this.finish();
				startActivity(intent);	
				break;
			case R.id.chart_lib_button3:
				intent=new Intent(ChartLib.this,Main.class);
				this.finish();
				startActivity(intent);	
				break;
			default:break;
		}
	}

	private List<ChartData> query(String name){
		List<ChartData> chartList=new ArrayList<ChartData>();
		List<ChartData> lists=DataSupport.findAll(ChartData.class);
		for(ChartData chart:lists){
			if(name==null||name.length()==0||chart.getName().equals(name)){
				chartList.add(chart);
			}
		}
		return chartList;
	}
}
