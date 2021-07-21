package com.ucreativa.calculadoramonolitica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CalculadoraMonoliticaApplicationClient {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mi calculadora");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 100);
		frame.setLayout(new GridLayout(2, 2));
		JTextField numero1 = new JTextField();
		JTextField numero2 = new JTextField();
		JLabel resultado = new JLabel("Aqui va el resultado");

		JButton suma = new JButton("Suma");
		JButton multi = new JButton("Multiplicacion");
		suma.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String suma = BusinessLayer.sumar(numero1.getText(), numero2.getText());

				resultado.setText(suma);
			}
		});

		multi.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = BusinessLayer.multi(numero1.getText(), numero2.getText());
				resultado.setText(result);
			}
		});

		frame.add(numero1);
		frame.add(numero2);
		frame.add(resultado);
		frame.add(suma);
		frame.add(multi);
		frame.setVisible(true);
		SpringApplication.run(CalculadoraMonoliticaApplicationClient.class, args);
	}

}
