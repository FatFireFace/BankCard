import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

final public class DebitCardWithCategories extends DebitCardWithCashback{
    private final HashSet<String> categories;
    public DebitCardWithCategories(BigDecimal initialBalance, BigDecimal cashbackPercent, String[] categories) {
        super(initialBalance, cashbackPercent, BigDecimal.ZERO);
        this.categories = new HashSet<>(Arrays.asList(categories));
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
    public String getAvailableFunds(){
        return "Own funds: " + super.balance + "\n"
                + "Cashback categories: "+ String.join(" ", categories) + "\n";
    }
}
