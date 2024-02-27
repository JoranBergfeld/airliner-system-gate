package com.joranbergfeld.airportsystem.gate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "gates")
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private int size;
    /**
     * Entity ID which may or may not have reserved this gate.
     */
    private Long occupyingEntityId;

    /**
     * Marks the gate unusable due to it being occupied.
     */
    private boolean occupied;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getOccupyingEntityId() {
        return occupyingEntityId;
    }

    public void setOccupyingEntityId(Long entityId) {
        this.occupyingEntityId = entityId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
