package com.vinisolon.fullstackcourse.repositories;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional(readOnly = true)
    @Query(
            "select distinct obj " +
                    "from Produto obj " +
                    "join obj.categorias cat " +
                    "where obj.nome like %:nome% " +
                    "and cat in :categorias"
    )
    Page<Produto> searchProdutosPaged(@Param("nome") String nome,
                                      @Param("categorias") List<Categoria> categorias,
                                      Pageable pageRequest);
//    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

}
