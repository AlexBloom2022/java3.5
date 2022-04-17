package ProductRepository;

import Manager.ProductManager;
import ProductRepository.ProductRepository;
import exeptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager productManager = new ProductManager(repository);
    Product book1 = new Book(3, "The fate of man", 300, "Sholohov");
    Product book2 = new Book(1, "Anna Karenina", 110, "Tolstoy");
    Product phone1 = new Smartphone(9, "Galaxy", 60000, "Samsung");
    Product phone2 = new Smartphone(42, "Iphone", 10000, "Apple");

    @Test
    void removeByNonExistingIdInRepo() {
        repository.save(phone1);
        repository.save(phone2);

         Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(11));
    }

    @Test
    void removeByExistingIdInRepo() {
        repository.save(phone1);
        repository.save(phone2);
        repository.removeById(9);
        Product[] expected = {phone2};

        assertArrayEquals(expected, repository.findAll());
    }

}