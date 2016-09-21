package Vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Game.Carte;
import Game.WavPlayer;
import javax.swing.SwingConstants;

public class EventTicketFrame extends JFrame {

	private PanelBackGround contentPane;
	//private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventTicketFrame frame = new EventTicketFrame(new Carte("cards/6/char_"+1+".txt",6), "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public EventTicketFrame(Carte c, String son) throws Exception {
		setTitle("LOOT !");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new PanelBackGround(c,son);
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnValidate = new JButton("OK");
		btnValidate.setBounds(480, 583, 320, 38);
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
		       ((EventTicketFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
		    }
		});
		
		PanelCardLoot panelCard1 = new PanelCardLoot(c,1);
		//JPanel panelCard1 = new JPanel();
		panelCard1.setBounds(480, 94, 320, 400);
		contentPane.add(panelCard1);
		
		
		JLabel lblNewLabel = new JLabel("F\u00E9licitations ! Vos efforts de cette saison vous ont permis de d\u00E9bloquer :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(340, 13, 600, 16);
		contentPane.add(lblNewLabel);
		
		this.setVisible(true);
		contentPane.add(btnValidate, lblNewLabel, panelCard1);
	}
}
