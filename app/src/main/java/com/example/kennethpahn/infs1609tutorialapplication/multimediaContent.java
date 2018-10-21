package com.example.kennethpahn.infs1609tutorialapplication;
// this class enumerates any relevant videos that students may wish to watch on YouTube.
public class multimediaContent {
    public int id; // just for identification purpose
    public int module_id; // this links the content to the module
    public int order; // may need multiple videos per lecture, so this just allows us to id them.
    public String URL; // this is to link directly to a youtube video
    public multimediaContent(int id, int module_id, int order, String URL){
        this.id = id;
        this.module_id = module_id;
        this.order = order;
        this.URL = URL;
    }
    public String getURL(){
        return this.URL;
    }
}
