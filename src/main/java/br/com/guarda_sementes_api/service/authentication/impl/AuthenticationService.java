package br.com.guarda_sementes_api.service.authentication.impl;

import br.com.guarda_sementes_api.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usuTxLogin) throws UsernameNotFoundException {
        return this.usuarioRepository.findByUsuTxLogin(usuTxLogin);
    }
}
