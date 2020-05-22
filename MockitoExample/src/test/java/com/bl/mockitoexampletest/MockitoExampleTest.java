package com.bl.mockitoexampletest;

import com.bl.mokitoexample.CalculatorService;
import com.bl.mokitoexample.MockitoExample;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

public class MockitoExampleTest {

    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    MockitoExample mockitoExample = new MockitoExample();

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calcService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testAdd(){
        //add the behavior of calc service to add two numbers
        mockitoExample.setCalculatorService(calcService);
        when(calcService.add(10.0,20.0)).thenReturn(30.0);

        //test the add functionality
        Assert.assertEquals(mockitoExample.add(10.0, 20.0),30.0,0);
        verify(calcService, times(1)).add(10.0, 20.0);
        verify(calcService, never()).multiply(10.0,20.0);
    }

    @Test(expected = RuntimeException.class)
    public void testDivide(){
        //add the behavior to throw exception
        doThrow(new ArithmeticException())
                .when(calcService).divide(10.0,0.0);

        //test the add functionality
        Assert.assertEquals(calcService.divide(10.0, 0.0),new ArithmeticException());
    }


    @Test
    public void testSubstract(){
        //add the behavior of calc service to add two numbers
        mockitoExample.setCalculatorService(calcService);
        when(calcService.subtract(10.0,20.0)).thenReturn(10.0);

        //test the add functionality
        Assert.assertEquals(mockitoExample.subtract(10.0, 20.0),10.0,0);
        verify(calcService, times(1)).subtract(10.0, 20.0);
        verify(calcService, never()).divide(10.0,20.0);
    }


}