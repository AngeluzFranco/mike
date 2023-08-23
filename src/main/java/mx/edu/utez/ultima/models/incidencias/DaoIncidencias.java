package mx.edu.utez.ultima.models.incidencias;

import mx.edu.utez.ultima.models.crud.DaoRepository;
import mx.edu.utez.ultima.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoIncidencias implements DaoRepository<Incidencias> {

    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List<Incidencias> findAll() {
        List<Incidencias> incidencias = new ArrayList<>();
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM incidencias;";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Incidencias incidencia = new Incidencias();
                incidencia.setId_incidencia(rs.getLong("id_incidencia"));
                incidencia.setTitulo(rs.getString("titulo"));
                incidencia.setDescripcion(rs.getString("descripcion"));
                incidencia.setTipo(rs.getString("tipo"));
                incidencia.setStatus(rs.getString("status"));
                incidencias.add(incidencia);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error findAll " + e.getMessage());
        } finally {
            close();
        }
        return incidencias;
    }


    @Override
    public Incidencias findOne(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM incidencias WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            Incidencias incidencias = new Incidencias();
            if (rs.next()) {

                incidencias.setId_incidencia(rs.getLong("id"));
                incidencias.setTitulo(rs.getString("titulo"));
                incidencias.setDescripcion(rs.getString("descripcion"));
                incidencias.setTipo(rs.getString("tipo"));
                incidencias.setStatus(rs.getString("status"));
            }
            return incidencias;
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error findOne " + e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    @Override
    public boolean save(Incidencias object) {
        try {
            conn = new MySQLConnection().connect();
            String query = "INSERT INTO incidencias (titulo, descripcion, tipo)" +" VALUES (?, ?, ?);";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, object.getTitulo());
            pstm.setString(2, object.getDescripcion());
            pstm.setString(3, object.getTipo());
            return pstm.executeUpdate() > 0; // == 1
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error save " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean update(Incidencias object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            conn = new MySQLConnection().connect();
            String query = "DELETE FROM incidencias WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error delete " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public boolean enabled(Incidencias incidencias) throws SQLException {
        try {
            conn = new MySQLConnection().connect();
            conn.setAutoCommit(false);
            String query = "UPDATE incidencias SET status = 'APROBADO' WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.execute();
            query = "UPDATE incidencias SET status = 'RECHAZADO' WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, incidencias.getId_incidencia());
            pstm.execute();
            conn.commit();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error update " + e.getMessage());
            conn.rollback();
        } finally {
            close();
        }
        return false;
    }

    public boolean active(Long id) {
        try{
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidencias SET status = 'ACTIVO' WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " Estado: Activo " );
            return pstm.executeUpdate() > 0; // ==1
        }catch(SQLException e){
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error findAll" + e.getMessage());
        }finally{
            close();
        }
        return false;
    }

    public boolean active2(Long id) {
        try{
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidencias SET status = 'APROBADO' WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " Estado: Aprobado" );
            return pstm.executeUpdate() > 0; // ==1
        }catch(SQLException e){
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error findAll" + e.getMessage());
        }finally{
            close();
        }
        return false;
    }

    public boolean inactive(Long id) {
        try{
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidencias SET status = 'RECHAZADO' WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " Estado: " + 0);
            return pstm.executeUpdate() > 0; // ==1
        }catch(SQLException e){
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error findAll" + e.getMessage());
        }finally{
            close();
        }
        return false;
    }

    public boolean inactive2(Long id) {
        try{
            conn = new MySQLConnection().connect();
            String query = "UPDATE incidencias SET status = 'RECHAZADO' WHERE id_incidencia = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, Long.parseLong(String.valueOf(id)));
            System.out.println("id: " + id + " Estado: " + 0);
            return pstm.executeUpdate() > 0; // ==1
        }catch(SQLException e){
            Logger.getLogger(DaoIncidencias.class.getName()).log(Level.SEVERE, "Error findAll" + e.getMessage());
        }finally{
            close();
        }
        return false;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {

        }
    }
}
