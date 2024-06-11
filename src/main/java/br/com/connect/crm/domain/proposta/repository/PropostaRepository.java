package br.com.connect.crm.domain.proposta.repository;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    /*@Query("""
        select p.id, p.numero, p.descricao, p.data, p.valor, p.status, e.nome, e.tipo
        from propostas p inner join entidades e on e.id = p.entidade_id
            """)
    Page<DadosDetalheProposta> findAllPropostas(Pageable paginacao);*/

}
