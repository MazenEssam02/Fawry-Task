import java.time.LocalDate;

public class main {

    public static void main(String[] args) {
        
        Product tv = new ShippableProduct("Smart TV", 600, 10, 10000);
        Product mobile = new RegularProduct("Smartphone", 300, 20);
        Product cheese = new ShippableProduct("Cheese", 5, 3, 200);
        Product scratchCard = new RegularProduct("Scratch Card", 10, 100);
        Product biscuits = new ExpirableProduct("Biscuits", 2, 30, LocalDate.of(2023, 12, 31));
        // change this ti 300 to check when customer insufficient Balance
        Customer customer = new Customer("Mazen Essam", 3000.00);
        Cart cart = new Cart();

        try {
            // remove these lines to check when cart is empty
            cart.add(tv, 1);
            cart.add(mobile, 1);
            cart.add(biscuits, 9);
            cart.add(scratchCard, 5);

            // add more than available (fail)
//            cart.add(cheese, 4);


            Checkout(customer,cart);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void Checkout(Customer customer, Cart cart){
        if (cart.isEmpty()){
            throw new IllegalStateException("The Cart is Empty");
        }
        int subTotal= cart.getSubTotal();
        int shippingFees= cart.getShippingFees();
        int total=subTotal+shippingFees;
        try{
            customer.deductBalance(total);
            for (CartItem item : cart.list) {
                item.getProduct().reduceQuantity(item.getQuantity());
            }

            System.out.println("\n=== CHECKOUT DETAILS ===");
            System.out.println("Customer: " + customer.getName());
            if(shippingFees>0){
                double totalWeight=0;
                System.out.println("\n** Shipment notice **");
                for (CartItem item : cart.list) {
                    if(item.getProduct() instanceof ShippableProduct){
                        int itemWeight=(int)((ShippableProduct) item.getProduct()).getWeight()*item.getQuantity();
                        System.out.printf("%dx %-12s %5d%n",
                                item.getQuantity(),
                                item.getProduct().getName(),
                                itemWeight);
                        totalWeight+=itemWeight;
                    }
                }
                System.out.println("Total package weight "+totalWeight/1000+"kg");
            }
            System.out.println("\n** Checkout receipt **");
            for (CartItem item : cart.list) {
                System.out.printf("%dx %-14s %5d%n",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        (int)item.getProduct().getPrice()*item.getQuantity());

            }
            System.out.printf("--------------------------------------");
            System.out.printf("\nSubtotal:                %d\n", subTotal);
            System.out.printf("Shipping fees:           %d\n", shippingFees);
            System.out.printf("Total paid:              %d\n", total);
            System.out.printf("Remaining balance:       %.2f\n", customer.getBalance());


            cart.list.clear();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
