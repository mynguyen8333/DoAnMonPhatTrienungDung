package gui_sinhvien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.LichHocDao;
import entity.ChiTietLopHocPhan;
import entity.LichHoc;
import entity.MonHocPhan;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GD_XemLichHoc extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String massv,nam;
	private int hocky;
	private LichHocDao lhd = new LichHocDao();
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GD_XemLichHoc frame = new GD_XemLichHoc();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public GD_XemLichHoc( String masv, String Nam, int HocKy) {
		this.massv = masv;
		this.nam =Nam;
		this.hocky = HocKy;	
		setTitle("Xem Lịch Học");
		setBounds(100, 100, 1850, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlTong = new JPanel();
		contentPane.add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 83, 1268, 525);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers1 = "Ngày học;Tiết học;Tên môn học;Phòng học;Nhóm; Ngày bắt Đầu".split(";");
		tableModel = new DefaultTableModel(headers1,0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTieuDe = new JLabel("XEM LỊCH HỌC ");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblTieuDe.setBounds(583, 30, 273, 40);
		pnlTong.add(lblTieuDe);
		
		DataBase.getInstance().connect();
		taiBangHP();
		

	}
	
	public void taiBangHP() {
		ArrayList<LichHoc> listLH = lhd.LayLichHoc(massv,  hocky, nam); 
		for (LichHoc ct : listLH) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			String date2 = sdf1.format(ct.getNgaybatdau());
			String [] rowData1 = {ct.getNgayHoc(),ct.getTietHoc(),ct.getTenMonHoc(),ct.getPhongHoc(),ct.getNhom(),date2};
			tableModel.addRow(rowData1);
		}
		table.setModel(tableModel);
	}
	
	

}
