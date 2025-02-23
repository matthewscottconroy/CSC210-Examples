import java.util.ArrayList;

class GoodCasino {

    public static void main(String []args){
        System.out.println("Hello World!");

        ArrayList<SlotMachine> machines = new ArrayList<SlotMachine>();
        ArrayList<Customer> customers = new ArrayList<Customer>();

        for(int i = 0; i < 3; i++){
            machines.add(new SlotMachine());
            customers.add(new Customer());
        }

        machines.get(0).toString();
        double amountSpent = customers.get(0).spend(100);
        machines.get(0).pullLever(amountSpent);
        machines.get(0).toString();

        System.out.println("Goodbye Cruel World!");
    }
}