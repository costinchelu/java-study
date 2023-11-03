package completable_future;

import completable_future.model.ExchangeService;
import completable_future.model.Shop;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static completable_future.model.ExchangeService.Money;


public class ComparativeOperations {

    private static final BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors() + ". Stores to process: " + bestPriceFinder.getShops().size());
        System.out.println("==================================================");
        execute("sequential stream", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel stream", () -> bestPriceFinder.findPricesParallelStream("myPhone27S"));
        execute("futures", () -> bestPriceFinder.findPricesJava7("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesCompletableFuture("myPhone27S"));
        System.out.println("==================================================");
        execute("combined USD Future", () -> bestPriceFinder.findPricesInUSDJava7("myPhone27S"));
        execute("combined USD CompletableFuture", () -> bestPriceFinder.findPricesInUSD("myPhone27S"));
        execute("combined USD CompletableFuture v2", () -> bestPriceFinder.findPricesInUSD2("myPhone27S"));
        execute("combined USD CompletableFuture v3", () -> bestPriceFinder.findPricesInUSD3("myPhone27S"));
    }

    private static void execute(String msg, Supplier<List<String>> supplier) {
        long start = System.nanoTime();
        supplier.get();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}

class BestPriceFinder {

    @Getter
    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopCom"),
            new Shop("BestEleven"),
            new Shop("ShopToo"),
            new Shop("BigMag"),
            new Shop("TenPam"),
            new Shop("Shop2Easy"),
            new Shop("SkyShop"),
            new Shop("AmazingShop"),
            new Shop("RShop"),
            new Shop("MarketMag"),
            new Shop("StoreUp"),
            new Shop("SavingStore"),
            new Shop("ShopMore")
    );
    private final int threadPoolSizeLimit = Math.min(shops.size(), 100);

    private final Executor executor =
            Executors.newFixedThreadPool(
                    threadPoolSizeLimit,
                    new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            // A Java program can’t terminate or exit while a normal thread is executing, so a leftover thread
            //  waiting for a never-satisfiable event causes problems.
            // By contrast, marking a thread as a daemon means it can be killed on program termination.
            // There’s no performance difference.
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallelStream(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<String> process = executor.submit(new Callable<String>() {
                public String call() {
                    return shop.getName() + " price is " + shop.getPrice(product);
                }
            });
            priceFutures.add(process);
        }
        List<String> prices = new ArrayList<>();
        for (Future<String> priceFuture : priceFutures) {
            try {
                if (priceFuture.isDone()) {
                    prices.add(priceFuture.get());
                }
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }

    public List<String> findPricesCompletableFuture(String product) {
        /*
        List<CompletableFuture<String>>
            >> each CompletableFuture in the List will contain
            >> String name of a shop when its computation is completed.
       Because the findPrices has to return just a List<String>, we'll have to wait for the completion of all these futures
       and extract the value they contain before returning the List.

       processing the stream with a single pipeline implies the evaluation order is sequential.
       In fact, a new CompletableFuture is created only after the former one has been completely evaluated.
       Conversely, gathering the CompletableFutures in a list first, allows all of them to start before waiting for their completion.
         */
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " + shop.getPrice(product),
                                executor))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                public Double call() {
                    return ExchangeService.getExchangeRate(Money.EUR, Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                public Double call() {
                    try {
                        return  shop.getPrice(product) * futureRate.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(/*shop.getName() +*/ " price is " + priceFuture.get());
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }

    public List<String> findPricesInUSD(String product) {
        // Start of Listing 10.20.
        // Only the type of futurePriceInUSD has been changed to CompletableFuture so that it is compatible with the CompletableFuture::join operation below.
        // Drawback: The shop is not accessible anymore outside the loop, so the getName() call below has been commented out.
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                            .thenCombine(
                                    CompletableFuture.supplyAsync(
                                            () ->  ExchangeService.getExchangeRate(Money.EUR, Money.USD)),
                                            (price, rate) -> price * rate
                            );
            priceFutures.add(futurePriceInUSD);
        }
        return priceFutures
                .stream()
                .map(CompletableFuture::join)
                .map(price -> /*shop.getName() +*/ " price is " + price)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD2(String product) {
        // Here, an extra operation has been added so that the shop name is retrieved within the loop.
        // As a result, we now deal with CompletableFuture<String> instances.
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            CompletableFuture<String> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                            .thenCombine(
                                    CompletableFuture.supplyAsync(
                                            () -> ExchangeService.getExchangeRate(Money.EUR, Money.USD)),
                                            (price, rate) -> price * rate
                            )
                            .thenApply(price -> shop.getName() + " price is " + price);
            priceFutures.add(futurePriceInUSD);
        }
        return priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD3(String product) {
        // Here, the for loop has been replaced by a mapping function...
        // However, we should gather the CompletableFutures into a List so that the asynchronous operations are triggered before being "joined."
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                        .thenCombine(
                                CompletableFuture.supplyAsync(
                                        () -> ExchangeService.getExchangeRate(Money.EUR, Money.USD)),
                                        (price, rate) -> price * rate
                        )
                        .thenApply(price -> shop.getName() + " price is " + price))
                .collect(Collectors.toList());

        return priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}