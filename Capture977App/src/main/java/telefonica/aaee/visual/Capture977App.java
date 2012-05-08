package telefonica.aaee.visual;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import telefonica.aaee.capture977.Split977;

public class Capture977App{

	private static final String PARAMS_XML = "/Capture977R.params.xml";
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	private static JButton btnGetFiles;
	private static JButton btnExecute;
	private static JButton btnClear;
	private static JTextArea jtaFicheros;
	private static JTextArea jtaResultado;
	private static JTextField jtf, jtfHost, jtfDB, jtfUser;
	private static JPasswordField jpfPass;
	private static JFrame frame;
	private static JScrollPane scrollingResult;
	private static JScrollPane scrollingFiles;
	private static JCheckBox jcbBorrarTablas;
	private static JCheckBox jcbDetalleLlamadas;
	private static JCheckBox jcbDetalleLlamadasRI;
	private static JLabel label, jlbHost, jlbDB, jlbUser, jlbPass;
	
	private static Properties iniValues;

	public static void addComponentsToPane(Container pane) {

		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		int fila = 0;

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// c.insets = new Insets(10,10,10,10); //top padding
		c.insets = new Insets(5, 5, 5, 5); // top padding

		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		label = new JLabel("Acuerdo:");
		GridBagConstraints c1 = (GridBagConstraints)c.clone();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = fila;
		label.setBounds(0, 0, 100, 60);
		pane.add(label, c1);

		jtf = new JTextField();
		GridBagConstraints c2 = (GridBagConstraints)c.clone();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 1;
		c2.gridy = fila;
		jtf.setBounds(0, 0, 100, 60);
		pane.add(jtf, c2);

		fila++;

		jtaFicheros = new JTextArea(5, 30);
		GridBagConstraints c3 = (GridBagConstraints)c.clone();
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 0;
		c3.gridwidth = 2;
		c3.gridy = fila;
		jtaFicheros.setBounds(0, 0, 100, 60);
		scrollingFiles = new JScrollPane(jtaFicheros);
		pane.add(scrollingFiles, c3);

		btnClear = new JButton("Borra ficheros a tratar");
		GridBagConstraints c4 = (GridBagConstraints)c.clone();
		c4.fill = GridBagConstraints.HORIZONTAL;
		c4.gridx = 2; // aligned with button 2
		c4.gridwidth = 1; // 2 columns wide
		c4.gridy = fila; // third row
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaFicheros.setText("");
			}
		});
		pane.add(btnClear, c4);

		btnGetFiles = new JButton("Localizar ficheros...");
		GridBagConstraints c5 = (GridBagConstraints)c.clone();
		c5.fill = GridBagConstraints.HORIZONTAL;
		c5.weightx = 0.5;
		c5.gridx = 3;
		c5.gridy = fila;
		btnGetFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localizarFicheros(); // code to execute when button is pressed
			}

			private void localizarFicheros() {
				JFileChooser chooser;
				String choosertitle = "Pepe";
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(choosertitle);

				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setMultiSelectionEnabled(true);
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//
				if (chooser.showOpenDialog(btnGetFiles) == JFileChooser.APPROVE_OPTION) {
					System.out.println("getCurrentDirectory(): "
							+ chooser.getCurrentDirectory());
					// System.out.println("getSelectedFile() : " +
					// chooser.getSelectedFile());
					File[] ficheros = chooser.getSelectedFiles();
					for (int i = 0; i < ficheros.length; i++) {
						System.out.println("getSelectedFiles() : "
								+ ficheros[i].getAbsolutePath());
						jtaFicheros.append(ficheros[i].getAbsolutePath() + "\n");
					}
					// System.out.println("getSelectedFile() : " +
					// chooser.getSelectedFiles());
				} else {
					System.out.println("No Selection ");
				}
			}
		});

		pane.add(btnGetFiles, c5);

		fila++;

		jcbBorrarTablas = new JCheckBox("Borrar Tablas");
		GridBagConstraints c6 = (GridBagConstraints)c.clone();
		c6.fill = GridBagConstraints.HORIZONTAL;
		c6.gridx = 0; // aligned with button 2
		c6.gridwidth = 4; // 2 columns wide
		c6.gridy = fila; // third row
		//pane.add(jcbBorrarTablas, c);

		//fila++;

		jcbDetalleLlamadas = new JCheckBox(
				"Incluir detalle de llamadas? (Reg.702010)");
		GridBagConstraints c7 = (GridBagConstraints)c.clone();
		c7.fill = GridBagConstraints.HORIZONTAL;
		c7.gridx = 0; // aligned with button 2
		c7.gridwidth = 4; // 2 columns wide
		c7.gridy = fila; // third row
		pane.add(jcbDetalleLlamadas, c7);

		fila++;

		jcbDetalleLlamadasRI = new JCheckBox(
				"Incluir detalle de llamadas Red Inteligente? (Reg.702020)");
		GridBagConstraints c8 = (GridBagConstraints)c.clone();
		c8.fill = GridBagConstraints.HORIZONTAL;
		c8.gridx = 0; // aligned with button 2
		c8.gridwidth = 4; // 2 columns wide
		c8.gridy = fila; // third row
		pane.add(jcbDetalleLlamadasRI, c8);

		fila++;

		jtaResultado = new JTextArea(6, 50);
		GridBagConstraints c9 = (GridBagConstraints)c.clone();
		c9.fill = GridBagConstraints.HORIZONTAL;
		c9.gridx = 0;
		c9.gridwidth = 3;
		c9.gridy = fila;
		jtaResultado.setBounds(0, 0, 100, 60);
		// jtaResultado.
		scrollingResult = new JScrollPane(jtaResultado,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.add(scrollingResult, c9);

		fila++;

		jlbHost = new JLabel("Host");
		GridBagConstraints c10 = (GridBagConstraints)c.clone();
		c10.fill = GridBagConstraints.HORIZONTAL;
		c10.gridx = 0;
		c10.gridwidth = 1;
		c10.gridy = fila;
		jlbHost.setBounds(0, 0, 100, 40);
		pane.add(jlbHost, c10);

		jlbDB = new JLabel("DB");
		GridBagConstraints c11 = (GridBagConstraints)c.clone();
		c11.fill = GridBagConstraints.HORIZONTAL;
		c11.gridx = 1;
		c11.gridwidth = 1;
		c11.gridy = fila;
		jlbDB.setBounds(0, 0, 100, 40);
		pane.add(jlbDB, c11);

		jlbUser = new JLabel("Usuario");
		GridBagConstraints c12 = (GridBagConstraints)c.clone();
		c12.fill = GridBagConstraints.HORIZONTAL;
		c12.gridx = 2;
		c12.gridwidth = 1;
		c12.gridy = fila;
		jlbUser.setBounds(0, 0, 100, 40);
		pane.add(jlbUser, c12);

		jlbPass = new JLabel("Contraseña");
		GridBagConstraints c13 = (GridBagConstraints)c.clone();
		c13.fill = GridBagConstraints.HORIZONTAL;
		c13.gridx = 3;
		c13.gridwidth = 1;
		c13.gridy = fila;
		jlbPass.setBounds(0, 0, 100, 40);
		pane.add(jlbPass, c13);

		fila++;

		jtfHost = new JTextField(iniValues.getProperty("host"));
		GridBagConstraints c14 = (GridBagConstraints)c.clone();
		c14.fill = GridBagConstraints.HORIZONTAL;
		c14.gridx = 0;
		c14.gridwidth = 1;
		c14.gridy = fila;
		jtfHost.setBounds(0, 0, 100, 60);
		pane.add(jtfHost, c14);

		jtfDB = new JTextField(iniValues.getProperty("db"));
		GridBagConstraints c15 = (GridBagConstraints)c.clone();
		c15.fill = GridBagConstraints.HORIZONTAL;
		c15.gridx = 1;
		c15.gridwidth = 1;
		c15.gridy = fila;
		jtfDB.setBounds(0, 0, 100, 60);
		pane.add(jtfDB, c15);

		jtfUser = new JTextField(iniValues.getProperty("user"));
		GridBagConstraints c16 = (GridBagConstraints)c.clone();
		c16.fill = GridBagConstraints.HORIZONTAL;
		c16.gridx = 2;
		c16.gridwidth = 1;
		c16.gridy = fila;
		jtfUser.setBounds(0, 0, 100, 60);
		pane.add(jtfUser, c16);

		jpfPass = new JPasswordField(iniValues.getProperty("pass"));
		GridBagConstraints c17 = (GridBagConstraints)c.clone();
		c17.fill = GridBagConstraints.HORIZONTAL;
		c17.gridx = 3;
		c17.gridwidth = 1;
		c17.gridy = fila;
		jpfPass.setBounds(0, 0, 100, 60);
		pane.add(jpfPass, c17);

		fila++;

		btnExecute = new JButton("Ejecuta");
		GridBagConstraints c18 = (GridBagConstraints)c.clone();
		c18.fill = GridBagConstraints.HORIZONTAL;
		c18.gridx = 3; // aligned with button 2
		c18.gridwidth = 1; // 2 columns wide
		c18.gridy = fila; // third row
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procesaFicheros();
				
			}

			private void procesaFicheros() {

				try {
					Split977 sp = new Split977();
					sp.setAcuerdo(jtf.getText());
					String[] f = jtaFicheros.getText().split("\n");
					JOptionPane.showMessageDialog(frame, "Número de ficheros:"
							+ f.length);
					sp.setFicheros(f);
					sp.setBorrarTablas(jcbBorrarTablas.isSelected());
					sp.setDetalleLlamadas(jcbDetalleLlamadas.isSelected());
					sp.setDetalleLlamadasRI(jcbDetalleLlamadasRI.isSelected());
					for (int i = 0; i < f.length; i++) {
						jtaResultado.append(f[i] + "\n");
					}

					sp.setDbHost(jtfHost.getText());
					sp.setDbName(jtfDB.getText());
					sp.setDbUser(jtfUser.getText());
					String pass = new String(jpfPass.getPassword());
					sp.setDbPass(pass);
					System.out.println(pass);

					jtaResultado.append(sp.execute());
					// JOptionPane.showConfirmDialog(frame, "Finito!");
					JOptionPane.showMessageDialog(frame, "Finito!");
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
		
		);
		pane.add(btnExecute, c18);

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Captura 977R");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = frame.getSize();

		int windowX = Math.max(0, (screenSize.width - windowSize.width) / 2);
		int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

		frame.setLocation(windowX, windowY); // Don't use "f." inside
		// constructor.
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				try {
					iniValues = new Properties();
					// FileInputStream fis = new
					// FileInputStream("Capture977R.params.xml");
					// iniValues.loadFromXML(fis);

					System.out.println(PARAMS_XML);
					InputStream XMLstream = getClass().getResourceAsStream(
							PARAMS_XML);
					if (XMLstream == null) {
						throw new FileNotFoundException(
								"No se ha encontrado el fichero en el JAR "
										+ ":" + PARAMS_XML);
					}
					iniValues.loadFromXML(XMLstream);

					createAndShowGUI();
				} catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				} catch (InvalidPropertiesFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param jcbBorrarTablas
	 *            the jcbBorrarTablas to set
	 */
	public static void setJcbBorrarTablas(JCheckBox jcbBorrarTablas) {
		Capture977App.jcbBorrarTablas = jcbBorrarTablas;
	}

	/**
	 * @return the jcbBorrarTablas
	 */
	public static JCheckBox getJcbBorrarTablas() {
		return jcbBorrarTablas;
	}
	
	
	
	
	
	class ProcesaFicherosTask extends SwingWorker<Void, Void> {
	    /*
	     * Main task. Executed in background thread.
	     */
	    @Override
	    public Void doInBackground() {

	      int progress = 0;
	      // Initialize progress property.
	      setProgress(0);
          setProgress(Math.min(progress, 100));
          
			try {
				Split977 sp = new Split977();
				sp.setAcuerdo(jtf.getText());
				String[] f = jtaFicheros.getText().split("\n");
				JOptionPane.showMessageDialog(frame, "Número de ficheros:"
						+ f.length);
				sp.setFicheros(f);
				sp.setBorrarTablas(jcbBorrarTablas.isSelected());
				sp.setDetalleLlamadas(jcbDetalleLlamadas.isSelected());
				sp.setDetalleLlamadasRI(jcbDetalleLlamadasRI.isSelected());
				for (int i = 0; i < f.length; i++) {
					jtaResultado.append(f[i] + "\n");
				}

				sp.setDbHost(jtfHost.getText());
				sp.setDbName(jtfDB.getText());
				sp.setDbUser(jtfUser.getText());
				String pass = new String(jpfPass.getPassword());
				sp.setDbPass(pass);
				System.out.println(pass);

				jtaResultado.append(sp.execute());
				// JOptionPane.showConfirmDialog(frame, "Finito!");
				JOptionPane.showMessageDialog(frame, "Finito!");
			} catch (Exception e) {
				// TODO: handle exception
			}
          

	      return null;
	    }

		/*
	     * Executed in event dispatch thread
	     */
	    public void done() {
	      Toolkit.getDefaultToolkit().beep();
	      jtaResultado.append("Fet!\n");
	    }
	    
	    

	  }
}
