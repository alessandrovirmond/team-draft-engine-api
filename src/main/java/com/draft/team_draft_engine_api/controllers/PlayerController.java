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
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;
    // (POST http://localhost:8080/api/players)
    @PostMapping
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody PlayerRequestDTO dto) {
     
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

    // (PUT http://localhost:8080/api/players/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable UUID id, @Valid @RequestBody PlayerRequestDTO dto) {
        
        return playerRepository.findById(id)
                .map(existingPlayer -> {
                    existingPlayer.setName(dto.name());
                    existingPlayer.setAge(dto.age());
                    existingPlayer.setPosition(dto.position());
                    existingPlayer.setIsPresent(dto.isPresent());
                    
                    Player updatedPlayer = playerRepository.save(existingPlayer);
                    return ResponseEntity.ok(updatedPlayer);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    

    // (DELETE http://localhost:8080/api/players/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        
        return playerRepository.findById(id)
                .map(player -> {
                    playerRepository.delete(player);
                    return ResponseEntity.noContent().<Void>build(); 
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}