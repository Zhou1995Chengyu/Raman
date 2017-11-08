package zjgy.raman.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.raman.ChartLib;
import com.example.raman.R;

import zjgy.raman.domain.ChartData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ChartLibAdapter extends ArrayAdapter<ChartData> {

	public static Map<Integer, Boolean> isCheckedMap=new HashMap<Integer, Boolean>();
	private int resourceID;
	private ViewHolder vh ;
	public ChartLibAdapter(Context context, int textViewResourceId, List<ChartData> objects) {
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
		
		ChartData chart=getItem(position);
		if(convertView==null){
			convertView=LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
			vh=new ViewHolder();
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder)convertView.getTag();
		}
		View view=convertView;
		//View view=LayoutInflater.from(getContext()).inflate(resourceID, parent,false);
		TextView tv1=(TextView)view.findViewById(R.id.listchartView_tv1);
		tv1.setText((position+1)+"");	
		TextView tv2=(TextView)view.findViewById(R.id.listchartView_tv2);
		tv2.setText(chart.getName());
		TextView tv3=(TextView)view.findViewById(R.id.listchartView_tv3);
		tv3.setText(chart.getType());
		if(position== ChartLib.index){
			convertView.setBackgroundColor(Color.argb(23,23,23,23));
		}else
		{
			convertView.setBackgroundColor(Color.argb(23,255,255,255));
		}

		return view;
	}
	
	static class ViewHolder {  
		private CheckBox cb;;  
    } 
	
}
