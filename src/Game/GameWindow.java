package Game;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;

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
import Boutons.ButtonFightTutorial;
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
import Vues.CardGemList;
import Vues.CardList;
import Vues.ItemList;
import Vues.MagasinList;
import Vues.PanelBossPreview;
import Vues.PanelCard;
import Vues.PanelFavorite;
import Vues.PanelRoulette;
import Vues.PanelRune;
import Vues.RuneList;
import Vues.VueBossPreview;
import Vues.VueCarteBossPreview;
import Vues.VueCarteRune;
import Vues.VueItem;
import Vues.VueRoulette;
import Vues.VueRune;
import Vues.VueShop;
import Vues.VueStatCard;
import Vues.VueStatPersonnage;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.List;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

public class GameWindow {

	private JFrame frmRpgCardCollector;
	private JTextField txtSelectExp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GameWindow window = new GameWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public GameWindow() throws InterruptedException {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		Game g = new Game();
		Controler control = new Controler(g);
		//test
		g.joueur.gold = 999999999999L;
		g.joueur.giveExp(199999999999L);
		//
		
		//On détecte s'il y a l'autoload ou non
		File file = new File("autoload.txt"); 
		BufferedReader reader = new BufferedReader(new FileReader(file)); 
		String isActivated = reader.readLine();
		if(isActivated.equals("Oui")) {
			String path = reader.readLine();
			try {
				FileInputStream fin = new FileInputStream(new File(path));
				ObjectInputStream ois = new ObjectInputStream(fin);
				g.loadData((Game) ois.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		reader.close();
		//
		
		frmRpgCardCollector = new JFrame();
		frmRpgCardCollector.setTitle("RPG Sbire Battle v.1.0 : The Awakening");
	//frmRpgCardCollector.getContentPane().add(pane);
		
		//-----------PLAYER----------------
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 102));
		tabbedPane.setBackground(new Color(153, 153, 102));
		frmRpgCardCollector.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		PanelFavorite panel_player = new PanelFavorite(g);
		panel_player.setBackground(new Color(0, 153, 204));
		tabbedPane.addTab("Joueur", null, panel_player, null);
		panel_player.setLayout(null);
		
		JLabel lblLevelDesc = new JLabel("Level :");
		lblLevelDesc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLevelDesc.setBounds(12, 13, 88, 31);
		panel_player.add(lblLevelDesc);
		
		VueStatPersonnage lblLevel = new VueStatPersonnage("",g,1);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLevel.setBounds(123, 13, 430, 31);
		panel_player.add(lblLevel);
		g.addObserver(lblLevel);
		
		JLabel lblDesc2 = new JLabel("Cartes Obtenues : ");
		lblDesc2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDesc2.setBounds(12, 57, 163, 31);
		panel_player.add(lblDesc2);
		
		VueStatPersonnage lblCardsTotal = new VueStatPersonnage("",g,2);
		lblCardsTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCardsTotal.setBounds(187, 57, 303, 31);
		panel_player.add(lblCardsTotal);
		g.addObserver(lblCardsTotal);
		
		JLabel lblDesc3 = new JLabel("Or : ");
		lblDesc3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDesc3.setBounds(12, 101, 40, 31);
		panel_player.add(lblDesc3);
		
		JLabel lblDesc4 = new JLabel("Energie : ");
		lblDesc4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDesc4.setBounds(12, 603, 130, 27);
		panel_player.add(lblDesc4);
		
		VueStatPersonnage lblEnergyPlayer = new VueStatPersonnage("",g,4);
		lblEnergyPlayer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEnergyPlayer.setBounds(165, 608, 264, 22);
		panel_player.add(lblEnergyPlayer);
		g.addObserver(lblEnergyPlayer);
		
		VueStatPersonnage lblGoldAmount = new VueStatPersonnage("",g,3);
		lblGoldAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGoldAmount.setBounds(64, 101, 144, 27);
		panel_player.add(lblGoldAmount);
		g.addObserver(lblGoldAmount);
		
		JLabel lblNewLabel = new JLabel("--- CHAMPION ---");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(651, 126, 286, 45);
		panel_player.add(lblNewLabel);
		
		VueStatPersonnage lblFireDust = new VueStatPersonnage("Poussi\u00E8res des flammes : 0",g,6);
		lblFireDust.setBounds(12, 189, 366, 31);
		panel_player.add(lblFireDust);
		g.addObserver(lblFireDust);
		
		VueStatPersonnage lblLeafDust = new VueStatPersonnage("Poussi\u00E8res verdoyantes : 0",g,7);
		lblLeafDust.setBounds(12, 234, 366, 31);
		panel_player.add(lblLeafDust);
		g.addObserver(lblLeafDust);
		
		VueStatPersonnage lblThunderDust = new VueStatPersonnage("Poussi\u00E8res de foudre : 0",g,8);
		lblThunderDust.setBounds(12, 278, 366, 31);
		panel_player.add(lblThunderDust);
		g.addObserver(lblThunderDust);
		
		VueStatPersonnage lblWaterDust = new VueStatPersonnage("Poussi\u00E8res abyssales : 0",g,9);
		lblWaterDust.setBounds(12, 322, 366, 31);
		panel_player.add(lblWaterDust);
		g.addObserver(lblWaterDust);
		
		//----------ROULETTE----------------
		
		JPanel panel_roulette = new JPanel();
		panel_roulette.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Roulette", null, panel_roulette, null);
		
		JLabel lblDescRoulette1 = new JLabel("Energy : ");
		lblDescRoulette1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDescRoulette1.setBounds(12, 13, 111, 25);
		
		VueStatPersonnage lblEnergyRoulette = new VueStatPersonnage("",g,4);
		lblEnergyRoulette.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEnergyRoulette.setBounds(135, 13, 227, 25);
		g.addObserver(lblEnergyRoulette);
		
		
		VueRoulette lblRoulette1 = new VueRoulette("X",g,1);
		lblRoulette1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRoulette1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoulette1.setBounds(135, 184, 160, 147);
		g.addObserver(lblRoulette1);
		
		VueRoulette lblRoulette2 = new VueRoulette("X",g,2);
		lblRoulette2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRoulette2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoulette2.setBounds(419, 184, 160, 147);
		g.addObserver(lblRoulette2);
		
		VueRoulette lblRoulette3 = new VueRoulette("X",g,3);
		lblRoulette3.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRoulette3.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoulette3.setBounds(721, 184, 160, 147);
		g.addObserver(lblRoulette3);
		panel_roulette.setLayout(null);
		panel_roulette.add(lblDescRoulette1);
		panel_roulette.add(lblEnergyRoulette);
		panel_roulette.add(lblRoulette1);
		panel_roulette.add(lblRoulette2);
		panel_roulette.add(lblRoulette3);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(349, 184, 13, 147);
		panel_roulette.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(645, 184, 13, 147);
		panel_roulette.add(verticalStrut_1);
		
		ButtonRoulette btnRoulettePlay = new ButtonRoulette("Jouer !");
		btnRoulettePlay.setBounds(419, 405, 160, 45);
		panel_roulette.add(btnRoulettePlay);
		btnRoulettePlay.addActionListener(control);
		
		VueStatPersonnage lblRouletteCost = new VueStatPersonnage("Coût : ",g,5);
		lblRouletteCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblRouletteCost.setBounds(419, 463, 160, 25);
		panel_roulette.add(lblRouletteCost);
		g.addObserver(lblRouletteCost);
		
		//---------- PANEL COLLECTION --------------
		
		PanelCard panel_collec = new PanelCard(g);
		panel_collec.setBackground(new Color(51, 153, 204));
		tabbedPane.addTab("Collection", null, panel_collec, null);
		panel_collec.setLayout(null);
		
		CardList listCard = new CardList(g,new DefaultListModel(),panel_collec);
		listCard.setBounds(12, 13, 288, 629);
		//panel_collec.add(listCard);
		g.addObserver(listCard);
		
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 13, 288, 629);
				scrollPane.setViewportView(listCard);
				panel_collec.add(scrollPane);
				
				VueStatCard lblCardName = new VueStatCard("Nom de carte",g,1);
				lblCardName.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblCardName.setBounds(694, 13, 362, 50);
				panel_collec.add(lblCardName);
				g.addObserver(lblCardName);
				
				VueStatCard lblCardRarity = new VueStatCard("Raret\u00E9",g,2);
				lblCardRarity.setBounds(694, 68, 362, 31);
				panel_collec.add(lblCardRarity);
				g.addObserver(lblCardRarity);
				
				VueStatCard lblCardLevel = new VueStatCard("Card Level",g,3);
				lblCardLevel.setBounds(694, 101, 362, 31);
				panel_collec.add(lblCardLevel);
				g.addObserver(lblCardLevel);
				
				VueStatCard lblCardType = new VueStatCard("Type",g,4);
				lblCardType.setBounds(694, 138, 362, 31);
				panel_collec.add(lblCardType);
				g.addObserver(lblCardType);
				
				VueStatCard lblStats = new VueStatCard("Stats",g,5);
				lblStats.setBounds(694, 181, 362, 193);
				panel_collec.add(lblStats);
				g.addObserver(lblStats);
				
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setBounds(694, 486, 362, 11);
				panel_collec.add(horizontalStrut);
				
				ButtonCardRarity btnCardEvolve = new ButtonCardRarity("Evolution");
				btnCardEvolve.setBounds(802, 523, 136, 31);
				panel_collec.add(btnCardEvolve);
				btnCardEvolve.addActionListener(control);
				
				ButtonCardTranscend btnCardTranscend = new ButtonCardTranscend("Transcendance");
				btnCardTranscend.setBounds(802, 611, 136, 31);
				panel_collec.add(btnCardTranscend);
				btnCardTranscend.addActionListener(control);
				
				ButtonCardDestroy btnCardDestroy = new ButtonCardDestroy("D\u00E9truire");
				btnCardDestroy.setBounds(333, 611, 115, 31);
				panel_collec.add(btnCardDestroy);
				btnCardDestroy.addActionListener(control);
				
				IntegerField txtSelectExp_1 = new IntegerField();
				txtSelectExp_1.setBounds(333, 529, 145, 31);
				panel_collec.add(txtSelectExp_1);
				txtSelectExp_1.setColumns(10);
				
				VueStatCard lblCardExpCost = new VueStatCard("Co\u00FBt : -",g,6);
				lblCardExpCost.setBounds(333, 561, 253, 24);
				panel_collec.add(lblCardExpCost);
				g.addObserver(lblCardExpCost);
				
				ButtonCardExp btnCardAmliorer = new ButtonCardExp("Am\u00E9liorer",txtSelectExp_1);
				btnCardAmliorer.setBounds(490, 532, 97, 25);
				panel_collec.add(btnCardAmliorer);
				btnCardAmliorer.addActionListener(control);
				
				VueStatCard lblCardExp = new VueStatCard("Exp : -",g,7);
				lblCardExp.setBounds(334, 466, 362, 24);
				panel_collec.add(lblCardExp);
				g.addObserver(lblCardExp);
				
				VueStatCard lblCardComment = new VueStatCard("Flavor Text",g,8);
				lblCardComment.setFont(new Font("Tahoma", Font.ITALIC, 11));
				lblCardComment.setForeground(new Color(204, 0, 0));
				lblCardComment.setBounds(694, 401, 362, 79);
				panel_collec.add(lblCardComment);
				g.addObserver(lblCardComment);
				
				ButtonCardFavorite btnCardFavorite = new ButtonCardFavorite("Favori");
				btnCardFavorite.setBounds(470, 611, 115, 31);
				panel_collec.add(btnCardFavorite);
				btnCardFavorite.addActionListener(control);
				
				ButtonCardGrimoire btnCardRarifier = new ButtonCardGrimoire("Rarifier");
				btnCardRarifier.setBounds(802, 567, 136, 31);
				panel_collec.add(btnCardRarifier);
				btnCardRarifier.addActionListener(control);
				
				JLabel lblGoldC = new JLabel("Or :");
				lblGoldC.setBounds(333, 500, 56, 16);
				panel_collec.add(lblGoldC);
				
				VueStatPersonnage lblGoldCard = new VueStatPersonnage("",g,3);
				lblGoldCard.setBounds(401, 500, 278, 16);
				panel_collec.add(lblGoldCard);
				g.addObserver(lblGoldCard);
				
				ButtonTriRarity btnCardTrier = new ButtonTriRarity("Tri par Raret\u00E9");
				btnCardTrier.setBounds(312, 13, 136, 25);
				panel_collec.add(btnCardTrier);
				btnCardTrier.addActionListener(control);
				
				ButtonMassDestroy btnMassDisenchant = new ButtonMassDestroy("Destruction de masse");
				btnMassDisenchant.setBounds(460, 13, 188, 24);
				panel_collec.add(btnMassDisenchant);
				btnMassDisenchant.addActionListener(control);
				
				ButtonChooseFighter btnSelectFight = new ButtonChooseFighter("Séléction combat");
				btnSelectFight.setBounds(610, 611, 136, 31);
				panel_collec.add(btnSelectFight);
				btnSelectFight.addActionListener(control);
				
				
		
		
		//--------- PANEL INVENTORY -------------
		
		JPanel panel_inventory = new JPanel();
		panel_inventory.setBackground(new Color(0, 153, 204));
		tabbedPane.addTab("Inventaire", null, panel_inventory, null);
		panel_inventory.setLayout(null);
		
		ItemList listItems = new ItemList(g,new DefaultListModel());
		listItems.setBounds(10, 10, 369, 635);
		//panel_inventory.add(listItems);
		g.addObserver(listItems);
		
		JScrollPane scrollPaneItem = new JScrollPane();
		scrollPaneItem.setBounds(10, 10, 369, 635);
		scrollPaneItem.setViewportView(listItems);
		panel_inventory.add(scrollPaneItem);
		
		VueItem lblInvTitle = new VueItem("Titre de l'objet",g,1);
		lblInvTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvTitle.setBounds(421, 10, 608, 30);
		panel_inventory.add(lblInvTitle);
		g.addObserver(lblInvTitle);
		
		VueItem lblInvDescr = new VueItem("Description de l'objet",g,2);
		lblInvDescr.setBounds(421, 69, 608, 188);
		panel_inventory.add(lblInvDescr);
		g.addObserver(lblInvDescr);
		
		VueItem lblInvQuantity = new VueItem("Quantity",g,3);
		lblInvQuantity.setBounds(421, 281, 194, 30);
		panel_inventory.add(lblInvQuantity);
		g.addObserver(lblInvQuantity);
		
		ButtonItemUse btnInvUse = new ButtonItemUse("Utiliser");
		btnInvUse.setBounds(421, 357, 303, 30);
		panel_inventory.add(btnInvUse);
		btnInvUse.addActionListener(control);
		
		SpinnerNumberModel model2 = new SpinnerNumberModel(1.0, 1.0, 999999999.0, 1.0);
		JSpinner sell_spinner = new JSpinner(model2);
		sell_spinner.setBounds(739, 417, 257, 22);
		panel_inventory.add(sell_spinner);
		
		ButtonSellItem btnInvSell = new ButtonSellItem("Vendre",sell_spinner);
		btnInvSell.setBounds(421, 413, 303, 30);
		panel_inventory.add(btnInvSell);
		btnInvSell.addActionListener(control);
		
		
		//--------- PANEL MAGASIN --------------
		
		JPanel panel_shop = new JPanel();
		panel_shop.setBackground(new Color(204, 153, 102));
		tabbedPane.addTab("Magasin", null, panel_shop, null);
		panel_shop.setLayout(null);
		
		MagasinList list = new MagasinList(g,new DefaultListModel());
		list.setBounds(231, 42, 599, 259);
		//panel_shop.add(list);
		
		JScrollPane scrollPaneMag = new JScrollPane();
		scrollPaneMag.setBounds(231, 42, 599, 259);
		scrollPaneMag.setViewportView(list);
		panel_shop.add(scrollPaneMag);
		
		VueShop lblShopDescr = new VueShop("Shop Descr",g,1);
		lblShopDescr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShopDescr.setHorizontalAlignment(SwingConstants.CENTER);
		lblShopDescr.setBounds(256, 307, 554, 70);
		panel_shop.add(lblShopDescr);
		g.addObserver(lblShopDescr);
		
		// from 0 to 9, in 1.0 steps start value 5  
		SpinnerNumberModel model1 = new SpinnerNumberModel(1.0, 1.0, 999999999.0, 1.0);  
		JSpinner ShopSpinner = new JSpinner(model1);
		ShopSpinner.setBounds(432, 481, 227, 22);
		panel_shop.add(ShopSpinner);
		
		ButtonBuyItem btnShopBuy = new ButtonBuyItem("Acheter",ShopSpinner);
		btnShopBuy.setBounds(358, 517, 371, 55);
		panel_shop.add(btnShopBuy);
		btnShopBuy.addActionListener(control);
		
		JLabel lblGoldM = new JLabel("Or :");
		lblGoldM.setBounds(12, 626, 56, 16);
		panel_shop.add(lblGoldM);
		
		VueStatPersonnage lblGoldMagasin = new VueStatPersonnage("",g,3);
		lblGoldMagasin.setBounds(80, 626, 389, 16);
		panel_shop.add(lblGoldMagasin);
		g.addObserver(lblGoldMagasin);
		
		//////////////////////////
		////// PANEL RUNES  //////
		//////////////////////////
		
		PanelRune panel_gems = new PanelRune(g);
		panel_gems.setBackground(new Color(153, 153, 153));
		tabbedPane.addTab("Runes", null, panel_gems, null);
		panel_gems.setLayout(null);
		
		CardGemList list_card_gem = new CardGemList(g,new DefaultListModel(),panel_gems);
		list_card_gem.setBounds(12, 13, 305, 446);
		//panel_gems.add(list_card_gem);
		g.addObserver(list_card_gem);
		
		JScrollPane scrollPaneCardGem = new JScrollPane();
		scrollPaneCardGem.setBounds(12, 13, 305, 446);
		scrollPaneCardGem.setViewportView(list_card_gem);
		panel_gems.add(scrollPaneCardGem);
		
		RuneList list_gem = new RuneList(g,new DefaultListModel(), panel_gems);
		list_gem.setBounds(711, 44, 346, 377);
		//panel_gems.add(list_gem);
		g.addObserver(list_gem);
		
		JScrollPane scrollPaneRune = new JScrollPane();
		scrollPaneRune.setBounds(711, 44, 346, 377);
		scrollPaneRune.setViewportView(list_gem);
		panel_gems.add(scrollPaneRune);
		
		JPanel panel = new JPanel();
		panel.setBounds(329, 13, 60, 60);
		panel_gems.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(329, 209, 60, 60);
		panel_gems.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(329, 408, 60, 60);
		panel_gems.add(panel_2);
		
		ButtonUnequipRune btnRetirerGem1 = new ButtonUnequipRune("Retirer",1);
		btnRetirerGem1.setBounds(329, 79, 97, 25);
		panel_gems.add(btnRetirerGem1);
		btnRetirerGem1.addActionListener(control);
		
		ButtonUnequipRune btnRetirerGem2 = new ButtonUnequipRune("Retirer",2);
		btnRetirerGem2.setBounds(329, 275, 97, 25);
		panel_gems.add(btnRetirerGem2);
		btnRetirerGem2.addActionListener(control);
		
		ButtonUnequipRune btnRetirerGem3 = new ButtonUnequipRune("Retirer",3);
		btnRetirerGem3.setBounds(329, 473, 97, 25);
		panel_gems.add(btnRetirerGem3);
		btnRetirerGem3.addActionListener(control);
		
		ButtonEquipRune btnEquiperGem1 = new ButtonEquipRune("Equiper",1);
		btnEquiperGem1.setBounds(329, 109, 97, 25);
		panel_gems.add(btnEquiperGem1);
		btnEquiperGem1.addActionListener(control);
		
		ButtonEquipRune btnEquiperGem2 = new ButtonEquipRune("Equiper",2);
		btnEquiperGem2.setBounds(329, 305, 97, 25);
		panel_gems.add(btnEquiperGem2);
		btnEquiperGem2.addActionListener(control);
		
		ButtonEquipRune btnEquiperGem3 = new ButtonEquipRune("Equiper",3);
		btnEquiperGem3.setBounds(329, 504, 97, 25);
		panel_gems.add(btnEquiperGem3);
		btnEquiperGem3.addActionListener(control);
		
		VueRune lblGemName1 = new VueRune("", g, 1, 0, 2);
		lblGemName1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGemName1.setBounds(401, 14, 310, 25);
		panel_gems.add(lblGemName1);
		g.addObserver(lblGemName1);
		
		VueRune lblGemName2 = new VueRune("", g, 1, 1, 2);
		lblGemName2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGemName2.setBounds(401, 209, 310, 25);
		panel_gems.add(lblGemName2);
		g.addObserver(lblGemName2);
		
		VueRune lblGemName3 = new VueRune("", g, 1, 2, 2);
		lblGemName3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGemName3.setBounds(401, 408, 310, 25);
		panel_gems.add(lblGemName3);
		g.addObserver(lblGemName3);
		
		VueRune lblGemEffect1 = new VueRune("", g, 2, 0, 2);
		lblGemEffect1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGemEffect1.setVerticalAlignment(SwingConstants.TOP);
		lblGemEffect1.setBounds(438, 83, 263, 105);
		panel_gems.add(lblGemEffect1);
		g.addObserver(lblGemEffect1);
		
		VueRune lblGemEffect2 = new VueRune("", g, 2, 1, 2);
		lblGemEffect2.setVerticalAlignment(SwingConstants.TOP);
		lblGemEffect2.setHorizontalAlignment(SwingConstants.LEFT);
		lblGemEffect2.setBounds(438, 279, 263, 105);
		panel_gems.add(lblGemEffect2);
		g.addObserver(lblGemEffect2);
		
		VueRune lblGemEffect3 = new VueRune("", g, 2, 2, 2);
		lblGemEffect3.setVerticalAlignment(SwingConstants.TOP);
		lblGemEffect3.setHorizontalAlignment(SwingConstants.LEFT);
		lblGemEffect3.setBounds(438, 477, 263, 105);
		panel_gems.add(lblGemEffect3);
		g.addObserver(lblGemEffect3);
		
		VueRune lblGemSelectedName = new VueRune("", g, 1, 0, 1);
		lblGemSelectedName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGemSelectedName.setBounds(733, 434, 324, 25);
		panel_gems.add(lblGemSelectedName);
		g.addObserver(lblGemSelectedName);
		
		VueRune lblGemSelectedEffect = new VueRune("", g, 2, 0, 1);
		lblGemSelectedEffect.setVerticalAlignment(SwingConstants.TOP);
		lblGemSelectedEffect.setBounds(732, 473, 325, 169);
		panel_gems.add(lblGemSelectedEffect);
		g.addObserver(lblGemSelectedEffect);
		
		ButtonSellRune btnSellGem = new ButtonSellRune("Vendre");
		btnSellGem.setBounds(711, 10, 346, 25);
		panel_gems.add(btnSellGem);
		btnSellGem.addActionListener(control);
		
		VueCarteRune lblStatCardRune = new VueCarteRune("",g,1);
		lblStatCardRune.setVerticalAlignment(SwingConstants.TOP);
		lblStatCardRune.setBounds(12, 472, 305, 170);
		panel_gems.add(lblStatCardRune);
		g.addObserver(lblStatCardRune);
		
		////////////////////////////
		/////// PANEL EVENT ////////
		////////////////////////////
		
		PanelBossPreview panel_event = new PanelBossPreview(g);
		panel_event.setBackground(new Color(255, 255, 204));
		panel_event.setToolTipText("");
		tabbedPane.addTab("Evenement", null, panel_event, null);
		panel_event.setLayout(null);
		
		JLabel lblBossChoice = new JLabel("Choix Niveau Boss : ");
		lblBossChoice.setBounds(12, 13, 124, 16);
		panel_event.add(lblBossChoice);
		
		SpinnerNumberModel model3 = new SpinnerNumberModel(1.0, 1.0, 999999999.0, 1.0);
		JSpinner spinnerBoss = new JSpinner(model3);
		spinnerBoss.setBounds(148, 10, 146, 22);
		panel_event.add(spinnerBoss);
		
		JPanel panel_imgboss = new JPanel();
		panel_imgboss.setBounds(715, 41, 320, 400);
		panel_event.add(panel_imgboss);
		
		VueBossPreview lblBossLevel = new VueBossPreview("",g,1,panel_event);
		lblBossLevel.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblBossLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblBossLevel.setBounds(716, 10, 319, 22);
		panel_event.add(lblBossLevel);
		g.addObserver(lblBossLevel);
		
		JPanel panelimg1 = new JPanel();
		panelimg1.setBounds(94, 42, 128, 160);
		panel_event.add(panelimg1);
		
		JPanel panelimg2 = new JPanel();
		panelimg2.setBounds(94, 235, 128, 160);
		panel_event.add(panelimg2);
		
		JPanel panelimg3 = new JPanel();
		panelimg3.setBounds(94, 430, 128, 160);
		panel_event.add(panelimg3);
		
		VueCarteBossPreview lbl_mob1_name = new VueCarteBossPreview("",g,1,1);
		lbl_mob1_name.setBounds(238, 45, 198, 16);
		panel_event.add(lbl_mob1_name);
		g.addObserver(lbl_mob1_name);
		
		VueCarteBossPreview lbl_mob2_name = new VueCarteBossPreview("",g,1,2);
		lbl_mob2_name.setBounds(235, 235, 198, 16);
		panel_event.add(lbl_mob2_name);
		g.addObserver(lbl_mob2_name);
		
		VueCarteBossPreview lbl_mob3_name = new VueCarteBossPreview("",g,1,3);
		lbl_mob3_name.setBounds(235, 430, 198, 16);
		panel_event.add(lbl_mob3_name);
		g.addObserver(lbl_mob3_name);
		
		VueCarteBossPreview lbl_mob1_stats = new VueCarteBossPreview("",g,2,1);
		lbl_mob1_stats.setBounds(238, 74, 204, 128);
		panel_event.add(lbl_mob1_stats);
		g.addObserver(lbl_mob1_stats);
		
		VueCarteBossPreview lbl_mob2_stats = new VueCarteBossPreview("",g,2,2);
		lbl_mob2_stats.setBounds(235, 264, 204, 128);
		panel_event.add(lbl_mob2_stats);
		g.addObserver(lbl_mob2_stats);
		
		VueCarteBossPreview lbl_mob3_stats = new VueCarteBossPreview("",g,2,3);
		lbl_mob3_stats.setBounds(235, 462, 204, 128);
		panel_event.add(lbl_mob3_stats);
		g.addObserver(lbl_mob3_stats);
		
		VueBossPreview lblBossName = new VueBossPreview("",g,2,panel_event);
		lblBossName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBossName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBossName.setBounds(715, 454, 320, 27);
		panel_event.add(lblBossName);
		g.addObserver(lblBossName);
		
		VueBossPreview lblBossStats = new VueBossPreview("",g,3,panel_event);
		lblBossStats.setVerticalAlignment(SwingConstants.TOP);
		lblBossStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblBossStats.setBounds(715, 494, 320, 96);
		panel_event.add(lblBossStats);
		g.addObserver(lblBossStats);
		
		ButtonFightBoss btnBossFight = new ButtonFightBoss("Combattre",spinnerBoss);
		btnBossFight.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBossFight.setBounds(94, 615, 167, 27);
		panel_event.add(btnBossFight);
		btnBossFight.addActionListener(control);
		
		ButtonResetBoss btnBossSurrend = new ButtonResetBoss("Abandonner");
		btnBossSurrend.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBossSurrend.setBounds(275, 615, 167, 27);
		panel_event.add(btnBossSurrend);
		btnBossSurrend.addActionListener(control);
		
		ButtonResetFighter btnDelete1 = new ButtonResetFighter("Enlever",0);
		btnDelete1.setBounds(469, 41, 109, 36);
		panel_event.add(btnDelete1);
		btnDelete1.addActionListener(control);
		
		ButtonResetFighter btnDelete2 = new ButtonResetFighter("Enlever",1);
		btnDelete2.setBounds(469, 235, 109, 36);
		panel_event.add(btnDelete2);
		btnDelete2.addActionListener(control);
		
		ButtonResetFighter btnDelete3 = new ButtonResetFighter("Enlever",2);
		btnDelete3.setBounds(469, 430, 109, 36);
		panel_event.add(btnDelete3);
		btnDelete3.addActionListener(control);
		
		ButtonLockFigther btnVerrouiller1 = new ButtonLockFigther("Verrouiller",0);
		btnVerrouiller1.setBounds(469, 90, 109, 36);
		panel_event.add(btnVerrouiller1);
		btnVerrouiller1.addActionListener(control);
		
		ButtonLockFigther btnVerrouiller2 = new ButtonLockFigther("Verrouiller",1);
		btnVerrouiller2.setBounds(469, 284, 109, 36);
		panel_event.add(btnVerrouiller2);
		btnVerrouiller2.addActionListener(control);
		
		ButtonLockFigther btnVerrouiller3 = new ButtonLockFigther("Verrouiller",2);
		btnVerrouiller3.setBounds(469, 479, 109, 36);
		panel_event.add(btnVerrouiller3);
		btnVerrouiller3.addActionListener(control);
		
		VueBossPreview lblBossMaxLvl = new VueBossPreview("",g,5,panel_event);
		lblBossMaxLvl.setBounds(319, 10, 167, 19);
		panel_event.add(lblBossMaxLvl);
		g.addObserver(lblBossMaxLvl);
		
		VueBossPreview lblSeasonScore = new VueBossPreview("",g,6,panel_event);
		lblSeasonScore.setBounds(469, 615, 566, 27);
		panel_event.add(lblSeasonScore);
		g.addObserver(lblSeasonScore);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 153, 204));
		tabbedPane.addTab("Hauts-Faits", null, panel_3, null);
		
		
		//--------- PANEL OPTION ----------------

		JPanel panel_option = new JPanel();
		panel_option.setBackground(new Color(255, 153, 102));
		tabbedPane.addTab("Options", null, panel_option, null);
		panel_option.setLayout(null);

		ButtonSave btnSauvegarder = new ButtonSave("Sauvegarder");
		btnSauvegarder.setBounds(320, 45, 390, 44);
		panel_option.add(btnSauvegarder);
		btnSauvegarder.addActionListener(control);

		ButtonLoad btnCharger = new ButtonLoad("Charger");
		btnCharger.setBounds(320, 108, 390, 44);
		panel_option.add(btnCharger);
		btnCharger.addActionListener(control);

		ButtonQuit btnQuitter = new ButtonQuit("Quitter");
		btnQuitter.setBounds(320, 446, 390, 44);
		panel_option.add(btnQuitter);
		btnQuitter.addActionListener(control);

		ButtonTutorial btnTuto = new ButtonTutorial(">> Tutoriel <<");
		btnTuto.setBounds(320, 171, 390, 44);
		panel_option.add(btnTuto);
		btnTuto.addActionListener(control);

		JCheckBox checkAutoCard = new JCheckBox("Séléction de carte auto");
		checkAutoCard.addActionListener(new BoxAutoSelect(g));
		checkAutoCard.setBounds(8, 621, 176, 25);
		panel_option.add(checkAutoCard);

		ButtonFightTutorial btnFightTuto = new ButtonFightTutorial("Informations Saison");
		btnFightTuto.setBounds(320, 260, 390, 44);
		panel_option.add(btnFightTuto);
		btnFightTuto.addActionListener(control);
		

		frmRpgCardCollector.setBounds(100, 100, 1080, 720);
		frmRpgCardCollector.setVisible(true);
		frmRpgCardCollector.setResizable(false);
		//frmRpgCardCollector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		g.updateVisuals();
		
		while(g.quit == false) {
			g.time++;
			if(g.timer == g.time) {
				g.updateTime();
			}
			Thread.sleep(1000);
		}
		new Thread(new Runnable() {
			  public void run() {
			    Sound.playSound("Sounds/torgueQuitter.wav");
			  }
		}).start();
		frmRpgCardCollector.dispose();
	}
}
