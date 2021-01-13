package com.example.fornecedor.repository;

import java.util.List;

import com.example.fornecedor.model.Produto;
import org.springframework.data.repository.CrudRepository;


public interface ProdutoRepository extends CrudRepository<Produto, Long>{

	List<Produto> findByEstado(String estado);
	
	List<Produto> findByIdIn(List<Long> ids);
}
