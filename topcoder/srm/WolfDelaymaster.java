// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class WolfDelaymaster {
	public String check(String str) {
            String template="wolf";
            int nw=0;
            int i=0;
            int n=0,oldI;

            i=0; oldI =0;
            while(i<str.length()){
                for(int j=0; j<template.length(); j++){
                    while(i<str.length() && str.charAt(i)==template.charAt(j)){
                        i++;
                    }
                    if(j==0) n=i-oldI;
                    if(j>0){
                        if(i-oldI != n)
                            return "INVALID";
                    }
                    oldI=i;
                }
            }

            return "VALID";
        }
        public static void main(String[] args) {
            WolfDelaymaster temp = new WolfDelaymaster();
            System.out.println(temp.check("o"));
        }
}
