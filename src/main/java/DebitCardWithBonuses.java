import java.math.BigDecimal;

public final class DebitCardWithBonuses extends DebitCard{
    private final BigDecimal percentForTopUp;
    private BigDecimal sumOfBonuses;

    public DebitCardWithBonuses(BigDecimal initialBalance, BigDecimal percentForTopUp){
        super(initialBalance);
        this.percentForTopUp = percentForTopUp;
    }

    @Override
    public void topUpBalance(BigDecimal amount){
        super.topUpBalance(amount);
        sumOfBonuses = sumOfBonuses.add(amount.multiply(percentForTopUp).divide(BigDecimal.valueOf(100)));
    }

    public String getAvailableFunds(){
        return "Текущий баланс: " + balance + "   накопленные бонусы: " + sumOfBonuses + "\n";
    }

}
