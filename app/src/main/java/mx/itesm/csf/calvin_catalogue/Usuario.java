package mx.itesm.csf.calvin_catalogue;

/**
 * Created by rodo on 28/04/2018.
 */

public class Usuario {

    private String Nombre;
    private String Appaterno;
    private String Apmaterno;
    private Long Edad;
    private Long id_compania;

    private String usuario;
    private String password;
    private String Rol;


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getAppaterno() {
        return Appaterno;
    }

    public void setAppaterno(String Appaterno) {
        this.Appaterno = Appaterno;
    }

    public String getApmaterno() {
        return Apmaterno;
    }

    public void setApmaterno(String Apmaterno) {
        this.Apmaterno = Apmaterno;
    }

    public Long getEdad() {
        return Edad;
    }

    public void setEdad(Long Edad) {
        this.Edad = Edad;
    }

    public Long getid_compania() {
        return id_compania;
    }

    public void setid_compania(Long id_compania) {
        this.id_compania = id_compania;
    }


    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }


    private static final Usuario info = new Usuario();

    public static Usuario getInstance() {
        return info;
    }

}

