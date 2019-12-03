import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exemples usats en el test(Valor->Prioritat):
 * Director -> 10L
 * CTO -> 8L
 * Empleat -> 5L
 * ClientVIP -> 3L
 * Client1 -> 1L
 * Client2 -> 1L
 */
class HeapQueueTest {
    HeapQueue<String, Long> heapQueue;

    @Test
    void testElementEmptyHeap(){
        heapQueue = new HeapQueue<>();
        assertThrows(NoSuchElementException.class ,() -> {heapQueue.element();});
    }

    @Test
    void testRemoveEmptyHeap(){
        heapQueue = new HeapQueue<>();
        assertThrows(NoSuchElementException.class ,() -> {heapQueue.remove();});
    }

    @Test
    void testElement(){
        heapQueue = new HeapQueue<>();

        heapQueue.add("Client1", 1L);
        assertEquals(heapQueue.element(), "Client1");

        heapQueue.add("Client2", 1L);
        heapQueue.add("Empleat", 5L);
        heapQueue.add("Director", 10L);
        heapQueue.add("ClientVIP", 3L);
        heapQueue.add("CTO", 8L);
        assertEquals(heapQueue.element(), "Director");
    }

    @Test
    void testAdd(){
        heapQueue = new HeapQueue<>();

        heapQueue.add("Empleat", 5L);
        assertEquals(heapQueue.element(), "Empleat");

        heapQueue.add("Client1", 1L);
        assertEquals(heapQueue.element(), "Empleat");

        heapQueue.add("Director", 10L);
        assertEquals(heapQueue.element(), "Director");

        heapQueue.add("ClientVIP", 3L);
        assertEquals(heapQueue.element(), "Director");

        heapQueue.add("CTO", 8L);
        assertEquals(heapQueue.element(), "Director");
    }

    @Test
    void testRemove(){
        heapQueue = new HeapQueue<>();

        heapQueue.add("Client1", 1L);
        heapQueue.add("Client2", 1L);
        heapQueue.add("Empleat", 5L);
        heapQueue.add("Director", 10L);
        heapQueue.add("ClientVIP", 3L);
        heapQueue.add("CTO", 8L);

        assertEquals(heapQueue.remove(), "Director");

        assertEquals(heapQueue.remove(), "CTO");

        assertEquals(heapQueue.remove(), "Empleat");

        assertEquals(heapQueue.remove(), "ClientVIP");

        assertEquals(heapQueue.remove(), "Client1");

        assertEquals(heapQueue.remove(), "Client2");
    }

    @Test
    void testSize(){
        heapQueue = new HeapQueue<>();

        assertEquals(heapQueue.size(), 0);

        heapQueue.add("Client1", 1L);
        heapQueue.add("Client2", 1L);
        heapQueue.add("Empleat", 5L);
        heapQueue.add("Director", 10L);
        heapQueue.add("ClientVIP", 3L);
        heapQueue.add("CTO", 8L);
        assertEquals(heapQueue.size(), 6);
    }

    @Test
    void testSamePriority(){
        heapQueue = new HeapQueue<>();

        heapQueue.add("Client1", 1L);
        heapQueue.add("Client2", 1L);
        heapQueue.add("Client3", 1L);
        assertEquals(heapQueue.remove(), "Client1");

        assertEquals(heapQueue.element(), "Client2");
    }

    @Test
    void testNullPriority(){
        heapQueue = new HeapQueue<>();

        heapQueue.add("Client1", null);
        heapQueue.add("Empleat", 5L);
        heapQueue.add("Director", 10L);
        heapQueue.add("CTO", null);
        assertEquals(heapQueue.remove(), "Director");

        assertEquals(heapQueue.remove(), "Empleat");

        assertEquals(heapQueue.element(), "Client1");
    }



}