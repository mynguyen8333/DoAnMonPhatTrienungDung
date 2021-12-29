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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietLopHocPhanDao;
import dao.DataBase;
import dao.GiangVienDao;
import dao.HocKyDao;
import dao.MonHocPhanDao;
import dao.NamDao;
import dao.NhomDao;
import dao.PhongHocDao;
import entity.ChiTietLopHocPhan;
import entity.ChiTietLopHocPhan1;
import entity.LopHocPhan;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GD_NhanVien_XepLich implements ActionListener,MouseListener{


	private JFrame frame;
	private JPanel pnlTong;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private JComboBox<Integer> cmbHocKy;
	private JComboBox<String> cmbTenMon,cmbMaLop,cmbMaNhom,cmbTietHoc,
	cmbNgayHoc,cmbPhongHoc,cmbTenGV,cmbMaGV,cmbNamHoc;
	private JButton btnXoaRong,btnXepLich,btnXoaLich,btnLoc,btnLoc2,btnLoc3;
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	private GiangVienDao dsgv = new GiangVienDao();
	private ChiTietLopHocPhanDao dsct = new ChiTietLopHocPhanDao();
	private JDateChooser txtNgayBatDau;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_XepLich window = new GD_NhanVien_XepLich();
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
	public GD_NhanVien_XepLich() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,  1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);

		JLabel lblTieuDe = new JLabel("XẾP LỊCH HỌC");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(500, 21, 150, 26);
		pnlTong.add(lblTieuDe);

		JLabel lblChonHocKi = new JLabel("Chọn học kì:");
		lblChonHocKi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblChonHocKi.setBounds(100, 60, 85, 26);
		pnlTong.add(lblChonHocKi);

		cmbHocKy = new JComboBox<Integer>();
		cmbHocKy.setBounds(220, 60, 260, 22);
		pnlTong.add(cmbHocKy);

		JLabel lblChonNam = new JLabel("Chọn năm:");
		lblChonNam.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblChonNam.setBounds(656, 60, 85, 23);
		pnlTong.add(lblChonNam);

		cmbNamHoc = new JComboBox<String>();
		cmbNamHoc.setBounds(776, 60, 260, 22);
		pnlTong.add(cmbNamHoc);

		btnLoc = new JButton("Lấy tên môn");
		btnLoc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLoc.setBounds(1065, 60, 114, 23);
		pnlTong.add(btnLoc);

		JLabel lblTenMon = new JLabel("Tên môn:");
		lblTenMon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenMon.setBounds(100, 95, 85, 14);
		pnlTong.add(lblTenMon);

		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setBounds(220, 95, 260, 22);
		pnlTong.add(cmbTenMon);

		btnLoc2 = new JButton("Lấy mã lớp\r\n");
		btnLoc2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLoc2.setBounds(490, 100, 107, 23);
		pnlTong.add(btnLoc2);

		JLabel lblMaLop = new JLabel("Mã lớp học phần:");
		lblMaLop.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaLop.setBounds(656, 95, 134, 26);
		pnlTong.add(lblMaLop);

		cmbMaLop = new JComboBox<String>();
		cmbMaLop.setBounds(776, 99, 260, 22);
		pnlTong.add(cmbMaLop);

		JLabel lblMaNhom = new JLabel("Nhóm:");
		lblMaNhom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaNhom.setBounds(100, 130, 85, 14);
		pnlTong.add(lblMaNhom);

		cmbMaNhom = new JComboBox<String>();
		cmbMaNhom.setBounds(220, 130, 260, 22);
		pnlTong.add(cmbMaNhom);

		JLabel lblTietHoc = new JLabel("Tiết học:");
		lblTietHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTietHoc.setBounds(656, 130, 85, 14);
		pnlTong.add(lblTietHoc);

		cmbTietHoc = new JComboBox<String>();
		cmbTietHoc.addItem("01-03");
		cmbTietHoc.addItem("04-06");
		cmbTietHoc.addItem("07-09");
		cmbTietHoc.addItem("10-12");
		cmbTietHoc.addItem("13-15");
		cmbTietHoc.setBounds(776, 132, 260, 22);
		pnlTong.add(cmbTietHoc);

		JLabel lblNgayHoc = new JLabel("Ngày học:");
		lblNgayHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgayHoc.setBounds(100, 165, 85, 20);
		pnlTong.add(lblNgayHoc);

		cmbNgayHoc = new JComboBox<String>();
		cmbNgayHoc.addItem("Thứ 2");
		cmbNgayHoc.addItem("Thứ 3");
		cmbNgayHoc.addItem("Thứ 4");
		cmbNgayHoc.addItem("Thứ 5");
		cmbNgayHoc.addItem("Thứ 6");
		cmbNgayHoc.addItem("Thứ 7");
		cmbNgayHoc.addItem("Chủ nhật");
		cmbNgayHoc.setBounds(220, 165, 260, 22);
		pnlTong.add(cmbNgayHoc);

		JLabel lblPhongHoc = new JLabel("Phòng học:");
		lblPhongHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPhongHoc.setBounds(656, 165, 85, 20);
		pnlTong.add(lblPhongHoc);

		cmbPhongHoc = new JComboBox<String>();
		cmbPhongHoc.setBounds(776, 165, 260, 22);
		pnlTong.add(cmbPhongHoc);

		JLabel lblTnGingVin = new JLabel("Tên giảng viên:");
		lblTnGingVin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTnGingVin.setBounds(100, 200, 102, 20);
		pnlTong.add(lblTnGingVin);

		cmbTenGV = new JComboBox<String>();
		cmbTenGV.setBounds(220, 200, 260, 22);
		pnlTong.add(cmbTenGV);

		btnLoc3 = new JButton("Lấy mã giảng viên\r\n");
		btnLoc3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLoc3.setBounds(490, 200, 141, 23);
		pnlTong.add(btnLoc3);

		JLabel lblMaGV = new JLabel("Mã giảng viên:");
		lblMaGV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaGV.setBounds(656, 200, 134, 26);
		pnlTong.add(lblMaGV);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(76, 273, 1129, 53);
		pnlTong.add(panel);
		panel.setLayout(null);

		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaRong.setBounds(260, 11, 116, 29);
		panel.add(btnXoaRong);

		btnXepLich = new JButton("Xếp lịch");
		btnXepLich.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXepLich.setBounds(460, 11, 116, 29);
		panel.add(btnXepLich);

		btnXoaLich = new JButton("Xóa lịch");
		btnXoaLich.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaLich.setBounds(660, 11, 116, 29);
		panel.add(btnXoaLich);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 335, 1129, 315);
		pnlTong.add(scrollPane);

		table = new JTable();
		//String[] headers = "Mã lớp học phần;Nhóm;Tiết học;Ngày học;Phòng học;Mã giảng viên;Ngày bắt đầu".split(";");
		String[] headers = "Tên môn học;Mã lớp học phần;Mã nhóm;Tiết học;Ngày học;Phòng học;Tên giảng viên;Ngày bắt đầu".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);

		cmbMaGV = new JComboBox<String>();
		cmbMaGV.setBounds(776, 198, 260, 22);
		pnlTong.add(cmbMaGV);
		
		JLabel lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgayBD.setBounds(100, 235, 102, 20);
		pnlTong.add(lblNgayBD);
		
		txtNgayBatDau = new JDateChooser();
		txtNgayBatDau.setBounds(220, 235, 260, 20);
		pnlTong.add(txtNgayBatDau);

		btnLoc.addActionListener(this);
		btnLoc2.addActionListener(this);
		btnLoc3.addActionListener(this);
		btnXepLich.addActionListener(this);
		btnXoaLich.addActionListener(this);
		btnXoaRong.addActionListener(this);

		DataBase.getInstance().connect();
		dulieuHocKy();
		dulieuNamHoc();
		dulieuPhongHoc();
		dulieuGiangVien();
		dulieuNhom();
	}
	public JPanel getJPanel() {
		return pnlTong;
	}
	/*
	 * 
	 */
	public void dulieuHocKy() {
		ArrayList<Integer> listHocKy = new HocKyDao().getDSHocKy();
		if (listHocKy==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (int hk: listHocKy) {
				cmbHocKy.addItem(hk);
			}

		}
	}
	public void dulieuNamHoc() {
		ArrayList<String> listNam = new NamDao().getDSNAm();
		if (listNam==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String nam: listNam) {
				cmbNamHoc.addItem(nam);
			}

		}
	}

	public void dulieuPhongHoc() {
		ArrayList<String> listPhong = new PhongHocDao().getDsPH();
		if (listPhong==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String phong: listPhong) {
				cmbPhongHoc.addItem(phong);
			}

		}
	}

	public void dulieuGiangVien() {
		cmbTenGV.removeAllItems();
		ArrayList<String> listTen = new GiangVienDao().getDsTenGV();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenGV.addItem(ten);
			}

		}
	}

	public void dulieuNhom() {
		ArrayList<String> listNhom = new NhomDao().getDsNhom();
		if (listNhom==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String nhom: listNhom) {
				cmbMaNhom.addItem(nhom);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	/*
	 * Kiem tra
	 */

	public void them() {
		cmbHocKy.setSelectedIndex(0);
		cmbNamHoc.setSelectedIndex(0);
		cmbTenMon.removeAllItems();
		cmbMaLop.removeAllItems();
		cmbMaNhom.setSelectedIndex(0);
		cmbTietHoc.setSelectedIndex(0);
		cmbNgayHoc.setSelectedIndex(0);
		cmbPhongHoc.setSelectedIndex(0);
		cmbTenGV.setSelectedIndex(0);
		cmbMaGV.removeAllItems();
		((JTextField)txtNgayBatDau.getDateEditor().getUiComponent()).setText("");

	}
	public boolean kiemtra() {
		int maLop = cmbMaLop.getSelectedIndex();
		int maGV = cmbMaGV.getSelectedIndex();
		Date d1 = txtNgayBatDau.getDate();
		if(d1==null) {
			JOptionPane.showMessageDialog(null, "Chưa chọn ngày bắt đầu");
			return false;
		}
		if(maLop<0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn mã lớp");
			return false;
		}
		if(maGV<0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn mã giảng viên");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(btnXoaRong)) {
			them();
		}
		if(o.equals(btnLoc)) {
			cmbTenMon.removeAllItems();
			ArrayList<String> list = dsmh.LayTenMHPTheoKi(cmbNamHoc.getSelectedItem().toString(),
					Integer.parseInt(cmbHocKy.getSelectedItem().toString()));
			for (String ten : list) {
				cmbTenMon.addItem(ten);
			}
			tableModel.setRowCount(0);
			ArrayList<ChiTietLopHocPhan1> listct = dsct.docTuBang((cmbNamHoc.getSelectedItem().toString()),
					Integer.parseInt(cmbHocKy.getSelectedItem().toString()));

			for (ChiTietLopHocPhan1 ct : listct) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String date = sdf.format(ct.getNgayBD());
				String tenMon = dsmh.LayTenMon(ct.getMaMHP());
				String [] rowdata = {tenMon,ct.getMaLHP(),ct.getMaNhom(),ct.getTietHoc(),
						ct.getNgayHoc(),ct.getPhongHoc(),ct.getHoTen(),date};
				tableModel.addRow(rowdata);
			}
			table_1.setModel(tableModel);
		}
		if(o.equals(btnLoc2)) {
			cmbMaLop.removeAllItems();
			if(cmbTenMon.getSelectedIndex()>=0) {
				ArrayList<String> list1 = dsmh.LayMaLopTheoTen(cmbNamHoc.getSelectedItem().toString(),
						Integer.parseInt(cmbHocKy.getSelectedItem().toString()),cmbTenMon.getSelectedItem().toString());
				for (String ma : list1) {
					cmbMaLop.addItem(ma);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Chưa lấy tên môn được mở lớp trong kì");
			}
			
		}
		if(o.equals(btnLoc3)) {
			cmbMaGV.removeAllItems();
			ArrayList<String> listGV = dsgv.LayMaGV(cmbTenGV.getSelectedItem().toString());
			for (String magv : listGV) {
				cmbMaGV.addItem(magv);
			}
		}
		if(o.equals(btnXepLich)) {
			if(kiemtra()==true) {
				ArrayList<String> listkt = dsct.kiemtraLHP(cmbNgayHoc.getSelectedItem().toString(),
						cmbMaGV.getSelectedItem().toString(),cmbPhongHoc.getSelectedItem().toString(),
						cmbTietHoc.getSelectedItem().toString(),cmbNamHoc.getSelectedItem().toString(),
						Integer.parseInt(cmbHocKy.getSelectedItem().toString()));
				if(listkt.size()==0) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txtNgayBatDau.getDate());
					if(dsct.ThemCTLHP(cmbMaNhom.getSelectedItem().toString(),
							cmbMaLop.getSelectedItem().toString(), 
							cmbTietHoc.getSelectedItem().toString(), 
							cmbNgayHoc.getSelectedItem().toString(), 
							cmbPhongHoc.getSelectedItem().toString(), 
							cmbMaGV.getSelectedItem().toString(),date)) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String date2 = sdf1.format(txtNgayBatDau.getDate());
						Object[] datarow = {cmbTenMon.getSelectedItem(),cmbMaLop.getSelectedItem(),
								cmbMaNhom.getSelectedItem(),cmbTietHoc.getSelectedItem(),cmbNgayHoc.getSelectedItem(),
								cmbPhongHoc.getSelectedItem(),cmbTenGV.getSelectedItem(),date2};
						tableModel.addRow(datarow);
						JOptionPane.showMessageDialog(null, "Thành công");
						them();

					}else {
						JOptionPane.showMessageDialog(null, "Lớp này đã được xếp lịch");
					}

				}else {
					JOptionPane.showMessageDialog(null, "Trùng lịch hoặc giáo viên");
				}
			}
		}
		if(o.equals(btnXoaLich)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maLop = (String)table_1.getValueAt(row, 1);
				String nhom = (String)table_1.getValueAt(row, 2);
				int hoinhac = JOptionPane.showConfirmDialog(null, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dsct.xoaChiTiet(maLop, nhom)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}
				}
			}
		}

	}
}
