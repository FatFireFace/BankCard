import java.math.BigDecimal;

public class DebitCard extends BankCard{

    public DebitCard(BigDecimal initialBalance){
        super(initialBalance);
    }

    @Override
    public Boolean payFromCard(BigDecimal amount){
        //if amount > balance returns -1, amount == balance returns 0 and amount < balance returns 1
        if ((this.balance.compareTo(amount)) >= 0){
            balance = balance.subtract(amount);
            return true;
        }
        else return false;
    }
    @Override
    public String getBalance(){
        return "Current balance: " + balance + "\n";
    }
}
