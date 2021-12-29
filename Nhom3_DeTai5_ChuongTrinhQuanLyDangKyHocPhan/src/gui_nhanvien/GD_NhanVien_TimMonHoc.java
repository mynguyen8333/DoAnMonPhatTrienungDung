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
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.KhoaDao;
import dao.MonHocPhanDao;
import entity.MonHocPhan;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class GD_NhanVien_TimMonHoc extends JFrame implements ActionListener,MouseListener{

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JPanel pnlTong;
	private JButton btnTimKiem1,btnTimKiem2,btnTimKiem3;
	private JComboBox<String>cmbTenMon,cmbTinChi,cmbTenKhoa ;
	private DefaultTableModel tableModel;
	private MonHocPhanDao dsmh = new MonHocPhanDao();
	private KhoaDao kh = new KhoaDao();
	private JPanel pnlTimTheoTenMon;
	private JPanel pnlTimTheoSoTC;
	private JPanel pnlTimTheoTenKhoa;
	private JLabel lblTieuDeBang;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVien_TimMonHoc window = new GD_NhanVien_TimMonHoc();
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
	public GD_NhanVien_TimMonHoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		pnlTong = new JPanel();
		frame.getContentPane().add(pnlTong, BorderLayout.CENTER);
		pnlTong.setLayout(null);

		JLabel lblTietDe = new JLabel("TÌM KIẾM MÔN HỌC PHẦN");
		lblTietDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTietDe.setBounds(500, 24, 364, 27);
		pnlTong.add(lblTietDe);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 306, 1110, 342);
		pnlTong.add(scrollPane);

		table = new JTable();

		String[] headers = "Mã môn học phần;Tên môn học;Số tín chỉ;Hình thức thi;Bắt buộc;Tên Khoa;Học phần yêu cầu".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table_1 = new JTable(tableModel);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		scrollPane.setViewportView(table_1);

		pnlTimTheoTenMon = new JPanel();
		pnlTimTheoTenMon.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm theo t\u00EAn m\u00F4n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimTheoTenMon.setBounds(100, 66, 1110, 50);
		pnlTong.add(pnlTimTheoTenMon);
		pnlTimTheoTenMon.setLayout(null);

		JLabel lblTenMon = new JLabel("Tên môn:");
		lblTenMon.setBounds(100, 21, 79, 18);
		pnlTimTheoTenMon.add(lblTenMon);
		lblTenMon.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		cmbTenMon = new JComboBox<String>();
		cmbTenMon.setBounds(165, 20, 355, 22);
		pnlTimTheoTenMon.add(cmbTenMon);

		btnTimKiem1 = new JButton("Tìm kiếm");
		btnTimKiem1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnTimKiem1.setBounds(543, 20, 100, 23);
		pnlTimTheoTenMon.add(btnTimKiem1);

		btnTimKiem1.addActionListener(this);

		pnlTimTheoSoTC = new JPanel();
		pnlTimTheoSoTC.setLayout(null);
		pnlTimTheoSoTC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm theo s\u1ED1 t\u00EDn ch\u1EC9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimTheoSoTC.setBounds(100, 130, 1110, 50);
		pnlTong.add(pnlTimTheoSoTC);

		JLabel lblSoTinChi = new JLabel("Số tín chỉ:");
		lblSoTinChi.setBounds(100, 18, 79, 18);
		pnlTimTheoSoTC.add(lblSoTinChi);
		lblSoTinChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		cmbTinChi = new JComboBox<String>();
		cmbTinChi.setBounds(165, 17, 355, 22);
		pnlTimTheoSoTC.add(cmbTinChi);

		btnTimKiem2 = new JButton("Tìm kiếm");
		btnTimKiem2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnTimKiem2.setBounds(543, 17, 100, 23);
		pnlTimTheoSoTC.add(btnTimKiem2);
		btnTimKiem2.addActionListener(this);
		cmbTinChi.addItem("2");
		cmbTinChi.addItem("3");
		cmbTinChi.addItem("4");
		cmbTinChi.addItem("5");

		pnlTimTheoTenKhoa = new JPanel();
		pnlTimTheoTenKhoa.setLayout(null);
		pnlTimTheoTenKhoa.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm theo t\u00EAn khoa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimTheoTenKhoa.setBounds(100, 194, 1110, 50);
		pnlTong.add(pnlTimTheoTenKhoa);

		JLabel lblKhoa = new JLabel("Tên khoa:");
		lblKhoa.setBounds(100, 18, 79, 18);
		pnlTimTheoTenKhoa.add(lblKhoa);
		lblKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		cmbTenKhoa = new JComboBox<String>();
		cmbTenKhoa.setBounds(165, 18, 355, 22);
		pnlTimTheoTenKhoa.add(cmbTenKhoa);

		btnTimKiem3 = new JButton("Tìm kiếm");
		btnTimKiem3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnTimKiem3.setBounds(543, 17, 100, 23);
		pnlTimTheoTenKhoa.add(btnTimKiem3);

		lblTieuDeBang = new JLabel("Danh sách môn tìm được");
		lblTieuDeBang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTieuDeBang.setBounds(100, 268, 174, 27);
		pnlTong.add(lblTieuDeBang);
		btnTimKiem3.addActionListener(this);

		DataBase.getInstance().connect();
		cbBoxTenKhoa();
		cbBoxTenMon();

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
							comboBoxFilter(textfield1.getText());
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
	public void comboBoxFilter(String enteredText) {
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

	public JPanel getJPanel() {
		return pnlTong;
	}

	/*
	 * Dữ liệu
	 */

	public void cbBoxTenKhoa() {
		ArrayList<String> listTen = new KhoaDao().getDsTen();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Loi ket noi");
		} else {
			for (String ten: listTen) {
				cmbTenKhoa.addItem(ten);
			}

		}
	}

	public void cbBoxTenMon() {
		ArrayList<String> listTen = new MonHocPhanDao().getDsTenMon();
		if (listTen==null) {
			JOptionPane.showMessageDialog(null, "Loi ket noi");
		} else {
			for (String ten: listTen) {
				cmbTenMon.addItem(ten);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		Object o = e.getSource();
		if(o.equals(btnTimKiem1)) {
			tableModel.setRowCount(0);
			ArrayList<MonHocPhan> list = dsmh.TimTheoTen(cmbTenMon.getSelectedItem().toString().trim());
			if(list.size()!=0) {
				for (MonHocPhan mh : list) {
					String tenMon = dsmh.LayTenMon(mh.getHocPhanYeuCau());
					String tenKhoa = kh.LayTenKhoa(mh.getMaKhoa());
					String [] rowdata = {mh.getMaMonHocPhan(),mh.getTenMonHocPhan(),
							mh.getSoTinChi()+"",mh.getHinhThucThi(),mh.getBatBuoc(),tenKhoa,
							tenMon};
					tableModel.addRow(rowdata);
				}
				table_1.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
				cmbTenMon.setSelectedIndex(0);
			}
			
		}
		if(o.equals(btnTimKiem2)) {
			tableModel.setRowCount(0);
			ArrayList<MonHocPhan> list = dsmh.TimTinChi(Integer.parseInt(cmbTinChi.getSelectedItem().toString()));

			if(list.size()!=0) {
				for (MonHocPhan mh : list) {
					String tenMon = dsmh.LayTenMon(mh.getHocPhanYeuCau());
					String tenKhoa = kh.LayTenKhoa(mh.getMaKhoa());
					String [] rowdata = {mh.getMaMonHocPhan(),mh.getTenMonHocPhan(),
							mh.getSoTinChi()+"",mh.getHinhThucThi(),mh.getBatBuoc(),tenKhoa,
							tenMon};
					tableModel.addRow(rowdata);
				}
				table_1.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
			}

		}
		if(o.equals(btnTimKiem3)) {
			tableModel.setRowCount(0);
			ArrayList<MonHocPhan> list = dsmh.TimKiemTheoKhoa(cmbTenKhoa.getSelectedItem().toString().trim());

			if(list.size()!=0) {
				for (MonHocPhan mh : list) {
					String tenMon = dsmh.LayTenMon(mh.getHocPhanYeuCau());
					String tenKhoa = kh.LayTenKhoa(mh.getMaKhoa());
					String [] rowdata = {mh.getMaMonHocPhan(),mh.getTenMonHocPhan(),
							mh.getSoTinChi()+"",mh.getHinhThucThi(),mh.getBatBuoc(),tenKhoa,
							tenMon};
					tableModel.addRow(rowdata);
				}
				table_1.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
				cmbTenKhoa.setSelectedIndex(0);
			}

		}

	}
}
