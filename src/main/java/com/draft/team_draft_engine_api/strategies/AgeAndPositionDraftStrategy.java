package com.draft.team_draft_engine_api.strategies;

import com.draft.team_draft_engine_api.dtos.TeamDTO;
import com.draft.team_draft_engine_api.models.Player;
import com.draft.team_draft_engine_api.models.PlayerPosition;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AgeAndPositionDraftStrategy implements TeamDraftStrategy {

    @Override
    public List<TeamDTO> draft(List<Player> availablePlayers, int numberOfTeams) {
        // 1. Cria as "caixas" vazias para os times
        List<TeamDTO> teams = new ArrayList<>();
        for (int i = 1; i <= numberOfTeams; i++) {
            teams.add(new TeamDTO("Time " + i, new ArrayList<>()));
        }

        // 2. Agrupa os jogadores por Posição
        Map<PlayerPosition, List<Player>> playersByPosition = availablePlayers.stream()
                .collect(Collectors.groupingBy(Player::getPosition));

        // 3. Ordena os jogadores de cada posição por idade (do mais velho pro mais novo)
        playersByPosition.values().forEach(list -> 
                list.sort(Comparator.comparing(Player::getAge).reversed())
        );

        // 4. Ordem de prioridade do sorteio: Primeiro os Goleiros, depois a espinha dorsal
        PlayerPosition[] draftOrder = {
                PlayerPosition.GOLEIRO, 
                PlayerPosition.ZAGUEIRO, 
                PlayerPosition.MEIA, 
                PlayerPosition.ATACANTE
        };

        // 5. Distribui os jogadores (Round-Robin)
        int currentTeamIndex = 0;
        for (PlayerPosition position : draftOrder) {
            List<Player> playersOfThisPosition = playersByPosition.getOrDefault(position, Collections.emptyList());
            
            for (Player player : playersOfThisPosition) {
                teams.get(currentTeamIndex).players().add(player);
                // Move para o próximo time. Se chegou no último, volta pro primeiro.
                currentTeamIndex = (currentTeamIndex + 1) % numberOfTeams; 
            }
        }

        return teams;
    }
}