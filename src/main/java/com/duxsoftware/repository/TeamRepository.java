package com.duxsoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duxsoftware.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	Optional<Team> getTeamByName(String name);
}
