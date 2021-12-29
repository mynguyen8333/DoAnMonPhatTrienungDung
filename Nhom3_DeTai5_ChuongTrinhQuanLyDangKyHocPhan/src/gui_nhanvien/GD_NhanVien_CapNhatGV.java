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

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DataBase;
import dao.GiangVienDao;
import dao.KhoaDao;
import dao.SinhVienDao;
import entity.GiangVien;
import entity.LopHocPhan;
import entity.SinhVien;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class GD_NhanVien_CapNhatGV extends JFrame implements ActionListener,MouseListener{


	private JFrame frame;
	private JPanel pnlTong;
	private JTextField txtMaSo;
	private JTextField txtDiaChi;
	private JDateChooser txtNgaySinh;
	private JTextField txtHoTen;
	private JTextField txtSoDienThoai;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> cmbGioiTinh,cmbTenKhoa;
	private JButton btnThemGV,btnXoa,btnXoaTrang,btnTim;
	private GiangVienDao dsGV = new GiangVienDao();
	private KhoaDao kh = new KhoaDao();
	private JLabel lblTimKiem;
	private JTextField txtTim;
	private JRadioButton radTenGV,radDiaChi,radSoDienThoai,radTenKhoa;
	private ButtonGroup group;
	private JButton btnCapNhat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatGV window = new GD_NhanVien_CapNhatGV();
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
	public GD_NhanVien_CapNhatGV() {
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

		JLabel lblTieuDe = new JLabel("CẬP NHẬT THÔNG TIN GIẢNG VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(477, 22, 576, 27);
		pnlTong.add(lblTieuDe);

		JLabel lblMaSoGV = new JLabel("Mã số giảng viên:");
		lblMaSoGV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaSoGV.setBounds(100, 60, 119, 27);
		pnlTong.add(lblMaSoGV);		
		txtMaSo = new JTextField();
		DataBase.getInstance().connect();

		int value = dsGV.LayMaTuDong()+2;
		String s1 = String.valueOf(value);
		txtMaSo.setText("GV"+ s1);

		txtMaSo.setEditable(false);
		txtMaSo.setBounds(245, 60, 350, 20);
		pnlTong.add(txtMaSo);
		txtMaSo.setColumns(10);

		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDiaChi.setBounds(696, 98, 83, 27);
		pnlTong.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(803, 102, 350, 20);
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
		txtNgaySinh.setBounds(245, 136, 350, 20);
		pnlTong.add(txtNgaySinh);

		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHoTen.setBounds(696, 60, 78, 27);
		pnlTong.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(803, 64, 350, 20);
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
		pnlTacVu.setBounds(70, 208, 1112, 58);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaTrang.setBounds(145, 11, 116, 36);
		pnlTacVu.add(btnXoaTrang);

		btnThemGV = new JButton("Thêm");
		btnThemGV.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemGV.setBounds(345, 11, 116, 36);
		pnlTacVu.add(btnThemGV);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(545, 11, 116, 36);
		pnlTacVu.add(btnXoa);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(745, 11, 116, 36);
		pnlTacVu.add(btnCapNhat);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 357, 1112, 293);
		pnlTong.add(scrollPane);

		table = new JTable();
		String[] headers = "Mã giảng viên;Họ tên;Giới tính;Ngày sinh;Địa chỉ;Số điện thoại;Tên khoa".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBounds(70, 270, 1112, 76);
		pnlTong.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JLabel lblThongTin = new JLabel("Nhập thông tin muốn tìm:");
		lblThongTin.setBounds(68, 11, 216, 27);
		pnlTimKiem.add(lblThongTin);
		lblThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtTim = new JTextField();
		txtTim.setBounds(251, 15, 447, 20);
		pnlTimKiem.add(txtTim);
		txtTim.setColumns(10);

		radDiaChi = new JRadioButton("Địa chỉ\r\n");
		radDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radDiaChi.setBounds(468, 41, 82, 23);
		pnlTimKiem.add(radDiaChi);

		lblTimKiem = new JLabel("Tìm theo:");
		lblTimKiem.setBounds(68, 39, 89, 27);
		pnlTimKiem.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		radTenGV = new JRadioButton("Tên giảng viên");
		radTenGV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radTenGV.setBounds(168, 42, 114, 23);
		pnlTimKiem.add(radTenGV);

		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnTim.setBounds(763, 12, 114, 27);
		pnlTimKiem.add(btnTim);

		radSoDienThoai = new JRadioButton("Số điện thoại");
		radSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radSoDienThoai.setBounds(318, 42, 109, 23);
		pnlTimKiem.add(radSoDienThoai);



		radTenKhoa = new JRadioButton("Tên khoa");
		radTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radTenKhoa.setBounds(618, 42, 109, 23);
		pnlTimKiem.add(radTenKhoa);

		group = new ButtonGroup();
		group.add(radTenGV);
		group.add(radDiaChi);
		group.add(radSoDienThoai);
		group.add(radTenKhoa);

		JLabel lblTenKhoa = new JLabel("Tên khoa:");
		lblTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenKhoa.setBounds(100, 174, 119, 27);
		pnlTong.add(lblTenKhoa);

		cmbTenKhoa = new JComboBox<String>();
		cmbTenKhoa.setBounds(245, 174, 350, 22);
		pnlTong.add(cmbTenKhoa);

		btnThemGV.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		radDiaChi.addActionListener(this);
		radTenGV.addActionListener(this);
		radSoDienThoai.addActionListener(this);
		radTenKhoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		
		table.addMouseListener(this);

		cbBoxTenKhoa();
		dulieubang();

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
	 * Thêm dữ liệu vào bảng
	 */
	public void dulieubang() {
		ArrayList<GiangVien> list = dsGV.doctubang();
		//SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		for (GiangVien gv : list) {
			//String date2 = sdf1.format(gv.getNgaySinh());
			String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),
					gv.getNgaySinh()+"",gv.getDiaChi(),gv.getsDT(),gv.getMaKhoa()};
			tableModel.addRow(rowdata);
		}
		table.setModel(tableModel);
	}

	/*
	 * Thêm dữ liệu vào cbox
	 */

	public void cbBoxTenKhoa() {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		cmbTenKhoa.removeAllItems();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenKhoa.addItem(ten);
			}

		}
	}

	public JPanel getJPanel() {
		return pnlTong;
	}

	public void them() {
		txtDiaChi.setText("");
		txtHoTen.setText("");
		int value = dsGV.LayMaTuDong()+2;
		String s1 = String.valueOf(value);
		txtMaSo.setText("GV"+ s1);
		txtSoDienThoai.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).setText("");
		cmbTenKhoa.setSelectedIndex(0);
	}
	public boolean kiemtra() throws ParseException{
		String hoTen = txtHoTen.getText().trim();
		Date d = txtNgaySinh.getDate();
		Date d1 = txtNgaySinh.getDate();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = sdf.parse("1997-1-1");
		int n = cmbTenKhoa.getSelectedIndex();
		String diaChi = txtDiaChi.getText().trim();
		String sdt = txtSoDienThoai.getText().trim();
		GiangVien gv = dsGV.KiemTraSDT(sdt);
		String maKhoa = kh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
		if(d1==null ) {
			JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống ");
			((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).requestFocus();
			return false;
		}
		if(d.compareTo(date2)>0) {
			JOptionPane.showMessageDialog(null,"Ngày sinh phải trước năm 1997");
			((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).selectAll();
			((JTextField)txtNgaySinh.getDateEditor().getUiComponent()).requestFocus();
			return false;
		}

		if(!(hoTen.length()>0)) {
			JOptionPane.showMessageDialog(null, "Họ tên giảng viên không được để trống");
			txtHoTen.selectAll();
			txtHoTen.requestFocus();
			return false;
		}

		if(!(diaChi.length()>0)) {
			JOptionPane.showMessageDialog(null, "Không được để trống địa chỉ");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		}

		if(!(sdt.length()>0 && sdt.matches("^(09||03)[0-9]{8}$"))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải theo cấu trúc ^(09||03)[0-9]{8}$");
			txtSoDienThoai.selectAll();
			txtSoDienThoai.requestFocus();
			return false;
		}
		if(gv!=null) {
			JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại");
			txtSoDienThoai.selectAll();
			txtSoDienThoai.requestFocus();
			return false;
		}
		if(n<0)
		{
			JOptionPane.showMessageDialog(null, "Chưa chọn tên khoa");
			cmbTenKhoa.setSelectedIndex(0);;
			return false;
		}
		if(maKhoa==null)
		{
			JOptionPane.showMessageDialog(null, "Chưa chọn tên khoa");
			cmbTenKhoa.setSelectedIndex(0);;
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(btnThemGV)) {
			try {
				if(kiemtra()==true) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txtNgaySinh.getDate());
					String maKhoa = kh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString());
					if(dsGV.ThemGiangVien(txtMaSo.getText().trim(),txtDiaChi.getText().trim(),
							cmbGioiTinh.getSelectedItem().toString(), txtHoTen.getText().trim(), 
							date, txtSoDienThoai.getText().trim(), maKhoa)) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String date1 = sdf1.format(txtNgaySinh.getDate());
						Object[] datarow = {txtMaSo.getText(),txtHoTen.getText(),cmbGioiTinh.getSelectedItem(),
								date1,txtDiaChi.getText(),txtSoDienThoai.getText(),cmbTenKhoa.getSelectedItem()};
						tableModel.addRow(datarow);
						JOptionPane.showMessageDialog(null, "Thêm thành công giảng viên");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(o.equals(btnXoaTrang)) {
			them();
		}

		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row>=0) {
				String maGV = (String)table.getValueAt(row, 0);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dsGV.xoaGV(maGV)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Chưa xóa các lớp đã được lên lịch cho giảng viên này");
					}
				}
			}

		}
		if(o.equals(btnTim)) {
			String tim = txtTim.getText().trim();
			if(tim.length()>0) {
				if(!(radDiaChi.isSelected() || radSoDienThoai.isSelected() || radTenGV.isSelected()|| radTenKhoa.isSelected() )) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn hướng tìm kiếm");
				}
				if(radDiaChi.isSelected()) {
					ArrayList<GiangVien> list1 = dsGV.TimTheoDiaChi(txtTim.getText().toString());
					//System.out.println(list1);
					if(list1.size()>0) {
						tableModel.setRowCount(0);
						for (GiangVien gv : list1) {
							String tenKhoa = kh.LayTenKhoa(gv.getMaKhoa());
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
							String date1 = sdf1.format(gv.getNgaySinh());
							String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),date1,
									gv.getDiaChi(),gv.getsDT(),tenKhoa};
							tableModel.addRow(rowdata);
						}
						table.setModel(tableModel);

					}if(list1.size()==0) {
						txtTim.selectAll();
						txtTim.requestFocus();
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						tableModel.setRowCount(0);
						dulieubang();

					}
				}
				if(radTenGV.isSelected()) {
					ArrayList<GiangVien> list = dsGV.TimTheoTen(txtTim.getText().toString());
					if(list.size()>0) {
						tableModel.setRowCount(0);
						for (GiangVien gv : list) {
							String tenKhoa = kh.LayTenKhoa(gv.getMaKhoa());
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
							String date1 = sdf1.format(gv.getNgaySinh());
							String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),date1,
									gv.getDiaChi(),gv.getsDT(),tenKhoa};
							tableModel.addRow(rowdata);
						}
						table.setModel(tableModel);


					}if(list.size()==0) {
						txtTim.selectAll();
						txtTim.requestFocus();
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						tableModel.setRowCount(0);
						dulieubang();

					}
				}
				if(radSoDienThoai.isSelected()) {
					ArrayList<GiangVien> list3 = dsGV.TimTheoSDT(txtTim.getText().toString());
					if(list3.size()>0) {
						tableModel.setRowCount(0);
						for (GiangVien gv : list3) {
							String tenKhoa = kh.LayTenKhoa(gv.getMaKhoa());
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
							String date1 = sdf1.format(gv.getNgaySinh());
							String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),date1,
									gv.getDiaChi(),gv.getsDT(),tenKhoa};
							tableModel.addRow(rowdata);
						}
						table.setModel(tableModel);

					}if(list3.size()==0) {
						txtTim.selectAll();
						txtTim.requestFocus();
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						tableModel.setRowCount(0);
						dulieubang();

					}
				}
				if(radTenKhoa.isSelected()) {
					ArrayList<GiangVien> list4 = dsGV.TimTheoTenKhoa(txtTim.getText().toString());
					if(list4.size()>0) {
						tableModel.setRowCount(0);
						for (GiangVien gv : list4) {
							String tenKhoa = kh.LayTenKhoa(gv.getMaKhoa());
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
							String date1 = sdf1.format(gv.getNgaySinh());
							String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),date1,
									gv.getDiaChi(),gv.getsDT(),tenKhoa};
							tableModel.addRow(rowdata);
						}
						table.setModel(tableModel);

					}if(list4.size()==0) {
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
			int row = table.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"Bạn có chắc không","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txtNgaySinh.getDate());
					String maKhoa = kh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString());
					if(dsGV.capNhatGiangVien(txtMaSo.getText(),txtHoTen.getText().trim(),
							cmbGioiTinh.getSelectedItem().toString(),
							date,txtDiaChi.getText().trim(),txtSoDienThoai.getText().trim(), maKhoa)) {
						table.setValueAt(txtMaSo.getText(), row,0);
						table.setValueAt(txtHoTen.getText(), row,1);
						table.setValueAt(cmbGioiTinh.getSelectedItem().toString(), row,2);
						table.setValueAt(date, row,3);
						table.setValueAt(txtDiaChi.getText(), row,4);
						table.setValueAt(txtSoDienThoai.getText(), row,5);
						table.setValueAt(cmbTenKhoa.getSelectedItem().toString(), row,6);
						them();
						JOptionPane.showMessageDialog(null,"Sửa thành công");
					}
					else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn giảng viên");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaSo.setText(table.getValueAt(row, 0).toString());
		txtHoTen.setText(table.getValueAt(row, 1).toString());
		cmbGioiTinh.setSelectedItem(table.getValueAt(row, 2).toString());
		try {
			Date date  = new SimpleDateFormat("yyyy-MM-dd").parse((String)table.getValueAt(row, 3));
			txtNgaySinh.setDate(date);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		txtDiaChi.setText(table.getValueAt(row, 4).toString());
		txtSoDienThoai.setText(table.getValueAt(row, 5).toString());
		cmbTenKhoa.setSelectedItem(table.getValueAt(row, 6).toString());
		
		
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
