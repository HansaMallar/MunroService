package com.munro.service;


import com.munro.bean.MunroBean;

public class MunroVO {
	public static enum MunroType {MUN,TOP,EMPTY};
	
	String name;
	MunroType munroType; 
    double heightInMeters;
    String gridRef;
    
	public MunroVO(MunroBean bean) {
		if (bean == null) {
			throw new IllegalArgumentException("Invalid argument error.");
		}
		name = bean.getName();
		
		try {
			munroType = MunroType.valueOf(bean.getPost1997());
		}catch (IllegalArgumentException e) {
			munroType = MunroType.EMPTY;
		}
		heightInMeters = bean.getHeightInMeters();
		gridRef = bean.getGridRef();
		
	}

	public String getName() {
		return name;
	}

	public double getHeightInMeters() {
		return heightInMeters;
	}

	public String getGridRef() {
		return gridRef;
	}

	public MunroType getMunroType() {
		return munroType;
	}
	
}
