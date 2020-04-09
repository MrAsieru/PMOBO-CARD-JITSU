package test;

import java.util.Random;

import cardjitsu.*;

public class mainC {
	
	public static void main(String[] args) {
		for(int i=0;i<5000;i++) {
			System.out.println(new Random().nextInt(509));
		}
	}

}
