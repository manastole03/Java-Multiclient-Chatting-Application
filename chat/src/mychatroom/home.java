package mychatroom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.border.EmptyBorder;

public class home extends JFrame
{
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private String user[]={"manas","mat"};
	private String pass[]={"abcd","abcde"};
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JPanel panal;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public home() {
		
		final Color color2 = new Color(174, 182, 191  );
		Color color1 = new Color(93, 109, 126  );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(true);
		
		panal = new JPanel();
		panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panal);
		panal.setLayout(null);
		//((RootPaneContainer) panal).getContentPane().setBackground(Color.BLUE);
        ImageIcon icon = new ImageIcon(getClass().getResource("chat.png"));

		panal.setBackground(Color.white);
		
		JLabel label2 = new JLabel(" Welcome to Mini Multiclient Chatting ");
		label2.setForeground(color2);
		label2.setFont(new Font("Lato", Font.PLAIN, 57));
		label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		label2.setBounds(12, 0, 1008, 65);
		panal.add(label2);
		
		JLabel label1 = new JLabel("                Application in Java");
		label1.setForeground(color2);
		label1.setFont(new Font("Lato", Font.PLAIN, 57));
		//label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		label1.setBounds(12, 70, 1008, 65);
		panal.add(label1);
		
		JLabel label3 = new JLabel();
		label3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/5226.jpg"))); // NOI18N
		label3.setForeground(Color.BLACK);
		label3.setFont(new Font("Lato", Font.PLAIN, 10));
		label3.setBounds(20, 0, 1000, 580);
		panal.add(label3);
		
		btnNewButton = new JButton("Let's Chat ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				inner_home obj=new inner_home();
				obj.setTitle("Option Frame");
				obj.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Lato", Font.BOLD, 27));
		btnNewButton.setBounds(320, 480, 400, 60);
		btnNewButton.setForeground(Color.BLACK);                                   //button text color: White
	//	btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	//	btnNewButton.setBackground(color2);
		btnNewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

		btnNewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/btn.png"))); 
		//btnNewButton.setPreferredSize(new java.awt.Dimension(180, 40)); ;
		btnNewButton.setOpaque(true);
		panal.add(btnNewButton);
				
		label_1 = new JLabel("");
		//label_1.setIcon(new ImageIcon("https://cdn.pixabay.com/photo/2017/07/02/09/53/feedback-2463927__340.jpg"));
		label_1.setBounds(0, 0, 1008, 562);
		panal.add(label_1);
	}
}