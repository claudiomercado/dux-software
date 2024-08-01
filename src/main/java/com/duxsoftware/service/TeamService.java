package com.duxsoftware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.duxsoftware.controller.dto.TeamDTO;
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
	
	public List<TeamDTO> getAllTeams() {
		List<TeamDTO> teams = teamRepository.findAll().stream()
				.map(team -> new TeamDTO(team.getId(), team.getName(), team.getLeague(), team.getCountry()))
				.collect(Collectors.toList());
		return teams;
	}
	
	public TeamDTO getTeamById(Long id) {	
		TeamDTO teamDTO = teamRepository.findById(id)
				.map(team -> new TeamDTO(
						team.getId(),
                        team.getName(),
                        team.getLeague(),
                        team.getCountry()))
				.orElseThrow(() -> new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND));
		
		return teamDTO;
	}
	
	public TeamDTO getTeamByName(String name) {
		TeamDTO teamDTO = teamRepository.getTeamByName(name)
				.map(team -> new TeamDTO(
						team.getId(),
                        team.getName(),
                        team.getLeague(),
                        team.getCountry()))
				.orElseThrow(() -> new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND));
		
		return teamDTO;
	}
	
	public Team createTeam(TeamDTO teamDTO) {
		if (teamDTO.getName() == null || teamDTO.getLeague() == null || teamDTO.getCountry() == null){
			throw new RequestException(MESSAGE_BAD_REQUEST,HttpStatus.BAD_REQUEST);
		}
		
		Team team = new Team(teamDTO.getName(),teamDTO.getLeague(),teamDTO.getCountry());
		
		return teamRepository.save(team);
	}
	
	public Team updateTeam(Long id,TeamDTO teamDTO) {
		Optional<Team> updateTeam = teamRepository.findById(id);
		
		if (updateTeam.isEmpty() || teamDTO.getName() == null || teamDTO.getLeague() == null || teamDTO.getCountry() == null) {
			throw new RequestException(MESSAGE_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
		
		updateTeam.get().setName(teamDTO.getName());
		updateTeam.get().setLeague(teamDTO.getLeague());
		updateTeam.get().setCountry(teamDTO.getCountry());
		
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
