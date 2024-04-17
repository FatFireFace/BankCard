import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public final class DebitCardWithCategories extends DebitCard{
    private final HashSet<String> categories;
    private final BigDecimal cashbackPercent;

    //todo можно использовать мапу и реализовать разные проценты под категории
    public DebitCardWithCategories(BigDecimal initialBalance, BigDecimal cashbackPercent, String[] categories) {
        super(initialBalance);
        this.categories = new HashSet<>(Arrays.asList(categories));
        this.cashbackPercent = cashbackPercent;
    }


    public Boolean payFromCard(BigDecimal amount, String category){
        if ((super.balance.compareTo(amount)) >= 0){
            balance = balance.subtract(amount);
            if (categories.contains(category)){
                balance = balance.add(amount.multiply(cashbackPercent.divide(BigDecimal.valueOf(100))));
            }
            return true;
        }
        else return false;
    }

    @Override
    public String getBalance(){
        return "Own funds: " + super.balance + "\n"
                + "Cashback categories: "+ String.join(" ", categories) + "\n";
    }
}
