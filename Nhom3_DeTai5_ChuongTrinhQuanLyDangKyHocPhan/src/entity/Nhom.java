package entity;

public class Nhom {
	private String maNhom;

	public String getMaNhom() {
		return maNhom;
	}

	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}

	public Nhom(String maNhom) {
		super();
		this.maNhom = maNhom;
	}

	public Nhom() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNhom == null) ? 0 : maNhom.hashCode());
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
		Nhom other = (Nhom) obj;
		if (maNhom == null) {
			if (other.maNhom != null)
				return false;
		} else if (!maNhom.equals(other.maNhom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nhom [maNhom=" + maNhom + "]";
	}
	

}
