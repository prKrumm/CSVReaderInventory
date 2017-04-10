package com.jcg.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.jcg.Config;
import com.jcg.model.Artikel;

/**
 * @author ashraf_sarhan
 *
 */
public class CsvFileReader {
	List<Artikel> artikelList;

	
	// CSV file header
	private static final String[] FILE_HEADER_MAPPING = { Config.ARTIKEL_ID, Config.ARTIKEL_LAGERFACH };

	public List<Artikel> readCsvFile(String fileName) {

		// Create the CSVFormat object with the header mapping
		CSVFormat csvFileFormat = CSVFormat.newFormat(Config.SEPERATOR).withHeader(FILE_HEADER_MAPPING);
		try (// initialize FileReader object
				FileReader fileReader = new FileReader(fileName);
				// initialize CSVParser object
				CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);) {

			// Create a new list of student to be filled by CSV file data
			artikelList = new ArrayList<Artikel>();


			// Get a list of CSV file records
			List<CSVRecord> csvRecords = csvFileParser.getRecords();

			// Read the CSV file records starting from the second record to skip
			// the header
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				Artikel artikel = new Artikel(record.get((Config.ARTIKEL_ID)), record.get(Config.ARTIKEL_LAGERFACH));
				artikelList.add(artikel);
			}

			// Print the new student list
			for (Artikel a : artikelList) {
				System.out.println(a.toString());
			}
			return artikelList;
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			System.out.println(fileName+" is not a directory\n try again!");
			e.printStackTrace();
			return null;
		}
	}

}
