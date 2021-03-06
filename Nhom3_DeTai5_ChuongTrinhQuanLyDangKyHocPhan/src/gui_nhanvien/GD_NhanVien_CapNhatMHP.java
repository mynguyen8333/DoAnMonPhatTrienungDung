package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.KhoaDao;
import dao.MonHocPhanDao;
import entity.MonHocPhan;

import javax.swing.border.TitledBorder;

public class GD_NhanVien_CapNhatMHP extends JFrame implements ActionListener,MouseListener{


	private JFrame frame;
	private JPanel pnlTong;
	private JTextField txtMaMon;
	private JTextField txtTenMon;
	private JTable table;
	private JTable table_1;
	private JButton btnXoaRong,btnThem,btnXoa;
	private DefaultTableModel tableModel;
	private JComboBox<String>cmbHinhThucThi, cmbBatBuoc,cmbTenKhoa,cmbTenMonYeuCau,cmbSoTinChi;
	private KhoaDao kh = new KhoaDao();
	private MonHocPhanDao dsMHP = new MonHocPhanDao();
	private GD_NhanVien_CapNhatLHP gdCNLHP;
	private JButton btnCapNhat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatMHP window = new GD_NhanVien_CapNhatMHP();
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
	public GD_NhanVien_CapNhatMHP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);

		JLabel lblNewLabel = new JLabel("C???P NH???T M??N H???C PH???N");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(519, 22, 309, 24);
		pnlTong.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("M?? m??n h???c:\r\n");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(100, 61, 100, 14);
		pnlTong.add(lblNewLabel_1);

		txtMaMon = new JTextField();
		DataBase.getInstance().connect();
		int value = dsMHP.LayMaTuDong()+1;
		String s1 = String.valueOf(value);
		int length = s1.length();

		if(length==1) {
			txtMaMon.setText("MHP0" + s1);
		}
		if(length>=2) {
			txtMaMon.setText("MHP" + s1);
		}
		txtMaMon.setBounds(245, 61, 350, 20);
		txtMaMon.setEditable(false);
		pnlTong.add(txtMaMon);
		txtMaMon.setColumns(10);

		JLabel lblTenMon = new JLabel("T??n m??n h???c:\r\n");
		lblTenMon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenMon.setBounds(696, 61, 100, 14);
		pnlTong.add(lblTenMon);

		txtTenMon = new JTextField();
		txtTenMon.setColumns(10);
		txtTenMon.setBounds(803, 59, 350, 20);
		pnlTong.add(txtTenMon);

		JLabel lblTinChi = new JLabel("S??? t??n ch???:\r\n");
		lblTinChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTinChi.setBounds(100, 96, 100, 14);
		pnlTong.add(lblTinChi);

		JLabel lblHinhThuc = new JLabel("H??nh th???c thi:\r\n");
		lblHinhThuc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHinhThuc.setBounds(696, 96, 100, 14);
		pnlTong.add(lblHinhThuc);

		cmbHinhThucThi = new JComboBox<String>();
		cmbHinhThucThi.addItem("L?? thuy???t");
		cmbHinhThucThi.addItem("Th???c h??nh");
		cmbHinhThucThi.addItem("B??o c??o");
		cmbHinhThucThi.setBounds(803, 93, 350, 22);
		pnlTong.add(cmbHinhThucThi);

		JLabel lblBatBuoc = new JLabel("B???t Bu???c:\r\n");
		lblBatBuoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblBatBuoc.setBounds(100, 129, 100, 14);
		pnlTong.add(lblBatBuoc);

		cmbBatBuoc = new JComboBox<String>();
		cmbBatBuoc.addItem("C??");
		cmbBatBuoc.addItem("Kh??ng");
		cmbBatBuoc.setBounds(245, 129, 350, 22);
		pnlTong.add(cmbBatBuoc);

		JLabel lblTenKhoa = new JLabel("T??n Khoa:");
		lblTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenKhoa.setBounds(696, 132, 100, 14);
		pnlTong.add(lblTenKhoa);

		cmbTenKhoa = new JComboBox<String>();
		cmbTenKhoa.setBounds(803, 129, 350, 22);
		pnlTong.add(cmbTenKhoa);

		JLabel lblTenMonYeuCau = new JLabel("T??n m??n y??u c???u:\r\n");
		lblTenMonYeuCau.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenMonYeuCau.setBounds(100, 162, 121, 24);
		pnlTong.add(lblTenMonYeuCau);

		cmbTenMonYeuCau = new JComboBox<String>();
		cmbTenMonYeuCau.setBounds(245, 162, 350, 22);
		cmbTenMonYeuCau.addItem("Kh??ng");
		pnlTong.add(cmbTenMonYeuCau);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 290, 1113, 346);
		pnlTong.add(scrollPane);

		table = new JTable();		
		String[] headers = "M?? m??n h???c ph???n;T??n m??n h???c;S??? t??n ch???;H??nh th???c thi;B???t bu???c;M?? khoa;H???c ph???n y??u c???u".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		scrollPane.setViewportView(table_1);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(76, 217, 1113, 62);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);

		btnXoaRong = new JButton("X??a r???ng");
		btnXoaRong.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaRong.setBounds(150, 11, 116, 40);
		pnlTacVu.add(btnXoaRong);

		btnThem = new JButton("Th??m");
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThem.setBounds(350, 11, 116, 40);
		pnlTacVu.add(btnThem);

		btnXoa = new JButton("X??a");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(550, 11, 116, 40);
		pnlTacVu.add(btnXoa);

		btnCapNhat = new JButton("C???p Nh???t");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(750, 11, 116, 40);
		pnlTacVu.add(btnCapNhat);

		cmbSoTinChi = new JComboBox<String>();
		cmbSoTinChi.addItem("2");
		cmbSoTinChi.addItem("3");
		cmbSoTinChi.addItem("4");
		cmbSoTinChi.addItem("5");
		cmbSoTinChi.setBounds(245, 96, 350, 22);
		pnlTong.add(cmbSoTinChi);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnCapNhat.addActionListener(this);
		table_1.addMouseListener(this);



		cbBoxTenKhoa();
		cbBoxTenMonYeuCau();
		updatetable();		
		
		
		//Jcombox filler
		cmbTenMonYeuCau.setEditable(true);
		cmbTenMonYeuCau.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		final JTextField textfield = (JTextField) cmbTenMonYeuCau.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if(!textfield.getText().isEmpty()){
							comboBoxTenMonYC(textfield.getText());
						}
					}
				});

			}
		});

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

	public void comboBoxTenMonYC(String enteredText) {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (!cmbTenMonYeuCau.isPopupVisible()) {
			cmbTenMonYeuCau.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenMonYeuCau.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenMonYeuCau.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}

	public JPanel getJPanel() {
		return pnlTong;
	}


	/*
	 * L???c d??? li???u l??n
	 */

	/*
	 * D??? li???u c???a combobox
	 */

	public void cbBoxTenKhoa() {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Loi ket noi");
		} else {
			for (String ten: listTen) {
				cmbTenKhoa.addItem(ten);
			}

		}
	}

	public void cbBoxTenMonYeuCau() {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "L???i k???t n???i");
		} else {
			for (String ten: listTen) {
				cmbTenMonYeuCau.addItem(ten);
			}

		}
	}
	/*
	 * Th??m d??? li???u v??o b???ng
	 */
	public void updatetable() {
		ArrayList<MonHocPhan> list = dsMHP.doctubang();
		for (MonHocPhan mh : list) {
			String tenKhoa = kh.LayTenKhoa(mh.getMaKhoa());
			String tenMonYC = dsMHP.LayTenMon(mh.getHocPhanYeuCau()); 
			String [] rowdata = {mh.getMaMonHocPhan(),mh.getTenMonHocPhan(),
					mh.getSoTinChi()+"",mh.getHinhThucThi(),mh.getBatBuoc(),tenKhoa,
					tenMonYC};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);

	}

	/*
	 * Ki???m tra d??? li???u
	 */

	public void them() {
		int value = dsMHP.LayMaTuDong()+1;
		String s1 = String.valueOf(value);
		int length = s1.length();

		if(length==1) {
			txtMaMon.setText("MHP0" + s1);
		}
		if(length>=2) {
			txtMaMon.setText("MHP" + s1);
		}
		txtTenMon.setText("");
		cmbBatBuoc.setSelectedIndex(0);
		cmbHinhThucThi.setSelectedIndex(0);
		cmbSoTinChi.setSelectedIndex(0);
		cmbTenKhoa.setSelectedIndex(0);
		cmbTenMonYeuCau.setSelectedIndex(0);		

	}
	public boolean kiemtra() {

		String maMH = txtMaMon.getText().trim();
		String ten = txtTenMon.getText().trim();
		MonHocPhan kt = dsMHP.KiemTraTenMon(ten);
		int tenKhoa = cmbTenKhoa.getSelectedIndex();
		String maKhoa = kh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
		if(!(maMH.length()>0 && maMH.matches("^MHP[0-9]{2}$"))) {
			JOptionPane.showMessageDialog(null,"M?? m??n kh??ng ???????c r???ng v?? theo c???u tr??c ^MHP[0-9]{2}$");
			txtMaMon.selectAll();
			txtMaMon.requestFocus();
			return false;
		}
		if(!(ten.length()>0)) {
			JOptionPane.showMessageDialog(null, "Nh???p sai t??n m??n");
			txtTenMon.selectAll();
			txtTenMon.requestFocus();
			return false;
		}
		if(kt!=null) {
			JOptionPane.showMessageDialog(null, "T??n m??n h???c ph???n ???? t???n t???i");
			return false;
		}
		if(tenKhoa<0) {
			JOptionPane.showMessageDialog(null, "Ch??a ch???n t??n khoa");
			return false;
		}
		if(maKhoa==null) {
			JOptionPane.showMessageDialog(null, "Ch??a ch???n t??n khoa");
			return false;
		}
		return true;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(btnThem)) {
			if(kiemtra()==true) {
				String maKhoa = kh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString());
				String maMon = dsMHP.LayMaMon(cmbTenMonYeuCau.getSelectedItem().toString());
				if(dsMHP.ThemMHP(txtMaMon.getText().trim(),txtTenMon.getText().trim(),
						Integer.parseInt(cmbSoTinChi.getSelectedItem().toString()),
						cmbHinhThucThi.getSelectedItem().toString(), 
						cmbBatBuoc.getSelectedItem().toString(), 
						maKhoa,maMon)) {
					Object[] datarow = {txtMaMon.getText(),
							txtTenMon.getText(),cmbSoTinChi.getSelectedItem(),
							cmbHinhThucThi.getSelectedItem(),
							cmbBatBuoc.getSelectedItem(),
							cmbTenKhoa.getSelectedItem(),cmbTenMonYeuCau.getSelectedItem()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
					cmbTenMonYeuCau.addItem(txtTenMon.getText().toString());
					them();
				}else {
					JOptionPane.showMessageDialog(null, "Th???t b???i");
				}
			}

		}
		if(o.equals(btnXoaRong)) {
			them();
		}
		if(o.equals(btnXoa)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maMHP = (String)table_1.getValueAt(row, 0);
				int hoinhac = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c","Ch?? ??",JOptionPane.YES_NO_OPTION);
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dsMHP.xoaMHP(maMHP)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "X??a th??nh c??ng");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Ch??a x??a c??c l???p h???c ph???n c???a m??n h???c n??y");
					}
				}
			}

		}
		if(o.equals(btnCapNhat)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"B???n c?? ch???c kh??ng","Ch?? ??",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					String maKhoa = kh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString());
					String maMon = dsMHP.LayMaMon(cmbTenMonYeuCau.getSelectedItem().toString());
					if(dsMHP.capNhatMonHocPhan(txtMaMon.getText(),txtTenMon.getText().trim(),
							Integer.parseInt(cmbSoTinChi.getSelectedItem().toString()),
							cmbHinhThucThi.getSelectedItem().toString(),
							cmbBatBuoc.getSelectedItem().toString(),
							maKhoa,maMon)){
						table_1.setValueAt(txtMaMon.getText(), row,0);
						table_1.setValueAt(txtTenMon.getText(), row,1);
						table_1.setValueAt(cmbSoTinChi.getSelectedItem().toString(), row,2);
						table_1.setValueAt(cmbHinhThucThi.getSelectedItem(), row,3);
						table_1.setValueAt(cmbBatBuoc.getSelectedItem(), row,4);
						table_1.setValueAt(cmbTenKhoa.getSelectedItem(), row,5);
						table_1.setValueAt(cmbTenMonYeuCau.getSelectedItem(), row,6);
						them();
						JOptionPane.showMessageDialog(null,"S???a th??nh c??ng");
					}
					else {
						JOptionPane.showMessageDialog(null, "S???a th???t b???i");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"B???n ch??a ch???n m??n h???c ph???n");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtMaMon.setText(table_1.getValueAt(row, 0).toString());
		txtTenMon.setText(table_1.getValueAt(row, 1).toString());
		cmbSoTinChi.setSelectedItem(table_1.getValueAt(row, 2).toString());
		cmbHinhThucThi.setSelectedItem(table_1.getValueAt(row, 3).toString());
		cmbBatBuoc.setSelectedItem(table_1.getValueAt(row, 4).toString());
		cmbTenKhoa.setSelectedItem(table_1.getValueAt(row, 5).toString());
		if((table_1.getValueAt(row, 6))==null){
			cmbTenMonYeuCau.setSelectedItem("Kh??ng");
		}else {
			cmbTenMonYeuCau.setSelectedItem(table_1.getValueAt(row, 6).toString());
		}
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
