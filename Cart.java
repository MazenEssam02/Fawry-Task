import java.util.ArrayList;

public class Cart {
private Customer customer;
protected ArrayList <CartItem> list;

    public Cart() {

        this.list = new ArrayList<>();
    }
    public void add(Product product,int quantity){
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough quantity available for " + product.getName());
        }

        list.add(new CartItem(product,quantity));
    }
    public int getSubTotal(){
        double subTotal=0;
        for(CartItem item :list){
            double price= item.getItemTotal();
            subTotal+=price;
        }
        return (int) subTotal;
    }
    public int getShippingFees(){
        double shippingFees = 0;

        for (CartItem item : list) {
            if (item.getProduct() instanceof ShippableProduct) {
                ShippableProduct sp = (ShippableProduct) item.getProduct();
                shippingFees += sp.getWeight()*0.01 * item.getQuantity();
            }
        }

        return (int) shippingFees;
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }
    public ArrayList<CartItem> getList(){
        return this.list;
    }
}

