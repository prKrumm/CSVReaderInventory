package com.jcg.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jcg.Config;
import com.jcg.model.Artikel;
import com.jcg.util.CsvFileReader;
import com.jcg.util.CsvFileWriter;
import com.jcg.util.RESTManager;

public class Controller {
	private List<Artikel> alleArtikelCSV;
	private List<Artikel> artikeListeKorrektEbay;
	private List<Artikel> artikelListeShop;
	private List<Artikel> artikelListeFalsch;
	private CsvFileReader csvFileReader;
	private CsvFileWriter csvFileWriter;
	private String oldFile;
	
	

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
		this.artikeListeKorrektEbay=new ArrayList<>();
		this.artikelListeFalsch=new ArrayList<>();
		this.artikelListeShop=new ArrayList<>();
		this.csvFileReader=new CsvFileReader();
		this.csvFileWriter=new CsvFileWriter();
		this.alleArtikelCSV=new ArrayList<>();
	}
	
	public Boolean inputLine(String fileName){
		this.oldFile=fileName;
		this.alleArtikelCSV=csvFileReader.readCsvFile(fileName);
		if(alleArtikelCSV==null)
			return false;
		else
			return true;
	}
	
	public void writeFile(){
		String newFileEbay=oldFile.replaceFirst(".csv", "EBAYNEU.csv");
		String newFileShop=oldFile.replaceFirst(".csv", "SHOPNEU.csv");
		String incorrectArtikel=oldFile.replaceFirst(".csv", "FALSCH.csv");
		csvFileWriter.writeCsvFile(artikeListeKorrektEbay, newFileEbay);
		csvFileWriter.writeCsvFile(artikelListeShop, newFileShop);
		csvFileWriter.writeCsvFile(artikelListeFalsch, incorrectArtikel);
	}
	
	public void checkInput(){
		for(Artikel a:this.alleArtikelCSV){
			if(checkArtikel(a)){
				if(checkEbayArtikel(a)){
					this.artikeListeKorrektEbay.add(a);
				} else{
					this.artikelListeShop.add(a);
				}
			} else{
				this.artikelListeFalsch.add(a);
			}
		}
	}
	
	public Boolean checkArtikel(Artikel artikel){
		Boolean artikelNr=checkEbayArtikel(artikel)||checkShopArtikel(artikel);
		Boolean lager_platz=artikel.lager_fach.matches(Config.lagerPlätze);
		return artikelNr && lager_platz;
	}
	
	public void sendToServer(){
		List<Artikel> artikelListe=new ArrayList<>();
		RESTManager restManager=new RESTManager();
		artikelListe=restManager.sendToServer(this.artikelListeShop);
		System.out.println("Sende Artikel zum Shop");
		for(Artikel a:artikelListe){
			System.out.println();
			a.toString();
		}
	}
	
	private Boolean checkEbayArtikel(Artikel artikel){
		return artikel.druck_pseudonym.matches(Config.ebayNummern);
	}
	
	private Boolean checkShopArtikel(Artikel artikel){
		return artikel.druck_pseudonym.matches(Config.ARTIKEL_LAGERFACH);
	}
	
	
	
	
	

}
