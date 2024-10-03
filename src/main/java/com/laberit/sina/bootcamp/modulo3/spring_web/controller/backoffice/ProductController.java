package com.laberit.sina.bootcamp.modulo3.spring_web.controller.backoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductCreateDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("backoffice/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ModelMapper modelMapper;


    // Crear un producto [CREATE]
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        Product product = modelMapper.map(productCreateDTO, Product.class);
        productServiceImpl.addProduct(product);
        return product;
    }

    // Obtener todos los productos [READ]
    @GetMapping
    public List<Product> getAllProducts() {
        return productServiceImpl.getAllProducts();
    }


    // Actualizar un producto [UPDATE]
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (productServiceImpl.getProductById(id) == null) {
            return new ResponseEntity<>("Producto con id: " + id + " no encontrado", HttpStatus.NOT_FOUND);
        } else if (product.getId() != null && !product.getId().equals(id)) {
            return new ResponseEntity<>("No se puede cambiar el id del producto", HttpStatus.BAD_REQUEST);
        } else {
            productServiceImpl.updateProduct(id, product);
            return new ResponseEntity<>("Producto con id: " + id + " actualizado", HttpStatus.OK);
        }
    }

    // Eliminar un producto [DELETE]
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean confirmed) {
        if (!confirmed) {
            return new ResponseEntity<>("No se ha confirmado la eliminación", HttpStatus.BAD_REQUEST);
        } else if (productServiceImpl.getProductById(id) == null) {
            return new ResponseEntity<>("Producto " + id + " no encontrado", HttpStatus.NOT_FOUND);
        } else {
            productServiceImpl.deleteProduct(id);
            return new ResponseEntity<>("Producto " + id + " eliminado", HttpStatus.OK);
        }
    }
}