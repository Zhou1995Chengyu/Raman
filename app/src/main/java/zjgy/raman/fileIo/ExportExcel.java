package zjgy.raman.fileIo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.raman.AdminLogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;



import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import zjgy.raman.domain.Data;

/**
 * Created by joker on 2016/1/30.
 */
public class ExportExcel{
    private static Toast toast;

	public static void writeExcel(Context context, List<Data> exportDate, String fileName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            AdminLogin.showToast( "SD卡不可用");
            return;
        }
        String[] title = {"No.", "编号", "类别", "时间","浓度"};
        //String sdCard=Environment.getExternalStorageState();
       // File dire = new File(sdCard.getAbsolutePath()+"/Myfiles");
        
        
        Calendar calendar = Calendar.getInstance();
		String year = ""+calendar.get(Calendar.YEAR);
		String monthOfYear = ""+(calendar.get(Calendar.MONTH)+1);
		String dayOfMonth = ""+calendar.get(Calendar.DAY_OF_MONTH);
		String hour=""+calendar.get(Calendar.HOUR);
		String minute=""+calendar.get(Calendar.MINUTE); 
		String second=""+calendar.get(Calendar.SECOND);
		String strFile=year+"-"+monthOfYear+"-"+dayOfMonth+"-"+hour+"-"+minute+"-"+second;
        
        File file1=new File("/mnt/udisk1/disk-1/"+strFile);
        file1.mkdirs();
        File file=new File("/mnt/udisk1/disk-1/"+strFile+"/"+fileName + ".xls");
        Log.d("zcy", "fileNameStr :" + file.getName());
        //创建Excel工作表
        WritableWorkbook wwb = null;
        OutputStream os=null;
        try {
            os = new FileOutputStream(file);
            wwb = Workbook.createWorkbook(os);
            //添加第一个工作表并设置第一个Sheet的名字
            WritableSheet sheet = wwb.createSheet("数据", 0);
            Label label;
            for (int i = 0; i < title.length; i++) {
                //Label(x,y,z)代表单元格的第x+1列，第y+1行，内容z
                //在Label对象的子对象中指明单元格的位置和内容
                label = new Label(i, 0, title[i], getHeader());
                //将定义好的单元格添加到工作表中
                sheet.addCell(label);
            }
            for (int i = 0; i < exportDate.size(); i++) {
                Data data= exportDate.get(i);
                Label no = new Label(0, i + 1,(i+1)+"");
                Label number = new Label(1, i + 1, data.getNumber());
                Label type = new Label(2, i + 1, data.getType());
                Label time = new Label(3, i + 1, data.getTime());
                Label concentration = new Label(4, i + 1, data.getConcentration());
                sheet.addCell(no);
                sheet.addCell(number);
                sheet.addCell(type);
                sheet.addCell(time);
                sheet.addCell(concentration);
                copyFile("/data/data/com.example.goldstandrad/files/"+data.getNumber()+".txt","/mnt/udisk1/disk-1/"+strFile+"/"+data.getNumber() + ".txt");
            }
            wwb.write();
            AdminLogin.showToastYes("写入成功");
        } catch (FileNotFoundException e) {
            AdminLogin.showToast( "写入失败");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            //
            if (wwb != null) {
                try {
                    wwb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }if(os!=null){
            	try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
    }

    private static CellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
        try {
            font.setColour(Colour.BLUE);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(Alignment.CENTRE); //左右居中
            format.setVerticalAlignment(VerticalAlignment.CENTRE); //上下居中
            format.setBackground(Colour.YELLOW);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    public static void copyFile(String oldPath, String newPath) {   
        try {   
            int bytesum = 0;   
            int byteread = 0;   
            File oldfile = new File(oldPath);   
            if (oldfile.exists()) { //文件存在时   
                InputStream inStream = new FileInputStream(oldPath); //读入原文件   
                FileOutputStream fs = new FileOutputStream(newPath);   
                byte[] buffer = new byte[1444];   
                int length;   
                while ( (byteread = inStream.read(buffer)) != -1) {   
                    bytesum += byteread; //字节数 文件大小   
                    System.out.println(bytesum);   
                    fs.write(buffer, 0, byteread);   
                }   
                inStream.close();   
            }   
        }   
        catch (Exception e) {   
            System.out.println("复制单个文件操作出错");   
            e.printStackTrace();   
   
        }   
   
    }   
    
}
