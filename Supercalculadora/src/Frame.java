import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Frame extends JFrame {

	private JTextArea txtNumero1;
	private JTextArea txtNumero2;
	private JTextArea txtResultado;
	private JButton btnCalcular;
	
	public Frame() {
		super("Super calculadora");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(500, 600);
		setLocationRelativeTo(null);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		iniciarComponentes();
		
		setVisible(true);
	}
	
	private void iniciarComponentes() {
		txtNumero1 = new JTextArea(1, 40);
		txtNumero1.setLineWrap(true);
		
		txtNumero2 =  new JTextArea();
		txtNumero2.setLineWrap(true);
		
		txtResultado = new JTextArea();
		txtResultado.setLineWrap(true);
		txtResultado.setEditable(false);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String num1 = txtNumero1.getText().trim();
				String num2 = txtNumero2.getText().trim();
				
				boolean unoMayor = Calculadora.esMayor(num1, num2);
				boolean dosMayor = Calculadora.esMayor(num2, num1);
				
				Lista sumatoria = Calculadora.sumar(num1, num2);
				Lista resta = Calculadora.restar(num1, num2);
				Lista multiplicacion = Calculadora.multiplicar(num1, num2);
				
				txtResultado.setText("");
				
				if (unoMayor) {
					txtResultado.append("Número 1 es el mayor");
				} else if (dosMayor) {
					txtResultado.append("Número 2 es el mayor");
				} else {
					txtResultado.append("Los dos números son iguales");
				}
				
				txtResultado.append("\nSuma: " + sumatoria);
				txtResultado.append("\nResta: " + resta);
				txtResultado.append("\nMultiplicación: " + multiplicacion);
			}
		});
		
		add(new JLabel("Número 1:"));
		add(new JScrollPane(txtNumero1));
		
		add(new JLabel("Número 2: "));
		add(new JScrollPane(txtNumero2));
		
		add(new JLabel("Resultado: "));
		add(new JScrollPane(txtResultado));
		
		add(btnCalcular);
	}
}