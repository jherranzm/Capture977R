package telefonica.aaee.visual;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import telefonica.aaee.capture977.Split977;
import telefonica.aaee.util.Utilidades;
import telefonica.aaee.webutils.ConfigUtils;


public class PantallaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String PARAMS_XML = "/Capture977R.params.xml"; 
	private static Properties iniValues;
	private static PantallaPrincipal frame;

	private JPanel contentPane;
	private JTextField textAcuerdo;

	private JTextField jtfHost;
	private JTextField jtfDB;
	private JTextField jtfUser;
	private JPasswordField jtfPass;

	private JTextArea textArea;
	private JTextArea jtaResultado;
	private JCheckBox checkDetalleTRF;
	private JCheckBox chckDetalleRedInteligente;

	private JComboBox seleccionaAcuerdo;

	private static final String ERROR_NO_SE_HA_INFORMADO_EL_ACUERDO = "Nada que hacer: no se ha informado el acuerdo!";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					iniValues = new Properties();
					// FileInputStream fis = new
					// FileInputStream("Capture977R.params.xml");
					// iniValues.loadFromXML(fis);

					System.out.println(PARAMS_XML);
					InputStream XMLstream = getClass().getResourceAsStream(PARAMS_XML);
					if (XMLstream == null) {
						throw new FileNotFoundException("No se ha encontrado el fichero en el JAR " 
								+ ":" + PARAMS_XML); 
					}
					iniValues.loadFromXML(XMLstream);
					XMLstream = getClass().getResourceAsStream(PARAMS_XML);
					ConfigUtils.configDB.loadFromXML(XMLstream);

					frame = new PantallaPrincipal();
					frame.setVisible(true);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					e.printStackTrace();
				} catch (InvalidPropertiesFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setTitle("Captura Datos formato 977R"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 791, 25);
		contentPane.add(menuBar);

		JMenu mnArchivo = new JMenu("Archivo"); 
		menuBar.add(mnArchivo);

		JMenuItem mntmSalir = new JMenuItem("Salir"); 
		mntmSalir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnUtilidades = new JMenu("Utilidades"); 
		menuBar.add(mnUtilidades);

		JMenuItem mntmCopiaCondiciones = new JMenuItem("Copia Condiciones (2 acuerdos)"); 
		mntmCopiaCondiciones.addActionListener(new CopiaCondicionesListener(this));
		mnUtilidades.add(mntmCopiaCondiciones);

		JMenuItem mntmCargaDatosCuotasTrafico = new JMenuItem("Carga Datos Cuotas y Tráfico"); 
		mntmCargaDatosCuotasTrafico.addActionListener(new CargaDatosCuotasTraficoListener(this));
		mnUtilidades.add(mntmCargaDatosCuotasTrafico);

		JMenuItem mntmAplicaCondiciones = new JMenuItem("Aplica Condiciones"); 
		mntmAplicaCondiciones.addActionListener(new AplicaCondicionesListener(this));
		mnUtilidades.add(mntmAplicaCondiciones);

		JPanel panel = new JPanel();
		panel.setBounds(0, 26, 791, 535);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAcuerdo = new JLabel("Acuerdo:"); 
		lblAcuerdo.setBounds(10, 33, 69, 23);
		panel.add(lblAcuerdo);

		lblAcuerdo.setLabelFor(textAcuerdo);

		textAcuerdo = new JTextField();
		textAcuerdo.setBounds(79, 33, 541, 23);
		panel.add(textAcuerdo);
		textAcuerdo.setColumns(10);

		JLabel lblAcuerdos = new JLabel("Acuerdos:"); 
		lblAcuerdos.setBounds(10, 60, 69, 23);
		panel.add(lblAcuerdos);

		seleccionaAcuerdo = new JComboBox();
		seleccionaAcuerdo.setBounds(79, 60, 541, 23);

		updateJComboAcuerdos();

		panel.add(seleccionaAcuerdo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 610, 152);
		panel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setColumns(75);
		textArea.setBounds(new Rectangle(0, 0, 610, 172));
		textArea.setRows(8);
		JScrollPane scrollingtextArea = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollingtextArea.setBounds(10, 314, 610, 172);
		scrollPane.add(scrollingtextArea);

		final JButton buttonGetFiles = new JButton("Localizar ficheros..."); 
		buttonGetFiles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				localizarFicheros(); // code to execute when button is pressed
			}



			private void localizarFicheros() {
				String choosertitle = "Localizar ficheros para cargar..."; 

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(".")); 
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setMultiSelectionEnabled(true);

				// disable the "All files" option.
				chooser.setAcceptAllFileFilterUsed(false);
				//
				if (chooser.showOpenDialog(buttonGetFiles) == JFileChooser.APPROVE_OPTION) {
					System.out.println("getCurrentDirectory(): " 
							+ chooser.getCurrentDirectory());
					// System.out.println("getSelectedFile() : " +
					// chooser.getSelectedFile());
					File[] ficheros = chooser.getSelectedFiles();
					for (int i = 0; i < ficheros.length; i++) {
						System.out.println("getSelectedFiles() : " 
								+ ficheros[i].getAbsolutePath());
						textArea.append(ficheros[i].getAbsolutePath() + "\n"); 
					}
					// System.out.println("getSelectedFile() : " +
					// chooser.getSelectedFiles());
				} else {
					System.out.println("No Selection "); 
				}
			}
		});
		buttonGetFiles.setBounds(641, 33, 127, 23);
		panel.add(buttonGetFiles);

		JButton buttonDeleteFiles = new JButton("Borrar ficheros"); 
		buttonDeleteFiles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textArea.setText(""); 
			}
		});
		buttonDeleteFiles.setBounds(641, 67, 128, 23);
		panel.add(buttonDeleteFiles);

		checkDetalleTRF = new JCheckBox("Incluir Detalle de llamadas (702010)"); 
		checkDetalleTRF.setBounds(10, 246, 610, 23);
		panel.add(checkDetalleTRF);

		chckDetalleRedInteligente = new JCheckBox("Incluir detalle de llamadas Red Inteligente? (Reg.702020)"); 
		chckDetalleRedInteligente.setBounds(10, 272, 610, 23);
		panel.add(chckDetalleRedInteligente);

		jtaResultado = new JTextArea();
		jtaResultado.setColumns(75);
		jtaResultado.setBounds(new Rectangle(0, 0, 610, 160));

		JScrollPane scrollingResult = new JScrollPane(jtaResultado, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollingResult.setBounds(10, 314, 610, 160);
		panel.add(scrollingResult);

		JLabel jlbHost = new JLabel("Host"); 
		jlbHost.setLabelFor(jtfHost);
		jlbHost.setBounds(10, 485, 46, 14);
		panel.add(jlbHost);

		jtfHost = new JTextField(iniValues.getProperty("host")); 
		jtfHost.setBounds(10, 504, 140, 20);
		panel.add(jtfHost);
		jtfHost.setColumns(10);

		JLabel jlblDB = new JLabel("DB"); 
		jlblDB.setLabelFor(jtfDB);
		jlblDB.setBounds(160, 485, 46, 14);
		panel.add(jlblDB);

		jtfDB = new JTextField(iniValues.getProperty("db")); 
		jtfDB.setColumns(10);
		jtfDB.setBounds(160, 504, 140, 20);
		panel.add(jtfDB);

		JLabel lblUser = new JLabel("User"); 
		lblUser.setLabelFor(jtfUser);
		lblUser.setBounds(310, 485, 46, 14);
		panel.add(lblUser);

		jtfUser = new JTextField(iniValues.getProperty("user")); 
		jtfUser.setColumns(10);
		jtfUser.setBounds(310, 504, 140, 20);
		panel.add(jtfUser);

		JLabel lblPass = new JLabel("Pass"); 
		lblPass.setBounds(460, 485, 46, 14);
		panel.add(lblPass);

		jtfPass = new JPasswordField(iniValues.getProperty("pass")); 
		lblPass.setLabelFor(jtfPass);
		jtfPass.setBounds(460, 504, 158, 20);
		panel.add(jtfPass);

		JButton buttonExecute = new JButton("Ejecuta"); 
		buttonExecute.addActionListener(new ButtonExecuteListener(this));
		buttonExecute.setBounds(641, 293, 127, 23);
		panel.add(buttonExecute);

		JButton buttonBorrarAcuerdo = new JButton("Borrar Datos Acuerdo"); 
		buttonBorrarAcuerdo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String acuerdoActual = textAcuerdo.getText();
				JOptionPane.showMessageDialog(frame, "Inicio:Borrar Datos Acuerdo " + acuerdoActual); 
				Calendar ini = Calendar.getInstance();
				long lIni = ini.getTimeInMillis();

				// final DB977RDAOFactory factory = DB977RDAOFactory
				// .getDAOFactory(AAEEModPlanas.DRIVER_MANAGER_MYSQL);
				//
				// String acuerdo = textAcuerdo.getText();
				// String ret =
				// factory.getDBIdb977rdao().callStoredProcBorrarDatosAcuerdo(acuerdo);

				
				String ret = Utilidades.borrarDatosAcuerdo(acuerdoActual);
				jtaResultado.setText("Resultado:" + ret); 

				Calendar fin = Calendar.getInstance();
				long lFin = fin.getTimeInMillis();

				JOptionPane.showMessageDialog(frame,
						"Finito!\nTiempo empleado:" + ((lFin - lIni) / 1000) + " segundos.");  
			}
		});
		buttonBorrarAcuerdo.setBounds(639, 101, 129, 23);
		panel.add(buttonBorrarAcuerdo);

	}



	private void updateJComboAcuerdos() {
		seleccionaAcuerdo.removeAll();
		List<String> acuerdos = Utilidades.getAcuerdos();
		for (String acuerdo : acuerdos) {
			seleccionaAcuerdo.addItem(acuerdo);
		}
	}
	
	
	class CopiaCondicionesListener implements ActionListener {
		public CopiaCondicionesListener() {}
		public CopiaCondicionesListener(PantallaPrincipal pantalla) {}

		public void actionPerformed(ActionEvent e) {
			String acuerdoAct = textAcuerdo.getText();
			String acuerdoAnt = seleccionaAcuerdo.getSelectedItem().toString();

			if (acuerdoAct == null || "".equals(acuerdoAct)) { 
				JOptionPane.showMessageDialog(frame, "Falta informar el acuerdo destino!"); 
			} else {
				String msg = "Copia Condiciones del acuerdo " + acuerdoAnt + " al acuerdo " + acuerdoAct;  
				JOptionPane.showMessageDialog(frame, "Inicio:" + msg);
				textArea.append(msg + "\n"); 
				String res = Utilidades.copiaCondiciones(acuerdoAct, acuerdoAnt);
				jtaResultado.append(res + "\n"); 
				JOptionPane.showMessageDialog(frame, "Fin:" + msg);
				textArea.append(msg + "\n"); 
			}			
		}
		
	}

	
	
	class CargaDatosCuotasTraficoListener implements ActionListener {
		public CargaDatosCuotasTraficoListener() {}
		public CargaDatosCuotasTraficoListener(PantallaPrincipal pantalla) {}

		public void actionPerformed(ActionEvent e) {
			String acuerdoAct = textAcuerdo.getText();
			if (acuerdoAct == null) {
				jtaResultado.append(ERROR_NO_SE_HA_INFORMADO_EL_ACUERDO); 
			} else {
				String msg = "Carga Datos Cuotas y Tráfico del acuerdo ["+acuerdoAct+"]";
				JOptionPane.showMessageDialog(frame, "Inicio:" + msg); 
				textArea.append("acuerdoAct:" + acuerdoAct + "\n");  
				String res = Utilidades.cargaDatosCuotasTrafico(acuerdoAct);
				jtaResultado.append("Resultado:" + res + "\n");  
				JOptionPane.showMessageDialog(frame, "Fin:" + msg); 
			}
			
		}
		
	}

	
	class AplicaCondicionesListener implements ActionListener {
		public AplicaCondicionesListener() {}
		public AplicaCondicionesListener(PantallaPrincipal pantalla) {}

		public void actionPerformed(ActionEvent e) {
			String acuerdoAct = textAcuerdo.getText();
			if (acuerdoAct == null) {
				jtaResultado.append(ERROR_NO_SE_HA_INFORMADO_EL_ACUERDO); 
			} else {
				String msg = "Aplica Condiciones al acuerdo ["+acuerdoAct+"]";
				JOptionPane.showMessageDialog(frame, "Inicio:" + msg); 
			
				textArea.append("acuerdoAct:" + acuerdoAct + "\n");  
				String res = Utilidades.aplicaCondiciones(acuerdoAct);
				jtaResultado.append("Resultado:" + res + "\n");  
				JOptionPane.showMessageDialog(frame, "Fin:" + msg); 
			}
		}
		
	}

	
	class ButtonExecuteListener implements ActionListener {

		public ButtonExecuteListener() {}
		public ButtonExecuteListener(PantallaPrincipal pantalla) {}

		public void actionPerformed(ActionEvent e) {
			new Thread(new ProcesaFicherosThread()).start();

			updateJComboAcuerdos();
		}

	}

	class ProcesaFicherosThread implements Runnable {

		public void run() {

			try {

				Split977 sp = new Split977();
				sp.setAcuerdo(textAcuerdo.getText());
				String[] f = textArea.getText().split("\n"); 
				JOptionPane.showMessageDialog(frame, "Número de ficheros:" 
						+ f.length);
				sp.setFicheros(f);
				sp.setDetalleLlamadas(checkDetalleTRF.isSelected());
				sp.setDetalleLlamadasRI(chckDetalleRedInteligente.isSelected());

				for (int i = 0; i < f.length; i++) {
					jtaResultado.append(f[i] + "\n"); 
				}
				sp.setDbHost(jtfHost.getText());
				sp.setDbName(jtfDB.getText());
				sp.setDbUser(jtfUser.getText());
				String pass = new String(jtfPass.getPassword());
				sp.setDbPass(pass);
				System.out.println(pass);

				jtaResultado.append(sp.execute());

				// JOptionPane.showConfirmDialog(frame, "Finito!");
				JOptionPane.showMessageDialog(frame,
						"Finito!\nTiempo empleado:" + (sp.getTiempoEmpleado() / 1000) + " segundos.");  
			} catch (Exception e) {
				// TODO: handle exception
			}// try
		}
	}



	public JTextArea getTextArea() {
		return textArea;
	}



	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}



	public JCheckBox getCheckDetalleTRF() {
		return checkDetalleTRF;
	}



	public void setCheckDetalleTRF(JCheckBox checkDetalleTRF) {
		this.checkDetalleTRF = checkDetalleTRF;
	}



	public JCheckBox getChckDetalleRedInteligente() {
		return chckDetalleRedInteligente;
	}



	public void setChckDetalleRedInteligente(JCheckBox chckDetalleRedInteligente) {
		this.chckDetalleRedInteligente = chckDetalleRedInteligente;
	}

}
