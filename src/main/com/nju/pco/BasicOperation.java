package com.nju.pco;

public class BasicOperation {
    public BasicOperation() {
    }
    // 7
    public String negation(String operand) {
        assert operand != null;
        String result = "";
        for (int i = 0; i < operand.length(); i++) {
            if (operand.charAt(i) == '0') {
                result += '1';
            } else
                result += '0';
        }

        return result;
    }


    // 9
    public String rightAriShift(String operand, int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            if (operand.charAt(0) == '1') {
                result += "1";
            }
            if (operand.charAt(0) == '0') {
                result += "0";
            }
        }
        result += operand.substring(0, operand.length() - n);
        return result;
    }


    // 11
    public String fullAdder(char x, char y, char c) {
        assert (x == '0' || x == '1') && (y == '0' || y == '1')
                && (c == '0' || c == '1');
        String result = "";
        int temp = 0;
        temp = (x - '0') + (y - '0') + (c - '0');
        switch (temp) {
            case 0:
                result = "00";
                break;
            case 1:
                result = "10";
                break;
            case 2:
                result = "01";
                break;
            case 3:
                result = "11";
                break;
            default:
                break;

        }
        return result;
    }

    // 12
    public String claAdder(String operand1, String operand2, char c) {
        assert (operand1.length() == 8) && (operand2.length() == 8);
        String result = "";
        int[] carry = new int[operand1.length() + 1];
        int[] p = new int[operand1.length() + 1];
        int[] g = new int[operand1.length() + 1];
        int[] temp = new int[operand1.length() + 1];
        char s;

        carry[0] = c - '0';
        for (int i = operand1.length() - 1; i >= 0; i--) {
            if (operand1.charAt(i) == '0' && operand2.charAt(i) == '0') {
                p[operand1.length() - i] = 0;
            } else {
                p[operand1.length() - i] = 1;
            }
            if (operand1.charAt(i) == '1' && operand2.charAt(i) == '1') {
                g[operand1.length() - i] = 1;
            } else {
                g[operand1.length() - i] = 0;
            }
        }
        for (int i = 1; i <= operand1.length(); i++) {
            if (p[i] == 1 && carry[i - 1] == 1)
                temp[i] = 1;
            else
                temp[i] = 0;
            if (temp[i] == 0 && g[i] == 0)
                carry[i] = 0;
            else
                carry[i] = 1;
        }
        for (int i = operand1.length() - 1; i >= 0; i--) {
            s = (carry[operand1.length() - i - 1] + "").charAt(0);
            result = fullAdder(operand1.charAt(i), operand2.charAt(i), s)
                    .charAt(0) + result;
        }
        result += carry[operand1.length()];

        return result;
    }

}