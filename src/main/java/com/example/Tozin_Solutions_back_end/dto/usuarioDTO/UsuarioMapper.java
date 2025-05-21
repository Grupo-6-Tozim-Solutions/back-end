package com.example.Tozin_Solutions_back_end.dto.usuarioDTO;

import com.example.Tozin_Solutions_back_end.model.Usuario;

public class UsuarioMapper {

    public static Usuario of(CadastrarUsuarioDTO cadastrarUsuario){
        Usuario usuario = new Usuario();

        usuario.setNome(cadastrarUsuario.getNome());
        usuario.setEmail(cadastrarUsuario.getEmail());
        usuario.setSenha(cadastrarUsuario.getSenha());


        return usuario;
    }

    public static Usuario of(LoginUsuarioDTO loginUsuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setEmail(loginUsuarioDTO.getEmail());
        usuario.setSenha(loginUsuarioDTO.getSenha());

        return usuario;
    }

    public static UsuarioTokenDTO of(Usuario usuario, String token){
        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();

        usuarioTokenDTO.setId(usuario.getId());
        usuarioTokenDTO.setEmail(usuario.getEmail());
        usuarioTokenDTO.setNome(usuario.getNome());
        usuarioTokenDTO.setToken(token);

        return usuarioTokenDTO;
    }

    //Mudar essa parte
    public static DadosUsuarioDTO of(Usuario usuario){
        DadosUsuarioDTO dadosUsuarioDTO = new DadosUsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());

        return dadosUsuarioDTO;
    }
}
