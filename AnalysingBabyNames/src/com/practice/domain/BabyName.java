package com.practice.domain;

public class BabyName {
	private String name;
	private String gender;
	private int numberOfBirths;
	private int year;

	public BabyName() {

	}

	public BabyName(String name, String gender, int numberOfBirths, int year) {
		super();
		this.name = name;
		this.gender = gender;
		this.numberOfBirths = numberOfBirths;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public int getNumberOfBirths() {
		return numberOfBirths;
	}

	public int getYear() {
		return year;
	}

	
}
