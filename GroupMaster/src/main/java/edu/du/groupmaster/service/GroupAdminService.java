package edu.du.groupmaster.service;

import edu.du.groupmaster.entity.GroupJoinRequest;
import edu.du.groupmaster.entity.GroupMember;
import edu.du.groupmaster.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupAdminService {

    private final StudyGroupRepository groupRepo;
    private final GroupMemberRepository memberRepo;


    public void updateMemberRole(Long groupId, Long masterId, Long targetUserId, Role newRole) {
        StudyGroup group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("그룹이 존재하지 않음"));

        if (!group.getMasterId().equals(masterId)) {
            throw new RuntimeException("권한 없음");
        }

        GroupMember member = memberRepo.findByGroupIdAndUserId(groupId, targetUserId)
                .orElseThrow(() -> new RuntimeException("해당 멤버 없음"));

        member.setRole(newRole);
        memberRepo.save(member);
    }

    public List<GroupMember> getGroupMembers(Long groupId, Long masterId) {
        validateMaster(groupId, masterId);
        return memberRepo.findAllByGroupId(groupId);
    }

    public List<GroupJoinRequest> getPendingRequests(Long groupId, Long masterId) {
        validateMaster(groupId, masterId);
        return joinRepo.findAllByGroupIdAndApprovedFalse(groupId);
    }

    public void approveJoinRequest(Long groupId, Long masterId, Long requestId, boolean approved) {
        validateMaster(groupId, masterId);

        GroupJoinRequest request = joinRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("요청 없음"));

        request.setApproved(approved);
        joinRepo.save(request);

        if (approved) {
            GroupMember member = new GroupMember();
            member.setGroupId(groupId);
            member.setUserId(request.getUserId());
            member.setRole(Role.USER);
            memberRepo.save(member);
        }
    }

    private void validateMaster(Long groupId, Long masterId) {
        StudyGroup group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("그룹 없음"));
        if (!group.getMasterId().equals(masterId)) {
            throw new RuntimeException("권한 없음");
        }
    }
}
