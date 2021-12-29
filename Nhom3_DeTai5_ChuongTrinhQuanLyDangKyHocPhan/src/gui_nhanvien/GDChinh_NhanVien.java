package gui_nhanvien;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import gui_sinhvien.DangNhap;

public class GDChinh_NhanVien extends JFrame implements ActionListener{
	
	
	
	private JMenuBar mnu;
	private JMenu menuHeThong, menuCapNhat, menuThongKe, menuTimKiem,menuXuLy;
	private JMenuItem mniDangXuat, mniCapNhatMHP, mniCapNhatLHP,mniCapNhatLichHoc,mniCapNhatSV,
				mniThongKeLHP, mniThongKeSLSV, mniTimKiemLHP,mniTimKiemMHP,
				mniCapNhatGV,mniTimKiemSV,mniTimKiemGV,mniXepCTKhung,
				mniCapNhatKhoa,mniCapNhatCN,mniChiaChuyenNganh,mniDMKhoa,mniDMChuyenNganh,
				mniDMGiangVien,mniCapNhatNam,mniCapNhatHocKy,mniCapNhatPhongHoc;
	private JPanel pnlCapNhatMHP,pnlCapNhatLHP,pnlThongKeLHP,pnlThongKeSV,
				pnlTimKiemLHP,pnlTimKiemMHP,pnlRong,pnlCapNhatLichHoc,
				pnlCapNhatSV,pnlCapNhatGV,pnlTimKiemGV,pnlTimKiemSV,pnlXepCTKhung,pnlCapNhatKhoa,
				pnlCapNhatCN,pnlChiaChuyenNganh,pnlDMGiangVien,pnlDMKhoa,pnlDMChuyenNganh,pnlCapnhatNam,
				pnlCapnhatHK,pnlCapnhatPhongHoc;
	
	private CardLayout cardLayout;
	private JPanel pane;
	private static DangNhap dn = new DangNhap();
	private GD_NhanVien_CapNhatLHP giaodienCNLHP;
	private GD_NhanVien_XepLich giaodienxeplich;
	
	public GDChinh_NhanVien() {
		setTitle("Quản lý đăng kí học phần");
		setSize(1900,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		mnu = new JMenuBar();
		mnu.setBounds(0, 0, 1300, 200);
    	setJMenuBar(mnu);
    	
    	//Menu chinh
    	mnu.add(Box.createHorizontalStrut(30));
    	
    	mnu.add(menuHeThong = new JMenu("Hệ thống"));
    	menuHeThong.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	menuHeThong.setMnemonic(KeyEvent.VK_H);
    	
    	
    	mnu.add(Box.createHorizontalStrut(55));
		JMenu mnuDanhMuc = new JMenu("Danh Mục");
		mnuDanhMuc.setMnemonic(KeyEvent.VK_D);
		mnuDanhMuc.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnu.add(mnuDanhMuc);
    	
    	mnu.add(Box.createHorizontalStrut(55));
    	mnu.add(menuCapNhat = new JMenu("Cập nhật"));
    	menuCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	menuCapNhat.setMnemonic(KeyEvent.VK_C);
    	mnu.add(Box.createHorizontalStrut(55));
    	

    	mnu.add(menuXuLy= new JMenu("Xử lý"));
    	menuXuLy.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	menuXuLy.setMnemonic(KeyEvent.VK_X);
    	mnu.add(Box.createHorizontalStrut(55));
    	
    	mnu.add(menuThongKe = new JMenu("Thống kê"));
    	menuThongKe.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	mnu.add(Box.createHorizontalStrut(55));
    	menuThongKe.setMnemonic(KeyEvent.VK_T);
    	
    	mnu.add(menuTimKiem = new JMenu("Tìm kiếm"));
    	menuTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 16));
    	menuTimKiem.setMnemonic(KeyEvent.VK_K);
    	
    	// menu Item
    	// menu Item He Thong
    	menuHeThong.add(mniDangXuat = new JMenuItem("Đăng xuất"));
    	mniDangXuat.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniDangXuat.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
    	
