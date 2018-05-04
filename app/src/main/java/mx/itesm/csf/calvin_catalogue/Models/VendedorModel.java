package mx.itesm.csf.calvin_catalogue.Models;

/**
 * Created by rodo on 04/05/2018.
 */

public class VendedorModel {

    private String venta_id;
    private String product_name;
    private String client_name;
    private String product_specs;
    private String product_price;

    /* Constructors */
    public VendedorModel( String venta, String p_name, String c_name, String p_specs, String p_price)
    {
        this.venta_id      = venta;
        this.product_name  = p_name;
        this.client_name   = c_name;
        this.product_specs = p_specs;
        this.product_price = p_price;
    }

    public VendedorModel()
    {
        this.venta_id      = "";
        this.product_name  = "";
        this.client_name   = "";
        this.product_specs = "";
        this.product_price = "";
    }

    /* Getters and Setters */

        /* Venta id */
            public String getVenta_id() {
                return venta_id;
            }

            public void setVenta_id(String venta_id) {
                this.venta_id = venta_id;
            }

        /* Product name */
            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

        /* Client Name */
            public String getClient_name() {
                return client_name;
            }

            public void setClient_name(String client_name) {
                this.client_name = client_name;
            }

        /* Product Specifications */
            public String getProduct_specs() {
                return product_specs;
            }

            public void setProduct_specs(String product_specs) {
                this.product_specs = product_specs;
            }

        /* Product price */
            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }
}
