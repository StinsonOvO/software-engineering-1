/*
 * The Test class allows to get all questions, write exam in XML file,
 * read exam from XML file, and do exam interactively
 * 
 */
package com.jianglong.exam;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
// libraries to parse XML file
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author Jianglong
 */
public class Test implements XMLizable {

    public List<Question> questions; // list to save all questions
    
    // constructor to initiate Test object
    Test() {
        questions = new ArrayList<>();
    }
    
    // method to add question to the list 
    public void addQuestions(Question q) {
        this.questions.add(q);
    }
    
    @Override
    public String toXML() { // method to turn Test object to XML
        String questionStr = "";
        for (Question q : questions) { // add question XML string
            questionStr += q.toXML();
        }
        String res = "<test>\n" + questionStr + "</test>\n";
        return res;
    }

    // method to get all questions from the Test object
    Iterable<Question> getQuestions() {
        return questions;
    }
    
    // method to save XML string into an XML file
    void saveXMLFile(String filename) {
        String str = this.toXML();
          try { // write the file
              Files.write(Paths.get(filename), str.getBytes());
          } catch (IOException ex) { // log in case of exception
              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    // method to parse the XML file and store as Test object
    public static Test readXMLFile(String filename) {
        // create a new Test object
        Test resTest = new Test();
        
        try {
            File inputFile = new File(filename); // parse XML file into File object
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // create new instance
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // create document builder
            Document doc = dBuilder.parse(inputFile); // further parse the doc file
            doc.getDocumentElement().normalize(); // normalize the parsed doc
            NodeList nList = doc.getElementsByTagName("question"); // get all questions and store them in NodeList
            
            // Loop through all question inside the NodeList
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp); // nNode as each question object
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    // case each question as Element
                    Element eElement = (Element) nNode;
                    
                    // get all possible answers by tag name...
                    String allAnswerStr = eElement
                                          .getElementsByTagName("allanswers")
                                          .item(0)
                                          .getTextContent();
                    
                    // ... and iteratively save them in an arraylist
                    ArrayList<String> allAnswers = new ArrayList<String>(Arrays.asList(allAnswerStr.split(";")));
                    
                    // construct a new Question object, with all required arguments
                    Question q = new Question(eElement.getAttribute("id"), // id
                                              eElement
                                              .getElementsByTagName("content") // content of question
                                              .item(0)
                                              .getTextContent(), 
                                              eElement
                                              .getElementsByTagName("type") // type of queston
                                              .item(0)
                                              .getTextContent(), 
                                              allAnswers, // all possible answers
                                              eElement
                                              .getElementsByTagName("correct") // unique correct answer
                                              .item(0)
                                              .getTextContent());
                    
                    resTest.addQuestions(q); // add newly created Question to Test object
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // print the error message in case of exception
        }
        
        return resTest; // return parsed Test object from XML file
    }
    
    // method to store Test object
    public static Test writeExam () {
        
        // question 1: multiple choice question
        // add all possible answers
        ArrayList<String> allAnswers1 = new ArrayList<String>(); 
        allAnswers1.add("USA");    
        allAnswers1.add("India");    
        allAnswers1.add("China");    
        allAnswers1.add("Russia");    
        Question q1 = new Question("1", "Which is the largest country in the world?", 
                                    "MCQ", allAnswers1, "Russia"); //id, content, type, allAnswers and correctAnswer
        
        // question 2: multiple choice question
        // add all possible answers
        ArrayList<String> allAnswers2 = new ArrayList<String>();
        allAnswers2.add("Yemen");    
        allAnswers2.add("Oman");    
        allAnswers2.add("Bahrain");    
        allAnswers2.add("Jordan");    
        Question q2 = new Question("2", "Muscat is the capital of which country?", 
                                    "MCQ", allAnswers2, "Oman"); //id, content, type, allAnswers and correctAnswer
        
        // question 3: true or false question
        ArrayList<String> allAnswers3 = new ArrayList<String>();
        Question q3 = new Question("3", "All wild pandas are found in China.", 
                                    "TF", allAnswers3, "T"); //id, content, type, allAnswers and correctAnswer
        
        // question 4: true or false question
        ArrayList<String> allAnswers4 = new ArrayList<String>();
        Question q4 = new Question("4", "Lightning never strikes in the same place twice.", 
                                    "TF", allAnswers4, "F"); //id, content, type, allAnswers and correctAnswer
        
        // question 5: filling the blank question
        ArrayList<String> allAnswers5 = new ArrayList<String>();
        Question q5 = new Question("5", "The capital of Seychelles is ___.", 
                                    "FB", allAnswers5, "Victoria"); //id, content, type, allAnswers and correctAnswer
        
        // question 6: filling the blank question
        ArrayList<String> allAnswers6 = new ArrayList<String>();
        Question q6 = new Question("6", "Sydney is located in the country of ___.", 
                                    "FB", allAnswers6, "Australia"); //id, content, type, allAnswers and correctAnswer
        
        // create Test object and add all questions
        Test t = new Test();
        t.addQuestions(q1);
        t.addQuestions(q2);
        t.addQuestions(q3);
        t.addQuestions(q4);
        t.addQuestions(q5);
        t.addQuestions(q6);
        
        return t; // return Test object
    }
    
    public void doExam() {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        
        // create arraylist to store exam results
        ArrayList<Boolean> allResults = new ArrayList<Boolean>();
        int correctCount = 0; // number of correct answer
        
        // loop through the questions
        for (Question q : this.getQuestions()) {
            
            String questionContent = q.getQuestion(); // get question content
            String questionType = q.getQuestionType(); // get question type: MCQ|TF|FB
            ArrayList<String> allAnswers = new ArrayList<String>(); // get all possible answers (for MCQ)
            String correctAnswer = q.getCorrectAnswer(); // get correct answer
            System.out.println("(" + questionType + ") " + questionContent); // print question with question type
            Boolean result = false; // initialize the result of user input as false
            
            if (null == questionType) {
                System.out.println("Unknown question type."); // error handling for unknown question type
                break;
            } else {
                switch (questionType) {
                    
                    case "MCQ": // multiple choice question
                        
                        allAnswers = q.getAllAnswers(); // get all possible answers
                        Iterator i = allAnswers.iterator(); // create an iterator for all choices
                        int numChoice = 0; // index for each choice
                        ArrayList<String> numChoiceArray = new ArrayList<String>(); // arraylist to store all index of choices
                        
                        while (i.hasNext()) { // iterate through all choices
                            numChoiceArray.add(String.valueOf(numChoice)); // add index of choice to arraylist
                            System.out.println("(" + numChoice + ") " + i.next()); // index + text of choice
                            numChoice++; // add 1 to index for each choice
                        }       
                        
                        // combine all index of choice into a string
                        String numChoiceStr = String.join(", ", numChoiceArray);
                        System.out.print("You answer (" + numChoiceStr + ") : "); // prompt the user to choose
                        String numAnswer = scanner.next(); // record user's choice
                        
                        while (!numChoiceArray.contains(numAnswer)) { // check if answer is contained in choices
                            System.out.print("Invalid answer. Please type one of " + numChoiceStr + " : ");
                            numAnswer = scanner.next(); // prompt the user to type valid argument.
                        }
                        
                        result = allAnswers.get(Integer.parseInt(numAnswer)).equals(correctAnswer); // compare with the correct answer
                        allResults.add(result); // add result to arraylist
                        break;
                        
                    case "TF": // true or false question 
                        
                        System.out.print("You answer (T/F) : "); // prompt the user to answer (T/F)
                        String tfAnswer = scanner.next();
                        
                        while(!tfAnswer.equals("T") && !tfAnswer.equals("F")) { // accept answer only T or F
                            System.out.print("Invalid answer. Please type T of F: ");
                            tfAnswer = scanner.next();
                        }

                        result = tfAnswer.equals(correctAnswer); // compare with the correct answer
                        allResults.add(result); // add result to arraylist
                        break;
                        
                    case "FB": // filling the blank question
                        System.out.print("You answer (fill the blank) : "); // prompt the use to fill the blank
                        String bfAnswer = scanner.next(); // record answer
                        result = bfAnswer.equals(correctAnswer); // compare with the correct answer
                        allResults.add(result); // add result to arraylist
                        break;
                    default:
                        System.out.println("Unknown question type.");
                        break; // error handling for unknown question type
                }
            }
            if (result) {
                System.out.println("You are right!"); // give result
            } else {
                System.out.println("Sorry, your answer is wrong.");
            }
        }
        for (Boolean b : allResults) { 
            if (b) correctCount++; // count correct answers
        }
        System.out.println(String.format("Your score is %d out of %d", // test report
                           correctCount, allResults.size()));
    }

}