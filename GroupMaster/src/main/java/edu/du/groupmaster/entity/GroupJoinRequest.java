package edu.du.groupmaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class GroupJoinRequest {
    @Id
    @GeneratedValue
    private Long id;

    private Long groupId;
    private Long userId;

    private String message; // 신청 메시지
    private boolean approved; // 수락 여부
}
