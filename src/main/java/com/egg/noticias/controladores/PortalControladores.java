package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticias;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.servicio.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControladores {

    @Autowired
    private NoticiaServicio noticiaServicio;

//    @GetMapping("/")
//    public String lista(ModelMap modelo) {
//
//        List<Noticias> todos = noticiaServicio.listarTodos();
//
//        modelo.addAttribute("noticias", todos);
//
//        return "index";
//    }

    @GetMapping("/cuerpo")
    public String cuerpo() {

        return "cuerpo.html";
    }

    @GetMapping("/")
    public String listar(ModelMap modelo) {

        try {
            List<Noticias> listNoticias = noticiaServicio.listarActivos();
            modelo.addAttribute("listNoticias", listNoticias);

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "admin.html";
        }
        return "index.html";
    }

//    @GetMapping("/cuerpo")
//	public String lista(ModelMap modelo) {
//		
//		List<Noticias> todos = noticiaServicio.listarTodos();
//		
//		modelo.addAttribute("noticias", todos);
//		
//		return "list-noticias";
}

//    @GetMapping("/baja/{id}")
//	public String baja(@PathVariable String id) {
//				
//		try {
//			noticiaServicio.baja(Integer.SIZE);
//			return "redirect:/perro/lista";
//		} catch (Exception e) {
//			return "redirect:/";
//		}
//	}
//	
//	@GetMapping("/alta/{id}")
//	public String alta(@PathVariable String id) {
//		
//		try {
//			noticiaServicio.alta(Integer.SIZE);
//			return "redirect:/perro/lista";
//		} catch (Exception e) {
//			return "redirect:/";
//		}
//	}    


//}
