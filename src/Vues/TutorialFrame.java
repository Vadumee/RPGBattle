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
		ArrayList<String> images = new ArrayList<String>();
		this.current = 0;
		/*for(int i=0;i<tutorials.length;i++) {
			this.tutorials[i] = "Sounds/tuto_"+(i+1)+".wav";
		}*/
		tutorials.add("<html>Bienvenue sur cet idle game incroyable ! Ton aventure est sur le point de commencer, mais tu dois te demander : Que m'attend-t-il ?<br>En fait, le concept de ce jeu est simple, ton but est de retrouver tous les personnages de diverses dimensions r�elles ou non et de les am�liorer � une puissance infinie ! Je vais maintenant t'expliquer l'utilit� de chaque menu.</html>");
		tutorials.add("<html>L'onglet joueur regroupe certaines des statistiques de ton h�ros, notemment son niveau, l'exp, les serviteurs que tu as d�j� rencontr�s (c kom un pokedex lol jej), l'�nergie, mais aussi les poussi�res �lementaires, qui servent � la transcendance (j'expliquerais �a plus tard), et pour finir le serviteur champion, ou favori, qui sert un peu de 'photo de profil', et qui � 10% de bonus aux d�gats, soins et � la r�sistance au combat !</html>");
		tutorials.add("<html>L'onglet roulette, c'est un peu le centre du jeu, c'est un peu comme le casino dans pokemon, tu joues, et tu gagnes des trucs, il y a plusieures r�compenses possibles : <br>- Rien, quand t'as que des signes diff�rents (enfin si tu gagnes une petite quantit� d'or et d'exp)<br>- Plus d'or (avec 3 signes Gx et le m�me nombre)<br>- Triple exp (avoir 3 Expx3, mais c'est un peu le lot de la tristesse ahah)<br>- Un portail d'invocation de serviteur commun (avoir 3 signes Port)<br>- Un portail d'invocation de serviteur rare (avoir 3 signes R Port ! -> Zoooooooooooohhhhh)<br>- Un coffre de butin de brutasse, contenant divers objets (avoir 3 signes Obj)<br><br>La roulette consomme 1 d'�nergie suppl�mentaire par 2 niveaux, mais donne des r�compenses suppl�mentaires !</html>");
		tutorials.add("<html>L'onglet serviteur g�re toutes formes de vie que tu as pu subjuger, venant de divers endroits (r�el, internet, films, etc...), en plus d'avoir chacun une description tr�s compl�te, il est possible d'effectuer diverses actions avec : <br>- Leur donner de l'exp en utilisant de l'or (la richesse fait la puissance gros), sachant que le niveau max augmente avec la raret� (et le prix de l'exp aussi)<br>- Evoluer le serviteur, cela va augmenter sa raret�, le faire passer lvl 1, et des fois, lui donner une forme plus BADASS !<br>- Augmenter sa raret� maximale, en utilisant un grimoire ad�quat, cela permet de l'�voluer un cran au-dessus sans remettre son niveau � 1.<br>- Mettre le serviteur en favori (c'est le champion de l'onglet Joueur, donc met ton serviteur le plus puissant !)<br>- Transcender le serviteur, ce qui augmente ses stats de 20% (utile pour progresser en lategame !) mais le remet lvl 1 et en raret� minimale, il faut des poussi�res �l�mentaires du m�me type que le serviteur.<br>A noter que la poussi�re se gagne en d�truisant des serviteur de raret� HR+ ou sup�rieur, plus la raret� est importante, plus le nombre de poussi�res aussi !</html>");
		tutorials.add("<html>Il y a aussi d'autres fonctions plus utilitaires, comme la possibilit� d'effectuer un tri par raret�, ou encore le d�senchantement de masse, ou toutes les cr�atures identiques � celle s�l�ctionn�e sont d�truites et donnent de l'exp � celle s�l�ctionn�e.<br>Le bouton s�l�ction de combat permet de choisir un serviteur pour un emplacement pr�cis (voir tuto de combat pour plus de d�tails).</html>");
		tutorials.add("<html>Ah ! Le dess.. euh l'inventaire ! Il contient toutes sortes d'objets que tu peux utiliser (oui), tu peux y trouver plusieurs types d'objets : <br>- Les portails d'invocations : Ils invoquent 5 serviteurs myst�res et tu ne peux en choisir qu'UN ! Selon ce que tu obtient, une voix suave et sensuelle fera un commentaire (mdr)<br>- Les grimoires antiques permettent d'am�liorer la raret� maximale d'un serviteur d'un cran, cependant, ce dernier doit avoir sa raret� et niveau maximum ! On fait pas d'oeufs sans casser les ortilles sur m�m� !<br>- Les coffres � butin, qui contiennent pleins de r�compenses juteuses (si t'as de la chatte bien sur haha noraj)<br>- Les breuvages de l'�me, qui sont des consommables permettant de r�g�nerer de l'�nergie (Gul'dan approuve !) pour r�utiliser encore la roulette de la RNG<br>N'utilisez jamais la croix rouge pour fermer les fen�tres, des boutons sont pr�vus pour cela (surtout que le programme se ferme sans sauvegarder dans le cas des petites popup).</html>");
		tutorials.add("<html>Pas grand chose � dire sur l'onglet magasin, tu peux acheter globalement tous les objets qui ont �t� pr�sent� (sauf les coffres � butin), ainsi que des coffrets � gemmes, chaque objet ayant diff�rents degr�s de puissance, avec un prix bien sur plus puissant).</html>");
		tutorials.add("<html>L'onglet runes permet de g�rer les diff�rentes runes que tu as : une rune est un objet �quipable donnant divers bonus aux statistiques d'un serviteur. Tu peux les �quiper sur un serviteur en le s�l�ctionnant puis en lui attribuant la rune.</html>");
		tutorials.add("<html>Pour plus d'infos sur l'onglet evenement, consulte le tutorial de saison.</html>");
		tutorials.add("<html>L'onglet haut-faits regroupe les diff�rents exploits que tu as pu r�alis� durant ton aventure, il s'agit g�n�ralement de haut-faits � objectifs tel que obtenir X Or, ou atteindre le niveau X, etc...</html>");
		tutorials.add("<html>L'onglet option te permet de sauvegarder ta progression, charger une sauvegarde, ou encore regarder le tuto (oh wait :D), tu peux tout simplement quitter le jeu dans un son d�chirant de tristesse et d'adieu :'(<br>Il est aussi possible de cocher 'Selection de Carte auto' pour que la premi�re carte soit automatiquement choisie lors de l'ouverture d'un paquet, sans que la fen�tre de choix ne s'ouvre.</html>");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i=1;i<12;i++) {
			images.add("Images/tuto/tuto_"+i+".jpg");
		}
		

		this.setVisible(true);
		this.setResizable(false);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelTuto panelImage = new PanelTuto(0,images);
		panelImage.setBounds(205, 13, 660, 440);
		contentPane.add(panelImage);
		
		JLabel lblTuto = new JLabel(tutorials.get(0));
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
			       ((TutorialFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
		    	}
		    	else {
		    		lblTuto.setText(tutorials.get(compt));
		    		panelImage.repaint();
		    	}
		    }
		});
		
	}
}
