package br.com.ordemtech.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.ordemtech.model.DAO;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class Login extends JFrame {
	
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCpf;
	private JButton btLogin;
	private JLabel lblData;
	private JPasswordField txtSenha;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logonovo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 424);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Lucida Bright", Font.BOLD, 17));
		lblCpf.setBounds(63, 121, 49, 21);
		contentPane.add(lblCpf);
		
		txtCpf = new JTextField();
		txtCpf.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCpf.setBackground(UIManager.getColor("Button.background"));
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setBounds(63, 146, 357, 30);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Lucida Bright", Font.BOLD, 17));
		lblSenha.setBounds(63, 187, 60, 21);
		contentPane.add(lblSenha);
		
		btLogin = new JButton("Login");
		btLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btLogin.setBackground(UIManager.getColor("Button.background"));
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		btLogin.setBounds(182, 292, 127, 60);
		contentPane.add(btLogin);
		
		lblData = new JLabel("");
		lblData.setBounds(364, 371, 116, 14);
		contentPane.add(lblData);
		
		txtSenha = new JPasswordField();
		txtSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSenha.setBackground(UIManager.getColor("Button.background"));
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSenha.setColumns(10);
		txtSenha.setBounds(63, 212, 357, 30);
		contentPane.add(txtSenha);
		
		JLabel lblTitulo = new JLabel("ORDEMTECH");
		lblTitulo.setFont(new Font("Lucida Bright", Font.BOLD, 24));
		lblTitulo.setBounds(156, 28, 159, 60);
		contentPane.add(lblTitulo);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(325, 28, 60, 60);
		contentPane.add(lblNewLabel);
	}
	public void logar() {
		String lerbd = "select * from funcionarios where Cpf=? and Senha=?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtCpf.getText());
			String pegar = new String(txtSenha.getPassword());
			pst.setString(2, pegar);
			rs = pst.executeQuery();
			if (rs.next()) {
				String login = rs.getString(6);
				if (login.equals("admin")) {
					TelaInicial inicial = new TelaInicial();
					inicial.setVisible(true);
					inicial.getItmFuncionarios().setEnabled(true);
					inicial.getItmClientes().setEnabled(true);
					inicial.getItmTecnicos().setEnabled(true);
					inicial.getLblFunc().setText(rs.getString(2));
					this.dispose();
					con.close();
				} else if (login.equals("user")) {
					TelaInicial inicial = new TelaInicial();
					inicial.setVisible(true);
					inicial.getItmClientes().setEnabled(true);
					inicial.getLblFunc().setText(rs.getString(2));
					this.dispose();
				}
				else {
					TelaInicial inicial = new TelaInicial();
					inicial.setVisible(true);
					inicial.getLblFunc().setText(rs.getString(2));
					this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "usuário ou senha incorreto");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
