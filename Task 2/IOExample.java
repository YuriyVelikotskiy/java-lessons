import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class IOExample {
    public static void main(String[] args) {
        String inPutExample = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        String path = "source\\testFile.txt";
        MyFileIOExample file = new MyFileIOExample(path);

        try {
            file.writeToFile(inPutExample);
            System.out.println(file.readFromFile());
        } catch (MyExceptionExample e){
            System.out.println(e.getMessage());
        }
    }
    public static class MyExceptionExample extends RuntimeException{
        public MyExceptionExample(Throwable exception, String massage){
            super(massage, exception);
        }
    }

    static class MyFileIOExample {
        private final Path filePath;

        public MyFileIOExample(String filePath){
            this.filePath = Path.of(filePath);
        }

        public void writeToFile(String string){
            try {
                Files.writeString(filePath, string, StandardOpenOption.CREATE);
            } catch (IOException e){
                throw new MyExceptionExample(e, "Ошибка записи!");
            }
        }

        public List<String> readFromFile(){
            try {
                return Files.readAllLines(filePath);
            } catch (NoSuchFileException e){
                throw new MyExceptionExample(e, "Такого файла нет!");
            }
            catch (IOException e) {
                throw new MyExceptionExample(e, "Ошибка чтения!");
            }
        }
    }
}
