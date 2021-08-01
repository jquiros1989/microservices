package com.ucreativa.microserviciosuma.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;


@Service
public class FileStoreService {
	
	private final static String fileName = "sumaFile.txt";

	
	public void saveFile(String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.append(content + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
