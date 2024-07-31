package com.duxsoftware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duxsoftware.model.Team;
import com.duxsoftware.repository.TeamRepository;

@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	public TeamService (TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public List<Team> getAllTeams(){
		return teamRepository.findAll();
	}
	
	public Team getTeamById(Long id) {	
		return teamRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Equipo no encontrado")));		
	}
	
	public Team getTeamByName(String name) {
		return teamRepository.getTeamByName(name)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Equipo con el nombre: "+name+" no encontrado")));
	}
	
	public Team createTeam(Team team) {
		if (team.getName() == null || team.getLeague() == null || team.getCountry() == null
				|| !teamRepository.findById(team.getId()).isEmpty()) {
			throw new IllegalArgumentException("La solicitud es invalida");
		}
		return teamRepository.save(team);
	}
	
	public Team updateTeam(Long id,Team team) {
		Optional<Team> updateTeam = teamRepository.findById(id);
		if (updateTeam.isEmpty() || team.getName() == null || team.getLeague() == null || team.getCountry() == null) {
			throw new IllegalArgumentException(String.format("Equipo no encontrado"));
		}
		updateTeam.get().setName(team.getName());
		updateTeam.get().setLeague(team.getLeague());
		updateTeam.get().setCountry(team.getCountry());
		return teamRepository.save(updateTeam.get());
	}
	
	public void deleteTeam(Long id) {
		Optional<Team> updateTeam = teamRepository.findById(id);
		if (updateTeam.isEmpty()) {
			throw new IllegalArgumentException(String.format("Equipo no encontrado"));
		}
		teamRepository.deleteById(id);
	}
}
