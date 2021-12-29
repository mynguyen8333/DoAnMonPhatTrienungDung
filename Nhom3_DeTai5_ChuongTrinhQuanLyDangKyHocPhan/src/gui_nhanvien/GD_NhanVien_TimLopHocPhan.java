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
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.GiangVienDao;
import dao.HocKyDao;
import dao.KhoaDao;
import dao.LopHocPhanDao;
import dao.MonHocPhanDao;
import dao.NamDao;
import entity.LopHocPhan;
import entity.MonHocPhan;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class GD_NhanVien_TimLopHocPhan extends JFrame implements ActionListener{


	private JFrame frame;
	private JPanel pnlTong;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnTimKiem,btnTimKiem2,btnTimKiem3;
	private JComboBox<String> cmbTenGV,cmbTietHoc,cmbNgayHoc,cmbTenMon;
	private JComboBox<Integer>cmbHocKi;
	private JComboBox<String>cmbNam;
	private LopHocPhanDao dslhp = new LopHocPhanDao();
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_TimLopHocPhan window = new GD_NhanVien_TimLopHocPhan();
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
	public GD_NhanVien_TimLopHocPhan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);

		JLabel lblTieuDe = new JLabel("TÌM KIẾM LỚP HỌC PHẦN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(500, 33, 306, 28);
		pnlTong.add(lblTieuDe);

		JPanel pnlTimKiemTheoTen = new JPanel();
		pnlTimKiemTheoTen.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm theo t\u00EAn gi\u1EA3ng vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiemTheoTen.setBounds(55, 134, 1160, 60);
		pnlTong.add(pnlTimKiemTheoTen);
		pnlTimKiemTheoTen.setLayout(null);

		JLabel lblTenGV = new JLabel("Tên giảng viên:");
		lblTenGV.setBounds(100, 26, 114, 18);
		pnlTimKiemTheoTen.add(lblTenGV);
		lblTenGV.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cmbTenGV = new JComboBox<String>();
		cmbTenGV.setBounds(211, 24, 250, 22);
		pnlTimKiemTheoTen.add(cmbTenGV);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(650, 23, 104, 23);
		pnlTimKiemTheoTen.add(btnTimKiem);
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JPanel pnlTimTheoThoiGian = new JPanel();
		pnlTimTheoThoiGian.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm theo th\u1EDDi gian h\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimTheoThoiGian.setBounds(55, 215, 1160, 60);
		pnlTong.add(pnlTimTheoThoiGian);
		pnlTimTheoThoiGian.setLayout(null);

		JLabel lblTietHoc = new JLabel("Tiết học:");
		lblTietHoc.setBounds(100, 26, 114, 18);
		pnlTimTheoThoiGian.add(lblTietHoc);
		lblTietHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cmbTietHoc = new JComboBox<String>();
		cmbTietHoc.addItem("01-03");
		cmbTietHoc.addItem("04-06");
		cmbTietHoc.addItem("07-09");
		cmbTietHoc.addItem("10-12");
		cmbTietHoc.addItem("13-15");
		cmbTietHoc.setBounds(170, 24, 150, 22);
		pnlTimTheoThoiGian.add(cmbTietHoc);

		JLabel lblNgayHoc = new JLabel("Ngày học:");
		lblNgayHoc.setBounds(400, 26, 114, 18);
		pnlTimTheoThoiGian.add(lblNgayHoc);
		lblNgayHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cmbNgayHoc = new JComboBox<String>();
		cmbNgayHoc.setBounds(480, 24, 150, 22);
		cmbNgayHoc.addItem("Thứ 2");
		cmbNgayHoc.addItem("Thứ 3");
		cmbNgayHoc.addItem("Thứ 4");
		cmbNgayHoc.addItem("Thứ 5");
		cmbNgayHoc.addItem("Thứ 6");
		cmbNgayHoc.addItem("Thứ 7");
		cmbNgayHoc.addItem("Chủ nhật");
		pnlTimTheoThoiGian.add(cmbNgayHoc);

		btnTimKiem2 = new JButton("Tìm kiếm");
		btnTimKiem2.setBounds(650, 23, 104, 23);
		pnlTimTheoThoiGian.add(btnTimKiem2);
		btnTimKiem2.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JPanel pnlDuLieuBatBuoc = new JPanel();
		pnlDuLieuBatBuoc.setBorder(new TitledBorder(null, "D\u1EEF li\u1EC7u b\u1EAFt bu\u1ED9c ph\u1EA3i ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDuLieuBatBuoc.setBounds(55, 72, 1160, 60);
		pnlTong.add(pnlDuLieuBatBuoc);
		pnlDuLieuBatBuoc.setLayout(null);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setBounds(100, 26, 52, 18);
		pnlDuLieuBatBuoc.add(lblNam);
		lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cmbNam = new JComboBox<String>();
		cmbNam.setBounds(170, 23, 150, 22);
		pnlDuLieuBatBuoc.add(cmbNam);

		JLabel lblHocKy = new JLabel("Học Kỳ:");
		lblHocKy.setBounds(400, 24, 67, 18);
		pnlDuLieuBatBuoc.add(lblHocKy);
		lblHocKy.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cmbHocKi = new JComboBox<Integer>();
		cmbHocKi.setBounds(480, 23, 150, 22);
		pnlDuLieuBatBuoc.add(cmbHocKi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 405, 1160, 245);
		pnlTong.add(scrollPane);

		table = new JTable();
		String[] headers = "Mã lớp học phần;Sĩ số;Tên môn học;Năm;Học Kì".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table = new JTable(tableModel);


		scrollPane.setViewportView(table);

		JPanel pnlTimTheoTenMon = new JPanel();
		pnlTimTheoTenMon.setLayout(null);
		pnlTimTheoTenMon.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm theo t\u00EAn m\u00F4n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimTheoTenMon.setBounds(55, 298, 1160, 60);
		pnlTong.add(pnlTimTheoTenMon);

		JLabel lblTenMon = new JLabel("Tên môn:");
		lblTenMon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenMon.setBounds(100, 26, 114, 18);
		pnlTimTheoTenMon.add(lblTenMon);

		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setBounds(211, 24, 250, 22);
		pnlTimTheoTenMon.add(cmbTenMon);

		btnTimKiem3 = new JButton("Tìm kiếm");
		btnTimKiem3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnTimKiem3.setBounds(650, 23, 104, 23);
		pnlTimTheoTenMon.add(btnTimKiem3);

		JLabel label = new JLabel("");
		label.setBounds(55, 374, 48, 14);
		pnlTong.add(label);

		JLabel lblTieuDeBang = new JLabel("Danh sách lớp tìm được");
		lblTieuDeBang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTieuDeBang.setBounds(55, 377, 198, 20);
		pnlTong.add(lblTieuDeBang);

		btnTimKiem.addActionListener(this);
		btnTimKiem2.addActionListener(this);
		btnTimKiem3.addActionListener(this);

		DataBase.getInstance().connect();
		dulieuHocKy();
		dulieuNamHoc();
		dulieuGiangVien();
		dulieuTenMon();

		/*
		 * Thêm comboxfill cho comboxTenMon và comboxTenGiangVien
		 */
		cmbTenMon.setEditable(true);
		cmbTenMon.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		final JTextField textfield = (JTextField) cmbTenMon.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if(!textfield.getText().isEmpty()){
							comboBoxTenMon(textfield.getText());
						}
					}
				});

			}
		});

		cmbTenGV.setEditable(true);
		cmbTenGV.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		final JTextField textfield1 = (JTextField) cmbTenGV.getEditor().getEditorComponent();
		textfield1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if(!textfield1.getText().isEmpty()){
							comboBoxTenGV(textfield1.getText());
						}
					}
				});

			}
		});


	}

	/*
	 * Hàm comboxfill tên môn
	 */
	public void comboBoxTenMon(String enteredText) {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (!cmbTenMon.isPopupVisible()) {
			cmbTenMon.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenMon.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenMon.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
	
	/*
	 * Hàm comboxfill tên giảng viên
	 */
	public void comboBoxTenGV(String enteredText) {
		ArrayList<String> listTen = new GiangVienDao().getDsTenGV();
		if (!cmbTenGV.isPopupVisible()) {
			cmbTenGV.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenGV.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenGV.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}

	public JPanel getJPanel() {
		return pnlTong;
	}

	/*
	 * Dữ liệu cho các combobox
	 */
	public void dulieuHocKy() {
		ArrayList<Integer> listHocKy = new HocKyDao().getDSHocKy();
		if (listHocKy==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (int hk: listHocKy) {
				cmbHocKi.addItem(hk);
			}

		}
	}
	public void dulieuNamHoc() {
		ArrayList<String> listNam = new NamDao().getDSNAm();
		if (listNam==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String nam: listNam) {
				cmbNam.addItem(nam);
			}

		}
	}

	public void dulieuGiangVien() {
		ArrayList<String> listTen = new GiangVienDao().getDsTenGV();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenGV.addItem(ten);
			}

		}
	}

	public void dulieuTenMon() {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenMon.addItem(ten);
			}

		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTimKiem)) {
			tableModel.setRowCount(0);
			ArrayList<LopHocPhan> list = dslhp.TimTheoTenGV(cmbTenGV.getSelectedItem().toString(), 
					cmbNam.getSelectedItem().toString(),Integer.parseInt(cmbHocKi.getSelectedItem().toString()));
			if(list.size()!=0) {
				for (LopHocPhan lhp : list) {
					String tenMon = dsmh.LayTenMon(lhp.getMaMHP());
					String [] rowdata = {lhp.getMaLopHP(),lhp.getSiSo()+"",tenMon,lhp.getNam(),lhp.getHocKy()+""};
					tableModel.addRow(rowdata);
				}
				table.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
				cmbTenGV.setSelectedIndex(0);
			}
		}
		if(o.equals(btnTimKiem2)) {
			tableModel.setRowCount(0);
			ArrayList<LopHocPhan> list = dslhp.TimTheoThoiGian(cmbNgayHoc.getSelectedItem().toString(),
					cmbTietHoc.getSelectedItem().toString(),
					cmbNam.getSelectedItem().toString(),
					Integer.parseInt(cmbHocKi.getSelectedItem().toString()));
			if(list.size()!=0) {
				for (LopHocPhan lhp : list) {
					String tenMon = dsmh.LayTenMon(lhp.getMaMHP());
					String [] rowdata = {lhp.getMaLopHP(),lhp.getSiSo()+"",tenMon,lhp.getNam(),lhp.getHocKy()+""};
					tableModel.addRow(rowdata);
				}
				table.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
			}
		}
		if(o.equals(btnTimKiem3)) {
			tableModel.setRowCount(0);
			ArrayList<LopHocPhan> list = dslhp.TimtheoTenMon(cmbTenMon.getSelectedItem().toString(),
					cmbNam.getSelectedItem().toString(),Integer.parseInt(cmbHocKi.getSelectedItem().toString()));
			if(list.size()!=0) {
				for (LopHocPhan lhp : list) {
					String tenMon = dsmh.LayTenMon(lhp.getMaMHP());
					String [] rowdata = {lhp.getMaLopHP(),lhp.getSiSo()+"",tenMon,lhp.getNam(),lhp.getHocKy()+""};
					tableModel.addRow(rowdata);
				}
				table.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
				cmbTenMon.setSelectedIndex(0);
			}
		}


	}
}
