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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChuongTrinhKhungDao;
import dao.ChuyenNganhDao;
import dao.DataBase;
import dao.KhoaDao;
import dao.MonHocPhanDao;
import entity.ChuongTrinhKhung1;
import entity.ChuyenNganh;
import entity.GiangVien;
import entity.Khoa;

public class GD_NhanVien_XepChuongTrinhKhung extends JFrame implements ActionListener{
	

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JButton btnXoaTrang,btnXep,btnXoa;
	private JPanel pnlTong;
	private KhoaDao dhkh = new KhoaDao();
	private ChuyenNganhDao dscn = new ChuyenNganhDao();
	private DefaultTableModel tableModel;
	private JLabel lblHocKy;
	private JComboBox<Integer> cmbHocKy;
	private JComboBox<String> cmbTenCN,cmbTenMonHP;
	private ChuongTrinhKhungDao dsctk = new ChuongTrinhKhungDao();
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_XepChuongTrinhKhung window = new GD_NhanVien_XepChuongTrinhKhung();
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
	public GD_NhanVien_XepChuongTrinhKhung() {
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
		
		JLabel lblTieuDe = new JLabel("XẾP CHƯƠNG TRÌNH KHUNG");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(500, 23, 378, 34);
		pnlTong.add(lblTieuDe);
		
		JLabel lblTenChuyenNganh = new JLabel("Tên chuyên ngành:");
		lblTenChuyenNganh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenChuyenNganh.setBounds(100, 82, 160, 22);
		pnlTong.add(lblTenChuyenNganh);
		
		JLabel lblTenCN = new JLabel("Tên môn học phần:");
		lblTenCN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTenCN.setBounds(696, 82, 127, 22);
		pnlTong.add(lblTenCN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 249, 1089, 381);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Tên chuyên ngành; Tên môn; Học kỳ".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(87, 183, 1089, 55);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaTrang.setBounds(260, 11, 116, 33);
		pnlTacVu.add(btnXoaTrang);
		
		btnXep = new JButton("Xếp");
		btnXep.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXep.setBounds(460, 11, 116, 33);
		pnlTacVu.add(btnXep);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(660, 11, 116, 33);
		pnlTacVu.add(btnXoa);
		
		lblHocKy = new JLabel("Học Kỳ");
		lblHocKy.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHocKy.setBounds(100, 127, 119, 22);
		pnlTong.add(lblHocKy);
		
		cmbHocKy = new JComboBox<Integer>();
		cmbHocKy.addItem(1);
		cmbHocKy.addItem(2);
		cmbHocKy.addItem(3);
		cmbHocKy.addItem(4);
		cmbHocKy.addItem(5);
		cmbHocKy.addItem(6);
		cmbHocKy.addItem(7);
		cmbHocKy.addItem(8);
		cmbHocKy.setBounds(270, 127, 350, 22);
		pnlTong.add(cmbHocKy);
		
		cmbTenCN = new JComboBox<String>();
		cmbTenCN.setBounds(270, 82, 350, 22);
		pnlTong.add(cmbTenCN);
		
		cmbTenMonHP = new JComboBox<String>();
		cmbTenMonHP.setBounds(826, 82, 350, 22);
		pnlTong.add(cmbTenMonHP);
		
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXep.addActionListener(this);
		
		DataBase.getInstance().connect();
		dulieubang();
		dulieutenmon();
		dulieutenchuyennganh();
		
		cmbTenMonHP.setEditable(true);
		cmbTenMonHP.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		final JTextField textfield = (JTextField) cmbTenMonHP.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if(!textfield.getText().isEmpty()){
							comboBoxFilter1(textfield.getText());
						}
					}
				});

			}
		});
		
		
	}
	
	public void comboBoxFilter1(String enteredText) {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (!cmbTenMonHP.isPopupVisible()) {
			cmbTenMonHP.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenMonHP.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenMonHP.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
	/*
	 * Hàm lấy giao diện
	 */
	
	public JPanel getJPanel() {
		return pnlTong;
	}
	
	/*
	 * Thêm dữ liệu vào bảng
	 */
	public void dulieubang() {
		ArrayList<ChuongTrinhKhung1> list = dsctk.docTuBang();
		for (ChuongTrinhKhung1 ckh : list) {
			String tenMon = dsmh.LayTenMon(ckh.getMaMon());
			String tenCN = dscn.LayTenCN(ckh.getMaChuyenNganh());
			String [] rowdata = {tenCN,tenMon,ckh.getHocKy()+""};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
	public void dulieutenmon() {
		cmbTenMonHP.removeAllItems();
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenMonHP.addItem(ten);
			}
		}
	}
	
	public void dulieutenchuyennganh() {
		cmbTenCN.removeAllItems();
		ArrayList<String> listTen = new ChuyenNganhDao().LayDSTen();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenCN.addItem(ten);
			}
		}
	}
	
	/*
	 * Hàm
	 */
	
	public void them() {
		cmbTenCN.setSelectedIndex(0);
		cmbTenMonHP.setSelectedIndex(0);
		cmbHocKy.setSelectedIndex(0);
	}
	public boolean kiemtra(){
		String maChuyenNganh = dscn.LayMaChuyenNganh(cmbTenCN.getSelectedItem().toString());
		String maMon = dsmh.LayMaMon(cmbTenMonHP.getSelectedItem().toString());
		int tenMon = cmbTenMonHP.getSelectedIndex();
		if(tenMon<0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn tên môn");
			return false;
		}
		ArrayList<ChuongTrinhKhung1> list = dsctk.kiemtra(maChuyenNganh, maMon);
		if(list.size()>0) {
			JOptionPane.showMessageDialog(null, "Đã tồn tại");
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
		if(o.equals(btnXep)) {
			if(kiemtra()==true) {
				String maCN = dscn.LayMaChuyenNganh(cmbTenCN.getSelectedItem().toString().trim());
				String maMon = dsmh.LayMaMon(cmbTenMonHP.getSelectedItem().toString().trim());
				if(dsctk.ThemCTK(maCN, maMon, Integer.parseInt(cmbHocKy.getSelectedItem().toString()))) {
					Object[] datarow = {cmbTenCN.getSelectedItem(),cmbTenMonHP.getSelectedItem(),cmbHocKy.getSelectedItem()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}else {
					JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
				}
			}
		}
		if(o.equals(btnXoa)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maChuyenNganh = dscn.LayMaChuyenNganh((String)table_1.getValueAt(row, 0));
				String maMon = dsmh.LayMaMon((String)table_1.getValueAt(row, 1));
				System.out.println(maChuyenNganh+ maMon);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dsctk.xoaCTK(maChuyenNganh, maMon)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
					}
				}
			}
		}
		
	}
}
