package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entity.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private final Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM seller");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            while (rs.next()) {

                Seller obj = instantiateSeller(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO seller (name, lastname, email, salary) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getLastname());
            st.setString(3, obj.getEmail());
            st.setDouble(4, obj.getSalary());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
                System.out.println("Inserted!");
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM seller WHERE id = ?");
            st.setLong(1, id);
            st.executeUpdate();
            System.out.println("Seller deleted with ID: " + id);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM seller WHERE seller.id = ? ");
            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateSeller(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE seller SET name = ?, lastname = ?, email = ?, salary = ? WHERE id = ? ");
            st.setString(1, obj.getName());
            st.setString(2, obj.getLastname());
            st.setString(3, obj.getEmail());
            st.setDouble(4, obj.getSalary());
            st.setLong(5, obj.getId());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Seller updated with ID: " + obj.getId());
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    private Seller instantiateSeller(ResultSet rs) throws SQLException {
        return new Seller(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("lastname"),
                rs.getString("email"),
                rs.getDouble("salary")
        );
    }
}
