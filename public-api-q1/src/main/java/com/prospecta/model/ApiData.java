package com.prospecta.model;

import java.util.List;

import lombok.Data;

@Data
public class ApiData {
	
	private Integer count;
	
	private List<Entry> entries;
}
