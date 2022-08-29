package com.example.projectboard.repository;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, //요거 하나만 넣어도 모든 검색기능은 끝난다고 보면됨
        //우리가 원하는 query를 만들기위해 ex : 부분검색
        QuerydslBinderCustomizer<QArticle> {

    //java 8 이후 interface 에서도 메서드 구현 가능
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true); //지정하지 않은 속성은 검색에서 제외된다.
        bindings.including(root.title , root.content, root.hashtag, root.createdAt, root.createdBy); //검색이 적용되는 속성들 설정
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //문자열 expression이고 부분검색과 대소문자를 구분하지 않도록
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}