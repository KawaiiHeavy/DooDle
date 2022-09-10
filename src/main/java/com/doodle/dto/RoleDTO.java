package com.doodle.dto;

import com.doodle.models.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RoleDTO {

    private Long id;
    private ERole name;

}
