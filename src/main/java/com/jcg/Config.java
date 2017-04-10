package com.jcg;

public class Config {
	// Student attributes
		public static final String ARTIKEL_ID = "druck_pseudonym";
		public static final String ARTIKEL_LAGERFACH = "lager_fach";
		public static final char SEPERATOR=';';
		public static final char QUOTECHAR='"';
		
		//Nummernkreise
		public static final String ebayNummern="[a-cA-C][0-9]{5,5}"; //A15000
		public static final String shopNummern="[sS][0-9]{5,5}";	//S15000	
		public static final String lagerPlätze="[kKsSiIaA]\\d\\d-\\d-\\d";		//K41-5-3

}
