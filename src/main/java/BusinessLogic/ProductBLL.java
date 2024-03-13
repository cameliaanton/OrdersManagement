package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic class for managing products.
 */
public class ProductBLL {
    private ProductDAO productDAO;
    /**
     * Constructs a new instance of the ProductBLL class.
     * Initializes productDAO.
     */
    public ProductBLL()
    {
        productDAO= new ProductDAO();
    }
    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product
     * @return the found product
     */
    public Product findProductById(int id) {
        Product st= (Product) this.productDAO.findById(id);
       /* if (st == null) {
            throw new NoSuchElementException("The Product with id =" + id + " was not found!");
        } */
        return (Product) st;

    }
    /**
     * Finds a product by its name.
     *
     * @param nume the name of the product
     * @return the found product
     * @throws NoSuchElementException if the product is not found
     */
    public Product findProductByNume(String nume) {
        Product st= (Product) this.productDAO.findByName(nume,"nume");
        if (st == null) {
            throw new NoSuchElementException("The Product with id =" + nume + " was not found!");
        }
        return (Product) st;

    }
    /**
     * Inserts a new product.
     *
     * @param product the product to insert
     * @return the inserted product's ID
     */
    public int insertProduct(Product product) {
        int c=this.productDAO.insert( product);
        afisareAllProduct();
        return (int) c;
    }
    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteProduct(int id){
        this.productDAO.delete(id);
        afisareAllProduct();
    }
    /**
     * Updates a product.
     *
     * @param product the product to update
     * @return the updated product
     */
    public Product updateProduct(Product product)
    {
        Product p=this.productDAO.update(product);
        afisareAllProduct();
        return (Product) p;
    }
    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public List<Product> findAllProduct()
    {
        return this.productDAO.findAll();
    }
    /**
     * Displays all products.
     */
    public void afisareAllProduct()
    {
        List<Product> list = new LinkedList<Product>();
        list=findAllProduct();
        for (int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).toString());
        }

    }
    /**
     * Retrieves the names of all products.
     *
     * @return a list of product names
     */
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        List<Product> products = findAllProduct();
        for (Product product : products) {
            names.add(product.getNume());
        }
        return names;
    }
    /**
     * Retrieves the data of all products.
     *
     * @return a 2D array of product data
     */
    public Object[][] getDataProducts() {
        List<Product> list = new LinkedList<Product>();
        list=findAllProduct();
        Object[][] data=null;
        data= new Object[list.size()][4];
        for(int i=0;i<list.size();i++)
        {
            Product c= list.get(i);
            data[i][0]=c.getId();
            data[i][1]=c.getNume();
            data[i][2]=c.getPret();
            data[i][3]=c.getStoc();
        }
        return data;
    }
}
