package moneyTreePlanner;

public class SavingsAccountsConnectDB {

    private int id;
    private String bankName;
    private int accountNo;
    private int amount;

    public SavingsAccountsConnectDB(int id, String bankName, int accountNo, int amount) {
        this.id = id;
        this.bankName = bankName;
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public int getAmount() {
        return amount;
    }
}

