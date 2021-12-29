package entity;

public class ThongKeLop {
	private String maMon;
	private String tenMon;
	private int soLop;
	public String getMaMon() {
		return maMon;
	}
	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public int getSoLop() {
		return soLop;
	}
	public void setSoLop(int soLop) {
		this.soLop = soLop;
	}
	public ThongKeLop(String maMon, String tenMon, int soLop) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.soLop = soLop;
	}
	public ThongKeLop() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maMon == null) ? 0 : maMon.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongKeLop other = (ThongKeLop) obj;
		if (maMon == null) {
			if (other.maMon != null)
				return false;
		} else if (!maMon.equals(other.maMon))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ThongKeLop [maMon=" + maMon + ", tenMon=" + tenMon + ", soLop=" + soLop + "]";
	}
	
	
	

}
