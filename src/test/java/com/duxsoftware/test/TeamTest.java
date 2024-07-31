package com.duxsoftware.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.duxsoftware.model.Team;
import com.duxsoftware.service.TeamService;

class TeamTest {
	
	@Test
	void testFindAllTeams() {
		TeamService teamService = mock(TeamService.class);
		when(teamService.getAllTeams()).thenReturn(new ArrayList<>());
		
		List<Team> teams = teamService.getAllTeams();
		assertNotNull(teams);
		assertTrue(teams.isEmpty());	
	}
	
	@Test
	void testFindTeamById() {
		TeamService teamService = mock(TeamService.class);
		Team team = new Team(1l,"Real Madrid","La Liga","España");
		when(teamService.getTeamById(2L)).thenReturn(team);

		Team foundTeamById = teamService.getTeamById(2L);
		assertNotNull(foundTeamById);
		assertEquals(1L, foundTeamById.getId());
	}
	
	@Test
	void testFindTeamByName() {
		TeamService teamService = mock(TeamService.class);
		Team team = new Team(1l,"Real Madrid","La Liga","España");
		when(teamService.getTeamByName(team.getName())).thenReturn(team);
		
		Team foundTeamByName = teamService.getTeamByName("Real Madrid");
		assertNotNull(foundTeamByName);
		assertEquals("Real Madrid", foundTeamByName.getName());
	}
	
	@Test
	void testCreateTeam() {
		TeamService teamService = mock(TeamService.class);
		Team team = new Team(1l,"Real Madrid","La Liga","España");
		when(teamService.createTeam(team)).thenReturn(team);
		
		Team createdTeam = teamService.createTeam(team);
		assertNotNull(createdTeam);
		assertTrue(createdTeam != null);
	}
	
	@Test
	void testUpdateTeam() {
		TeamService teamService = mock(TeamService.class);
		Team team = new Team(1l,"Real Madrid","La Liga","España");
		when(teamService.updateTeam(1L, team)).thenReturn(team);
		
		Team updatedTeam = teamService.updateTeam(1L, team);
		assertNotNull(updatedTeam);
		assertFalse(updatedTeam == null);
	}
	
}