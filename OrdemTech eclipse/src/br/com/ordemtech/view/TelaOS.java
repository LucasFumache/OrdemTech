package br.com.ordemtech.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.ordemtech.model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class TelaOS extends JInternalFrame {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private JTextField NumOS;
	private JTextField DataOs;
	private JTextField cliName;
	private JTextField IdCli;
	private JTable tblCli;
	private JTextField IdTec;
	private JTable tblTec;
	private JTextField tecName;
	private JRadioButton rbtOrc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField TxtDefeito;
	private JTextField TxtEquipamento;
	private JTextField TxtTecnico;
	private JTextField TxtValor;
	private JTextField TxtData;
	private JButton btnSalvar;
	private JButton btnPesquisar;
	private JButton btnAtualizar;
	private JButton btnDelete;
	private JComboBox cmbSitu;
	private JRadioButton rbtOS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOS frame = new TelaOS();
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
	public TelaOS() {
		setTitle("Ordem de serviço");
		con = dao.conectar();
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 100, 1065, 575);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(24, 11, 461, 169);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nº da OS\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(76, 11, 88, 43);
		panel.add(lblNewLabel);

		NumOS = new JTextField();
		NumOS.setFont(new Font("Tahoma", Font.BOLD, 12));
		NumOS.setEnabled(false);
		NumOS.setBounds(68, 56, 96, 30);
		panel.add(NumOS);
		NumOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data de realização");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(264, 17, 169, 31);
		panel.add(lblNewLabel_1);

		DataOs = new JTextField();
		DataOs.setFont(new Font("Tahoma", Font.BOLD, 11));
		DataOs.setEnabled(false);
		DataOs.setBounds(232, 56, 201, 30);
		panel.add(DataOs);
		DataOs.setColumns(10);

		rbtOrc = new JRadioButton("Orçamento");
		buttonGroup.add(rbtOrc);
		rbtOrc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbtOrc.setBounds(68, 121, 111, 23);
		panel.add(rbtOrc);
		rbtOrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orcamento();
			}
		});

		rbtOS = new JRadioButton("Ordem de Serviço\r\n");
		buttonGroup.add(rbtOS);
		rbtOS.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rbtOS.setBounds(215, 122, 133, 23);
		panel.add(rbtOS);
		rbtOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordemserv();
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cliente",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(495, 11, 524, 169);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		cliName = new JTextField();
		cliName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cliName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar_cliente();
			}
		});
		cliName.setBounds(10, 30, 352, 33);
		panel_1.add(cliName);
		cliName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\r\n");
		lblNewLabel_2.setIcon(new ImageIcon(TelaOS.class.getResource("/img/search.png")));
		lblNewLabel_2.setBounds(372, 14, 54, 64);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Id:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(436, 21, 49, 14);
		panel_1.add(lblNewLabel_3);

		IdCli = new JTextField();
		IdCli.setFont(new Font("Tahoma", Font.BOLD, 12));
		IdCli.setSelectionColor(Color.BLACK);
		IdCli.setEnabled(false);
		IdCli.setBounds(434, 43, 80, 20);
		panel_1.add(IdCli);
		IdCli.setColumns(10);

		tblCli = new JTable();
		tblCli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inserir_campos_cliente();
			}
		});
		tblCli.setColumnSelectionAllowed(true);
		tblCli.setModel(
				new DefaultTableModel(
						new Object[][] { { null, null, null }, { null, null, null }, { null, null, null },
								{ null, null, null }, { null, null, null }, },
						new String[] { "New column", "New column", "New column" }));
		tblCli.setBounds(10, 84, 504, 74);
		panel_1.add(tblCli);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "T\u00E9cnico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(495, 232, 524, 169);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaOS.class.getResource("/img/search.png")));
		lblNewLabel_4.setBounds(378, 11, 49, 53);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Id:\r\n");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(437, 11, 49, 14);
		panel_2.add(lblNewLabel_5);

		IdTec = new JTextField();
		IdTec.setFont(new Font("Tahoma", Font.BOLD, 12));
		IdTec.setEnabled(false);
		IdTec.setBounds(437, 30, 77, 20);
		panel_2.add(IdTec);
		IdTec.setColumns(10);

		tblTec = new JTable();
		tblTec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inserir_campos_tecnico();
			}
		});
		tblTec.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, },
				new String[] { "New column", "New column", "New column" }));
		tblTec.setBounds(10, 63, 504, 95);
		panel_2.add(tblTec);

		tecName = new JTextField();
		tecName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tecName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar_tecnico();
			}
		});
		tecName.setBounds(10, 25, 358, 30);
		panel_2.add(tecName);
		tecName.setColumns(10);
		

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(124, 261, 0, 0);
		getContentPane().add(comboBox);

		JLabel lblNewLabel_6 = new JLabel("Situação:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(24, 191, 68, 29);
		getContentPane().add(lblNewLabel_6);

		cmbSitu = new JComboBox();
		cmbSitu.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmbSitu.setModel(
				new DefaultComboBoxModel(new String[] {  "Aguardando pagamento!", "Esperando liberação!", "Já entregue!", "Já pago"}));
		cmbSitu.setBounds(93, 191, 233, 29);
		getContentPane().add(cmbSitu);

		JLabel lblNewLabel_7 = new JLabel("Equipamento:\r\n");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(24, 235, 100, 29);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Defeito:\r\n");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(63, 274, 60, 14);
		getContentPane().add(lblNewLabel_8);

		TxtDefeito = new JTextField();
		TxtDefeito.setFont(new Font("Tahoma", Font.BOLD, 12));
		TxtDefeito.setBounds(124, 272, 361, 20);
		getContentPane().add(TxtDefeito);
		TxtDefeito.setColumns(10);

		TxtEquipamento = new JTextField();
		TxtEquipamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		TxtEquipamento.setColumns(10);
		TxtEquipamento.setBounds(124, 241, 361, 20);
		getContentPane().add(TxtEquipamento);

		JLabel lblNewLabel_10 = new JLabel("Técnico:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(63, 302, 60, 19);
		getContentPane().add(lblNewLabel_10);

		TxtTecnico = new JTextField();
		TxtTecnico.setFont(new Font("Tahoma", Font.BOLD, 11));
		TxtTecnico.setEnabled(false);
		TxtTecnico.setBounds(124, 303, 361, 20);
		getContentPane().add(TxtTecnico);
		TxtTecnico.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Valor Total:\r\n");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(42, 366, 77, 14);
		getContentPane().add(lblNewLabel_11);

		TxtValor = new JTextField();
		TxtValor.setName("");
		TxtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		TxtValor.setBounds(124, 368, 361, 20);
		getContentPane().add(TxtValor);
		TxtValor.setColumns(10);

		btnSalvar = new JButton("\r\n");
		btnSalvar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 205),
				new Color(0, 0, 0), new Color(0, 0, 205)));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criar_os();
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaOS.class.getResource("/img/save.png")));
		btnSalvar.setBounds(220, 442, 109, 92);
		getContentPane().add(btnSalvar);

		btnPesquisar = new JButton("\r\n");
		btnPesquisar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 205),
				new Color(0, 0, 0), new Color(0, 0, 205)));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar_os();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaOS.class.getResource("/img/pesquisar.png")));
		btnPesquisar.setBounds(378, 442, 109, 92);
		getContentPane().add(btnPesquisar);

		btnAtualizar = new JButton("");
		btnAtualizar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 205),
				new Color(0, 0, 0), new Color(0, 0, 205)));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar_os();
			}
		});
		btnAtualizar.setIcon(new ImageIcon(TelaOS.class.getResource("/img/update.png")));
		btnAtualizar.setBounds(540, 442, 109, 92);
		getContentPane().add(btnAtualizar);

		btnDelete = new JButton("\r\n");
		btnDelete.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 205),
				new Color(0, 0, 0), new Color(0, 0, 205)));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir_os();
			}
		});
		btnDelete.setIcon(new ImageIcon(TelaOS.class.getResource("/img/delete.png")));
		btnDelete.setBounds(700, 442, 109, 92);
		getContentPane().add(btnDelete);

		JLabel lblNewLabel_11_1 = new JLabel("Data Entrega:");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1.setBounds(28, 330, 95, 22);
		getContentPane().add(lblNewLabel_11_1);

		TxtData = new JTextField();
		TxtData.setFont(new Font("Tahoma", Font.BOLD, 12));
		TxtData.setToolTipText("");
		TxtData.setColumns(10);
		TxtData.setBounds(124, 333, 361, 20);
		getContentPane().add(TxtData);

		JLabel lblNewLabel_9 = new JLabel("Exemplo: 000,00");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(124, 387, 109, 14);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_9_1 = new JLabel("Exemplo: AnoMêsDia");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9_1.setBounds(124, 352, 144, 14);
		getContentPane().add(lblNewLabel_9_1);
	}

	private void buscar_cliente() {
		String lerbd = "select id, nome, CPF, telefone from clientes where nome like ?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, cliName.getText() + "%");
			rs = pst.executeQuery();
			tblCli.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void inserir_campos_cliente() {
		int inserir = tblCli.getSelectedRow();
		IdCli.setText(tblCli.getModel().getValueAt(inserir, 0).toString());
	}

	private void buscar_tecnico() {
		String lerbd = "select id, nome, especialidade, telefone from tecnicos where especialidade like ?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, tecName.getText() + "%");
			rs = pst.executeQuery();
			tblTec.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void inserir_campos_tecnico() {
		int inserir = tblTec.getSelectedRow();
		IdTec.setText(tblTec.getModel().getValueAt(inserir, 0).toString());
		TxtTecnico.setText(tblTec.getModel().getValueAt(inserir, 1).toString());
	}

	private void criar_os() {
		String lerbd = "insert into ordem_de_servico (situacao, aparelho, defeito, nome_tecnico, data_de_entrega, valortotal, id_cliente, id_tecnico, status_servico) values (?,?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, cmbSitu.getSelectedItem().toString());
			pst.setString(2, TxtEquipamento.getText());
			pst.setString(3, TxtDefeito.getText());
			pst.setString(4, TxtTecnico.getText());
			pst.setString(5, TxtData.getText());
			pst.setString(6, TxtValor.getText());
			pst.setString(7, IdCli.getText());
			pst.setString(8, IdTec.getText());
			if (rbtOrc.isSelected()) {
				pst.setString(9, "Orçamento");
			} else {
				pst.setString(9, "Ordem de Serviço");
			}
			if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
					|| (TxtTecnico.getText().isEmpty()) || (TxtData.getText().isEmpty())
					|| (TxtValor.getText().isEmpty()) || (IdCli.getText().isEmpty()) || (IdTec.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "OS feita com sucesso!!!");
					limpar_campos();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void alterar_os() {
		String lerbd = "update ordem_de_servico set situacao=?, aparelho=?, defeito=?, nome_tecnico=?, data_de_entrega=?, valortotal=?, id_cliente=?, id_tecnico=?, status_servico=? where id=?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1, cmbSitu.getSelectedItem().toString());
			pst.setString(2, TxtEquipamento.getText());
			pst.setString(3, TxtDefeito.getText());
			pst.setString(4, TxtTecnico.getText());
			pst.setString(5, TxtData.getText());
			pst.setString(6, TxtValor.getText());
			pst.setString(7, IdCli.getText());
			pst.setString(8, IdTec.getText());
			if (rbtOrc.isSelected()) {
				pst.setString(9, "Orçamento");
			} else {
				pst.setString(9, "Ordem de Serviço");
			}
			pst.setString(10, NumOS.getText());
			if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
					|| (TxtTecnico.getText().isEmpty()) || (TxtData.getText().isEmpty())
					|| (TxtValor.getText().isEmpty()) || (IdCli.getText().isEmpty()) || (IdTec.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			} else {
				int inserido = pst.executeUpdate();
				if (inserido > 0) {
					JOptionPane.showMessageDialog(null, "OS alterada com sucesso!!!");
					limpar_campos();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void pesquisar_os() {
		String num_os = JOptionPane.showInputDialog("Número da OS");
		String sql = "select * from ordem_de_servico where id=" + num_os;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				NumOS.setText(rs.getString(1));
				DataOs.setText(rs.getString(2));
				String rbtTipo = rs.getString(3);
				if (rbtTipo.equals("Ordem de Serviço")) {
					rbtOS.setSelected(true);
				} else {
					rbtOrc.setSelected(true);
				}
				cmbSitu.setSelectedItem(rs.getString(4));
				TxtEquipamento.setText(rs.getString(5));
				TxtDefeito.setText(rs.getString(6));
				TxtTecnico.setText(rs.getString(7));
				TxtData.setText(rs.getString(8));
				TxtValor.setText(rs.getString(9));
				IdCli.setText(rs.getString(10));
				IdTec.setText(rs.getString(11));
				btnSalvar.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "OS não encontrada");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void excluir_os() {
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta OS??", "Atenção",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			int confirmadnovo = JOptionPane.showConfirmDialog(null, "VOCÊ TEM CERTEZA???", "NÃO TEM VOLTA!!",JOptionPane.YES_NO_OPTION);
			if (confirmadnovo == JOptionPane.YES_OPTION) {
				String lerbd = "delete from ordem_de_servico where id=?";
				try {
					pst = con.prepareStatement(lerbd);
					pst.setString(1, NumOS.getText());
					int excluido = pst.executeUpdate();
					if (excluido > 0) {
						JOptionPane.showMessageDialog(null, "OS apagada com sucesso");
						limpar_campos();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
	}

	private void limpar_campos() {
		NumOS.setText(null);
		DataOs.setText(null);
		rbtOrc.setSelected(false);
		rbtOS.setSelected(false);
		TxtEquipamento.setText(null);
		TxtDefeito.setText(null);
		TxtTecnico.setText(null);
		TxtData.setText(null);
		TxtValor.setText(null);
		IdCli.setText(null);
		IdTec.setText(null);
		tecName.setText(null);
		cliName.setText(null);
		btnSalvar.setEnabled(true);
	}
	
	private void orcamento() {
		if(rbtOrc.isSelected()) {
			cmbSitu.setModel(new DefaultComboBoxModel(new String[] {  "Esperando liberação!"}));
		}
	}
	private void ordemserv() {
		if(rbtOS.isSelected()) {
			cmbSitu.setModel(new DefaultComboBoxModel(new String[] {  "Aguardando pagamento!", "Esperando liberação!", "Já entregue!", "Já pago"}));
			cmbSitu.setEnabled(true);
		}
	}
	 
}
