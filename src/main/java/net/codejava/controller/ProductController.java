package net.codejava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.model.Product;
import net.codejava.service.ProductService;

@Controller
public class ProductController {
	@Autowired
    private ProductService service;
     
    // handler methods...
    @RequestMapping("/view")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "view_product";
    }
    
    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
         
        return "new_product";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
         
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);        
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/view";       
    }
    
    @RequestMapping("/search")
    public String searchNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "search_product";
    }
    
    @RequestMapping("/searchproduct/")
    public String searchNewProductPage(@RequestParam(name = "id") int id,Model model) {
        Product p=service.find(id);
        List<Product> products =new ArrayList<>();
        products.add(p);
        model.addAttribute("listProducts", products);
        return "view_product";
    }

}
