package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="HEALTHRECORDS")
public class HealthRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HEALTH_RECORD_ID")
    private Long healthRecordId;

    @Column(name = "RECORD_PERIOD")
    private String recordPeriod;

    @Column(name = "WEEK")
    private String week;

    @Column(name ="NFT_COUNT")
    private Long nftCount;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCount(Long nftCount) {
        this.nftCount = nftCount;
    }
}
