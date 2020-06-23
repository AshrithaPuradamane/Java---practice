package com.practice.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BabyNameSearch {
	public void totalBirths(BufferedReader fileReader) {
		Set<Object> girlsSet = new TreeSet<>();
		Set<Object> boysSet = new TreeSet<>();
		int numberOfGirls = 0;
		int numberOfBoys = 0;
		int totalNumber = 0;
		int totalNames = 0;
		try {
			String line = null;
			while ((line = fileReader.readLine()) != null) {
				totalNames++;
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String name = tokenizer.nextToken();
				String gender = tokenizer.nextToken();
				int num = Integer.parseInt(tokenizer.nextToken());
				if (gender.equalsIgnoreCase("F")) {
					numberOfGirls += num;
					girlsSet.add(name);
				} else {
					numberOfBoys += num;
					boysSet.add(name);
				}
			}
			totalNumber = numberOfGirls + numberOfBoys;
			System.out.println("Total births=" + totalNumber);
			System.out.println("Number of unique girls names = " + girlsSet.size());
			System.out.println("Number of unique boys names = " + boysSet.size());
			System.out.println("Total names in the file = " + totalNames);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public int getRank(int year, String name, String gender) {
		BufferedReader fileReader = null;
		List<BabyName> array = new ArrayList<>();
		int rank = 0;
		try {
			if (year == 2012) {
				fileReader = new BufferedReader(new FileReader("data/yob2012short.csv"));
			} else if (year == 2013) {
				fileReader = new BufferedReader(new FileReader("data/yob2013short.csv"));
			} else if (year == 2014) {
				fileReader = new BufferedReader(new FileReader("data/yob2014short.csv"));
			} else {
				System.out.println("No file is found for the year: " + year);
				System.exit(-1);
			}
			String line = null;
			while ((line = fileReader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String nameToken = tokenizer.nextToken();
				String genderToken = tokenizer.nextToken();
				int numToken = Integer.parseInt(tokenizer.nextToken());
				array.add(new BabyName(nameToken, genderToken, numToken, year));
			}
			Collections.sort(array, new SortBasedOnNumberOfBirths());
			Iterator<BabyName> list = array.iterator();
			while (list.hasNext()) {
				BabyName babyName = list.next();
				if (babyName.getGender().equalsIgnoreCase(gender)) {
					rank++;
					if (babyName.getName().equals(name)) {
						break;
					}
				}
			}
			return rank;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return -1;
	}

	public String getName(int year, int rank, String gender) {
		BufferedReader fileReader = null;
		List<BabyName> array = new ArrayList<>();
		String name = null;
		int count = 0;
		try {
			if (year == 2012) {
				fileReader = new BufferedReader(new FileReader("data/yob2012short.csv"));
			} else if (year == 2013) {
				fileReader = new BufferedReader(new FileReader("data/yob2013short.csv"));
			} else if (year == 2014) {
				fileReader = new BufferedReader(new FileReader("data/yob2014short.csv"));
			} else {
				System.out.println("No file is found for the year: " + year);
				System.exit(-1);
			}
			String line = null;
			while ((line = fileReader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String nameToken = tokenizer.nextToken();
				String genderToken = tokenizer.nextToken();
				int numToken = Integer.parseInt(tokenizer.nextToken());
				array.add(new BabyName(nameToken, genderToken, numToken, year));
			}
			Collections.sort(array, new SortBasedOnNumberOfBirths());
			Iterator<BabyName> list = array.iterator();
			while (list.hasNext()) {
				BabyName babyName = list.next();
				if (babyName.getGender().equalsIgnoreCase(gender)) {
					count++;
					if (count == rank) {
						name = babyName.getName();
						break;
					}
				}
			}
			return name;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "NO NAME";
	}

	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		int currentRank = getRank(year, name, gender);
		if (currentRank != -1) {
			System.out.println(name + " born in " + year + " would be " + getName(newYear, currentRank, gender)
					+ " if she was born in " + newYear);
		} else {
			System.out.println(name + " is not found in the " + year + " year.");
		}
	}

	public int yearOfHighestRank(String name, String gender) {
		int rank2012 = getRank(2012, name, gender);
		int rank2013 = getRank(2013, name, gender);
		int rank2014 = getRank(2014, name, gender);
		if (rank2012 > rank2013 && rank2012 > rank2014) {
			return rank2012;
		} else if (rank2013 > rank2014) {
			return rank2013;
		} else {
			return rank2014;
		}
	}

	public double getAverageRank(String name, String gender) {
		int rank2012 = getRank(2012, name, gender);
		int rank2013 = getRank(2013, name, gender);
		int rank2014 = getRank(2014, name, gender);
		return (rank2012 + rank2013 + rank2014) / 3.0;
	}

	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		BufferedReader fileReader = null;
		int totalBirthsRankedHigher = 0;
		try {
			if (year == 2012) {
				fileReader = new BufferedReader(new FileReader("data/yob2012short.csv"));
			} else if (year == 2013) {
				fileReader = new BufferedReader(new FileReader("data/yob2013short.csv"));
			} else if (year == 2014) {
				fileReader = new BufferedReader(new FileReader("data/yob2014short.csv"));
			} else {
				System.out.println("No file is found for the year: " + year);
				System.exit(-1);
			}
			String line;
			int rank = getRank(year, name, gender);
			while ((line = fileReader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String nameToken = tokenizer.nextToken();
				String genderToken = tokenizer.nextToken();
				int numOfBirthsToken = Integer.parseInt(tokenizer.nextToken());
				int currentRank = getRank(year, nameToken, genderToken);
				if (gender.equals(genderToken) && rank > currentRank) {
					totalBirthsRankedHigher += numOfBirthsToken;
					if (nameToken.equals(name)) {
						break;
					}
				}
			}
			return totalBirthsRankedHigher;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void main(String[] args) {
		BabyNameSearch babyNameSearch = new BabyNameSearch();
		System.out.println("Fetching total birth details of 2012:");
		BufferedReader fileReader;
		try {
			fileReader = new BufferedReader(new FileReader("data/yob2012short.csv"));
			babyNameSearch.totalBirths(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------");
		System.out.println("Finding the rank of year:2012, name:Manson, gender:M");
		System.out.println(babyNameSearch.getRank(2012, "Mason", "M"));
		System.out.println("----------------------------------------------");
		System.out.println("Finding the name of year:2012, rank:2, gender:M");
		System.out.println(babyNameSearch.getName(2012, 2, "M"));
		System.out.println("----------------------------------------------");
		System.out.println("Finding what name would have been named if Isabella of 2012 was born in 2014");
		babyNameSearch.whatIsNameInYear("Isabella", 2012, 2014, "F");
		System.out.println("----------------------------------------------");
		System.out.println("Finding year of Highest Rank with name Mason and gender ‘M’:");
		System.out.println(babyNameSearch.yearOfHighestRank("Mason", "M"));
		System.out.println("----------------------------------------------");
		System.out.println("Finding average Rank with name Jacob and gender ‘M’:");
		System.out.println(babyNameSearch.getAverageRank("Jacob", "M"));
		System.out.println("----------------------------------------------");
		System.out.println("Finding total births ranked higher for year:2012, name:Ethan, gender 'M':");
		System.out.println(babyNameSearch.getTotalBirthsRankedHigher(2012, "Ethan", "M"));
		System.out.println("----------------------------------------------");
	}
}
