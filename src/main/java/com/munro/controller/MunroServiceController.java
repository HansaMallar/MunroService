package com.munro.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.munro.service.FilterCriteria;
import com.munro.service.MunroService;
import com.munro.service.MunroVO;
import com.munro.service.MunroVO.MunroType;

@RestController
public class MunroServiceController {
	private MunroService munroService = new MunroService();

	private static final String apiTemplate = "https://localhost/%s";
	private static Logger logger = LoggerFactory.getLogger(MunroServiceController.class);

	@GetMapping("/apis")
	public List<String> apis() {
		List<String> apis = new ArrayList<String>();
		apis.add(String.format(apiTemplate,
				"munros/type=[MUN|TOP] maxHeight=?? minHeight=?? limit=?? sortByHeight=[asc|desc] sortByName=[asc|desc]"));
		return apis;
	}

	@GetMapping(path = "/munros", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MunroVO> fetchMunros(@RequestParam(value = "type", defaultValue = "") String munroType,
			@RequestParam(value = "maxHeight", defaultValue = "-1") double maxHeight,
			@RequestParam(value = "minHeight", defaultValue = "-1") double minHeight,
			@RequestParam(value = "limit", defaultValue = "-1") int limit,
			@RequestParam(value = "sortByName", defaultValue = "asc") String sortByName,
			@RequestParam(value = "sortByHeight", defaultValue = "asc") String sortByHeight)

	{

		FilterCriteria filter = new FilterCriteria();

		if (!munroType.isBlank() && (MunroType.valueOf(munroType).equals(MunroType.MUN)
				|| MunroType.valueOf(munroType).equals(MunroType.TOP))) {

			filter.setMunroType(MunroType.valueOf(munroType));

		}

		if (maxHeight != -1 && minHeight != -1 && minHeight > maxHeight) {
			throw new IllegalArgumentException("MunroServiceController:fetchMunros Invalid height range provided.");
		}

		if (!(MunroService.sortASC.equalsIgnoreCase(sortByName))
				&& (!(MunroService.sortDESC.equalsIgnoreCase(sortByName)))) {
			throw new IllegalArgumentException("MunroServiceController: Invalid sort order for Name.");
		}

		if (!(MunroService.sortASC.equalsIgnoreCase(sortByHeight))
				&& (!(MunroService.sortDESC.equalsIgnoreCase(sortByHeight)))) {
			throw new IllegalArgumentException("MunroServiceController: Invalid sort order for height.");
		}
		filter.setMaxHeight(maxHeight);
		filter.setMinHeight(minHeight);
		filter.setResultsLimit(limit);

		return munroService.fetchMunros(filter, sortByHeight, sortByName);

	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		CustomErrorResponse customError = new CustomErrorResponse();
		customError.setTimestamp(LocalDateTime.now());
		customError.setError(ex.getMessage());
        if (ex instanceof HttpRequestMethodNotSupportedException) {
        	customError.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            
        } else {
        	customError.setStatus(HttpStatus.NOT_FOUND.value());
        	}

		return new ResponseEntity<>(customError,HttpStatus.NOT_FOUND);
	}

	class CustomErrorResponse {
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")

		private LocalDateTime timestamp;
		private int status;
		private String error;
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public int getStatus() {
			return status;
		}
		public String getError() {
			return error;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public void setError(String error) {
			this.error = error;
		}
	}
}