package mychatroom;

import java.net.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class client_frame extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 1112;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    DataOutputStream dout;
    DataInputStream din;
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            dout.writeUTF(bye); 
            dout.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);
    }
    
    public client_frame() 
    {
        initComponents();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat",file = "File";

            try 
            {
                while ((stream = din.readUTF()) != null) 
                {
                     data = stream.split(":");
                       
                     if (data[2].equals(chat)) 
                     {
                         if(stream.contains("is stopping"))
                         {
                             isConnected=false;
                             tf_username.setEditable(true);
                             tf_password.setEditable(true);
                             sock.close();
                         }
                         ta_chat.append(data[0] + ": " + data[1] + "\n");
                         ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        users.clear();
                     }
                     else if(data[2].equals(file))
                     {
                         Thread.sleep(2000);
                         ta_chat.append(data[0] + ": "+data[1]+"\n");
                         ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                        
                        try{
                            String filename="";
                            filename=din.readUTF(); 
                            filename="client"+filename;
                            long sz=Long.parseLong(din.readUTF());
                            System.out.println ("File Size: "+(sz/(1024*1024))+" MB");

                            byte b[]=new byte [1024];
                            System.out.println("Receving file..");
                            FileOutputStream fos=new FileOutputStream(new File(filename),true);
                            long bytesRead;
                            do
                            {
                                bytesRead = din.read(b, 0, b.length);
                                fos.write(b,0,b.length);
                            }while(!(bytesRead<1024));
                            fos.close(); 
                            }
                            catch(EOFException e)
                            {
                            }
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        lb_password = new javax.swing.JLabel();
        tf_password = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
		setBounds(450, 190, 600, 597);
        setName("client"); // NOI18N
        setResizable(false);
        
        JPanel panal = new JPanel();
		panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panal);
		panal.setLayout(null);

        Color c = new Color(215, 219, 221);

		
        lb_address.setText("Address : ");
        lb_address.setBounds(20, 450, 120, 30);
        lb_address.setFont(new Font("Lato", Font.BOLD, 15));
        panal.add(lb_address);
        
        tf_address.setText("localhost");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });
        tf_address.setFont(new Font("Lato", Font.BOLD, 15));
        tf_address.setBounds(100, 450, 100, 30);
        panal.add(tf_address);
        
        lb_port.setText("Port :");
        lb_port.setBounds(240, 450, 100, 30);
        lb_port.setFont(new Font("Lato", Font.BOLD, 15));
        panal.add(lb_port);
        
        tf_port.setText("3901");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });
        tf_port.setFont(new Font("Lato", Font.BOLD, 15));
        tf_port.setBounds(290, 450, 100, 30);
        panal.add(tf_port);

        lb_username.setText("Username :");
        lb_username.setBounds(18, 400, 100, 30);
        lb_username.setFont(new Font("Lato", Font.BOLD, 15));

        panal.add(lb_username);
        
        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });
        tf_username.setFont(new Font("Lato", Font.BOLD, 15));
        tf_username.setBounds(105, 400, 180, 30);
        panal.add(tf_username);

        lb_password.setText("Password : ");
        lb_password.setBounds(305, 400, 200, 30);
        lb_password.setFont(new Font("Lato", Font.BOLD, 15));
        panal.add(lb_password);
        
        tf_password.setBounds(390, 400, 180, 30);
        tf_password.setFont(new Font("Lato", Font.BOLD, 15));
        panal.add(tf_password);
        
        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });
        b_connect.setFont(new Font("Lato", Font.BOLD, 15));
        b_connect.setBounds(20, 500, 250, 30);
        b_connect.setForeground(Color.BLACK);                                   //button text color: White
        b_connect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //b_connect.setBackground(color1);
        b_connect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_connect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/btn.png"))); 
		//btnNewButton_1.setBorder(new RoundedBorder(40)); 
        b_connect.setBorderPainted(true);
       // b_connect.setFocusPainted(true);
       // b_connect.setContentAreaFilled(true);
		//setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); 
		//btnNewButton.setPreferredSize(new java.awt.Dimension(180, 40)); ;
        b_connect.setOpaque(false);
        
        /*b_connect.setBounds(20, 500, 250, 30);
        b_connect.setFont(new Font("Lato", Font.BOLD, 17));
        b_connect.setBorderPainted(false);
		//b_connect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/btn.png"))); 
		b_connect.setForeground(Color.BLACK	);
		b_connect.setOpaque(false);
		b_connect.setBackground(Color.BLACK);
        b_connect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/btn.png"))); 
		b_connect.setContentAreaFilled(false);*/

        panal.add(b_connect);

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });
        b_disconnect.setBounds(300, 500, 250, 30);
        b_disconnect.setFont(new Font("Lato", Font.BOLD, 17));
        b_disconnect.setForeground(Color.BLACK);                                   //button text color: White
        b_disconnect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b_disconnect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_disconnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/red.png"))); 

        panal.add(b_disconnect);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);
        jScrollPane1.setFont(new Font("Lato", Font.BOLD, 15));
        jScrollPane1.setBounds(20, 30, 550, 310);
        panal.add(jScrollPane1);

        tf_chat.setBounds(20, 350, 430, 30);
        panal.add(tf_chat);
        
        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
        b_send.setBounds(470, 350, 100, 30);
        b_send.setBackground(c);
        b_send.setFont(new Font("Lato", Font.BOLD, 15));

        panal.add(b_send);
        
      /*  jButton1.setText("Send file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_fileActionPerformed(evt);
            }
        });
        jButton1.setBounds(430, 450, 100, 30);
        panal.add(jButton1);*/
        
        jButton1.setText("Clear Chat");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });
        jButton1.setBounds(415, 450, 150, 30);
        jButton1.setBackground(c);
        jButton1.setFont(new Font("Lato", Font.BOLD, 15));
        panal.add(jButton1);
        
    }                        

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
    }                                          

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {                                        
   
    }                                       

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {                                            
    
    }                                           

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try 
            {
                address  = tf_address.getText();
                System.out.println(address);
                sock = new Socket(address, port);
                din = new DataInputStream(sock.getInputStream());
                dout = new DataOutputStream(sock.getOutputStream());
                System.out.println(tf_password.getText());
                dout.writeUTF(tf_password.getText());
                dout.flush();
                dout.writeUTF(username + ":has connected.:Connect");
                dout.flush(); 
                String s="Password not matched";
                if(!din.readUTF().equals(s))
                {
                    isConnected = true;
                    ListenThread();
                }
                else
                {
                    ta_chat.append("Password not matched!\n");
                    tf_username.setEditable(true);
                    sock.close();
                }
                
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }                                         

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {                                             
        sendDisconnect();
        Disconnect();
    }                                            

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {                                        
        ta_chat.setText("");
    }
    
    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               dout.writeUTF(username + ":" + tf_chat.getText() + ":" + "Chat");
               dout.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }                                      

    private void b_fileActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
        try{
            String filename="";
            String str="";  
            filename = tf_chat.getText();
            dout.writeUTF(username + ":" + tf_chat.getText() + ":" + "File");
               dout.flush();
                dout.writeUTF(filename);  
                dout.flush();  

                File f=new File(filename);
                FileInputStream fin=new FileInputStream(f);
                long sz=(int) f.length();

                byte b[]=new byte [1024];

                int read;

                dout.writeUTF(Long.toString(sz)); 
                dout.flush(); 

                while((read = fin.read(b)) != -1){
                    dout.write(b, 0, read); 
                    dout.flush(); 
                }
                fin.close();

                dout.flush(); 
            }
            catch(Exception e)
            {
                    e.printStackTrace();
                    System.out.println("An error occured");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }
    }                                      

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override

            	public void run() 
            	{
            		//for(int i=0 ; i<3 ; i++)
                    //{
            			new client_frame().setVisible(true);
                   // }
            	
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    // End of variables declaration                   
}
