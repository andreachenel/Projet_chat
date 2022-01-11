package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class NewFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFrame frame = new NewFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewFrame() {
		
		// Creates the panel
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		JTextField msg = new JTextField(10);
		JLabel coUsr = new JLabel("Connected Users : ");
		JButton send = new JButton("Send");
		JLabel usrCh = new JLabel("User Choosen");
		JButton done = new JButton("Done");
		JButton logOut = new JButton("Log out");
		JButton refresh = new JButton("Refresh");
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		// Set bounds for every component
		coUsr.setBounds(150, 10, 200, 80); // x, y, largeur, hauteur
		msg.setBounds(650, 480, 500, 20);
		send.setBounds(1180, 480, 70, 20);
		usrCh.setBounds(160, 150, 400, 100);
		// usrComboBox.setBounds(150, 100, 160, 30);
		done.setBounds(190, 150, 80, 20);
		logOut.setBounds(1100, 20, 100, 30);
		refresh.setBounds(20, 20, 150, 30);
		
		// Left Part
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane.setLeftComponent(scrollPane);
		
//		scrollPane.setViewportView(coUsr);
//		scrollPane.setViewportView(done);
//		scrollPane.setViewportView(refresh);
		scrollPane.setViewportView(usrCh);
		
		// Right part
		JScrollPane scrollPane_1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane.setRightComponent(scrollPane_1);
		
//		scrollPane_1.add(send);
		scrollPane_1.add(msg);
//		scrollPane_1.add(logOut);
		
	
		
		
	}
	
	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		contentPane.setVisible(true);
	}

}
