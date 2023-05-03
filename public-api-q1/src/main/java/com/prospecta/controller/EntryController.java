package com.prospecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.exception.EntryException;
import com.prospecta.model.Entry;
import com.prospecta.model.EntryDTO;
import com.prospecta.service.EntryService;

@RestController
public class EntryController {
	
	@Autowired
	private EntryService service;
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDTO>> getTitlesAndDescriptionsHandler(@PathVariable("category") String category) throws EntryException {
		
		
		List<EntryDTO> entryDtos = service.getTitleAndDescription(category);
		
		
		return new ResponseEntity<List<EntryDTO>>(entryDtos,HttpStatus.OK);
	}
	
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> createEntryHandler (@RequestBody Entry entry) {
		
		Entry savedEntry = service.createEntry(entry);
		
		return new ResponseEntity<Entry>(savedEntry, HttpStatus.CREATED);
	}
}
