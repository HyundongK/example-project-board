package com.example.projectboard.repository;

import com.example.projectboard.config.JpaConfig;
import com.example.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

import java.util.List;


@Import(JpaConfig.class)
@DataJpaTest
@DisplayName("JPA 연결 테스트")
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;

    }


    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        //Given
        long previousCount = articleRepository.count();
        Article article= Article.of("new article", "new article", "#spring");
        articleRepository.save(article);

        //When
        List<Article> articles=articleRepository.findAll();

        //Then
        assertThat(articles)
                .isNotNull()
                .hasSize((int)previousCount+1);

    }

    @Test
    void update_Test(){
        Article article=articleRepository.findById(1L).orElseThrow();
        String updatedHashtag="#spring";
        article.setHashtag(updatedHashtag);

        Article savedArticle=articleRepository.save(article);
        assertThat(savedArticle)
                .hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @Test
    void delete_Test(){
        //given
        Article article=articleRepository.findById(1L).orElseThrow();
        long previousArticleCount=articleRepository.count();
        long previousArticleCommentCount=articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleCommentSet().size();

        //when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentsSize);
    }
}