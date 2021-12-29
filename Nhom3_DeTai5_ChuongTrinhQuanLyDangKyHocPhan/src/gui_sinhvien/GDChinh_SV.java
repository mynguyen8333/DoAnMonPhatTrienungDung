package gui_sinhvien;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import gui_nhanvien.GD_NhanVien_TimLopHocPhan;
import gui_nhanvien.GD_NhanVien_TimMonHoc;

import javax.swing.JMenu;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class GDChinh_SV extends JFrame implements ActionListener{

	private JPanel pnXemTT,pnDKHP,pnXemKhung,pnlTimlHP,pnDoiMK, pnTrong, pnDangNhap,pnlTimMHP;
	private JMenuBar mnuBar;
	private JMenu mnuSinhVien, mnuHocPhan;
	private JMenuItem mniDMK, mniTT, mniDangxuat, mniDKHP, mniXemKhung, mniTimHP,mniTimMHP;
	private CardLayout cardLayout;
	private JPanel pnTong;
	private String massv;
	
	


//	public GDChinh_SV(String massv) throws HeadlessException {
//		super();
//		this.massv = massv;
//	}


	
	private static DangNhap dn = new DangNhap();
	
	public GDChinh_SV (String maSo) {
		this.massv = maSo;
		//System.out.println(massv+"gdc");
//		massv = dn.getMaSo();
//		System.out.println("ma sinh vien"+ massv);
		setTitle("Sinh viên");
		setSize(1900,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		mnuBar = new JMenuBar();
    	setJMenuBar(mnuBar);
    	
    	//Menu chinh
    	mnuBar.add(Box.createHorizontalStrut(30));
    	mnuBar.add(mnuSinhVien = new JMenu("Tài khoản"));
    	mnuSinhVien.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	mnuSinhVien.setMnemonic(KeyEvent.VK_T);
    	
    	mnuBar.add(Box.createHorizontalStrut(55));
    	mnuBar.add(mnuHocPhan = new JMenu("Học phần"));
    	mnuHocPhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	mnuHocPhan.setMnemonic(KeyEvent.VK_H);
    	
    	// menu Item
    	// menu Item Tai khoan
    	mnuSinhVien.add(mniDangxuat = new JMenuItem("Đăng xuất"));
    	mniDangxuat.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
    	mniDangxuat.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mnuSinhVien.add(mniTT = new JMenuItem("Xem thông tin cá nhân"));
    	mniTT.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
    	mniTT.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mnuSinhVien.add(mniDMK = new JMenuItem("Đổi mật khẩu"));
    	mniDMK.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK));
    	mniDMK.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	
    	// menu Item Hoc phan
    	mnuHocPhan.add(mniDKHP = new JMenuItem("Đăng ký học phần"));
    	mniDKHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    	mniDKHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	
    	mnuHocPhan.add(mniXemKhung = new JMenuItem("Xem chương trình khung"));
    	mniXemKhung.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniXemKhung.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.ALT_MASK));
    	
    	mnuHocPhan.add(mniTimHP = new JMenuItem("Tìm lớp học phần"));
    	mniTimHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniTimHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.ALT_MASK));
    	
    	mnuHocPhan.add(mniTimMHP = new JMenuItem("Tìm môn học phần"));
    	mniTimMHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniTimMHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F7, ActionEvent.ALT_MASK));
    	
    	mniDangxuat.addActionListener(this);
    	mniDMK.addActionListener(this);
    	mniTT.addActionListener(this);
    	mniDKHP.addActionListener(this);
    	mniTimHP.addActionListener(this);
    	mniXemKhung.addActionListener(this);
    	mniTimMHP.addActionListener(this);
 
    	
    	cardLayout= new CardLayout();
		pnTong = new JPanel(cardLayout);
		
		Trong giaodienDau = new Trong();
		pnTrong = giaodienDau.getJPanel();
		pnTrong.setVisible(true);
		pnTong.add(pnTrong);
		
		DangNhap giaodienDN = new DangNhap();
		pnDangNhap = giaodienDN.getJPanel();
		pnDangNhap.setVisible(true);
		pnTong.add(pnDangNhap,"DangNhap");
		
		XemTTSV giaodienXemTT = new XemTTSV(maSo);
		pnXemTT = giaodienXemTT.getJPanel();
		pnXemTT.setVisible(true);
		pnTong.add(pnXemTT,"XemThongTinCaNhan");
		
		Doimatkhau giaodienDMK = new Doimatkhau(maSo);
		pnDoiMK = giaodienDMK.getJPanel();
		pnDoiMK.setVisible(true);
		pnTong.add(pnDoiMK,"DoiMatKhau");
		
		GD_SV_DKHP giaodienDKHP = new GD_SV_DKHP(maSo);
		pnDKHP = giaodienDKHP.getJPanel();
		pnDKHP.setVisible(true);
		pnTong.add(pnDKHP,"DangKyHocPhan");
    	
		XemKhung giaodienXemKhung = new XemKhung(maSo);
		pnXemKhung = giaodienXemKhung.getJPanel();
		pnXemKhung.setVisible(true);
		pnTong.add(pnXemKhung,"XemChuongTrinhKhung");
		
//		TimKiemHP giaodienTimHP = new TimKiemHP();
//		pnTimHP = giaodienTimHP.getJPanel();
//		pnTimHP.setVisible(true);
//		pnTong.add(pnTimHP,"TimKiemHocPhan");
		
		GD_NhanVien_TimLopHocPhan giaodienTLHP = new GD_NhanVien_TimLopHocPhan();
		pnlTimlHP = giaodienTLHP.getJPanel();
		pnlTimlHP.setVisible(true);
		pnTong.add(pnlTimlHP,"TimLopHP");
		
		GD_NhanVien_TimMonHoc giaodienTMHP = new GD_NhanVien_TimMonHoc();
		pnlTimMHP = giaodienTMHP.getJPanel();
		pnlTimMHP.setVisible(true);
		pnTong.add(pnlTimMHP,"TimMHP");
		
		
		add(pnTong);
	}	
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(mniDangxuat)) {
			dispose();
			dn.setVisible(true);
		}else if(o.equals(mniDMK)){
			cardLayout.show(pnTong,"DoiMatKhau");
		}else if(o.equals(mniTT)) {
			cardLayout.show(pnTong, "XemThongTinCaNhan");
		}else if(o.equals(mniDKHP)) {
			cardLayout.show(pnTong, "DangKyHocPhan");
		}else if(o.equals(mniXemKhung)) {
			cardLayout.show(pnTong, "XemChuongTrinhKhung");	
		}else if(o.equals(mniTimHP)) {
			cardLayout.show(pnTong, "TimLopHP");
		}else if(o.equals(mniTimMHP)) {
			cardLayout.show(pnTong, "TimMHP");
		}
	}
	public static void main(String[] args) {
		 
	}
}

