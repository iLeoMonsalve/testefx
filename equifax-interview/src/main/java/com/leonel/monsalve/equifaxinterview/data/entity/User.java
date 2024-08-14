package com.leonel.monsalve.equifaxinterview.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
