package com.rgarmal.exament2.controllers;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import com.rgarmal.exament2.model.LoginUsuario;
import com.rgarmal.exament2.model.Noticia;

import java.util.*;  

@Controller
@RequestMapping("noticias")
public class NoticiaController {

    @RequestMapping(value = "/listaNoticias")
    public ModelAndView list(HttpSession session,LoginUsuario usuario,Model model) {
        if (usuario!=null) {
            session.setAttribute("usuario", usuario);
            model.addAttribute("usuario", usuario.getUsuario());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", getNoticias());
        modelAndView.setViewName("noticias/listaNoticias");
        return modelAndView;
    }

    @RequestMapping(value = {"/modificarNoticia"} )
    public ModelAndView edit(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", getNoticia(codigo));
        modelAndView.setViewName("noticias/modificarNoticia");
        return modelAndView;
    }

    @PostMapping(value = "/editNoticia")
    public ModelAndView editCliente(Noticia noticia) {
        ModelAndView modelAndView = new ModelAndView();
        List noticias = getNoticias();
        int indexOf = noticias.indexOf(noticia);
        noticias.set(indexOf,noticia);
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/listaNoticias");
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevaNoticia"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticias/nuevaNoticia");
        return modelAndView;
    }

    @PostMapping(value = "/newNoticia")
    public ModelAndView saveCliente(Noticia noticia) {
        ModelAndView modelAndView = new ModelAndView();
        List noticias = getNoticias();
        noticias.add(noticia);
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/listaNoticias");
        return modelAndView;
    }


    @RequestMapping(value = "/borrarNoticia")
    public ModelAndView delete(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        List<Noticia> noticias = getNoticias();
        noticias.remove(new Noticia(codigo));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias",noticias);
        modelAndView.setViewName("noticias/listaNoticias");
        return modelAndView;
    }

    private Noticia getNoticia(int codigo) {
        List<Noticia> noticias = getNoticias();
        int indexOf = noticias.indexOf(new Noticia(codigo));
        
        return noticias.get(indexOf);
    }

    private List<Noticia> getNoticias() {
        ArrayList<Noticia> productos = new ArrayList<Noticia>();
        productos.add(new Noticia(0,"Titular 1","Descripcion 1"));
        productos.add(new Noticia(1,"Titular 2","Descripcion 2"));
        productos.add(new Noticia(2,"Titular 3","Descripcion 3"));
        productos.add(new Noticia(3,"Titular 4","Descripcion 4"));
      return productos;
    }

    @PostMapping(value = {"/login"} )
    public ModelAndView guardarUsuario(Model model,LoginUsuario usuario, HttpSession session) {
        session.setAttribute("usuario", usuario);
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("usuario", usuario.getUsuario());
        modelAndView.addObject("noticias", getNoticias());
        modelAndView.setViewName("noticias/listaNoticias");
        return modelAndView;
    }
}