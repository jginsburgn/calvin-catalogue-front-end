package mx.itesm.csf.calvin_catalogue.Models;

import android.graphics.Bitmap;

/**
 * Created by rodo on 30/04/2018.
 */

public class CatalogModel {

    private String Product_id;
    private String Name;
    private String Desc;
    private String price;
    private Bitmap image;
    private String image_name;

    /* Constructors */
        public CatalogModel( String Product_id, String Name, String Desc, String price, Bitmap image, String image_name)
        {
            this.Product_id = Product_id;
            this.Name       = Name;
            this.price      = price;
            this.image      = image;
            this.Desc       = Desc;
            this.image_name = image_name;
        }

        public CatalogModel()
        {
            this.Product_id = "";
            this.Name       = "";
            this.price      = "";
            this.image      =  null;
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
        public Bitmap getImage() {
            return image;
        }

        public void setImage(Bitmap image) {
            this.image = image;
        }

        public void setImageN(String image_name){
            this.image_name = image_name;
        }
        public String getImageN(){return image_name;}
}
