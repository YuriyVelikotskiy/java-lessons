public class AdapterExample {

    interface MetricSpeedometer{
        String showSpeed(int speed);
    }

    public static class BritishSpeedometer{
        public String showSpeedBrit(int speed){
            return "%d ".formatted(speed);
        }
    }

    public static class BritishToMetricSpeedometerAdapter extends BritishSpeedometer implements MetricSpeedometer{

        @Override
        public String showSpeed(int speed){
            return showSpeedBrit((int) (speed * 1.6));
        }
    }

    public static void main(String[] args) {
        System.out.println(new BritishSpeedometer().showSpeedBrit(20).concat("kph"));
        System.out.println(new BritishToMetricSpeedometerAdapter().showSpeed(20).concat("mph"));
    }
}
