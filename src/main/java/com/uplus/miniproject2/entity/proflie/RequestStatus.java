package com.uplus.miniproject2.entity.proflie;

public enum RequestStatus {
    APPROVED("승인"), PENDING("대기 중"), REJECTED("거절");

    private String name;

    RequestStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNotApproved() {
        if (this == PENDING || this == REJECTED) {
            return true;
        }

        return false;
    }
}
