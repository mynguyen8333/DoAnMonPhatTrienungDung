package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenNganhDao;
import dao.DataBase;
import dao.KhoaDao;
import entity.ChuyenNganh;
import entity.GiangVien;
import entity.Khoa;

public class GD_NhanVien_CapNhatChuyenNganh extends JFrame implements ActionListener,MouseListener{
	

	private JFrame frame;
	private JTextField txtmaCN;
	private JTextField txtTenCN;
	private JTable table;
	private JTable table_1;
	private JButton btnXoaTrang,btnThemCN,btnXoa;
	private JPanel pnlTong;
	private KhoaDao dhkh = new KhoaDao();
	private ChuyenNganhDao dscn = new ChuyenNganhDao();
	private DefaultTableModel tableModel;
	private JLabel lblTenKhoa;
	private JComboBox<String> cmbTenKhoa;
	private JButton btnCapNhat;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatChuyenNganh window = new GD_NhanVien_CapNhatChuyenNganh();
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
	public GD_NhanVien_CapNhatChuyenNganh() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);
		
		JLabel lblTieuDe = new JLabel("C??P NH???T TH??NG TIN CHUY??N NG??NH");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(461, 22, 378, 34);
		pnlTong.add(lblTieuDe);
		
		JLabel lblMaCN = new JLabel("M?? chuy??n ng??nh:");
		lblMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaCN.setBounds(100, 84, 136, 17);
		pnlTong.add(lblMaCN);
		
		txtmaCN = new JTextField();
		txtmaCN.setBounds(245, 83, 350, 20);
		pnlTong.add(txtmaCN);
		txtmaCN.setColumns(10);
		
		JLabel lblTenCN = new JLabel("T??n chuy??n ng??nh:");
		lblTenCN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenCN.setBounds(696, 84, 135, 17);
		pnlTong.add(lblTenCN);
		
		txtTenCN = new JTextField();
		txtTenCN.setColumns(10);
		txtTenCN.setBounds(820, 83, 350, 20);
		pnlTong.add(txtTenCN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 249, 1128, 379);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "T??n khoa;M?? chuy??n ng??nh;T??n chuy??n ng??nh".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(76, 183, 1128, 55);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnXoaTrang = new JButton("X??a tr???ng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnXoaTrang.setBounds(160, 11, 116, 33);
		pnlTacVu.add(btnXoaTrang);
		
		btnThemCN = new JButton("Th??m ");
		btnThemCN.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemCN.setBounds(360, 11, 116, 33);
		pnlTacVu.add(btnThemCN);
		
		btnXoa = new JButton("X??a");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(560, 11, 116, 33);
		pnlTacVu.add(btnXoa);
		
		btnCapNhat = new JButton("C???p nh???t");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(760, 11, 116, 33);
		pnlTacVu.add(btnCapNhat);
		
		lblTenKhoa = new JLabel("T??n khoa:");
		lblTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenKhoa.setBounds(100, 127, 119, 17);
		pnlTong.add(lblTenKhoa);
		
		cmbTenKhoa = new JComboBox<String>();
		cmbTenKhoa.setBounds(245, 125, 350, 22);
		pnlTong.add(cmbTenKhoa);
		
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThemCN.addActionListener(this);
		btnCapNhat.addActionListener(this);
		table_1.addMouseListener(this);
		
		DataBase.getInstance().connect();
		dulieubang();
		cbBoxTenKhoa();
		
		cmbTenKhoa.setEditable(true);
		cmbTenKhoa.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		final JTextField textfield1 = (JTextField) cmbTenKhoa.getEditor().getEditorComponent();
		textfield1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if(!textfield1.getText().isEmpty()){
							comboBoxTenKhoa(textfield1.getText());	
						}
					}
				});

			}
		});
	}
	
	public void comboBoxTenKhoa(String enteredText) {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		if (!cmbTenKhoa.isPopupVisible()) {
			cmbTenKhoa.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenKhoa.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenKhoa.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
	/*
	 * H??m l???y giao di???n
	 */
	
	public JPanel getJPanel() {
		return pnlTong;
	}
	
	/*
	 * Th??m d??? li???u v??o b???ng
	 */
	public void dulieubang() {
		ArrayList<ChuyenNganh> list = dscn.docTuBang();
		for (ChuyenNganh cn : list) {
			String tenKhoa = dhkh.LayTenKhoa(cn.getMaKhoa());
			String [] rowdata = {tenKhoa,cn.getMaChuyenNganh(),cn.getTenChuyenNganh()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
	public void cbBoxTenKhoa() {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "L???i k???t n???i");
		} else {
			for (String ten: listTen) {
				cmbTenKhoa.addItem(ten);
			}

		}
	}
	
	/*
	 * H??m
	 */
	
	public void them() {
		txtmaCN.setText("");
		txtTenCN.setText("");
		txtmaCN.setEditable(true);
		cmbTenKhoa.setSelectedIndex(0);
	}
	public boolean kiemtra(){
		String maCN = txtmaCN.getText().trim();
		String tenCN = txtTenCN.getText().trim();
		int tenKhoa = cmbTenKhoa.getSelectedIndex();
		String maKhoa = dhkh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
		
		if(tenKhoa<0) {
			JOptionPane.showMessageDialog(null,"Ch??a ch???n t??n khoa");
			return false;
		}
		
		if(maKhoa==null) {
			JOptionPane.showMessageDialog(null,"Ch??a ch???n t??n khoa");
			return false;
		}
		if(!(maCN.length()>0)) {
			JOptionPane.showMessageDialog(null, "M?? chuy??n ng??nh kh??ng ???????c ????? tr???ng");
			txtmaCN.selectAll();
			txtmaCN.requestFocus();
			return false;
		}

		if(!(tenCN.length()>0)) {
			JOptionPane.showMessageDialog(null, "T??n chuy??n ng??nh kh??ng ???????c ????? tr???ng");
			txtTenCN.selectAll();
			txtTenCN.requestFocus();
			return false;
		}
		

		return true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			them();
		}
		if(o.equals(btnThemCN)) {
			if(kiemtra()==true) {
				String maKhoa = dhkh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
				if(dscn.ThemChuyenNganh(txtmaCN.getText().trim(),txtTenCN.getText().trim(), maKhoa)) {
					Object[] datarow = {cmbTenKhoa.getSelectedItem(),txtmaCN.getText(),txtTenCN.getText()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng chuy??n ng??nh");
					them();
				}else {
					JOptionPane.showMessageDialog(null, "L???i nh???p li???u");
				}
			}
		}
		if(o.equals(btnXoa)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maChuyenNganh = (String)table_1.getValueAt(row, 1);
				int hoinhac = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c","Ch?? ??",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dscn.xoaChuyenNganh(maChuyenNganh)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "X??a Th??nh c??ng");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Ch??a x??a ch????ng tr??nh khung c???a chuy??n ng??nh");
					}
				}
			}
		}
		if(o.equals(btnCapNhat)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"B???n c?? ch???c kh??ng","Ch?? ??",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					String maKhoa = dhkh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
					if(dscn.capNhatChuyenNganh(txtmaCN.getText(), txtTenCN.getText(), maKhoa)) {
						table_1.setValueAt(cmbTenKhoa.getSelectedItem(), row,0);
						table_1.setValueAt(txtmaCN.getText(), row,1);
						table_1.setValueAt(txtTenCN.getText(), row,2);
						them();
						JOptionPane.showMessageDialog(null,"S???a th??nh c??ng");
					}
					else {
						JOptionPane.showMessageDialog(null, "S???a th???t b???i");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"B???n ch??a ch???n chuy??n ng??nh");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtmaCN.setText(table_1.getValueAt(row, 1).toString());
		txtmaCN.setEditable(false);
		txtTenCN.setText(table_1.getValueAt(row, 2).toString());
		cmbTenKhoa.setSelectedItem(table_1.getValueAt(row, 0).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
