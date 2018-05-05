package mx.itesm.csf.calvin_catalogue.Models;

/**
 * Created by rodo on 04/05/2018.
 */

/*
*
*  Vendedor Model
*
*  Cuenta con todos los datos del vendedor
*
*/
public class VendedorModel {

    private String id;
    private String name;
    private String store;
    private String sales_num;

    /* Constructors */
    public VendedorModel(String id, String name, String store, String sales_num)
    {
        this.id        = id;
        this.name      = name;
        this.store     = store;
        this.sales_num = sales_num;
    }

    public VendedorModel()
    {
        this.id        = "";
        this.name      = "";
        this.store     = "";
        this.sales_num = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getSales_num() {
        return sales_num;
    }

    public void setSales_num(String sales_num) {
        this.sales_num = sales_num;
    }
}
