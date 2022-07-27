package com.egg.noticias.servicio;

import com.egg.noticias.entidades.Noticias;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.repositorio.NoticiasRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiasRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo, String foto) throws MiException {
        
        validar(titulo, cuerpo, foto);
        
        Noticias noticia = new Noticias();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFoto(foto);
        noticia.setAlta(new Date());
        noticia.setActivo(true);
        
        noticiaRepositorio.save(noticia);
    }
    
    @Transactional
    public void modificarNoticia(Integer id, String titulo, String cuerpo, String foto) throws MiException {
        
        validar(titulo, cuerpo, foto);
        
        Optional<Noticias> respuesta = noticiaRepositorio.findById(Integer.SIZE);
        
        if (respuesta.isPresent()) {
            
            Noticias noticias = respuesta.get();
            
            noticias.setTitulo(titulo);
            noticias.setCuerpo(cuerpo);
            noticias.setFoto(foto);
            noticias.setActivo(true);
        }
    }
    
    @Transactional
    public Noticias alta(Integer id) {
        
        Noticias entidad = noticiaRepositorio.getOne(id);
        
        entidad.setActivo(true);
        return noticiaRepositorio.save(entidad);
    }
    
    @Transactional
    public Noticias baja(Integer id) {
        
        Noticias entidad = noticiaRepositorio.getOne(Integer.SIZE);
        
        entidad.setActivo(false);
        return noticiaRepositorio.save(entidad);
    }
    
    public void eliminar(Integer id) throws Exception {
		Noticias entidad = noticiaRepositorio.getOne(id);
		noticiaRepositorio.delete(entidad);
	}
    
//    @Transactional
//    public void eliminar(Integer id){
//        Noticias noticia= buscarNoticias(id);
//        noticia.setActivo(false);
//        noticiaRepositorio.save(noticia);
//    }
    
    @Transactional(readOnly = true)
    public List<Noticias> listarActivos() throws MiException {
        List<Noticias> lista = noticiaRepositorio.buscarActivos();
        
        return lista;
    }
    
    @Transactional(readOnly = true)
    public List<Noticias> listarTodos() throws MiException {
        List<Noticias> lista = noticiaRepositorio.buscarTodos();
        return lista;
    }
    
    public Noticias getOne(Integer id){
        return noticiaRepositorio.getOne(id);
    }
    
    private void validar(String titulo, String cuerpo, String foto) throws MiException {
        
        if (titulo == null) {
            throw new MiException("El titulo no puede ser nulo");
        }
        
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo no puede ser nulo");
        }
        
        if (foto.isEmpty() || foto == null) {
            throw new MiException("La foto no puede ser nulo");
        }
    }

    public Noticias obtenerNoticiaId(Integer id) {
        
        Noticias noticia= noticiaRepositorio.buscarNoticias(id);
        
        return noticia;
    }
}
