package com.rodolfocf.client.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cellphone")
@Builder
@DynamicUpdate
public class Cellphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "areaCode", length = 5)
    private String areaCode;

    @Column(name = "client_id")
    private Long client_id;




}
