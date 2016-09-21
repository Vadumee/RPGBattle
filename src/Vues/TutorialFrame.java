package Vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Game.Sound;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class TutorialFrame extends JFrame {

	private JPanel contentPane;
	public String[] tutorials;
	public int current;

	/**
	 * Create the frame.
	 */
	public TutorialFrame() {
		this.tutorials = new String[8];
		this.current = 0;
		/*for(int i=0;i<tutorials.length;i++) {
			this.tutorials[i] = "Sounds/tuto_"+(i+1)+".wav";
		}*/
		this.tutorials[0] = "<html>Bienvenue sur cet idle game incroyable ! Ton aventure est sur le point de commencer, mais tu dois te demander : Que m'attend-t-il ?<br>En fait, le concept de ce jeu est simple, ton but est de retrouver tous les personnages de diverses dimensions réelles ou non et de les améliorer à une puissance infinie ! Je vais maintenant t'expliquer l'utilité de chaque menu.</html>";
		this.tutorials[1] = "<html>L'onglet joueur regroupe certaines des statistiques de ton héros, notemment son niveau, l'exp, les serviteurs que tu as déjà rencontrés (c kom un pokedex lol jej), l'énergie, mais aussi les poussières élementaires, qui servent à la transcendance (j'expliquerais ça plus tard), et pour finir le serviteur champion, ou favori, qui sert un peu de 'photo de profil', mais qui plus tard aura une utilité réelle !</html>";
		this.tutorials[2] = "<html>L'onglet roulette, c'est un peu le centre du jeu, c'est un peu comme le casino dans pokemon, tu joues, et tu gagnes des trucs, il y a plusieures récompenses possibles : <br>- Rien, quand t'as que des signes différents (enfin si tu gagnes une petite quantité d'or et d'exp)<br>- Plus d'or (avec 3 signes Gx et le même nombre)<br>- Triple exp (avoir 3 Expx3, mais c'est un peu le lot de la tristesse ahah)<br>- Un portail d'invocation de serviteur commun (avoir 3 signes Tck !)<br>- Un portail d'invocation de serviteur rare (avoir 3 signes R Tck ! -> Zoooooooooooohhhhh)<br>- Un coffre de butin de brutasse, contenant divers objets (avoir 3 signes Obj)<br><br>La roulette consomme 1 d'énergie supplémentaire par 2 niveaux, mais donne des récompenses supplémentaires !</html>";
		this.tutorials[3] = "<html>L'onglet serviteur gère toutes formes de vie que tu as pu subjuger, venant de divers endroits (réel, internet, films, etc...), en plus d'avoir chacun une description très complète, il est possible d'effectuer diverses actions avec : <br>- Leur donner de l'exp en utilisant de l'or (la richesse fait la puissance gros), sachant que le niveau max augmente avec la rareté (et le prix de l'exp aussi)<br>- Evoluer le serviteur, cela va augmenter sa rareté, le faire passer lvl 1, et des fois, lui donner une forme plus BADASS !<br>- Augmenter sa rareté maximale, en utilisant un grimoire adéquat, cela permet de l'évoluer un cran au-dessus sans remettre son niveau à 1.<br>- Mettre le serviteur en favori (c'est le champion de l'onglet Joueur, donc met ton serviteur badass2edgy4me)<br>- Transcender le serviteur, ce qui augmente ses stats de 20% (inutile actuellement) mais le remet lvl 1 et en rareté minimale, il faut des poussières élèmentaires du même type que le serviteur.<br>A noter que la poussière élementaire se gagne en détruisant des serviteur de rareté HR+ ou supérieur et de niveau max, plus la rareté est importante, plus le nombre de poussières aussi !</html>";
		this.tutorials[4] = "<html>Ah ! Le dess.. euh l'inventaire ! Il contient toutes sortes d'objets que tu peux utiliser (captain obvious !), tu peux y trouver plusieurs types d'objets : <br>- Les portails d'invocations : Ils invoquent 5 serviteurs mystères et tu ne peux en choisir qu'UN ! Selon ce que tu obtient, une voix suave et sensuelle fera un commentaire (mdr)<br>- Les grimoires antiques permettent d'améliorer la rareté maximale d'un serviteur d'un cran, cependant, ce dernier doit avoir sa rareté et niveau maximum ! On fait pas d'oeufs sans casser les ortilles sur mémé !<br>- Les coffres à butin, qui contiennent pleins de récompenses juteuses (si t'as de la chatte bien sur haha noraj)<br>- Les breuvages de l'âme, qui sont des consommables permettant de régénerer de l'énergie (Gul'dan approuve !) pour réutiliser encore la roulette du destin de RNGesus<br>A noter qu'il est vivement déconseillé d'appuyer sur le bouton 'Utiliser' si vous n'avez aucun objet séléctionné xD<br>Et n'utilisez jamais la croix rouge pour fermer les fenêtres, des boutons sont prévus pour cela (surtout que le programme se ferme sans sauvegarder dans le cas des petites popup</html>";
		this.tutorials[5] = "<html>Pas grand chose à dire sur l'onglet magasin, tu peux acheter globalement tous les objets qui ont été présenté (sauf les coffres à butin), chaque objet ayant différents degrés de puissance, avec un prix bien sur plus puissant.</html>";
		this.tutorials[6] = "<html>L'onglet option te permet de sauvegarder ta progression, charger une sauvegarde, ou encore regarder le tuto (oh wait :D), tu peux tout simplement quitter le jeu dans un son déchirant de tristesse et d'adieu :'(</html>";
		this.tutorials[7] = "<html>Voila ! Je pense que pour l'instant j'ai tout expliqué, à noter que ce jeu est une application 'cobaye', car mon but ultime est de faire un jeu similaire sur web, mais avec un vrai système de combat, des duels, des events, etc... mais je dois acquérir la connaissance du dev web :3<br>Il est fort possible que d'autres versions soient développées, j'ai en tête un espèce de système d'event qui rendrait utile la transcendance et les stats.<br>Ce qui est bien, c'est que les versions rajoutant simplement de nouveaux serviteurs ne corrompront pas vos sauvegarde (par contre yaura un gros reset avec la màj des events).</html>";
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 102));
		panel.setBounds(5, 5, 872, 543);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnContinue = new JButton("Continuer");
		btnContinue.setBounds(388, 505, 97, 25);
		panel.add(btnContinue);
		
		JLabel lblTuto = new JLabel("Tuto");
		lblTuto.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblTuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTuto.setText(tutorials[0]);
		lblTuto.setBounds(12, 13, 848, 482);
		panel.add(lblTuto);
		
		btnContinue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
	           if((current+1) <  tutorials.length) {
	        	   //Sound.playSound(tutorials[current]);
	        	   current++;
	        	   lblTuto.setText(tutorials[current]);
	        	   //modifier l'image
	        	   
	           }
	           else {
	        	   ((TutorialFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
	           }
		    }
		});
		
		this.setVisible(true);
		this.setResizable(false);
	}
}
