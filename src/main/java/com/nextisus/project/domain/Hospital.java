package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="HOSPITAL")
public class Hospital extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    @Column(name="HOSPITAL_NAME")
    private String hospitalName;

    @Column(name="HOSPITAL_ADDRESS")
    private String hospitalAddress;

    @Column(name="HOSPITAL_TEL")
    private String hospitalTel;
}
