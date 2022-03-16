package market.model.entities;

import java.time.LocalDate;

public class Order {
    private long id;
    private long userId;
    private long productId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    private LocalDate date;

    public Order() {
    }

    public Order(long userId, long productId) {
        this.userId = userId;
        this.productId = productId;
        this.date = LocalDate.now();
    }

    public Order(long id, long userId, long productId, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
