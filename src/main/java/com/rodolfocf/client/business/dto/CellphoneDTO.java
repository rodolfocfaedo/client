package com.rodolfocf.client.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellphoneDTO {

    private Long id;
    private String number;
    private String areaCode;

}
