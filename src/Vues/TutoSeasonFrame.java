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
		//on cr�er les tableaux de texte et d'images
		ArrayList<String> descriptions = new ArrayList<String>();
		ArrayList<String> images = new ArrayList<String>();
		this.current = 0;
		//on ajoute le contenu
		descriptions.add("<html>Avec la version 1.0 du jeu, un syst�me de combat � �t� implant�, enracin� autour du syst�me de saison, les combats sont au coeur du gameplay. Vous pourrez y affronter des boss qui changeront au fur et � mesure du temps, et remporter des r�compenses tout aussi brutales que seront vos adversaires !</html>");
		descriptions.add("<html>Voila � quoi ressemble la fen�tre de combat, on peut y trouver de nombreux boutons, les deux principaux sont Combattre et Abandonner :<br>- la premi�re fonction cr�er un boss s'il n'y en a pas, ou lance la fen�tre de combat (quasiment identique � celle-ci).<br>- La seconde met fin au combat, ne donnant AUCUNE r�compenses, mais peut avoir son utilit�.</html>");
		descriptions.add("<html>Il y a deux r�les parmi les trois combattants que vous pourrez employer, attaquant et soigneur, comme leur nom l'indique, l'attaquant inflige des d�gats au boss tandis que le soigneur essaie de remettre sur pied toute son �quipe (lui y comprit).<br>Vous pouvez g�rer chacun de vos combattants � l'aide des boutons qui sont � c�t� d'eux :<br>- Le bouton enlever permet de retirer le serviteur de l'�quipe, tandis que verrouiller vous permet de choisir un serviteur � cet emplacement via le menu Collection (bouton 'Selection Combat').<br>A noter que tant qu'un boss est en vie, il est impossible de changer votre �quipe, ou bien d'�quiper des gemmes sur l'un d'entre eux.</html>");
		descriptions.add("<html>Chaque serviteur poss�de des statistiques qui lui sont propres : L'attaque (ATK) qui d�finit les d�gats, ou 50% des heal si votre serviteur est soigneur.<br>La d�fense (DEF) d�finit la r�sistance de votre serviteur face aux attaques, elle est calcul� en fonction de l'attaque ennemie.<br>La provocation (PROV) d�finit la chance de votre serviteur de se faire attaquer par l'ennemi.<br>L'agilit� d�finie les chances de coups crititques, et 50% du heal si votre serviteur est soigneur.<br>Pour finir, la vitesse (VIT) d�finit les chances d'esquiver les attaques ennemies, elle offre aussi un multiplicateur de soins � certains pallier.</html>");
		descriptions.add("<html>Lorsqu'un serviteur meurt, il ne peut plus attaquer, ni �tre soign�, il reg�n�re ses pv petit � petit au fur et � mesure du temps, jusqu'� �tre de nouveau en �tat de se battre tel le voyou qu'il est.</html>");
		descriptions.add("<html>A droite, vous avez les infos concernant le boss : son niveau, sa vie, son attaque, sa d�fense, son nom... etc. Il est aussi possible de voir son passif en laissant votre curseur sur son nom.<br>Les boss, ont, tout comme vos serviteur, un type, qui est cach�, selon l'avantage ou la faiblesse du type, vos serviteurs peuvent infliger moiti� moins ou deux fois plus de d�gats !<br>ATTENTION ! Les boss b�n�ficient aussi des types, ils vous infligeront TOUJOURS des d�gats normaux dans le pire cas, et le double, s'il a l'avantage.<br>Oubliez donc l'id�e de jouer avec une team fixe de roxxor, car si votre team est mal compos�e, le boss pourrait vous mettre en PLS facilement.</html>");
		descriptions.add("<html>N'h�sitez pas � utiliser l'abandon de mani�re strat�gique lorsque vous avez d�cel� le type du boss, et mettre une �quipe plus �fficace pour contrer son type ET son passif.<br>Jouer sur les runes pour booster vos combattants est un atout majeur, car vous pouvez les utiliser pour combler des faiblesses, ou au contraire, maximiser leurs atouts !</html>");
		descriptions.add("<html>Entrons maintenant dans le syst�me de saison ! Les saisons durent un certain temps, elles permettent d'obtenir des r�compenses juteuses lors de combats contre les boss, et de d�bloquer des serviteurs exclusifs et tr�s puissants ! (Vous pouvez consulter votre score de saison dans l'onglet Evenement).<br>Chaque saison poss�de un th�me particulier, et des boss uniques ayant chacun son passif, bien que le syst�me de combat perdure m�me en hors saison, les r�compenses ne sont plus obtensibles.</html>");
		descriptions.add("<html>A chaque saison, il y a 4 serviteurs exclusifs � d�bloquer, tous l�gendaires. 3 d'entre eux sont des r�compenses de pallier au score de saison, le dernier, surpuissant, n�cessite d'effectuer la 'Conqu�te de Saison'.<br>La conqu�te de saison n�cessite les requis suivants : <br>- Avoir obtenu 200.000 points au cours de la saison (augmentera de 20.000 chaque saison)<br>- Avoir vaincu un boss de niveau 20 minimum (idem que pour les points).<br>- Avoir am�lior� au minimum UN des trois autres serviteurs de saison en qualit� Super Mythique (peu importe le niveau).</html>");
		descriptions.add("<html>Saison 1 : Raid sur l'acad�mie Honnoji !<br>- Il est temps pour vous de mettre fin � la dictature qui � �t� impos�e sur cette acad�mie utilisant des uniformes surpuissants pour r�gner en ma�tre. Cependant, dans cette boucle alternative, m�me la rebelle aux lames ciseaux n'a pas su vaincre l'ennemi implacable, alors c'est � vous que revient le r�le de remettre l'ordre dans ce lieu de dictature ! NUDIIIIIIIIIISTO BEAAAAAAACH !</html>");
		descriptions.add("<html>Note : La fin de ce tuto d�crivant la saison et les r�compenses associ�es sera mis � jour d�s qu'une nouvelle saison fera son apparition.");
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
