import java.math.BigDecimal;

public class CreditCard extends BankCard {
    protected final   BigDecimal maxCreditBalance;
    protected         BigDecimal currentCreditBalance;

    public CreditCard(BigDecimal initialBalance, BigDecimal initialCreditBalance){
        super(initialBalance);
        this.maxCreditBalance = initialCreditBalance;
        this.currentCreditBalance = maxCreditBalance;
    }

    //todo стоит изменить логику, можно обойтись одним полем кредитного баланса, а реальный баланс может опускаться ниже нуля, тогда второе поле избыточно
    @Override
    public void topUpBalance(BigDecimal amount) {
        if (currentCreditBalance.compareTo(maxCreditBalance) < 0) {
            BigDecimal remainingCredit = maxCreditBalance.subtract(currentCreditBalance);
            if (amount.compareTo(remainingCredit) >= 0) {
                BigDecimal excessAmount = amount.subtract(remainingCredit);
                currentCreditBalance = maxCreditBalance;
                balance = balance.add(excessAmount);
            } else {
                currentCreditBalance = currentCreditBalance.add(amount);
            }
        } else {
            balance = balance.add(amount);
        }
    }
    @Override
    public Boolean payFromCard(BigDecimal amount){
            if ((currentCreditBalance.add(super.balance)).compareTo(amount) >= 0){
                    if ((amount.compareTo(balance)) >= 0){
                        amount = amount.subtract(balance);
                        balance = BigDecimal.ZERO;
                        this.currentCreditBalance = currentCreditBalance.subtract(amount);
                    }
                        else{
                            this.balance = balance.subtract(amount);
                    }
                return true;
            }
            else return false;
    }
    
    public String getBalance(){
        return "Own funds: " + (super.balance.add(this.currentCreditBalance)) + "\n";
                
    }
}
