package moneyTreePlanner;

public class OutgoingSoursersConnectDB {

    private int id;
    private String sourceName;
    private int amount;
    private String description;

    public OutgoingSoursersConnectDB(int id, String sourceName, int amount, String description) {
        this.id = id;
        this.sourceName = sourceName;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

