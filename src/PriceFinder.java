import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class PriceFinder {
    public static void main(String[] args) throws Exception {
        var start=LocalTime.now();
        var service=new FlightService();
        var futures=
        service.getQuotes().map(future->future.thenAccept(System.out::println))
        .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
        .thenRun(()->{
            var end=LocalTime.now();
            var duration=Duration.between(start, end);
            System.out.println("Retrieved all quotes in "+ duration.toMillis()+" msec.");
        });
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

    }
}
