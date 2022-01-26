package com.newera.marathon.base.demo;

public class MainTest
{
    public static void main(String[] args)
    {
        test10();
    }

    public static void test10()
    {
        String s1 = "ab";

        String s2 = "abc";

        String s3 = s1 + "c";
        System.out.println(s3 == s2);
    }
}