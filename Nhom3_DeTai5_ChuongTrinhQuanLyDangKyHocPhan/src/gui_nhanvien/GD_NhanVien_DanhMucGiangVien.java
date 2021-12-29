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

public class GD_NhanVien_DanhMucGiangVien extends JFrame{


	private JFrame frame;
	private JPanel pnlTong;
	private JTable table;
	private DefaultTableModel tableModel;
	private GiangVienDao dsGV = new GiangVienDao();
	private KhoaDao kh = new KhoaDao();
	private ButtonGroup group;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_DanhMucGiangVien window = new GD_NhanVien_DanhMucGiangVien();
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
	public GD_NhanVien_DanhMucGiangVien() {
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

		JLabel lblTieuDe = new JLabel("DANH MỤC GIẢNG VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(553, 22, 576, 27);
		pnlTong.add(lblTieuDe);
		DataBase.getInstance().connect();

		int value = dsGV.LayMaTuDong()+2;
		String s1 = String.valueOf(value);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 72, 1126, 578);
		pnlTong.add(scrollPane);

		table = new JTable();
		String[] headers = "Mã giảng viên;Họ tên;Giới tính;Ngày sinh;Địa chỉ;Số điện thoại;Tên khoa".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		group = new ButtonGroup();
		table.setEnabled(false);
		dulieubang();


	}
	
	/*
	 * Thêm dữ liệu vào bảng
	 */
	public void dulieubang() {
		ArrayList<GiangVien> list = dsGV.doctubang();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		for (GiangVien gv : list) {
			String date2 = sdf1.format(gv.getNgaySinh());
			String [] rowdata = {gv.getMaGV(),gv.getTenGV(),gv.getGioTinh(),
					date2,gv.getDiaChi(),gv.getsDT(),gv.getMaKhoa()};
			tableModel.addRow(rowdata);
		}
		table.setModel(tableModel);
	}

	public JPanel getJPanel() {
		return pnlTong;
	}
}
