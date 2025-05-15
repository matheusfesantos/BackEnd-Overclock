package com.example.overclockAPI.services.endpoints;

import com.example.overclockAPI.entitys.Usuarios;
import com.example.overclockAPI.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuarios> listarTodos(){
        return usuarioRepository.findAll();
    }

   public ResponseEntity<Usuarios> buscarPorId(Long id){
        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
   }

   public boolean validarId(Long id){
       boolean exist = usuarioRepository.existsById(id);

       if(exist){
           return true;
       }
       else{
           return false;
       }
   }

    public Usuarios salvarUsuario(Usuarios usuarios){
        return usuarioRepository.save(usuarios);
    }

    public boolean deletarUsuario(Long id) {
        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
            return true;
        }
        return false;
    }
}
