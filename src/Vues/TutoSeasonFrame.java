package Vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.WavPlayer;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

public class TutoSeasonFrame extends JFrame {

	private JPanel contentPane;
	public int current;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutoSeasonFrame frame = new TutoSeasonFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public TutoSeasonFrame() {
		//on créer les tableaux de texte et d'images
		ArrayList<String> descriptions = new ArrayList<String>();
		ArrayList<String> images = new ArrayList<String>();
		this.current = 0;
		//on ajoute le contenu
		descriptions.add("<html>Avec la version 1.0 du jeu, un système de combat à été implanté, enraciné autour du système de saison, les combats sont au coeur du gameplay. Vous pourrez y affronter des boss qui changeront au fur et à mesure du temps, et remporter des récompenses tout aussi brutales que seront vos adversaires !</html>");
		descriptions.add("<html>Voila à quoi ressemble la fenêtre de combat, on peut y trouver de nombreux boutons, les deux principaux sont Combattre et Abandonner :<br>- la première fonction créer un boss s'il n'y en a pas, ou lance la fenêtre de combat (quasiment identique à celle-ci).<br>- La seconde met fin au combat, ne donnant AUCUNE récompenses, mais peut avoir son utilité.</html>");
		descriptions.add("<html>Il y a deux rôles parmi les trois combattants que vous pourrez employer, attaquant et soigneur, comme leur nom l'indique, l'attaquant inflige des dégats au boss tandis que le soigneur essaie de remettre sur pied toute son équipe (lui y comprit).<br>Vous pouvez gérer chacun de vos combattants à l'aide des boutons qui sont à côté d'eux :<br>- Le bouton enlever permet de retirer le serviteur de l'équipe, tandis que verrouiller vous permet de choisir un serviteur à cet emplacement via le menu Collection (bouton 'Selection Combat').<br>A noter que tant qu'un boss est en vie, il est impossible de changer votre équipe, ou bien d'équiper des gemmes sur l'un d'entre eux.</html>");
		descriptions.add("<html>Chaque serviteur possède des statistiques qui lui sont propres : L'attaque (ATK) qui définit les dégats, ou 50% des heal si votre serviteur est soigneur.<br>La défense (DEF) définit la résistance de votre serviteur face aux attaques, elle est calculé en fonction de l'attaque ennemie.<br>La provocation (PROV) définit la chance de votre serviteur de se faire attaquer par l'ennemi.<br>L'agilité définie les chances de coups crititques, et 50% du heal si votre serviteur est soigneur.<br>Pour finir, la vitesse (VIT) définit les chances d'esquiver les attaques ennemies, elle offre aussi un multiplicateur de soins à certains pallier.</html>");
		descriptions.add("<html>Lorsqu'un serviteur meurt, il ne peut plus attaquer, ni être soigné, il regénère ses pv petit à petit au fur et à mesure du temps, jusqu'à être de nouveau en état de se battre tel le voyou qu'il est.</html>");
		descriptions.add("<html>A droite, vous avez les infos concernant le boss : son niveau, sa vie, son attaque, sa défense, son nom... etc. Il est aussi possible de voir son passif en laissant votre curseur sur son nom.<br>Les boss, ont, tout comme vos serviteur, un type, qui est caché, selon l'avantage ou la faiblesse du type, vos serviteurs peuvent infliger moitié moins ou deux fois plus de dégats !<br>ATTENTION ! Les boss bénéficient aussi des types, ils vous infligeront TOUJOURS des dégats normaux dans le pire cas, et le double, s'il a l'avantage.<br>Oubliez donc l'idée de jouer avec une team fixe de roxxor, car si votre team est mal composée, le boss pourrait vous mettre en PLS facilement.</html>");
		descriptions.add("<html>N'hésitez pas à utiliser l'abandon de manière stratégique lorsque vous avez décelé le type du boss, et mettre une équipe plus éfficace pour contrer son type ET son passif.<br>Jouer sur les runes pour booster vos combattants est un atout majeur, car vous pouvez les utiliser pour combler des faiblesses, ou au contraire, maximiser leurs atouts !</html>");
		descriptions.add("<html>Entrons maintenant dans le système de saison ! Les saisons durent un certain temps, elles permettent d'obtenir des récompenses juteuses lors de combats contre les boss, et de débloquer des serviteurs exclusifs et très puissants ! (Vous pouvez consulter votre score de saison dans l'onglet Evenement).<br>Chaque saison possède un thème particulier, et des boss uniques ayant chacun son passif, bien que le système de combat perdure même en hors saison, les récompenses ne sont plus obtensibles.</html>");
		descriptions.add("<html>A chaque saison, il y a 4 serviteurs exclusifs à débloquer, tous légendaires. 3 d'entre eux sont des récompenses de pallier au score de saison, le dernier, surpuissant, nécessite d'effectuer la 'Conquête de Saison'.<br>La conquête de saison nécessite les requis suivants : <br>- Avoir obtenu 200.000 points au cours de la saison (augmentera de 20.000 chaque saison)<br>- Avoir vaincu un boss de niveau 20 minimum (idem que pour les points).<br>- Avoir amélioré au minimum UN des trois autres serviteurs de saison en qualité Super Mythique (peu importe le niveau).</html>");
		descriptions.add("<html>Saison 1 : Raid sur l'académie Honnoji !<br>- Il est temps pour vous de mettre fin à la dictature qui à été imposée sur cette académie utilisant des uniformes surpuissants pour régner en maître. Cependant, dans cette boucle alternative, même la rebelle aux lames ciseaux n'a pas su vaincre l'ennemi implacable, alors c'est à vous que revient le rôle de remettre l'ordre dans ce lieu de dictature ! NUDIIIIIIIIIISTO BEAAAAAAACH !</html>");
		descriptions.add("<html>Note : La fin de ce tuto décrivant la saison et les récompenses associées sera mis à jour dès qu'une nouvelle saison fera son apparition.");
		//
		for(int i=1;i<12;i++) {
			images.add("Images/tuto_season/image_"+i+".jpg");
		}
		
		//il ne faut pas oublier de jouer la musique 
		final WavPlayer wp = new WavPlayer(new File("Sounds/event_intro.wav"));
		new Thread(new Runnable() {
			public void run() {
				wp.open();
				wp.play();
			}
		}).start();
		//
		

		this.setVisible(true);
		this.setResizable(false);
		setTitle("Combat et Saison");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelTuto panelImage = new PanelTuto(0,images);
		panelImage.setBounds(205, 13, 660, 440);
		contentPane.add(panelImage);
		
		JLabel lblTuto = new JLabel(descriptions.get(0));
		lblTuto.setVerticalAlignment(SwingConstants.TOP);
		lblTuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTuto.setBounds(12, 470, 1038, 152);
		contentPane.add(lblTuto);
		
		JButton btnContinue = new JButton("Continuer");
		btnContinue.setBounds(205, 635, 660, 25);
		contentPane.add(btnContinue);
		
		btnContinue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	panelImage.indice++;
		    	final int compt = panelImage.indice;
		    	if(compt == 11) {
		    		wp.stop();
			    	wp.close();
			       ((TutoSeasonFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
		    	}
		    	else {
		    		lblTuto.setText(descriptions.get(compt));
		    		panelImage.repaint();
		    	}
		    }
		});
	}
}
