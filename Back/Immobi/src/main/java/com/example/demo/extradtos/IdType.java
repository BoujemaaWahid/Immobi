package com.example.demo.extradtos;

public enum IdType {
	PHONE(1), EMAIL(2), USERNAME(3);
	
	private int value;
	
	private IdType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
