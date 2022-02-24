/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.connection.DatabaseConnection;
import domain.GeneralDomainObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rados
 */
public class DBBroker implements IDBBroker {


    @Override
    public GeneralDomainObject find(GeneralDomainObject gdo) throws Exception {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            System.out.println("preuzeta konekcija na bazu");

            String query = "SELECT " + gdo.select() + " FROM " + gdo.getTableName() + gdo.getJoinClause() + " WHERE "  + gdo.getCodeClause();
            System.out.println(query);
            
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            GeneralDomainObject g = gdo.load(rs);

            rs.close();
            s.close();
            return g;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failute to find!");
        }
    }
    
    @Override
    public ArrayList<GeneralDomainObject> getall(GeneralDomainObject gdo) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "SELECT " + gdo.select() + " FROM " + gdo.getTableName() + gdo.getJoinClause() + gdo.getRequirementForMore() + " ORDER BY " + gdo.getOrderByClause();
        System.out.println(query);

        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        ArrayList<GeneralDomainObject> list = gdo.getListFromResultSet(rs);

        rs.close();
        s.close();
        return list;
    }

    @Override
    public int save(GeneralDomainObject gdo) throws Exception {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            System.out.println("preuzeta konekcija na bazu");

            String query = "INSERT INTO " + gdo.getTableName() + "(" + gdo.getAttributeNamesForInsert() + ")" + " VALUES (" + gdo.getAttributeValuesForInsert() + ")";
            System.out.println(query);

            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            System.out.println(ps);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Failute to save!");
        }
        //ps.close();//metoda bi bila void vez generatedkeys
    }

    @Override
    public void update(GeneralDomainObject gdo) throws Exception {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            System.out.println("preuzeta konekcija na bazu");

            String query = "UPDATE " + gdo.getTableName() + " SET " + gdo.getUpdateValues() + " WHERE " + gdo.getPrimaryKeyClause();
            System.out.println(query);

            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println(ps);

            ps.executeUpdate();
            ps.close();
            System.out.println("Successfully updated object");
        } catch (SQLException ex) {
            throw new Exception("Failed changed!");
        }
    }

    @Override
    public ArrayList<GeneralDomainObject> search(GeneralDomainObject gdo, String criterium) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "SELECT * FROM " + gdo.getTableName() + gdo.getJoinClause() + " WHERE " + criterium + " ORDER BY " + gdo.getOrderByClause();
        System.out.println(query);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        ArrayList<GeneralDomainObject> list = gdo.getListFromResultSet(rs);

        rs.close();
        s.close();
        return list;
    }

    @Override
    public void delete(GeneralDomainObject gdo) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        System.out.println("preuzeta konekcija na bazu");

        String query = "DELETE FROM " + gdo.getTableName()
                + " WHERE " + gdo.getPrimaryKeyClause();
        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        System.out.println(ps);

        ps.executeUpdate();
        ps.close();
        System.out.println("Successfully deleted object");
    }

}
