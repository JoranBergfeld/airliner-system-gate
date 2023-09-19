package com.joranbergfeld.airportsystem.gate.web.request;

public class OccupyGateRequest {
    private Long occupyingEntityId;

    public Long getOccupyingEntityId() {
        return occupyingEntityId;
    }

    public void setOccupyingEntityId(Long occupyingEntityId) {
        this.occupyingEntityId = occupyingEntityId;
    }
}
