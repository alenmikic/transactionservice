package com.transactionservice.model;

public class Transaction {
	private long id;
	private int parent_id;
	private double amount;
	private String type;
	
	Transaction(){
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public Transaction(long id,int parent_id, String type, double amount){
		this.id=id;
		this.parent_id=parent_id;
		this.amount=amount;
		this.type=type;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
