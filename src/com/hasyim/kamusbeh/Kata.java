package com.hasyim.kamusbeh;

public class Kata {

	private long id;
	private String indonesia;
	private String inggris;
	private String jerman;
	
	public Kata(){
		
	}
	
	//id
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	
	//indonesia
	public String getIndonesia(){
		return indonesia;
	}
	
	public void setIndonesia(String indonesia){
		this.indonesia = indonesia;
	}
	
	//inggris
	public String getInggris(){
		return inggris;
	}
	
	public void setInggris(String inggris){
		this.inggris = inggris;
	}
	
	//jerman
	public String getJerman(){
		return jerman;
	}
	
	public void setJerman(String jerman){
		this.jerman = jerman;
	}
	
	public String toString(){
		return "Kata  " + indonesia +"  " +inggris+"  " +jerman;
	}
}
















