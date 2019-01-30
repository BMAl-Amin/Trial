package com.gams.dao.implmnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gams.dao.interfaces.ClientDaoInterface;
import com.gams.models.Client;
import com.gams.models.ClientInfo;
import com.gams.models.ClientPayment;
import com.gams.utilities.DBC;

public class ClientDaoImpl implements ClientDaoInterface {

	private ArrayList<Client> allClients;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;

	@Override
	public ArrayList<Client> getAllClients() {
		allClients = new ArrayList<>();
		con = DBC.getInstance().getConnection();
		String query = "SELECT * FROM client";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				allClients.add(new Client(rs.getInt(1), rs.getString(3), rs.getDouble(7), rs.getDouble(5),
						rs.getDouble(6), rs.getString(2), rs.getString(4), rs.getDouble(8)));
			}

			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Getting all client error !" + e.getMessage());
		}
		return allClients;
	}

	@Override
	public void saveNewClient(Client model) throws SQLException {
		con = DBC.getInstance().getConnection();
		String query = "INSERT INTO client(c_name, c_phone, c_address, c_arrear, c_paid, c_total_bill, c_pay_rate) VALUES(?,?,?,?,?,?,?)";
		ps = con.prepareStatement(query);
		ps.setString(1, model.getClientName());
		ps.setString(2, model.getClientPhone());
		ps.setString(3, model.getClientAddress());
		ps.setFloat(4, 0);
		ps.setFloat(5, 0);
		ps.setFloat(6, 0);
		ps.setFloat(7, 0);
		ps.executeUpdate();
		ps.close();
		con.close();

	}

	@Override
	public ArrayList<Client> fetchTopFiveClient(String query) {
		allClients = new ArrayList<>();
		con = DBC.getInstance().getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt(1);
				boolean clientRepeat = false;
				if (allClients.size() > 0) {
					for (Client c : allClients) {
						if (c.getClientId() == id) {
							clientRepeat = true;
							break;
						}
					}
				}

				if (!clientRepeat) {
					allClients.add(new Client(rs.getInt(1), rs.getString(3), rs.getDouble(7), rs.getDouble(5),
							rs.getDouble(6), rs.getString(2), rs.getString(4), rs.getDouble(8)));
				}

				if (allClients.size() >= 5) {
					break;
				}

			} // while ends

			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Getting Top client error !" + e.getMessage());
		}

		return allClients;
	}

	@Override
	public ArrayList<ClientInfo> fetchClientInfos(int id) {
		ArrayList<ClientInfo> infos = new ArrayList<>();
		String query = "SELECT * FROM client, sale WHERE client.c_id=sale.c_id AND sale.c_id=" + id;
		con = DBC.getInstance().getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				infos.add(new ClientInfo(rs.getInt("c_id"), rs.getString("c_name"), rs.getString("c_phone"),
						rs.getString("c_address"), rs.getDouble("c_total_bill"), rs.getDouble("c_arrear"),
						rs.getDouble("c_paid"), rs.getDouble("c_pay_rate"), rs.getDouble("sale_cost"),
						rs.getDouble("sale_payment"), rs.getString("sale_time"), rs.getInt("sale_date"),
						rs.getString("sale_month"), rs.getInt("sale_year")));
			}

		} catch (SQLException e) {
			System.out.println("Error in fetching client info.." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return infos;
	}// ends

	public double getClientAccountStatus(int id, String columnName) throws SQLException {
		double value = 0.0;
		String query = "SELECT " + columnName + " FROM client WHERE c_id=" + id;
		con = DBC.getInstance().getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query);
		while (rs.next()) {
			value = rs.getDouble(1);
		}
		rs.close();
		st.close();
		con.close();
		return value;
	}// ends

	public void paymentTransaction(ClientPayment payModel) {
		con = DBC.getInstance().getConnection();
		String insertQuery = "INSERT INTO client_payment(c_id, pay_amount, pay_date, pay_month, pay_year) VALUES(?,?,?,?,?)";
		String updateQuery = "UPDATE client SET c_paid=?, c_arrear=?, c_pay_rate=? WHERE c_id=?";
		PreparedStatement psInsert = null, psUpdate = null;
		try {
			con.setAutoCommit(false);

			psInsert = con.prepareStatement(insertQuery);
			psInsert.setInt(1, payModel.getClientId());
			psInsert.setDouble(2, payModel.getPayAmount());
			psInsert.setInt(3, payModel.getPayDate());
			psInsert.setString(4, payModel.getPayMonth());
			psInsert.setInt(5, payModel.getPayYear());
			psInsert.executeUpdate();

			psUpdate = con.prepareStatement(updateQuery);
			psUpdate.setDouble(1, payModel.getClientPaid());
			psUpdate.setDouble(2, payModel.getClientArrear());
			psUpdate.setDouble(3, payModel.getClientPayRate());
			psUpdate.setInt(4, payModel.getClientId());
			psUpdate.executeUpdate();

			con.commit();

			payModel.setPayNotice("Successful !");
		} catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			payModel.setPayNotice(e.getMessage());

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (psInsert != null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (psUpdate != null) {
				try {
					psUpdate.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}// ends

}
