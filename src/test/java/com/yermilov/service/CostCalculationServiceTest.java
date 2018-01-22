package com.yermilov.service;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostCalculationServiceTest {
    @Test
    public void getDriveCost_appliesDiscount_Right() {
        final double distance = 100;
        final int discount = 3;
        final double rightDriveCost =107;
        //final double answer = CostCalculationService.getCostCalculationService().getDriveCost(distance,discount);
        //assertEquals(rightDriveCost,answer,0.01);
    }

}
