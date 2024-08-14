package com.leonel.monsalve.equifaxinterview.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "record")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rut;
    private String field1;
    private String field2;
    private String field3;
}
