public class BuilderExample {

    public static class Car{
        private final String chassis;
        private final String engine;
        private final String body;
        private final String interior;
        private final String exterior;

        public Car(String chassis, String engine, String body, String interior, String exterior) {
            this.chassis = chassis;
            this.engine = engine;
            this.body = body;
            this.interior = interior;
            this.exterior = exterior;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "chassis='" + chassis + '\'' +
                    ", engine='" + engine + '\'' +
                    ", body='" + body + '\'' +
                    ", interior='" + interior + '\'' +
                    ", exterior='" + exterior + '\'' +
                    '}';
        }
    }

    public interface CarBuilder{
        CarBuilder buildChassis();
        CarBuilder buildEngine();
        CarBuilder buildBody();
        CarBuilder buildInterior();
        CarBuilder buildExterior();
        Car build();
    }

    public static class PorscheCarBuilder implements CarBuilder{
        private String chassis;
        private String engine;
        private String body;
        private String interior;
        private String exterior;
        @Override
        public CarBuilder buildChassis() {
            System.out.println("Assembling the chassis for Porsche!!!");
            this.chassis = "Chassis for Porsche";
            return this;
        }

        @Override
        public CarBuilder buildEngine() {
            System.out.println("Assembling the engine for Porsche!!!");
            this.engine = "Engine for Porsche";
            return this;
        }

        @Override
        public CarBuilder buildBody() {
            System.out.println("Assembling the body for Porsche!!!");
            this.body = "Body for Porsche";
            return this;
        }

        @Override
        public CarBuilder buildInterior() {
            System.out.println("Assembling the interior for Porsche!!!");
            this.interior = "Interior for Porsche";
            return this;
        }

        @Override
        public CarBuilder buildExterior() {
            System.out.println("Assembling the exterior for Porsche!!!");
            this.exterior = "Exterior for Porsche";
            return this;
        }

        @Override
        public Car build() {
            Car car = new Car(chassis, engine, body, interior,exterior);
            System.out.println("The Porsche assembly is finished!!!");
            return car;
        }
    }

    public static class BuildDirector{
        private final CarBuilder builder;

        public BuildDirector(CarBuilder builder){
            this.builder = builder;
        }
        public Car carAssembly(){
            return builder.buildChassis().buildEngine().buildBody().buildInterior().buildExterior().build();
        }
    }
    public static void main(String[] args) {
        CarBuilder carBuilder = new PorscheCarBuilder();
        BuildDirector buildDirector = new BuildDirector(carBuilder);
        Car porsche = buildDirector.carAssembly();
        System.out.println(porsche);
    }
}
