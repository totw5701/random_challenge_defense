package com.rcd.rcdapi.domain.challengelog;


public enum ChallengeLogStatus {
    READY("R"), // 준비
    END("E"),   // 종료
    HOLD("H");  // 보류

    private final String status;

    ChallengeLogStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
