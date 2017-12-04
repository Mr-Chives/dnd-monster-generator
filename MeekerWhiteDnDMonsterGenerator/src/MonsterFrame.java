import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class MonsterFrame extends JFrame {
	public MonsterFrame() {
		setBounds(100,100,1000,805);
		setTitle("D&D Monster Generator V0.001");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//might need to add if(shouldFill)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JPanel imagePan = new JPanel();
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
		statPan.setBorder(BorderFactory.createLoweredBevelBorder());
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 700;
		gbc.ipady = 250;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		c.add(statPan, gbc);
		JPanel descPan = new JPanel();
		descPan.setBorder(BorderFactory.createLoweredBevelBorder());
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 700;
		gbc.ipady = 650;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		c.add(descPan, gbc);
		menuPan.setLayout(new GridLayout(3,1));
		JButton btnNew = new JButton("New Monster");
		menuPan.add(btnNew);
		JButton btnSave = new JButton("Save Monster");
		menuPan.add(btnSave);
		JButton btnLoad = new JButton("Load In Monster");
		menuPan.add(btnLoad);
	}
	public static void main(String[] args) {
		MonsterFrame frm = new MonsterFrame();
		frm.setVisible(true);
	}
}
