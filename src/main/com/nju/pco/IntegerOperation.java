package com.nju.pco;

public class IntegerOperation {
    private BasicOperation basicOperation;

    public IntegerOperation(BasicOperation basicOperation) {
        this.basicOperation = basicOperation;
    }

    public IntegerOperation() {
        this.basicOperation = new BasicOperation();
    }

    // 13
    public String integerAddition(String operand1, String operand2, char c,
                                  int length) {
        assert (operand1.length() <= length) && (operand2.length() <= length)
                && (operand1 != null) && (operand2 != null);
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
        operand2Negation = integerAddition(s, oper2, '0', oper2.length())
                .substring(0, oper2.length());
        assert operand2Negation != operand2;
        result = integerAddition(operand1, operand2Negation, '0', length);
        return result;
    }

    // 15
    public String integerMultiplication(String operand1, String operand2,
                                        int length) {
        assert (operand1.length() <= length) && (operand2.length() <= length)
                && (operand1 != null) && (operand2 != null);
        String result = "";
        String oper1 = operand1;
        String oper2 = operand2;
        String oper1N = "";
        String s = "";
        String zero = "";
        String oper1Negation = "";
        String all = "";
        String temp = "";
        if (operand1.length() < length) {
            for (int j = 0; j < length - operand1.length(); j++) {
                if (operand1.charAt(0) == '0') {
                    oper1 = "0" + oper1;
                } else if (operand1.charAt(0) == '1') {
                    oper1 = "1" + oper1;
                }
            }
        }
        if (operand2.length() < length) {
            for (int j = 0; j < length - operand2.length(); j++) {
                if (operand2.charAt(0) == '0') {
                    oper2 = "0" + oper2;
                } else if (operand2.charAt(0) == '1') {
                    oper2 = "1" + oper2;
                }
            }
        }
        oper1N = basicOperation.negation(oper1);
        for (int i = 0; i < oper1.length(); i++) {
            if (i == oper1.length() - 1) {
                s += "1";
            } else
                s += "0";
        }
        oper1Negation = integerAddition(s, oper1N, '0', oper1.length())
                .substring(0, oper1N.length());
        all = oper2;
        for (int i = all.length(); i < length * 2; i++) {
            all = "0" + all;
        }
        all += "0";
        for (int j = 0; j < length; j++) {
            zero += "0";
        }
        for (int i = 0; i < length; i++) {
            if (all.charAt(length * 2) - all.charAt(length * 2 - 1) == -1) {
                temp = integerAddition(oper1Negation, all.substring(0, length),
                        '0', length);
            } else if (all.charAt(length * 2) - all.charAt(length * 2 - 1) == 1) {
                temp = integerAddition(oper1, all.substring(0, length), '0',
                        length);
            } else if (all.charAt(length * 2) - all.charAt(length * 2 - 1) == 0) {
                temp = integerAddition(zero, all.substring(0, length), '0',
                        length);
            }
            StringBuffer sb = new StringBuffer(all);
            sb.replace(0, length, temp.substring(0, temp.length() - 1));
            all = sb.toString();
            all = basicOperation.rightAriShift(all, 1);
        }
        result = all.substring(0, length * 2);
        return result;
    }


}