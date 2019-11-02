package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GenerateClass gen = new GenerateClass("Person", new String[]{"name","dob"}, new String[]{"String", "Date"});
    }

}

