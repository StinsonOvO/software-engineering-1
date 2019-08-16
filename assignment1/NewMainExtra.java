/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Jianglong Liao
 */
public class NewMainExtra {

    /**
     * @param args the command line arguments
     */
    
    
    /**
     * getMin is a function that takes any number of numeric arguments, and 
     * return minimum in the format of double
    */
    
    public static double getMin(double... numberArray) {
        
        // initialize minimum number with the first element in the array
        double minNumber = numberArray[0];
        
        // for loop to change the minimum number
        for(int i = 0; i < numberArray.length; i++) {
            // change the minimum number if there is a smaller number
            if(numberArray[i] < minNumber) {
                minNumber = numberArray[i];   
            }
        }
        
        // return the minimum number
        return minNumber;
        
    }
    
    /**
     * getMax is a function that takes any number of numeric arguments, and 
     * return maximum in the format of double
    */
    public static double getMax(double... numberArray) {

        // initialize maximum number with the first element in the array
        double maxNumber = numberArray[0];
        
        // for loop to change the maximum number
        for(int i = 0; i < numberArray.length; i++) {
            // change the maximum number if there is a larger number
            if(numberArray[i] > maxNumber) {
                maxNumber = numberArray[i];   
            }
        }
        
        // return the maximum number
        return maxNumber;
        
    }
    
    /**
     * getSum is a function that takes any number of numeric arguments, and 
     * return sum in the format of double
    */
    public static double getSum(double... numberArray) {
        
        // initialize sum with 0
        double sum = 0;
        
        // add each element while iterating through the array
        for(int i = 0; i < numberArray.length; i++) {
            sum += numberArray[i];
        }
        
        // return the sum number
        return sum;
        
    }
    
    /**
     * getAvg is a function that takes any number of numeric arguments, and 
     * return average in the format of double
    */
    public static double getAvg(double... numberArray) {

        // devide sum with the length of array to get average
        double avg = getSum(numberArray)/numberArray.length;
 
        return avg; // return average number
        
    }
    
    public static void main(String[] args) {
        // some unit tests - functions can take 3 arguments
        System.out.println("min: " + getMin(5.3, 1.2, 2.3)); //1.2
        System.out.println("max: " + getMax(5.3, 1.2, 2.3)); //5.3
        System.out.println("avg: " + getAvg(5.3, 1.2, 2.3)); //2.9333333333333336
        System.out.println("==========================");
        // functions can take more than 3 arguments
        System.out.println("min: " + getMin(5.3, 1.2, 2.3, 3.5, 6)); //1.2
        System.out.println("max: " + getMax(5.3, 1.2, 2.3, 3.5, 6)); //6.0
        System.out.println("avg: " + getAvg(5.3, 1.2, 2.3, 3.5, 6)); //3.66
        System.out.println("==========================");
        // functions can take less than 3 arguments
        System.out.println("min: " + getMin(5.3, 1.2)); //1.2
        System.out.println("max: " + getMax(5.3, 1.2)); //5.3
        System.out.println("avg: " + getAvg(5.3, 1.2)); //3.25
        System.out.println("=====end of unit test=====");
        
        
    }  
    
}

