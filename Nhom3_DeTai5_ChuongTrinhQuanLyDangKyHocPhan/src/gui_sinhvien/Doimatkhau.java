package gui_sinhvien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DataBase;
import dao.TaiKhoanDao;
import entity.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Doimatkhau extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JLabel lblHeader, lblMKCu, lblMKMoi, lblMKMoiLai;
	private JButton btnDMK;
	private String massv;
	private TaiKhoanDao dstk = new TaiKhoanDao();
	private JPasswordField txtMKCu;
	private JPasswordField txtMKMoi;
	private JPasswordField txtNhapLaiMK;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Doimatkhau frame = new Doimatkhau();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Doimatkhau(String maSo) {
		this.massv = maSo;
		//System.out.println(maSo+"doimk");
		setTitle("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 700);
		contentPane = new JPanel();
//		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblHeader = new JLabel("ĐỔI MẬT KHẨU");
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHeader.setBounds(650, 63, 211, 43);
		contentPane.add(lblHeader);
		
		lblMKCu = new JLabel("Mật khẩu cũ:");
		lblMKCu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMKCu.setBounds(400, 150, 186, 32);
		contentPane.add(lblMKCu);
		
		lblMKMoi = new JLabel("Mật khẩu mới:");
		lblMKMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMKMoi.setBounds(400, 193, 186, 34);
		contentPane.add(lblMKMoi);
		
		lblMKMoiLai = new JLabel("Nhập lại mật khẩu mới:");
		lblMKMoiLai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMKMoiLai.setBounds(400, 238, 186, 30);
		contentPane.add(lblMKMoiLai);
		
		btnDMK = new JButton("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		btnDMK.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDMK.setBounds(901, 303, 147, 32);
		contentPane.add(btnDMK);
		
		txtMKCu = new JPasswordField();
		txtMKCu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMKCu.setBounds(650, 150, 398, 29);
		contentPane.add(txtMKCu);
		
		txtMKMoi = new JPasswordField();
		txtMKMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMKMoi.setBounds(650, 195, 398, 31);
		contentPane.add(txtMKMoi);
		
		txtNhapLaiMK = new JPasswordField();
		txtNhapLaiMK.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNhapLaiMK.setBounds(650, 238, 398, 30);
		contentPane.add(txtNhapLaiMK);
		DataBase.getInstance().connect();
		btnDMK.addActionListener(this);
	}
	public JPanel getJPanel() {
		return contentPane;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDMK)) {
			char Mkcu[] = txtMKCu.getPassword();
			String MKcu1 = new String(Mkcu);
			
			char Mkmoi[] = txtMKMoi.getPassword();
			String MKmoi1 = new String(Mkmoi);
			
			char MkNhaplai[] = txtNhapLaiMK.getPassword();
			String MKNhapLai1 = new String(MkNhaplai);
			
			
			if(MKcu1.length()==0|| MKmoi1.length()==0||MKNhapLai1.length()==0) {
				JOptionPane.showMessageDialog(null, "Chưa nhật đủ dữ liệu");
			}else {
				ArrayList<TaiKhoan> list = dstk.timKiemTKSV(massv.trim(),MKcu1);
				if(list.size()!=0) {
					if(MKmoi1.equalsIgnoreCase(MKNhapLai1)) {
						int hoinhac = JOptionPane.showConfirmDialog(null,"Bạn có chắc không","Chú ý",JOptionPane.YES_NO_OPTION);
						if(hoinhac ==JOptionPane.YES_OPTION) {
							if(dstk.update(massv,MKNhapLai1)) {
								JOptionPane.showMessageDialog(this, "Cập nhật thành công");
							}else {
								JOptionPane.showMessageDialog(this,"Sai ở đâu đó");
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Hai mật khẩu mới không trùng");
						txtMKMoi.setText("");
						txtNhapLaiMK.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Nhập sai mật khẩu cũ");
					txtMKCu.selectAll();
				}
			}
			
		}
		
	}
}
