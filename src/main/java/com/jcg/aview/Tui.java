package com.jcg.aview;

import java.util.Scanner;

import com.jcg.controller.Controller;

public class Tui {
	
	private Controller controller;

	public Tui(Controller controller) {
		super();
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void inputLine(){
		Scanner scanner=new Scanner(System.in);
		System.out.print("Bitte den Pfad zur CSV datei angeben. Mit Endung!");
		String fileName=scanner.next();
		if(!controller.inputLine(fileName)){
			inputLine();
		} else{
			controller.checkInput();
			controller.writeFile();
			
		}
		
	}
	
	

}
