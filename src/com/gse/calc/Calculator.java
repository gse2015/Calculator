package com.gse.calc;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by GSE on 2016/7/27.
 */
public class Calculator {
    private String result = "123";
//    private Queue<Number> operandQueue = new LinkedList<>();
    private static HashSet<Character> validOperatorSet = null;
//    private Queue<Character> operatorQueue = new LinkedList<>();
    private ArrayList<Object> expressionList = new ArrayList<>();

    private static void initOperatorSet(){
        try {
            if (validOperatorSet == null){
                Character[] validOperator = {'+', '-', '*', '/'};
                validOperatorSet = new HashSet<>(Arrays.asList(validOperator));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Calculator() {
        Calculator.initOperatorSet();
    }

    public static Character[] getSupportOperator(){
        Calculator.initOperatorSet();
        return (Character[]) Calculator.validOperatorSet.toArray();
    }

    public String getResult(){
        return result;
    }

    void inputNumber(String number)throws ParseException{
        if (number == null){
            throw new NullPointerException("Parameter is must not null!");
        }
        Number numObj = NumberFormat.getInstance().parse(number);
        expressionList.add(numObj);
    }

    void inputNumber(Integer number){
        if (number == null){
            throw new NullPointerException("Parameter is must not null!");
        }
        expressionList.add(number);
    }

    void inputNumber(Double number) {
        if (number == null){
            throw new NullPointerException("Parameter is must not null!");
        }
        expressionList.add(number);
    }

    void inputOperator(char operator) {
        if (!validOperatorSet.contains(operator)){
            throw new InvalidParameterException("Not support this operator!");
        }
        expressionList.add(operator);
    }

    void inputBracket(char bracket) {
        if (bracket == '(' || bracket == ')'){
            throw new InvalidParameterException("Only support '(' and ')'!");
        }
        expressionList.add(bracket);
    }

    //Infix Expression to Postfix Expression
    private void parseExpression(){
        Stack<Character> operatorStack = new Stack<>();
        ArrayList<Object> expressionListTemp = new ArrayList<>();
        for (Object obj: expressionList){
            if (obj instanceof Number){
                expressionListTemp.add(obj);
            }else if (obj instanceof Character){
                Character ch = (Character)obj;
                if (ch == '('){
                    operatorStack.push(ch);
                }else if (ch == ')'){
                    while (!operatorStack.isEmpty()){
                        Character chT = operatorStack.pop();
                        if (chT == '(')
                            break;
                        expressionListTemp.add(chT);
                    }
                }else{
                    if (!operatorStack.isEmpty()){
                        Character chT = operatorStack.peek();
                        if (getOperatorLevel(ch) <= getOperatorLevel(chT)){
                            expressionListTemp.add(chT);
                        }
                    }
                    operatorStack.push(ch);
                }
            }
        }
        while (!operatorStack.isEmpty()){
            Character chT = operatorStack.pop();
            expressionListTemp.add(chT);
        }
        expressionList.clear();
        expressionList = expressionListTemp;
    }

    private int getOperatorLevel(Character operator) {
        switch (operator){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:return 0;
        }
    }

    String calculate() {
        parseExpression();
        Stack<Number> operandStack = new Stack<>();
//        Stack<Character> operatorStack = new Stack<>();
        Double resultTemp = 0.0;
        for (Object obj : expressionList){
            if (obj instanceof Number){
                operandStack.push((Number)obj);
            }else{
                Character ch = (Character)obj;
                switch (ch){
                    case '+':{
                        Number right = operandStack.pop();
                        Number left = operandStack.pop();
                        resultTemp += (left.doubleValue() + right.doubleValue());
                        break;
                    }
                    case '-':{
                        Number right = operandStack.pop();
                        Number left = operandStack.pop();
                        resultTemp += (left.doubleValue() - right.doubleValue());
                        break;
                    }
                    case '*':{
                        Number right = operandStack.pop();
                        Number left = operandStack.pop();
                        resultTemp += (left.doubleValue() * right.doubleValue());
                        break;
                    }
                    case '/':{
                        Number right = operandStack.pop();
                        Number left = operandStack.pop();
                        if (right instanceof Integer && left instanceof Integer){
                            resultTemp += (left.intValue() / right.intValue());
                        }else{
                            resultTemp += (left.doubleValue() / right.doubleValue());
                        }
                        break;
                    }
                }
            }
        }
        result = resultTemp.toString();
        return result;
    }
}
