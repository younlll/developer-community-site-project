package project.developmentcomunity.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.repository.CategoryRepository;

import java.util.List;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;

    @Test
    void 카테고리별_질문목록_조회() {

        // given
        Category category = new Category();
        category.setCategoryId(1000);

        // when
        List<Question> qnas = categoryService.inqQuestionByCategory(category.getCategoryId());

        // then
        for(Question qna : qnas) {
            Assertions.assertThat(category.getCategoryId()).isEqualTo(qna.getCategoryId());
        }
    }
}