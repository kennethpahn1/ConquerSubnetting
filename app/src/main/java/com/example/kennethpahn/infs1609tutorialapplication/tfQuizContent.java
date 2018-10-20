package com.example.kennethpahn.infs1609tutorialapplication;
// helps enumerate for the quiz content that are of true/false nature
public class tfQuizContent {
    public int id; // just for identification purpose
    public int module_id; // this links the content to the module
    public int order; // may need multiple questions per lecture
    public String question; // enumerates the question
    public int answer; // tells the system which one is the answer. 0 = false, 1 = true;
    public tfQuizContent(int id, int module_id, int order, String question, int answer){
        this.id = id;
        this.module_id = module_id;
        this.order = order;
        this.question = question;
        this.answer = answer;
    }
    public String getQuestion(){
        return this.question;
    }
    public int getSolution(){
        return this.answer;
    }
}
