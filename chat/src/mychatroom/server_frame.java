package mychatroom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class server_frame extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;
   ServerSocket serversocket;  
   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       //PrintWriter client;
       DataOutputStream client;
       DataOutputStream dout;
       DataInputStream din;
       
       public ClientHandler(Socket clientSocket, DataOutputStream user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                din = new DataInputStream(sock.getInputStream());
                dout = new DataOutputStream(sock.getOutputStream());
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat",file = "File" ;
            String[] data;

            try 
            {
                while ((message = din.readUTF()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else if(data[2].equals(file))
                    {
                        String filename="";
                        try{
                                filename=din.readUTF(); 
                                filename="server"+filename;
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
                                    //do nothing
                            }
                            Iterator it = clientOutputStreams.iterator();

                            while (it.hasNext()) 
                            {
                                try 
                                {
                                    DataOutputStream writer = (DataOutputStream) it.next();
                                    try{
                                            writer.writeUTF(message);
                                            writer.flush();
                                            writer.writeUTF(filename);  
                                            writer.flush();  

                                            File f=new File(filename);
                                            FileInputStream fin=new FileInputStream(f);
                                            long sz=(int) f.length();

                                            byte b[]=new byte [1024];

                                            int read;

                                            writer.writeUTF(Long.toString(sz)); 
                                            writer.flush(); 

                                            while((read = fin.read(b)) != -1){
                                                writer.write(b, 0, read); 
                                                writer.flush(); 
                                            }
                                            fin.close();

                                        }
                                        catch(Exception e)
                                        {
                                                e.printStackTrace();
                                                System.out.println("An error occured");
                                        }

                                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

                                } 
                                catch (Exception ex) 
                                {
                                    ta_chat.append("Error telling everyone. \n");
                                }
                        } 
                    }
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	
        jScrollPane1 = new JScrollPane();
        ta_chat = new JTextArea();
        b_start = new JButton();
        b_end = new JButton();
        b_users = new JButton();
        b_clear = new JButton();
        tf_pwd = new JTextField();
        jLabel1 = new JLabel();
        lbl = new JLabel();
        JPanel panal;
        JFrame frame = new JFrame("Server History");
       // icon = new ImageIcon(getClass().getResource("images/cv.png"));
        
        
        Color c = new Color(215, 219, 221);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mini Discord Server frame");
        setName("server"); // NOI18N
		setBounds(450, 190, 600, 597);
        setResizable(false);
        
        panal = new JPanel();
		panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panal);
		panal.setLayout(null);
		//((RootPaneContainer) panal).getContentPane().setBackground(Color.BLUE);
       // ImageIcon icon = new ImageIcon(getClass().getResource("chat.png"));

		lbl.setText("Server Window");
		lbl.setBounds(150, 2, 1000, 50);
		lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lbl.setForeground(Color.BLACK);
		lbl.setFont(new Font("Lato", Font.BOLD, 40));
        lbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		panal.add(lbl);
		
        ta_chat.setColumns(150);
        ta_chat.setRows(50);
        jScrollPane1.setViewportView(ta_chat);
        jScrollPane1.setFont(new Font("Lato", Font.BOLD, 15));
        jScrollPane1.setBounds(20, 230, 550, 310);
    //    ta_chat.setBounds(20, 230, 550, 400);
        
       // jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
       // jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
  
        /*frame.getContentPane().add(jScrollPane1);  
        frame.setBounds(20, 230, 450, 300);

        frame.setVisible(true);  
        panal.add(ta_chat);*/
        panal.add(jScrollPane1);
    //    panal.add(frame);
        
        b_start.setText("START Connection");
    //    b_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/bt.png"))); 
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                server_start(evt);
            }
        });
        b_start.setForeground(Color.BLACK);
        b_start.setFont(new Font("Lato", Font.BOLD, 15));
        b_start.setBounds(20, 70, 180, 40);
        b_start.setBackground(new Color (39, 174, 96));
        b_start.setBorderPainted(false);
        b_start.setOpaque(true);
		b_start.setFocusPainted(false);

        panal.add(b_start);

        b_end.setText("END Connection");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });
        b_end.setForeground(Color.BLACK);
        b_end.setBounds(220, 70, 180, 40);
        b_end.setFont(new Font("Lato", Font.BOLD, 15));
        b_end.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mychatroom/red.png"))); 
        b_end.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_end.setBackground(new Color(231, 76, 60));
        b_end.setBorder(new RoundedBorder(10));
        b_end.setBorderPainted(false);
        b_end.setOpaque(true);
        panal.add(b_end);

        b_users.setText("Online Users");
        b_users.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                online_users(evt);
            }
        });
        b_users.setForeground(Color.BLACK);
        b_users.setBackground(c);
        b_users.setBounds(420, 70, 150, 40);
        b_users.setFont(new Font("Lato", Font.BOLD, 15));
        panal.add(b_users);

        b_clear.setText("Clear server History");
        b_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });
        b_clear.setBounds(350, 150, 200, 40);
        b_clear.setFont(new Font("Lato", Font.BOLD, 15));
        b_clear.setBackground(c);
        panal.add(b_clear);

        tf_pwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_password(evt);
            }
        });
        tf_pwd.setForeground(Color.BLACK);
        tf_pwd.setFont(new Font("Lato", Font.BOLD, 15));
        tf_pwd.setBounds(120, 150, 200, 40);
        panal.add(tf_pwd);

        jLabel1.setText("Password :");
        jLabel1.setForeground(Color.BLACK);
		jLabel1.setFont(new Font("Lato", Font.BOLD, 15));
        jLabel1.setBounds(20, 150, 100, 40);
        panal.add(jLabel1);
        
        
    }                     

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {                                      
        try 
        {
            Thread.sleep(2000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        tf_pwd.setEditable(true);
        ta_chat.setText("");
       try {
           
           clientOutputStreams.clear();
           users.clear();
           serversocket.close();
           System.out.println(clientOutputStreams.size());
           b_end.setEnabled(false);
           b_start.setEnabled(true);
           
       } catch (IOException ex) {
                System.out.println("lllll");
               }
    }                                     

    //Function for server starting
    private void server_start(java.awt.event.ActionEvent evt) {                                        
        Thread starter = new Thread(new ServerStart());
        starter.start();
        tf_pwd.setEditable(false);
        ta_chat.append("Server started...\n");
        b_start.setEnabled(false);
        b_end.setEnabled(true);
        
    }                                       

    private void online_users(java.awt.event.ActionEvent evt) {                                        
        ta_chat.append("\n Online users : \n");
        for (String current_user : users)
        {
            ta_chat.append(current_user);
            ta_chat.append("\n");
        }    
        
    }                                       

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {                                        
        ta_chat.setText("");
    }                                       

    private void set_password(java.awt.event.ActionEvent evt) {                              
        // TODO add your handling code here:
    }                             

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  
            try 
            {
                serversocket = new ServerSocket(1112);

                while (true) 
                {
                            Socket clientSock = serversocket.accept();
                            DataOutputStream dout=new DataOutputStream(clientSock.getOutputStream());
                            DataInputStream din = new DataInputStream(clientSock.getInputStream());
                            String s = din.readUTF();
                            System.out.println(s);
                            if(s.equals(tf_pwd.getText()))
                            {
                                dout.writeUTF("Connected");
                                dout.flush();
                                clientOutputStreams.add(dout);
                                Thread listener = new Thread(new ClientHandler(clientSock, dout));
                                listener.start();
                                ta_chat.append("Got a connection. \n");
                            }
                            else
                            {
                                dout.writeUTF("Password not matched");
                                dout.flush();
                            }
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.add(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                DataOutputStream writer = (DataOutputStream) it.next();
                writer.writeUTF(message);
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
            } 
            catch (Exception ex) 
            {
                System.out.println("inside");
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_pwd;
    
    // End of variables declaration                   
}
