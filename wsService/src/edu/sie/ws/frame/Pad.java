package edu.sie.ws.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.websocket.Session;

import edu.sie.ws.components.MyTextPane;

//import Components.EnDe;


public class Pad extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MyTextPane editArea;
	private JScrollPane scrollPane;
	private JButton mybtn;
	Map<String,Object> connections = new HashMap<String,Object>();  
	
	
	public void addConnection(String usernamestr,Session serverSession) {
		connections.put(usernamestr, serverSession);
	}
	public void delConnection(String usernamestr,Session serverSession) {
		connections.remove(usernamestr, serverSession);
	}

	public Pad() {
		setTitle("广播");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mybtn = new JButton("发送");
		mybtn.setBounds(105, 210, 80, 50);
		mybtn.addActionListener(this);

		contentPane.add(mybtn);

		editArea = new MyTextPane();

		scrollPane = new JScrollPane(editArea);
		scrollPane.setBounds(5, 5, 300, 200);
		contentPane.add(scrollPane);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// EnDe ed = new EnDe(uid+upsw);

		if (e.getSource() == mybtn) {
			System.out.println("㏒"+editArea.getText());
			try {
				Iterator it = connections.entrySet().iterator();
				
				while(it.hasNext()){
					Entry entry=(Entry) it.next();
					((Session) entry.getValue()).getBasicRemote().sendText("㏒"+editArea.getText());

				}
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
