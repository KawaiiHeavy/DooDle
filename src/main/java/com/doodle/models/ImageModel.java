package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "images")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ImageModel {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

}
