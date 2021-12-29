package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenNganhDao;
import dao.DanhSachSinhVienCNDao;
import dao.DataBase;
import entity.ChuongTrinhKhung1;
import entity.SinhVien;

import javax.swing.JComboBox;

public class GD_NhanVien_ChiaChuyenNganh extends JFrame implements ActionListener,MouseListener{
	
	
	private JFrame frame;
	private JPanel pnlTong;
	private JTable table;
	private JTable table_2;
	private DefaultTableModel tableModel,tableModel1;
	private JButton btnDanhSVSV;
	private JButton btnThem;
	private JComboBox<String> cmbTenCN;
	private JTable table_1;
	private JTable table_1_1;
	private DanhSachSinhVienCNDao dssvvn = new DanhSachSinhVienCNDao();
	private ChuyenNganhDao dscn = new ChuyenNganhDao();
	private JButton btnLoc;
	private JButton btnXoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_ChiaChuyenNganh window = new GD_NhanVien_ChiaChuyenNganh();
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
	public GD_NhanVien_ChiaChuyenNganh() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);
		
		JLabel lblTieuDe = new JLabel("CHIA CHUYÊN NGÀNH CHO SINH VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(500, 24, 411, 24);
		pnlTong.add(lblTieuDe);
		
		btnDanhSVSV = new JButton("Danh sách sinh viên chưa có chuyên ngành");
		btnDanhSVSV.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnDanhSVSV.setBounds(100, 60, 285, 23);
		pnlTong.add(btnDanhSVSV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 94, 1080, 199);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Mã số sinh viên;Họ tên;Giới tính;Ngày sinh".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_2 = new JTable(tableModel);
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_2);
		
		cmbTenCN = new JComboBox<String>();
		cmbTenCN.setBounds(230, 319, 339, 22);
		pnlTong.add(cmbTenCN);
		
		JLabel lblTenCN = new JLabel("Tên chuyên ngành:");
		lblTenCN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenCN.setBounds(100, 320, 131, 18);
		pnlTong.add(lblTenCN);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 383, 1080, 267);
		pnlTong.add(scrollPane_1);
		
		table_1 = new JTable();
		String[] headers1 = "Mã số sinh viên;Họ tên;Giới tính;Ngày sinh".split(";");
		tableModel1 = new DefaultTableModel(headers1,0);
		table_1_1 = new JTable(tableModel1);
		table_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane_1.setViewportView(table_1_1);
		
		JLabel lblTieuDeBang = new JLabel("Danh sách sinh viên thuộc chuyên ngành");
		lblTieuDeBang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTieuDeBang.setBounds(100, 352, 267, 18);
		pnlTong.add(lblTieuDeBang);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThem.setBounds(591, 319, 116, 23);
		pnlTong.add(btnThem);
		
		btnLoc = new JButton("Lọc");
		btnLoc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLoc.setBounds(377, 349, 116, 23);
		pnlTong.add(btnLoc);
		
		btnXoa = new JButton("Xóa sinh viên khỏi chuyên ngành");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(717, 319, 211, 23);
		pnlTong.add(btnXoa);
		
		btnDanhSVSV.addActionListener(this);
		btnThem.addActionListener(this);
		cmbTenCN.addMouseListener(this);
		btnLoc.addActionListener(this);
		btnXoa.addActionListener(this);
		DataBase.getInstance().connect();
		dulieutenchuyennganh();
	}
	
	public void dulieutenchuyennganh() {
		cmbTenCN.removeAllItems();
		ArrayList<String> listTen = new ChuyenNganhDao().LayDSTen();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenCN.addItem(ten);
			}
		}
	}
	
	public JPanel getJPanel() {
		return pnlTong;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		int click = cmbTenCN.getSelectedIndex();
//		if(click>0) {
//			tableModel1.setRowCount(0);
//			String maCN = dscn.LayMaChuyenNganh(cmbTenCN.getSelectedItem().toString());
//			System.out.println(maCN);
//			ArrayList<SinhVien> list = dssvvn.SinhVienThuocCN(maCN);
//			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
//			for (SinhVien sv : list) {
//				String date2 = sdf1.format(sv.getNgaySinh());
//				String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),date2};
//				tableModel1.addRow(rowdata);
//			}
//			table_1.setModel(tableModel1);
//		}

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(btnDanhSVSV)) {
			tableModel.setRowCount(0);
			ArrayList<SinhVien> list = dssvvn.chuaCoCN();
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			for (SinhVien sv : list) {
				String date2 = sdf1.format(sv.getNgaySinh());
				String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),date2};
				tableModel.addRow(rowdata);
			}
			table_2.setModel(tableModel);
		}
		if(o.equals(btnLoc)) {
			tableModel1.setRowCount(0);
			String maCN = dscn.LayMaChuyenNganh(cmbTenCN.getSelectedItem().toString());
			ArrayList<SinhVien> list = dssvvn.SinhVienThuocCN(maCN);
			if(list.size()>0) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				for (SinhVien sv : list) {
					String date2 = sdf1.format(sv.getNgaySinh());
					String [] rowdata = {sv.getMaSV(),sv.getHoTen(),sv.getGioiTinh(),date2};
					tableModel1.addRow(rowdata);
				}
				table_1_1.setModel(tableModel1);
			}else {
				JOptionPane.showMessageDialog(null, "Chưa có sinh viên thuộc chuyên ngành này");
			}

		}
		if(o.equals(btnThem)) {
			int row = table_2.getSelectedRow();
			System.out.println(row);
			if(row>=0) {
				String maCN = dscn.LayMaChuyenNganh(cmbTenCN.getSelectedItem().toString());
				String maSV = (String)table_2.getValueAt(row,0);
				String hoTen = (String)table_2.getValueAt(row,1);
				String gioiTinh = (String)table_2.getValueAt(row,2);
				String ngaySinh = (String)table_2.getValueAt(row,3);
				if(dssvvn.ThemDanhSach(maSV, maCN)) {
					Object[] datarow = {maSV,hoTen,gioiTinh,ngaySinh};
					tableModel1.addRow(datarow);
					tableModel.removeRow(row);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên");
			}
		}
		if(o.equals(btnXoa)) {
			int row = table_1_1.getSelectedRow();
			if(row>=0) {
				String maSV = (String)table_1_1.getValueAt(row,0);
				String hoTen = (String)table_1_1.getValueAt(row,1);
				String gioiTinh = (String)table_1_1.getValueAt(row,2);
				String ngaySinh = (String)table_1_1.getValueAt(row,3);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dssvvn.xoaSVTDS(maSV)) {
						tableModel1.removeRow(row);
						Object[] datarow = {maSV,hoTen,gioiTinh,ngaySinh};
						tableModel.addRow(datarow);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên để xóa");
			}

		}
		
	}
}
