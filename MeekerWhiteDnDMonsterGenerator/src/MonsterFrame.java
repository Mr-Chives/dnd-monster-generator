import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JMenuItem;


public class MonsterFrame extends JFrame {
	private Monster monster;
	private DungeonMasterController dm;
	private JPanel imagePan;
	private JPanel statPan;
	private JTextField charisma;
	private JTextField constitution;
	private JTextField speed;
	private JTextField wisdom;
	private JTextField dexterity;
	private JTextField health;
	private JTextField armorClass;
	private JTextField strength;
	private JTextField intelligence;
	private JTextArea description;
	public MonsterFrame() throws IOException {
		dm = new DungeonMasterController(this);
		monster = dm.createAnyMonster();
		dm.randomizeMonster(monster);
		charisma = new JTextField(null);
		constitution = new JTextField(null);
		speed = new JTextField(null);
		wisdom = new JTextField(null);
		dexterity = new JTextField(null);
		health = new JTextField(null);
		armorClass = new JTextField(null);
		strength = new JTextField(null);
		intelligence = new JTextField(null);
		Font fooont = new Font("Gabriola",Font.BOLD,17);
		Font fnt = new Font("Arial",Font.BOLD,16);
		setBounds(100,100,1300,850);
		setTitle("D&D Monster Generator v0.506");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("beholder.jpg")).getImage());
		Container c = getContentPane();
		c.setLayout(new GridBagLayout());
		JMenuBar mnBar = new JMenuBar();
		JMenu mnu = new JMenu("Serialization");
		JMenu dev = new JMenu("Dev Options");
		JMenuItem sb = new JMenuItem("Save As Binary");
		sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.saveMonsterToBinary(monster);
			}
		});
		JMenuItem sj = new JMenuItem("Save As JSON");
		sj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.saveMonsterToJSON(monster);
			}
		});
		JMenuItem lb = new JMenuItem("Load In Binary");
		lb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monster = dm.loadMonsterFromBinary();
				setStatLabels(monster);
				setDescription(monster);
				repaint();
			}
		});
		
		JMenuItem ks = new JMenuItem("All My Friends Are Dead.");
		JMenu non = new JMenu("Nothing to see here...");
		JMenu stop = new JMenu("Seriously stop...");
		JMenu edn = new JMenu("okay thats it..");
		JMenu okay = new JMenu("I warned you. I am a master in the magical arts.");
		JMenuItem noodle = new JMenuItem("skidaddle skidoodle your dick is now a noodle");
		
		GridBagConstraints gbc = new GridBagConstraints();
		setJMenuBar(mnBar);
		mnBar.add(mnu);
		mnBar.add(dev);
		mnu.add(sb);
		mnu.add(sj);
		mnu.add(lb);
		mnu.add(ks);
		mnBar.add(dev);
		dev.add(non);
		non.add(stop);
		stop.add(edn);
		edn.add(okay);
		okay.add(noodle);
		//might need to add if(shouldFill)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		imagePan = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(monster.getPicture(),0,0,515,310, Color.GRAY, null);
			}
		};
		//this panel will import an image for the monster generated to go along with the description and stats
		imagePan.setBorder(BorderFactory.createLoweredBevelBorder());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 300;
		gbc.ipady = 500;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weighty = 0.5;
		c.add(imagePan, gbc);
		JPanel menuPan = new JPanel();
		menuPan.setBorder(BorderFactory.createLoweredBevelBorder());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipadx = 300;
		gbc.ipady = 300;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		c.add(menuPan, gbc);
		statPan = new JPanel();
		// this panel will imput all required stat numbers filled from the correct locations.
		statPan.setPreferredSize(new Dimension(600,250));
		statPan.setBorder(BorderFactory.createLoweredBevelBorder());
		statPan.setBackground(Color.GRAY);
		statPan.setLayout(new GridLayout(3,6));
		statPan.setOpaque(true);
		JLabel AC = new JLabel("AC");
		JLabel STR = new JLabel("STR");
		JLabel INT = new JLabel("INT");
		JLabel HP = new JLabel("HP");
		JLabel DEX = new JLabel("DEX");
		JLabel WIZ = new JLabel("WIS");
		JLabel SPD = new JLabel("SPD");
		JLabel CON = new JLabel("CON");
		JLabel CHA = new JLabel("CHA");
		
		setStatLabels(monster);
		statPan.add(AC);
		statPan.add(armorClass);
		armorClass.setEditable(false);
		statPan.add(STR);
		statPan.add(strength);
		strength.setEditable(false);
		statPan.add(INT);
		statPan.add(intelligence);
		intelligence.setEditable(false);
		statPan.add(HP);
		statPan.add(health);
		health.setEditable(false);
		statPan.add(DEX);
		statPan.add(dexterity);
		dexterity.setEditable(false);
		statPan.add(WIZ);
		statPan.add(wisdom);
		wisdom.setEditable(false);
		statPan.add(SPD);
		statPan.add(speed);
		speed.setEditable(false);
		statPan.add(CON);
		statPan.add(constitution);
		constitution.setEditable(false);
		statPan.add(CHA);
		statPan.add(charisma);
		charisma.setEditable(false);
		charisma.setBackground(Color.GRAY);
		constitution.setBackground(Color.GRAY);
		speed.setBackground(Color.GRAY);
		wisdom.setBackground(Color.GRAY);
		dexterity.setBackground(Color.GRAY);
		health.setBackground(Color.GRAY);
		armorClass.setBackground(Color.GRAY);
		strength.setBackground(Color.GRAY);
		intelligence.setBackground(Color.GRAY);
		charisma.setForeground(Color.RED);
		constitution.setForeground(Color.RED);
		speed.setForeground(Color.RED);
		wisdom.setForeground(Color.RED);
		dexterity.setForeground(Color.RED);
		health.setForeground(Color.RED);
		armorClass.setForeground(Color.RED);
		strength.setForeground(Color.RED);
		intelligence.setForeground(Color.RED);
		armorClass.setBorder(null);
		strength.setBorder(null);
		health.setBorder(null);
		dexterity.setBorder(null);
		wisdom.setBorder(null);
		speed.setBorder(null);
		charisma.setBorder(null);
		constitution.setBorder(null);
		intelligence.setBorder(null);
		AC.setFont(fooont);
		STR.setFont(fooont);
		INT.setFont(fooont);
		HP.setFont(fooont);
		DEX.setFont(fooont);
		WIZ.setFont(fooont);
		SPD.setFont(fooont);
		CON.setFont(fooont);
		CHA.setFont(fooont);
		armorClass.setFont(fnt);
		strength.setFont(fnt);
		intelligence.setFont(fnt);
		health.setFont(fnt);
		dexterity.setFont(fnt);
		wisdom.setFont(fnt);
		speed.setFont(fnt);
		constitution.setFont(fnt);
		charisma.setFont(fnt);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 550;
		gbc.ipady = 250;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		c.add(statPan, gbc);
		JPanel descPan = new JPanel();
		// this area requires information supplied from the background application that stores the generated monsters description
		description = new JTextArea(null,28,20);
		description.setLineWrap(true);
		descPan.setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(description);
		descPan.setAlignmentX(0);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBackground(Color.GRAY);
		description.setFont(new Font("Gabriola",Font.BOLD,19));
		description.setForeground(Color.RED);
		setDescription(monster);
		descPan.add(scroll);
		descPan.setBorder(BorderFactory.createLoweredBevelBorder());
		descPan.setBackground(Color.GRAY);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 700;
		gbc.ipady = 650;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		c.add(descPan, gbc);
		// requires Actionlisteners to generate the required actions as labled
		menuPan.setLayout(new GridLayout(3,1));
		// btnnew requires to grab all new information from the description image and stats
		JButton btnNew = new JButton("New Monster");
		btnNew.setBackground(Color.GRAY);
		btnNew.setForeground(Color.WHITE);
		btnNew.setFont(new Font("Gabriola",Font.BOLD,30));
		btnNew.setFocusPainted(false);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monster = dm.createAnyMonster();
				dm.randomizeMonster(monster);
				setStatLabels(monster);
				setDescription(monster);
				repaint();
			}
		});
		menuPan.add(btnNew);
		// this button will save the created monster in either binary or JSON format
		JButton btnSave = new JButton("Save Monster");
		btnSave.setBackground(Color.GRAY);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Gabriola",Font.BOLD,30));
		btnSave.setFocusPainted(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.saveMonsterToBinary(monster);
			}
		});
		menuPan.add(btnSave);
		//this button will load in the saved file created and display the stats description and image for created monster 
		JButton btnLoad = new JButton("Load In Monster");
		btnLoad.setBackground(Color.GRAY);
		btnLoad.setForeground(Color.WHITE);
		btnLoad.setFont(new Font("Gabriola",Font.BOLD,30));
		btnLoad.setFocusPainted(false);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monster = dm.loadMonsterFromBinary();
				setStatLabels(monster);
				setDescription(monster);
				repaint();
			}
		});
		menuPan.add(btnLoad);
	}
	private void setStatLabels(Monster monster) {
		charisma.setText(String.format("%d", monster.abilityScores.getAbilityScore("cha")));
		constitution.setText(String.format("%d", monster.abilityScores.getAbilityScore("con")));
		speed.setText(String.format("%d ft.", monster.getSpeed()));
		wisdom.setText(String.format("%d", monster.abilityScores.getAbilityScore("wis")));
		dexterity.setText(String.format("%d", monster.abilityScores.getAbilityScore("dex")));
		health.setText(String.format("%d", monster.hitPoints.getAvgHitPoints()));
		armorClass.setText(String.format("%d", monster.armorClass.getArmorClass()));
		strength.setText(String.format("%d", monster.abilityScores.getAbilityScore("str")));
		intelligence.setText(String.format("%d", monster.abilityScores.getAbilityScore("int")));
	}
	private void setDescription(Monster monster) {
		description.setText(String.format("Challenge Rating: %s", monster.getChallengeRating()) + "\n");
		description.append(String.format("Senses: %s",monster.senses.toString()) + "\n");
		description.append(String.format("Languages: %s", monster.languages.toString()) +"\n\n");
		description.append(String.format("Abilities: %s", monster.abilities.toString()) + "\n\n");
		description.append("Actions:\n");
		description.append(monster.actions.toString());
		description.setCaretPosition(0);
	}
	public static void main(String[] args) throws IOException {
		MonsterFrame frm = new MonsterFrame();
		frm.setVisible(true);
	}
}

