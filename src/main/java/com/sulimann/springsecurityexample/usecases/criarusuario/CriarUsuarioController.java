package com.sulimann.springsecurityexample.usecases.criarusuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulimann.springsecurityexample.models.Usuario;
import com.sulimann.springsecurityexample.repositories.UsuarioRepository;
import com.sulimann.springsecurityexample.utils.constants.Path;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = Path.USUARIO)
public class CriarUsuarioController {

  private final UsuarioRepository repository;

  public CriarUsuarioController(UsuarioRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public ResponseEntity<CriarUsuarioResponse> criarUsuario(@RequestBody @Valid CriarUsuarioRequest request){
    Usuario usuario = request.toModel();
    this.repository.save(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(new CriarUsuarioResponse(usuario));
  }

}
