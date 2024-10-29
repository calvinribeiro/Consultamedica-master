package br.com.calvinribeiro.consultamedica.consulta.repositories;


import br.com.calvinribeiro.consultamedica.consulta.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
