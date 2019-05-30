package com.test;

import java.util.Scanner;

public class CodingExercise {
	public static void main(String args[]){
		Scanner reader = new Scanner(System.in);
		int a = reader.nextInt();
		//Thint e argument in input area (see below code editor) is read into str variable at runtime....Now continue from here
		    int i,count=0;
		    for(i=1;i<=a;i++){
		        if(a%i==0){
		            count++;
		        }
		    }if(count==2){
		        System.out.println("Prime");
		    }
		    else{
		        System.out.println("Not Prime");
		    }
		    
		    
		}
		
}
