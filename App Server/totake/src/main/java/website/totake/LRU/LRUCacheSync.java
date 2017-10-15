package website.totake.LRU;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LRUCacheSync<K, V> {
    LRUCache<K, V> cache;
    ExecutorService executor;

    public LRUCacheSync(int capacity) {
        cache = new LRUCache<>(capacity);
        executor = Executors.newSingleThreadExecutor();
    }

    public void put(K key, V value){
        executor.submit(() -> cache.put(key, value));
    }

    public Future<Integer> size() {
        return executor.submit(() -> cache.size());
    }

    public Future<V> get(K key){
        return executor.submit(() -> cache.get(key));
    }

    public void evict(K key){
        executor.submit(() -> cache.evict(key));
    }

    public Future<Boolean> contains(K key) {
        return executor.submit(() -> {
           if (cache.get(key) != null) {
               return true;
           }
           return false;
        });
    }

}
