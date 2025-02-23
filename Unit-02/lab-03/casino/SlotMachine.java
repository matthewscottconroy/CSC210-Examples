import java.util.Random;
import java.util.Date;

class SlotMachine {

    public static enum SlotSymbol {
        HEART,
        SMILEY,
        SEVEN
    }

    public SlotMachine(){
        long seed = new Date().getTime();

        this.random = new Random(seed);
        this.slots = new SlotSymbol[3];
        this.moneyPot = 10999.00;
    }

    public SlotMachine(double moneyPot){
        long seed = new Date().getTime();
         
        this.random = new Random(seed);
        this.slots = new SlotSymbol[3];
        this.moneyPot = moneyPot;
    }


    public static char retrieveSymbol(SlotSymbol s) throws Exception {
        switch(s){
            case HEART:
                return  '\u2764';
            case SMILEY:
                return '\u263A';
            case SEVEN:
                return '\u0037';
            default:
                throw new Exception();
        }
    }

   private SlotSymbol getRandomSymbol(){
        SlotSymbol[] s = SlotSymbol.values();
        int index = this.random.nextInt(3);
        return s[index];
   }

   private boolean isJackPot(){
        boolean isStillSame = true;

        for(int i = 0; i < 2; i++){
            if(this.slots[i] != this.slots[i+1]){
                isStillSame = false;
            }
        }

        return isStillSame;
   }

    public double pullLever(double wager){
       for(int i = 0; i < 3; i++){
            this.slots[i] = this.getRandomSymbol();
       } 
       
       if (this.isJackPot()){
        double jackpot = wager * 10;
        this.moneyPot = moneyPot - jackpot;

        return jackpot;
       } else {
        return 0;
       }
    }

    public String toString(){
        System.out.println("Money pot has: $" + this.moneyPot);
        for(int i = 0; i < 3; i++){
            System.out.println(this.slots[i]);
        }
        return "Not Set Yet";
    }

    private SlotSymbol[] slots;
    private double moneyPot;
    private long seed;
    private Random random;

    public static void main(String args[]){
        try {
            //char v = SlotMachine.retrieveSymbol(SlotSymbol.SMILEY);
            //System.out.println(v);
            //SlotSymbol[] s = SlotSymbol.values();

            //System.out.println(s[0]);
            SlotMachine s = new SlotMachine(22999);
            s.toString();
            s.pullLever(300.00);
            s.toString();;
            s.pullLever(100.00);
            s.toString();
            s.pullLever(500.00);
            s.toString();
            

        } catch(Exception e){
            System.err.println(e);
        } 
    }
}