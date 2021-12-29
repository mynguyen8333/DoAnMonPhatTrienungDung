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
import dao.NamDao;
import dao.PhongHocDao;
import entity.GiangVien;
import entity.Khoa;

import javax.swing.JButton;

public class GD_NhanVien_CapNhatPhong extends JFrame implements ActionListener,MouseListener{
	

	private JFrame frame;
	private JTextField txtPhongHoc;
	
	private JTable table_1;
	private JButton btnXoaTrang,btnThemPhong,btnXoaPhong;
	private JPanel pnlTong;
	//private NamDao dsm = new NamDao();
	private PhongHocDao dsph = new PhongHocDao();
	private DefaultTableModel tableModel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatPhong window = new GD_NhanVien_CapNhatPhong();
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
	public GD_NhanVien_CapNhatPhong() {
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
		
		JLabel lblTieuDe = new JLabel("CẬP NHẬT THÔNG TIN PHÒNG HỌC");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(497, 23, 343, 34);
		pnlTong.add(lblTieuDe);
		
		JLabel lblNamHoc = new JLabel("Phòng học:");
		lblNamHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNamHoc.setBounds(100, 84, 84, 17);
		pnlTong.add(lblNamHoc);
		
		txtPhongHoc = new JTextField();
		txtPhongHoc.setBounds(245, 83, 350, 20);
		pnlTong.add(txtPhongHoc);
		txtPhongHoc.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 197, 1122, 379);
		pnlTong.add(scrollPane);
		
		String[] headers = "Phòng học".split(";");
		
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(88, 131, 1122, 55);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaTrang.setBounds(160, 11, 116, 33);
		pnlTacVu.add(btnXoaTrang);
		
		btnThemPhong = new JButton("Thêm");
		btnThemPhong.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemPhong.setBounds(360, 11, 116, 33);
		pnlTacVu.add(btnThemPhong);
		
		btnXoaPhong = new JButton("Xóa");
		btnXoaPhong.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaPhong.setBounds(560, 11, 116, 33);
		pnlTacVu.add(btnXoaPhong);
		
		btnXoaTrang.addActionListener(this);
		btnXoaPhong.addActionListener(this);
		btnThemPhong.addActionListener(this);
		table_1.addMouseListener(this);
		
		
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
		ArrayList<String> list = dsph.getDsPH();
		for (String kh : list) {
			String [] rowdata = {kh};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
	/*
	 * Hàm
	 */
	
	public void them() {
		txtPhongHoc.setText("");
		txtPhongHoc.setEditable(true);
	}
	public boolean kiemtra(){
		String maKhoa = txtPhongHoc.getText().trim();
		
		if(!(maKhoa.length()>0)) {
			JOptionPane.showMessageDialog(null, "Phòng không được để trống");
			txtPhongHoc.selectAll();
			txtPhongHoc.requestFocus();
			return false;
		}
		return true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			them();
		}
		if(o.equals(btnThemPhong)) {
			if(kiemtra()==true) {
				if(dsph.themPhong(txtPhongHoc.getText().trim().toString())) {
					Object[] datarow = {txtPhongHoc.getText()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Thêm thành công phòng");
					them();
				}else {
					JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
				}
			}
		}
		if(o.equals(btnXoaPhong)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String phong = (String)table_1.getValueAt(row, 0);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dsph.xoaPhong(phong)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Lỗi");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng muốn xóa");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtPhongHoc.setText(table_1.getValueAt(row, 0).toString());
		txtPhongHoc.setEditable(false);
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
