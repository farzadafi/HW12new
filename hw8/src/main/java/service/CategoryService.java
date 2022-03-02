package service;

import entity.Category;
import repository.CategoryRepository;

import java.sql.SQLException;
import java.util.List;

public class CategoryService implements Service<Category> {
    private CategoryRepository categoryRepository = new CategoryRepository();


    @Override
    public int add(Category category) {
        try {
            return categoryRepository.add(category);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<Category> findAll() {
        try {
            return categoryRepository.findAll();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public int update(Category category) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    public List<Category> showCategory(int number){
        try {
            return categoryRepository.showCategory(number);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
