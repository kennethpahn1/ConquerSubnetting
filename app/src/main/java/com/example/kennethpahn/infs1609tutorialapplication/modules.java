package com.example.kennethpahn.infs1609tutorialapplication;
// this is the class that provides information about the modules that a user can possibly complete.
public class modules {
    public int id; // just the lecture number it correlates to
    public String name; // the name of the lecture (or the subject)
    public String description; // describes what it would be about
    public modules(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
}
