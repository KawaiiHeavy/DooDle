package com.doodle.controllers;

import com.doodle.dto.GroupDTO;
import com.doodle.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("api/group")
@AllArgsConstructor
public class GroupController {

    private GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<GroupDTO.Read> createGroup(@RequestBody GroupDTO.Create groupDTO){
        GroupDTO.Read group = groupService.createGroup(groupDTO);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable UUID id){
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<GroupDTO.Read>> findAllGroups(){
        Set<GroupDTO.Read> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<GroupDTO.Read> updateGroup(@RequestBody GroupDTO.Read groupDTO){
        GroupDTO.Read group = groupService.updateGroup(groupDTO);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<GroupDTO.Read>> getAllGroupsPaging(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<GroupDTO.Read> groupPage = groupService.getAllGroupsPageable(paging);
        return new ResponseEntity<>(groupPage, HttpStatus.OK);
    }
    
}
