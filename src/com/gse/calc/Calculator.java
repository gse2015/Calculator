package com.gse.calc;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by GSE on 2016/7/27.
 */
public class Calculator {
    private String result = "123";
    private Queue<Number> operandQueue = new LinkedList<>();
    private static HashSet<Character> validOperatorSet = null;
    private Queue<Character> operatorQueue = new LinkedList<>();
    private ArrayList<Object> expressionList = new ArrayList<>();

    public Calculator() {
        if (validOperatorSet == null){
            Character[] validOperator = {'+', '-', '*', '/'};
            validOperatorSet = new HashSet<Character>(Arrays.asList(validOperator));
        }
    }

    public String getResult(){
        return result;
    }
    boolean inputNumber(String number){
        try {
            Number numObj = NumberFormat.getInstance().parse(number);
            return operandQueue.offer(numObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean inputNumber(Integer number){
        assert(number != null);
        return operandQueue.offer(number);
    }

    public boolean inputNumber(Double number) {
        assert(number != null);
        return operandQueue.offer(number);
    }

    public boolean inputOperator(char operator) {
        if (validOperatorSet.contains(operator)){
            return operatorQueue.offer(operator);
        }
        return false;
    }

    public boolean inputBracket(char bracket) {
        assert(bracket == '(' || bracket == ')');
        return bracket == '(' || bracket == ')';
    }
}
