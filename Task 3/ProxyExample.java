public class ProxyExample {
    public interface Cell {
        void displayCell();
    }

    public static class RealCell implements Cell {
        private String content;

        public RealCell(String content) {
            this.content = content;
        }

        @Override
        public void displayCell() {
            System.out.printf("The original content '%s'%n", content);
        }
    }

    public static class ProxyCell implements Cell {
        private String content;
        private RealCell realCell;

        public ProxyCell(String content) {
            this.content = content;
        }

        public RealCell getRealCell() {
            return realCell;
        }

        @Override
        public void displayCell() {
            if (realCell == null) {
                realCell = new RealCell(content);
            }
            realCell.displayCell();
        }
    }

    public static void main(String[] args) {
        Cell realCell = new RealCell("!!!Message!!!");
        realCell.displayCell();

        Cell cell = new ProxyCell("Message");
        ProxyCell proxyCell = (ProxyCell) cell;

        if (proxyCell.getRealCell() == null) {
            System.out.println("Real cell is null");
        }
        System.out.print("Proxy cell: ");
        cell.displayCell();
        System.out.print("Real cell: ");
        proxyCell.getRealCell().displayCell();
    }
}
