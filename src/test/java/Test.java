import java.math.BigDecimal;

public class Test{
    private static void terminalMessage(Boolean result){
        if (result){
            System.out.println("operation successfully completed");
        } else {
            System.out.println("insufficient funds");
        }
    }

    private static void testDB(){
        System.out.println("Создаем дебетовую карту с балансом в 5000");
        DebitCard debitCard = new DebitCard(BigDecimal.valueOf(5000)); //карта с начальным балансом в 5000 рублей
        System.out.println("Пополнение на 2000");
        debitCard.topUpBalance(BigDecimal.valueOf(2000));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 3000");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(3000)));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 5000, недостаток средств");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(5000)));
        System.out.println(debitCard.getBalance());
    }

    private static void testDBCB(){
        System.out.println("Создаем дебетовую карту с балансом в 5000 и кешбеком в 1.0 процент при покупках от 5000");
        DebitCardWithCashback debitCard = new DebitCardWithCashback(BigDecimal.valueOf(5000), BigDecimal.valueOf(1.0), BigDecimal.valueOf(5000));
        System.out.println("Пополнение на 5000");
        debitCard.topUpBalance(BigDecimal.valueOf(5000));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 3000, недостаточно для кешбека");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(3000)));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 6000, зачислен кешбек");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(6000)));
        System.out.println(debitCard.getBalance());
    }

    private static void testDBWC(){
        System.out.println("Создаем дебетовую карту с балансом в 5000 и кешбеком в 1.5 на категории \"поездки, путешествия и продукты \"");
        DebitCardWithCategories debitCard = new DebitCardWithCategories(BigDecimal.valueOf(5000), BigDecimal.valueOf(1.0), new String[]{"поездки", "путешествия", "продукты"});
        System.out.println("Пополнение на 7000");
        debitCard.topUpBalance(BigDecimal.valueOf(7000));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 3000 категория \"продукты\"");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(3000), "продукты"));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 1500 категория \"украшения\"");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(1500), "украшения"));
        System.out.println(debitCard.getBalance());

        System.out.println("Покупка на 6000");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(6000)));
        System.out.println(debitCard.getBalance());
    }

    private static void testCC(){
        System.out.println("Создаем кредитную карту c начальным балансом 0 и максимальным кредитным балансом 10000");
        CreditCard creditCard = new CreditCard(BigDecimal.valueOf(0), BigDecimal.valueOf(10000));
        creditCard.topUpBalance(BigDecimal.valueOf(10000));
        System.out.println(creditCard.getBalance());

        System.out.println("Пополнить карту на 5000");
        creditCard.topUpBalance(BigDecimal.valueOf(5000));
        System.out.println(creditCard.getBalance());

        System.out.println("Покупка на 5000");
        terminalMessage(creditCard.payFromCard(BigDecimal.valueOf(5000)));
        System.out.println(creditCard.getBalance());

        System.out.println("Покупка на 3000");
        terminalMessage(creditCard.payFromCard(BigDecimal.valueOf(3000)));
        System.out.println(creditCard.getBalance());

        System.out.println("Пополнение на 2000");
        creditCard.topUpBalance(BigDecimal.valueOf(2000));
        System.out.println(creditCard.getBalance());

        System.out.println("Пополнение на 2000");
        creditCard.topUpBalance(BigDecimal.valueOf(2000));
        System.out.println(creditCard.getBalance());
    }

    private static void testCCWAS(){
        System.out.println("Создаем кредитную карту c начальным балансом 0, максимальным кредитным балансом 10000 и дополнительным накоплением 0.005% при пополнении");
        CreditCardWithAdditionalSum creditCard = new CreditCardWithAdditionalSum(BigDecimal.valueOf(0), BigDecimal.valueOf(10000), BigDecimal.valueOf(0.005));
        creditCard.topUpBalance(BigDecimal.valueOf(10000));
        System.out.println(creditCard.getBalance());

        System.out.println("Пополнить карту на 5000");
        creditCard.topUpBalance(BigDecimal.valueOf(5000));
        System.out.println(creditCard.getBalance());

        System.out.println("Покупка на 5000");
        terminalMessage(creditCard.payFromCard(BigDecimal.valueOf(5000)));
        System.out.println(creditCard.getBalance());

        System.out.println("Покупка на 3000");
        terminalMessage(creditCard.payFromCard(BigDecimal.valueOf(3000)));
        System.out.println(creditCard.getBalance());

        System.out.println("Пополнение на 2000");
        creditCard.topUpBalance(BigDecimal.valueOf(2000));
        System.out.println(creditCard.getBalance());

        System.out.println("Пополнение на 2000");
        creditCard.topUpBalance(BigDecimal.valueOf(2000));
        System.out.println(creditCard.getBalance());
    }

    private static void testDBWB(){
        System.out.println("Создаем дебетовую карту с балансом в 5000 и накоплением в 1 процент от суммы пополнений");
        DebitCardWithBonuses debitCard = new DebitCardWithBonuses(BigDecimal.valueOf(5000), BigDecimal.valueOf(1));
        System.out.println("Пополнение на 5000");
        debitCard.topUpBalance(BigDecimal.valueOf(5000));
        System.out.println(debitCard.getAvailableFunds());

        System.out.println("Покупка на 3000");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(3000)));
        System.out.println("Пополнение на 10000");
        debitCard.topUpBalance(BigDecimal.valueOf(10000));
        System.out.println(debitCard.getAvailableFunds());

        System.out.println("Покупка на 6000");
        terminalMessage(debitCard.payFromCard(BigDecimal.valueOf(6000)));
        System.out.println(debitCard.getAvailableFunds());
    }

    public static void main(String[] args){
        testDB();
        System.out.println("****");
        testDBCB();
        System.out.println("****");
        testDBWC();
        System.out.println("****");
        testCC();
        System.out.println("****");
        testCCWAS();
    }
}
