package Vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	public int current;

	/**
	 * Create the frame.
	 */
	public TutorialFrame() {
		ArrayList<String> tutorials = new ArrayList<String>();
		this.current = 0;
		/*for(int i=0;i<tutorials.length;i++) {
			this.tutorials[i] = "Sounds/tuto_"+(i+1)+".wav";
		}*/
		tutorials.add("<html>Bienvenue sur cet idle game incroyable ! Ton aventure est sur le point de commencer, mais tu dois te demander : Que m'attend-t-il ?<br>En fait, le concept de ce jeu est simple, ton but est de retrouver tous les personnages de diverses dimensions réelles ou non et de les améliorer à une puissance infinie ! Je vais maintenant t'expliquer l'utilité de chaque menu.</html>");
		tutorials.add("<html>L'onglet joueur regroupe certaines des statistiques de ton héros, notemment son niveau, l'exp, les serviteurs que tu as déjà rencontrés (c kom un pokedex lol jej), l'énergie, mais aussi les poussières élementaires, qui servent à la transcendance (j'expliquerais ça plus tard), et pour finir le serviteur champion, ou favori, qui sert un peu de 'photo de profil', et qui à 10% de bonus aux dégats, soins et à la résistance au combat !</html>");
		tutorials.add("<html>L'onglet roulette, c'est un peu le centre du jeu, c'est un peu comme le casino dans pokemon, tu joues, et tu gagnes des trucs, il y a plusieures récompenses possibles : <br>- Rien, quand t'as que des signes différents (enfin si tu gagnes une petite quantité d'or et d'exp)<br>- Plus d'or (avec 3 signes Gx et le même nombre)<br>- Triple exp (avoir 3 Expx3, mais c'est un peu le lot de la tristesse ahah)<br>- Un portail d'invocation de serviteur commun (avoir 3 signes Tck !)<br>- Un portail d'invocation de serviteur rare (avoir 3 signes R Tck ! -> Zoooooooooooohhhhh)<br>- Un coffre de butin de brutasse, contenant divers objets (avoir 3 signes Obj)<br><br>La roulette consomme 1 d'énergie supplémentaire par 2 niveaux, mais donne des récompenses supplémentaires !</html>");
		tutorials.add("<html>L'onglet serviteur gère toutes formes de vie que tu as pu subjuger, venant de divers endroits (réel, internet, films, etc...), en plus d'avoir chacun une description très complète, il est possible d'effectuer diverses actions avec : <br>- Leur donner de l'exp en utilisant de l'or (la richesse fait la puissance gros), sachant que le niveau max augmente avec la rareté (et le prix de l'exp aussi)<br>- Evoluer le serviteur, cela va augmenter sa rareté, le faire passer lvl 1, et des fois, lui donner une forme plus BADASS !<br>- Augmenter sa rareté maximale, en utilisant un grimoire adéquat, cela permet de l'évoluer un cran au-dessus sans remettre son niveau à 1.<br>- Mettre le serviteur en favori (c'est le champion de l'onglet Joueur, donc met ton serviteur le plus puissant !)<br>- Transcender le serviteur, ce qui augmente ses stats de 20% (inutile actuellement) mais le remet lvl 1 et en rareté minimale, il faut des poussières élèmentaires du même type que le serviteur.<br>A noter que la poussière élementaire se gagne en détruisant des serviteur de rareté HR+ ou supérieur et de niveau max, plus la rareté est importante, plus le nombre de poussières aussi !</html>");
		tutorials.add("<html>Il y a aussi d'autres fonctions plus utilitaires, comme la possibilité d'effectuer un tri par rareté, ou encore le désenchantement de masse, ou toutes les créatures identiques à celle séléctionnée sont détruites et donnent de l'exp à celle séléctionnée.<br>Le bouton séléction de combat permet de choisir un serviteur pour un emplacement précis (voir tuto de combat pour plus de détails.</html>");
		tutorials.add("<html>Ah ! Le dess.. euh l'inventaire ! Il contient toutes sortes d'objets que tu peux utiliser (oui), tu peux y trouver plusieurs types d'objets : <br>- Les portails d'invocations : Ils invoquent 5 serviteurs mystères et tu ne peux en choisir qu'UN ! Selon ce que tu obtient, une voix suave et sensuelle fera un commentaire (mdr)<br>- Les grimoires antiques permettent d'améliorer la rareté maximale d'un serviteur d'un cran, cependant, ce dernier doit avoir sa rareté et niveau maximum ! On fait pas d'oeufs sans casser les ortilles sur mémé !<br>- Les coffres à butin, qui contiennent pleins de récompenses juteuses (si t'as de la chatte bien sur haha noraj)<br>- Les breuvages de l'âme, qui sont des consommables permettant de régénerer de l'énergie (Gul'dan approuve !) pour réutiliser encore la roulette du destin de RNGesus<br>A noter qu'il est vivement déconseillé d'appuyer sur le bouton 'Utiliser' si vous n'avez aucun objet séléctionné xD<br>Et n'utilisez jamais la croix rouge pour fermer les fenêtres, des boutons sont prévus pour cela (surtout que le programme se ferme sans sauvegarder dans le cas des petites popup</html>");
		tutorials.add("<html>Pas grand chose à dire sur l'onglet magasin, tu peux acheter globalement tous les objets qui ont été présenté (sauf les coffres à butin), ainsi que des coffrets à gemmes, chaque objet ayant différents degrés de puissance, avec un prix bien sur plus puissant.</html>");
		tutorials.add("<html>L'onglet runes permet de gérer les différentes runes que tu as : une rune est un objet équipable donnant divers bonus aux statistiques d'un serviteur. Tu peux les équiper sur un serviteur en le séléctionnant puis en lui attribuant la rune.</html>");
		tutorials.add("<html>Pour plus d'infos sur l'onglet evenement, consulte le tutorial de saison.</html>");
		tutorials.add("<html>L'onglet haut-faits regroupe les différents exploits que tu as pu réalisé durant ton aventure, il s'agit généralement de haut-faits à objectifs tel que obtenir X Or, ou atteindre le niveau X, etc...</html>");
		tutorials.add("<html>L'onglet option te permet de sauvegarder ta progression, charger une sauvegarde, ou encore regarder le tuto (oh wait :D), tu peux tout simplement quitter le jeu dans un son déchirant de tristesse et d'adieu :'(</html>");
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
		lblTuto.setText(tutorials.get(0));
		lblTuto.setBounds(12, 13, 848, 482);
		panel.add(lblTuto);
		
		btnContinue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
	           if((current+1) <  tutorials.size()) {
	        	   //Sound.playSound(tutorials[current]);
	        	   current++;
	        	   lblTuto.setText(tutorials.get(current));
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
