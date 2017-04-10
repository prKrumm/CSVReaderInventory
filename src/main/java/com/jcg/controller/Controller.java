package com.jcg.controller;

import java.util.List;

import com.jcg.Config;
import com.jcg.model.Artikel;
import com.jcg.util.CsvFileReader;
import com.jcg.util.CsvFileWriter;

public class Controller {
	
	private List<Artikel> artikeListeKorrektEbay;
	private List<Artikel> artikelListeShop;
	private List<Artikel> artikelListeFalsch;
	private CsvFileReader csvFileReader;
	private CsvFileWriter csvFileWriter;
	private String oldFile;
	private String newFile;
	
	

	public Controller(List<Artikel> artikelListe, CsvFileReader csvFileReader, CsvFileWriter csvFileWriter) {
		super();
		this.artikeListeKorrektEbay = artikelListe;
		this.csvFileReader = csvFileReader;
		this.csvFileWriter = csvFileWriter;
	}

	public CsvFileReader getCsvFileReader() {
		return csvFileReader;
	}

	public void setCsvFileReader(CsvFileReader csvFileReader) {
		this.csvFileReader = csvFileReader;
	}

	public CsvFileWriter getCsvFileWriter() {
		return csvFileWriter;
	}

	public void setCsvFileWriter(CsvFileWriter csvFileWriter) {
		this.csvFileWriter = csvFileWriter;
	}

	public List<Artikel> getArtikelListe() {
		return artikeListeKorrektEbay;
	}

	public void setArtikelListe(List<Artikel> artikelListe) {
		this.artikeListeKorrektEbay = artikelListe;
	}

	public Controller(List<Artikel> artikelListe) {
		super();
		this.artikeListeKorrektEbay = artikelListe;
	}

	public Controller() {
		super();
	}
	
	public Boolean inputLine(String fileName){
		this.oldFile=fileName;
		this.artikeListeKorrektEbay=csvFileReader.readCsvFile(fileName);
		if(artikeListeKorrektEbay==null)
			return false;
		else
			return true;
	}
	
	public void writeFile(){
		newFile=oldFile.replaceFirst(".csv", "NEU.csv");
		csvFileWriter.writeCsvFile(artikeListeKorrektEbay, newFile);
		
	}
	
	public void checkInput(){
		
	}
	
	private Boolean checkArtikel(Artikel artikel){
		Boolean artikelNr=artikel.druck_pseudonym.matches(Config.ebayNummern)||artikel.druck_pseudonym.matches(Config.shopNummern);
		Boolean lager_platz=artikel.lager_fach.matches(Config.lagerPlätze);
		return artikelNr && lager_platz;
	}
	
	
	
	

}
