/*
 * The Main class runs the program: write the exam to XML, read the exam from XML
 * and do the exam interactively through the terminal.
 */
package com.jianglong.exam;

/**
 *
 * @author Jianglong
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // store the exam in the Test object t
        Test t = Test.writeExam();
        
        // print the XML string on the terminal
        System.out.println(t.toXML());
        System.out.println("=============end of XML string==============");
        
        // save the XML string in a file
        t.saveXMLFile("exam.xml");
        
        // read the XML file and store in a Test object
        Test t2 = Test.readXMLFile("exam.xml");
        
        // reuse the Test object, do live exam interactively on the terminal
        System.out.println("============= start of exam ==============");
        t2.doExam();

    }    
    
}
