package networkstatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

public class NetworkStatus extends JFrame implements ActionListener {

    private JLabel statusLabel;
    JButton checkButton,close; 
    
    public NetworkStatus() {
        statusLabel = new JLabel("          Checking...");
        statusLabel.setFont(new Font("Raleway", Font.PLAIN, 25));
        
        
        checkButton = new JButton("Check Network Status");
        checkButton.addActionListener(this);
        
        close = new JButton("Close");
        close.addActionListener(this);
        
        JLabel text=new JLabel("<html>Network<br>  Status<br>Checker</html>");
        text.setFont(new Font("Raleway",Font.ITALIC,30));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(statusLabel, BorderLayout.CENTER);
        panel.add(checkButton, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(close,BorderLayout.SOUTH);
        panel.add(text,BorderLayout.LINE_START);
        
        getContentPane().add(panel);
        
        setBounds(400,200,500,400);
        setUndecorated(true);
        setSize(500,400);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==checkButton){
                        updateNetworkStatus();
                }else if(ae.getSource()==close){
                    dispose();
                }
    }
    
    public static void main(String[] args) {
        new NetworkStatus();
    }
    
    private void updateNetworkStatus() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            if (address.isReachable(5000)) {
                statusLabel.setText("    Connected to the Internet");
                statusLabel.setForeground(Color.GREEN);
            } else {
                statusLabel.setText("      Not Connected");
                statusLabel.setForeground(Color.RED);
            }
        } catch (IOException e) {
            statusLabel.setText("    Error checking status");
            statusLabel.setForeground(Color.BLUE);
        }
    }
}
