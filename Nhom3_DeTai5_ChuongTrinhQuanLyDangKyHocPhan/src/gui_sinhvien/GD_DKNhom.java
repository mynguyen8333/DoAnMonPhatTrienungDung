package gui_sinhvien;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import dao.ChiTietLopHocPhanDao;
import dao.DataBase;
import dao.LopHocPhanDaDangKyDao;
import dao.LopHocPhanDao;
import dao.PhieuDangKyLopHPDao;
import entity.ChiTietLopHocPhan;
import entity.LopHocPhanDaDangKy;

import javax.swing.JButton;

public class GD_DKNhom extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable tblThucHanh, tblLyThuyet;
	private DefaultTableModel tblModel, tblModel1;
	private JScrollPane scrLopHP,scrLichHoc;
	private JButton btnHuy;
	private	JButton btnDangKy;

	private   String massv;
	private   String maLop;
	private		int maHK;
	String nam;

	private PhieuDangKyLopHPDao pdk = new PhieuDangKyLopHPDao();
	private ChiTietLopHocPhanDao dsct = new ChiTietLopHocPhanDao();
	private LopHocPhanDaDangKyDao dsLHP_Da_DK = new LopHocPhanDaDangKyDao();
	private LopHocPhanDao dslhp = new LopHocPhanDao();

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					GD_DKNhom frame = new GD_DKNhom(massv, maLop);
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
	public GD_DKNhom(String mssv, String maLopHP, int maHK, String nam) {
		this.massv = mssv;
		this.maLop= maLopHP;
		this.maHK = maHK;
		this.nam=nam;



		//System.out.println(maHK +"/////   " +nam);

		setTitle("Đăng ký nhóm");
		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1850, 700);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCcLpHc = new JLabel("ĐĂNG KÍ NHÓM");
		lblCcLpHc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCcLpHc.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblCcLpHc.setBounds(519, 34, 446, 32);
		contentPane.add(lblCcLpHc);

		scrLopHP = new JScrollPane();
		scrLopHP.setBounds(67, 120, 1271, 190);
		contentPane.add(scrLopHP);


		tblThucHanh = new JTable();
		String[] headers1 = "Tên nhóm;Giảng viên;Ngày học;Phòng học;Tiết học;Ngày bắt đầu".split(";");
		tblModel = new DefaultTableModel(headers1,0);
		tblThucHanh = new JTable(tblModel);
		tblThucHanh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrLopHP.setViewportView(tblThucHanh);

		btnHuy = new JButton("Trở về");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnHuy.setBounds(1030, 567, 135, 32);
		contentPane.add(btnHuy);

		scrLichHoc = new JScrollPane();
		scrLichHoc.setBounds(67, 378, 1271, 178);
		contentPane.add(scrLichHoc);

		//maNhom, maLop1, tietHoc, ngayHoc, phongHoc, maGV,ngayBD
		tblLyThuyet = new JTable();
		String[] headers2 = "Tên nhóm;Giảng viên;Ngày học;Phòng học;Tiết học;Ngày bắt đầu".split(";");
		tblModel1 = new DefaultTableModel(headers2,0);
		tblLyThuyet = new JTable(tblModel1);
		tblLyThuyet.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrLichHoc.setViewportView(tblLyThuyet);

		btnDangKy = new JButton("Đăng Ký");
		btnDangKy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDangKy.setBounds(1203, 567, 135, 32);
		contentPane.add(btnDangKy);

		JLabel lblNhmLThuyt = new JLabel("Nhóm Thực Hành");
		lblNhmLThuyt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNhmLThuyt.setBounds(67, 75, 159, 31);
		contentPane.add(lblNhmLThuyt);

		JLabel lblNhmThcHnh = new JLabel("Nhóm Lý Thuyết");
		lblNhmThcHnh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNhmThcHnh.setBounds(67, 335, 199, 32);
		contentPane.add(lblNhmThcHnh);

		//		tblLyThuyet.getSelectionModel().addSelectionInterval(0, 0);


		btnDangKy.addActionListener(this);
		btnHuy.addActionListener(this);
		DataBase.getInstance().connect();

		//		System.out.println(massv + maLop +"nhom");
		taiBang();

	}	
	public void taiBang(){
		// TODO Auto-generated method stub
		ArrayList<ChiTietLopHocPhan> listct = dsct.LayDSChiTiet(maLop.toString()); //?????
		//System.out.println(listct);

		tblModel.setRowCount(0);
		for (ChiTietLopHocPhan ct : listct) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String date = sdf.format(ct.getNgayBD());

			//			System.out.println(ct.getMaNhom());
			String [] rowData1 = {ct.getMaNhom(),ct.getMaGV(),ct.getNgayHoc(),ct.getPhongHoc(),ct.getTietHoc(), date};
			if(ct.getMaNhom().equals("Thực Hành 1" )|| ct.getMaNhom().equals("Thực Hành 2"))
				tblModel.addRow(rowData1);
			if(ct.getMaNhom().equals("Lý Thuyết"))
				tblModel1.addRow(rowData1);
		}
		tblThucHanh.setModel(tblModel);
		tblLyThuyet.setModel(tblModel1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnHuy))
		{
			dispose();
			//				GDChinh_SV chinh = new GDChinh_SV(massv);

		}
		if(o.equals(btnDangKy)) {

			int row = tblThucHanh.getSelectedRow();
			int row2 = tblLyThuyet.getSelectedRow();
			System.out.println("row"+row);
			boolean list3 = false ,list4= false;

			if( row2<0) 												 /// xet dieu kien chua chon ly thuyet
				JOptionPane.showMessageDialog(this, "bạn chưa chọn nhóm học phần lý thuyết");
			if(row <0 && tblThucHanh.getRowCount()>0) 					// xet dieu kien co nhom thuc hanh nhung khong chon
				JOptionPane.showMessageDialog(this, "bạn chưa chọn nhóm học phần thực hành");

			if(row >=0) {												// kiem tra xem lich thuc hanh co trung khong
				list3 = dsLHP_Da_DK.KTTG_DaDK(massv, maHK , nam, tblThucHanh.getValueAt(row, 4)+"", tblThucHanh.getValueAt(row, 2)+"");
				System.out.println("cot lay"+tblThucHanh.getValueAt(row, 4));
				System.out.println("list3 "+list3);
				if(list3)
					JOptionPane.showMessageDialog(this, "nhóm thực hành trùng lịch với môn khác bạn đã đăng ký");

			}
			System.out.println("row2"+ row2);
			if(row2>=0) {												// kiem tra xem lich ly thuyet co trung khong
				list4 = dsLHP_Da_DK.KTTG_DaDK(massv, maHK , nam, tblLyThuyet.getValueAt(row2, 4)+"",tblLyThuyet.getValueAt(row2, 2)+"");
				System.out.println("list4 "+list4);
				if(list4)
					JOptionPane.showMessageDialog(this, "nhóm lý thuyết trùng lịch với môn khác bạn đã đăng ký");
			}

			System.out.println("int "+tblThucHanh.getRowCount());



			//				System.out.println("list3.size() =" + list3.size() +"list4.size() =" + list4.size());
			if(row2>=0     &&  !(list4)  &&
					(   ( row ==-1 && tblThucHanh.getRowCount() == 0) ||  (row > -1 && tblThucHanh.getRowCount() > 0  && 	!(list3)  )   )  )   {			// truong hop hop le
				System.out.println("khong trung");
				int rowT1 = tblThucHanh.getSelectedRow();
				int rowT2 = tblLyThuyet.getSelectedRow();
				if(row >= 0)
					if( pdk.ThemPhieuDangKy(massv, maLop, tblThucHanh.getValueAt(rowT1, 0).toString()) )
						JOptionPane.showMessageDialog(this, "đăng ký nhóm thực hành thành công");
				if(rowT2 >= 0)
					if( pdk.ThemPhieuDangKy(massv, maLop, tblLyThuyet.getValueAt(rowT2, 0).toString()) )
						JOptionPane.showMessageDialog(this, "đăng ký nhóm lý thuyết thành công");
						int a = dslhp.LaySiSoDK(maLop);
						int b = a+1;
						dslhp.capNhatSiSo(b, maLop);
			}
		}
	}


}

