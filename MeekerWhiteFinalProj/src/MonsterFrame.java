import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*class ImagePan extends JPanel{
	BufferedImage image;
	public ImagePan() throws IOException {
		image = ImageIO.read(new File("c:\\Workspace\\MeekerWhiteProj\\beholder.jpg"));	
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0,300,500, Color.GRAY, null);
		System.out.println("THIS SOMEWHAT WORKS");
	}
}
*/
public class MonsterFrame extends JFrame {
	public MonsterFrame() throws IOException {
		Font fooont = new Font("Gabriola",Font.BOLD,15);
		setBounds(100,100,1300,850);
		setTitle("D&D Monster Generator V0.175");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("beholder.jpg")).getImage());
		Container c = getContentPane();
		c.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//might need to add if(shouldFill)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		BufferedImage image = ImageIO.read(new File(".\\beholder.jpg"));
		JPanel imagePan = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image,0,0,525,320, Color.GRAY, null);
			}
		};
		//ImageIcon monster = new ImageIcon(".\\beholder.jpg");
		//imagePan.add(new JLabel(monster));
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
		JPanel statPan = new JPanel();
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
		JTextField charisma = new JTextField("10");
		JTextField constitution = new JTextField("10");
		JTextField speed = new JTextField("30ft");
		JTextField wisdom = new JTextField("7");
		JTextField dexterity = new JTextField("10");
		JTextField health = new JTextField("200");
		JTextField armorClass = new JTextField("20");
		JTextField strength = new JTextField("10");
		JTextField intelligence = new JTextField("10");
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
		AC.setFont(fooont);
		STR.setFont(fooont);
		INT.setFont(fooont);
		HP.setFont(fooont);
		DEX.setFont(fooont);
		WIZ.setFont(fooont);
		SPD.setFont(fooont);
		CON.setFont(fooont);
		CHA.setFont(fooont);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 550;
		gbc.ipady = 250;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		c.add(statPan, gbc);
		JPanel descPan = new JPanel();
		JTextArea  description = new JTextArea("This is just a test to see how this will fill on the display. With my luck this will probably fail.....",28,20);
		description.setLineWrap(true);
		descPan.setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(description);
		descPan.setAlignmentX(0);
		description.setEditable(false);
		description.setBackground(Color.GRAY);
		description.setFont(new Font("Gabriola",Font.BOLD,19));
		description.setForeground(Color.RED);
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
		menuPan.setLayout(new GridLayout(3,1));
		JButton btnNew = new JButton("New Monster");
		btnNew.setBackground(Color.GRAY);
		btnNew.setForeground(Color.WHITE);
		btnNew.setFont(new Font("Gabriola",Font.BOLD,30));
		btnNew.setFocusPainted(false);
		menuPan.add(btnNew);
		JButton btnSave = new JButton("Save Monster");
		btnSave.setBackground(Color.GRAY);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Gabriola",Font.BOLD,30));
		menuPan.add(btnSave);
		JButton btnLoad = new JButton("Load In Monster");
		btnLoad.setBackground(Color.GRAY);
		btnLoad.setForeground(Color.WHITE);
		btnLoad.setFont(new Font("Gabriola",Font.BOLD,30));
		menuPan.add(btnLoad);
	}
	private BufferedImage ImageIO(File file) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) throws IOException {
		MonsterFrame frm = new MonsterFrame();
		frm.setVisible(true);
	}
}
