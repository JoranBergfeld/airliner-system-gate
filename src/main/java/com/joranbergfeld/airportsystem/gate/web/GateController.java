package com.joranbergfeld.airportsystem.gate.web;

import com.joranbergfeld.airportsystem.gate.Gate;
import com.joranbergfeld.airportsystem.gate.GateService;
import com.joranbergfeld.airportsystem.gate.web.exception.GateOccupiedException;
import com.joranbergfeld.airportsystem.gate.web.request.OccupyGateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gate")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping
    public List<Gate> getAllGates() {
        return gateService.getActiveGates();
    }

    @GetMapping("/{id}")
    public Gate getGateById(@PathVariable("id") Long id) {
        return gateService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Gate> createGate(@Valid @RequestBody Gate gate) {
        return new ResponseEntity<>(gateService.storeGate(gate), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Gate updateGate(@PathVariable("id") Long id, @Valid @RequestBody Gate updatedGate) {
        return gateService.updateGate(id, updatedGate);
    }

    @DeleteMapping("/{id}")
    public Gate deleteGate(@PathVariable("id") Long id) {
        return gateService.deleteGate(id);
    }

    @PostMapping("/{id}/occupy")
    public Gate occupyGate(@PathVariable("id") Long id, @Valid @RequestBody OccupyGateRequest occupyGateRequest) {
        return gateService.occupyGate(id, occupyGateRequest.getOccupyingEntityId());
    }
}
