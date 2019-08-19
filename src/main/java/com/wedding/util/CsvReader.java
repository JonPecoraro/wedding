package com.wedding.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.wedding.model.Guest;

public class CsvReader {
	private static final String DEFAULT_FILE_NAME = "classpath:files/guestList.csv";
	private static final String COMMA_DELIMITER = ",";
	private static final String SPACE_DELIMITER = " ";
	
	private String filename;
	
	public CsvReader() {
		this.filename = DEFAULT_FILE_NAME;
	}
	
	public List<Guest> readGuestList() {
		List<List<String>> records = getRecords();
		if (records.isEmpty()) {
			return null;
		}
		
		List<Guest> guestList = new ArrayList<>();
		for (List<String> record : records) {
			Guest guest = getGuestFromRecord(record);
			guestList.add(guest);
		}
		
		return guestList;
	}
	
	private List<List<String>> getRecords() {
		List<List<String>> records = new ArrayList<>();
		
		try {
			File guestListFile = ResourceUtils.getFile(this.filename);
			BufferedReader br = new BufferedReader(new FileReader(guestListFile));
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
				records.add(Arrays.asList(values));
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return records;
	}
	
	private Guest getGuestFromRecord(List<String> record) {
		String name = record.get(0);
		String[] splitName = name.split(SPACE_DELIMITER);
		
		String firstName = "";
		String lastName = "";
		
		if (splitName.length > 0) {
			firstName = splitName[0];
			if (splitName.length > 1) {
				lastName = splitName[1];
			}
		}
		
		Guest guest = new Guest();
		guest.setFirstName(firstName);
		guest.setLastName(lastName);
		
		return guest;
	}
}
