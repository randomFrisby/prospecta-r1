package com.prospecta.service;

import java.util.List;

import com.prospecta.exception.EntryException;
import com.prospecta.model.Entry;
import com.prospecta.model.EntryDTO;

public interface EntryService {

	public List<EntryDTO> getTitleAndDescription(String category) throws EntryException;
	
	public Entry createEntry(Entry entry);
}
