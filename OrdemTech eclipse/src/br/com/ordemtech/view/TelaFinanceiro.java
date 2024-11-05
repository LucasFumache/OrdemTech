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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFinanceiro extends JInternalFrame {
    
    DAO dao = new DAO();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private static final long serialVersionUID = 1L;
    private JTextField cliName;
    private JTextField IdCli;
    private JTable tblCli;
    private JTable tblOs;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JComboBox cmbSitu;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaFinanceiro frame = new TelaFinanceiro();
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
    public TelaFinanceiro() {
        setTitle("Ordem de serviço");
        con = dao.conectar();
        setIconifiable(true);
        setClosable(true);
        setMaximizable(true);
        setBounds(100, 100, 1065, 575);
        getContentPane().setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cliente", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(24, 11, 532, 169);
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
        
        JLabel lblNewLabel_3 = new JLabel("Id:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(372, 22, 49, 14);
        panel_1.add(lblNewLabel_3);
        
        IdCli = new JTextField();
        IdCli.setFont(new Font("Tahoma", Font.BOLD, 12));
        IdCli.setSelectionColor(Color.BLACK);
        IdCli.setEnabled(false);
        IdCli.setBounds(372, 43, 80, 20);
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
        tblCli.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            },
            new String[] {
                "New column", "New column", "New column"
            }
        ));
        tblCli.setBounds(10, 84, 504, 74);
        panel_1.add(tblCli);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ordens de serviço", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(24, 191, 991, 343);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);
        
        tblOs = new JTable();
        tblOs.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
            },
            new String[] {
                "New column", "New column", "New column", "New column"
            }
        ));
        tblOs.setBounds(10, 27, 951, 305);
        panel_2.add(tblOs);
        
        JLabel lblNewLabel_6 = new JLabel("Situação:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(617, 11, 68, 29);
        getContentPane().add(lblNewLabel_6);
        
        cmbSitu = new JComboBox();
        cmbSitu.setFont(new Font("Tahoma", Font.BOLD, 15));
        cmbSitu.setModel(new DefaultComboBoxModel(new String[] {"Aguardando pagamento!", "Esperando liberação!", "Já entregue!", "Já pago"}));
        cmbSitu.setBounds(617, 51, 233, 29);
        getContentPane().add(cmbSitu);

      
        cmbSitu.setSelectedIndex(0); 
        buscar_os_nome(); 

        
        cmbSitu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscar_os_nome(); 
            }
        });
    }

    private void buscar_cliente() {
        String lerbd = "select id, nome, CPF from clientes where nome like ?";
        try {
            pst = con.prepareStatement(lerbd);
            pst.setString(1, cliName.getText()+"%");
            rs = pst.executeQuery();
            tblCli.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void inserir_campos_cliente() {
        int inserir = tblCli.getSelectedRow();
        IdCli.setText(tblCli.getModel().getValueAt(inserir, 0).toString());
        buscar_os_nome();
    }

    private void buscar_os_nome() {
        String sql = "select ordem_de_servico.valortotal, ordem_de_servico.situacao, clientes.nome, clientes.telefone from ordem_de_servico join clientes on clientes.id = ordem_de_servico.id_cliente where clientes.id like ? and ordem_de_servico.situacao like ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, IdCli.getText()+"%");
            pst.setString(2, cmbSitu.getSelectedItem().toString()+"%");
            rs = pst.executeQuery();
            tblOs.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void limpar_campos() {
        IdCli.setText(null);
        cliName.setText(null);
    }
}
