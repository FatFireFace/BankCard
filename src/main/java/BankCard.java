import java.math.BigDecimal;

abstract class BankCard {
    protected BigDecimal balance;

    public BankCard(BigDecimal initialBalance){
        this.balance = initialBalance;
    }
    public void topUpBalance(BigDecimal amount){
        this.balance = balance.add(amount);
    }

    public abstract Boolean payFromCard(BigDecimal amountOfBill);

    public BigDecimal getBalance(){
        return balance;
    }

    public abstract String getAvailableFunds();

}
