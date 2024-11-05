package br.com.ordemtech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.ordemtech.model.DAO;
import java.awt.Color;

public class TelaFuncionarios extends JInternalFrame {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JPasswordField txtSenha;
	private JComboBox cmbLogin;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JButton btnSalvar;
	private JButton btnAtualizar;
	private JButton btnDeletar;
	private JButton btnPesquisar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionarios frame = new TelaFuncionarios();
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
	public TelaFuncionarios() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Funcionários");
		con = dao.conectar();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1065, 575);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblId.setBounds(123, 56, 26, 14);
		getContentPane().add(lblId);
		
		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblSenha.setBounds(86, 177, 63, 14);
		getContentPane().add(lblSenha);
		
		JLabel lblLogin = new JLabel("LOGIN:");
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblLogin.setBounds(89, 209, 60, 14);
		getContentPane().add(lblLogin);
		
		txtId = new JTextField();
		txtId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtId.setBackground(UIManager.getColor("Button.background"));
		txtId.setColumns(10);
		txtId.setBounds(162, 50, 106, 20);
		getContentPane().add(txtId);
		
		txtSenha = new JPasswordField();
		txtSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSenha.setBackground(UIManager.getColor("Button.background"));
		txtSenha.setColumns(10);
		txtSenha.setBounds(162, 175, 400, 20);
		getContentPane().add(txtSenha);
		
		cmbLogin = new JComboBox();
		cmbLogin.setBackground(UIManager.getColor("Button.highlight"));
		cmbLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cmbLogin.setModel(new DefaultComboBoxModel(new String[] {"", "admin", "user"}));
		cmbLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbLogin.setBounds(162, 205, 112, 22);
		getContentPane().add(cmbLogin);
		
		btnSalvar = new JButton("");
		btnSalvar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSalvar.setIcon(new ImageIcon(TelaFuncionarios.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			}
		});
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBounds(69, 337, 80, 80);
		getContentPane().add(btnSalvar);
		
		btnAtualizar = new JButton("");
		btnAtualizar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAtualizar.setIcon(new ImageIcon(TelaFuncionarios.class.getResource("/img/update.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setBounds(194, 337, 80, 80);
		getContentPane().add(btnAtualizar);
		
		btnDeletar = new JButton("");
		btnDeletar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDeletar.setIcon(new ImageIcon(TelaFuncionarios.class.getResource("/img/delete.png")));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setBounds(320, 337, 80, 80);
		getContentPane().add(btnDeletar);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPesquisar.setIcon(new ImageIcon(TelaFuncionarios.class.getResource("/img/pesquisar.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBounds(438, 337, 80, 80);
		getContentPane().add(btnPesquisar);
		
		txtNome = new JTextField();
		txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.setBackground(UIManager.getColor("Button.background"));
		txtNome.setColumns(10);
		txtNome.setBounds(162, 82, 400, 20);
		getContentPane().add(txtNome);
		
		txtCpf = new JTextField();
		txtCpf.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCpf.setBackground(UIManager.getColor("Button.background"));
		txtCpf.setColumns(10);
		txtCpf.setBounds(162, 113, 400, 20);
		getContentPane().add(txtCpf);
		
		txtTelefone = new JTextField();
		txtTelefone.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtTelefone.setBackground(UIManager.getColor("Button.background"));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(162, 144, 400, 20);
		getContentPane().add(txtTelefone);
		
		JLabel lblNome = new JLabel("NOME:");
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNome.setBounds(94, 83, 55, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblCpf.setBounds(112, 114, 37, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblTelefone = new JLabel("TELEFONE:");
		lblTelefone.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblTelefone.setBounds(60, 145, 89, 14);
		getContentPane().add(lblTelefone);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaFuncionarios.class.getResource("/img/logo4.png")));
		lblNewLabel.setBounds(599, 60, 440, 440);
		getContentPane().add(lblNewLabel);
	} 
	private void inserir() {
		String lerbd = "insert into funcionarios (nome, cpf, telefone, senha, login) values (?,?,?,?,?)";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCpf.getText());
			pst.setString(3, txtTelefone.getText());
			pst.setString(4, txtSenha.getText());
			pst.setString(5, cmbLogin.getSelectedItem().toString());
			if ((txtNome.getText().isEmpty()) || txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!!!");
					txtId.setText(null);
					txtNome.setText(null);
					txtCpf.setText(null);
					txtTelefone.setText(null);
					txtSenha.setText(null);
					cmbLogin.setSelectedItem(null);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void atualizar() {
		String lerbd = "update funcionarios set nome=?, cpf=?, telefone=?, senha=?, login=? where id=?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCpf.getText());
			pst.setString(3, txtTelefone.getText());
			pst.setString(4, txtSenha.getText());
			pst.setString(5, cmbLogin.getSelectedItem().toString());
			pst.setString(6, txtId.getText());
			if (((txtId.getText().isEmpty()) || txtNome.getText().isEmpty()) || txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!!!");
					txtId.setText(null);
					txtNome.setText(null);
					txtCpf.setText(null);
					txtTelefone.setText(null);
					txtSenha.setText(null);
					cmbLogin.setSelectedItem(null);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void deletar() {
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o usuário","Atenção",JOptionPane.YES_NO_OPTION);
		if (confirma==JOptionPane.YES_OPTION) {
			String lerbd="delete from funcionarios where id=?";
			try {
				pst = con.prepareStatement(lerbd);
				pst.setString(1, txtId.getText());
				int excluido = pst.executeUpdate();
				if (excluido>0) {
					JOptionPane.showMessageDialog(null, "Usuário apagado com sucesso");
					txtId.setText(null);
					txtNome.setText(null);
					txtCpf.setText(null);
					txtTelefone.setText(null);
					txtSenha.setText(null);
					cmbLogin.setSelectedItem(null);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	private void pesquisar() {
		String lerbd = "select * from funcionarios where cpf=?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtCpf.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtId.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtTelefone.setText(rs.getString(4));
				txtSenha.setText(rs.getString(5));
				cmbLogin.setSelectedItem(rs.getString(6));
				btnSalvar.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "usuário inexistente");
				txtNome.setText(null);
				txtCpf.setText(null);
				txtTelefone.setText(null);
				txtSenha.setText(null);
				cmbLogin.setSelectedItem(null);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
