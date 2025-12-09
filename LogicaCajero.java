import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogicaCajero {

    // DEPÓSITO
    public boolean realizarDeposito(String idCuenta, double monto, String responsable) {

        String sqlUpdate = "UPDATE cuenta SET saldo = saldo + ? WHERE id_cuenta = ?";
        
        String sqlInsert = "INSERT INTO transaccion (id_transaccion,id_cliente,id_cuenta,responsable,monto,tipo,fecha) VALUES (?, ?,?, ?, 1000,?, NOW())";

        return ejecutarTransaccion(sqlUpdate, sqlInsert, idCuenta, monto, responsable);
    }

    // RETIRO
    public boolean realizarRetiro(String idCuenta, double monto, String responsable) {
        // 1. Verificar saldo antes de restar
        double saldoActual = obtenerSaldo(idCuenta);
        
        // Si retorna -1 significa que la cuenta no existe o hubo error de conexión
        if (saldoActual == -1) { 
            System.out.println("Error: Cuenta no encontrada o error de conexión.");
            return false; 
        }
        
        if (saldoActual < monto) {
            System.out.println("Error: Saldo insuficiente.");
            return false; 
        }

        // 2. Restar saldo y registrar
        String sqlUpdate = "UPDATE cuenta SET saldo = saldo - ? WHERE id_cuenta = ?";
        String sqlInsert = "INSERT INTO transaccion (id_cuenta, tipo, monto, responsable, fecha) VALUES (?, 'Retiro', ?, ?, NOW())";

        return ejecutarTransaccion(sqlUpdate, sqlInsert, idCuenta, monto, responsable);
    }

    // MÉTODO PRIVADO AUXILIAR (El motor de la transacción)
    private boolean ejecutarTransaccion(String sqlUpdate, String sqlInsert, String idCuenta, double monto, String responsable) {
        Connection con = null; 
        PreparedStatement psUp = null;
        PreparedStatement psIn = null;
        
        try {
            //  Llamamos a la clase Conexion
            con = Conexion.getConexion(); 

            if (con != null) {
                con.setAutoCommit(false); // Inicio transacción segura

                // 1. Actualizar Saldo
                psUp = con.prepareStatement(sqlUpdate);
                psUp.setDouble(1, monto);
                psUp.setString(2, idCuenta); // Ahora acepta String (ej. "CTA-001")
                int filasSaldo = psUp.executeUpdate();

                // 2. Insertar Historial
                psIn = con.prepareStatement(sqlInsert);
                psIn.setString(1, idCuenta);
                psIn.setDouble(2, monto);
                psIn.setString(3, responsable); // Guardamos quién lo hizo
                int filasHistorial = psIn.executeUpdate();

                // Confirmar si ambos pasos funcionaron
                if (filasSaldo > 0 && filasHistorial > 0) {
                    con.commit(); // CONFIRMAR CAMBIOS
                    return true;
                } else {
                    con.rollback(); // DESHACER CAMBIOS
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
        } finally {
            // Cerrar recursos para no bloquear la BD
            try {
                if (psUp != null) psUp.close();
                if (psIn != null) psIn.close();
                if (con != null) con.close();
            } catch (SQLException ex) {}
        }
        return false;
    }

    // CONSULTAR SALDO
    public double obtenerSaldo(String idCuenta) {
        double saldo = -1; // -1 indica error o cuenta no encontrada
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConexion(); 
            if (con != null) {
                String sql = "SELECT saldo FROM cuenta WHERE id_cuenta = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idCuenta);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    saldo = rs.getDouble("saldo");
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {}
        }
        return saldo;
    }
}