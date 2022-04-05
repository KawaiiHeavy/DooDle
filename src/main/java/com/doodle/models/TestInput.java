package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestInput {

    private String title;

    private UUID creatorId;

    private Double maxBall;

    private Integer seconds;
}