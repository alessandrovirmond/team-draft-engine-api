package com.draft.team_draft_engine_api.services;

import com.draft.team_draft_engine_api.dtos.TeamDTO;
import com.draft.team_draft_engine_api.models.Player;
import com.draft.team_draft_engine_api.repositories.PlayerRepository;
import com.draft.team_draft_engine_api.strategies.TeamDraftStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DraftService {

    private final PlayerRepository playerRepository;
    private final TeamDraftStrategy draftStrategy;

    public List<TeamDTO> generateTeams(String groupId, int numberOfTeams) {
        List<Player> availablePlayers = playerRepository.findByGroupId(groupId).stream()
                .filter(Player::getIsPresent)
                .toList();

        if (availablePlayers.size() < numberOfTeams) {
            throw new IllegalArgumentException("Não há jogadores suficientes para formar " + numberOfTeams + " times.");
        }

        return draftStrategy.draft(availablePlayers, numberOfTeams);
    }
}