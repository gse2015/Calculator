package com.gse;

import com.gse.calc.Calculator;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("This is my first java application.");
        Calculator calc1 = new Calculator();
        System.out.println("The result is "+ calc1.getResult());
    }
}
