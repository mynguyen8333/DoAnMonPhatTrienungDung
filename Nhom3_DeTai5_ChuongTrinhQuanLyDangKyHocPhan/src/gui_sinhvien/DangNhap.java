package gui_sinhvien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DataBase;
import dao.TaiKhoanDao;
import entity.TaiKhoan;
import gui_nhanvien.GDChinh_NhanVien;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

public class DangNhap extends JFrame  implements MouseListener, ActionListener {

	private JPanel contentPane;
	private JTextField txtTK, txtThongBao;
	private JLabel lblDangNhap, lblTK, lblMK;
	private JButton btnDangNhap;
	private String maSo;
	private TaiKhoanDao dstk = new TaiKhoanDao();
	private GDChinh_SV gdSV ;
	private JPasswordField passwordField;
	private GDChinh_NhanVien gdNV;
	public String getMaSo() {
		return maSo;
	}

	public void setMaSo(String maSo) {
		this.maSo = maSo;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 500);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDangNhap.setBounds(350, 229, 127, 43);
		contentPane.add(btnDangNhap);

		lblDangNhap = new JLabel("ĐĂNG NHẬP");
		lblDangNhap.setBackground(Color.WHITE);
		lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblDangNhap.setBounds(200, 30, 164, 49);
		contentPane.add(lblDangNhap);

		lblTK = new JLabel("Tài khoản");
		lblTK.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTK.setBounds(50, 90, 109, 37);
		contentPane.add(lblTK);

		lblMK = new JLabel("Mật khẩu");
		lblMK.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMK.setBounds(50, 157, 109, 32);
		contentPane.add(lblMK);

		txtTK = new JTextField();
		txtTK.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		//txtMK.setE
		txtTK.setBounds(150, 90, 327, 37);
		contentPane.add(txtTK);
		txtTK.setColumns(10);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnHuy.setBounds(150, 229, 127, 43);
		contentPane.add(btnHuy);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		passwordField.setEchoChar('*');
		passwordField.setBounds(150, 157, 327, 34);
		contentPane.add(passwordField);

		btnDangNhap.addActionListener(this);
		DataBase.getInstance().connect();



	}
	public JPanel getJPanel() {
		return contentPane;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals( btnDangNhap)){
			if(passwordField.getPassword().length==0 || txtTK.getText().toString().length() == 0)
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ dữ liệu");		
			
			else {
				char chPassword[] = passwordField.getPassword();
				String strPassword = new String(chPassword);
				ArrayList<TaiKhoan> list = dstk.timKiemTKNV(txtTK.getText().toString(),strPassword);
				ArrayList<TaiKhoan> list1 = dstk.timKiemTKSV(txtTK.getText().toString(),strPassword);

				//System.out.println(list);
				//System.out.println("\n");
				//System.out.println(list1);
				if(list.size()==0 && list1.size()==0) {
					JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại");
				}else {
					if(list.size()!=0) {
						dispose();
						gdNV = new GDChinh_NhanVien();
						gdNV.setVisible(true);
					}
					if(list1.size()!=0) {
						maSo = txtTK.getText().toString();
						
						//JOptionPane.showMessageDialog(null, "Dang nhap sinh vien");
						dispose();
//						System.out.println(maSo + "gdDN");
						gdSV =  new GDChinh_SV(maSo);
						gdSV.setVisible(true);
						//System.out.println(maSo);
					}
				}
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
}
