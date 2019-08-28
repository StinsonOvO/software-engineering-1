/*
 * The Question class includes the object structure of Question
 * and its methods.
 */
package com.jianglong.exam;

import java.util.ArrayList;
/**
 *
 * @author Jianglong
 */ 
public class Question implements XMLizable { // ensure Question is XMLizable
    private String id; // id of the question
    private String question; // question content
    private ArrayList<String> allAnswers; // all possible answers for MCQ
    private String correctAnswer; // unique correct answer
    private String questionType; // type of questions, including MCQ, TF(true of false), FB (filling blank)
         
    // constructor to initiate the object
    public Question(String id, String question, String questionType, 
                    ArrayList<String> allAnswers, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.allAnswers = allAnswers;
        this.correctAnswer = correctAnswer;
    }
    
    @Override
    public String toXML() { // create xml for the question
        String res = "<question id='" + id + "'>\n" + "<content>" + question + 
                     "</content>\n" + "<type>" + questionType + "</type>\n" + 
                     "<allanswers>";
        String allAnswerStr = String.join(";", allAnswers);
        res += allAnswerStr + "</allanswers>\n" + "<correct>" + correctAnswer + 
                "</correct>\n" + "</question>\n";
        
        return res;
    }
    
    /**
     * An xml string output will look something like:
     *  <test>
     *      <question id='1'>
     *          <content>Which is the largest country in the world?</content>
     *          <type>MCQ</type>
     *          <allanswers>USA;India;China;Russia</allanswers>
     *          <correct>Russia</correct>
     *      </question>
     *      <question id='4'>
     *          <content>Lightning never strikes in the same place twice.</content>
     *          <type>TF</type>
     *          <allanswers></allanswers>
     *          <correct>F</correct>
     *      </question>
     *      <question id='5'>
     *          <content>The capital of Seychelles is ___.</content>
     *          <type>FB</type>
     *          <allanswers></allanswers>
     *          <correct>Victoria</correct>
     *      </question>
     *  </test>
     */
    
    // get question content
    String getQuestion() {
        return question;
    }

    // get all possible answers for MCQ
    ArrayList<String> getAllAnswers() {
        return allAnswers;
    }
    
    // get the unique correct answer
    String getCorrectAnswer() {
        return correctAnswer;
    }
    
    // get the type of question: MCQ | TF (true of false) | FB (filling blank)
    String getQuestionType () {
        return questionType;
    }
        
}
