package com.practice.domain;

import java.util.Comparator;

public class SortBasedOnNumberOfBirths implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		int numberOfBirths1 = ((BabyName)o1).getNumberOfBirths();
		int numberOfBirths2 = ((BabyName)o2).getNumberOfBirths();
		String name1 = ((BabyName)o1).getName();
		String name2 = ((BabyName)o2).getName();
		//decreasing order
		if(numberOfBirths1 == numberOfBirths2) {
			name1.compareTo(name2);
		}
		return numberOfBirths2-numberOfBirths1;
	}
}
