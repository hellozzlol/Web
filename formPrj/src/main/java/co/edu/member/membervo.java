package co.edu.member;

public class membervo {
	private int membno;
	private String membname;
	private String membphone;
	private String membaddr;
	private String membbirth ;
	private String membImage ;
	
	
	public int getMembno() {
		return membno;
	}
	
	public String getMembImage() {
		return membImage;
	}

	public void setMembImage(String membImage) {
		this.membImage = membImage;
	}

	public void setMembno(int membno) {
		this.membno = membno;
	}
	public String getMembname() {
		return membname;
	}
	public void setMembname(String membname) {
		this.membname = membname;
	}
	public String getMembphone() {
		return membphone;
	}
	public void setMembphone(String membphone) {
		this.membphone = membphone;
	}
	public String getMembaddr() {
		return membaddr;
	}
	public void setMembaddr(String membaddr) {
		this.membaddr = membaddr;
	}
	public String getMembbirth() {
		return membbirth;
	}
	public void setMembbirth(String membbirth) {
		this.membbirth = membbirth;
	}

	@Override
	public String toString() {
		return "membervo [membno=" + membno + ", membname=" + membname + ", membphone=" + membphone + ", membaddr="
				+ membaddr + ", membbirth=" + membbirth + ", membImage=" + membImage + "]";
	}
	
	
}
