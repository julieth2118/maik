import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Libro {
    private final String titulo;
    private final String autor;
    private final String codigo;
    private boolean disponible;

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true; 
    }

    public void mostrarDatos() {
        System.out.println("Título: " + titulo + " | Autor: " + autor + 
                           " | Código: " + codigo + " | " + 
                           (disponible ? "Disponible" : "Prestado"));
    }

    public void marcarPrestado() {
        disponible = false; 
    }

    public void marcarDisponible() {
        disponible = true; 
    }

    public boolean isDisponible() {
        return disponible; 
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo; 
    }
}

class Usuario {
    private final String nombre;
    private final String idUsuario;
    private final List<Libro> librosPrestados;
    private static final int MAX_LIBROS = 3; 

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>(); 
    }

    public void mostrarDatos() {
        System.out.println("Usuario: " + nombre + " | ID: " + idUsuario);
        System.out.println("Libros prestados (" + librosPrestados.size() + "/" + MAX_LIBROS + "):");
        
        if (librosPrestados.isEmpty()) {
            System.out.println("  - Ninguno");
        } else {
            for (Libro libro : librosPrestados) {
                System.out.println("  - " + libro.getTitulo() + " (" + libro.getCodigo() + ")");
            }
        }
    }

    public boolean agregarPrestamo(Libro libro) {
        if (librosPrestados.size() >= MAX_LIBROS) {
            System.out.println("Error: El usuario ya tiene " + MAX_LIBROS + " libros prestados.");
            return false; 
        }
        
        librosPrestados.add(libro); 
        libro.marcarPrestado(); 
        return true; 
    }

    public boolean devolverLibro(Libro libro) {
        boolean removed = librosPrestados.remove(libro); 
        if (removed) {
            libro.marcarDisponible(); 
        }
        return removed; 
    }

    public boolean tieneLibro(Libro libro) {
        return librosPrestados.contains(libro); 
    }

    public String getIdUsuario() {
        return idUsuario; 
    }

    public String getNombre() {
        return nombre; 
    }
}

class RegistroPrestamo {
    private final Usuario usuario;
    private final Libro libro;
    private final Date fechaPrestamo;
    private final Date fechaDevolucion;

    public RegistroPrestamo(Usuario usuario, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public void mostrarDatos() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Préstamo:");
        System.out.println("  Usuario: " + usuario.getNombre() + " (" + usuario.getIdUsuario() + ")");
        System.out.println("  Libro: " + libro.getTitulo() + " (" + libro.getCodigo() + ")");
        System.out.println("  Fecha préstamo: " + sdf.format(fechaPrestamo));
        System.out.println("  Fecha devolución: " + sdf.format(fechaDevolucion));
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion; 
    }

    public Libro getLibro() {
        return libro; 
    }

    public Usuario getUsuario() {
        return usuario; 
    }
}

public class SistemaBiblioteca {
    private final List<Libro> libros;
    private final List<Usuario> usuarios;
    private final List<RegistroPrestamo> prestamos;
    private final Scanner scanner;
    private static final int DIAS_PRESTAMO = 7; 
    private static final int MULTA_POR_DIA = 500; 

    public SistemaBiblioteca() {
        libros = new ArrayList<>(); 
        usuarios = new ArrayList<>(); 
        prestamos = new ArrayList<>(); 
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        mostrarMenuPrincipal(); 
    }

