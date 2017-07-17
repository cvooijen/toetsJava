package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cooijen on 17-7-2017.
 */

@RestController
public class APIProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This API call will show all products in JSON format
     */
    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public Iterable<Product> list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return productService.listAllProducts();
    }

    /**
     * This API call will show selected product in JSON format
     */
    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.GET)
    public Product showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return productService.getProductById(id);
    }

    /**
     * This API call will return selected product for editing in JSON format
     */
    @RequestMapping(value = "/api/product/edit/{id}", method = RequestMethod.GET)
    public Product edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/api/product/new", method = RequestMethod.POST)
    public Product newProduct(Model model, Product product){
        model.addAttribute("product", product);
        return product;
    }

    /**
     * This API call will show saved product in JSON format
     */
    @RequestMapping(value = "/api/product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

    /**
     * This API call will show deleted product in JSON format
     */
    @RequestMapping(value = "/api/product/delete/{id}", method = RequestMethod.GET)
    public Product deleteProduct(@PathVariable Integer id){
        Product p = productService.getProductById(id);
        productService.deleteProduct(p);
        return p;
    }
}
