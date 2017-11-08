package zjgy.raman.domain;

import org.litepal.crud.DataSupport;

public class ChartData extends DataSupport {

	private int id;
	private String number;
	private String name;
	private String type;
	
	public ChartData(){
		
	}
	
	
	public ChartData(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public ChartData(String number,String name, String type) {
		super();
		this.number=number;
		this.name = name;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
