package zjgy.raman.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.raman.Query;
import com.example.raman.R;

import zjgy.raman.domain.Data;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<Data> {

	public static Map<Integer, Boolean> isCheckedMap=new HashMap<Integer, Boolean>();
	private int resourceID;
	private ViewHolder vh ;
	public DataAdapter(Context context, int textViewResourceId, List<Data> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceID=textViewResourceId;
		for(int i=0;i<2000;i++){
			isCheckedMap.put(i, false);
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	//	return super.getView(position, convertView, parent);
		
		Data data=getItem(position);
		if(convertView==null){
			convertView=LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
			vh=new ViewHolder();
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder)convertView.getTag();
		}
		View view=convertView;
		//View view=LayoutInflater.from(getContext()).inflate(resourceID, parent,false);
		TextView tv1=(TextView)view.findViewById(R.id.listView_tv1);
		tv1.setText((position+1)+"");	
		TextView tv2=(TextView)view.findViewById(R.id.listView_tv2);
		tv2.setText(data.getNumber());
		TextView tv3=(TextView)view.findViewById(R.id.listView_tv3);
		tv3.setText(data.getType());
		TextView tv4=(TextView)view.findViewById(R.id.listView_tv4);
		tv4.setText(data.getTime());
		TextView tv5=(TextView)view.findViewById(R.id.listView_tv5);
		tv5.setText(data.getConcentration());

		//listView选中效果(颜色变化)
		//在listView 的点击事件中应调用DataAdapter对象的notifyDataSetInvalidated()方法刷新listView
		if(position== Query.index)
		{
			convertView.setBackgroundColor(Color.argb(23,23,23,23));
		}else
		{
			convertView.setBackgroundColor(Color.argb(23,255,255,255));
		}

		return view;
	}
	
	static class ViewHolder {  
		private CheckBox cb;
    } 
	
}
