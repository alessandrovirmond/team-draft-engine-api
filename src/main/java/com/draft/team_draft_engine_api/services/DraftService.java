package com.draft.team_draft_engine_api.services;

import com.draft.team_draft_engine_api.dtos.TeamDTO;
import com.draft.team_draft_engine_api.models.Player;
import com.draft.team_draft_engine_api.repositories.PlayerRepository;
import com.draft.team_draft_engine_api.strategies.TeamDraftStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DraftService {

    private final PlayerRepository playerRepository;
    private final TeamDraftStrategy draftStrategy;

    public List<TeamDTO> generateTeamsAuto(String groupId, List<UUID> presentPlayerIds) {
        List<Player> availablePlayers = playerRepository.findAllById(presentPlayerIds);

        int totalPlayers = availablePlayers.size();
        int numberOfTeams = calculateDynamicTeams(totalPlayers);

        return executeDraft(availablePlayers, numberOfTeams);
    }

    private int calculateDynamicTeams(int total) {
        if (total < 12) return 2;
        if (total <= 14) return 2;
        if (total <= 21) return 3;
        return 4;
    }

    private List<TeamDTO> executeDraft(List<Player> availablePlayers, int numberOfTeams) {
        if (availablePlayers.size() < 4) {
            throw new IllegalArgumentException("Não há jogadores suficientes para formar times.");
        }
        return draftStrategy.draft(availablePlayers, numberOfTeams);
    }
}