    	// menu Item Danh Muc
    	mnuDanhMuc.add(mniDMKhoa = new JMenuItem("Khoa"));
    	mniDMKhoa.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniDMKhoa.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
    	mnuDanhMuc.add(mniDMChuyenNganh = new JMenuItem("Chuyên ngành"));
    	mniDMChuyenNganh.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniDMChuyenNganh.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK));
    	mnuDanhMuc.add(mniDMGiangVien = new JMenuItem("Giảng Viên"));
    	mniDMGiangVien.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniDMGiangVien.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    	
    	// menu Item Cap nhat
    	menuCapNhat.add(mniCapNhatKhoa = new JMenuItem("Khoa"));
    	mniCapNhatKhoa.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatKhoa.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.ALT_MASK));
    	menuCapNhat.add(mniCapNhatCN = new JMenuItem("Chuyên ngành"));
    	mniCapNhatCN.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatCN.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.ALT_MASK));
    	menuCapNhat.add(mniCapNhatSV = new JMenuItem("Sinh Viên"));
    	mniCapNhatSV.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatSV.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F7, ActionEvent.ALT_MASK));
    	menuCapNhat.add(mniCapNhatGV = new JMenuItem("Giảng viên"));
    	mniCapNhatGV.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatGV.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F8, ActionEvent.ALT_MASK));
    	menuCapNhat.add(mniCapNhatMHP = new JMenuItem("Môn học phần"));
    	mniCapNhatMHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatMHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.ALT_MASK));
    	menuCapNhat.add(mniCapNhatLHP = new JMenuItem("Lớp học phần"));
    	mniCapNhatLHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatLHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F10, ActionEvent.ALT_MASK));
    	
    	menuCapNhat.add(mniCapNhatNam = new JMenuItem("Năm học"));
    	mniCapNhatNam.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	menuCapNhat.add(mniCapNhatHocKy = new JMenuItem("Học Kỳ"));
    	mniCapNhatHocKy.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	menuCapNhat.add(mniCapNhatPhongHoc = new JMenuItem("Phòng học"));
    	mniCapNhatPhongHoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	
    	//menu xu ly
    	menuXuLy.add(mniXepCTKhung = new JMenuItem("Xếp chương trình khung"));
    	mniXepCTKhung.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniXepCTKhung.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.ALT_MASK));
    	menuXuLy.add(mniChiaChuyenNganh = new JMenuItem("Chia chuyên ngành cho sinh viên"));
    	mniChiaChuyenNganh.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniChiaChuyenNganh.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.ALT_MASK));
    	menuXuLy.add(mniCapNhatLichHoc = new JMenuItem("Xếp lịch học"));
    	mniCapNhatLichHoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniCapNhatLichHoc.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    	
    	
    	// menu Item Thong Ke
    	menuThongKe.add(mniThongKeLHP = new JMenuItem("Số lượng lớp học phần"));
    	mniThongKeLHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniThongKeLHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
    	
    	menuThongKe.add(mniThongKeSLSV = new JMenuItem("Số lượng sinh viên"));
    	mniThongKeSLSV.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniThongKeSLSV.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
      	
    	
    	// menu Item Tim Kiem
    	menuTimKiem.add(mniTimKiemSV = new JMenuItem("Sinh viên"));
    	mniTimKiemSV.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniTimKiemSV.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
    	menuTimKiem.add(mniTimKiemGV = new JMenuItem("Giảng viên"));
    	mniTimKiemGV.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniTimKiemGV.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
    	menuTimKiem.add(mniTimKiemMHP = new JMenuItem("Môn học phần"));
    	mniTimKiemMHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniTimKiemMHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));
    	menuTimKiem.add(mniTimKiemLHP = new JMenuItem("Lớp học phần"));
    	mniTimKiemLHP.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	mniTimKiemLHP.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));
    	
    	
    	mniDangXuat.addActionListener(this);
    	mniCapNhatMHP.addActionListener(this);
    	mniCapNhatLHP.addActionListener(this);
    	mniThongKeLHP.addActionListener(this);
    	mniThongKeSLSV.addActionListener(this);
    	mniTimKiemLHP.addActionListener(this);
    	mniTimKiemMHP.addActionListener(this);
    	mniCapNhatLichHoc.addActionListener(this);
    	mniCapNhatSV.addActionListener(this);
    	mniCapNhatGV.addActionListener(this);
    	mniTimKiemGV.addActionListener(this);
    	mniTimKiemSV.addActionListener(this);
    	mniCapNhatKhoa.addActionListener(this);
    	mniCapNhatCN.addActionListener(this);
    	mniXepCTKhung.addActionListener(this);
    	mniChiaChuyenNganh.addActionListener(this);
    	mniDMChuyenNganh.addActionListener(this);
    	mniDMGiangVien.addActionListener(this);
    	mniDMKhoa.addActionListener(this);
    	mniCapNhatNam.addActionListener(this);
    	mniCapNhatHocKy.addActionListener(this);
    	mniCapNhatPhongHoc.addActionListener(this);
    	
    	
    	cardLayout= new CardLayout();
		pane = new JPanel(cardLayout);
		
		GD_rong1 giaodienRong = new GD_rong1();
		pnlRong = giaodienRong.getJPanel();
		pnlRong.setVisible(true);
		pane.add(pnlRong);

		
		GD_NhanVien_CapNhatKhoa giaodienCNKH = new GD_NhanVien_CapNhatKhoa();
		pnlCapNhatKhoa = giaodienCNKH.getJPanel();
		pnlCapNhatKhoa.setVisible(true);
		pane.add(pnlCapNhatKhoa,"CapNhatKH");
		
		GD_NhanVien_CapNhatChuyenNganh giaodienCNCN = new GD_NhanVien_CapNhatChuyenNganh();
		pnlCapNhatCN = giaodienCNCN.getJPanel();
		pnlCapNhatCN.setVisible(true);
		pane.add(pnlCapNhatCN,"CapNhatCN");
		
		GD_NhanVien_XepChuongTrinhKhung giaodienXCTK = new GD_NhanVien_XepChuongTrinhKhung();
		pnlXepCTKhung = giaodienXCTK.getJPanel();
		pnlXepCTKhung.setVisible(true);
		pane.add(pnlXepCTKhung,"XepCTKH");
		
		GD_NhanVien_ChiaChuyenNganh giaodienCCN = new GD_NhanVien_ChiaChuyenNganh();
		pnlChiaChuyenNganh = giaodienCCN.getJPanel();
		pnlChiaChuyenNganh.setVisible(true);
		pane.add(pnlChiaChuyenNganh,"ChiaCN");
		
		GD_NhanVien_CapNhatSinhVien giaodienCNSV = new GD_NhanVien_CapNhatSinhVien();
		pnlCapNhatSV = giaodienCNSV.getJPanel();
		pnlCapNhatSV.setVisible(true);
		pane.add(pnlCapNhatSV,"CapNhatSV");
		
		giaodienCNLHP = new GD_NhanVien_CapNhatLHP();
		pnlCapNhatLHP = giaodienCNLHP.getJPanel();
		pnlCapNhatLHP.setVisible(true);
		pane.add(pnlCapNhatLHP,"CapnhatLHP");
		
		GD_NhanVien_CapNhatMHP giaodienCNMHP = new GD_NhanVien_CapNhatMHP();
		pnlCapNhatMHP = giaodienCNMHP.getJPanel();
		pnlCapNhatMHP.setVisible(true);
		pane.add(pnlCapNhatMHP,"CapnhatMHP");
		
		giaodienxeplich = new GD_NhanVien_XepLich();
		pnlCapNhatLichHoc = giaodienxeplich.getJPanel();
		pnlCapNhatLichHoc.setVisible(true);
		pane.add(pnlCapNhatLichHoc,"CapnhatLH");

		GD_NhanVien_TimLopHocPhan giaodientimLHP = new GD_NhanVien_TimLopHocPhan();
		pnlTimKiemLHP = giaodientimLHP.getJPanel();
		pnlTimKiemLHP.setVisible(true);
		pane.add(pnlTimKiemLHP,"TimKiemLHP");
		
		GD_NhanVien_TimMonHoc giaodientimMHP = new GD_NhanVien_TimMonHoc();
		pnlTimKiemMHP = giaodientimMHP.getJPanel();
		pnlTimKiemMHP.setVisible(true);
		pane.add(pnlTimKiemMHP,"TimKiemMHP");
		
		GD_NhanVien_ThongKeSoLuongSV giaodienTKSLSV = new GD_NhanVien_ThongKeSoLuongSV();
		pnlThongKeSV = giaodienTKSLSV.getJPanel();
		pnlThongKeSV.setVisible(true);
		pane.add(pnlThongKeSV,"ThongKeSV");
		
		GD_NhanVien_ThongKeLopHocPhan giaodienTKSLLHP = new GD_NhanVien_ThongKeLopHocPhan();
		pnlThongKeLHP = giaodienTKSLLHP.getJPanel();
		pnlThongKeLHP.setVisible(true);
		pane.add(pnlThongKeLHP,"ThongKeLHP");
		
		GD_NhanVien_CapNhatGV giaodienCNGV = new GD_NhanVien_CapNhatGV();
		pnlCapNhatGV = giaodienCNGV.getJPanel();
		pnlCapNhatGV.setVisible(true);
		pane.add(pnlCapNhatGV,"CapNhatGV");
		
		GD_NhanVien_TimKiemSV giaodienTKSV = new GD_NhanVien_TimKiemSV();
		pnlTimKiemSV = giaodienTKSV.getJPanel();
		pnlTimKiemSV.setVisible(true);
		pane.add(pnlTimKiemSV,"TimKiemSV");
		
		GD_NhanVien_TimKiemGV giaodienTKGV = new GD_NhanVien_TimKiemGV();
		pnlTimKiemGV = giaodienTKGV.getJPanel();
		pnlTimKiemGV.setVisible(true);
		pane.add(pnlTimKiemGV,"TimKiemGV");
		
		GD_NhanVien_DanhMucChuyenNganh giaodienDMCN = new GD_NhanVien_DanhMucChuyenNganh();
		pnlDMChuyenNganh = giaodienDMCN.getJPanel();
		pnlDMChuyenNganh.setVisible(true);
		pane.add(pnlDMChuyenNganh,"DanhMucCN");
		
		GD_NhanVien_DanhMucGiangVien giaodienDMGV = new GD_NhanVien_DanhMucGiangVien();
		pnlDMGiangVien = giaodienDMGV.getJPanel();
		pnlDMGiangVien.setVisible(true);
		pane.add(pnlDMGiangVien,"DanhMucGV");
		
		GD_NhanVien_DanhMucKhoa giaodienDMKH = new GD_NhanVien_DanhMucKhoa();
		pnlDMKhoa = giaodienDMKH.getJPanel();
		pnlDMKhoa.setVisible(true);
		pane.add(pnlDMKhoa,"DanhMucKH");
		
		GD_NhanVien_CapNhatNam giaodienCNNam = new GD_NhanVien_CapNhatNam();
		pnlCapnhatNam = giaodienCNNam.getJPanel();
		pnlCapnhatNam.setVisible(true);
		pane.add(pnlCapnhatNam,"CapNhatNamHoc");
		
		GD_NhanVien_CapNhatHocKy giaodienCNHK = new GD_NhanVien_CapNhatHocKy();
		pnlCapnhatHK = giaodienCNHK.getJPanel();
		pnlCapnhatHK.setVisible(true);
		pane.add(pnlCapnhatHK,"CapNhatHocKy");
		
		GD_NhanVien_CapNhatPhong giaodienCNP = new GD_NhanVien_CapNhatPhong();
		pnlCapnhatPhongHoc = giaodienCNP.getJPanel();
		pnlCapnhatPhongHoc.setVisible(true);
		pane.add(pnlCapnhatPhongHoc,"CapNhatPhong");
		
		add(pane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(mniDangXuat)) {
			dispose();
			dn.setVisible(true);			
		}else if(o.equals(mniCapNhatSV)){
			cardLayout.show(pane,"CapNhatSV");
		}else if(o.equals(mniCapNhatLHP)){
			giaodienCNLHP.dulieutenmon();
			cardLayout.show(pane,"CapnhatLHP");
		}else if(o.equals(mniCapNhatMHP)){
			cardLayout.show(pane,"CapnhatMHP");
		}else if(o.equals(mniCapNhatLichHoc)){
			giaodienxeplich.dulieuGiangVien();
			cardLayout.show(pane,"CapnhatLH");
		}else if(o.equals(mniTimKiemLHP)){
			cardLayout.show(pane,"TimKiemLHP");
		}else if(o.equals(mniTimKiemMHP)){
			cardLayout.show(pane,"TimKiemMHP");
		}else if(o.equals(mniThongKeLHP)){
			cardLayout.show(pane,"ThongKeLHP");
		}else if(o.equals(mniThongKeSLSV)){
			cardLayout.show(pane,"ThongKeSV");
		}else if(o.equals(mniCapNhatGV)){
			cardLayout.show(pane,"CapNhatGV");
		}else if(o.equals(mniTimKiemGV)){
			cardLayout.show(pane,"TimKiemGV");
		}else if(o.equals(mniTimKiemSV)){
			cardLayout.show(pane,"TimKiemSV");
		}else if(o.equals(mniCapNhatCN)){
			cardLayout.show(pane,"CapNhatCN");
		}else if(o.equals(mniCapNhatKhoa)){
			cardLayout.show(pane,"CapNhatKH");
		}else if(o.equals(mniXepCTKhung)){
			cardLayout.show(pane,"XepCTKH");
		}else if(o.equals(mniChiaChuyenNganh)){
			cardLayout.show(pane,"ChiaCN");
		}else if(o.equals(mniDMChuyenNganh)){
			cardLayout.show(pane,"DanhMucCN");
		}else if(o.equals(mniDMGiangVien)){
			cardLayout.show(pane,"DanhMucGV");
		}else if(o.equals(mniDMKhoa)){
			cardLayout.show(pane,"DanhMucKH");
		}else if(o.equals(mniCapNhatNam)){
			cardLayout.show(pane,"CapNhatNamHoc");
		}else if(o.equals(mniCapNhatHocKy)){
			cardLayout.show(pane,"CapNhatHocKy");
		}else if(o.equals(mniCapNhatPhongHoc)){
			cardLayout.show(pane,"CapNhatPhong");
		}

	}
	
	public static void main(String[] args) {
		new GDChinh_NhanVien().setVisible(true);
		
	}
	
	
	

}
