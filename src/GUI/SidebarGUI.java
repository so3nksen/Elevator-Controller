package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Util.Props;
import Util.SaveCsvData;

public class SidebarGUI implements ActionListener {

	// enum of content panels showable
	private enum MenuState {
		REQUEST, INFO, STATS, EXIT
	}

	// init panels
	private JPanel root;
	private JPanel contentPanel;

	/**
	 * Method to start gui.
	 */
	public void startUserInterface() {
		JFrame jf = createFrame();
		jf.setVisible(true);
	}

	/**
	 * create jframe and child content.
	 * 
	 * @return JFrame -> jframe to show.
	 */
	private JFrame createFrame() {

		JFrame jf = new JFrame();
		// start fullscreen
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// window listener for closing operation
		jf.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(jf, "Wollen Sie die Änderungen speichern?", "Programm beenden.",
						JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
					SaveCsvData s = new SaveCsvData();
					s.save();
					return;
				}
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		//
		// create inner content pane
		//
		root = new JPanel(new GridBagLayout());
		// root.setBorder(new EmptyBorder(30, 30, 30, 30));
		jf.setContentPane(root);

		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		//
		// add sidebar panel
		//
		c.weightx = 0.05;
		c.weighty = 1;
		root.add(createSideBar(), c);

		//
		// add content panel
		//
		contentPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		c.weightx = 0.9;
		c.weighty = 1;
		contentPanel.setBackground(Color.BLUE);
		root.add(contentPanel, c);

		ElevatorRequestPanel erp = new ElevatorRequestPanel();
		JPanel panel = erp.get();
		c.weightx = 1;
		c.weighty = 1;
		contentPanel.add(panel, c);

		return jf;
	}

	/**
	 * Sidebar of gui.
	 * 
	 * @return JPanel -> sidebar to be added.
	 */
	private JPanel createSideBar() {

		JPanel sidebar = new JPanel(new GridBagLayout());
		sidebar.setBackground(Color.LIGHT_GRAY);

		// init grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		/**
		 * 
		 * INFO BUTTON PANEL
		 * 
		 */
		JPanel requestPanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.25;
		sidebar.add(requestPanel, c);

		// try to load request image
		try {
			BufferedImage img = ImageIO.read(new File(Props.ABSOLUTE_PATH + Props.REQUEST_ICON));
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			c.gridx = 0;
			c.gridy = 0;
			c.weighty = 0.5;
			requestPanel.add(imgLabel, c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// invisible btn fills whole parent
		JButton requestBtn = new JButton();
		requestBtn.addActionListener(this);
		requestBtn.setActionCommand(MenuState.REQUEST.name());
		requestBtn.setBackground(null);
		requestBtn.setOpaque(false);
		requestBtn.setContentAreaFilled(false);
		requestBtn.setBorderPainted(false);
		requestBtn.setBorder(null);
		requestBtn.setMargin(new Insets(0, 0, 0, 0));
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 1;
		requestPanel.add(requestBtn, c);

		/**
		 * 
		 * INFO BUTTON PANEL
		 * 
		 */
		JPanel infoPanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.25;
		sidebar.add(infoPanel, c);

		// try to load info image
		try {
			BufferedImage img = ImageIO.read(new File(Props.ABSOLUTE_PATH + Props.INFO_ICON));
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			c.gridx = 0;
			c.gridy = 0;
			c.weighty = 0.5;
			infoPanel.add(imgLabel, c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// invisible btn fills whole parent
		JButton infoBtn = new JButton();
		infoBtn.addActionListener(this);
		infoBtn.setActionCommand(MenuState.INFO.name());
		infoBtn.setBackground(null);
		infoBtn.setOpaque(false);
		infoBtn.setContentAreaFilled(false);
		infoBtn.setBorderPainted(false);
		infoBtn.setBorder(null);
		infoBtn.setMargin(new Insets(0, 0, 0, 0));
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 1;
		infoPanel.add(infoBtn, c);

		/**
		 * 
		 * STATS BUTTON PANEL
		 * 
		 * 
		 */
		JPanel statsPanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0.25;
		sidebar.add(statsPanel, c);

		// try to load stats image
		try {
			BufferedImage img = ImageIO.read(new File(Props.ABSOLUTE_PATH + Props.STATS_ICON));
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			c.gridx = 0;
			c.gridy = 0;
			c.weighty = 0.5;
			statsPanel.add(imgLabel, c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// invisible btn fills whole parent
		JButton statsBtn = new JButton();
		statsBtn.addActionListener(this);
		statsBtn.setActionCommand(MenuState.STATS.name());
		statsBtn.setBackground(null);
		statsBtn.setOpaque(false);
		statsBtn.setContentAreaFilled(false);
		statsBtn.setBorderPainted(false);
		statsBtn.setBorder(null);
		statsBtn.setMargin(new Insets(0, 0, 0, 0));
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 1;
		statsPanel.add(statsBtn, c);

		/**
		 * 
		 * STATS BUTTON PANEL
		 * 
		 * 
		 */
		JPanel exitPanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 0.25;
		sidebar.add(exitPanel, c);

		// try to load exit image
		try {
			BufferedImage img = ImageIO.read(new File(Props.ABSOLUTE_PATH + Props.EXIT_ICON));
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			c.gridx = 0;
			c.gridy = 0;
			c.weighty = 0.5;
			exitPanel.add(imgLabel, c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// invisible btn fills whole parent
		JButton exitBtn = new JButton();
		exitBtn.addActionListener(this);
		exitBtn.setActionCommand(MenuState.EXIT.name());
		exitBtn.setBackground(null);
		exitBtn.setOpaque(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setBorder(null);
		exitBtn.setMargin(new Insets(0, 0, 0, 0));
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 1;
		exitPanel.add(exitBtn, c);

		return sidebar;
	}

	/**
	 * action perform events of sidebar btns. -> working with
	 */
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();

		// show request panel
		if (action.equals(MenuState.REQUEST.name())) {
			contentPanel.removeAll();
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			ElevatorRequestPanel erp = new ElevatorRequestPanel();
			JPanel panel = erp.get();
			contentPanel.add(panel, c);
			contentPanel.revalidate();
			contentPanel.repaint();
		}

		// show info panel
		else if (action.equals(MenuState.INFO.name())) {
			contentPanel.removeAll();
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			ElevatorInfoPanel eip = new ElevatorInfoPanel();
			JPanel panel = eip.get();
			contentPanel.add(panel, c);
			contentPanel.revalidate();
			contentPanel.repaint();
		}

		// show stats panel
		else if (action.equals(MenuState.STATS.name())) {
			contentPanel.removeAll();
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			ElevatorStatsPanel esp = new ElevatorStatsPanel();
			JPanel panel = esp.get();
			contentPanel.add(panel, c);
			contentPanel.revalidate();
			contentPanel.repaint();
		}

		// execute program exit
		else if (action.equals(MenuState.EXIT.name())) {
			SaveCsvData s = new SaveCsvData();
			s.save();
			System.exit(0);
		}
	}

}
