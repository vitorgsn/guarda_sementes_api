package br.com.guarda_sementes_api.service;

import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    public @NonNull UsuarioEntidade getUsuarioAutenticado() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UsuarioEntidade usuario) {
            return usuario;
        }
        throw new AccessDeniedException("Nenhum usuário autenticado");
    }
}
