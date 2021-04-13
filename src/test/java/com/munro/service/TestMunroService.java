package com.munro.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;  
import org.junit.AfterClass;  
import org.junit.Before;  
import org.junit.BeforeClass;  
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.munro.controller.TestMunroController;  

public class TestMunroService  {
	    private static Logger logger = LoggerFactory.getLogger(TestMunroService.class);
	
	    @Before  
	    public void setUp() throws Exception {  
	        logger.debug("before");  
	    }  
	  
	    @Test
		public void testfetchMunrosLimit() {
			/*MunroService munroService = new MunroService();
			FilterCriteria filter = new FilterCriteria();
			filter.setMaxHeight(985);
			filter.setMinHeight(940);
			filter.setResultsLimit(2);
			assert(munroService.fetchMunros(filter,"asc","asc").size() <= 2);*/
			
		}
	     
	    @After  
	    public void tearDown() throws Exception {  
	    	logger.debug("after");  
	    }  
	  


	  
}
