package br.com.connect.crm.domain.proposta.repository;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    /*@Query("""
            select p.id, p.numero, p.descricao, p.data, p.valor, e.nome entidade, TipoProposta tipo, StatusProposta status
            from Proposta p inner join Entidade e on p.entidade = e.id
            """)
    List<DadosDetalheProposta> findPropostas();*/

}
