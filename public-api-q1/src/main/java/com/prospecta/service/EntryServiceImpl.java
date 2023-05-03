package com.prospecta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prospecta.exception.EntryException;
import com.prospecta.model.ApiData;
import com.prospecta.model.Entry;
import com.prospecta.model.EntryDTO;
import com.prospecta.repository.EntryRepo;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntryRepo entryRepo;
	
	
	// PART 1: Create an API that lists the title, description based on the category passed as an input parameter.
	
	@Override
	public List<EntryDTO> getTitleAndDescription(String category) throws EntryException {
		
		String uri = "https://api.publicapis.org/entries";
		
		// making use of restTemplate because I need all the entries in my Java application 
		// for extracting title and description from entries.
		ApiData data = restTemplate.getForObject(uri, ApiData.class);
		
		// According to the API documentation :-
		// `For categories like "Science & Math" which have a space and an ampersand, 
		// the query is simply the first word. 
		// Using "Science & Math" as an example, the correct query would be category=science`
		
		// spliting the category by space and 
		// taking the first index as the category string
		String[] strArr = category.split(" ");
		String myCategory = strArr[0];
		
		// lambda expression for filtering and mapping  
		List<EntryDTO> list  = data.getEntries()
								   .stream()
								   .filter(e -> e.getCategory().equals(myCategory))
								   .map(e -> new EntryDTO(e.getApi(), e.getDescription()))
								   .collect(Collectors.toList());
		
		return list;
		
	}

	
	// PART 2: Create an API that would save a new entry with 
	// all the relevant properties which retrieves values from the endpoint GET /entries.
	@Override
	public Entry createEntry(Entry entry) {
		
		String uri = "https://api.publicapis.org/entries";
	
		ApiData data = restTemplate.getForObject(uri, ApiData.class);
		
		// getting all the entries
		List<Entry> entries = data.getEntries();

		entries.add(entry);
		
		// using local database for storing the entry
		
		for(int i = 0; i < entries.size(); i++) {
			
			entryRepo.save(entries.get(i));
			
		}
		
		return entry;
		
	}
	
	// PART 3: Question: what are the key things you would consider when creating/consuming an API to ensure that it is secure and reliable?
	
	/*
	1. Validating input sent by the user before persisting it in our database. We can make use of validation API in java.
	
	2. Using strong Authentication and Authorization logic, this ensures the person with right authority 
	can access resources. We can make use Spring Security which provides built-in strong security features, 
	we can add this security in our	application by configuring it and can use different mechanism like OAuth2.0
	
	3. Exposing only limited data which is necessary: APIs may contains sensitive information like 
	password and other information that need not to be shared.
	
	4. Limiting the per user request in a day or any time span.
	
	5. Handle all the exceptions so that the flow of our application will remain smooth.

	 */
	

}
