package com.duxsoftware.repository.team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duxsoftware.model.team.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

	Optional<TeamEntity> getTeamByName(String name);
}
