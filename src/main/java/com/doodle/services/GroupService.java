package com.doodle.services;

import com.doodle.dto.GroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface GroupService {

    Set<GroupDTO.Read> getAllGroups();
    Page<GroupDTO.Read> getAllGroupsPageable(Pageable pageable);
    GroupDTO.Read createGroup(GroupDTO.Create groupDTO);
    GroupDTO.Read findGroupById(UUID id);
    GroupDTO.Read updateGroup(GroupDTO.Read groupDTO);
    void deleteGroup(UUID id);
    
}
