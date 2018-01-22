package com.yermilov.service;

import com.yermilov.services.DistanceCalculationService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class DistanceCalculationServiceTest {

    @Test
    public void getDistance_NotNull(){
        assertTrue(DistanceCalculationService.getDistanceCalculationService().getDistance("Kyiv","Vinnytsia")>0);
    }
}
