package mx.itesm.csf.calvin_catalogue.Models;

/**
 * Created by rodo on 30/04/2018.
 */

public class CatalogModel {

    private String Product_id;
    private String Name;
    private String Desc;
    private String price;
    private String image;

    /* Constructors */
        public CatalogModel( String Product_id, String Name, String Desc, String price, String image)
        {
            this.Product_id = Product_id;
            this.Name       = Name;
            this.price      = price;
            this.image      = image;
            this.Desc       = Desc;
        }

        public CatalogModel()
        {
            this.Product_id = "";
            this.Name       = "";
            this.price      = "";
            this.image      = "";
            this.Desc       = "";
        }

    /* Product Id */
        public String getProduct_id() {
            return Product_id;
        }

        public void setProduct_id(String product_id) {
            Product_id = product_id;
        }

    /* Name */
        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

    /* Description */
        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            Desc = desc;
        }

    /* Price*/
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

    /* Image */
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
}
