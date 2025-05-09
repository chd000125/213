package edu.du.groupmaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import edu.du.groupmaster.enums.Role;

@Entity
public class GroupMember {
    @Id
    @GeneratedValue
    private Long id;

    private Long groupId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role; // MASTER, USER
}
