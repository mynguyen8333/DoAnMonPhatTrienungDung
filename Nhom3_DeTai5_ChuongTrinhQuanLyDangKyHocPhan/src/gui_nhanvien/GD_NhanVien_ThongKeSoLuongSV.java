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
import dao.ThongKeSinhVienDao;
import entity.ThongKeLop;
import entity.ThongKeSoLuongSinhVien;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GD_NhanVien_ThongKeSoLuongSV extends JFrame implements ActionListener{
	
	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JPanel pnlTong;
	private DefaultTableModel tableModel;
	private JButton btnInBaoCao,btnThongKe,btnTim,btnXemChiTiet,btnXemNhieu,btnXemIt;
	private JComboBox<Integer> cmbHocKy;
	private JComboBox<String>cmbNam,cmbTenMon;
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	private ThongKeSinhVienDao dstk = new ThongKeSinhVienDao();
	private GD_NhanVien_XemChiTiet1 gd_XemChiTiet;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_ThongKeSoLuongSV window = new GD_NhanVien_ThongKeSoLuongSV();
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
	public GD_NhanVien_ThongKeSoLuongSV() {
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
		
		JLabel lblTieuDe = new JLabel("THỐNG KÊ SỐ LƯỢNG SINH VIÊN THEO TỪNG MÔN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(395, 27, 628, 42);
		pnlTong.add(lblTieuDe);
		
		JPanel pnlDuLieuChon = new JPanel();
		pnlDuLieuChon.setBorder(new TitledBorder(null, "D\u1EEF li\u1EC7u ch\u1ECDn v\u00E0o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDuLieuChon.setBounds(100, 80, 1076, 60);
		pnlTong.add(pnlDuLieuChon);
		pnlDuLieuChon.setLayout(null);
		
		JLabel lblHocKy = new JLabel("Chọn học kỳ:");
		lblHocKy.setBounds(476, 22, 107, 17);
		pnlDuLieuChon.add(lblHocKy);
		lblHocKy.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblNamHoc = new JLabel("Chọn năm học:");
		lblNamHoc.setBounds(100, 22, 107, 17);
		pnlDuLieuChon.add(lblNamHoc);
		lblNamHoc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		cmbHocKy = new JComboBox<Integer>();
		cmbHocKy.setBounds(593, 20, 200, 22);
		pnlDuLieuChon.add(cmbHocKy);
		
		cmbNam = new JComboBox<String>();
		cmbNam.setBounds(202, 20, 200, 22);
		pnlDuLieuChon.add(cmbNam);
		
		btnThongKe = new JButton("Liệt kê");
		btnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThongKe.setBounds(827, 19, 100, 23);
		pnlDuLieuChon.add(btnThongKe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 233, 1076, 325);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Mã môn học phần;Tên môn học phần;Số lượng sinh viên cần;Số lượng sinh viên đăng kí".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(100, 580, 1076, 60);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnInBaoCao = new JButton("In báo cáo");
		btnInBaoCao.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnInBaoCao.setBounds(799, 15, 100, 27);
		pnlTacVu.add(btnInBaoCao);
		
		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXemChiTiet.setBounds(100, 15, 108, 27);
		pnlTacVu.add(btnXemChiTiet);
		
		btnXemNhieu = new JButton("Môn có nhiều sinh viên nhất");
		btnXemNhieu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXemNhieu.setBounds(250, 15, 218, 27);
		pnlTacVu.add(btnXemNhieu);
		
		btnXemIt = new JButton("Môn có ít sinh viên nhất");
		btnXemIt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXemIt.setBounds(506, 15, 238, 27);
		pnlTacVu.add(btnXemIt);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm tr\u00EAn b\u1EA3ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(100, 150, 1076, 60);
		pnlTong.add(panel);
		panel.setLayout(null);
		
		JLabel lblTenMon = new JLabel("Tên môn:");
		lblTenMon.setBounds(100, 25, 71, 17);
		panel.add(lblTenMon);
		lblTenMon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setBounds(202, 23, 352, 22);
		panel.add(cmbTenMon);
		
		btnTim = new JButton("Tìm kiếm\r\n");
		btnTim.setBounds(574, 22, 100, 23);
		panel.add(btnTim);
		btnTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
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
		font.setFontHeightInPoints((short)18);
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
			ArrayList<ThongKeSoLuongSinhVien> list = dsmh.ThongKeSoLuongSV(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
					,cmbNam.getSelectedItem().toString());
			if(list.size()!=0) {
				cmbTenMon.removeAllItems();
				for (ThongKeSoLuongSinhVien tk : list) {
					String [] rowdata = {tk.getMaMH(),tk.getTenMH(),tk.getSoLuongSVCan()+"",tk.getSoLuonhSVDaDK()+""};
					tableModel.addRow(rowdata);
					cmbTenMon.addItem(tk.getTenMH().toString());
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
			}else {
				JOptionPane.showMessageDialog(this, "Không có");
			}
		}
		if(o.equals(btnTim)) {
			try {
				
				if(cmbTenMon.getSelectedIndex()>=0) {
					String tenmon = cmbTenMon.getSelectedItem().toString().trim();
					ArrayList<ThongKeSoLuongSinhVien> list1 = dstk.ThongKeSoLuongSV(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
							,cmbNam.getSelectedItem().toString());
					//System.out.println(list1);
					ThongKeSoLuongSinhVien tk = dstk.timMon(tenmon);
					//System.out.println(tk);
					if (tk == null) {
						JOptionPane.showMessageDialog(this, "Không có trên bảng");
						return;
					} else {
						tableModel.setRowCount(0);
						String[] dataRow = {tk.getMaMH(),tk.getTenMH(),tk.getSoLuongSVCan()+"",
								tk.getSoLuonhSVDaDK()+""};
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
				gd_XemChiTiet = new GD_NhanVien_XemChiTiet1(table_1.getValueAt(row, 0).toString(),
						table_1.getValueAt(row, 1).toString(),cmbNam.getSelectedItem().toString(),
						Integer.parseInt(cmbHocKy.getSelectedItem().toString()),
						Integer.parseInt(table_1.getValueAt(row, 2).toString()), 
						Integer.parseInt(table_1.getValueAt(row, 3).toString()));
				gd_XemChiTiet.setVisible(true);
			}
		}
		if(o.equals(btnXemNhieu)) {
			ArrayList<ThongKeSoLuongSinhVien> list = dsmh.MonNhieuNhat(cmbNam.getSelectedItem().toString(),
					Integer.parseInt(cmbHocKy.getSelectedItem().toString()));
			tableModel.setRowCount(0);
			for (ThongKeSoLuongSinhVien tk : list) {
				String [] rowdata = {tk.getMaMH(),tk.getTenMH(),tk.getSoLuongSVCan()+"",
						tk.getSoLuonhSVDaDK()+""};
				tableModel.addRow(rowdata);
			}
			table_1.setModel(tableModel);
		}
		if(o.equals(btnXemIt)) {
			ArrayList<ThongKeSoLuongSinhVien> list = dsmh.MonItNhat(cmbNam.getSelectedItem().toString(),
					Integer.parseInt(cmbHocKy.getSelectedItem().toString()));
			tableModel.setRowCount(0);
			for (ThongKeSoLuongSinhVien tk : list) {
				String [] rowdata = {tk.getMaMH(),tk.getTenMH(),tk.getSoLuongSVCan()+"",
						tk.getSoLuonhSVDaDK()+""};
				tableModel.addRow(rowdata);
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
				cell.setCellValue("THỐNG KÊ SỐ LƯỢNG SINH VIÊN THEO TỪNG MÔN");
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
				// Mã môn học phần
				
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã môn học phần");
				cell.setCellStyle(style);
				// Tên môn học phần
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Tên môn học phần");
				cell.setCellStyle(style);
				// Số lượng sinh viên cần 
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Sĩ số");
				cell.setCellStyle(style);
				// Số lượng sinh viên đã đăng kí 
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Đã đăng ký");
				cell.setCellStyle(style);


				ArrayList<ThongKeSoLuongSinhVien> list = dsmh.ThongKeSoLuongSV(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
						,cmbNam.getSelectedItem().toString());

				if(list!=null) {
					for (ThongKeSoLuongSinhVien tk : list) {
						rownum++;
						row = sheet.createRow(rownum);
						//Tạo từng dòng
						//Mã môn học phần
						cell = row.createCell(0, CellType.STRING);
						cell.setCellValue(tk.getMaMH());
						// Tên môn học phần
						cell = row.createCell(1, CellType.STRING);
						cell.setCellValue(tk.getTenMH());
						// Số lượng sinh viên cần
						cell = row.createCell(2, CellType.NUMERIC);
						cell.setCellValue(tk.getSoLuongSVCan());
						// Số lượng sinh viên đã đăng kí
						cell = row.createCell(3, CellType.NUMERIC);
						cell.setCellValue(tk.getSoLuonhSVDaDK());
					}

				}
				//save file
				if(tableModel.getRowCount()>0) {
					File file = new File("./baocao/nhanvien/ThongKeSoLuongSVTheoTungMon.xlsx");
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
    	ArrayList<String> list = dsmh.ThongKeSoLuongSV1(Integer.parseInt(cmbHocKy.getSelectedItem().toString())
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
