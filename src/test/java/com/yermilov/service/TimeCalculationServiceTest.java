package com.yermilov.service;

import com.yermilov.services.TimeCalculationService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TimeCalculationServiceTest {

    @Test
    public void getTimeWithPoints_ReturnsNonZero(){
        assertTrue(TimeCalculationService.getTimeCalculationService().getTime("Kyiv","Vinnytsia")>0);
    }
    @Test
    public void getTimeWithDistance_ReturnsNonZero(){
        assertTrue(TimeCalculationService.getTimeCalculationService().getTime(13)>0);
    }
}
