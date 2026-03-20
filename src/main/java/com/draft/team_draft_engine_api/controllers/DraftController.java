package com.draft.team_draft_engine_api.controllers;

import com.draft.team_draft_engine_api.dtos.TeamDTO;
import com.draft.team_draft_engine_api.services.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/draft")
@RequiredArgsConstructor
public class DraftController {

    private final DraftService draftService;

    @GetMapping("/{groupId}")
    public ResponseEntity<List<TeamDTO>> draftTeams(
            @PathVariable String groupId,
            @RequestParam(defaultValue = "2") int numberOfTeams) {
        
        List<TeamDTO> teams = draftService.generateTeams(groupId, numberOfTeams);
        return ResponseEntity.ok(teams);
    }
}