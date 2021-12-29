package gui_sinhvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ChiTietLopHocPhanDao;
import dao.CongNoDao;
import dao.DataBase;
import dao.LopHocPhanDaDangKyDao;
import dao.LopHocPhanDao;
import dao.PhieuDangKyLopHPDao;
import entity.ChiTietLopHocPhan;
import entity.CongNo;
import entity.LichHoc;
import entity.LopHocPhan;
import entity.LopHocPhanDaDangKy;

import javax.swing.JTextField;


public class GD_CongNo extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable tblCongNo;
	private DefaultTableModel tblModel;
	private JScrollPane scrLopHP;
	private	JButton btnInCongNo;
	private CongNoDao dsCN = new CongNoDao();
	private double tongTien;

	private String mssv, nam;
	private int hk;

	private JTextField txtTongTien;

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
	public GD_CongNo (String msv,  int maHK, String Nam) {
		this.mssv = msv;
		this.nam = Nam;
		this.hk = maHK;

		setTitle("Công Nợ");
		setBounds(100, 100, 1850, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCongNo = new JLabel("XEM CÔNG NỢ");
		lblCongNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongNo.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblCongNo.setBounds(493, 32, 446, 32);
		contentPane.add(lblCongNo);

		scrLopHP = new JScrollPane();
		scrLopHP.setBounds(77, 90, 1260, 456);
		contentPane.add(scrLopHP);


		tblCongNo = new JTable();
		String[] headers1 = "Tên môn;Số Chỉ;Học Kỳ;Năm Học;Số Tiền".split(";");
		tblModel = new DefaultTableModel(headers1,0);
		tblCongNo = new JTable(tblModel);
		tblCongNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrLopHP.setViewportView(tblCongNo);


		btnInCongNo = new JButton("In Công Nợ");
		btnInCongNo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnInCongNo.setBounds(1202, 557, 135, 29);
		contentPane.add(btnInCongNo);
		
		JLabel lblTongCongNo = new JLabel("Tổng Công Nợ");
		lblTongCongNo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTongCongNo.setBounds(636, 555, 124, 32);
		contentPane.add(lblTongCongNo);
		
		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTongTien.setBounds(770, 558, 187, 28);
		contentPane.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		JLabel lblDong = new JLabel("Đồng");
		lblDong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDong.setBounds(981, 557, 84, 29);
		contentPane.add(lblDong);
		btnInCongNo.addActionListener(this);
		//		tblLyThuyet.getSelectionModel().addSelectionInterval(0, 0);
		DataBase.getInstance().connect();
		dulieubang();
	
		tongTien = dsCN.TongChinhChi(msv, maHK, Nam) *790000 ;
		txtTongTien.setText(tongTien+"");
		
		
		
//		while (tblCongNo.getModel().getValueAt(i+1 ,5) != null) {
//			tongTien = tongTien +   Integer.parseInt(tblCongNo.getModel().getValueAt(5, i)+"");
//			i++;
//		}
//		System.out.println("Tong tien");
//		System.out.println(tongTien);
		
	
		
		
		
		
//		 dscn = cnd.LayCongNo(mssv, hk, nam);
//		System.out.println(dscn);
//		for (CongNo cn : dscn) {
//			String [] rowData1 = {cn.getTenMon(), cn.getSoChi()+"", cn.getHoKy()+"", cn.getNam(), cn.getTienHoc()+""};
//			tblModel.addRow(rowData1);
//		}
//		tblCongNo.setModel(tblModel);
//
//		//		System.out.println(massv + maLop +"nhom");
		

	}
	
	public void dulieubang() {
		ArrayList<CongNo> list = dsCN.LayCongNo(mssv, hk, nam) ;
		for (CongNo cn : list) {
			String [] rowdata = {cn.getTenMon(),cn.getSoChi()+"",cn.getHoKy()+"",cn.getNam(),cn.getTienHoc()+""};
			tblModel.addRow(rowdata);
		}
		tblCongNo.setModel(tblModel);
		
	}
	
	private static	XSSFCellStyle tieudebang(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;

	}
	
	private static XSSFCellStyle tieudetrang(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setItalic(true);

		//Kích cỡ
		font.setFontHeightInPoints((short)18);;
		//Màu sắc
		font.setColor(IndexedColors.BLACK.index);

		//Kiểu
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnInCongNo)) {
			try {
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Công nợ");
				XSSFCellStyle style = tieudebang(workbook);
				XSSFCellStyle style2 = tieudetrang(workbook);
				//Tạo từng dòng
				int rownum = 0;
				Cell cell = null;
				Row row;

				row = sheet.createRow(rownum);
				cell  = row.createCell(0,CellType.STRING);
				cell.setCellValue("CÔNG NỢ CỦA SINH VIÊN");
				cell.setCellStyle(style2);
				rownum++;
				rownum++;
				
				row = sheet.createRow(rownum);
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue("Mã sinh viên:");
				cell.setCellStyle(style);

				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue(mssv);
				cell.setCellStyle(style);
				rownum++;

				row = sheet.createRow(rownum);
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue("Học kì:");
				cell.setCellStyle(style);

				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue(hk);
				cell.setCellStyle(style);
				rownum++;

				row = sheet.createRow(rownum);
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue("Năm học:");
				cell.setCellStyle(style);

				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue(nam);
				cell.setCellStyle(style);

				rownum++;
				rownum++;
				row = sheet.createRow(rownum);
				///Tạo tiêu đề cho bảng
				// Tên môn học phần
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Tên môn");
				cell.setCellStyle(style);
				// Số tín chỉ
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Số tín chỉ");
				cell.setCellStyle(style);
				// Học kỳ
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Học Kỳ");
				cell.setCellStyle(style);
				//Năm
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Năm");
				cell.setCellStyle(style);
				//Tiền học
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Tiền học");
				cell.setCellStyle(style);


				ArrayList<CongNo> list = dsCN.LayCongNo(mssv, hk, nam) ;

				if(list!=null) {
					for (CongNo cn : list) {
						rownum++;
						row = sheet.createRow(rownum);
						//Tên môn
						cell = row.createCell(0, CellType.STRING);
						cell.setCellValue(cn.getTenMon());
						//Số tín chỉ
						cell = row.createCell(1, CellType.NUMERIC);
						cell.setCellValue(cn.getSoChi());
						//Học kì
						cell = row.createCell(2, CellType.NUMERIC);
						cell.setCellValue(cn.getHoKy());
						//Năm
						cell = row.createCell(3, CellType.STRING);
						cell.setCellValue(cn.getNam());
						//Tiền học
						cell = row.createCell(4, CellType.NUMERIC);
						cell.setCellValue(cn.getTienHoc());
					}
					rownum++;
					
					row = sheet.createRow(rownum);
					cell = row.createCell(3,CellType.STRING);
					cell.setCellValue("Tổng cộng:");
					cell.setCellStyle(style);

					cell = row.createCell(4,CellType.NUMERIC);
					cell.setCellValue(tongTien + "VND");
					cell.setCellStyle(style);
				}
				//save file
				if(tblModel.getRowCount()>0) {
					File file = new File("./baocao/sinhvien/DanhSachCongNo.xlsx");
					file.getParentFile().mkdirs();

					FileOutputStream outFile = new FileOutputStream(file);
					workbook.write(outFile);
					JOptionPane.showMessageDialog(null, "In thành công");
				}else {
					JOptionPane.showMessageDialog(null, "Chưa có dữ liệu trên bảng");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}


