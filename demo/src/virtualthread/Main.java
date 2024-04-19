package virtualthread;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread thread = Thread.ofVirtual().start(() -> System.out.println("Hello"));
        thread.join();

        Thread.Builder builder = Thread.ofVirtual().name("MyThread");
        Runnable task = () -> {
            System.out.println("Running thread");
        };
        Thread t = builder.start(task);
        System.out.println("Thread t name: " + t.getName());
        t.join();


        System.out.format("Tasks started at %s", LocalDateTime.now());
        try (var executor = Executors.newVirtualThreadPerTaskExecutor())
        {
            IntStream.range(0, 10).forEach(i ->
            {
                long threadId;
                Future<Long> future = executor.submit(() ->
                {
                    Thread.sleep(Duration.ofSeconds(1));
                    return Thread.currentThread().threadId();
                });
                try {
                    threadId = future.get();
                    System.out.format("\nThread #%d completed at %s", threadId, LocalDateTime.now());
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }

            });
        }
        System.out.format("\nTasks completed at %s", LocalDateTime.now());
    }
}
