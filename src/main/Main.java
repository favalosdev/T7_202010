package main;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) 
	{
		Controller controler = new Controller();
		try {
			controler.run();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
