package business;

import java.io.Serializable;

public final class LibraryMember extends Person implements Serializable {
	private final String memberId;
	private CheckoutRecord checkoutRecord;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		this.checkoutRecord = BookCheckoutRecordAndEntryFactory.createCheckoutRecord(this);
	}
	
	public String getMemberId() {
		return memberId;
	}

	
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void updateCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}
}
