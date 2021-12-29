package gui_sinhvien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.MonHocPhanDao;
import dao.XemKhungDao;
import entity.ChiTietLopHocPhan;
import entity.ChuongTrinhKhung;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

public class XemKhung extends JFrame {

	private JPanel contentPane;
	private JTable tblKhung;
	private JLabel lblKhung;
	private JScrollPane scrKhung;
	private DefaultTableModel tableModel;
	private String massv;
	private XemKhungDao xkd = new XemKhungDao();
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					XemKhung frame = new XemKhung();
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
	public XemKhung(String maSo) {
		this.massv = maSo;
		//System.out.println(maSo+"xemkhung");
		setTitle("Xem ch\u01B0\u01A1ng tr\u00ECnh khung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1850, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrKhung = new JScrollPane();
		scrKhung.setBounds(46, 116, 1287, 564);
		contentPane.add(scrKhung);
		
	
		tblKhung = new JTable();
		String[] headers = "Mã học phần;Tên học phần;Học phần yêu cầu;Học Kỳ".split(";");
		tableModel = new  DefaultTableModel(headers,0);
		tblKhung = new JTable(tableModel);
		tblKhung.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrKhung.setViewportView(tblKhung);
		
//		tblKhung = new JTable();
//		tblKhung.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		tblKhung.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"STT", "M\u00E3 h\u1ECDc ph\u1EA7n", "T\u00EAn h\u1ECDc ph\u1EA7n", "S\u1ED1 t\u00EDn ch\u1EC9", "H\u1ECDc k\u1EF3"
//			}
//		));
		
		lblKhung = new JLabel("CHƯƠNG TRÌNH KHUNG");
		lblKhung.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblKhung.setBounds(555, 64, 398, 41);
		contentPane.add(lblKhung);
		DataBase.getInstance().connect();
		
		ArrayList<ChuongTrinhKhung> listct = xkd.layChuongTrinhKhung(maSo);
		
		tableModel.setRowCount(0);
		for (ChuongTrinhKhung ct : listct) {
			String tenMon = dsmh.LayTenMon(ct.getHocPhanTQ());
			String [] rowData1 = {ct.getMaHp(), ct.getTenHp(), tenMon, ct.getHocKy()+""};
			tableModel.addRow(rowData1);
		}
		tblKhung.setModel(tableModel);
		
		
		
	}

	public JPanel getJPanel() {
		return contentPane;
		
	}
}
