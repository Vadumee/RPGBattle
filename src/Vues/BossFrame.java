package Vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Boutons.ButtonBossHit;
import Game.ControlerFight;
import Game.Game;
import Game.WavPlayer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.io.File;

public class BossFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BossFrame frame = new BossFrame();
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
	public BossFrame(Game g) throws Exception {
		WavPlayer wp = new WavPlayer(new File(g.boss.sound_link));
		wp.open();
		wp.play();
		ControlerFight cf = new ControlerFight(g,wp);
		setVisible(true);
		setTitle("Combat !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new PanelBossBattle(g);
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		VueBattleEnergy lblEnergyFight = new VueBattleEnergy("Energie : -",g);
		lblEnergyFight.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEnergyFight.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnergyFight.setBounds(244, 13, 533, 29);
		contentPane.add(lblEnergyFight);
		g.addObserver(lblEnergyFight);
		
		JPanel panel = new JPanel();
		panel.setBounds(731, 70, 320, 400);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 70, 128, 160);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 281, 128, 160);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 489, 128, 160);
		contentPane.add(panel_3);
		
		VueCarteBossPreview lblName1 = new VueCarteBossPreview("",g,1,1);
		lblName1.setBounds(152, 70, 303, 29);
		contentPane.add(lblName1);
		g.addObserver(lblName1);
		
		VueCarteBossPreview lblName2 = new VueCarteBossPreview("",g,1,2);
		lblName2.setBounds(152, 281, 303, 29);
		contentPane.add(lblName2);
		g.addObserver(lblName2);
		
		VueCarteBossPreview lblName3 = new VueCarteBossPreview("",g,1,3);
		lblName3.setBounds(152, 489, 303, 29);
		contentPane.add(lblName3);
		g.addObserver(lblName3);
		
		VueCarteBossPreview lblStats1 = new VueCarteBossPreview("",g,2,1);
		lblStats1.setBounds(150, 112, 303, 114);
		contentPane.add(lblStats1);
		g.addObserver(lblStats1);
		
		VueCarteBossPreview lblStats2 = new VueCarteBossPreview("",g,2,2);
		lblStats2.setBounds(152, 323, 303, 114);
		contentPane.add(lblStats2);
		g.addObserver(lblStats2);
		
		VueCarteBossPreview lblStats3 = new VueCarteBossPreview("",g,2,3);
		lblStats3.setBounds(152, 535, 303, 114);
		contentPane.add(lblStats3);
		g.addObserver(lblStats3);
		
		VueBossPreview lblBossName = new VueBossPreview("",g,2,contentPane);
		lblBossName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBossName.setBounds(731, 483, 319, 23);
		contentPane.add(lblBossName);
		g.addObserver(lblBossName);
		
		VueBossPreview lblBossStats = new VueBossPreview("",g,3,contentPane);
		lblBossStats.setVerticalAlignment(SwingConstants.TOP);
		lblBossStats.setBounds(731, 519, 320, 100);
		contentPane.add(lblBossStats);
		g.addObserver(lblBossStats);
		
		JButton btnContinuer = new JButton("Continuer");
		btnContinuer.setBounds(901, 635, 149, 25);
		contentPane.add(btnContinuer);
		btnContinuer.addActionListener(cf);
		
		ButtonBossHit btnBossAttack = new ButtonBossHit("Attaquer");
		btnBossAttack.setBounds(717, 636, 159, 23);
		contentPane.add(btnBossAttack);
		btnBossAttack.addActionListener(cf);
		
		VueBossPreview lblEnergyCost = new VueBossPreview("Co\u00FBt : -",g,4,contentPane);
		lblEnergyCost.setBounds(471, 635, 234, 25);
		contentPane.add(lblEnergyCost);
		g.addObserver(lblEnergyCost);
	}
}
