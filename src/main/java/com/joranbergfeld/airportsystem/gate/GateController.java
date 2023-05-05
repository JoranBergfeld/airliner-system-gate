package com.joranbergfeld.airportsystem.gate;

import jakarta.validation.Valid;
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
    public Gate createGate(@Valid @RequestBody Gate gate) {
        return gateService.storeGate(gate);
    }

    @PutMapping("/{id}")
    public Gate updateGate(@PathVariable("id") Long id, @Valid @RequestBody Gate updatedGate) {
        return gateService.updateGate(id, updatedGate);
    }

    @DeleteMapping("/{id}")
    public Gate deleteGate(@PathVariable("id") Long id) {
        return gateService.deleteGate(id);
    }
}
