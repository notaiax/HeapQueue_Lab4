import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapQueue<V, P extends Comparable<? super P>> implements PriorityQueue<V, P>{
    private final ArrayList<TSPair<V, P>> pairs = new ArrayList<>();
    private long nextTimeStamp = 0L;

    public static class TSPair<V, P extends Comparable<? super P>> implements Comparable<TSPair<V, P>>{
        private final V value;
        private final P priority;
        private final long timeStamp;

        public TSPair(V value, P priority, long timeStamp){
            this.value = value;
            this.priority = priority;
            this.timeStamp = timeStamp;
        }

        @Override
        public int compareTo(TSPair<V,P> other){
            if(other.priority == null && this.priority != null){
                return +1;
            }else if(other.priority != null && this.priority == null){
                return -1;
            }else if(other.priority == null && this.priority == null){
                return Long.compare(other.timeStamp, this.timeStamp );
            }

            if(this.priority.compareTo(other.priority) != 0){
                return this.priority.compareTo(other.priority);
            }else{
                return Long.compare(other.timeStamp, this.timeStamp );

            }
        }
    }

    static int parent(int index){
        return (index - 1) / 2;
    }
    static int left(int index){
        return (index * 2) + 1;
    }
    static int right(int index){
        return (index * 2) + 2;
    }

    private boolean isValid(int index) { return 0 <= index && index < size(); }
    private boolean hasParent(int index) { return index > 0; }
    private boolean hasLeft(int index) { return isValid(left(index)); }
    private boolean hasRight(int index) { return isValid(right(index)); }

    @Override
    public void add(V value, P priority){
        pairs.add(new TSPair<>(value, priority, this.nextTimeStamp));
        this.nextTimeStamp++;
        checkPriority();
    }

    private void checkPriority(){
        int newPairIndex = pairs.size() - 1;

        while(hasParent(newPairIndex) && compareByIndex(newPairIndex, parent(newPairIndex)) > 0){
            swap(newPairIndex, parent(newPairIndex));
            newPairIndex = parent(newPairIndex);
        }
    }

    private int compareByIndex(int index1, int index2){
        return pairs.get(index1).compareTo(pairs.get(index2));
    }

    private void swap(int index1, int index2){
        TSPair<V,P> temp = pairs.get(index1);
        pairs.set(index1, pairs.get(index2));
        pairs.set(index2, temp);
    }

    @Override
    public V remove(){
        V maxValue = this.element();

        swap(0, pairs.size() - 1);
        pairs.remove(pairs.size() - 1);
        checkItsMaxHeap();

        return maxValue;
    }

    private void checkItsMaxHeap(){
        int index = 0;
        int maxChildIndex = getMaxChild(index);

        while(hasLeft(index) && compareByIndex(index, maxChildIndex) < 0){
            swap(index, maxChildIndex);
            index = maxChildIndex;
            maxChildIndex = getMaxChild(index);
        }
    }

    private int getMaxChild(int index){
        if (hasLeft(index) && hasRight(index) && compareByIndex(left(index), right(index)) < 0){
                return right(index);
        }
        return left(index);
    }

    @Override
    public V element(){
        if (pairs.size() == 0){
            throw new NoSuchElementException();
        }
        return pairs.get(0).value;
    }

    @Override
    public int size(){
        return pairs.size();
    }
}
