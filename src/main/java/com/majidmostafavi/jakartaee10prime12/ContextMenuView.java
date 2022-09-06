package com.majidmostafavi.jakartaee10prime12;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("dtContextMenuView")
@ViewScoped
public class ContextMenuView implements Serializable {

    private List<Product> products;

    private Product selectedProduct;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = service.getProducts(10);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public void deleteProduct() {
        products.remove(selectedProduct);
        selectedProduct = null;
    }
}
