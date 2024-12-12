package br.com.guarda_sementes_api.service.usuario.impl;

import br.com.guarda_sementes_api.model.usuario.PerfilUsuarioRelacionamento;
import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import br.com.guarda_sementes_api.repository.usuario.PerfilRepository;
import br.com.guarda_sementes_api.repository.usuario.PerfilUsuarioRelacionamentoRepository;
import br.com.guarda_sementes_api.repository.usuario.UsuarioRepository;
import br.com.guarda_sementes_api.service.usuario.dto.UsuarioDto;
import br.com.guarda_sementes_api.service.usuario.form.UsuarioForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilUsuarioRelacionamentoRepository perfilUsuarioRelacionamentoRepository;

    @Override
    public UserDetails loadUserByUsername(String usuTxLogin) throws UsernameNotFoundException {
        return this.usuarioRepository.findByUsuTxLogin(usuTxLogin);
    }

    public UsuarioDto cadastrarOuAtualizarUsuario(UUID usuNrId, UsuarioForm form) {
        var usuario = usuNrId != null ?
                this.usuarioRepository.buscarUsuarioPorId(usuNrId)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado")): new UsuarioEntidade();

        var usu = this.usuarioRepository.findByUsuTxLogin(form.usuTxLogin());

        if (usu != null) throw new RuntimeException("Este login já está em uso, por favor, informe um novo login");

        usuario.setUsuTxNome(form.usuTxNome());
        usuario.setUsuTxLogin(form.usuTxLogin());

        String senhaCriptografada = new BCryptPasswordEncoder().encode(form.usuTxSenha());
        usuario.setUsuTxSenha(senhaCriptografada);

        this.usuarioRepository.save(usuario);

        this.associarUsuarioAoPerfil(usuario.getUsuNrId(), 3L);

        return new UsuarioDto(usuario);
    }

    public void associarUsuarioAoPerfil (UUID usuNrId, Long perNrId) {
        PerfilUsuarioRelacionamento perfilUsuarioRelacionamento = new PerfilUsuarioRelacionamento();
        perfilUsuarioRelacionamento.setUsuNrId(usuNrId);
        perfilUsuarioRelacionamento.setPerNrId(perNrId);

        this.perfilUsuarioRelacionamentoRepository.save(perfilUsuarioRelacionamento);
    }
}
