package Vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Game.Carte;
import Game.Sound;
import Game.WavPlayer;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TicketFrame extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TicketFrame(ArrayList<Carte> cartes, String son) throws Exception {
		setTitle("LOOT !");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnValidate = new JButton("OK");
		btnValidate.setBounds(23, 465, 255, 25);
		contentPane.add(btnValidate);
		
		final WavPlayer wp = new WavPlayer(new File(son));
		new Thread(new Runnable() {
			public void run() {
				wp.open();
				wp.play();
			}
		}).start();
		
		btnValidate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	wp.stop();
		    	wp.close();
		       ((TicketFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
		    }
		});
		
		JLabel lblNewLabel = new JLabel("Cartes manqu\u00E9es :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(359, 51, 219, 35);
		contentPane.add(lblNewLabel);
		
		PanelCardLoot panelCard1 = new PanelCardLoot(cartes.get(0),1);
		panelCard1.setBounds(23, 13, 320, 400);
		contentPane.add(panelCard1);
		
		PanelCardLoot panelCard2 = new PanelCardLoot(cartes.get(1),2);
		panelCard2.setBounds(369, 99, 160, 200);
		contentPane.add(panelCard2);
		
		PanelCardLoot panelCard3 = new PanelCardLoot(cartes.get(2),2);
		panelCard3.setBounds(541, 99, 160, 200);
		contentPane.add(panelCard3);
		
		PanelCardLoot panelCard4 = new PanelCardLoot(cartes.get(3),2);
		panelCard4.setBounds(713, 99, 160, 200);
		contentPane.add(panelCard4);
		
		PanelCardLoot panelCard5 = new PanelCardLoot(cartes.get(4),2);
		panelCard5.setBounds(885, 99, 160, 200);
		contentPane.add(panelCard5);
		
		this.setVisible(true);
	}
}
