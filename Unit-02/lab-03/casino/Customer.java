class Customer {

    public Customer(){
        this.wallet = 1000.00;
    }

    public double spend(double amount){
        if(amount < this.wallet){
            this.wallet -= amount;
            return amount;
        } else {
            double walletAmount = this.wallet;
            this.wallet = 0;

            return walletAmount;
        }
    }

    private double wallet;
}