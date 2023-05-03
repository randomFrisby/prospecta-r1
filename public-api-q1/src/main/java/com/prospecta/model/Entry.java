package com.prospecta.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Entry {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entryId;
	
    @JsonProperty("API")
    private String api;
    
    @JsonProperty("Description")
    private String description;
    
    @JsonProperty("Auth")
    private String auth;
    
    @JsonProperty("HTTPS")
    private Boolean https;
    
    @JsonProperty("Cors")
    private String cors;
    
    @JsonProperty("Link")
    private String link;

    @JsonProperty("Category")
    private String category;
}
