package entity;

public class HocKy {
	private int HocKy;

	public int getHocKy() {
		return HocKy;
	}

	public void setHocKy(int hocKy) {
		HocKy = hocKy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + HocKy;
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
		HocKy other = (HocKy) obj;
		if (HocKy != other.HocKy)
			return false;
		return true;
	}

	public HocKy(int hocKy) {
		super();
		HocKy = hocKy;
	}

	public HocKy() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HocKy [HocKy=" + HocKy + "]";
	}
	
}
