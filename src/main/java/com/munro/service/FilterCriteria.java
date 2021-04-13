package com.munro.service;

import com.munro.service.MunroVO.MunroType;

public class FilterCriteria {
	MunroType munroType = null;
	double maxHeight = -1;
	double minHeight = -1;
	int resultsLimit = -1;
	
	public MunroType getMunroType() {
		return munroType;
	}
	public void setMunroType(MunroType munroType) {
		this.munroType = munroType;
	}
	public double getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}
	public double getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(double minHeight) {
		this.minHeight = minHeight;
	}
	public int getResultsLimit() {
		return resultsLimit;
	}
	public void setResultsLimit(int resultsLimit) {
		this.resultsLimit = resultsLimit;
	}
	

}
