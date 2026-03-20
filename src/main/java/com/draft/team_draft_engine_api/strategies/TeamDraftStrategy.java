package com.draft.team_draft_engine_api.strategies;

import com.draft.team_draft_engine_api.dtos.TeamDTO;
import com.draft.team_draft_engine_api.models.Player;
import java.util.List;

public interface TeamDraftStrategy {
    List<TeamDTO> draft(List<Player> availablePlayers, int numberOfTeams);
}