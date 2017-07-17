package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    @RequestMapping(value = "/product/new/", method = RequestMethod.POST)
    public String newProduct(Model model, @RequestBody Product product){
        model.addAttribute("product", product);
        return "redirect:/products/";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Integer id){
        Product p = productService.getProductById(id);
        productService.deleteProduct(p);
        return "redirect:/products/";
    }

    @RequestMapping(value = "/product/stock/{id}/{newAmount}", method = RequestMethod.GET)
    public String updateAmountStockForProduct(@PathVariable Integer id, @PathVariable Integer newAmount) {
        Product p = productService.getProductById(id);
        p.setAmountInStock(newAmount);
        productService.saveProduct(p);
        return "redirect:/products/";
    }

}
