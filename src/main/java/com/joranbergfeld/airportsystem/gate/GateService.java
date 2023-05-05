package com.joranbergfeld.airportsystem.gate;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    private final GateRepository gateRepository;

    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public List<Gate> getActiveGates() {
        return gateRepository.findAllByActiveTrue();
    }

    public Gate findById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id " + id));
    }

    public Gate storeGate(Gate gate) {
        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, Gate updatedGate) {
        return gateRepository.findById(id)
                .map(gate -> {
                    gate.setName(updatedGate.getName());
                    gate.setSize(updatedGate.getSize());
                    return gateRepository.save(gate);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id " + id));
    }

    public Gate deleteGate(Long id) {
        return gateRepository.findById(id)
                .map(gate -> {
                    gate.setActive(false);
                    return gateRepository.save(gate);
                }).orElseThrow(() -> new ResourceNotFoundException("Gate not found with id " + id));
    }
}
