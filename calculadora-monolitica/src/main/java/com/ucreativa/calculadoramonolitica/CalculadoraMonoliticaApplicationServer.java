package com.ucreativa.calculadoramonolitica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculadoraMonoliticaApplicationServer {

	public static void main(String[] args) {
		ServerSocket serverSocket;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8081);
			socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (true) {
				InputStream inputStream;

				inputStream = socket.getInputStream();

				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String[] messageFromClient = reader.readLine().split(",");
				System.out.println("Mensaje del cliente: " + messageFromClient);
				FileRepository.saveDocument(messageFromClient[0], messageFromClient[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SpringApplication.run(CalculadoraMonoliticaApplicationServer.class, args);

	}

}