    private void mostrarMenuPrincipal() {
        int opcion;

        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE BIBLIOTECA ---");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar todos los libros");
            System.out.println("7. Mostrar usuarios registrados");
            System.out.println("8. Mostrar historial de préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = obtenerOpcionValida(); 
            
            switch(opcion) {
                case 1: registrarLibro(); break;
                case 2: registrarUsuario(); break;
                case 3: prestarLibro(); break;
                case 4: devolverLibro(); break;
                case 5: mostrarLibrosDisponibles(); break;
                case 6: mostrarTodosLibros(); break;
                case 7: mostrarUsuarios(); break;
                case 8: mostrarHistorialPrestamos(); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while(opcion != 0);
        
        scanner.close();
    }

    private int obtenerOpcionValida() {
        while(!scanner.hasNextInt()) {
            System.out.print("Por favor ingrese un número: ");
            scanner.next(); 
        }
        return scanner.nextInt(); 
    }

    private void registrarLibro() {
        scanner.nextLine(); 
        
        System.out.println("\n--- REGISTRAR NUEVO LIBRO ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        if (titulo.trim().isEmpty()) {
            System.out.println("Error: El título no puede estar vacío.");
            return;
        }
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        if (codigo.trim().isEmpty()) {
            System.out.println("Error: El código no puede estar vacío.");
            return;
        }
      
        if (buscarLibroPorCodigo(codigo) != null) {
            System.out.println("Error: Ya existe un libro con este código.");
            return;
        }
        
        Libro nuevoLibro = new Libro(titulo, autor, codigo);
        libros.add(nuevoLibro); 
        System.out.println("Libro registrado exitosamente!");
        nuevoLibro.mostrarDatos(); 
    }

    private void registrarUsuario() {
        scanner.nextLine(); 
        
        System.out.println("\n--- REGISTRAR NUEVO USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        if (nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }
        
        System.out.print("ID: ");
        String id = scanner.nextLine();
        
        if (id.trim().isEmpty()) {
            System.out.println("Error: El ID no puede estar vacío.");
            return;
        }
        
    
        if (buscarUsuarioPorId(id) != null) {
            System.out.println("Error: Ya existe un usuario con este ID.");
            return;
        }
        
        Usuario nuevoUsuario = new Usuario(nombre, id);
        usuarios.add(nuevoUsuario); 
        System.out.println("Usuario registrado exitosamente!");
        nuevoUsuario.mostrarDatos(); 
    }

    private void prestarLibro() {
        scanner.nextLine();
        
        System.out.println("\n--- PRESTAR LIBRO ---");
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        
        System.out.print("Código del libro: ");
        String codigoLibro = scanner.nextLine();
        Libro libro = buscarLibroPorCodigo(codigoLibro);
        
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        
        if (!libro.isDisponible()) {
            System.out.println("El libro no está disponible.");
            return;
        }
        
        
        if (usuario.agregarPrestamo(libro)) {
           
            Date fechaPrestamo = new Date();
            Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (long) DIAS_PRESTAMO * 24 * 60 * 60 * 1000);
            
      
            prestamos.add(new RegistroPrestamo(usuario, libro, fechaPrestamo, fechaDevolucion));
            
            System.out.println("Préstamo realizado exitosamente!");
            System.out.println("Fecha de devolución: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaDevolucion));
        }
    }

    private void devolverLibro() {
        scanner.nextLine();
        
        System.out.println("\n--- DEVOLVER LIBRO ---");
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        
        System.out.print("Código del libro: ");
        String codigoLibro = scanner.nextLine();
        Libro libro = buscarLibroPorCodigo(codigoLibro);
        
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        
        if (!usuario.tieneLibro(libro)) {
            System.out.println("Este usuario no tiene prestado este libro.");
            return;
        }
   
        usuario.devolverLibro(libro);
        
  
        RegistroPrestamo prestamo = buscarPrestamo(usuario, libro);
        if (prestamo != null) {
            Date hoy = new Date();
            if (hoy.after(prestamo.getFechaDevolucion())) {
                long diasRetraso = (hoy.getTime() - prestamo.getFechaDevolucion().getTime()) / (1000 * 60 * 60 * 24);
                int multa = (int) diasRetraso * MULTA_POR_DIA;
                System.out.println("¡Atención! Devolución fuera de plazo.");
                System.out.println("Días de retraso: " + diasRetraso);
                System.out.println("Multa a pagar: $" + multa);
            }
        }
        
        System.out.println("Libro devuelto exitosamente!");
    }

    private void mostrarLibrosDisponibles() {
        System.out.println("\n--- LIBROS DISPONIBLES ---");
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        boolean hayDisponibles = false;
        
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                libro.mostrarDatos(); 
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles en este momento.");
        }
    }

    private void mostrarTodosLibros() {
        System.out.println("\n--- TODOS LOS LIBROS ---");
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        for (Libro libro : libros) {
            libro.mostrarDatos(); 
        }
    }

    private void mostrarUsuarios() {
        System.out.println("\n--- USUARIOS REGISTRADOS ---");
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        for (Usuario usuario : usuarios) {
            usuario.mostrarDatos(); 
            System.out.println("---------------------");
        }
    }

    private void mostrarHistorialPrestamos() {
        System.out.println("\n--- HISTORIAL DE PRÉSTAMOS ---");
        
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        
        for (RegistroPrestamo prestamo : prestamos) {
            prestamo.mostrarDatos(); 
            System.out.println("---------------------");
        }
    }

    private Libro buscarLibroPorCodigo(String codigo) {
        for (Libro libro : libros) {
            if (libro.getCodigo().equalsIgnoreCase(codigo)) {
                return libro; 
            }
        }
        return null; 
    }

    private Usuario buscarUsuarioPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equalsIgnoreCase(id)) {
                return usuario; 
            }
        }
        return null; 
    }

    private RegistroPrestamo buscarPrestamo(Usuario usuario, Libro libro) {
        for (RegistroPrestamo prestamo : prestamos) {
            if (prestamo.getUsuario().equals(usuario) && prestamo.getLibro().equals(libro)) {
                return prestamo; 
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SistemaBiblioteca biblioteca = new SistemaBiblioteca(); 
        biblioteca.iniciar(); 
    }
}