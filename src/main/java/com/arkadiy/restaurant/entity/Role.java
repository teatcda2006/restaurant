package com.arkadiy.restaurant.entity;

import com.arkadiy.restaurant.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole name;
}
