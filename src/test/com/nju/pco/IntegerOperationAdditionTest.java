package com.nju.pco;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class IntegerOperationAdditionTest {
    private String operand1;
    private String operand2;
    private char c;
    private String expectedResult;

    private IntegerOperation integerOperation;

    @Before
    public void init() {
        integerOperation = new IntegerOperation();
    }

    @Parameterized.Parameters
    public static Collection inputParam() {
        return Arrays.asList(new Object[][]{
                {2, true, 1},
                {23, true, 1}
        });
    }

    @Test
    public void integerAdditionTest() {
        System.out.println("Input[Operand1:" + operand1 + ",Operand2:" + operand2 + ",c:" + c + "]" + " {ExpectedResult:"
                + expectedResult + "}");
        TestCase.assertEquals(expectedResult, integerOperation.integerAddition(operand1, operand2, c));
    }


}