package com.wora.eBanking.dtos.role;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateRoleDTO {
    @NotBlank
    private String roleName;
}
