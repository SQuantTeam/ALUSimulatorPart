package com.nju.pco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerOperation {
    private BasicOperation basicOperation;

    public IntegerOperation(BasicOperation basicOperation) {
        this.basicOperation = basicOperation;
    }

    public IntegerOperation() {
        this.basicOperation = new BasicOperation();
    }

    // 13
    public String integerAddition(String operand1, String operand2, char c) {
        int length = 32;
        if (!((operand1 != null) && (operand2 != null) && (operand1.length() <= length) && (operand2.length() <= length)
                && (!operand1.isEmpty()) && (!operand2.isEmpty()))) {
            System.out.println("Illegal Input");
        }
        ;
        Pattern pattern = Pattern.compile("^[0-1]+$");
        Matcher oper1Match = pattern.matcher(operand1);
        Matcher oper2Match = pattern.matcher(operand2);
        if (!(oper1Match.matches() && oper2Match.matches())) {
            System.out.println("Illegal Input");
        }
        String result = "";
        String lens = "";
        String oper1 = operand1;
        String oper2 = operand2;
        int len = 0;
        if (length % 8 == 0) {
            len = length;
        } else {
            len = (length / 8 + 1) * 8;
        }
        String[] temp = new String[len / 8];
        if (operand1.length() < len) {
            for (int j = 0; j < len - operand1.length(); j++) {
                if (operand1.charAt(0) == '0') {
                    oper1 = "0" + oper1;
                } else if (operand1.charAt(0) == '1') {
                    oper1 = "1" + oper1;
                }
            }
        }
        if (operand2.length() < len) {
            for (int j = 0; j < len - operand2.length(); j++) {
                if (operand2.charAt(0) == '0') {
                    oper2 = "0" + oper2;
                } else if (operand2.charAt(0) == '1') {
                    oper2 = "1" + oper2;
                }
            }
        }
        for (int k = len / 8 - 1; k >= 0; k--) {
            if (k == len / 8 - 1) {
                temp[k] = basicOperation.claAdder(oper1.substring(8 * k, 8 * (k + 1)),
                        oper2.substring(8 * k, 8 * (k + 1)), c);
            } else {
                temp[k] = basicOperation.claAdder(oper1.substring(8 * k, 8 * (k + 1)),
                        oper2.substring(8 * k, 8 * (k + 1)),
                        temp[k + 1].charAt(8));
            }

        }
        for (int i = 0; i < len / 8; i++) {
            lens += temp[i].substring(0, 8);
        }
        result = lens.substring(len - length);
        if (oper1.charAt(0) != oper2.charAt(0)) {
            result += "0";
        } else {
            if (oper1.charAt(0) == result.charAt(0)) {
                result += "0";
            } else {
                result += "1";
            }
        }
        return result;
    }

    // 14
    public String integerSubtraction(String operand1, String operand2,
                                     int length) {
        assert (operand1.length() <= length) && (operand2.length() <= length)
                && (operand1 != null) && (operand2 != null);
        String result = "";
        String s = "";
        String oper2 = "";
        String operand2Negation = "";
        oper2 = basicOperation.negation(operand2);
        for (int i = 0; i < oper2.length(); i++) {
            if (i == oper2.length() - 1) {
                s += "1";
            } else
                s += "0";
        }
        operand2Negation = integerAddition(s, oper2, '0');
        assert operand2Negation != operand2;
        result = integerAddition(operand1, operand2Negation, '0');
        return result;
    }


}