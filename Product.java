abstract public class Product {
    private String name;
    private double price;
    private int quantity;



    public Product(String name,double price,int quantity){
    this.name=name;
    this.price=price;
    this.quantity=quantity;
    }
    public boolean isAvailable(int quantity){
        return quantity<=this.quantity;
    }
    public void reduceQuantity(int quantity){
     if(quantity>this.quantity){
         throw new IllegalArgumentException("The Quantity is more than the quantity in stock");
     }
     this.quantity-=quantity;
    }
    public String getName() { return this.name; }
    public double getPrice() { return this.price; }
    public int getQuantity() { return this.quantity; }
}

