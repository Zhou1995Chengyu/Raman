package zjgy.raman.view;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;



public class MyChartServiceA{
	
	public static GraphicalView mGraphicalView;
	public static XYMultipleSeriesDataset multipleSeriesDataset;
	public static XYMultipleSeriesRenderer multipleSeriesRenderer;
	
	public static XYSeries flourdata; //曲线数据集
	public static XYSeriesRenderer flour;//曲线渲染器
	
	
	private Context context;
	
	//构造器初始化  context 曲线图页面布局
	public MyChartServiceA(Context context){
		this.context=context;
	}
	
	/**
	 * 获取图表
	 * 
	 * @return
	 */
	public GraphicalView getGraphicalView(){
		mGraphicalView=ChartFactory.getCubeLineChartView(context, multipleSeriesDataset, multipleSeriesRenderer, 0.1f);
		//ChartFactory android图表工具
		return mGraphicalView;
	}
	
	/**
	 * 获取数据集，及xy坐标的集合
	 * 
	 * @param curveTitle
	 */
	public void setXYMultipleSeriesDataset(){
		multipleSeriesDataset=new XYMultipleSeriesDataset();//初始化
		flourdata=new XYSeries("flourdata");
		multipleSeriesDataset.addSeries(flourdata);
	}
	
	
	/**
     * 获取渲染器{曲线图各种属性}
	 * 
	 * @param maxX
	 *            x轴最大值
	 * @param maxY
	 *            y轴最大值
	 * @param chartTitle
	 *            曲线的标题
	 * @param xTitle
	 *            x轴标题
	 * @param yTitle
	 *            y轴标题
	 * @param axeColor
	 *            坐标轴颜色
	 * @param labelColor
	 *            标题颜色
	 * @param curveColor
	 *            曲线颜色
	 * @param gridColor
	 *            网格颜色
	 */
	
	public void setXYMultipleSeriesRenderer(){
		multipleSeriesRenderer=new XYMultipleSeriesRenderer();
		multipleSeriesRenderer.setChartTitle("光谱曲线");
		multipleSeriesRenderer.setXTitle("");
		multipleSeriesRenderer.setYTitle("");
		multipleSeriesRenderer.setRange(new double[]{0,60,0,1000});;// xy轴的范围
		multipleSeriesRenderer.setLabelsColor(Color.RED);
		multipleSeriesRenderer.setXLabels(5);
		multipleSeriesRenderer.setYLabels(5);//设置Y轴刻度个数
		multipleSeriesRenderer.setXLabelsAlign(Align.LEFT);
		multipleSeriesRenderer.setYLabelsAlign(Align.RIGHT);
		multipleSeriesRenderer.setAxisTitleTextSize(0);
		multipleSeriesRenderer.setChartTitleTextSize(10);
		multipleSeriesRenderer.setLabelsTextSize(11);
		multipleSeriesRenderer.setPointSize(8f);// 曲线描点尺寸
		multipleSeriesRenderer.setMargins(new int[] { 15, 40, 5, 15 });
		multipleSeriesRenderer.setShowGrid(true);
		multipleSeriesRenderer.setZoomEnabled(true, true);
		multipleSeriesRenderer.setAxesColor(Color.argb(0xff, 0x00, 0xff, 0x00));// 图表边界颜色
		multipleSeriesRenderer.setGridColor(Color.argb(0xff, 0x00, 0x00, 0x00));// 图表网格颜色
		multipleSeriesRenderer.setBackgroundColor(Color.WHITE);// 背景色
		multipleSeriesRenderer.setAxesColor(Color.GRAY);
		multipleSeriesRenderer.setXLabelsColor(Color.BLACK);
		multipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
		multipleSeriesRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0xff, 0xff));// 边距背景色，默认背景色为黑色，这里修改为白色
		multipleSeriesRenderer.setShowLegend(false);
		
		flour = new XYSeriesRenderer();
		// 设置曲线的颜色
		flour.setColor(Color.argb(0xff, 0xff, 0x00, 0x00));
		// 设置曲线宽度
		flour.setLineWidth(3f);
		multipleSeriesRenderer.addSeriesRenderer(flour);
	}
	
	/**
	 * 添加新的数据，多组，更新曲线，只能运行在主线程
	 * 
	 * @param xList
	 * @param yList
	 */
	
	public static void updateChart(){
		double maxY=0;
		double minY=0;
		double maxX=0;
		double minX=2000;
		//Y 最大值
		maxY=maxY<flourdata.getMaxY()?flourdata.getMaxY():maxY;
		// Y 最小值
		if(flourdata.getMinY()>0){
			minY=minY<flourdata.getMinY() ? flourdata.getMinY() : minY;
		}
		// X 最大值
		maxX = maxX < flourdata.getMaxX() ? flourdata.getMaxX() : maxX;
		minX=minX>flourdata.getMinX()?flourdata.getMinX():minX;
		// 动态设定xy轴的范围
		multipleSeriesRenderer.setRange(new double[] {minX, maxX, minY,
				maxY+500});
		mGraphicalView.invalidate();// 此处也可以调用invalidate() 刷新图表
	}
}
