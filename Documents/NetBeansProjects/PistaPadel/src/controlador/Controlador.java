/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.PreparedStatement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Pistas;
import vista.IniciarSesionUsuario;
import vista.IniciarSesionAdmin;
import vista.GestionUsuarios;
import modelo.Usuarios;
import vista.CrearUsuario;
import vista.DiaSeleccionado;
import vista.EditarUsuario;
import vista.GestionPistas;
import vista.Reservar;
import vista.ReservarPistas;
import vista.SeleccionPista;
import vista.GestionarReservas;
import java.sql.Time;
import vista.DiaSeleccionadoAdmin;
import vista.ReservarAdmin;
/**
 *
 * @author isard
 */
public class Controlador {
    
    private static IniciarSesionUsuario viewISU = new IniciarSesionUsuario();
    private static IniciarSesionAdmin viewISA = new IniciarSesionAdmin();
    private static GestionUsuarios viewGU = new GestionUsuarios();
    private static CrearUsuario viewCU = new CrearUsuario();
    private static EditarUsuario viewEU = new EditarUsuario();
    private static GestionPistas viewGP = new GestionPistas();
    private static ReservarPistas viewRP = new ReservarPistas();
    private static SeleccionPista viewSP= new SeleccionPista();
    private static Reservar viewR = new Reservar();
    private static DiaSeleccionado viewDS = new DiaSeleccionado();
    private static GestionarReservas viewGR = new GestionarReservas();
    private static Usuarios model = new Usuarios();
    private static Pistas modelopista = new Pistas();
    private static ArrayList<String> pistas1 = new ArrayList<>();
    private static ReservarAdmin viewRA = new ReservarAdmin();
    private static DiaSeleccionadoAdmin viewDSA = new DiaSeleccionadoAdmin();
    
// REDIRECCIONES
    public static void CentrarTodoYTamaño(){
        viewISU.setLocationRelativeTo(null);
        viewISA.setLocationRelativeTo(null);
        viewGU.setLocationRelativeTo(null);
        viewCU.setLocationRelativeTo(null);
        viewEU.setLocationRelativeTo(null);
        viewGP.setLocationRelativeTo(null);
        viewRP.setLocationRelativeTo(null);
        viewSP.setLocationRelativeTo(null);
        viewR.setLocationRelativeTo(null);
        viewDS.setLocationRelativeTo(null);
        viewGR.setLocationRelativeTo(null);
        viewRA.setLocationRelativeTo(null);
        viewDSA.setLocationRelativeTo(null);
        //Tamaño fijo
        viewISU.setResizable(false);
        viewISA.setResizable(false);
        viewGU.setResizable(false);
        viewCU.setResizable(false);
        viewEU.setResizable(false);
        viewGP.setResizable(false);
        viewRP.setResizable(false);
        viewSP.setResizable(false);
        viewR.setResizable(false);
        viewDS.setResizable(false);
        viewGR.setResizable(false);
        viewRA.setResizable(false);
        viewDSA.setResizable(false);
    }
    
    public static void IniciarSesionUsuario() {
        CentrarTodoYTamaño();
        viewISA.setVisible(false);
        viewISA.txtUsuario.setText("");
        viewISA.txtContraseña.setText("");
        viewISU.setVisible(true);
    }
    
    public static void SalirGU(){
        viewGU.setVisible(false);
        viewISA.txtUsuario.setText("");
        viewISA.txtContraseña.setText("");
        viewISA.setVisible(true);
    }
    
    public static void SalirGP(){
        viewGP.setVisible(false);
        viewISA.txtUsuario.setText("");
        viewISA.txtContraseña.setText("");
        viewISA.setVisible(true);
    }
    
    public static void IniciarSesionAdmin(){
        viewISU.setVisible(false);
        viewISU.txtUsuario.setText("");
        viewISU.txtContraseña.setText("");
        viewISA.setVisible(true);
    }
    
    public static void CreacionUsuarios() {
        viewGU.setVisible(false);
        viewCU.setVisible(true);
    }
    
    public static void VolverCrearUsuario() throws SQLException{
        viewCU.setVisible(false);
        viewGU.setVisible(true);
        AllUsuarios();
    }
    
