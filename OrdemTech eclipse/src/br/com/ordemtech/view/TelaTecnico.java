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
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaTecnico extends JInternalFrame {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JPasswordField txtSenha;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JButton btnSalvar;
	private JButton btnAtualizar;
	private JButton btnDeletar;
	private JLabel lblNewLabel;
	private JTextField txtTecPesquisar;
	private JTable tblTec;
	private JTextField txtCpf;
	private JLabel lblCpf;
	private JComboBox cmbEspecialidade;

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
	public TelaTecnico() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Técnicos");
		con = dao.conectar();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1065, 575);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblId.setBounds(123, 162, 26, 14);
		getContentPane().add(lblId);
		
		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblSenha.setBounds(86, 317, 63, 14);
		getContentPane().add(lblSenha);
		
		txtId = new JTextField();
		txtId.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtId.setBackground(UIManager.getColor("Button.background"));
		txtId.setColumns(10);
		txtId.setBounds(159, 161, 106, 20);
		getContentPane().add(txtId);
		
		txtSenha = new JPasswordField();
		txtSenha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSenha.setBackground(UIManager.getColor("Button.background"));
		txtSenha.setColumns(10);
		txtSenha.setBounds(159, 316, 400, 20);
		getContentPane().add(txtSenha);
		
		btnSalvar = new JButton("");
		btnSalvar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSalvar.setIcon(new ImageIcon(TelaFuncionarios.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			}
		});
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBounds(86, 400, 80, 80);
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
		btnAtualizar.setBounds(224, 400, 80, 80);
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
		btnDeletar.setBounds(350, 400, 80, 80);
		getContentPane().add(btnDeletar);
		
		txtNome = new JTextField();
		txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.setBackground(UIManager.getColor("Button.background"));
		txtNome.setColumns(10);
		txtNome.setBounds(159, 192, 400, 20);
		getContentPane().add(txtNome);
		
		txtTelefone = new JTextField();
		txtTelefone.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtTelefone.setBackground(UIManager.getColor("Button.background"));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(159, 285, 400, 20);
		getContentPane().add(txtTelefone);
		
		JLabel lblNome = new JLabel("NOME:");
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNome.setBounds(94, 193, 55, 14);
		getContentPane().add(lblNome);
		
		JLabel lbl = new JLabel("ESPECIALIDADE:");
		lbl.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lbl.setBounds(10, 255, 139, 14);
		getContentPane().add(lbl);
		
		JLabel lblTelefone = new JLabel("TELEFONE:");
		lblTelefone.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblTelefone.setBounds(56, 286, 99, 14);
		getContentPane().add(lblTelefone);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaTecnico.class.getResource("/img/logo4.png")));
		lblNewLabel.setBounds(599, 58, 440, 440);
		getContentPane().add(lblNewLabel);
		
		txtTecPesquisar = new JTextField();
		txtTecPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar_tecnicos();
			}
		});
		txtTecPesquisar.setColumns(10);
		txtTecPesquisar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtTecPesquisar.setBackground(UIManager.getColor("Button.background"));
		txtTecPesquisar.setBounds(10, 21, 536, 20);
		getContentPane().add(txtTecPesquisar);
		
		tblTec = new JTable();
		tblTec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inserir_campos();
			}
		});
		tblTec.setModel(new DefaultTableModel(
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
		tblTec.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblTec.setBackground(UIManager.getColor("Button.background"));
		tblTec.setBounds(10, 55, 580, 96);
		getContentPane().add(tblTec);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaTecnico.class.getResource("/img/search.png")));
		lblNewLabel_1.setBounds(556, 0, 49, 48);
		getContentPane().add(lblNewLabel_1);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCpf.setBackground(UIManager.getColor("Button.background"));
		txtCpf.setBounds(159, 223, 400, 20);
		getContentPane().add(txtCpf);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblCpf.setBounds(107, 224, 42, 14);
		getContentPane().add(lblCpf);
		
		cmbEspecialidade = new JComboBox();
		cmbEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbEspecialidade.setModel(new DefaultComboBoxModel(new String[] {"", "Dispositivos Móveis", "Computadores e Periféricos", "Áudio e Vídeo", "Equipamentos Comunicação", "Dispositivos Entreterimento", "Equipamentos de Automação Residencial", ""}));
		cmbEspecialidade.setBounds(159, 252, 400, 22);
		getContentPane().add(cmbEspecialidade);
	}
	private void inserir() {
		String lerbd = "insert into tecnicos (nome, cpf, especialidade, telefone, senha) values (?,?,?,?,?)";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCpf.getText());
			pst.setString(3, cmbEspecialidade.getSelectedItem().toString());
			pst.setString(4, txtTelefone.getText());
			pst.setString(5, txtSenha.getText());
			if ((txtNome.getText().isEmpty()) || txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "Técnico cadastrado com sucesso!!!");
					txtId.setText(null);
					txtNome.setText(null);
					txtCpf.setText(null);
					cmbEspecialidade.setSelectedItem(null);
					txtTelefone.setText(null);
					txtSenha.setText(null);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void atualizar() {
		String lerbd = "update tecnicos set nome=?, cpf=?, especialidade=?, telefone=?, senha=? where id=?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCpf.getText());
			pst.setString(3, cmbEspecialidade.getSelectedItem().toString());
			pst.setString(4, txtTelefone.getText());
			pst.setString(5, txtSenha.getText());
			pst.setString(6, txtId.getText());
			if (((txtId.getText().isEmpty()) || txtNome.getText().isEmpty()) || txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "Técnico alterado com sucesso!!!");
					txtId.setText(null);
					txtCpf.setText(null);
					txtNome.setText(null);
					cmbEspecialidade.setSelectedItem(null);
					txtTelefone.setText(null);
					txtSenha.setText(null);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void deletar() {
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o técnico","Atenção",JOptionPane.YES_NO_OPTION);
		if (confirma==JOptionPane.YES_OPTION) {
			String lerbd="delete from tecnico where id=?";
			try {
				pst = con.prepareStatement(lerbd);
				pst.setString(1, txtId.getText());
				int excluido = pst.executeUpdate();
				if (excluido>0) {
					JOptionPane.showMessageDialog(null, "Técnico apagado com sucesso");
					txtId.setText(null);
					txtNome.setText(null);
					txtCpf.setText(null);
					cmbEspecialidade.setSelectedItem(null);
					txtTelefone.setText(null);
					txtSenha.setText(null);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	private void buscar_tecnicos() {
		String lerbd = "select * from tecnicos where nome like ?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, txtTecPesquisar.getText()+"%");
			rs = pst.executeQuery();
			tblTec.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void inserir_campos() {
		int inserir = tblTec.getSelectedRow();
		txtId.setText(tblTec.getModel().getValueAt(inserir, 0).toString());
		txtNome.setText(tblTec.getModel().getValueAt(inserir, 1).toString());
		txtCpf.setText(tblTec.getModel().getValueAt(inserir, 2).toString());
		txtTelefone.setText(tblTec.getModel().getValueAt(inserir, 3).toString());
		txtSenha.setText(tblTec.getModel().getValueAt(inserir, 4).toString());
		cmbEspecialidade.setSelectedItem(tblTec.getModel().getValueAt(inserir,5).toString());
		btnSalvar.setEnabled(false);
	}
}

