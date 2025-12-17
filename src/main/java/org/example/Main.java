package org.example;

import website.scrapper.Scrapper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scrapper scrapper = new Scrapper();
        scrapper.Extract_Data("https://www.youtube.com/");
    }
}