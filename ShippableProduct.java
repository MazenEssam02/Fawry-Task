public class ShippableProduct extends Product{
    private int weight;
    public ShippableProduct(String name, double price, int quantity,int weight) {
        super(name, price, quantity);
        this.weight=weight;
    }
    public double getWeight() { return this.weight; }
}
