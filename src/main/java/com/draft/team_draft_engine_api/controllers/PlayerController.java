package com.draft.team_draft_engine_api.controllers;

import com.draft.team_draft_engine_api.dtos.PlayerRequestDTO;
import com.draft.team_draft_engine_api.models.Player;
import com.draft.team_draft_engine_api.repositories.PlayerRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;
    // (POST http://localhost:8080/api/players)
    @PostMapping
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody PlayerRequestDTO dto) {
        // Convertendo o DTO válido para a Entidade do Banco de Dados
        Player player = Player.builder()
                .groupId(dto.groupId())
                .name(dto.name())
                .age(dto.age())
                .position(dto.position())
                .isPresent(dto.isPresent())
                .build();

        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    // (GET http://localhost:8080/api/players/{groupId})
    @GetMapping("/{groupId}")
    public ResponseEntity<List<Player>> getPlayersByGroup(@PathVariable String groupId) {
        List<Player> players = playerRepository.findByGroupId(groupId);
        return ResponseEntity.ok(players);
    }
}