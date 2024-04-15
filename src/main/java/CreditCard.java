import java.math.BigDecimal;

public class CreditCard extends BankCard {
    protected final   BigDecimal MaxCreditBalance;
    protected         BigDecimal CurrentCreditBalance = BigDecimal.ZERO;

    public CreditCard(BigDecimal initialBalance, BigDecimal initialCreditBalance){
        super(initialBalance);
        this.MaxCreditBalance = initialCreditBalance;
    }

    @Override
    public void topUpBalance(BigDecimal amount) {
        if (CurrentCreditBalance.compareTo(MaxCreditBalance) < 0) {
            BigDecimal remainingCredit = MaxCreditBalance.subtract(CurrentCreditBalance);
            if (amount.compareTo(remainingCredit) >= 0) {
                BigDecimal excessAmount = amount.subtract(remainingCredit);
                CurrentCreditBalance = MaxCreditBalance;
                balance = balance.add(excessAmount);
            } else {
                CurrentCreditBalance = CurrentCreditBalance.add(amount);
            }
        } else {
            balance = balance.add(amount);
        }
    }
    @Override
    public Boolean payFromCard(BigDecimal amount){
            if ((CurrentCreditBalance.add(super.balance)).compareTo(amount) >= 0){
                    if ((amount.compareTo(balance)) >= 0){
                        amount = amount.subtract(balance);
                        balance = BigDecimal.ZERO;
                        this.CurrentCreditBalance = CurrentCreditBalance.subtract(amount);
                    }
                        else{
                            this.balance = balance.subtract(amount);
                    }
                return true;
            }
            else return false;
    }

    @Override
    public String getAvailableFunds(){
        return "Own funds: " + super.balance + "\n"
                + "Credit balance: " + this.CurrentCreditBalance + "\n";
    }
}
