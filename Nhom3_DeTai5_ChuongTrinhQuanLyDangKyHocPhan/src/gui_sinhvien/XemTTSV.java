package gui_sinhvien;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DataBase;
import dao.SinhVienDao;
import entity.MonHocPhan;
import entity.SinhVien;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class XemTTSV extends JFrame{

	private JPanel contentPane;
	private JTextField txtMSSV, txtHoTen, txtDiaChi, txtNgaySinh, txtSDT;
	private JLabel lblHeader, lblMSSV, lblHoTen, lblDiaChi, lblNgaySinh, lblSDT,lblIcon;
	private String massv;
	private SinhVienDao dssv = new SinhVienDao();
	private JLabel lblGioiTinh;
	private JTextField txtGioiTinh;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					XemTTSV frame = new XemTTSV();
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
	public XemTTSV(String maSo) {
		//System.out.println(maSo+"xem thong tin");
		this.massv = maSo;
		setTitle("Th\u00F4ng tin sinh vi\u00EAn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1850, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHeader = new JLabel("THÔNG TIN SINH VIÊN");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHeader.setBounds(500, 45, 356, 31);
		contentPane.add(lblHeader);
		
		lblMSSV = new JLabel("Mã số sinh viên:");
		lblMSSV.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMSSV.setBounds(200, 160, 129, 43);
		contentPane.add(lblMSSV);
		
		lblHoTen = new JLabel("H\u1ECD v\u00E0 t\u00EAn:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHoTen.setBounds(200, 210, 129, 40);
		contentPane.add(lblHoTen);
		
		lblDiaChi = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDiaChi.setBounds(200, 310, 129, 40);
		contentPane.add(lblDiaChi);
		
		lblNgaySinh = new JLabel("Ng\u00E0y sinh: \r\n");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgaySinh.setBounds(200, 360, 129, 46);
		contentPane.add(lblNgaySinh);
		
		lblSDT = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i: ");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSDT.setBounds(200, 410, 128, 40);
		contentPane.add(lblSDT);
		
		txtMSSV = new JTextField();
		txtMSSV.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMSSV.setEditable(false);
		txtMSSV.setBounds(400, 160, 547, 31);
		contentPane.add(txtMSSV);
		txtMSSV.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(400, 210, 547, 31);
		contentPane.add(txtHoTen);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(400, 310, 547, 31);
		contentPane.add(txtDiaChi);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(400, 360, 547, 31);
		contentPane.add(txtNgaySinh);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(400, 410, 547, 31);
		contentPane.add(txtSDT);
		
		lblGioiTinh = new JLabel("Giới tính\r\n");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGioiTinh.setBounds(200, 260, 129, 40);
		contentPane.add(lblGioiTinh);
		
		txtGioiTinh = new JTextField();
		txtGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(400, 260, 547, 34);
		contentPane.add(txtGioiTinh);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(1041, 141, 307, 283);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/avatar1.png")));
		contentPane.add(lblNewLabel);
		
		DataBase.getInstance().connect();
		updatetxt();
		
	}
	public JPanel getJPanel() {
		return contentPane;

	}
	
	public void updatetxt() {
		ArrayList<SinhVien> ds = dssv.LaySinhVienTheoMa(massv);
		for (SinhVien sinhVien : ds) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String date = sdf.format(sinhVien.getNgaySinh());
			txtDiaChi.setText(sinhVien.getDiaChi());
			txtHoTen.setText(sinhVien.getHoTen());
			txtMSSV.setText(sinhVien.getMaSV());
			txtNgaySinh.setText(date);
			txtSDT.setText(sinhVien.getSdt());
			txtGioiTinh.setText(sinhVien.getGioiTinh());
		}
		
	}
}
