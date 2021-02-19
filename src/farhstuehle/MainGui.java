package farhstuehle;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainGui {

	private JFrame frame;
	private ArrayList<Object> list;
	private ArrayList<Aufzugfahrt> travelData;
	private int sumPersonen;
	private int sumGewicht;
	private int sumStkGefahren;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		list = new ArrayList<Object>();
		travelData = new ArrayList<Aufzugfahrt>();
		fillArray();
		fillFahrtenArray();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 963, 684);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aufzug Controller");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(359, 27, 248, 54);
		frame.getContentPane().add(lblNewLabel);
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
		        if (JOptionPane.showConfirmDialog(frame, "Wollen Sie Änderungen speichern?", "Confirm exit.", JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
		            return;
		        }
		        saveDataToCSV();
		        System.exit(-1);
		    }
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton btnNewButton = new JButton("Aufzug rufen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"Personen", "Güter"};
				int x = JOptionPane.showOptionDialog(null, "Was wollen Sie transportieren?",
		                "Click a button",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				if(x == 0){
					int persAnz = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Personen wollen Sie befördern?"));
					if(persAnz > 30) {
						JOptionPane.showMessageDialog(null, "Zu viele Personen");
						return;
					}
					else {
						PersonenAufzug aufzug;
						int aktStock = Integer.parseInt(JOptionPane.showInputDialog("Auf welchem Stockwerk startet der Aufzug?"));
						int zielStock = Integer.parseInt(JOptionPane.showInputDialog("Zu welchem Stockwerk fährt der Aufzug?"));
						if(persAnz <= ((Aufzug) list.get(0)).getPersonenZahl()) {
							aufzug = (PersonenAufzug) findeNaechstenAufzug(aktStock, 0);
						}
						else {
							aufzug = (PersonenAufzug) findeNaechstenAufzug(aktStock, 1);
						}
						aufzug.setAktuellesStockwerk(zielStock);
						travelData.add(new Aufzugfahrt("Personenaufzug", 0, persAnz, Math.abs(aktStock - zielStock)));
					}
				}
				else if(x == 1){
					LastenAufzug aufzug;
					int gueterGew = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Kg wiegen Ihre Güter, die Sie befördern wollen?"));
					if(gueterGew > 10000) {
						JOptionPane.showMessageDialog(null, "Zu schwer");
						return;
					}
					else {
						int aktStock = Integer.parseInt(JOptionPane.showInputDialog("Auf welchem Stockwerk startet der Aufzug?"));
						int zielStock = Integer.parseInt(JOptionPane.showInputDialog("Zu welchem Stockwerk fährt der Aufzug?"));
						if(gueterGew < 5001) {
						aufzug = (LastenAufzug) findeNaechstenAufzug(aktStock, 2);
						}
						else {
							aufzug = (LastenAufzug) findeNaechstenAufzug(aktStock, 3);
						}
						aufzug.setAktuellesStockwerk(zielStock);
						travelData.add(new Aufzugfahrt("Lastenaufzug", gueterGew, 0, Math.abs(aktStock - zielStock)));
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(112, 103, 738, 60);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnVipaufzugRufen = new JButton("VIP-Aufzug rufen");
		btnVipaufzugRufen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pin = JOptionPane.showInputDialog(null, "Bitte PIN eingeben:");
				if(pin.equals("0000")) {
					String[] options = {"Personen", "Güter"};
					int x = JOptionPane.showOptionDialog(null, "Was wollen Sie transportieren?",
			                "Click a button",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(x == 0){
						int persAnz = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Personen wollen Sie befördern?"));
						if(persAnz > 5) {
							JOptionPane.showMessageDialog(null, "Zu viele Personen");
							return;
						}
						else {
							VipAufzug aufzug;
							int aktStock = Integer.parseInt(JOptionPane.showInputDialog("Auf welchem Stockwerk startet der Aufzug?"));
							int zielStock = Integer.parseInt(JOptionPane.showInputDialog("Zu welchem Stockwerk fährt der Aufzug?"));
							aufzug = (VipAufzug) findeNaechstenAufzug(aktStock, 66);
							aufzug.setAktuellesStockwerk(zielStock);
							travelData.add(new Aufzugfahrt("VIP-Aufzug", 0, persAnz, Math.abs(aktStock - zielStock)));
						}
					}
					else if(x == 1){
						int gueterGew = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Kg wiegen Ihre Güter, die Sie befördern wollen?"));
						if(gueterGew > 400) {
							JOptionPane.showMessageDialog(null, "Zu schwer");
							return;
						}
						else {
							int aktStock = Integer.parseInt(JOptionPane.showInputDialog("Auf welchem Stockwerk startet der Aufzug?"));
							int zielStock = Integer.parseInt(JOptionPane.showInputDialog("Zu welchem Stockwerk fährt der Aufzug?"));
							VipAufzug aufzug = (VipAufzug) findeNaechstenAufzug(aktStock, 66);
							aufzug.setAktuellesStockwerk(zielStock);
							travelData.add(new Aufzugfahrt("VIP-Aufzug", gueterGew, 0, Math.abs(aktStock - zielStock)));
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Falsche PIN!");
				}
			}
		});
		btnVipaufzugRufen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVipaufzugRufen.setBounds(112, 197, 738, 60);
		frame.getContentPane().add(btnVipaufzugRufen);
		
		JButton btnNewButton_1 = new JButton("Aufzug Informationen");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printArray();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(112, 290, 738, 60);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Aufzug-Attribute \u00E4ndern");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"Personenaufzüge", "Güteraufzüge", "VIP-Aufzüge"};
				String[] filter1 = {"Kleiner Aufzug", "Großer Aufzug"};
				String[] filter = {"Personenkapazität", "Maximalgewicht", ""};
 				int x = JOptionPane.showOptionDialog(null, "Von welcher Aufzugsart wollen Sie ein Attribut verändern?",
		                "Click a button",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				if(x == 0) {
					filter[2] = "Aufzugmusik";
					int y = JOptionPane.showOptionDialog(null, "Welche Personenaufzüge wollen Sie verändern?",
			                "Click a button",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter1, filter1[0]);
					if(y == 0) {
						int z = JOptionPane.showOptionDialog(null, "Welches Attribut wollen Sie verändern?",
				                "Click a button",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
						if(z == 0) {
							String newPersAnz = JOptionPane.showInputDialog("Wie viele Personen können kleine Personenaufzüge befördern?");
							for (int i = 0; i < 20; i++) {
								setNewPersonenAnzahl((Aufzug) list.get(i), newPersAnz);
							}
						}
						else if(z == 1) {
							String newMaxGew = JOptionPane.showInputDialog("Wie viel Gewicht können kleine Personenaufzüge befördern?");
							for (int i = 0; i < 20; i++) {
								setNewMaxGewicht((Aufzug) list.get(i), newMaxGew);
							}
						}
						else if(z == 2) {
							int musik = JOptionPane.showConfirmDialog(null, "Haben kleine Personenaufzüge Musik?", "Musikauswahl", JOptionPane.YES_NO_CANCEL_OPTION);
							if(musik == 0) {
								for (int i = 0; i < 20; i++) {
									((PersonenAufzug) list.get(i)).setMelodie(true);;
								}
							}
							else if(musik == 1) {
								for (int i = 0; i < 20; i++) {
									((PersonenAufzug) list.get(i)).setMelodie(false);;
								}
							}
						}
					}
					else if(y == 1) {
						int z = JOptionPane.showOptionDialog(null, "Welches Attribut wollen Sie verändern?",
				                "Click a button",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
						if(z == 0) {
							String newPersAnz = JOptionPane.showInputDialog("Wie viele Personen können große Personenaufzüge befördern?");
							for (int i = 20; i < 30; i++) {
								setNewPersonenAnzahl((Aufzug) list.get(i), newPersAnz);
							}
						}
						else if(z == 1) {
							String newMaxGew = JOptionPane.showInputDialog("Wie viel Gewicht können große Personenaufzüge befördern?");
							for (int i = 20; i < 30; i++) {
								setNewMaxGewicht((Aufzug) list.get(i), newMaxGew);
							}
						}
						else if(z == 2) {
							int musik = JOptionPane.showConfirmDialog(null, "Haben große Personenaufzüge Musik?", "Musikauswahl", JOptionPane.YES_NO_CANCEL_OPTION);
							if(musik == 0) {
								for (int i = 20; i < 30; i++) {
									((PersonenAufzug) list.get(i)).setMelodie(true);;
								}
							}
							else if(musik == 1) {
								for (int i = 20; i < 30; i++) {
									((PersonenAufzug) list.get(i)).setMelodie(false);;
								}
							}
						}
					}
				}
				else if(x == 1) {
					filter[2] = "Quadratmeter";
					int y = JOptionPane.showOptionDialog(null, "Welche Güteraufzüge wollen Sie verändern?",
			                "Click a button",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter1, filter1[0]);
					if(y == 0) {
						int z = JOptionPane.showOptionDialog(null, "Welches Attribut wollen Sie verändern?",
				                "Click a button",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
						if(z == 0) {
							String newPersAnz = JOptionPane.showInputDialog("Wie viele Personen können kleine Lastenaufzüge befördern?");
							for (int i = 30; i < 40; i++) {
								setNewPersonenAnzahl((Aufzug) list.get(i), newPersAnz);
							}
						}
						else if(z == 1) {
							String newMaxGew = JOptionPane.showInputDialog("Wie viel Gewicht können kleine Lastenaufzüge befördern?");
							for (int i = 30; i < 40; i++) {
								setNewMaxGewicht((Aufzug) list.get(i), newMaxGew);
							}
						}
						else if(z == 2) {
							String newSize = JOptionPane.showInputDialog("Wie groß (in qm) sind kleine Lastenaufzüge?");
							try {
								for (int i = 30; i < 40; i++) {
									((LastenAufzug) list.get(i)).setQm(Integer.parseInt(newSize));
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
							}
						}
					}
					else if(y == 1) {
						int z = JOptionPane.showOptionDialog(null, "Welches Attribut wollen Sie verändern?",
				                "Click a button",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
						if(z == 0) {
							String newPersAnz = JOptionPane.showInputDialog("Wie viele Personen können große Lastenaufzüge befördern?");
							for (int i = 40; i < 45; i++) {
								setNewPersonenAnzahl((Aufzug) list.get(i), newPersAnz);
							}
						}
						else if(z == 1) {
							String newMaxGew = JOptionPane.showInputDialog("Wie viel Gewicht können große Lastenaufzüge befördern?");
							for (int i = 40; i < 45; i++) {
								setNewMaxGewicht((Aufzug) list.get(i), newMaxGew);
							}
						}
						else if(z == 2) {
							String newSize = JOptionPane.showInputDialog("Wie groß (in qm) sind große Lastenaufzüge?");
							try {
								for (int i = 40; i < 45; i++) {
									((LastenAufzug) list.get(i)).setQm(Integer.parseInt(newSize));
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
							}
						}
					}
					
				}
				else if(x == 2) {
					filter[2] = "Maximalgeschwindigkeit";
					int z = JOptionPane.showOptionDialog(null, "Welches Attribut wollen Sie verändern?",
			                "Click a button",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
					if(z == 0) {
						String newPersAnz = JOptionPane.showInputDialog("Wie viele Personen können VIP-Aufzüge befördern?");
						for (int i = 45; i < 50; i++) {
							setNewPersonenAnzahl((Aufzug) list.get(i), newPersAnz);
						}
					}
					else if(z == 1) {
						String newMaxGew = JOptionPane.showInputDialog("Wie viel Gewicht können VIP-Aufzüge befördern?");
						for (int i = 45; i < 50; i++) {
							setNewMaxGewicht((Aufzug) list.get(i), newMaxGew);
						}
					}
					else if(z == 2) {
						String newSpeed = JOptionPane.showInputDialog("Wie groß (in qm) sind kleine Lastenaufzüge?");
						try {
							for (int i = 45; i < 50; i++) {
								((VipAufzug) list.get(i)).setMaxKmh(Integer.parseInt(newSpeed));
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
						}
					}
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(112, 384, 738, 60);
		frame.getContentPane().add(btnNewButton_1_1);
	}
	
	private void setNewPersonenAnzahl(Aufzug aufzug, String newValue) {
		try {
			int newAnzahl = Integer.parseInt(newValue);
			if(newAnzahl < 31) {
				aufzug.setPersonenZahl(newAnzahl);
			}
			else {
				JOptionPane.showMessageDialog(null, "Ein Aufzug kann nicht mehr als 30 Personen beförden!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
		}
	}
	
	private void setNewMaxGewicht(Aufzug aufzug, String newValue) {
		try {
			int newGewicht = Integer.parseInt(newValue);
			if(newGewicht < 10000) {
				aufzug.setPersonenZahl(newGewicht);
			}
			else {
				JOptionPane.showMessageDialog(null, "Ein Aufzug kann nicht mehr als 10000 Kilogramm beförden!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
		}
	}
	
	private void fillArray(){
		BufferedReader csvReader = null;
		try {
			String row;
			int personen = 0;
			int maxGew = 0;
			int aktSto = 0;
			csvReader = new BufferedReader(new FileReader("aufzuege.csv"));
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    if(!data[0].equals("Aufzug")) {
				    personen = Integer.parseInt(data[2]);
				    maxGew = Integer.parseInt(data[3]);
				    aktSto = Integer.parseInt(data[4]);
				    
				    if(data[0].equals("Personenaufzug")) {
				    	list.add(new PersonenAufzug(personen, maxGew, aktSto, Boolean.parseBoolean(data[1])));
				    }
				    else if(data[0].equals("Lastenaufzug")) {
				    	list.add(new LastenAufzug(personen, maxGew, aktSto, Integer.parseInt(data[1])));
				    }
				    else {
				    	list.add(new VipAufzug(personen, maxGew, aktSto, Integer.parseInt(data[1])));
				    }
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 finally {
	            try {
	            	csvReader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			
		//Unterer Teil für erstes Ausfühen -> erstellen Datei
		if(list.size() == 0) {
			for(int i = 0 ; i < 50; i++) {
				if(i < 20) {
					list.add(new PersonenAufzug(0, 1200, 0, false));
				}
				else if(i < 30) {
					list.add(new PersonenAufzug(0, 2400, 0, true));
				}
				else if(i < 40) {
					list.add(new LastenAufzug(0, 5000, 0, 3));
				}
				else if(i < 45) {
					list.add(new LastenAufzug(0, 10000, 0, 5));
				}
				else {
					list.add(new VipAufzug(0, 400, 0, 10));
				}
			}
		}	
	}
	
	public void fillFahrtenArray() {
		BufferedReader csvReader = null;
		try {
			String row;
			csvReader = new BufferedReader(new FileReader("fahrtenInfo.csv"));
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    if(!data[0].equals("Aufzugtyp")) {
			    	if(data[0].contains("ufzug")) {
			    		travelData.add(new Aufzugfahrt(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
			    	}
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 finally {
            try {
            	csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	private Object findeNaechstenAufzug(int aktStock, int typeOfAufzug) {
		int diff = 100;
		int lowPos = 0;
		int startPos;
		int endPos;
		if(typeOfAufzug == 0) {
			startPos = 0;
			endPos = 30;
		}
		else if(typeOfAufzug == 1){
			startPos = 20;
			endPos = 30;
		}
		else if(typeOfAufzug == 2){
			startPos = 30;
			endPos = 45;
		}
		else if(typeOfAufzug == 3){
			startPos = 40;
			endPos = 45;
		}
		else {
			startPos = 45;
			endPos = 50;
		}
		for (int i = startPos; i < endPos; i++) {
			int aufzugStockwerk =((Aufzug) list.get(i)).getAktuellesStockwerk();
			if(i == 0) {
			diff = Math.abs(aufzugStockwerk - aktStock);
			}
			if(aufzugStockwerk == aktStock) {
				return list.get(i);
			}
			else {
				if(diff > Math.abs(aufzugStockwerk - aktStock)) {
					diff = Math.abs(aufzugStockwerk - aktStock);
					lowPos = i;
				}
			}
		}
		return list.get(lowPos);
	}
	
	public void printArray() {
		String message = "";
		String[] options = {"Alle", "Personen", "Güter", "VIP"};
		String[] filter = new String[2];
		int y;
		int z;
		int x = JOptionPane.showOptionDialog(null, "Welche Informationen wollen Sie haben?",
                "Click a button",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(x == 0) {
			for (int i = 0; i < list.size(); i++) {
				message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + "\n";
				//System.out.println(list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk());
			}
		}
		else if(x == 1) {
			filter[0] = "Alle";
			filter[1] = "Über Aufzugmusik filtern";
			y = JOptionPane.showOptionDialog(null, "Welche Informationen wollen Sie haben?",
	                "Click a button",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
			if(y == 0) {
				for (int i = 0; i < 30; i++) {
					message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + "\n";
				}
			}
			else if(y == 1) {
				String[] filter2 = {"Mit Musik", "Ohne Musik"};
				z = JOptionPane.showOptionDialog(null, "Welche Personensaufzüge wollen Sie anzeigen?",
		                "Click a button",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter2, filter2[0]);
				if(z == 0) {
					for (int i = 0; i < 30; i++) {
						if(((PersonenAufzug) list.get(i)).getMelodie()) {
							message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + " Musik " + ((PersonenAufzug) list.get(i)).getMelodie() + "\n";
						}
					}
				}
				else if(z == 1) {
					for (int i = 0; i < 30; i++) {
						if(!((PersonenAufzug) list.get(i)).getMelodie()) {
							message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + " Musik " + ((PersonenAufzug) list.get(i)).getMelodie() + "\n";
						}
					}
				}
			}
		}
		else if(x == 2) {
			filter[0] = "Alle";
			filter[1] = "Über Quadratmeter filtern";
			y = JOptionPane.showOptionDialog(null, "Welche Informationen wollen Sie haben?",
	                "Click a button",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
			if(y == 0) {
				for (int i = 30; i < 45; i++) {
					message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + " Quadratmeter: " + ((LastenAufzug) list.get(i)).getQm() + "\n";
				}
			}
			else if(y == 1) {
				try {
					int qmEingabe = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Quadratmeter hat der gesuchte Aufzug?"));
					for (int i = 30; i < 45; i++) {
						if(qmEingabe == ((LastenAufzug) list.get(i)).getQm()) {
							message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + " Quadratmeter: " + ((LastenAufzug) list.get(i)).getQm() + "\n";
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
				}
			}
			else {
				return;
			}
		}
		else if(x == 3) {
			filter[0] = "Alle";
			filter[1] = "Über Höchstgeschwindigkeit filtern";
			y = JOptionPane.showOptionDialog(null, "Welche Informationen wollen Sie haben?",
	                "Click a button",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, filter, filter[0]);
			if(y == 0) {
				for (int i = 45; i < 50; i++) {
					message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + " Höchstgeschwindigkeit: " + ((VipAufzug) list.get(i)).getMaxKmh() + "\n";
				}
			}
			else if(y == 1) {
				try {
					int maxKmhEingabe = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Quadratmeter hat der gesuchte Aufzug?"));
					for (int i = 45; i < 50; i++) {
						if(maxKmhEingabe == ((VipAufzug) list.get(i)).getMaxKmh()) {
							message = message + list.get(i).getClass().getSimpleName() + " an Pos " + i + " Stockwerk : " + ((Aufzug) list.get(i)).getAktuellesStockwerk() + " Höchstgeschwindigkeit: " + ((VipAufzug) list.get(i)).getMaxKmh() + "\n";
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Bitte Eingabe prüfen!");
				}
			}
			else {
				return;
			}
		}
		else {
			return;
		}
		if(message != "") {
			JOptionPane.showMessageDialog(null, message);
		}
		else {
			JOptionPane.showMessageDialog(null, "Keine Aufüge ausgewählt oder gefunden!");
		}
	}
	
	public void saveDataToCSV() {
		try (PrintWriter writer = new PrintWriter(new File("aufzuege.csv"))) {

		      StringBuilder sb = new StringBuilder();
		      sb.append("Aufzug");
		      sb.append(',');
		      sb.append("Aufzug spez Information");
		      sb.append(',');
		      sb.append("Max Personen");
		      sb.append(',');
		      sb.append("Max Gesamtgewicht");
		      sb.append(',');
		      sb.append("Aktuelles Stockwerk");
		      sb.append('\n');
		      
		      for (int i = 0; i < list.size(); i++) {
				if(i < 30) {
					sb.append("Personenaufzug");
					sb.append(',');
					sb.append(((PersonenAufzug) list.get(i)).getMelodie());
				}
				else if(i < 45) {
					sb.append("Lastenaufzug");
					sb.append(',');
					sb.append(((LastenAufzug) list.get(i)).getQm());
				}
				else {
					sb.append("Vip-Aufzug");
					sb.append(',');
					sb.append(((VipAufzug) list.get(i)).getMaxKmh());
				}
				sb.append(',');
				sb.append(((Aufzug) list.get(i)).getPersonenZahl());
				sb.append(',');
				sb.append(((Aufzug) list.get(i)).getMaxGewicht());
				sb.append(',');
				sb.append(((Aufzug) list.get(i)).getAktuellesStockwerk());
				sb.append('\n');
			}
		      
		      writer.write(sb.toString());

		      System.out.println("Daten gespeichert!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
		
		try (PrintWriter writer = new PrintWriter(new File("fahrtenInfo.csv"))) {
			StringBuilder sb = new StringBuilder();
			  sb.append("Aufzugtyp");
		      sb.append(',');
		      sb.append("Fahrgewicht");
		      sb.append(',');
		      sb.append("Personenzahl");
		      sb.append(',');
		      sb.append("Stockwerke zurückgelegt");
		      sb.append("\n");
		      
		      for (Aufzugfahrt fahrt : travelData) {
				  sb.append(fahrt.getAufzugTyp());
				  sb.append(',');
				  sb.append(fahrt.getFahrtGewicht());
				  sumGewicht += fahrt.getFahrtGewicht();
			      sb.append(',');
			      sb.append(fahrt.getPersonenZahl());
			      sumPersonen += fahrt.getPersonenZahl();
			      sb.append(',');
			      sb.append(fahrt.getGefahreneStockwerke());
			      sumStkGefahren += fahrt.getGefahreneStockwerke();
			      sb.append("\n");
			}
		      sb.append("\n");
		      sb.append("\n");
		      sb.append("Das gesamte beförderte Gewicht aller Aufzüge beträgt insgesamt: " + sumGewicht);
		      sb.append("\n");
		      sb.append("Anzahl der insgesamt beförderten Personen: " + sumPersonen);
		      sb.append("\n");
		      sb.append("Anzahl der insgesamt zurückgelegten Stockwerke: " + sumStkGefahren);
		      
		      writer.write(sb.toString());
		  } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
	    }
		
	}
}
