package br.com.ordemtech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Color;


public class TelaInicial extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem itmFuncionarios;
	private JLabel lblFunc;
	private JDesktopPane desktop;
	private JMenuItem itmClientes;
	private JMenuItem itmOS;
	private JMenuItem itmSair;
	private JMenuItem itmTecnicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/img/logonovo.png")));
		setTitle("Tela inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1249, 737);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		mnCadastrar.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		menuBar.add(mnCadastrar);
		
		itmClientes = new JMenuItem("Clientes");
		itmClientes.setBackground(Color.LIGHT_GRAY);
		itmClientes.setForeground(Color.BLACK);
		itmClientes.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/cliente.png")));
		itmClientes.setEnabled(false);
		itmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				desktop.add(cliente);
			}
		});
		itmClientes.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		mnCadastrar.add(itmClientes);
		
		itmFuncionarios = new JMenuItem("Funcionários");
		itmFuncionarios.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/funcionarios.png")));
		itmFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFuncionarios funcionario = new TelaFuncionarios();
				funcionario.setVisible(true);
				desktop.add(funcionario);
			}
		});
		itmFuncionarios.setEnabled(false);
		itmFuncionarios.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		mnCadastrar.add(itmFuncionarios);
		
		itmTecnicos = new JMenuItem("Técnicos");
		itmTecnicos.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/tecnico.png")));
		itmTecnicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTecnico Tecnico = new TelaTecnico();
				Tecnico.setVisible(true);
				desktop.add(Tecnico);
			}
		});
		itmTecnicos.setEnabled(false);
		itmTecnicos.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		mnCadastrar.add(itmTecnicos);
		
		JMenu mnOrdemServ = new JMenu("Ordem De Serviço");
		mnOrdemServ.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		menuBar.add(mnOrdemServ);
		
		itmOS = new JMenuItem("OS");
		itmOS.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/os.png")));
		itmOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOS os = new TelaOS();
				os.setVisible(true);
				desktop.add(os);
			}
		});
		itmOS.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		mnOrdemServ.add(itmOS);
		
		JMenu mnFinanceiro = new JMenu("Financeiro");
		mnFinanceiro.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		menuBar.add(mnFinanceiro);
		
		JMenuItem itmFinanceiro = new JMenuItem("Financeiro\r\n");
		itmFinanceiro.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/financeiro.png")));
		mnFinanceiro.add(itmFinanceiro);
		itmFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFinanceiro financeiro = new TelaFinanceiro();
				financeiro.setVisible(true);
				desktop.add(financeiro);
			}
		});
		
		
		JMenu mnLogout = new JMenu("Logout");
		mnLogout.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		menuBar.add(mnLogout);
		
		itmSair = new JMenuItem("Sair");
		itmSair.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/logout.png")));
		itmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Realmente deseja sair?", "Atenção!!!", JOptionPane.YES_NO_OPTION);
				if (sair==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		itmSair.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		mnLogout.add(itmSair);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktop = new JDesktopPane();
		desktop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		desktop.setBackground(UIManager.getColor("Button.background"));
		desktop.setBounds(10, 11, 1066, 575);
		contentPane.add(desktop);
		desktop.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblFunc = new JLabel("");
		lblFunc.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		lblFunc.setBounds(1097, 81, 136, 34);
		contentPane.add(lblFunc);
		
		JLabel lblNewLabel = new JLabel("Olá,");
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblNewLabel.setBounds(1097, 56, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ORDEMTECH");
		lblNewLabel_1.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblNewLabel_1.setBounds(1097, 11, 126, 34);
		contentPane.add(lblNewLabel_1);
	}
	public JMenuItem getItmFuncionarios() {
		return itmFuncionarios;
	}
	public JLabel getLblFunc() {
		return lblFunc;
	}
	public JMenuItem getItmClientes() {
		return itmClientes;
	}
	public JMenuItem getItmTecnicos() {
		return itmTecnicos;
	}
}
