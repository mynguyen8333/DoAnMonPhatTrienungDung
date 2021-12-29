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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import com.toedter.calendar.JDateChooser;

import dao.DataBase;
import dao.HocKyDao;
import dao.LopHocPhanDao;
import dao.MonHocPhanDao;
import dao.NamDao;
import entity.LopHocPhan;
import entity.MonHocPhan;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GD_NhanVien_CapNhatLHP extends JFrame implements ActionListener,MouseListener{
	

	private JFrame frame;
	private JPanel pnlTong;
	private JTextField txtMaLop;
	private JTextField txtSiSo;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private JButton btnThemLop,btnXaTrng,btnXoaLop,btnLayTenMon;
	private JComboBox<Integer> cmbHocKi;
	private JComboBox<String> cmbTenMon,cmbNam;
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	private LopHocPhanDao dslhp = new LopHocPhanDao();
	private JButton btnCapNhat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_CapNhatLHP window = new GD_NhanVien_CapNhatLHP();
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
	public GD_NhanVien_CapNhatLHP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);

		JLabel lblCpNhtLp = new JLabel("CẬP NHẬT LỚP HỌC PHẦN");
		lblCpNhtLp.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCpNhtLp.setBounds(554, 16, 275, 36);
		pnlTong.add(lblCpNhtLp);

		JLabel lblMLpHc = new JLabel("Mã lớp học phần:");
		lblMLpHc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMLpHc.setBounds(100, 60, 123, 33);
		pnlTong.add(lblMLpHc);

		JLabel lblSiSo = new JLabel("Sĩ số:");
		lblSiSo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSiSo.setBounds(100, 110, 109, 19);
		pnlTong.add(lblSiSo);
		DataBase.getInstance().connect();
		txtMaLop = new JTextField();
		int value = dslhp.LayMaTuDong()+5;
		//System.out.println(value);
		String s1 = String.valueOf(value);
		int length = s1.length();
		if(length==1) {
			txtMaLop.setText("LHP0" + s1);
		}
		if(length>=2) {
			txtMaLop.setText("LHP" + s1);
		}

		txtMaLop.setBounds(245, 60, 350, 20);
		txtMaLop.setEditable(false);
		pnlTong.add(txtMaLop);
		txtMaLop.setColumns(10);

		txtSiSo = new JTextField();
		txtSiSo.setText("0");
		txtSiSo.setColumns(10);
		txtSiSo.setBounds(245, 110, 350, 20);
		pnlTong.add(txtSiSo);

		JLabel lblTenMonHocPhan = new JLabel("Tên môn học phần:");
		lblTenMonHocPhan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenMonHocPhan.setBounds(696, 60, 123, 26);
		pnlTong.add(lblTenMonHocPhan);

		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setBounds(830, 60, 350, 22);
		pnlTong.add(cmbTenMon);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNam.setBounds(696, 110, 109, 19);
		pnlTong.add(lblNam);

		cmbNam = new JComboBox<String>();
		cmbNam.setBounds(830, 110, 350, 22);
		pnlTong.add(cmbNam);

		JLabel lblHocKi = new JLabel("Học Kì:");
		lblHocKi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHocKi.setBounds(100, 160, 109, 20);
		pnlTong.add(lblHocKi);

		cmbHocKi = new JComboBox<Integer>();
		cmbHocKi.setBounds(245, 160, 350, 22);
		pnlTong.add(cmbHocKi);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(62, 202, 1138, 63);
		pnlTong.add(panel);
		panel.setLayout(null);

		btnXaTrng = new JButton("Xóa trắng");
		btnXaTrng.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXaTrng.setBounds(160, 11, 116, 41);
		panel.add(btnXaTrng);

		btnThemLop = new JButton("Thêm");
		btnThemLop.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnThemLop.setBounds(360, 11, 116, 41);
		panel.add(btnThemLop);

		btnXoaLop = new JButton("Xóa");
		btnXoaLop.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnXoaLop.setBounds(560, 11, 116, 41);
		panel.add(btnXoaLop);
		
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCapNhat.setBounds(760, 11, 116, 41);
		panel.add(btnCapNhat);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 276, 1138, 374);
		pnlTong.add(scrollPane);

		table = new JTable();
		String[] headers = "Mã lớp học phần;Sĩ số;Tên Môn học phần;Năm;Học kì".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);

		btnThemLop.addActionListener(this);
		btnXaTrng.addActionListener(this);
		btnXoaLop.addActionListener(this);
		table_1.addMouseListener(this);
		btnCapNhat.addActionListener(this);
		
		
		dulieutenmon();
		dulieuHocKy();
		dulieuNamHoc();
		dulieubang();
		
		
		
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
							comboBoxFilter1(textfield.getText());
						}
					}
				});

			}
		});
	}
	
	public void comboBoxFilter1(String enteredText) {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (!cmbTenMon.isPopupVisible()) {
			cmbTenMon.showPopup();
		}

		ArrayList<String> filterArray= new ArrayList<String>();
		for (int i = 0; i < listTen.size(); i++) {
			if (listTen.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(listTen.get(i));
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
	
	public void dulieutenmon() {
		cmbTenMon.removeAllItems();
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		//System.out.println(listTen);
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (String ten: listTen) {
				cmbTenMon.addItem(ten);
			}
		}
	}

	public JPanel getJPanel() {
		return pnlTong;
		
	}

	/*
	 *	Cap Nhat Du lieu 
	 */
	public void dulieuHocKy() {
		ArrayList<Integer> listHocKy = new HocKyDao().getDSHocKy();
		if (listHocKy==null) {
			JOptionPane.showMessageDialog(null, "Lỗi kết nối");
		} else {
			for (int hk: listHocKy) {
				cmbHocKi.addItem(hk);
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
	 * Thêm dữ liệu vào bảng
	 */
	public void dulieubang() {
		ArrayList<LopHocPhan> list = dslhp.doctubang();
		for (LopHocPhan lhp : list) {
			String [] rowdata = {lhp.getMaLopHP(),lhp.getSiSo()+"",lhp.getMaMHP(),
					lhp.getNam()+"",lhp.getHocKy()+""};
			tableModel.addRow(rowdata);
		}
		table_1.setModel(tableModel);

	}
	

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void them() {
		int value = dslhp.LayMaTuDong()+5;
		String s1 = String.valueOf(value);
		int length = s1.length();

		if(length==1) {
			txtMaLop.setText("LHP0" + s1);
		}
		if(length>=2) {
			txtMaLop.setText("LHP" + s1);
		}
		txtSiSo.setText("0");
		cmbHocKi.setSelectedIndex(0);
		cmbNam.setSelectedIndex(0);
		dulieutenmon();
		cmbTenMon.setSelectedIndex(0);
	}
	public boolean kiemtra() {
		String siso = txtSiSo.getText().trim();
		int cm = cmbTenMon.getSelectedIndex();

		if(siso.length()>0) {
			try {
				int x = Integer.parseInt(siso);
				if(x<40||x>80) {
					JOptionPane.showMessageDialog(null,"Nhập sai sĩ số");
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		if(cm<0) {
			JOptionPane.showMessageDialog(null, "Tên môn học phần không được để trống");
			cmbTenMon.setSelectedIndex(0);
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXaTrng)) {
			them();
		}
		if(o.equals(btnThemLop)) {
			if(kiemtra()==true) {
				int n = 0;
				String maMon = dsmh.LayMaMon(cmbTenMon.getSelectedItem().toString());
				if(dslhp.ThemLopPhan(txtMaLop.getText(),Integer.parseInt(txtSiSo.getText()), 
						maMon,cmbNam.getSelectedItem().toString(),
						Integer.parseInt(cmbHocKi.getSelectedItem().toString()),n)) {
					Object[] datarow = {txtMaLop.getText(),txtSiSo.getText(),cmbTenMon.getSelectedItem(),
							cmbNam.getSelectedItem(),cmbHocKi.getSelectedItem()};
					tableModel.addRow(datarow);
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					them();

				}else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại");
				}
			}

		}
		if(o.equals(btnXoaLop)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				String maLop = (String)table_1.getValueAt(row, 0);
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac==JOptionPane.YES_OPTION) {
					if(dslhp.xoaLHP(maLop)) {
						tableModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa Thành công");
						them();
					}else {
						JOptionPane.showMessageDialog(null, "Chưa xóa lịch của lớp học phần này");
					}
				}
			}

		}
		if(o.equals(btnCapNhat)) {
			int row = table_1.getSelectedRow();
			if(row>=0) {
				int hoinhac = JOptionPane.showConfirmDialog(null,"Bạn có chắc không","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoinhac == JOptionPane.YES_OPTION) {
					String maMon = dsmh.LayMaMon(cmbTenMon.getSelectedItem().toString());
					if(dslhp.capNhatLopHocPhan(txtMaLop.getText(),Integer.parseInt(txtSiSo.getText().toString()),
							maMon,cmbNam.getSelectedItem().toString(),
							Integer.parseInt(cmbHocKi.getSelectedItem().toString()))){
						table_1.setValueAt(txtMaLop.getText(), row,0);
						table_1.setValueAt(txtSiSo.getText(), row,1);
						table_1.setValueAt(cmbTenMon.getSelectedItem().toString(), row,2);
						table_1.setValueAt(cmbNam.getSelectedItem(), row,3);
						table_1.setValueAt(cmbHocKi.getSelectedItem(), row,4);
						them();
						JOptionPane.showMessageDialog(null,"Sửa thành công");
					}
					else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
				}
			}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn lớp học phần");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table_1.getSelectedRow();
		txtMaLop.setText(table_1.getValueAt(row, 0).toString());
		txtSiSo.setText(table_1.getValueAt(row, 1).toString());
		cmbTenMon.setSelectedItem(table_1.getValueAt(row, 2).toString());
		cmbNam.setSelectedItem(table_1.getValueAt(row, 3).toString());
		cmbHocKi.setSelectedItem(table_1.getValueAt(row, 4).toString());
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
}
