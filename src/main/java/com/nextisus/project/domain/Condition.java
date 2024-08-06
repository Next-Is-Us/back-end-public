package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`CONDITION`")
public class Condition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conditionId;

    @Column(name = "SLEEP_TIME")
    private String sleepTime;

    @Column(name = "RECORD", columnDefinition = "TEXT")
    private String record;

    //안면 홍조
    @Column(name="IS_BLUSHING")
    private Boolean isBlushing;

    //두통
    @Column(name="IS_HEADACHING")
    private Boolean isHeadache;

    //복통
    @Column(name="IS_STOMACHACHING")
    private Boolean isStomachache;

    //변비
    @Column(name="IS_CONSTIPATED")
    private Boolean isConstipated;

    //근육통
    @Column(name="IS_MUSCLE_PAINFUL")
    private Boolean isMusclePainful;

    //피부 트러블
    @Column(name="IS_SKIN_TROUBLED")
    private Boolean isSkinTroubled;

    //손발 저림
    @Column(name="IS_NUMBNESS")
    private Boolean isNumbness;

    //오한
    @Column(name="IS_CHILLED")
    private Boolean isChilled;

    //우울
    @Column(name="IS_DEPRESSED")
    private Boolean isDepressed;

    @Column(name="YEAR")
    private Long year;

    @Column(name="MONTH")
    private Long month;

    @Column(name="DAY")
    private Long day;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="NFT_ID")
    private Nft nft;

    @ManyToOne
    @JoinColumn(name="health_record_id")
    private HealthRecord healthRecord;

    //연관관계 설정
    public void createdCondition(User user) {
        this.user = user;
    }

    public void setNft(Nft nft) {
        this.nft = nft;
    }
    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }
}
