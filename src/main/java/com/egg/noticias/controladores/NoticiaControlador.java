package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticias;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.servicio.NoticiaServicio;
import com.sun.istack.logging.Logger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/crear")
    public String crear() {
        return "crearNoticia.html";
    }

    @PostMapping("/crearNoti")
    public String guardar(ModelMap modelo, @RequestParam String titulo, @RequestParam String cuerpo, @RequestParam String foto) {

        try {
            noticiaServicio.crearNoticia(titulo, cuerpo, foto);
            modelo.put("exito", "La noticia fue cargada correctamente");

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "crearNoticia";
        }
        return "crearNoticia";
    }

    @GetMapping("/admin")
    public String listar(ModelMap modelo) {

        try {
            List<Noticias> listNoticias = noticiaServicio.listarActivos();
            modelo.addAttribute("listNoticias", listNoticias);

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
        return "admin.html";
    }

     @GetMapping("/editar")
    public String editar() {
        return "editar.html";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, ModelMap modelo) {
       
        Noticias noticia= noticiaServicio.obtenerNoticiaId(id);
        
        modelo.put("noticia", noticiaServicio.getOne(id));

        return "editar";
    }

//    @PostMapping("/editar/{id}")
//    public String editar1(@PathVariable Integer id, String titulo, String cuerpo, String foto, ModelMap modelo) {
//        try {
//            noticiaServicio.modificarNoticia(id, titulo, cuerpo, foto);
//
//            return "redirect:../admin";
//
//        } catch (MiException ex) {
//            modelo.put("error", ex.getMessage());
//            return "index.html";
//        }
//
//    }
}
