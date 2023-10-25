package com.features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Features11 {

    public static void main(String[] args) throws IOException {
        // Isblank()
        String s = "Anupam";
        System.out.println(s.isBlank());

        // lines()
        String str = "Bonjour\nMembre\nInfluent\nDu\nCercle\nDes\nAmis";
        System.out.println(str);
        System.out.println(str.lines().toList());

        // strip(), stripLeading(), stripTrailing()
        String srt = " JD ";
        System.out.print("Start");
        System.out.print(srt.strip());
        System.out.println("End");

        // repeat()
        String rep = "low;";
        System.out.println(rep.repeat(5));

        // writeString and readString
//        Path path = Files.writeString(Files.createTempFile("test", ".txt"), "This was posted on JD");
//        System.out.println(path);
//        String ss = Files.readString(path);
//        System.out.println(ss); //This was posted on JD

        int tab[] = new int [6];

        for (int i = 6; i > 0; i--)
            tab[6-i] = i;

        Arrays.fill(tab, 1, 5, 0);

        for (int i = 0; i < 6 ; i++)
            System.out.print(tab[i]);

    }
}
