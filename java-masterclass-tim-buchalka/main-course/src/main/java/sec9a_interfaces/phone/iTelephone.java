package sec9a_interfaces.phone;

public interface iTelephone {

	//interfaces can have variables, but they will all be static final variables
	// 								(essentially constant values that you never change)

	//they don't have constructors (of course they can't instantiate)

	//all methods are "public" by default (exists only to be implemented)

	public void powerOn();
	void dial(int phoneNumber);
	void answer();
	boolean callPhone(int phoneNumber);
	boolean ringingStatus();
}
