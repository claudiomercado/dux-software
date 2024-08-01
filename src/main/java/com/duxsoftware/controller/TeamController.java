package com.duxsoftware.controller;

import java.util.List;

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

import com.duxsoftware.controller.dto.TeamDTO;
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
	public ResponseEntity<List<TeamDTO>> getAllTeam(){
		return ResponseEntity.ok(teamService.getAllTeams());
	}
	
	@GetMapping("/equipo/{id}")
	public ResponseEntity<Object> getTeamById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(teamService.getTeamById(id));
	}
	
	@GetMapping("/equipo")
	public ResponseEntity<Object> getTeamByName(@RequestParam(name = "nombre") String name){
		return ResponseEntity.ok(teamService.getTeamByName(name));
	}
	
	@PostMapping("/crearEquipo")
	public ResponseEntity<Object> createTeam(@RequestBody TeamDTO teamDTO){
		return new ResponseEntity<>(teamService.createTeam(teamDTO),HttpStatus.CREATED);
	}
	
	@PutMapping("/equipo/{id}")
	public ResponseEntity<Object> updateTeam(@PathVariable(name = "id") Long id, @RequestBody TeamDTO teamDTO){
		return ResponseEntity.ok(teamService.updateTeam(id,teamDTO));
	}
	
	@DeleteMapping("/equipo/{id}")
	public ResponseEntity<Object> deleteTeam(@PathVariable(name = "id") Long id){
		teamService.deleteTeam(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
