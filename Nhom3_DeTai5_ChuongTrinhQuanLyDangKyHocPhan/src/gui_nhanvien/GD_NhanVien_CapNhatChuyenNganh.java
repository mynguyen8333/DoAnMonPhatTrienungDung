package gui_nhanvien;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenNganhDao;
import dao.DataBase;
import dao.KhoaDao;
import entity.ChuyenNganh;
import entity.GiangVien;
import entity.Khoa;

public class GD_NhanVien_CapNhatChuyenNganh extends JFrame implements ActionListener,MouseListener{
	

	private JFrame frame;
	private JTextField txtmaCN;
	private JTextField txtTenCN;
	private JTable table;
	private JTable table_1;
	private JButton btnXoaTrang,btnThemCN,btnXoa;
	private JPanel pnlTong;
	private KhoaDao dhkh = new KhoaDao();
	private ChuyenNganhDao dscn = new ChuyenNganhDao();
	private DefaultTableModel tableModel;
	private JLabel lblTenKhoa;
	private JComboBox<String> cmbTenKhoa;
	private JButton btnCapNhat;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatChuyenNganh window = new GD_NhanVien_CapNhatChuyenNganh();
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
	public GD_NhanVien_CapNhatChuyenNganh() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);
		
		JLabel lblTieuDe = new JLabel("CÂP NHẬT THÔNG TIN CHUYÊN NGÀNH");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTieuDe.setBounds(461, 22, 378, 34);
		pnlTong.add(lblTieuDe);
		
		JLabel lblMaCN = new JLabel("Mã chuyên ngành:");
		lblMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMaCN.setBounds(100, 84, 136, 17);
		pnlTong.add(lblMaCN);
		
		txtmaCN = new JTextField();
		txtmaCN.setBounds(245, 83, 350, 20);
		pnlTong.add(txtmaCN);
		txtmaCN.setColumns(10);
		
		JLabel lblTenCN = new JLabel("Tên chuyên ngành:");
		lblTenCN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenCN.setBounds(696, 84, 135, 17);
		pnlTong.add(lblTenCN);
		
		txtTenCN = new JTextField();
		txtTenCN.setColumns(10);
		txtTenCN.setBounds(820, 83, 350, 20);
		pnlTong.add(txtTenCN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 249, 1128, 379);
		pnlTong.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Tên khoa;Mã chuyên ngành;Tên chuyên ngành".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTacVu.setBounds(76, 183, 1128, 55);
		pnlTong.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnXoaTrang.setBounds(160, 11, 116, 33);
		pnlTacVu.add(btnXoaTrang);
		
		btnThemCN = new JButton("Thêm ");
		btnThemCN.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemCN.setBounds(360, 11, 116, 33);
		pnlTacVu.add(btnThemCN);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoa.setBounds(560, 11, 116, 33);
		pnlTacVu.add(btnXoa);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(760, 11, 116, 33);
		pnlTacVu.add(btnCapNhat);
		
		lblTenKhoa = new JLabel("Tên khoa:");
		lblTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenKhoa.setBounds(100, 127, 119, 17);
		pnlTong.add(lblTenKhoa);
		
		cmbTenKhoa = new JComboBox<String>();
		cmbTenKhoa.setBounds(245, 125, 350, 22);
		pnlTong.add(cmbTenKhoa);
		
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThemCN.addActionListener(this);
		btnCapNhat.addActionListener(this);
		table_1.addMouseListener(this);
		
		DataBase.getInstance().connect();
		dulieubang();
		cbBoxTenKhoa();
		
		cmbTenKhoa.setEditable(true);
		cmbTenKhoa.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		final JTextField textfield1 = (JTextField) cmbTenKhoa.getEditor().getEditorComponent();
		textfield1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if(!textfield1.getText().isEmpty()){
							comboBoxTenKhoa(textfield1.getText());	
						}
					}
				});

			}
		});
	}
	
	public void comboBoxTenKhoa(String enteredText) {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		if (!cmbTenKhoa.isPopupVisible()) {
			cmbTenKhoa.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbTenKhoa.getModel();
			model.removeAllElements();
			model.addElement("");
			for (String s: filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) cmbTenKhoa.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
	/*
	 * Hàm lấy giao diện
	 */
	
	public JPanel getJPanel() {
		return pnlTong;
	}
	
	/*
	 * Thêm dữ liệu vào bảng
	 */
	public void dulieubang() {
		ArrayList<ChuyenNganh> list = dscn.docTuBang();
		for (ChuyenNganh cn : list) {
			String tenKhoa = dhkh.LayTenKhoa(cn.getMaKhoa());
			String [] rowdata = {tenKhoa,cn.getMaChuyenNganh(),cn.getTenChuyenNganh()};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);
	}
	
	public void cbBoxTenKhoa() {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenKhoa.addItem(ten);
			}

		}
	}
	
	/*
	 * Hàm
	 */
	
	public void them() {
		txtmaCN.setText("");
		txtTenCN.setText("");
		txtmaCN.setEditable(true);
		cmbTenKhoa.setSelectedIndex(0);
	}
	public boolean kiemtra(){
		String maCN = txtmaCN.getText().trim();
		String tenCN = txtTenCN.getText().trim();
		int tenKhoa = cmbTenKhoa.getSelectedIndex();
		String maKhoa = dhkh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
		
		if(tenKhoa<0) {
			JOptionPane.showMessageDialog(null,"Chưa chọn tên khoa");
			return false;
		}
		
		if(maKhoa==null) {
			JOptionPane.showMessageDialog(null,"Chưa chọn tên khoa");
			return false;
		}
		if(!(maCN.length()>0)) {
			JOptionPane.showMessageDialog(null, "Mã chuyên ngành không được để trống");
			txtmaCN.selectAll();
			txtmaCN.requestFocus();
			return false;
		}

		if(!(tenCN.length()>0)) {
			JOptionPane.showMessageDialog(null, "Tên chuyên ngành không được để trống");
			txtTenCN.selectAll();
			txtTenCN.requestFocus();
			return false;
		}
		

		return true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			them();
		}
		if(o.equals(btnThemCN)) {
			if(kiemtra()==true) {
				String maKhoa = dhkh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
				if(dscn.ThemChuyenNganh(txtmaCN.getText().trim(),txtTenCN.getText().trim(), maKhoa)) {
					Object[] datarow = {cmbTenKhoa.getSelectedItem(),txtmaCN.getText(),txtTenCN.getText()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Thêm thành công chuyên ngành");
					them();
				}else {
					JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
				}
			}
		}
		if(o.equals(btnXoa)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maChuyenNganh = (String)table_1.getValueAt(row, 1);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);				
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dscn.xoaChuyenNganh(maChuyenNganh)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Chưa xóa chương trình khung của chuyên ngành");
					}
				}
			}
		}
		if(o.equals(btnCapNhat)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"Bạn có chắc không","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					String maKhoa = dhkh.LayMaKhoa(cmbTenKhoa.getSelectedItem().toString().trim());
					if(dscn.capNhatChuyenNganh(txtmaCN.getText(), txtTenCN.getText(), maKhoa)) {
						table_1.setValueAt(cmbTenKhoa.getSelectedItem(), row,0);
						table_1.setValueAt(txtmaCN.getText(), row,1);
						table_1.setValueAt(txtTenCN.getText(), row,2);
						them();
						JOptionPane.showMessageDialog(null,"Sửa thành công");
					}
					else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn chuyên ngành");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtmaCN.setText(table_1.getValueAt(row, 1).toString());
		txtmaCN.setEditable(false);
		txtTenCN.setText(table_1.getValueAt(row, 2).toString());
		cmbTenKhoa.setSelectedItem(table_1.getValueAt(row, 0).toString());
		
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
}
