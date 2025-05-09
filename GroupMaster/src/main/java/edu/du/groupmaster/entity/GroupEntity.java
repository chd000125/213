package edu.du.groupmaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class GroupEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long masterId; // 그룹장의 userId
}
