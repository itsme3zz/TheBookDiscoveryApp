package com.example.android.thebookdiscoveryapp;

public class QuestionLibrary {

    private String Questions [] = {
            //INTROVERTED OR EXTROVERTED
            "I find it difficult to\n introduce myself to people.",
            "I don't like being the center of attention.",
            "An interesting book or\n a video game is often better than\n a social event.",
            //OBSERVANT OR INTUITIVE
            "You often get so lost in thoughts\n that you ignore or forget your\n surroundings.",
            "You consider yourself more\n practical than creative.",
            "You rarely get carried away\n by fantasies and ideas.",
            //THINKING OR FEELING
            "I would be honest to my friends even\n if it's going to hurt them.",
            "Winning a debate matters more to you than making sure no one gets upset.",
            "People can rarely upset you.",
            //JUDGING OR PROSPECTING
            "You rely more on your experience \nthan your imagination.",
            "You find it hard to stay relaxed\n when there is some pressure.",
            "Being organized is more important\n to you than being adaptable.",
            //DONE
            "You Are Done"

    };


    private String Choices [][] = {
            {"Agree","Disagree"}, //1
            {"Agree","Disagree"}, //2
            {"Agree","Disagree"}, //3
            {"Agree","Disagree"}, //4
            {"Agree","Disagree"}, //5
            {"Agree","Disagree"}, //6
            {"Agree","Disagree"}, //7
            {"Agree","Disagree"}, //8
            {"Agree","Disagree"}, //9
            {"Agree","Disagree"}, //10
            {"Agree","Disagree"}, //11
            {"Agree","Disagree"},  //12
            {" "," "}

    };


 public String getQuestion(int i){

     String question = Questions[i];
     return question;
 }

    public String getChoice1(int i){

        String choice0 = Choices[i][0];
        return choice0;
    }

    public String getChoice2(int i){

        String choice1 = Choices[i][1];
        return choice1;
    }

}
