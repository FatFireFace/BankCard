import java.math.BigDecimal;

public final class CreditCardWithAdditionalSum extends CreditCard{
    final private BigDecimal percentForTopUp;
    public CreditCardWithAdditionalSum(BigDecimal initialBalance, BigDecimal initialCreditBalance, BigDecimal percentForTopUp){
        super(initialBalance, initialCreditBalance);
        this.percentForTopUp = percentForTopUp;
    }

    @Override
    public void topUpBalance(BigDecimal amount){
    amount = amount.add(amount.multiply(percentForTopUp.divide(BigDecimal.valueOf(100))));
        if (!(currentCreditBalance.equals(maxCreditBalance))){
            if(amount.compareTo(maxCreditBalance.subtract(currentCreditBalance)) >= 0){
                amount = amount.subtract(currentCreditBalance);
                currentCreditBalance = maxCreditBalance;
                balance = balance.add(amount);
            } else{
                currentCreditBalance = currentCreditBalance.add(amount);
            }
        }
        else this.balance = balance.add(amount);
    }
}
