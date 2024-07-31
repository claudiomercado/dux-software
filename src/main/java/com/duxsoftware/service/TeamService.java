package com.duxsoftware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.duxsoftware.exception.RequestException;
import com.duxsoftware.model.Team;
import com.duxsoftware.repository.TeamRepository;

@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	private final static String MESSAGE_NOT_FOUND = "Equipo no encontrado";
	private final static String MESSAGE_BAD_REQUEST = "La solicitud es invalida";
	
	public TeamService (TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public List<Team> getAllTeams(){
		return teamRepository.findAll();
	}
	
	public Team getTeamById(Long id) {	
		return teamRepository.findById(id)
				.orElseThrow(() -> new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND));		
	}
	
	public Team getTeamByName(String name) {
		return teamRepository.getTeamByName(name)
				.orElseThrow(() -> new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND));
	}
	
	public Team createTeam(Team team) {
		if (team.getName() == null || team.getLeague() == null || team.getCountry() == null
				|| !teamRepository.findById(team.getId()).isEmpty()) {
			throw new RequestException(MESSAGE_BAD_REQUEST,HttpStatus.BAD_REQUEST);
		}
		return teamRepository.save(team);
	}
	
	public Team updateTeam(Long id,Team team) {
		Optional<Team> updateTeam = teamRepository.findById(id);
		if (updateTeam.isEmpty() || team.getName() == null || team.getLeague() == null || team.getCountry() == null) {
			throw new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
		updateTeam.get().setName(team.getName());
		updateTeam.get().setLeague(team.getLeague());
		updateTeam.get().setCountry(team.getCountry());
		return teamRepository.save(updateTeam.get());
	}
	
	public void deleteTeam(Long id) {
		Optional<Team> updateTeam = teamRepository.findById(id);
		if (updateTeam.isEmpty()) {
			throw new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
		teamRepository.deleteById(id);
	}
}
