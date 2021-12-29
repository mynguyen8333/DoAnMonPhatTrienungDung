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

public class GD_NhanVien_CapNhatKhoa extends JFrame implements ActionListener,MouseListener{
	

	private JFrame frame;
	private JTextField txtMaKhoa;
	private JTextField txtTenKhoa;
	
	private JTable table_1;
	private JButton btnXoaTrang,btnThemKhoa,btnXoaKhoa;
	private JPanel pnlTong;
	private KhoaDao dhkh = new KhoaDao();
	private DefaultTableModel tableModel;
	private JButton btnCapNhat;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatKhoa window = new GD_NhanVien_CapNhatKhoa();
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
	public GD_NhanVien_CapNhatKhoa() {
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
		
		JLabel lblTieuDe = new JLabel("CẬP NHẬT THÔNG TIN KHOA");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(497, 23, 343, 34);
		pnlTong.add(lblTieuDe);
		
		JLabel lblMaKhoa = new JLabel("Mã khoa:");
		lblMaKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaKhoa.setBounds(100, 84, 84, 17);
		pnlTong.add(lblMaKhoa);
		
		txtMaKhoa = new JTextField();
		txtMaKhoa.setBounds(245, 83, 350, 20);
		pnlTong.add(txtMaKhoa);
		txtMaKhoa.setColumns(10);
		
		JLabel lblTenKhoa = new JLabel("Tên khoa:");
		lblTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenKhoa.setBounds(696, 84, 84, 17);
		pnlTong.add(lblTenKhoa);
		
		txtTenKhoa = new JTextField();
		txtTenKhoa.setColumns(10);
		txtTenKhoa.setBounds(803, 83, 350, 20);
		pnlTong.add(txtTenKhoa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 197, 1122, 379);
		pnlTong.add(scrollPane);
		
		String[] headers = "Mã khoa;Tên khoa".split(";");
		
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
		
		btnThemKhoa = new JButton("Thêm");
		btnThemKhoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemKhoa.setBounds(360, 11, 116, 33);
		pnlTacVu.add(btnThemKhoa);
		
		btnXoaKhoa = new JButton("Xóa");
		btnXoaKhoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaKhoa.setBounds(560, 11, 116, 33);
		pnlTacVu.add(btnXoaKhoa);
		
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(760, 11, 116, 33);
		pnlTacVu.add(btnCapNhat);
		
		btnXoaTrang.addActionListener(this);
		btnXoaKhoa.addActionListener(this);
		btnThemKhoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
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
		ArrayList<Khoa> list = dhkh.docTuBang();
		for (Khoa kh : list) {
			String [] rowdata = {kh.getMaKhoa(),kh.getTenKhoa()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
	/*
	 * Hàm
	 */
	
	public void them() {
		txtMaKhoa.setText("");
		txtTenKhoa.setText("");
		txtMaKhoa.setEditable(true);
	}
	public boolean kiemtra(){
		String maKhoa = txtMaKhoa.getText().trim();
		String tenKhoa = txtTenKhoa.getText().trim();
		
		if(!(maKhoa.length()>0)) {
			JOptionPane.showMessageDialog(null, "Mã khoa không được để trống và phải là chữ viết tắt của tên khoa");
			txtMaKhoa.selectAll();
			txtMaKhoa.requestFocus();
			return false;
		}

		if(!(tenKhoa.length()>0)) {
			JOptionPane.showMessageDialog(null, "Tên khoa không được để trống");
			txtTenKhoa.selectAll();
			txtTenKhoa.requestFocus();
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
		if(o.equals(btnThemKhoa)) {
			if(kiemtra()==true) {
				if(dhkh.ThemKhoa(txtMaKhoa.getText().trim(),txtTenKhoa.getText().trim())) {
					Object[] datarow = {txtTenKhoa.getText(),txtMaKhoa.getText()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Thêm thành công khoa");
					them();
				}else {
					JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
				}
			}
		}
		if(o.equals(btnXoaKhoa)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maKhoa = (String)table_1.getValueAt(row, 0);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dhkh.xoaKhoa(maKhoa)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Chưa xóa các chuyên ngành và giảng viên của khoa");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn khoa muốn xóa");
			}
		}
		
		if(o.equals(btnCapNhat)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"Bạn có chắc không","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					if(dhkh.capNhatKhoa(txtMaKhoa.getText().trim(),txtTenKhoa.getText().trim())) {
						table_1.setValueAt(txtMaKhoa.getText(), row,0);
						table_1.setValueAt(txtTenKhoa.getText(), row,1);
						them();
						JOptionPane.showMessageDialog(null,"Sửa thành công");
					}else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn Khoa");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtMaKhoa.setText(table_1.getValueAt(row, 0).toString());
		txtMaKhoa.setEditable(false);
		txtTenKhoa.setText(table_1.getValueAt(row, 1).toString());
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
