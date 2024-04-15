import java.math.BigDecimal;

public class CreditCardWithAdditionalSum extends CreditCard{
    final private BigDecimal percentForTopUp;
    public CreditCardWithAdditionalSum(BigDecimal initialBalance, BigDecimal initialCreditBalance, BigDecimal percentForTopUp){
        super(initialBalance, initialCreditBalance);
        this.percentForTopUp = percentForTopUp;
    }

    @Override
    public void topUpBalance(BigDecimal amount){
    amount = amount.add(amount.multiply(percentForTopUp.divide(BigDecimal.valueOf(100))));
        if (!(CurrentCreditBalance.equals(MaxCreditBalance))){
            if(amount.compareTo(MaxCreditBalance.subtract(CurrentCreditBalance)) >= 0){
                amount = amount.subtract(CurrentCreditBalance);
                CurrentCreditBalance = MaxCreditBalance;
                balance = balance.add(amount);
            } else{
                CurrentCreditBalance = CurrentCreditBalance.add(amount);
            }
        }
        else this.balance = balance.add(amount);
    }
}
