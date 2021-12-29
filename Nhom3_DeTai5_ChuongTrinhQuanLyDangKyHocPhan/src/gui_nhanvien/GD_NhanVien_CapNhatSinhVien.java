package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DataBase;
import dao.SinhVienDao;
import entity.LopHocPhan;
import entity.SinhVien;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class GD_NhanVien_CapNhatSinhVien extends JFrame implements ActionListener,MouseListener{
	

	private JFrame frame;
	private JPanel pnlTong;
	private JTextField txtMaSo;
	private JTextField txtDiaChi;
	private JDateChooser txtNgaySinh;
	private JTextField txtHoTen;
	private JTextField txtSoDienThoai;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private JComboBox<String> cmbGioiTinh;
	private JButton btnThemSV,btnXoa,btnXoaTrang,btnTim;
	private SinhVienDao dssv = new SinhVienDao();
	private JLabel lblTimKiem;
	private JTextField txtTim;
	private JRadioButton radTenSV,radDiaChi,radSoDienThoai;
	private ButtonGroup group;
	private JButton btnCapNhat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatSinhVien window = new GD_NhanVien_CapNhatSinhVien();
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
	public GD_NhanVien_CapNhatSinhVien() {
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

		JLabel lblCpNhtThng = new JLabel("CẬP NHẬT THÔNG TIN SINH VIÊN");
		lblCpNhtThng.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCpNhtThng.setBounds(521, 22, 396, 27);
		pnlTong.add(lblCpNhtThng);

		JLabel lblMaSoSV = new JLabel("Mã số sinh viên:");
		lblMaSoSV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaSoSV.setBounds(100, 60, 119, 27);
		pnlTong.add(lblMaSoSV);		
		txtMaSo = new JTextField();
		DataBase.getInstance().connect();
		int value = dssv.LayMaTuDong()+1;
		String s1 = String.valueOf(value);
		int length = s1.length();

		if(length==1) {
			txtMaSo.setText("000" + s1);
		}
		if(length==2) {
			txtMaSo.setText("00" + s1);
		}
		if(length==3) {
			txtMaSo.setText("0"+ s1);
		}
		txtMaSo.setEditable(false);
		txtMaSo.setBounds(245, 64, 350, 20);
		pnlTong.add(txtMaSo);
		txtMaSo.setColumns(10);

		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDiaChi.setBounds(696, 60, 119, 27);
		pnlTong.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(803, 60, 350, 20);
		pnlTong.add(txtDiaChi);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGioiTinh.setBounds(100, 98, 119, 27);
		pnlTong.add(lblGioiTinh);

		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.addItem("Nam");
		cmbGioiTinh.addItem("Nữ");
		cmbGioiTinh.setBounds(245, 98, 350, 22);
		pnlTong.add(cmbGioiTinh);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgaySinh.setBounds(100, 136, 119, 27);
		pnlTong.add(lblNgaySinh);

		txtNgaySinh = new JDateChooser();
		//txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(245, 143, 350, 20);
		pnlTong.add(txtNgaySinh);

		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHoTen.setBounds(696, 98, 119, 27);
		pnlTong.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(803, 102, 350, 20);
		pnlTong.add(txtHoTen);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSoDienThoai.setBounds(696, 136, 119, 27);
		pnlTong.add(lblSoDienThoai);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(803, 140, 350, 20);
		pnlTong.add(txtSoDienThoai);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(70, 191, 1106, 58);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaTrang.setBounds(166, 11, 116, 36);
		pnlTacVu.add(btnXoaTrang);

		btnThemSV = new JButton("Thêm");
		btnThemSV.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemSV.setBounds(348, 11, 116, 36);
		pnlTacVu.add(btnThemSV);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(528, 11, 116, 36);
		pnlTacVu.add(btnXoa);
		
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(727, 11, 116, 36);
		pnlTacVu.add(btnCapNhat);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 340, 1106, 310);
		pnlTong.add(scrollPane);

		table = new JTable();
		String[] headers = "Mã sinh viên;Họ tên;Giới tính;Ngày sinh;Địa chỉ;Số điện thoại".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBounds(70, 260, 1106, 76);
		pnlTong.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JLabel lblThongTin = new JLabel("Nhập thông tin muốn tìm:");
		lblThongTin.setBounds(68, 11, 216, 27);
		pnlTimKiem.add(lblThongTin);
		lblThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtTim = new JTextField();
		txtTim.setBounds(232, 15, 492, 20);
		pnlTimKiem.add(txtTim);
		txtTim.setColumns(10);

		radDiaChi = new JRadioButton("Địa chỉ\r\n");
		radDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radDiaChi.setBounds(318, 41, 82, 23);
		pnlTimKiem.add(radDiaChi);

		lblTimKiem = new JLabel("Tìm theo:");
		lblTimKiem.setBounds(68, 39, 89, 27);
		pnlTimKiem.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		radTenSV = new JRadioButton("Tên sinh viên");
		radTenSV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radTenSV.setBounds(168, 42, 109, 23);
		pnlTimKiem.add(radTenSV);

		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnTim.setBounds(734, 12, 116, 27);
		pnlTimKiem.add(btnTim);
		
		radSoDienThoai = new JRadioButton("Số điện thoại");
		radSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radSoDienThoai.setBounds(468, 42, 164, 23);
		pnlTimKiem.add(radSoDienThoai);
		
		group = new ButtonGroup();
		group.add(radTenSV);
		group.add(radDiaChi);
		group.add(radSoDienThoai);

		btnThemSV.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		radDiaChi.addActionListener(this);
		radTenSV.addActionListener(this);
		radSoDienThoai.addActionListener(this);
		btnCapNhat.addActionListener(this);
		
		table_1.addMouseListener(this);

		dulieubang();

	}

	/*
	 * Thêm dữ liệu vào bảng
	 */
	public void dulieubang() {
		ArrayList<SinhVien> list = dssv.doctubang();
		//SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		
		for (SinhVien sv : list) {
			//String date2 = sdf1.format(sv.getNgaySinh());
			String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),sv.getNgaySinh()+"",
					sv.getDiaChi(),sv.getSdt()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}

	public JPanel getJPanel() {
		return pnlTong;
	}

	public void them() {
		txtDiaChi.setText("");
		txtHoTen.setText("");
		int value = dssv.LayMaTuDong()+1;
		String s1 = String.valueOf(value);
		int length = s1.length();

		if(length==1) {
			txtMaSo.setText("000" + s1);
		}
		if(length==2) {
			txtMaSo.setText("00" + s1);
		}
		if(length==3) {
			txtMaSo.setText("0"+ s1);
		}
		txtSoDienThoai.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).setText("");
	}
	public boolean kiemtra() throws ParseException{
		String hoTen = txtHoTen.getText().trim();
		Date d = txtNgaySinh.getDate();
		Date d1 = txtNgaySinh.getDate();
		
		SinhVien sv = dssv.KiemTraSDT(txtSoDienThoai.getText().trim());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = sdf.parse("2003-1-1");

		String diaChi = txtDiaChi.getText().trim();
		String sdt = txtSoDienThoai.getText().trim();
		if(d1==null ) {
			JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống ");
			((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).requestFocus();
			return false;
		}
		if(d.compareTo(date2)>0) {
			JOptionPane.showMessageDialog(null,"Ngày sinh phải trước năm 2003");
			((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).selectAll();;
			((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).requestFocus();
			return false;
		}
		if(!(diaChi.length()>0)) {
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			JOptionPane.showMessageDialog(null, "Không được để trống địa chỉ");
			return false;
		}
		if(!(hoTen.length()>0)) {
			txtHoTen.selectAll();
			txtHoTen.requestFocus();
			JOptionPane.showMessageDialog(null, "Họ tên sinh viên không được để trống");
			return false;
		}


		if(!(sdt.length()>0 && sdt.matches("^(09||03)[0-9]{8}$"))) {
			txtSoDienThoai.selectAll();
			txtSoDienThoai.requestFocus();
			JOptionPane.showMessageDialog(null, "Số điện thoại phải theo cấu trúc ^(09||03)[0-9]{8}$");
			return false;
		}
		if(sv!=null) {
			txtSoDienThoai.selectAll();
			txtSoDienThoai.requestFocus();
			JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại");
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(btnThemSV)) {
			try {
				if(kiemtra()==true) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txtNgaySinh.getDate());
					if(dssv.ThemSinhVien(txtMaSo.getText(),txtDiaChi.getText(),
							cmbGioiTinh.getSelectedItem().toString(),txtHoTen.getText(),date, txtSoDienThoai.getText())) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String date1 = sdf1.format(txtNgaySinh.getDate());
						Object[] datarow = {txtMaSo.getText(),txtHoTen.getText(),cmbGioiTinh.getSelectedItem(),
								date1,txtDiaChi.getText(),txtSoDienThoai.getText()};
						tableModel.addRow(datarow);
						JOptionPane.showMessageDialog(null, "Thêm thành công sinh viên");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
					}
				}else {
					//JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(o.equals(btnXoaTrang)) {
			them();
		}

		if(o.equals(btnXoa)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maSV = (String)table_1.getValueAt(row, 0);
				String maTK = (String)table_1.getValueAt(row, 0);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dssv.xoaTK(maTK) && dssv.xoaSV(maSV)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}else {
						JOptionPane.showMessageDialog(null,"Chưa xóa thông tin sinh viên trong danh sách chuyên ngành");
					}
				}
			}

		}
		if(o.equals(btnTim)) {
			String tim = txtTim.getText().trim();
			if(tim.length()>0) {
				if(radDiaChi.isSelected()) {
					if(!(radDiaChi.isSelected() || radSoDienThoai.isSelected() || radTenSV.isSelected())) {
						JOptionPane.showMessageDialog(null, "Bạn chưa chọn hướng tìm kiếm");
					}
					ArrayList<SinhVien> list1 = dssv.TimTheoDiaChi(txtTim.getText().toString());
					if(list1.size()>0) {
						tableModel.setRowCount(0);
						for (SinhVien sv : list1) {
							String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),sv.getNgaySinh()+"",
									sv.getDiaChi(),sv.getSdt()};
							tableModel.addRow(rowdata);
						}
						table_1.setModel(tableModel);

					}if(list1.size()==0) {
						txtTim.selectAll();
						txtTim.requestFocus();
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						tableModel.setRowCount(0);
						dulieubang();

					}
				}if(radTenSV.isSelected()) {
					ArrayList<SinhVien> list = dssv.TimTheoTen(txtTim.getText().toString());
					if(list.size()>0) {
						tableModel.setRowCount(0);
						for (SinhVien sv : list) {
							String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),sv.getNgaySinh()+"",
									sv.getDiaChi(),sv.getSdt()};
							tableModel.addRow(rowdata);
						}
						table_1.setModel(tableModel);


					}if(list.size()==0) {
						txtTim.selectAll();
						txtTim.requestFocus();
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						tableModel.setRowCount(0);
						dulieubang();

					}
				}if(radSoDienThoai.isSelected()) {
					ArrayList<SinhVien> list2 = dssv.TimTheoSDT(txtTim.getText().toString());
					if(list2.size()>0) {
						tableModel.setRowCount(0);
						for (SinhVien sv : list2) {
							String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),sv.getNgaySinh()+"",
									sv.getDiaChi(),sv.getSdt()};
							tableModel.addRow(rowdata);
						}
						table_1.setModel(tableModel);

					}if(list2.size()==0) {
						txtTim.selectAll();
						txtTim.requestFocus();
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						tableModel.setRowCount(0);
						dulieubang();

					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ khóa");
				tableModel.setRowCount(0);
				dulieubang();
			}

		}
		if(o.equals(btnCapNhat)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"Bạn có chắc không","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txtNgaySinh.getDate());
					if(dssv.capNhatSinhVien(txtMaSo.getText(), txtHoTen.getText().trim(),
							cmbGioiTinh.getSelectedItem().toString(),date,txtDiaChi.getText().trim(),
							txtSoDienThoai.getText().trim())) {
						table_1.setValueAt(txtMaSo.getText(), row,0);
						table_1.setValueAt(txtHoTen.getText(), row,1);
						table_1.setValueAt(cmbGioiTinh.getSelectedItem().toString(), row,2);
						table_1.setValueAt(date, row,3);
						table_1.setValueAt(txtDiaChi.getText(), row,4);
						table_1.setValueAt(txtSoDienThoai.getText(), row,5);
						them();
						JOptionPane.showMessageDialog(null,"Sửa thành công");
					}
					else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn sinh viên");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtMaSo.setText(table_1.getValueAt(row, 0).toString());
		txtHoTen.setText(table_1.getValueAt(row, 1).toString());
		cmbGioiTinh.setSelectedItem(table_1.getValueAt(row, 2).toString());
		txtDiaChi.setText(table_1.getValueAt(row, 4).toString());
		txtSoDienThoai.setText(table_1.getValueAt(row, 5).toString());
		
		try {
			Date date  = new SimpleDateFormat("yyyy-MM-dd").parse((String)table_1.getValueAt(row, 3));
			txtNgaySinh.setDate(date);
		} catch (Exception e2) {
			e2.printStackTrace();
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
