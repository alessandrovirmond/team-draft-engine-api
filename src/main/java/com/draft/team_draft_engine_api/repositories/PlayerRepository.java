package com.draft.team_draft_engine_api.repositories;

import com.draft.team_draft_engine_api.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    
    List<Player> findByGroupId(String groupId);
    
}