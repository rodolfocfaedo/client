package com.rodolfocf.client.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {


    private String name;
    private String email;
    private String password;
    private List<CellphoneDTO> cellphones;
}
