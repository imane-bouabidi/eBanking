package com.wora.eBanking.dtos.role;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRoleDTO {
    @NotBlank
    private String name;
}
