package Manager;

import ProductRepository.ProductRepository;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void addItem(Product item) {
        repository.save(item);
    }

    public Product[] getAll() {
        return repository.findAll();
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product item :
                repository.findAll()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;

                result = tmp;

            }
        }
        return result;
    }


    public boolean matches(Product item, String search) {
        if (item instanceof Book) {
            Book book = (Book) item;
            if (book.getAuthor().contains(search)) {
                return true;
            }
            if (book.getName().contains(search)) {
                return true;
            }

        }

        //   return false;

        if (item instanceof Smartphone) {
            Smartphone phone = (Smartphone) item;
            if (phone.getModel().contains(search)) {
                return true;
            }
            if (phone.getName().contains(search)) {
                return true;


            }
            return false;
        }
        return false;
    }
}
