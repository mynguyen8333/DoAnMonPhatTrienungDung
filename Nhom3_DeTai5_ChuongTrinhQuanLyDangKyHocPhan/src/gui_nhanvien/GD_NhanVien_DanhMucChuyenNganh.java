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

public class GD_NhanVien_DanhMucChuyenNganh extends JFrame{
	

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JPanel pnlTong;
	private KhoaDao dhkh = new KhoaDao();
	private ChuyenNganhDao dscn = new ChuyenNganhDao();
	private DefaultTableModel tableModel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_DanhMucChuyenNganh window = new GD_NhanVien_DanhMucChuyenNganh();
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
	public GD_NhanVien_DanhMucChuyenNganh() {
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
		
		JLabel lblTieuDe = new JLabel("DANH MỤC CHUYÊN NGÀNH");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(488, 22, 378, 34);
		pnlTong.add(lblTieuDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 85, 1128, 543);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Tên khoa;Mã chuyên ngành;Tên chuyên ngành".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);

		DataBase.getInstance().connect();
		dulieubang();
		table_1.setEnabled(false);
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
		ArrayList<ChuyenNganh> list = dscn.docTuBang();
		for (ChuyenNganh cn : list) {
			String tenKhoa = dhkh.LayTenKhoa(cn.getMaKhoa());
			String [] rowdata = {tenKhoa,cn.getMaChuyenNganh(),cn.getTenChuyenNganh()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
}
