package br.com.calvinribeiro.consultamedica.usuario.repositories;

import br.com.calvinribeiro.consultamedica.usuario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
