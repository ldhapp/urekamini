package com.uplus.miniproject2.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
public class FamiliarityRankingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String targetUserName;
    private double nameScore;
    private double mbtiScore;
    private double hobbyScore;
    private double finalScore;
    private byte[] targetUserImage;
    private Long targetUserId;

    @Builder
    public FamiliarityRankingDto(String targetUserName, double nameScore, double mbtiScore, double hobbyScore,
                                 double finalScore, byte[] targetUserImage, Long targetUserId) {
        this.targetUserName = targetUserName;
        this.nameScore = nameScore;
        this.mbtiScore = mbtiScore;
        this.hobbyScore = hobbyScore;
        this.finalScore = finalScore;
        this.targetUserImage = targetUserImage;
        this.targetUserId = targetUserId;
    }
}
