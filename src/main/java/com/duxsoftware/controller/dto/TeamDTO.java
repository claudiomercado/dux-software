package com.duxsoftware.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
	
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "nombre")
	private String name;
	@JsonProperty(value = "liga")
	private String league;
	@JsonProperty(value = "pais")
	private String country;
	
	public TeamDTO(Long id, String name, String league, String country) {
		this.id = id;
		this.name = name;
		this.league = league;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
