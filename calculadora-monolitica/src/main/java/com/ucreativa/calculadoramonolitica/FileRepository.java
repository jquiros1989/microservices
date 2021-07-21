package com.ucreativa.calculadoramonolitica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileRepository {
    public static void saveDocument(String texto, String file){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(texto + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
