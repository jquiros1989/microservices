package com.ucreativa.calculadoramonolitica;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BusinessLayer {

	public static String sumar(String n1, String n2) {
		Integer resultadoInt = Integer.parseInt(n1) + Integer.parseInt(n2);
		salvar(resultadoInt.toString(), "suma.db");
		return resultadoInt.toString();
	}

	public static String multi(String numero1, String numero2) {
		Integer resultadoInt = Integer.parseInt(numero1) * Integer.parseInt(numero2);
		salvar(resultadoInt.toString(), "multiplicacion.db");
		return resultadoInt.toString();
	}

	public static void salvar(String resultado, String file) {
		try (Socket socket = getSocketConfig();) {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream, true);
			writer.println(resultado + "," + file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Socket getSocketConfig() throws UnknownHostException, IOException {
		InetAddress ip = InetAddress.getLocalHost();
		int port = 8081;
		return new Socket(ip, port);
	}

}
