package sec9b_innerclass.gearbox;

import sec9b_innerclass.button.Button;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Gearbox mcLaren = new Gearbox(6);

		mcLaren.addGear(1, 5.3);
		mcLaren.addGear(2, 10.6);
		mcLaren.addGear(3, 15.9);

		mcLaren.operateClutch(true);
		mcLaren.changeGear(1);

		mcLaren.operateClutch(false);
		System.out.println(mcLaren.wheelSpeed(1000));
		mcLaren.changeGear(2);
		System.out.println(mcLaren.wheelSpeed(3000));

		mcLaren.operateClutch(true);
		mcLaren.changeGear(3);

		mcLaren.operateClutch(false);
		System.out.println(Math.round(mcLaren.wheelSpeed(6000)));


//        Gearbox.Gear first = mcLaren.new Gear(1, 12.3);
//syntax to create an inner object of mcLaren object (when Gear class is not private)

//      System.out.println(first.driveSpeed(1000));
//
//        Gearbox.Gear second = new Gearbox.Gear(2, 15.4);
//        Gearbox.Gear third = new mcLaren.Gear(3, 17.8);
// that's not working

	}
}

