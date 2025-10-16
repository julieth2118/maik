package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Usuario;

public class UsuarioRepository {
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (NOMBRE, EDAD) VALUES (?, ?)";
     
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setLong(2, usuario.getEdad());
            
            preparedStatement.executeUpdate();
            
            System.out.println("Usuario insertado correctamente");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Usuario> listaruUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        
        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                usuarios.add(new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getLong("edad")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    public Usuario obtenerUsuarioPorId(long id) {
        String sql = "SELECT * FROM USUARIO WHERE id = ?";
        Usuario usuario = null;
        
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getLong("edad")
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE USUARIO SET NOMBRE = ?, EDAD = ? WHERE ID = ?";
        
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setLong(2, usuario.getEdad());
            preparedStatement.setLong(3, usuario.getId());  // Asumimos que el ID es parte del objeto Usuario
            
            int filasAfectadas = preparedStatement.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("No se encontró el usuario para actualizar");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarUsuario(long id) {
        String sql = "DELETE FROM USUARIO WHERE id = ?";
        
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setLong(1, id);
            
            int filasAfectadas = preparedStatement.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println(" El usuario con ID: " + id + " ha sido eliminado exitosamente.");
            } else {
                System.out.println(" parece que el usuario con ID: " + id + " no está en la base de datos.");
            }
            
        } catch (Exception e) {
            System.out.println(" Hubo un error al tratar de eliminar el usuario.");
            e.printStackTrace();
        }
    }
}








