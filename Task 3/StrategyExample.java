public class StrategyExample {
    public static class Document{

    }
    public interface PrintingStrategy{
        void printing(Document document);
    }

    public static class ColorPrintingStrategy implements PrintingStrategy{
        @Override
        public void printing(Document document) {
            System.out.println("Color printing!!!");
        }
    }

    public static class InvertedColorPrintingStrategy implements PrintingStrategy{
        @Override
        public void printing(Document document) {
            System.out.println("Inverted printing!!!");
        }
    }

    public static class MonochromeColorPrintingStrategy implements PrintingStrategy{
        @Override
        public void printing(Document document) {
            System.out.println("Monochrome printing!!!");
        }
    }

    public static class Printer{
        private PrintingStrategy strategy;

        public Printer setStrategy(PrintingStrategy strategy){
            this.strategy = strategy;
            return this;
        }
        public void print(Document document){
            this.strategy.printing(document);
        }
    }

    public static void main(String[] args) {
        new Printer().setStrategy(new ColorPrintingStrategy()).print(new Document());
        new Printer().setStrategy(new InvertedColorPrintingStrategy()).print(new Document());
        new Printer().setStrategy(new MonochromeColorPrintingStrategy()).print(new Document());
    }
}
