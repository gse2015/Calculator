package com.gse.calc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by GSE on 2016/7/27.
 */
public class CalculatorTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getResult() throws Exception {
        Calculator calcTest = new Calculator();
        assertEquals("123", calcTest.getResult());
    }

    @Test
    public void inputNumberString_Valid() throws Exception{
        Calculator calcTest = new Calculator();
        assertEquals(true, calcTest.inputNumber("123"));
        assertEquals(true, calcTest.inputNumber("123.2"));
        assertEquals(true, calcTest.inputNumber("0.343"));
        //assertEquals(true, calcTest.inputNumber("+123"));
        assertEquals(true, calcTest.inputNumber("-123"));
        assertEquals(true, calcTest.inputNumber("-123.9"));
        assertEquals(true, calcTest.inputNumber("0"));
    }

    @Test
    public void inputNumberInteger_Valid() throws Exception {
        Calculator calcTest = new Calculator();
        assertTrue(calcTest.inputNumber(123));
    }

    @Test
    public void inputNumberDouble_valid() throws Exception {
        Calculator calcTest = new Calculator();
        assertTrue(calcTest.inputNumber(123.94));
    }

    @Test
    public void inputOperator_Valid() throws Exception {
        Calculator calcTest = new Calculator();
        assertTrue(calcTest.inputOperator('+'));
        assertTrue(calcTest.inputOperator('-'));
        assertTrue(calcTest.inputOperator('*'));
        assertTrue(calcTest.inputOperator('/'));
    }

    @Test
    public void inputBracket_Valid() throws Exception {
        Calculator calcTest = new Calculator();
        assertTrue(calcTest.inputBracket('('));
        assertTrue(calcTest.inputBracket(')'));
    }
}