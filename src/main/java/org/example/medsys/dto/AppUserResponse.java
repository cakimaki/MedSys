package org.example.medsys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AppUserResponse {
    private Long id;
    private String egn;
    private List<String> roleList;
}
