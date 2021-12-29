package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.DataBase;
import dao.HocKyDao;
import dao.MonHocPhanDao;
import dao.NamDao;
import dao.ThongKeLopDao;
import entity.LopHocPhan;
import entity.ThongKeLop;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GD_NhanVien_ThongKeLopHocPhan extends JFrame implements ActionListener{

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JPanel pnlTong;
	private DefaultTableModel tableModel;
	private JButton btnInBaoCao,btnThongKe,btnTim,btnXemChiTiet,btnXemNhieu,btnXemIt;
	private JComboBox<Integer> cmbHocKy;
	private JComboBox<String>cmbNam,cmbTenMon;
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	private ThongKeLopDao dstk = new ThongKeLopDao();
	private GD_NhanVien_XemChiTiet gd_XemChiTiet;
	private JTextField txtTongSoLuongLop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_ThongKeLopHocPhan window = new GD_NhanVien_ThongKeLopHocPhan();
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
	public GD_NhanVien_ThongKeLopHocPhan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);

		JLabel lblTieuDe = new JLabel("THỐNG KÊ SỐ LƯỢNG LỚP HỌC PHẦN THEO TỪNG MÔN HỌC");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(350, 27, 566, 28);
		pnlTong.add(lblTieuDe);

		JPanel pnlDuLieuChon = new JPanel();
		pnlDuLieuChon.setBorder(new TitledBorder(null, "D\u1EEF li\u1EC7u ch\u1ECDn v\u00E0o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDuLieuChon.setBounds(79, 80, 1117, 60);
		pnlTong.add(pnlDuLieuChon);
		pnlDuLieuChon.setLayout(null);

		JLabel lblHocKy = new JLabel("Chọn học kỳ:");
		lblHocKy.setBounds(493, 22, 107, 18);
		pnlDuLieuChon.add(lblHocKy);
		lblHocKy.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblNamHoc = new JLabel("Chọn năm học:");
		lblNamHoc.setBounds(100, 22, 107, 18);
		pnlDuLieuChon.add(lblNamHoc);
		lblNamHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cmbHocKy = new JComboBox<Integer>();
		cmbHocKy.setBounds(610, 21, 158, 22);
		pnlDuLieuChon.add(cmbHocKy);

		cmbNam = new JComboBox<String>();
		cmbNam.setBounds(218, 21, 158, 22);
		pnlDuLieuChon.add(cmbNam);

		btnThongKe = new JButton("Liệt kê");
		btnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThongKe.setBounds(806, 21, 100, 23);
		pnlDuLieuChon.add(btnThongKe);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 216, 1117, 343);
		pnlTong.add(scrollPane);

		table = new JTable();
		String[] headers = "Mã môn học phần;Tên môn học phần;Số lượng lớp".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(79, 600, 1117, 60);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);

		btnInBaoCao = new JButton("In báo cáo");
		btnInBaoCao.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnInBaoCao.setBounds(750, 15, 100, 27);
		pnlTacVu.add(btnInBaoCao);

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXemChiTiet.setBounds(100, 15, 109, 27);
		pnlTacVu.add(btnXemChiTiet);

		btnXemNhieu = new JButton("Xem môn nhiều lớp nhất");
		btnXemNhieu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXemNhieu.setBounds(250, 15, 177, 27);
		pnlTacVu.add(btnXemNhieu);

		btnXemIt = new JButton("Xem môn ít lớp nhất");
		btnXemIt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXemIt.setBounds(500, 15, 177, 27);
		pnlTacVu.add(btnXemIt);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm tr\u00EAn b\u1EA3ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(79, 144, 1117, 60);
		pnlTong.add(panel);
		panel.setLayout(null);

		btnTim = new JButton("Tìm kiếm");
		btnTim.setBounds(629, 26, 100, 23);
		panel.add(btnTim);
		btnTim.setFont(new Font("Times New Roman", Font.BOLD, 12));

		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setBounds(218, 26, 401, 22);
		panel.add(cmbTenMon);

		JLabel lblTimKiem = new JLabel("Tên môn:\r\n");
		lblTimKiem.setBounds(100, 28, 77, 17);
		panel.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblTongSo = new JLabel("Tổng số lượng lớp:");
		lblTongSo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTongSo.setBounds(830, 570, 130, 17);
		pnlTong.add(lblTongSo);

		txtTongSoLuongLop = new JTextField();
		txtTongSoLuongLop.setBounds(996, 570, 200, 20);
		pnlTong.add(txtTongSoLuongLop);
		txtTongSoLuongLop.setColumns(10);
		txtTongSoLuongLop.setEditable(false);
		btnTim.addActionListener(this);

		btnInBaoCao.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		btnXemIt.addActionListener(this);
		btnXemNhieu.addActionListener(this);

		DataBase.getInstance().connect();
		dulieuHocKy();
		dulieuNamHoc();

	}

	public JPanel getJPanel() {
		return pnlTong;
	}
	/*
	 * Dữ liệu cho các combobox
	 */
	public void dulieuHocKy() {
		ArrayList<Integer> listHocKy = new HocKyDao().getDSHocKy();
		if (listHocKy==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (int hk: listHocKy) {
				cmbHocKy.addItem(hk);
			}

		}
	}
	public void dulieuNamHoc() {
		ArrayList<String> listNam = new NamDao().getDSNAm();
		if (listNam==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String nam: listNam) {
				cmbNam.addItem(nam);
			}

		}
	}
	/*
	 * Tạo kiểu chữ
	 */

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
		Object o =e.getSource();
		if(o.equals(btnThongKe)) {		
			tableModel.setRowCount(0);
			ArrayList<ThongKeLop> list = dsmh.ThongKeLop(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
					,cmbNam.getSelectedItem().toString());
			if(list.size()!=0) {
				cmbTenMon.removeAllItems();
				for (ThongKeLop tk : list) {
					String [] rowdata = {tk.getMaMon(),tk.getTenMon(),tk.getSoLop()+""};
					tableModel.addRow(rowdata);
					cmbTenMon.addItem(tk.getTenMon());
					cmbTenMon.setEditable(true);
					cmbTenMon.addItemListener(new ItemListener() {			
						@Override
						public void itemStateChanged(ItemEvent e) {

						}
					});
					final JTextField textfield = (JTextField) cmbTenMon.getEditor().getEditorComponent();
					textfield.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent ke) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									if(!textfield.getText().isEmpty()){
										comboBoxFilter(textfield.getText());
									}
								}
							});

						}
					});
				}
				table_1.setModel(tableModel);
				int tongLop = dstk.tongSLLHP(Integer.parseInt(cmbHocKy.getSelectedItem().toString()), cmbNam.getSelectedItem().toString());
				String tongsoLop = String.valueOf(tongLop);
				txtTongSoLuongLop.setText(tongsoLop);
			}else {
				cmbTenMon.removeAllItems();
				txtTongSoLuongLop.setText("");
				JOptionPane.showMessageDialog(this, "Không có");
				
			}
		}
		if(o.equals(btnTim)) {
			try {
				if(cmbTenMon.getSelectedIndex()>=0)
				{
					String tenmon = cmbTenMon.getSelectedItem().toString().trim();
					ArrayList<ThongKeLop> list = dstk.ThongKeLop(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
							,cmbNam.getSelectedItem().toString());
					ThongKeLop tk = dstk.timThuocTenThuoc(tenmon);
					if (tk == null) {
						JOptionPane.showMessageDialog(this, "Chưa chọn môn học");
						return;
					} else {
						tableModel.setRowCount(0);
						String[] dataRow = {tk.getMaMon(),tk.getTenMon(),tk.getSoLop()+""};
						tableModel.addRow(dataRow);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Chưa có tên môn");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(o.equals(btnXemChiTiet)) {
			int row = table_1.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn môn");
			}else {
				gd_XemChiTiet = new GD_NhanVien_XemChiTiet(table_1.getValueAt(row, 0).toString(), 
						table_1.getValueAt(row, 1).toString(),cmbNam.getSelectedItem().toString(),Integer.parseInt(cmbHocKy.getSelectedItem().toString()), 
						Integer.parseInt(table_1.getValueAt(row, 2).toString()));
				gd_XemChiTiet.setVisible(true);
			}

		}
		if(o.equals(btnXemNhieu)) {
			ArrayList<ThongKeLop> list = dsmh.LopNhieuNhat(cmbNam.getSelectedItem().toString(),
					Integer.parseInt(cmbHocKy.getSelectedItem().toString()));
			tableModel.setRowCount(0);
			for (ThongKeLop tk : list) {
				String [] rowdata = {tk.getMaMon(),tk.getTenMon(),tk.getSoLop()+""};
				tableModel.addRow(rowdata);
				cmbTenMon.addItem(tk.getTenMon());
			}
			table_1.setModel(tableModel);
		}
		if(o.equals(btnXemIt)) {
			ArrayList<ThongKeLop> list = dsmh.LopItNhat(cmbNam.getSelectedItem().toString(),
					Integer.parseInt(cmbHocKy.getSelectedItem().toString()));
			tableModel.setRowCount(0);
			for (ThongKeLop tk : list) {
				String [] rowdata = {tk.getMaMon(),tk.getTenMon(),tk.getSoLop()+""};
				tableModel.addRow(rowdata);
				cmbTenMon.addItem(tk.getTenMon());
			}
			table_1.setModel(tableModel);
		}
		if(o.equals(btnInBaoCao)) {
			try {
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Báo cáo");
				XSSFCellStyle style = tieudebang(workbook);
				XSSFCellStyle style2 = tieudetrang(workbook);
				//Tạo từng dòng
				int rownum = 0;
				Cell cell = null;
				Row row;

				row = sheet.createRow(rownum);
				cell  = row.createCell(0,CellType.STRING);
				cell.setCellValue("THỐNG KÊ SỐ LƯỢNG LỚP HỌC PHẦN THEO TỪNG MÔN HỌC");
				cell.setCellStyle(style2);
				rownum++;
				rownum++;

				row = sheet.createRow(rownum);
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue("Học kì:");
				cell.setCellStyle(style);

				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue(cmbHocKy.getSelectedItem().toString());
				cell.setCellStyle(style);
				rownum++;

				row = sheet.createRow(rownum);
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue("Năm học:");
				cell.setCellStyle(style);

				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue(cmbNam.getSelectedItem().toString());
				cell.setCellStyle(style);

				rownum++;
				rownum++;
				row = sheet.createRow(rownum);
				///Tạo tiêu đề cho bảng
				// Mã môn
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã môn");
				cell.setCellStyle(style);
				// Tên môn
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Tên môn");
				cell.setCellStyle(style);
				// Số lớp của từng môn
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Số lớp");
				cell.setCellStyle(style);


				ArrayList<ThongKeLop> list = dsmh.ThongKeLop(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
						,cmbNam.getSelectedItem().toString());

				if(list!=null) {
					for (ThongKeLop tk : list) {
						rownum++;
						row = sheet.createRow(rownum);
						//Ma mon
						cell = row.createCell(0, CellType.STRING);
						cell.setCellValue(tk.getMaMon());
						// Ten mon 
						cell = row.createCell(1, CellType.STRING);
						cell.setCellValue(tk.getTenMon());
						// So Lop
						cell = row.createCell(2, CellType.NUMERIC);
						cell.setCellValue(tk.getSoLop());
					}

				}
				
				rownum++;
				
				row = sheet.createRow(rownum);

				int tongLop = dstk.tongSLLHP(Integer.parseInt(cmbHocKy.getSelectedItem().toString()), cmbNam.getSelectedItem().toString());	
				cell = row.createCell(2,CellType.NUMERIC);
				cell.setCellValue("Tổng số lớp:"+tongLop);
				cell.setCellStyle(style);
				//save file
				if(tableModel.getRowCount()>0) {
					File file = new File("./baocao/nhanvien/ThongKeSoLuongLopHP.xlsx");
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

	public void comboBoxFilter(String enteredText) {
		ArrayList<String> list = dsmh.ThongKeLop1(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
				,cmbNam.getSelectedItem().toString());
		if (!cmbTenMon.isPopupVisible()) {
			cmbTenMon.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(list.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenMon.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenMon.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
}
