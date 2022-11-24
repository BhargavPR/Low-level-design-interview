package Cache;


import Cache.action.Action;
import Cache.database.CacheDatabase;
import Cache.service.CacheService;
import Cache.strategy.CacheStrategy;
import Cache.strategy.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CacheApplication {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/20033132/machine coding/machine coding/src/Cache/input.txt"));
        CacheDatabase cacheDatabase = CacheDatabase.getInstance();
        Context context = new Context();

        CacheService cacheService = new CacheService(cacheDatabase, context.getCacheStrategy(CacheStrategy.LRU));

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            Action action = Action.valueOf(input[0]);
            switch (action) {
                case CREATE_CACHE:
                    cacheDatabase.insertCache(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                    break;
                case READ:
                    cacheService.readCache(input[1]);
                    break;
                case WRITE:
                    cacheService.writeCache(input[1], input[2]);
                    break;
                case PRINT:
                    cacheService.printCache();
                    break;
            }
        }
    }
}
