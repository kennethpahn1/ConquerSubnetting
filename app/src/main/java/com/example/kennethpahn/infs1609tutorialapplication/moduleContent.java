package com.example.kennethpahn.infs1609tutorialapplication;
// this class enumerates the lecture content for students to read
public class moduleContent {
    public int id; // just for identification purpose
    public int module_id; // this links the content to the module
    public int order; // since a lecture would probably have multiple slides, this just places everything in order.
    public String text; // this is to run through what a user would see.
    public moduleContent(int id, int module_id, int order, String text){
        this.id = id;
        this.module_id = module_id;
        this.order = order;
        this.text = text;
    }
    public String getContent(){
        return this.text;
    }
}
