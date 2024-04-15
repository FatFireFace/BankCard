import java.math.BigDecimal;

public class DebitCardWithCashback extends DebitCard{
    private final BigDecimal SUM_FOR_CB;
    protected final BigDecimal cashbackPercent;
    public DebitCardWithCashback(BigDecimal initialBalance, BigDecimal cashbackPercent, BigDecimal SUM_FOR_CB) {
        super(initialBalance);
        this.cashbackPercent = cashbackPercent;
        this.SUM_FOR_CB = SUM_FOR_CB;
    }

    @Override
    public Boolean payFromCard(BigDecimal amount){
        //if amount > balance returns -1, amount == balance returns 0 and amount < balance returns 1
        if ((super.balance.compareTo(amount)) >= 0){
            balance = balance.subtract(amount);
                if (amount.compareTo(SUM_FOR_CB) >= 0){
                balance = balance.add(amount.multiply(cashbackPercent.divide(BigDecimal.valueOf(100))));
                }
            return true;
        }
        else return false;
    }
}
