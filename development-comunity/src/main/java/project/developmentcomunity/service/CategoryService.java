package project.developmentcomunity.service;

import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * 카테고리 목록 가져오기
     */
    public List<Category> inqAllCategoryList() {
        return categoryRepository.inqCategoryList();
    }

    /**
     * 카테고리별 세부 목록(질문 목록) 가져오기
     */
    public List<Question> inqQuestionByCategory(long categoryId) {
        return categoryRepository.inqQuestionList(categoryId);
    }
}
