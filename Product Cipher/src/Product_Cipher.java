import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Product_Cipher {

	private JFrame frame;
	private JTextField Textkey;
	private String key;
	private JTextField toEncript;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_Cipher window = new Product_Cipher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Product_Cipher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Textkey = new JTextField();
		Textkey.setForeground(SystemColor.textText);
		Textkey.setColumns(10);
		Textkey.setBounds(42, 157, 98, 20);
		frame.getContentPane().add(Textkey);

		toEncript = new JTextField();
		toEncript.setForeground(Color.BLACK);
		toEncript.setColumns(10);
		toEncript.setBounds(300, 157, 101, 20);
		frame.getContentPane().add(toEncript);

		JButton Encryptbutton = new JButton("Encrypt");
		Encryptbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int enKey = Integer.parseInt(toEncript.getText());
				FileHandle filehandler = new FileHandle();
				ArrayList<String> inputArray = filehandler.readFile("input.txt");
				Encryption encryption = new Encryption(enKey);
				encryption.SubstitutionEncription(inputArray);
				toEncript.setText("");

			}
		});
		Encryptbutton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		Encryptbutton.setBounds(300, 188, 101, 23);
		frame.getContentPane().add(Encryptbutton);

		JLabel lblEnterYourSecurity = new JLabel("Enter your Security key here:");
		lblEnterYourSecurity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterYourSecurity.setForeground(Color.WHITE);
		lblEnterYourSecurity.setBounds(21, 116, 228, 30);
		frame.getContentPane().add(lblEnterYourSecurity);

		JLabel lblProductCipher = new JLabel("PRODUCT CIPHER");
		lblProductCipher.setForeground(Color.WHITE);
		lblProductCipher.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 23));
		lblProductCipher.setBounds(73, 0, 258, 49);
		frame.getContentPane().add(lblProductCipher);

		JButton btnSubmit = new JButton("Decrypt");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int decKey = Integer.parseInt(Textkey.getText());

					Decryption decription = new Decryption(decKey);
					decription.TranspositionDecription();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Enter Valid key");
				}
			}
		});
		btnSubmit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		btnSubmit.setBounds(39, 190, 101, 23);
		frame.getContentPane().add(btnSubmit);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(0, 0, 434, 261);
		Image img = new ImageIcon(this.getClass().getResource("/sec.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel);

	}
}
