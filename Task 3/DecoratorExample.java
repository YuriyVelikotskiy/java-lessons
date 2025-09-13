import java.util.Arrays;

public class DecoratorExample {
    public interface IceCream {
        String getDescription();

        int getPrice();
    }

    public abstract static class IceCreamDecorator implements IceCream {
        protected IceCream iceCream;

        public IceCreamDecorator(IceCream iceCream) {
            this.iceCream = iceCream;
        }
    }

    public static class ConfettiDecorator extends IceCreamDecorator {

        public ConfettiDecorator(IceCream iceCream) {
            super(iceCream);
        }

        @Override
        public String getDescription() {
            return iceCream.getDescription().concat(" with Confetti");
        }

        @Override
        public int getPrice() {
            return iceCream.getPrice() + 5;
        }
    }

    public static class VanillaIceCream implements IceCream {

        private final static String description = "Vanilla ice cream";
        private final static int price = 20;

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    public static class ChocolateIceCream implements IceCream {

        private final static String description = "Chocolate ice cream";
        private final static int price = 30;

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getPrice() {
            return (price);
        }
    }

    public static void main(String[] args) {
        Arrays.asList(new VanillaIceCream(),
                        new ChocolateIceCream(),
                        new ConfettiDecorator(new VanillaIceCream()),
                        new ConfettiDecorator(new ChocolateIceCream()))
                .forEach(i -> System.out.printf((i.getDescription().concat(" costs %d")) + "%n", i.getPrice()));
    }
}