    public static void VolverEditarUsuario() throws SQLException{
        viewEU.setVisible(false);
        viewGU.setVisible(true);
        AllUsuarios();
    }
    
    public static void ActualizarUsuario() throws SQLException{
        viewEU.setVisible(false);
        viewGU.setVisible(true);
        UpdatearUsuario();
        AllUsuarios();
    }
    
    public static java.sql.Date GuardarFecha(){
       Date date =  Controlador.viewR.jCalendar.getDate();
       long d = date.getTime();
       java.sql.Date fecha = new java.sql.Date (d);
        modelopista.setDia(fecha);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Convertir el objeto Date a una cadena de texto
       String fechaComoTexto = sdf.format(fecha);
       viewDS.setjLabel1(fechaComoTexto);
       return fecha;
    }
    
    public static java.sql.Date GuardarFechaAdmin(){
       Date date =  Controlador.viewRA.jCalendar.getDate();
       long d = date.getTime();
       java.sql.Date fecha = new java.sql.Date (d);
        modelopista.setDia(fecha);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Convertir el objeto Date a una cadena de texto
       String fechaComoTexto = sdf.format(fecha);
       viewDSA.setjLabel1(fechaComoTexto);
       return fecha;
    }
    
    public static void Reserva1(){
        try{
            viewR.setVisible(false);
            viewDS.setVisible(true);
            viewDS.txtHora10.setVisible(false);
            viewDS.txtHora10.setText("Hora no disponible");
            viewDS.txtHora11.setVisible(false);
            viewDS.txtHora11.setText("Hora no disponible");
            viewDS.txtHora12.setVisible(false);
            viewDS.txtHora12.setText("Hora no disponible");
            viewDS.txtHora13.setVisible(false);
            viewDS.txtHora13.setText("Hora no disponible");
            viewDS.txtHora17.setVisible(false);
            viewDS.txtHora17.setText("Hora no disponible");
            viewDS.txtHora18.setVisible(false);
            viewDS.txtHora18.setText("Hora no disponible");
            viewDS.txtHora19.setVisible(false);
            viewDS.txtHora19.setText("Hora no disponible");
            viewDS.txtHora20.setVisible(false);
            viewDS.txtHora20.setText("Hora no disponible");
            viewDS.btnReservar10.setVisible(true);
            viewDS.btnReservar11.setVisible(true);
            viewDS.btnReservar12.setVisible(true);
            viewDS.btnReservar13.setVisible(true);
            viewDS.btnReservar17.setVisible(true);
            viewDS.btnReservar18.setVisible(true);
            viewDS.btnReservar19.setVisible(true);
            viewDS.btnReservar20.setVisible(true);
           
            Statement estado = conectarse();
            ResultSet result = estado.executeQuery("SELECT * FROM reservas WHERE fk_codigo ='"+modelopista.getCodigo()+"' AND dia ='"+modelopista.getDia()+"'");
            while (result.next()) {
                String usu = result.getString(1);
                String hora = result.getString(4);
                if(hora.equals("10:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora10.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar10.setVisible(false);
                    viewDS.txtHora10.setVisible(true);
                }
                if(hora.equals("11:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora11.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar11.setVisible(false);
                    viewDS.txtHora11.setVisible(true);
                }
                if(hora.equals("12:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora12.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar12.setVisible(false);
                    viewDS.txtHora12.setVisible(true);
                }
                if(hora.equals("13:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora13.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar13.setVisible(false);
                    viewDS.txtHora13.setVisible(true);
                }
                if(hora.equals("17:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora17.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar17.setVisible(false);
                    viewDS.txtHora17.setVisible(true);
                }
                if(hora.equals("18:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora18.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar18.setVisible(false);
                    viewDS.txtHora18.setVisible(true);
                }
                if(hora.equals("19:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora19.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar19.setVisible(false);
                    viewDS.txtHora19.setVisible(true);
                }
                if(hora.equals("20:00:00")){
                    if(usu.equals("admin")){
                        viewDS.txtHora20.setText("Pista deshabilitada");
                    }
                    viewDS.btnReservar20.setVisible(false);
                    viewDS.txtHora20.setVisible(true);
                }
            }
            viewDS.setTxtPISTA(modelopista.getCodigo());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void ReservaAdmin1(){
        try{
            viewRA.setVisible(false);
            viewDSA.setVisible(true);
            viewDSA.txtHora10.setVisible(false);
            viewDSA.txtHora11.setVisible(false);
            viewDSA.txtHora12.setVisible(false);
            viewDSA.txtHora13.setVisible(false);
            viewDSA.txtHora17.setVisible(false);
            viewDSA.txtHora18.setVisible(false);
            viewDSA.txtHora19.setVisible(false);
            viewDSA.txtHora20.setVisible(false);
            viewDSA.btnReservar10.setVisible(true);
            viewDSA.btnReservar11.setVisible(true);
            viewDSA.btnReservar12.setVisible(true);
            viewDSA.btnReservar13.setVisible(true);
            viewDSA.btnReservar17.setVisible(true);
            viewDSA.btnReservar18.setVisible(true);
            viewDSA.btnReservar19.setVisible(true);
            viewDSA.btnReservar20.setVisible(true);
            
            Statement estado = conectarse();
            ResultSet result = estado.executeQuery("SELECT * FROM reservas WHERE fk_correo = 'admin' AND fk_codigo ='"+modelopista.getCodigo()+"' AND dia ='"+modelopista.getDia()+"'");
            while (result.next()) {
                String hora = result.getString(4);
                if(hora.equals("10:00:00")){
                    viewDSA.btnReservar10.setVisible(false);
                    viewDSA.txtHora10.setVisible(true);
                }
                if(hora.equals("11:00:00")){
                    viewDSA.btnReservar11.setVisible(false);
                    viewDSA.txtHora11.setVisible(true);
                }
                if(hora.equals("12:00:00")){
                    viewDSA.btnReservar12.setVisible(false);
                    viewDSA.txtHora12.setVisible(true);
                }
                if(hora.equals("13:00:00")){
                    viewDSA.btnReservar13.setVisible(false);
                    viewDSA.txtHora13.setVisible(true);
                }
                if(hora.equals("17:00:00")){
                    viewDSA.btnReservar17.setVisible(false);
                    viewDSA.txtHora17.setVisible(true);
                }
                if(hora.equals("18:00:00")){
                    viewDSA.btnReservar18.setVisible(false);
                    viewDSA.txtHora18.setVisible(true);
                }
                if(hora.equals("19:00:00")){
                    viewDSA.btnReservar19.setVisible(false);
                    viewDSA.txtHora19.setVisible(true);
                }
                if(hora.equals("20:00:00")){
                    viewDSA.btnReservar20.setVisible(false);
                    viewDSA.txtHora20.setVisible(true);
                }
            }
            viewDSA.setTxtPISTA(modelopista.getCodigo());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void Reserva3() throws SQLException{
        viewRP.setVisible(false);
        viewSP.setVisible(true);
        Date fechaActual = new Date();
        viewR.jCalendar.setMinSelectableDate(fechaActual);
        AllPistas2();
    }
    
    public static void ReservaAdmin3() throws SQLException{
        viewGP.setVisible(false);
        viewRA.setVisible(true);
        Date fechaActual = new Date();
        viewRA.jCalendar.setMinSelectableDate(fechaActual); 
        
    }
    
    public static void Moverse() throws SQLException{
        viewGU.setVisible(false);
        viewGP.setVisible(true);
        AllPistas();
    }
    
    public static void Moverse2() throws SQLException{
        viewGP.setVisible(false);
        viewGU.setVisible(true);
        AllUsuarios();
    }
    
    public static void VolverDiaSeleccionado(){
        viewDS.setVisible(false);
        viewR.setVisible(true);
    }
    
    public static void VolverDiaSeleccionadoAdmin(){
        viewDSA.setVisible(false);
        viewRA.setVisible(true);
    }
    
    public static void InicioDiaSeleccionado(){
        viewDS.setVisible(false);
        viewRP.setVisible(true);
    }
    
    public static void InicioDiaSeleccionadoAdmin(){
        viewDSA.setVisible(false);
        viewGP.setVisible(true);
    }
    
    public static void VolverReservar(){
        viewR.setVisible(false);
        viewSP.setVisible(true);
    }
    
    public static void VolverReservarAdmin(){
        viewRA.setVisible(false);
        viewGP.setVisible(true);
    }
    
    public static void InicioReservar(){
        viewR.setVisible(false);
        viewRP.setVisible(true);
    }
    
    public static void VolverSeleccionPista(){
        viewSP.setVisible(false);
        viewRP.setVisible(true);
    }
    
    public static void CerrarSesionReservarPista(){
        viewRP.setVisible(false);
        viewISU.txtUsuario.setText("");
        viewISU.txtContraseña.setText("");
        viewISU.setVisible(true);
    }
    
    public static void GestionR() throws SQLException{
        viewRP.setVisible(false);
        viewGR.setVisible(true);
        AllReservas();
    }
    
    public static void  VolverMenuGestion(){
        viewGR.setVisible(false);
        viewRP.setVisible(true);
    }
            
// CONECTARSE A LA BBDD
    
    public static Statement conectarse() {
        String driver = "com.mysql.jdbc.Driver";
        String cadenaConeccion = "jdbc:mysql://127.0.0.1/pistapadel";
        String usuario = "root";
        String contraseña = "";
        Connection con;
        Statement st = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(cadenaConeccion, usuario, contraseña);
            st = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido establecer una conexion con la BD");
        }
        return st;
    }

// INICIAR SESION
    
    public static void EntrarAdmin() throws SQLException, NoSuchAlgorithmException{
        
        try {
            Statement estado = conectarse();
        ResultSet result = estado.executeQuery("SELECT * FROM administrador");
        String contraseña = "";
        String usuario = "";
        while (result.next()) {
            usuario = result.getString(1);
            contraseña = result.getString(2);
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(viewISA.txtContraseña.getText().getBytes());
        StringBuilder hexContraseña = new StringBuilder();
        for(byte b : hash){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1){
                hexContraseña.append("0");
            }
            hexContraseña.append(hex);
        }
        if(usuario.equals(viewISA.txtUsuario.getText()) && contraseña.equals(hexContraseña.toString())){
            viewISA.setVisible(false);
            viewGU.setVisible(true);
            AllUsuarios();
            viewISA.txtUsuario.setText("");
            viewISA.txtContraseña.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos");
        }
        } catch (Exception e) {
            System.out.println("Sin conexion");
        }
        
    }
    
    public static void EntrarUsuario() throws SQLException, NoSuchAlgorithmException{
        try {
            Statement estado = conectarse();
        ResultSet result = estado.executeQuery("SELECT * FROM usuarios");
        String contraseña = "";
        String usuario = "";
        String activo = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(viewISU.txtContraseña.getText().getBytes());
        StringBuilder hexContraseña = new StringBuilder();
        for(byte b : hash){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1){
                hexContraseña.append("0");
            }
            hexContraseña.append(hex);
        }
        String usu = viewISU.txtUsuario.getText() + "/" + hexContraseña.toString();
        Boolean entrar = false;
        ArrayList<String> usuarios = new ArrayList<>();
        while (result.next()) {
            usuario = result.getString(2);
            contraseña = result.getString(5);
            activo = result.getString(6);
            usuarios.add(usuario + "/" + contraseña);
            model.correo = result.getString(1);
        }
        for(String i : usuarios){
            if(i.equals(usu)){
                entrar = true;
            }
        }
        if(activo.equals("1")){
            if(entrar){
            viewISU.setVisible(false);
            viewRP.setVisible(true);
            }else{
            JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El usuario no esta activo");
            viewISU.txtUsuario.setText("");
            viewISU.txtContraseña.setText("");
        }
        } catch (Exception e) {
            System.out.println("Sin conexion");
        }
        
    }
    
// FUNCIONES DE LA PARTE DE ADMIN
    
// USUARIOS
    
    public static void AllUsuarios() throws SQLException {
        try {
            Statement estado = conectarse();
        ResultSet result = estado.executeQuery("SELECT * FROM usuarios");
        ArrayList<String> usuarios = new ArrayList<>();
        while (result.next()) {
            String nombre = result.getString(3);
            String apellidos = result.getString(4);
            usuarios.add(nombre + " " + apellidos);
        }
        viewGU.ListaUsuarios.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return usuarios.size();
            }

            public String getElementAt(int i) {
                return usuarios.get(i);
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void BuscarUsuarios() throws SQLException{
        try {
            Statement estado = conectarse();
        String buscar = viewGU.txtBuscar.getText();
        ResultSet result = estado.executeQuery("SELECT * FROM usuarios");
        ArrayList<String> usuarios = new ArrayList<>();
        while (result.next()) {
            String nombre = result.getString(3);
            String apellidos = result.getString(4);
            if(nombre.contains(buscar)){
                usuarios.add(nombre + " " + apellidos);
            }
        }
        viewGU.ListaUsuarios.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return usuarios.size();
            }

            public String getElementAt(int i) {
                return usuarios.get(i);
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void CrearUsuario() throws SQLException, NoSuchAlgorithmException{
        try {
            Statement conexion = conectarse();
        String Nombre = viewCU.txtNombre.getText();
        String Apellidos = viewCU.txtApellidos.getText();
        String Correo = viewCU.txtCorreo.getText();
        String Usuarios = viewCU.txtUser.getText();
        String Contraseña = viewCU.txtPassword.getText();
       
        if (Nombre.isEmpty() || Apellidos.isEmpty() || Correo.isEmpty() || Usuarios.isEmpty() || Contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
        } else {
            String sql = ("INSERT INTO usuarios (correo, usuario, nombre, apellidos, contraseña, socio) VALUES (?,?,?,?,?,?)");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(Contraseña.getBytes());
            StringBuilder hexContraseña = new StringBuilder();
            for(byte b : hash){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1){
                    hexContraseña.append("0");
                }
                hexContraseña.append(hex);
            }
            try (PreparedStatement query = (PreparedStatement) conexion.getConnection().prepareStatement(sql)) {
                query.setString(1, Correo);
                query.setString(2, Usuarios);
                query.setString(3, Nombre);
                query.setString(4, Apellidos);
                query.setString(5, hexContraseña.toString());
                query.setBoolean(6, true);

                int filasAfectadas = query.executeUpdate();
                viewCU.setVisible(false);
                viewGU.setVisible(true);
                viewCU.txtNombre.setText("");
                viewCU.txtApellidos.setText("");
                viewCU.txtCorreo.setText("");
                viewCU.txtUser.setText("");
                viewCU.txtPassword.setText("");
                AllUsuarios();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe");
            }
        }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void Activo(){
        String valor = (String) viewEU.Desplegable.getSelectedItem();
        boolean actiu = false;
        if(valor == "Activo"){
            actiu = true;
        }
        Statement estado = conectarse();
        try(estado){
            String sql = "UPDATE usuarios SET socio = ? WHERE nombre='" + model.nombre + "' AND apellidos='" + model.apellidos + "'";
            try(PreparedStatement prepareQuery = (PreparedStatement) estado.getConnection().prepareStatement(sql)){
                prepareQuery.setBoolean(1, actiu);
                prepareQuery.executeUpdate();
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
    }
    
    public static void EditarUsuario() throws SQLException {
        try {
            Statement estado = conectarse();
        if(viewGU.ListaUsuarios.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Porfavor, seleccione un usuario para editarlo");
        }else{
            String usuario = viewGU.ListaUsuarios.getSelectedValue();
            String[] usuarios = usuario.split(" ");
            model.nombre = usuarios[0];
            model.apellidos = usuarios[1];
            ResultSet result = estado.executeQuery("SELECT * FROM usuarios WHERE nombre='" + model.nombre + "' AND apellidos='" + model.apellidos + "'");
            if (result.next()) {
                viewEU.txtUser.setText(result.getString(2));
                viewEU.txtNombre.setText(result.getString(3));
                viewEU.txtApellidos.setText(result.getString(4));
            }
            viewGU.setVisible(false);
            viewEU.setVisible(true);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void UpdatearUsuario() throws SQLException {
        try {
            Statement estado = conectarse();
        String sql = "UPDATE usuarios SET usuario=?, nombre=?, apellidos=? WHERE nombre='" + model.nombre + "' AND apellidos='" + model.apellidos + "'";
        try (PreparedStatement prepareQuery = (PreparedStatement) estado.getConnection().prepareStatement(sql)) {
            prepareQuery.setString(1, viewEU.txtUser.getText());
            prepareQuery.setString(2, viewEU.txtNombre.getText());
            prepareQuery.setString(3, viewEU.txtApellidos.getText());
            prepareQuery.executeUpdate();
            Activo();
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
// PISTAS
    
    public static void AllPistas() throws SQLException {
        try {
            Statement estado = conectarse();
        ResultSet result = estado.executeQuery("SELECT * FROM pista");
        ArrayList<String> pistas = new ArrayList<>();
        while (result.next()) {
            String codigo = result.getString(1);
            String activo = "";
            if(result.getInt(2)==1){
                activo = "Pista activa";
            }else{
                activo = "Pista inactiva";
            }
            pistas.add(codigo + " - " + activo);
        }
        viewGP.ListaPistas.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return pistas.size();
            }

            public String getElementAt(int i) {
                return pistas.get(i);
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void BuscarPistas() throws SQLException{
        try {
            Statement estado = conectarse();
        String buscar = viewGP.txtBuscar.getText();
        ResultSet result = estado.executeQuery("SELECT * FROM pista");
        ArrayList<String> pistas = new ArrayList<>();
        while (result.next()) {
            String codigo = result.getString(1);
            String activo = "";
            if(result.getInt(2)==1){
                activo = "Pista activa";
            }else{
                activo = "Pista inactiva";
            }
            if(codigo.contains(buscar)){
                pistas.add(codigo + " - " + activo);
            }
        }
        viewGP.ListaPistas.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return pistas.size();
            }

            public String getElementAt(int i) {
                return pistas.get(i);
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void CrearPista(){
        try {
              Statement estado = conectarse();
        String sql = ("INSERT INTO pista (disponibilidad) VALUES (?)");
        try (PreparedStatement query = (PreparedStatement) estado.getConnection().prepareStatement(sql)) {
                query.setBoolean(1, true);
                int filasAfectadas = query.executeUpdate();
                JOptionPane.showMessageDialog(null, "¡Pista creada con exito!");
                AllPistas();
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear una pista");
        }          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void ActivarPista() throws SQLException {
        try {
            Statement estado = conectarse();
        if(viewGP.ListaPistas.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Porfavor, seleccione una pista");
        }else{
            String pistas = viewGP.ListaPistas.getSelectedValue();
            String[] pista = pistas.split(" ");
            modelopista.codigo = pista[0];
            ResultSet result = estado.executeQuery("SELECT * FROM pista WHERE codigo ='" + modelopista.codigo + "'"); 
            if (result.next()) {
                try(estado){
                    String sql = "UPDATE pista SET disponibilidad = ? WHERE codigo='" + modelopista.codigo + "'";
                    try(PreparedStatement prepareQuery = (PreparedStatement) estado.getConnection().prepareStatement(sql)){
                        prepareQuery.setBoolean(1, true);
                        prepareQuery.executeUpdate();
                    }
                    AllPistas();
                }
            }  
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void DesactivarPista() throws SQLException {
        try {
            Statement estado = conectarse();
        if(viewGP.ListaPistas.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Porfavor, seleccione una pista");
        }else{
            String pistas = viewGP.ListaPistas.getSelectedValue();
            String[] pista = pistas.split(" ");
            modelopista.codigo = pista[0];
            ResultSet result = estado.executeQuery("SELECT * FROM pista WHERE codigo ='" + modelopista.codigo + "'"); 
            if (result.next()) {
                try(estado){
                    String sql = "UPDATE pista SET disponibilidad = ? WHERE codigo='" + modelopista.codigo + "'";
                    try(PreparedStatement prepareQuery = (PreparedStatement) estado.getConnection().prepareStatement(sql)){
                        prepareQuery.setBoolean(1, false);
                        prepareQuery.executeUpdate();
                    }
                    AllPistas();
                }
            }  
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void SelecionarPistaAdmin() throws SQLException {
        if(viewGP.ListaPistas.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Porfavor, seleccione una pista");
        }else{
            String pista = viewGP.ListaPistas.getSelectedValue();
            String[] pistas = pista.split(" ");
            String codigo = pistas[0];
            modelopista.codigo = codigo;
            ReservaAdmin3();
        }
    }
    
    public static void ReservarPistaAdmin(Time horaseleccionada) throws SQLException{
        try {
            Statement conexion = conectarse();
        int filasAfectadas = 0;
        String sql1 = ("SELECT * FROM reservas WHERE fk_codigo = ? AND dia = ? AND hora = ?");
        try (PreparedStatement query = (PreparedStatement) conexion.getConnection().prepareStatement(sql1)) {
            query.setString(1, modelopista.getCodigo());
            query.setDate(2, modelopista.getDia());
            modelopista.hora = horaseleccionada;
            query.setTime(3, modelopista.getHora());
            ResultSet resultSet = query.executeQuery();
            
            if(!resultSet.next()){
                String sql3 = ("INSERT INTO reservas (fk_correo, fk_codigo, dia, hora, pago) VALUES (?,?,?,?,?)");
                try (PreparedStatement insertquery = (PreparedStatement) conexion.getConnection().prepareStatement(sql3)) {
                    insertquery.setString(1, "admin");
                insertquery.setString(2, modelopista.getCodigo());
                    insertquery.setDate(3, modelopista.getDia());
                    insertquery.setTime(4, modelopista.getHora());
                    insertquery.setBoolean(5, false);
                    filasAfectadas = insertquery.executeUpdate();
                    ReservaAdmin1();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al reservar la pista");
                }
            }else{
                String sql2 = ("DELETE FROM reservas WHERE fk_codigo = ? AND dia = ? AND hora = ?");
                try (PreparedStatement deletequery = (PreparedStatement) conexion.getConnection().prepareStatement(sql2)) {
                    deletequery.setString(1, modelopista.getCodigo());
                    deletequery.setDate(2, modelopista.getDia());
                    deletequery.setTime(3, modelopista.getHora());
                    filasAfectadas = deletequery.executeUpdate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al reservar la pista");
                }
                String sql3 = ("INSERT INTO reservas (fk_correo, fk_codigo, dia, hora, pago) VALUES (?,?,?,?,?)");
                try (PreparedStatement insertquery = (PreparedStatement) conexion.getConnection().prepareStatement(sql3)) {
                    insertquery.setString(1, "admin");
                    insertquery.setString(2, modelopista.getCodigo());
                    insertquery.setDate(3, modelopista.getDia());
                    insertquery.setTime(4, modelopista.getHora());
                    insertquery.setBoolean(5, false);
                    filasAfectadas = insertquery.executeUpdate();
                    ReservaAdmin1();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al reservar la pista");
                }    
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al reservar la pista");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    } 
    
// FUNCIONES DE LA PARTE DE USUARIO
    
    public static void AllReservas() throws SQLException {
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Formateada = sdf.format(fechaActual);
        try {
            Statement estado = conectarse();
        ResultSet result = estado.executeQuery("SELECT * FROM reservas WHERE fk_correo ='"+model.getCorreo()+"' AND dia >= '"+Formateada+"'");
        pistas1 = new ArrayList<>();
        while (result.next()) {
            String pista = result.getString(2);
            String dia = result.getString(3);
            String hora = result.getString(4);
            String pago = result.getString(5);
            if (pago.equals("0")){
                pago="No";
            } else{
                pago="Si";
            }
            pistas1.add("Pista: "+pista+" Dia: "+dia+" Hora: "+hora+" Pagado: "+pago); 
        }
        viewGR.ListaReservas.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return pistas1.size();
            }

            public String getElementAt(int i) {
                return pistas1.get(i);
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void AllPistas2() throws SQLException {
        try {
            Statement estado = conectarse();
        ResultSet result = estado.executeQuery("SELECT * FROM pista WHERE disponibilidad = true");
        ArrayList<String> pistas = new ArrayList<>();
        while (result.next()) {
            String codigo = result.getString(1);
            pistas.add("Pista: "+codigo );
            
        }
        viewSP.ListaPistas.setModel(new javax.swing.AbstractListModel<String>() {

            public int getSize() {
                return pistas.size();
            }

            public String getElementAt(int i) {
                return pistas.get(i);
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void SelecionarPista() throws SQLException {
        if(viewSP.ListaPistas.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Porfavor, seleccione una pista");
        }else{
            String pista = viewSP.ListaPistas.getSelectedValue();
            String[] pistas = pista.split(":");
            if (pistas.length == 2) {
                String codigo = pistas[1].trim();
                modelopista.codigo = codigo;
                viewSP.setVisible(false);
                viewR.setVisible(true);
            }else{
                modelopista.codigo = pistas[1];
                viewSP.setVisible(false);
                viewR.setVisible(true);
            } 
        }
    }
    
    public static void Reservar(Time horaseleccionada){
        try {
            Statement conexion = conectarse();
        String sql = ("INSERT INTO reservas (fk_correo, fk_codigo, dia, hora, pago) VALUES (?,?,?,?,?)");
        try (PreparedStatement query = (PreparedStatement) conexion.getConnection().prepareStatement(sql)) {
            query.setString(1, model.correo);
            query.setString(2, modelopista.getCodigo());
            query.setDate(3, modelopista.getDia());
            modelopista.hora = horaseleccionada;
            query.setTime(4, modelopista.getHora());
            query.setBoolean(5, false);
            int filasAfectadas = query.executeUpdate();
            Reserva1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al reservar la pista");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void CancelarReserva(){
        try {
            Statement conexion = conectarse();
        String sql = "DELETE FROM reservas WHERE fk_correo = ? AND fk_codigo = ? AND dia = ? AND hora = ?";
        try (PreparedStatement query = (PreparedStatement) conexion.getConnection().prepareStatement(sql)) {
            if(viewGR.ListaReservas.getSelectedValue()!= null){
                String reserva = viewGR.ListaReservas.getSelectedValue();
                String[] partes = reserva.split("Dia: | Hora: | Pagado: ");
                String pista = partes[0].replace("Pista: ", "");
                String dia = partes[1];
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                // Convierte la cadena a un objeto java.util.Date
                java.util.Date utilDate = formato.parse(dia);
                // Luego, convierte java.util.Date a java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                String hora = partes[2];
                Time horaseleccionada = java.sql.Time.valueOf(hora);
      
                query.setString(1, model.correo);
                query.setString(2, pista);
                query.setDate(3, sqlDate);
                query.setTime(4, horaseleccionada);
                int filasAfectadas = query.executeUpdate();
            } else{
                JOptionPane.showMessageDialog(null, "Porfavor, seleccione una reserva para cancelar");
            }
            AllReservas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cancelar la reserva");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
        
    }
    
    public static void PagarReserva(){
        try {
             Statement conexion = conectarse();
        String sql1 = "SELECT * FROM reservas WHERE fk_correo = ? AND fk_codigo = ? AND dia = ? AND hora = ?";
        String sql2 = "UPDATE reservas SET pago = 1 WHERE fk_correo = ? AND fk_codigo = ? AND dia = ? AND hora = ?";
        try (PreparedStatement query = (PreparedStatement) conexion.getConnection().prepareStatement(sql1)) {
            if(viewGR.ListaReservas.getSelectedValue()!= null){
                String reserva = viewGR.ListaReservas.getSelectedValue();
                String[] partes = reserva.split("Dia: | Hora: | Pagado: ");
                String pista = partes[0].replace("Pista: ", "");
                String dia = partes[1];
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                // Convierte la cadena a un objeto java.util.Date
                java.util.Date utilDate = formato.parse(dia);
                // Luego, convierte java.util.Date a java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                String hora = partes[2];
                Time horaseleccionada = java.sql.Time.valueOf(hora);
                String pago = partes[3];
     
                query.setString(1, model.correo);
                query.setString(2, pista);
                query.setDate(3, sqlDate);
                query.setTime(4, horaseleccionada);
                if(pago.equals("Si")){
                    JOptionPane.showMessageDialog(null, "La reserva ya esta pagada");
                }else{
                    try (PreparedStatement query2 = (PreparedStatement) conexion.getConnection().prepareStatement(sql2)) {
                    
                        query2.setString(1, model.correo);
                        query2.setString(2, pista);
                        query2.setDate(3, sqlDate);
                        query2.setTime(4, horaseleccionada);
                        int filasAfectadas = query2.executeUpdate();
                        AllReservas();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al pagar la reserva");
                    }
                }
            } else{
                JOptionPane.showMessageDialog(null, "Porfavor, seleccione una reserva para pagar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al pagar la reserva");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Posible error de conexion");
        }
       
    }
}