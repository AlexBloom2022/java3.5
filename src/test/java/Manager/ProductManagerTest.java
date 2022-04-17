package Manager;

import ProductRepository.ProductRepository;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager productManager = new ProductManager(repository);
    Product book1 = new Book(3, "The fate of man", 300, "Sholohov");
    Product book2 = new Book(1, "Anna Karenina", 110, "Tolstoy");
    Product book3 = new Book(5, "Karamazovs", 220, "Dostoevsky");
    Product book4 = new Book(3, "The fate of man", 300, "Sholohov");
    Product phone1 = new Smartphone(9, "Galaxy", 60000, "Samsung");
    Product phone2 = new Smartphone(42, "Iphone", 10000, "Apple");


    @Test
    void searchByEmptyTitle() {

        Product[] expected = {};
        Product[] actual = productManager.searchBy(null);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNonExistingProduct() {
        productManager.addItem(book4);
        productManager.addItem(book2);

        Product[] expected = {};
        Product[] actual = productManager.searchBy("Yasna");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByTwinProduct() {
        productManager.addItem(phone1);
        productManager.addItem(book1);
        productManager.addItem(book4);


        Product[] expected = {book1, book4};
        Product[] actual = productManager.searchBy("The fate of man");

        assertArrayEquals(expected, actual);
    }


    @Test
    void searchByTitle() {
        productManager.addItem(phone2);
        productManager.addItem(book3);


        Product[] expected = {book3};
        Product[] actual = productManager.searchBy("Karamazovs");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByEmpty() {

        Product[] expected = {};
        Product[] actual = productManager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByDoubleAddedItem() {
        productManager.addItem(book1);
        productManager.addItem(book1);
        Product[] expected = {book1, book1};
        Product[] actual = productManager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByModel() {
        productManager.addItem(phone1);
        productManager.addItem(book2);


        Product[] expected = {phone1};
        Product[] actual = productManager.searchBy("Samsung");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByName() {
        productManager.addItem(phone2);
        productManager.addItem(phone1);


        Product[] expected = {phone2};
        Product[] actual = productManager.searchBy("Iphone");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        productManager.addItem(book2);
        productManager.addItem(book3);
        productManager.addItem(phone1);
        Product[] expected = {book3};
        Product[] actual = productManager.searchBy("Dostoevsky");

        assertArrayEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        repository.save(book1);

        Product[] expected = {book1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    void removeByIdTest() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.removeById(5);

        Product[] expected = {book1, book2, book4};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = {phone1};
        Product[] actual = {repository.findById(9)};

        assertArrayEquals(expected, actual);
    }

    @Test
    void findByIdNullTest() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = {null};
        Product[] actual = {repository.findById(54)};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdManagerTest() {
        productManager.addItem(book2);
        productManager.removeById(1);

        Product[] expected = {};
        Product[] actual = productManager.getAll();

        assertArrayEquals(expected, actual);
    }

}