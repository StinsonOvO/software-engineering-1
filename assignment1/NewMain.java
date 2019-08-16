/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
/**
 *
 * @author Jianglong
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    
    /**
     * getMin is a function that takes 3 numeric arguments, and 
     * return minimum in the format of double
    */
    public static double getMin(double arg1, double arg2, double arg3) {
        
        // compare 3 arguments to return minimum number
        if (arg1 <= arg2 && arg1 <= arg3) {
            return arg1;
        } else if (arg2 < arg1 && arg2 <= arg3) {
            return arg2;
        } else {
            return arg3;
        }
        
    }
    
    /**
     * getMax is a function that takes 3 numeric arguments, and 
     * return maximum in the format of double
    */
    public static double getMax(double arg1, double arg2, double arg3) {

        // compare 3 arguments to return maximum number
        if (arg1 >= arg2 && arg1 >= arg3) {
            return arg1;
        } else if (arg2 > arg1 && arg2 >= arg3) {
            return arg2;
        } else {
            return arg3;
        }
        
    }
    
    /**
     * getAvg is a function that takes 3 numeric arguments, and 
     * return average in the format of double
    */
    public static double getAvg(double arg1, double arg2, double arg3) {
 
        return (arg1 + arg2 + arg3)/3; // return average number
        
    }
    
    public static void main(String[] args) {
        // some unit tests - functions can and only take 3 arguments
        System.out.println("min: " + getMin(5.3, 1.2, 2.3)); //1.2
        System.out.println("max: " + getMax(5.3, 1.2, 2.3)); //5.3
        System.out.println("avg: " + getAvg(5.3, 1.2, 2.3)); //2.9333333333333336

    }  
    
}
