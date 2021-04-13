package com.munro.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import com.munro.bean.MunroBean;


@Service
public class MunroService {

	public static String sortASC = "asc";
	public static String sortDESC = "desc";
	
	List<MunroBean> munroBeansList;

	private static Logger logger = LoggerFactory.getLogger(MunroService.class);

	
	public MunroService() {
		if (munroBeansList == null) {
			try {
				logger.info("Loading .........");
				munroBeansList = loadData();
			} catch (IOException | URISyntaxException e) {
				logger.error("MunroService Error: Unable to read the CSV file.");
				e.printStackTrace();

			}
		}

	}

	private List<MunroBean> loadData() throws IOException, URISyntaxException {
 
		//TODO make file path configurable

		String dataFile = "src/main/resources/munrotab_v6.2.csv";
		
		Path filePath = Paths.get(dataFile);
		

		try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {

			HeaderColumnNameMappingStrategy<MunroBean> strategy = new HeaderColumnNameMappingStrategy<>();
			strategy.setType(MunroBean.class);

			CsvToBean<MunroBean> csvToBean = new CsvToBeanBuilder<MunroBean>(br).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<MunroBean> resultList = csvToBean.parse();

			return resultList;
		}
	}

	public List<MunroVO> fetchMunros() {
		List<MunroVO> munroVOList = new ArrayList<MunroVO>();

		if (munroBeansList != null) {
			for (MunroBean bean : munroBeansList) {
				MunroVO vo = new MunroVO(bean);
				munroVOList.add(vo);
			}

		}
		return munroVOList;
	}
	
	
	public List<MunroVO> fetchMunros(FilterCriteria filter, String sortHeight, String sortName) {
	
		List<MunroVO> munroVOList = new ArrayList<MunroVO>();
		//TODO: To move the complete pipeline to Streams
		if (munroBeansList != null && filter != null) {
			
			String filterType = (filter.getMunroType() != null) ? filter.getMunroType().toString() : "";
			
			int count = 0; 
			
			for (MunroBean bean : munroBeansList) {
				
				
				if ((filter.getResultsLimit() > 0) && (count >= filter.getResultsLimit())) {
					break;
				}
				
				if (!validMunroType(bean, filterType)) {
					continue;
				}

				if (!validHeight(bean, filter.maxHeight, filter.minHeight)) {
					continue;
				}
				
				MunroVO vo = new MunroVO(bean);
				munroVOList.add(vo);
				count++;
				
			}
		}

		//TODO: Add the sort order to the sort fields
		return munroVOList
		        .stream()
		        .sorted(getComparator(sortHeight, sortName))		        
		        .collect(Collectors.toList());

	}
	
	private Comparator<MunroVO> getComparator(String sortHeight, String sortName) {
		
		logger.debug("Height: " + sortHeight);
		logger.debug("Name: " + sortName);
		
		//Height comparator
		Comparator<MunroVO> compareByHeight = Comparator.comparingDouble(MunroVO::getHeightInMeters);
		if (sortHeight.equalsIgnoreCase(sortDESC)) {
			compareByHeight = compareByHeight.reversed();
			logger.info("compareByHeight.reversed()");
		}
		 
		//Name comparator
		Comparator<MunroVO> compareByName = Comparator.comparing( MunroVO::getName );
		if (sortName.equalsIgnoreCase(sortDESC)) {
			compareByName = compareByName.reversed();
			logger.info("compareByName.reversed()");
		}
		
		//Compare by Height and then Name
		Comparator<MunroVO> comparator = compareByHeight.thenComparing(compareByName);
		
		
		return comparator;
	}
	
	private boolean validMunroType(MunroBean bean, String filterType) {
		
		boolean result = true;
		
		//ignore entries with blank Post1997 field
		if (bean.getPost1997() == null || bean.getPost1997().isBlank()) {
			result = false;
		}
		else if(!filterType.isEmpty() && !filterType.equals(bean.getPost1997())){
			result = false;
		}
		
		return result;
	}

	private boolean validHeight(MunroBean bean, double maxHeight, double minHeight ) {
		
		boolean result = true;
		
		if (maxHeight > 0 && bean.getHeightInMeters() > maxHeight) {
			result = false;
		} else if (minHeight > 0 && bean.getHeightInMeters() < minHeight) {
			result = false;
		} 
		
		return result;
		
	}
}

