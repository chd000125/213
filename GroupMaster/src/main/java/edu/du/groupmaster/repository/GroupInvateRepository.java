package edu.du.groupmaster.repository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    Optional<GroupMember> findByGroupIdAndUserId(Long groupId, Long userId);
}
