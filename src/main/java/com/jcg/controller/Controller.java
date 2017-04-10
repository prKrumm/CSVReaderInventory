package com.jcg.controller;

import java.util.List;

import com.jcg.model.Artikel;
import com.jcg.util.CsvFileReader;
import com.jcg.util.CsvFileWriter;

public class Controller {
	
	private List<Artikel> artikelListe;
	private CsvFileReader csvFileReader;
	private CsvFileWriter csvFileWriter;
	private String oldFile;
	private String newFile;
	
	

	public Controller(List<Artikel> artikelListe, CsvFileReader csvFileReader, CsvFileWriter csvFileWriter) {
		super();
		this.artikelListe = artikelListe;
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
		return artikelListe;
	}

	public void setArtikelListe(List<Artikel> artikelListe) {
		this.artikelListe = artikelListe;
	}

	public Controller(List<Artikel> artikelListe) {
		super();
		this.artikelListe = artikelListe;
	}

	public Controller() {
		super();
	}
	
	public Boolean inputLine(String fileName){
		this.oldFile=fileName;
		this.artikelListe=csvFileReader.readCsvFile(fileName);
		if(artikelListe==null)
			return false;
		else
			return true;
	}
	
	public void writeFile(){
		newFile=oldFile.replaceFirst(".csv", "NEU.csv");
		csvFileWriter.writeCsvFile(artikelListe, newFile);
		
	}
	
	
	
	

}
