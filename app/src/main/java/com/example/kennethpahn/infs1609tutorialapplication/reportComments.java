package com.example.kennethpahn.infs1609tutorialapplication;

public class reportComments {
    public int id; // just for identification purpose
    public int module_id; // this links the content to the module
    public int order; // may need multiple questions per lecture
    public String incorrect; // what to say if the user got it correct
    public reportComments(int id, int module_id, int order, String incorrect){
        this.id = id;
        this.module_id = module_id;
        this.order = order;
        this.incorrect = incorrect;
    }
    public String getComment() {
        return incorrect;
    }
}
