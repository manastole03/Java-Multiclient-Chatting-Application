package mychatroom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



public class inner_home extends JFrame {
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private String user[]={"manas","mat"};
	private String pass[]={"abcd","abcde"};
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JPanel panal;

    private int radius;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inner_home frame = new inner_home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public inner_home() {
		
		final Color color2 = new Color(174, 182, 191  );
		Color color11 = new Color(93, 109, 126  );
		Color color1 = new Color(		244, 208, 63 );

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(true);
		
		panal = new JPanel();
		panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panal);
		panal.setLayout(null);
		//((RootPaneContainer) panal).getContentPane().setBackground(Color.BLUE);
		panal.setBackground(Color.WHITE);

		

		JLabel label3 = new JLabel();
		label3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/7883.jpg"))); // NOI18N
		label3.setForeground(Color.BLACK);
		label3.setFont(new Font("Lato", Font.PLAIN, 20));
		label3.setBounds(0, 0, 1000, 500);
		panal.add(label3);
		

		btnNewButton = new JButton("Start Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				server_frame obj=new server_frame();
				//obj.setTitle("Click for multiclient windows");
				obj.setVisible(true);
				//panal.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Lato", Font.BOLD, 47));
		btnNewButton.setBounds(520, 500, 400, 50);
		btnNewButton.setForeground(Color.BLACK);                                   //button text color: White
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(color1);
		btnNewButton.setBorderPainted(false);

		btnNewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		//btnNewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/btn.png"))); 
		//btnNewButton.setPreferredSize(new java.awt.Dimension(180, 40)); ;
		btnNewButton.setOpaque(true);
		panal.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Start Chatting");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				client_frame obj=new client_frame();
				//obj.setTitle("Feedback");
				obj.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lato", Font.BOLD, 47));
		btnNewButton_1.setBounds(56, 500, 400, 50);
		btnNewButton_1.setForeground(Color.BLACK);                                   //button text color: White
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(color1);
		btnNewButton_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnNewButton_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/btn.png"))); 
		//btnNewButton_1.setBorder(new RoundedBorder(40)); 
		btnNewButton_1.setBorderPainted(false);
		//btnNewButton_1.setFocusPainted(false);

		btnNewButton_1.setContentAreaFilled(false);
		//setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); 
		//btnNewButton.setPreferredSize(new java.awt.Dimension(180, 40)); ;
		btnNewButton_1.setOpaque(false);
		panal.add(btnNewButton_1);
		
		/*btnNewButton_1 = new JButton("Start gyygi Chatting");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	dispose();
				client_frame obj=new client_frame();
				obj.setTitle("Feedback");
				obj.setVisible(true);
	}
		});
		btnNewButton_1.setFont(new Font("Lato", Font.BOLD, 27));
		btnNewButton_1.setBounds(56, 410, 400, 100);
		btnNewButton_1.setBackground(color1);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		panal.add(btnNewButton_1);*/
		
		
		label_1 = new JLabel("");
		//label_1.setIcon(new ImageIcon("https://cdn.pixabay.com/photo/2017/07/02/09/53/feedback-2463927__340.jpg"));
		label_1.setBounds(0, 0, 1008, 562);
		panal.add(label_1);
	
	}
}