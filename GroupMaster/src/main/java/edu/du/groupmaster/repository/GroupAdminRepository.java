package edu.du.groupmaster.repository;

public interface GroupInviteRepository extends JpaRepository<GroupInvite, Long> {
    List<GroupInvite> findAllByGroupId(Long groupId);
}
