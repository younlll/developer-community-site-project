package project.developmentcomunity.repository;

import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;

import java.util.List;

public interface CategoryRepository {

    List<Category> inqCategoryList();
    List<Question> inqQuestionList(long categoryId);
}
