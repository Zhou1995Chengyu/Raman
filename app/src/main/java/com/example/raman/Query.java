package com.example.raman;

import java.util.ArrayList;
import java.util.List;






import zjgy.raman.adapter.DataAdapter;
import zjgy.raman.domain.Data;




import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

public class Query extends Activity implements OnClickListener {
	
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private Intent intent;
	private ListView listView;
	public static List<Data> datas=new ArrayList<Data>();
	private DataAdapter da;
	public static int index=-1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.query);
		
		bt1=(Button)findViewById(R.id.query_button1);
		bt2=(Button)findViewById(R.id.query_button2);
		bt3=(Button)findViewById(R.id.query_button3);
		bt4=(Button)findViewById(R.id.query_button4);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		
		datas= DataSupport.findAll(Data.class);

		listView=(ListView)findViewById(R.id.query_list_view);
		da=new DataAdapter(this,R.layout.listview,datas);
		
		listView.setAdapter(da);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
				public void onItemClick(AdapterView<?> parent,View v,int position,long id) {
					index = position;
					//v.setBackgroundResource(R.drawable.text_listview_bg);
					da.notifyDataSetInvalidated();
				}
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
			case R.id.query_button1://清空
				DataSupport.deleteAll(Data.class);
				// TODO Auto-generated method stub 清空数据点
				break;
			case R.id.query_button2://详情
				if(index==-1){
					AdminLogin.showToast("请先选择数据");
					break;
				}
				intent=new Intent(Query.this,QueryChart.class);
				intent.putExtra("number",datas.get(index).getNumber());
				this.finish();
				startActivity(intent);
				break;
			case R.id.query_button3:
				
				break;
			case R.id.query_button4:
				intent=new Intent(Query.this,Main.class);
				this.finish();
				startActivity(intent);
				break;
			default:break;
		}
	}
}
