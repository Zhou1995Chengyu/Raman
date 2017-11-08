package zjgy.raman.domain;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Data extends DataSupport {

	private int id;
	private String	number;
	private String type;
	private String time;
	private String concentration;
	
	
	public Data() {
		super();
	}
	
	public Data(String number, String time, String concentration) {
		super();
		this.number = number;
		this.time = time;
		this.concentration = concentration;
	}
	
	public Data(String number, String type,String time, String concentration) {
		super();
		this.number = number;
		this.type=type;
		this.time = time;
		this.concentration = concentration;
	}

	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
