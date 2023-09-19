package com.joranbergfeld.airportsystem.gate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class OccupyGateTest {

    private final GateRepository gateRepository = Mockito.mock(GateRepository.class);
    private final GateService gateService = new GateService(gateRepository);


    @Test
    void shouldOccupyGate() {

        Long gateId = 123L;

        Gate gate = new Gate();
        gate.setId(gateId);
        gate.setOccupied(false);

        Mockito.when(gateRepository.findById(gateId)).thenReturn(Optional.of(gate));
        Mockito.when(gateRepository.save(any())).thenReturn(gate);
        Gate returnedGate = gateService.occupyGate(gateId, 456L);

        Assertions.assertTrue(returnedGate.isOccupied(), "Gate should be occupied after call to occupy gate has been successful.");
    }
}
