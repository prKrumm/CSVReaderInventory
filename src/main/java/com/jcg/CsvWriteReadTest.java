package com.jcg;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.jcg.aview.Tui;
import com.jcg.controller.Controller;
import com.jcg.model.Artikel;
import com.jcg.util.CsvFileReader;
import com.jcg.util.CsvFileWriter;

/**
 * @author ashraf
 *
 */
public class CsvWriteReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Controller controller=new Controller();
		Tui tui=new Tui(controller);
		
		tui.inputLine();
		
		
		

	}

}
