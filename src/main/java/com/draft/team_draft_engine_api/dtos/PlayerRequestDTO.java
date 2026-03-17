package com.draft.team_draft_engine_api.dtos;

import com.draft.team_draft_engine_api.models.PlayerPosition;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerRequestDTO(
    @NotBlank(message = "O ID do grupo não pode estar vazio")
    String groupId,

    @NotBlank(message = "O nome do jogador é obrigatório")
    String name,

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 15, message = "O jogador deve ter no mínimo 15 anos")
    @Max(value = 99, message = "Idade inválida")
    Integer age,

    @NotNull(message = "A posição do jogador é obrigatória")
    PlayerPosition position,

    @NotNull(message = "O status de presença é obrigatório")
    Boolean isPresent
) {}