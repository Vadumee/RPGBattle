package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Boutons.ButtonBuyItem;
import Boutons.ButtonCardDestroy;
import Boutons.ButtonCardExp;
import Boutons.ButtonCardFavorite;
import Boutons.ButtonCardGrimoire;
import Boutons.ButtonCardRarity;
import Boutons.ButtonCardTranscend;
import Boutons.ButtonChooseFighter;
import Boutons.ButtonEquipRune;
import Boutons.ButtonFightBoss;
import Boutons.ButtonItemUse;
import Boutons.ButtonLoad;
import Boutons.ButtonLockFigther;
import Boutons.ButtonMassDestroy;
import Boutons.ButtonQuit;
import Boutons.ButtonResetBoss;
import Boutons.ButtonResetFighter;
import Boutons.ButtonRoulette;
import Boutons.ButtonSave;
import Boutons.ButtonSellItem;
import Boutons.ButtonSellRune;
import Boutons.ButtonTriRarity;
import Boutons.ButtonTutorial;
import Boutons.ButtonUnequipRune;
import Game.Game;
import Vues.BossFrame;
import Vues.TutorialFrame;

public class Controler implements ActionListener{

	public Game game;

	public Controler(Game g) {
		this.game = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof ButtonRoulette) {
			if(game.joueur.energy >= (2+(game.joueur.level/2))) {
				try {
					game.rollRoulette();
					//game.updateVisuals();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() instanceof ButtonTutorial) {
			TutorialFrame tf = new TutorialFrame();
		}
		else if(e.getSource() instanceof ButtonCardFavorite) {
			game.current_favorite_id = game.current_card_id;
			game.updateVisuals();
		}
		else if(e.getSource() instanceof ButtonCardDestroy) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.current_card_id != -1) {
				int option = jop1.showConfirmDialog(null, "Etes-vous sûr de vouloir anéantir ce serviteur ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
				if(option == JOptionPane.OK_OPTION){
					//game.joueur.gold += ((300*game.joueur.collection.get(game.current_card_id).rarity_id)*mult);
					game.sacrificeMob();
					game.joueur.collection.remove(game.current_card_id);
					for(int i=0;i<game.indice_fighters.length;i++) {
						if(game.indice_fighters[i] == game.current_card_id) {
							game.indice_fighters[i] = -1;
						}
						else if(game.indice_fighters[i] > game.current_card_id) {
							game.indice_fighters[i] -= 1;
						}
					}
					if(game.current_card_id < game.current_favorite_id) {
						game.current_favorite_id -= 1;
					}
					else if(game.current_card_id == game.current_favorite_id) {
						game.current_favorite_id = -1;
					}
					game.current_card_id = -1;
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonMassDestroy) {
			if(game.current_card_id != -1) {
				JOptionPane jop1;
				jop1 = new JOptionPane();
				int option = jop1.showConfirmDialog(null, "Etes-vous sûr de vouloir anéantir tous les doubles de ce serviteur ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
				if(option == JOptionPane.OK_OPTION){
					int i = 0;
					while(i<game.joueur.collection.size()) {
						if((i != game.current_card_id) && (game.joueur.collection.get(game.current_card_id).id == game.joueur.collection.get(i).id)) {
							game.dezDeMasse(i);
							game.joueur.collection.remove(i);
							for(int j=0;j<game.indice_fighters.length;j++) {
								if(game.indice_fighters[j] == i) {
									game.indice_fighters[j] = -1;
								}
								else if(game.indice_fighters[j] > i) {
									game.indice_fighters[j] -= 1;
								}
							}
							if(i < game.current_favorite_id) {
								game.current_favorite_id -= 1;
							}
							else if(i == game.current_favorite_id) {
								game.current_favorite_id = -1;
							}
						}
						else {
							i++;
						}
					}
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonCardExp) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.current_card_id != -1) {
				int montant = Integer.parseInt(((ButtonCardExp)e.getSource()).field.getText());
				int mult = game.joueur.collection.get(game.current_card_id).rarity_id*3;
				if(game.joueur.gold >= (montant * mult * 1L)) {
					int option = jop1.showConfirmDialog(null, "Donner "+montant+" exp à ce serviteur coûte "+(montant*mult*1L)+" pièces d'or, voulez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
					if(option == JOptionPane.OK_OPTION){
						game.joueur.gold -= (montant * mult * 1L);
						game.joueur.collection.get(game.current_card_id).giveExp(montant);
					}
				}
				else {
					jop1.showMessageDialog(null, "Vous n'avez pas les moyens de renforcer autant ce serviteur !", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonCardRarity) {
			if(game.current_card_id != -1) {
				if(game.joueur.collection.get(game.current_card_id).level == game.joueur.collection.get(game.current_card_id).max_level) {
					long cost = game.joueur.collection.get(game.current_card_id).getUpgradeCost();
					if(game.joueur.collection.get(game.current_card_id).rarity_id == game.joueur.collection.get(game.current_card_id).max_rarity_id) {
						JOptionPane jop1;
						jop1 = new JOptionPane();
						jop1.showMessageDialog(null, "Cette carte est déjà à sa rareté maximale", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(game.joueur.gold >= cost) {
						JOptionPane jop = new JOptionPane();			
						int option = jop.showConfirmDialog(null, "L'amélioration de rareté coûte "+cost+" Or, voulez-vous l'effectuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
						if(option == JOptionPane.OK_OPTION){
							game.joueur.gold -= cost;
							game.joueur.collection.get(game.current_card_id).increaseRarity();
							//on gere le gain d'exp a l'augmentation de rarete
							if(game.joueur.collection.get(game.current_card_id).rarity_id >= 8) {
								game.joueur.giveExp(1000+(game.joueur.level*70L));
							}
							else if(game.joueur.collection.get(game.current_card_id).rarity_id >= 6) {
								game.joueur.giveExp(500+(game.joueur.level*20L));
							}
							else if(game.joueur.collection.get(game.current_card_id).rarity_id >= 3) {
								game.joueur.giveExp(200+(game.joueur.level*8L));
							}
							else if(game.joueur.collection.get(game.current_card_id).rarity_id >= 2) {
								game.joueur.giveExp(25);
							}
							//
						}
					}
					else {
						JOptionPane jop1;
						jop1 = new JOptionPane();
						jop1.showMessageDialog(null, "L'amélioration de rareté coûte "+cost+" Or, vous n'avez pas ce montant.", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane jop1;
					jop1 = new JOptionPane();
					jop1.showMessageDialog(null, "Votre serviteur doit être au niveau maximum pour avoir sa rareté augmenté, courage !", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);

				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonCardGrimoire) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.current_card_id != -1) {
				if((game.joueur.collection.get(game.current_card_id).rarity_id == game.joueur.collection.get(game.current_card_id).max_rarity_id) && (game.joueur.collection.get(game.current_card_id).level == game.joueur.collection.get(game.current_card_id).max_level)) {
					int rar = game.joueur.collection.get(game.current_card_id).rarity_id;
					Grimoire grim = game.getGrimoire(rar,game.joueur.collection.get(game.current_card_id).id);
					if(grim != null) {
						int option = jop1.showConfirmDialog(null, "L'amélioration consumera le grimoire, voulez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
						if(option == JOptionPane.OK_OPTION){
							game.joueur.collection.get(game.current_card_id).useGrimoire();
							grim.quantity -= 1;
							game.eraseUsedItems();
							game.current_item_selected = -1;
						}
					}
					else {
						jop1.showMessageDialog(null, "Vous devez posséder un grimoire adequat pour améliorer ce serviteur.", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					jop1.showMessageDialog(null, "Votre serviteur n'a pas le niveau pour avoir sa rareté améliorée.", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonCardTranscend) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.current_card_id != -1) {
				if((game.joueur.collection.get(game.current_card_id).rarity_id == 9) && (game.joueur.collection.get(game.current_card_id).level == game.joueur.collection.get(game.current_card_id).max_level)) {
					int sacrifiable = game.searchDust(game.joueur.collection.get(game.current_card_id).type_id);
					int cost = 1000 + (1000*(game.joueur.collection.get(game.current_card_id).incremental(game.joueur.collection.get(game.current_card_id).prestige)));
					if(sacrifiable >= cost) {
						int option = jop1.showConfirmDialog(null, "La créature va revenir au niveau de rareté minimale, voulez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
						if(option == JOptionPane.OK_OPTION){
							game.joueur.collection.get(game.current_card_id).prestige();
							game.deleteDust(game.joueur.collection.get(game.current_card_id).type_id, cost);
						}
					}
					else {
						int type = game.joueur.collection.get(game.current_card_id).type_id;
						String desc = "";
						if(type == 1) {
							desc = "des flammes";
						}
						else if(type == 2) {
							desc = "verdoyantes";
						}
						else if(type == 3) {
							desc = "de foudre";
						}
						else {
							desc = "abyssales";
						}
						jop1.showMessageDialog(null, "Il vous faut "+cost+" poussières "+desc+", vous n'avez pas ce montant.", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					jop1.showMessageDialog(null, "Votre serviteur n'a pas le niveau pour pouvoir transcender.", "Echec de l'amélioration", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonItemUse) {
			if(game.current_item_selected != -1) {
				game.inventaire.get(game.current_item_selected).utiliser();
			}
			if((game.inventaire.size() == 0)) {
				game.current_item_selected = -1;
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonBuyItem) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.current_item_magasin != -1) {
				int montant = (int) ((double)(((ButtonBuyItem)e.getSource()).spinner.getValue())+0);
				long cout = 1L*montant*game.magasin.get(game.current_item_magasin).cost;
				if(cout <= game.joueur.gold) {
					int option = jop1.showConfirmDialog(null, "L'achat coûte "+cout+" pièces d'or, souhaitez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
					if(option == JOptionPane.OK_OPTION){
						new Thread(new Runnable() {
							public void run() {
								Sound.playSound("Sounds/or.wav");
							}
						}).start();
						game.joueur.gold -= cout;
						for(int i=0;i<montant;i++) {
							game.ajouterObjet(game.magasin.get(game.current_item_magasin));
						}
					}
				}
				else {
					jop1.showMessageDialog(null, "Vous n'avez pas assez d'argent pour effectuer cet achat, boloss !", "Tentative d'arnaque de fdp détéctée", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonQuit) {
			//On détecte s'il y a l'autoload ou non
			try {
				File file = new File("autoload.txt"); 
				BufferedReader reader = new BufferedReader(new FileReader(file)); 
				String isActivated = reader.readLine();
				if(isActivated.equals("Oui")) {
					String path = reader.readLine();
					game.time_last_save = System.currentTimeMillis()/1000;
					FileOutputStream fout = new FileOutputStream(path);
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(game);
				}
				reader.close();
			} catch (IOException e1) {
				System.out.println("bordel");
				e1.printStackTrace();
			} 
			//
			game.quit = true;
		}
		else if(e.getSource() instanceof ButtonSave) {
			String file = "";
			JFileChooser choix = new JFileChooser();
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("sauvegarde de jeu", "sav");
			choix.addChoosableFileFilter(filtre);
			int retour=choix.showSaveDialog(null);
			if(retour==JFileChooser.APPROVE_OPTION){
				// un fichier a été choisi (sortie par OK)
				// nom du fichier  choisi 
				choix.getSelectedFile().getName();
				// chemin absolu du fichier choisi
				file = choix.getSelectedFile().getAbsolutePath();
				try {
					game.time_last_save = System.currentTimeMillis()/1000;
					FileOutputStream fout = new FileOutputStream(file+".sav");
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(game);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {

			}
		}
		else if(e.getSource() instanceof ButtonLoad) {
			String file = "";
			JFileChooser choix = new JFileChooser();
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("sauvegarde de jeu", "sav");
			choix.addChoosableFileFilter(filtre);
			int retour=choix.showOpenDialog(null);
			if(retour==JFileChooser.APPROVE_OPTION){
				// un fichier a été choisi (sortie par OK)
				// nom du fichier  choisi 
				choix.getSelectedFile().getName();
				// chemin absolu du fichier choisi
				file = choix.getSelectedFile().getAbsolutePath();

				try {
					FileInputStream fin = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fin);
					game.loadData((Game) ois.readObject()); 
					System.out.println(game.joueur.collection.get(0).level);
				} catch (IOException e1) {
					System.out.println("ioexception");
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					System.out.println("Classnotfoundexception");
					e1.printStackTrace();
				}
			}else {

			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonTriRarity) {
			game.triParRarete();
			game.current_card_id = -1;
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonSellItem) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.current_item_selected != -1) {
				int nbsell = (int) ((double)(((ButtonSellItem)e.getSource()).spinner.getValue())+0);
				long cost_sell = (1L * nbsell * game.inventaire.get(game.current_item_selected).cost) / 2L;
				if(nbsell <= game.inventaire.get(game.current_item_selected).quantity) {
					int option = jop1.showConfirmDialog(null, "La revente vous redonnera "+cost_sell+" or, mais détruira les objets pour toujours (et dans d'horribles souffrances :( ), voulez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
					if(option == JOptionPane.OK_OPTION){
						game.joueur.gold += cost_sell;
						game.inventaire.get(game.current_item_selected).quantity -= nbsell;
						if(game.inventaire.get(game.current_item_selected).quantity == 0) {
							game.current_item_selected = -1;
							game.eraseUsedItems();
						}
					}
				}
				else {
					jop1.showMessageDialog(null, "Oh la ! Vous ne pouvez pas vendre plus d'objets que vous en possedez, non mais !", "Tentative d'arnaque de jambobinateur détéctée", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonUnequipRune) {
			if(game.current_card_runed != -1) {
				int ind = ((ButtonUnequipRune)e.getSource()).id;
				if(((game.current_card_runed != game.indice_fighters[0]) && (game.current_card_runed != game.indice_fighters[1]) && (game.current_card_runed != game.indice_fighters[2])) || (game.boss == null)) {
					if(game.joueur.collection.get(game.current_card_runed).runes[ind-1] != null) {
						game.inventaire_runes.add(game.joueur.collection.get(game.current_card_runed).runes[ind-1]);
						game.joueur.collection.get(game.current_card_runed).runes[ind-1] = null;
						game.joueur.collection.get(game.current_card_runed).calculateStatFight();
					}
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonSellRune) {
			if(game.current_rune_selected != -1) {
				long refund = game.inventaire_runes.get(game.current_rune_selected).cost / 2L;
				game.joueur.gold += refund;
				game.inventaire_runes.remove(game.current_rune_selected);
				game.current_rune_selected = -1;
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonEquipRune) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			int ind = ((ButtonEquipRune)e.getSource()).id;
			if(game.current_rune_selected != -1 && game.current_card_runed != -1) {
				boolean canChange = game.canEquipRune(game.inventaire_runes.get(game.current_rune_selected), ind);
				if(canChange == true) {
					if(((game.current_card_runed != game.indice_fighters[0]) && (game.current_card_runed != game.indice_fighters[1]) && (game.current_card_runed != game.indice_fighters[2])) || (game.boss == null)) {
						if(game.joueur.collection.get(game.current_card_runed).runes[ind-1] == null) {
							game.joueur.collection.get(game.current_card_runed).runes[ind-1] = game.inventaire_runes.get(game.current_rune_selected);
							game.inventaire_runes.remove(game.current_rune_selected);
						}
						else {
							Rune rer = game.joueur.collection.get(game.current_card_runed).runes[ind-1];
							game.joueur.collection.get(game.current_card_runed).runes[ind-1] = game.inventaire_runes.get(game.current_rune_selected);
							game.inventaire_runes.set(game.current_rune_selected,rer);
						}
						game.joueur.collection.get(game.current_card_runed).calculateStatFight();
						game.current_rune_selected = -1;
					}
				}
				else {
					String rar = "";
					if(game.inventaire_runes.get(game.current_rune_selected).rarete == 2) {
						rar = "Super Rare";
					}
					else if(game.inventaire_runes.get(game.current_rune_selected).rarete == 3) {
						rar = "Hyper Rare+";
					}
					else if(game.inventaire_runes.get(game.current_rune_selected).rarete == 4) {
						rar = "Super Légendaire";
					}
					else if(game.inventaire_runes.get(game.current_rune_selected).rarete == 5) {
						rar = "Super Mythique";
					}
					jop1.showMessageDialog(null, "Seul un serviteur de rareté "+rar+" ou supérieur est capable de porter un artefact d'une telle puissance !", "Message triste généré", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			game.eraseCard();
		}
		else if(e.getSource() instanceof ButtonFightBoss) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			if(game.boss == null) {
				int ind = (int) ((double)(((ButtonFightBoss)e.getSource()).js.getValue())+0);
				//il faut vérifier que le lvl max possible correspond
				if(ind <= game.max_battle_level) {
					game.boss = new Mob(ind);
				}
				else {
					jop1.showMessageDialog(null, "Vous ne pouvez pas générer un boss si puissant, il vous faut affronter un boss de chaque level inférieur.", "Message triste généré", JOptionPane.INFORMATION_MESSAGE);
				}
				//
			}
			else {
				//on ouvre la fenetre de combat ma gueule
				try {
					if(!((game.indice_fighters[0] == -1) && (game.indice_fighters[1] == -1) && (game.indice_fighters[2] == -1))) {
						BossFrame bf = new BossFrame(game);
					}
					else {
						jop1.showMessageDialog(null, "Il faut au moins un serviteur pour pouvoir affronter un boss.", "Message triste généré", JOptionPane.INFORMATION_MESSAGE);
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			game.updateVisuals();
		}
		else if(e.getSource() instanceof ButtonResetBoss) {
			if(game.boss != null) {
				game.boss = null;
			}
			game.updateVisuals();
		}
		else if(e.getSource() instanceof ButtonResetFighter) {
			if(game.boss == null) {
				int id = ((ButtonResetFighter)e.getSource()).id;
				game.indice_fighters[id] = -1;
				game.updateVisuals();
			}
		}
		else if(e.getSource() instanceof ButtonChooseFighter) {
			if(game.indice_battle != -1 && game.boss == null) {
				boolean remplacable = true;
				for(int i=0;i<game.indice_fighters.length;i++) {
					if(i != (game.indice_battle)) {
						if(game.current_card_id == game.indice_fighters[i]) {
							remplacable = false;
						}
					}
				}
				if(remplacable == true) {
					game.indice_fighters[game.indice_battle] = game.current_card_id;
				}
			}
			game.updateVisuals();
		}
		else if(e.getSource() instanceof ButtonLockFigther) {
			int id = ((ButtonLockFigther)e.getSource()).id;
			game.indice_battle = id;
			game.updateVisuals();
		}
	}

}
