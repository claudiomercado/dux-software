package com.duxsoftware.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duxsoftware.model.Team;
import com.duxsoftware.service.TeamService;

@RestController
@RequestMapping("/")
public class TeamController {

	private final TeamService teamService;
	
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@GetMapping("/equipos")
	public ResponseEntity<List<Team>> getAllTeam(){
		return ResponseEntity.ok(teamService.getAllTeams());
	}
	
	@GetMapping("/equipo/{id}")
	public ResponseEntity<Object> getTeamById(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.ok(teamService.getTeamById(id));
		} catch (IllegalArgumentException e) {
			Map<String,Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/equipo")
	public ResponseEntity<Object> getTeamByName(@RequestParam(name = "nombre") String name){
		try {
			return ResponseEntity.ok(teamService.getTeamByName(name));
		} catch (IllegalArgumentException e) {
			Map<String,Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/crearEquipo")
	public ResponseEntity<Object> createTeam(@RequestBody Team team){
		try {
			return new ResponseEntity<>(teamService.createTeam(team),HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			Map<String,Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			response.put("code", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/equipo/{id}")
	public ResponseEntity<Object> updateTeam(@PathVariable(name = "id") Long id, @RequestBody Team team){
		try {
			return ResponseEntity.ok(teamService.updateTeam(id,team));
		} catch (IllegalArgumentException e) {
			Map<String,Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/equipo/{id}")
	public ResponseEntity<Object> deleteTeam(@PathVariable(name = "id") Long id){
		try {
			teamService.deleteTeam(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			Map<String,Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
}
