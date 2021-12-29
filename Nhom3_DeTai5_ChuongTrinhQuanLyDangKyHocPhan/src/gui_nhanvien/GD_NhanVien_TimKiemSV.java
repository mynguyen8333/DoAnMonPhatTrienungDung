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
import dao.SinhVienDao;
import entity.SinhVien;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GD_NhanVien_TimKiemSV implements ActionListener{
	

	private JFrame frame;
	private JTextField txtTim;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private JRadioButton radTenSV,radDiaChi,radSoDienThoai;
	private ButtonGroup group;
	private JButton btnTim;
	private SinhVienDao dssv = new SinhVienDao();
	private JPanel pnlTong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_TimKiemSV window = new GD_NhanVien_TimKiemSV();
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
	public GD_NhanVien_TimKiemSV() {
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
		
		JLabel lblTieuDe = new JLabel("TÌM KIẾM SINH VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(500, 21, 319, 31);
		pnlTong.add(lblTieuDe);
		
		JPanel pnlTim = new JPanel();
		pnlTim.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTim.setBounds(100, 89, 1061, 92);
		pnlTong.add(pnlTim);
		pnlTim.setLayout(null);
		
		JLabel lblThongTin = new JLabel("Nhập thông tin muốn tìm:");
		lblThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblThongTin.setBounds(74, 21, 188, 22);
		pnlTim.add(lblThongTin);
		
		txtTim = new JTextField();
		txtTim.setBounds(272, 23, 380, 20);
		pnlTim.add(txtTim);
		txtTim.setColumns(10);
		
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnTim.setBounds(698, 22, 108, 23);
		pnlTim.add(btnTim);
		
		JLabel lblTimTheo = new JLabel("Tìm theo:");
		lblTimTheo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTimTheo.setBounds(74, 59, 78, 22);
		pnlTim.add(lblTimTheo);
		
		radTenSV = new JRadioButton("Tên sinh viên");
		radTenSV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radTenSV.setBounds(260, 60, 126, 23);
		pnlTim.add(radTenSV);
		
		radDiaChi = new JRadioButton("Địa chỉ\r\n");
		radDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radDiaChi.setBounds(423, 59, 85, 23);
		pnlTim.add(radDiaChi);
		
		radSoDienThoai = new JRadioButton("Số điện thoại");
		radSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radSoDienThoai.setBounds(565, 59, 128, 23);
		pnlTim.add(radSoDienThoai);
		
		group = new ButtonGroup();
		group.add(radTenSV);
		group.add(radDiaChi);
		group.add(radSoDienThoai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 215, 1112, 398);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Mã sinh viên;Họ tên;Giới tính;Ngày sinh;Địa chỉ;Số điện thoại".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		DataBase.getInstance().connect();
		dulieubang();
		btnTim.addActionListener(this);
	}
	
	public void dulieubang() {
		ArrayList<SinhVien> list = dssv.doctubang();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		
		for (SinhVien sv : list) {
			String date2 = sdf1.format(sv.getNgaySinh());
			String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),date2,
					sv.getDiaChi(),sv.getSdt()};
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
				if(!(radDiaChi.isSelected() || radSoDienThoai.isSelected() || radTenSV.isSelected())) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn hướng tìm kiếm");
				}
				if(radDiaChi.isSelected()) {
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
				}
				if(radSoDienThoai.isSelected()) {
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
		
		
	}
}
