package com.munro.bean;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;


public class MunroBean extends CsvToBean<Object> {



    @CsvBindByName(column = "Running No")
    private int runningNo;

    @CsvBindByName(column = "DoBIH")
    private int doBIH;
    
    @CsvBindByName(column = "Streetmap")
    String streetmap;
    
    @CsvBindByName (column = "Geograph")
    String geograph;
    
    @CsvBindByName(column = "Hill-bagging")
    String hillBagging;
    
    @CsvBindByName(column = "Name")
    String name;
    
    @CsvBindByName(column = "SMC Section")
    int SMCSection;
    
    @CsvBindByName(column = "RHB Section")
    String RHBSection;
    
    @CsvBindByName(column = "_Section")
    float _Section;
    
    @CsvBindByName(column = "Height (m)")
    double heightInMeters;
    
    @CsvBindByName(column = "Height (ft)")
    double heightInFt;
    
    @CsvBindByName(column = "Map 1:50")
    String map1_50;
    
    @CsvBindByName(column = "Map 1:25")
    String map1_25;
    
    @CsvBindByName(column = "Grid Ref")
    String gridRef;
    
    @CsvBindByName(column = "GridRefXY")
    String gridRefXY;
    
    @CsvBindByName(column = "xcoord")
    Double xcoord;
    
    @CsvBindByName(column = "ycoord")
    Double ycoord;
    
    @CsvBindByName(column = "1891")
    String year1891;
    
    @CsvBindByName(column = "1921")
    String year1921;
    
    @CsvBindByName(column = "1933")
    String year1933;
    
    @CsvBindByName(column = "1953")
    String year1953;
    
    @CsvBindByName(column = "1969")
    String year1969;
    @CsvBindByName(column = "1974")
    String year1974;
    
    @CsvBindByName(column = "1981")
    
    String year1981;
    
    @CsvBindByName(column = "1984")
    String year1984;
    
    @CsvBindByName(column = "1990")
    String year1990;
    
    @CsvBindByName(column = "1997")
    String year1997;
    
    @CsvBindByName(column = "Post 1997")
    String post1997;
    
    @CsvBindByName(column = "Comments")
    String comments;

	public int getRunningNo() {
		return runningNo;
	}

	public int getDoBIH() {
		return doBIH;
	}

	public String getStreetmap() {
		return streetmap;
	}

	public String getGeograph() {
		return geograph;
	}

	public String getHillBagging() {
		return hillBagging;
	}

	public String getName() {
		return name;
	}

	public int getSMCSection() {
		return SMCSection;
	}

	public String getRHBSection() {
		return RHBSection;
	}

	public float get_Section() {
		return _Section;
	}

	public double getHeightInMeters() {
		return heightInMeters;
	}

	public double getHeightInFt() {
		return heightInFt;
	}

	public String getMap1_50() {
		return map1_50;
	}

	public String getMap1_25() {
		return map1_25;
	}

	public String getGridRef() {
		return gridRef;
	}

	public String getGridRefXY() {
		return gridRefXY;
	}

	public double getXcoord() {
		return xcoord;
	}

	public double getYcoord() {
		return ycoord;
	}

	public String getYear1891() {
		return year1891;
	}

	public String getYear1921() {
		return year1921;
	}

	public String getYear1933() {
		return year1933;
	}

	public String getYear1953() {
		return year1953;
	}

	public String getYear1969() {
		return year1969;
	}

	public String getYear1974() {
		return year1974;
	}

	public String getYear1981() {
		return year1981;
	}

	public String getYear1984() {
		return year1984;
	}

	public String getYear1990() {
		return year1990;
	}

	public String getYear1997() {
		return year1997;
	}

	public String getPost1997() {
		return post1997;
	}

	public String getComments() {
		return comments;
	}
    

    // getters and setters

}
