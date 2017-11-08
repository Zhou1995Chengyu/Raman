package zjgy.raman.fileIo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
  

public class FileHelper {  
  
    private Context mContext;  
  
    //空参数构造函数，传入的值为空时，不出错  
    public FileHelper() {  
    }  
  
    public FileHelper(Context mContext) {  
        super();  
        this.mContext = mContext;  
    }  
  
    /* 
    * 定义文件保存的方法，写入到文件中，所以是输出流 
    * */  
    public void save(String name, List<Integer> content) throws Exception {
        //Context.MODE_PRIVATE权限，只有自身程序才能访问，而且写入的内容会覆盖文本内原有内容  
        /*FileOutputStream output = mContext.openFileOutput(name, Context.MODE_PRIVATE);  
        ObjectOutputStream oos=new ObjectOutputStream(output);
        oos.writeDouble(content);  //将String字符串以字节流的形式写入到输出流中  
        output.close();*/
        //关闭输出流  
        //files/
        File file=new File("/data/data/com.example.raman/files");
        if (!file.exists()){//判断指定的路径或者指定的目录文件是否已经存在。
        	file.mkdir();//新建文件夹
        }
        FileWriter fw=new FileWriter("/data/data/com.example.raman/files/"+name+".txt");
        BufferedWriter bw=new BufferedWriter(fw);
        for(int i=0;i<content.size();i++){
	        String str=content.get(i)+"\n";
	        bw.write(str);
        }
        bw.close();
        fw.close();
        
    }  
  

    public static List<Integer> readTxt(String fileTxt) {
        List<Integer> receives = new ArrayList<Integer>();
        BufferedReader br = null;
        InputStreamReader isr = null;
        InputStream is = null;
        try {
            File file = new File("/data/data/com.example.raman/files/" + fileTxt + ".txt");
            is = new FileInputStream(file);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String str;
            while((str=br.readLine())!=null) {
                str.trim();
                receives.add(Integer.parseInt(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
        return receives;
    }

    /* 
    * 定义文件读取的方法 
    * */  
    public String read(String filename) throws IOException {  
        //打开文件输入流  
        FileInputStream input = mContext.openFileInput(filename);  
        //定义1M的缓冲区  
        byte[] temp = new byte[1024];  
        //定义字符串变量  
        StringBuilder sb = new StringBuilder("");  
        int len = 0;  
        //读取文件内容，当文件内容长度大于0时，  
        while ((len = input.read(temp)) > 0) {  
            //把字条串连接到尾部  
            sb.append(new String(temp, 0, len));  
        }  
        //关闭输入流  
        input.close();  
        //返回字符串  
        return sb.toString();  
    }  
  
}
