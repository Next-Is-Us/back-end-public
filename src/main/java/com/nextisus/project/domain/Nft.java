package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NFT")
public class Nft extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId; // Long 타입으로 변경

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "RECORD_PERIOD")
    private String recordPeriod;

    @Column(name = "WEEK")
    private String week;

    @ManyToOne
    @JoinColumn(name="HEALTH_RECORD_ID")
    private HealthRecord healthRecord;

    public void createNft (User user) {
        this.user = user;
    }
    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

}
