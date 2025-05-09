package edu.du.groupmaster.controller;

import edu.du.groupmaster.entity.GroupJoinRequest;
import edu.du.groupmaster.entity.GroupMember;
import edu.du.groupmaster.service.GroupAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/admin")
@RequiredArgsConstructor
public class GroupAdminController {

    private final GroupAdminService adminService;

    @PutMapping("/{groupId}/members/{userId}/role")
    public ResponseEntity<Void> updateRole(@PathVariable Long groupId,
                                           @RequestParam Long masterId,
                                           @PathVariable Long userId,
                                           @RequestParam Role role) {
        adminService.updateMemberRole(groupId, masterId, userId, role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<List<GroupMember>> listMembers(@PathVariable Long groupId,
                                                         @RequestParam Long masterId) {
        return ResponseEntity.ok(adminService.getGroupMembers(groupId, masterId));
    }

    @GetMapping("/{groupId}/requests")
    public ResponseEntity<List<GroupJoinRequest>> pendingRequests(@PathVariable Long groupId,
                                                                  @RequestParam Long masterId) {
        return ResponseEntity.ok(adminService.getPendingRequests(groupId, masterId));
    }

    @PostMapping("/{groupId}/requests/{requestId}/decision")
    public ResponseEntity<Void> decideRequest(@PathVariable Long groupId,
                                              @RequestParam Long masterId,
                                              @PathVariable Long requestId,
                                              @RequestParam boolean approved) {
        adminService.approveJoinRequest(groupId, masterId, requestId, approved);
        return ResponseEntity.ok().build();
    }
}
