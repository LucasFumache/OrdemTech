package br.com.ordemtech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.ordemtech.model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;

public class TelaCliente extends JInternalFrame {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private JTextField txtClifpesquisar;
	private JTable tblCli;
	private JTextField txtCliId;
	private JTextField txtCliNome;
	private JPasswordField txtCliSenha;
	private JTextField txtCliCpf;
	private JButton btnSalvar;
	private JTextField txtCliEndereco;
	private JTextField txtCliTelefone;
	private JButton btnAtualizar;
	private JButton btnDeletar;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		getContentPane().setBackground(new Color(255, 255, 255));
		con = dao.conectar();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Clientes");
		setBounds(100, 100, 1065, 575);
		getContentPane().setLayout(null);

		txtClifpesquisar = new JTextField();
		txtClifpesquisar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtClifpesquisar.setBackground(UIManager.getColor("Button.background"));
		txtClifpesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar_cliente();
			}
		});
		txtClifpesquisar.setColumns(10);
		txtClifpesquisar.setBounds(10, 23, 536, 20);
		getContentPane().add(txtClifpesquisar);

		tblCli = new JTable();
		tblCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblCli.setBackground(UIManager.getColor("Button.background"));
		tblCli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inserir_campos();
			}
		});
		tblCli.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		tblCli.setBounds(10, 60, 604, 96);
		getContentPane().add(tblCli);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblId.setBounds(113, 167, 26, 14);
		getContentPane().add(lblId);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNome.setBounds(84, 192, 55, 14);
		getContentPane().add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblCpf.setBounds(102, 221, 37, 14);
		getContentPane().add(lblCpf);

		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblSenha.setBounds(76, 253, 63, 14);
		getContentPane().add(lblSenha);

		txtCliId = new JTextField();
		txtCliId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliId.setBackground(UIManager.getColor("Button.background"));
		txtCliId.setColumns(10);
		txtCliId.setBounds(146, 161, 74, 20);
		getContentPane().add(txtCliId);

		txtCliNome = new JTextField();
		txtCliNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliNome.setBackground(UIManager.getColor("Button.background"));
		txtCliNome.setColumns(10);
		txtCliNome.setBounds(146, 191, 400, 20);
		getContentPane().add(txtCliNome);

		txtCliSenha = new JPasswordField();
		txtCliSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliSenha.setBackground(UIManager.getColor("Button.background"));
		txtCliSenha.setColumns(10);
		txtCliSenha.setBounds(146, 252, 400, 20);
		getContentPane().add(txtCliSenha);

		txtCliCpf = new JTextField();
		txtCliCpf.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliCpf.setBackground(UIManager.getColor("Button.background"));
		txtCliCpf.setColumns(10);
		txtCliCpf.setBounds(145, 220, 401, 20);
		getContentPane().add(txtCliCpf);

		btnSalvar = new JButton("");
		btnSalvar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSalvar.setIcon(new ImageIcon(TelaCliente.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			}
		});
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBounds(113, 402, 80, 80);
		getContentPane().add(btnSalvar);

		btnAtualizar = new JButton("");
		btnAtualizar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAtualizar.setIcon(new ImageIcon(TelaCliente.class.getResource("/img/update.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setBounds(253, 402, 80, 80);
		getContentPane().add(btnAtualizar);

		btnDeletar = new JButton("");
		btnDeletar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDeletar.setIcon(new ImageIcon(TelaCliente.class.getResource("/img/delete.png")));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setBounds(397, 402, 80, 80);
		getContentPane().add(btnDeletar);
		
		txtCliEndereco = new JTextField();
		txtCliEndereco.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliEndereco.setBackground(UIManager.getColor("Button.background"));
		txtCliEndereco.setColumns(10);
		txtCliEndereco.setBounds(146, 283, 400, 20);
		getContentPane().add(txtCliEndereco);
		
		txtCliTelefone = new JTextField();
		txtCliTelefone.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliTelefone.setBackground(UIManager.getColor("Button.background"));
		txtCliTelefone.setColumns(10);
		txtCliTelefone.setBounds(146, 314, 400, 20);
		getContentPane().add(txtCliTelefone);
		
		JLabel lblCliEndereco = new JLabel("ENDEREÇO:");
		lblCliEndereco.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblCliEndereco.setBounds(44, 284, 95, 14);
		getContentPane().add(lblCliEndereco);
		
		JLabel lblCliTelefone = new JLabel("TELEFONE:");
		lblCliTelefone.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblCliTelefone.setBounds(50, 315, 89, 14);
		getContentPane().add(lblCliTelefone);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaCliente.class.getResource("/img/search.png")));
		lblNewLabel.setBounds(553, 11, 49, 48);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaCliente.class.getResource("/img/logo4.png")));
		lblNewLabel_1.setBounds(629, 60, 410, 440);
		getContentPane().add(lblNewLabel_1);
	}
	private void buscar_cliente() {
		String lerbd = "select * from clientes where nome like ?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtClifpesquisar.getText()+"%");
			rs = pst.executeQuery();
			tblCli.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void inserir_campos() {
		int inserir = tblCli.getSelectedRow();
		txtCliId.setText(tblCli.getModel().getValueAt(inserir, 0).toString());
		txtCliNome.setText(tblCli.getModel().getValueAt(inserir, 1).toString());;
		txtCliCpf.setText(tblCli.getModel().getValueAt(inserir, 2).toString());;
		txtCliSenha.setText(tblCli.getModel().getValueAt(inserir,3).toString());;	
		txtCliEndereco.setText(tblCli.getModel().getValueAt(inserir,4).toString());;
		txtCliTelefone.setText(tblCli.getModel().getValueAt(inserir,5).toString());;
		btnSalvar.setEnabled(false);
	}
	private void inserir() {
		String lerbd = "insert into clientes (nome, cpf, senha, endereco, telefone) values (?,?,?,?,?)";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtCliNome.getText());
			pst.setString(2, txtCliCpf.getText());
			pst.setString(3, txtCliSenha.getText());
			pst.setString(4, txtCliEndereco.getText());
			pst.setString(5, txtCliTelefone.getText());
			if ((txtCliNome.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!!!");
					limpar_campos();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void atualizar() {
		String lerbd = "update clientes set nome=?, cpf=?, senha=?, endereco=?, telefone=? where id=?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtCliNome.getText());
			pst.setString(2, txtCliCpf.getText());
			pst.setString(3, txtCliSenha.getText());
			pst.setString(4, txtCliEndereco.getText());
			pst.setString(5, txtCliTelefone.getText());
			pst.setString(6, txtCliId.getText());
			if ((txtCliNome.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!!!");
					limpar_campos();
					btnSalvar.setEnabled(true);
				}
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e);
		}
	}
	private void deletar() {
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cliente","Atenção", JOptionPane.YES_NO_OPTION);
		if (confirma==JOptionPane.YES_OPTION) {
			String lerbd = "delete from clientes where id=?";
			try {
				pst = con.prepareStatement(lerbd);
				pst.setString(1, txtCliId.getText());
				int excluido = pst.executeUpdate();
				if (excluido>0) {
					JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso");
					limpar_campos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	private void limpar_campos() {
		txtClifpesquisar.setText(null);
		txtCliId.setText(null);
		txtCliNome.setText(null);
		txtCliCpf.setText(null);
		txtCliSenha.setText(null);
		txtCliEndereco.setText(null);
		txtCliTelefone.setText(null);
		((DefaultTableModel)tblCli.getModel()).setRowCount(0);
	}
}
