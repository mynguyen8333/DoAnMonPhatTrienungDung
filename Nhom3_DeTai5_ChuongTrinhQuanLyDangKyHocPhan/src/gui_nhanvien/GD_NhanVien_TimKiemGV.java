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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.GiangVienDao;
import dao.KhoaDao;
import dao.SinhVienDao;
import entity.GiangVien;
import entity.SinhVien;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GD_NhanVien_TimKiemGV implements ActionListener{
	

	private JFrame frame;
	private JTextField txtTim;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private JRadioButton radTenGV,radDiaChi,radSoDienThoai;
	private ButtonGroup group;
	private JButton btnTim;
	private GiangVienDao dsGV = new GiangVienDao();
	private KhoaDao kh = new KhoaDao();
	private JPanel pnlTong;
	private JRadioButton radTenKhoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_TimKiemGV window = new GD_NhanVien_TimKiemGV();
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
	public GD_NhanVien_TimKiemGV() {
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
		
		JLabel lblTieuDe = new JLabel("TÌM KIẾM GIẢNG VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(500, 21, 319, 31);
		pnlTong.add(lblTieuDe);
		
		JPanel pnlTim = new JPanel();
		pnlTim.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTim.setBounds(100, 89, 1011, 115);
		pnlTong.add(pnlTim);
		pnlTim.setLayout(null);
		
		JLabel lblThongTin = new JLabel("Nhập thông tin muốn tìm:");
		lblThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblThongTin.setBounds(74, 21, 210, 22);
		pnlTim.add(lblThongTin);
		
		txtTim = new JTextField();
		txtTim.setBounds(318, 23, 338, 20);
		pnlTim.add(txtTim);
		txtTim.setColumns(10);
		
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnTim.setBounds(700, 22, 108, 23);
		pnlTim.add(btnTim);
		
		JLabel lblTimTheo = new JLabel("Tìm theo:");
		lblTimTheo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTimTheo.setBounds(74, 59, 78, 22);
		pnlTim.add(lblTimTheo);
		
		radTenGV = new JRadioButton("Tên giảng viên");
		radTenGV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radTenGV.setBounds(150, 60, 126, 23);
		pnlTim.add(radTenGV);
		
		radDiaChi = new JRadioButton("Địa chỉ\r\n");
		radDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radDiaChi.setBounds(300, 60, 78, 23);
		pnlTim.add(radDiaChi);
		
		radSoDienThoai = new JRadioButton("Số điện thoại");
		radSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radSoDienThoai.setBounds(450, 59, 114, 23);
		pnlTim.add(radSoDienThoai);
		
		
		radTenKhoa = new JRadioButton("Tên Khoa");
		radTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radTenKhoa.setBounds(650, 59, 128, 23);
		pnlTim.add(radTenKhoa);
		
		
		group = new ButtonGroup();
		group.add(radTenGV);
		group.add(radDiaChi);
		group.add(radSoDienThoai);
		group.add(radTenKhoa);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 215, 1011, 398);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Mã giảng viên;Họ tên;Giới tính;Ngày sinh;Địa chỉ;Số điện thoại;Tên khoa".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		DataBase.getInstance().connect();
		dulieubang();
		btnTim.addActionListener(this);
	}
	
	public void dulieubang() {
		ArrayList<GiangVien> list = dsGV.doctubang();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		for (GiangVien gv : list) {
			String date2 = sdf1.format(gv.getNgaySinh());
			String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),
					date2,gv.getDiaChi(),gv.getsDT(),gv.getMaKhoa()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}

	public JPanel getJPanel() {
		return pnlTong;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
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
						table_1.setModel(tableModel);

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
						table_1.setModel(tableModel);


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
						table_1.setModel(tableModel);

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
						table_1.setModel(tableModel);

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
		
		
	}
}
