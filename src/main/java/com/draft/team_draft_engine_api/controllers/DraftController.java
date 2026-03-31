package com.draft.team_draft_engine_api.controllers;

import com.draft.team_draft_engine_api.dtos.TeamDTO;
import com.draft.team_draft_engine_api.services.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/draft")
@RequiredArgsConstructor
public class DraftController {

    private final DraftService draftService;

    @PostMapping("/{groupId}/auto")
    public ResponseEntity<List<TeamDTO>> draftTeamsAuto(
            @PathVariable String groupId,
            @RequestBody List<UUID> presentPlayerIds) {
        
        List<TeamDTO> teams = draftService.generateTeamsAuto(groupId, presentPlayerIds);
        return ResponseEntity.ok(teams);
    }
}