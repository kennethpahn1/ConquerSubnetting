package com.example.kennethpahn.infs1609tutorialapplication;
// helps enumerate for the quiz content that are multiple choice
public class mcqQuizContent {
    public int id; // just for identification purpose
    public int module_id; // this links the content to the module
    public int order; // may need multiple questions per lecture
    public String question; // enumerates the question
    public int answer; // tells the system which one is the answer. 0 = a, 1 = b, 2 = c, 3 = d
    public String a; // option A user can select
    public String b; //option B
    public String c; // you should know this
    public String d; // and this too
    public mcqQuizContent(int id, int module_id, int order, String question, int answer, String a, String b, String c, String d){
        this.id = id;
        this.module_id = module_id;
        this.order = order;
        this.question = question;
        this.answer = answer;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public String getQuestion(){
        return this.question;
    }
    public String[] getAnswers(){
        String[] answerArray = new String[4];
        answerArray[0] = this.a;
        answerArray[1] = this.b;
        answerArray[2] = this.c;
        answerArray[3] = this.d;
        return answerArray;
    }
    public int getSolution(){
        return this.answer;
    }
}
