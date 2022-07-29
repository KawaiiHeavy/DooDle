package com.doodle.services.impl;

import com.doodle.dto.GroupDTO;
import com.doodle.exceptions.GroupNotFoundException;
import com.doodle.models.Group;
import com.doodle.repostitories.GroupRepository;
import com.doodle.services.GroupService;
import com.doodle.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private Mapper mapper;

    @Override
    public GroupDTO.Read createGroup(GroupDTO.Create group) {
        return mapper.mapToReadGroupDTO(
                groupRepository.save(
                        mapper.mapToGroup(group)
                )
        );
    }

    @Override
    public GroupDTO.Read findGroupById(UUID id) {
        return mapper.mapToReadGroupDTO(groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group by id " + id + " was not found")));
    }

    @Override
    public GroupDTO.Read updateGroup(GroupDTO.Read groupDTO) {
        return mapper.mapToReadGroupDTO(
                groupRepository.save(
                        mapper.mapToGroup(groupDTO)
                )
        );
    }

    @Override
    public Set<GroupDTO.Read> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(mapper::mapToReadGroupDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Page<GroupDTO.Read> getAllGroupsPageable(Pageable pageable) {
        Page<Group> page = groupRepository.findAll(pageable);
        return page.map(mapper::mapToReadGroupDTO);
    }

    @Transactional
    public void deleteGroup(UUID id) {
        Group group = groupRepository.getById(id);
        groupRepository.delete(group);
    }

}
