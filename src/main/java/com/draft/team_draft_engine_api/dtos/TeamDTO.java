package com.draft.team_draft_engine_api.dtos;

import com.draft.team_draft_engine_api.models.Player;
import java.util.List;

public record TeamDTO(
    String teamName,
    List<Player> players
) {}