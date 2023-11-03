package sec9a_interfaces.phone;

public class MobilePhone implements iTelephone{

	private int myNumber;
	private boolean isRinging;
	private boolean isOn = false;
	
	
	public MobilePhone(int myNumber) {
		super();
		this.myNumber = myNumber;
	}
	
	
	@Override
	public void powerOn() {
		isOn = true;
		System.out.println("Mobile phone powered up");
	}
	
	@Override
	public void dial(int phoneNumber) {
		if (isOn) {
		System.out.println("Now ringing " + phoneNumber + " on desk phone.");
		}
		else {
			System.out.println("Phone is off");
		}
	}
	
	@Override
	public void answer() {
		if (isRinging) {
			System.out.println("Answering the mobile phone");
			isRinging = false;
		}
	}
	
	@Override
	public boolean callPhone(int phoneNumber) {
		if (phoneNumber == myNumber && isOn) {
			isRinging = true;
			System.out.println("Melody ring!");
		}
		else {
			isRinging = false;
			System.out.println("Mobile phone not on or number different.");
		}
		return isRinging;
	}
	
	@Override
	public boolean ringingStatus() {
		return isRinging;
	}
}
