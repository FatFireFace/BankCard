import java.math.BigDecimal;

public final class DebitCardWithCashback extends DebitCard{
    private final BigDecimal sumForCb;
    private final BigDecimal cashbackPercent;
    public DebitCardWithCashback(BigDecimal initialBalance, BigDecimal cashbackPercent, BigDecimal SUM_FOR_CB) {
        super(initialBalance);
        this.cashbackPercent = cashbackPercent;
        this.sumForCb = SUM_FOR_CB;
    }

    //todo кешбек вынести за пределы общего расчета, чтобы не изменять логику. возможно стоит определить отдельный метод и вызывать его
    @Override
    public Boolean payFromCard(BigDecimal amount){
        //if amount > balance returns -1, amount == balance returns 0 and amount < balance returns 1
        if ((super.balance.compareTo(amount)) >= 0){
            balance = balance.subtract(amount);
                if (amount.compareTo(sumForCb) >= 0){
                balance = balance.add(amount.multiply(cashbackPercent.divide(BigDecimal.valueOf(100))));
                }
            return true;
        }
        else return false;
    }
}
