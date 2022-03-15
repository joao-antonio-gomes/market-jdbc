package market.model.entities;

public class Customer {
    private long id;
    private String name;
    private String cellphone;
    private String cpf;

    public Customer() {
    }

    public Customer(long id, String name, String cellphone, String cpf) {
        this.id = id;
        this.name = name;
        this.cellphone = cellphone;
        this.cpf = cpf;
    }

    public Customer(String name, String cellphone, String cpf) {
        this.name = name;
        this.cellphone = cellphone;
        this.cpf = cpf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
