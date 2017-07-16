package com.jcg.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import com.jcg.Config;
import com.jcg.model.Artikel;

/**
 * @author ashraf
 * 
 */
public class CsvFileWriter {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final Object[] FILE_HEADER = { Config.ARTIKEL_ID, Config.ARTIKEL_LAGERFACH };

	public void writeCsvFile(List<Artikel> artikelList,String fileNeu) {
		
		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.newFormat(Config.SEPERATOR).withQuote(Config.QUOTECHAR).withRecordSeparator(NEW_LINE_SEPARATOR).withQuoteMode(QuoteMode.ALL);
		
		try (// initialize FileWriter object
				
			FileWriter fileWriter = new FileWriter(fileNeu);

				// initialize CSVPrinter object
			CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);) {

			
			// Create CSV file header
			csvFilePrinter.printRecord(FILE_HEADER);

			// Write a new student object list to the CSV file
			for (Artikel a : artikelList) {
				List<String> artikelDataRecord = new ArrayList<String>();
				artikelDataRecord.add(a.druck_pseudonym);
				artikelDataRecord.add(a.lager_fach);
				csvFilePrinter.printRecord(artikelDataRecord);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();

		}
	}
}
