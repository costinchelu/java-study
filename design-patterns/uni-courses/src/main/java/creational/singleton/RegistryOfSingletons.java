package creational.singleton;

import java.util.HashMap;
import java.util.Map;

public class RegistryOfSingletons {

    public static void main(String[] args) {
        TouristicPackage acc1 = new PackageAccommodation();
        TouristicPackage acc2 = new PackageAccommodation();
        TouristicPackage transport = new PackageTransport();
        TouristicPackage complete = new PackageAllInclusive();

        try {
            PackageRegistry.register("Accommodation", acc1);
//            PackageRegistry.register("Accommodation", acc2);
            PackageRegistry.register("Transport", transport);
            PackageRegistry.register("All inclusive", complete);

            PackageRegistry.getPackage("Accommodation").description();
            PackageRegistry.getPackage("Transport").description();
            PackageRegistry.getPackage("All inclusive").description();

        } catch (AlreadyInstantiatedException e) {
            System.out.println(e.getMessage());
        }
    }
}


interface TouristicPackage {

    void description();
}

class PackageTransport implements TouristicPackage {

    @Override
    public void description() {
        System.out.println("Package includes only transport.");
    }
}

class PackageAccommodation implements TouristicPackage {

    @Override
    public void description() {
        System.out.println("Package includes only accommodation.");
    }
}

class PackageAllInclusive implements TouristicPackage {

    @Override
    public void description() {
        System.out.println("Package including transport and accommodation.");
    }
}

class PackageRegistry {

    static Map<String, TouristicPackage> packageCollection = new HashMap<>();

    public static void register(String name, TouristicPackage pack) throws AlreadyInstantiatedException {
        if (packageCollection.containsKey(name)) {
            throw new AlreadyInstantiatedException("Key " + name + " already registered!");
        }
        packageCollection.put(name, pack);
    }

    public static TouristicPackage getPackage(String name) {
        return packageCollection.get(name);
    }
}

class AlreadyInstantiatedException extends Exception {

    public AlreadyInstantiatedException(String message) {
        super(message);
    }
}