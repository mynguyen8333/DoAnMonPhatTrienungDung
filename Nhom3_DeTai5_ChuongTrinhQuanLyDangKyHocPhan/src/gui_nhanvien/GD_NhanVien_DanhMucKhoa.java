package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.KhoaDao;
import entity.GiangVien;
import entity.Khoa;

import javax.swing.JButton;

public class GD_NhanVien_DanhMucKhoa extends JFrame{
	

	private JFrame frame;
	
	private JTable table_1;
	private JPanel pnlTong;
	private KhoaDao dhkh = new KhoaDao();
	private DefaultTableModel tableModel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_DanhMucKhoa window = new GD_NhanVien_DanhMucKhoa();
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
	public GD_NhanVien_DanhMucKhoa() {
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
		
		JLabel lblTieuDe = new JLabel("DANH MỤC KHOA");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(615, 23, 343, 34);
		pnlTong.add(lblTieuDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 80, 1145, 542);
		pnlTong.add(scrollPane);
		
		String[] headers = "Mã khoa;Tên khoa".split(";");
		tableModel = new DefaultTableModel(headers,0);
		
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		scrollPane.setViewportView(table_1);
		table_1.setEnabled(false);
		
		DataBase.getInstance().connect();
		dulieubang();
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
		ArrayList<Khoa> list = dhkh.docTuBang();
		for (Khoa kh : list) {
			String [] rowdata = {kh.getMaKhoa(),kh.getTenKhoa()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
	
	
	
}
