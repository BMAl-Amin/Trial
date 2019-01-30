package com.gams.dao.implmnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gams.dao.interfaces.SaleDaoInt;
import com.gams.models.SaleModel;
import com.gams.utilities.DBC;
import com.gams.utilities.ProcessCalender;

public class SaleDaoImpl implements SaleDaoInt {

	private Connection con;
	
	
	
	@Override
	public void doSaleInvoiceTransaction(SaleModel model) {
		con = DBC.getInstance().getConnection();
		String insertQuery = "INSERT INTO sale(c_id, sale_cost, sale_payment, sale_arrear, sale_time, sale_date, sale_month, sale_year) VALUES(?,?,?,?,?,?,?,?)";
		String insertQuery2 = "INSERT INTO product_sale(sale_id, product_id, product_amount) VALUES(?,?,?)";
		String payQuery = "INSERT INTO client_payment(c_id, pay_amount, pay_date, pay_month, pay_year) VALUES(?,?,?,?,?)";
		String updateQuery = "UPDATE client SET c_total_bill=?, c_paid=?, c_arrear=?, c_pay_rate=? WHERE c_id='"+model.getClientId()+"'";
		ResultSet rs = null;
		PreparedStatement psInsert=null, psInsert2=null, psPayment=null, psUpdate=null;
		try {
			con.setAutoCommit(false);
			psInsert= con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			psInsert.setInt(1, model.getClientId());
			psInsert.setFloat(2, model.getSaleCost());
			psInsert.setFloat(3, model.getSalePaidCost());
			psInsert.setFloat(4, model.getSaleArrear());
			psInsert.setString(5,ProcessCalender.getInstance().getCurTime());
			psInsert.setInt(6, ProcessCalender.getInstance().getCurDate());
			psInsert.setString(7,ProcessCalender.getInstance().getCurMonth());
			psInsert.setInt(8, ProcessCalender.getInstance().getCurYear());
			psInsert.executeUpdate();
			
			
			psPayment = con.prepareStatement(payQuery);
			psPayment.setInt(1, model.getClientId());
			psPayment.setFloat(2, model.getSalePaidCost());
			psPayment.setInt(3, ProcessCalender.getInstance().getCurDate());
			psPayment.setString(4, ProcessCalender.getInstance().getCurMonth());
			psPayment.setInt(5, ProcessCalender.getInstance().getCurYear());
			psPayment.executeUpdate();
			
			
			
			
			rs=psInsert.getGeneratedKeys();
            if(rs.next()){
                model.setSaleId(rs.getInt(1));
            }
			
            
            for(int i=0; i<model.getpIds().size();i++) {
            	if(psInsert2!=null) {
            		psInsert2.close();
            		psInsert2=null;
            	}
            	psInsert2 = con.prepareStatement(insertQuery2);
            	psInsert2.setInt(1, model.getSaleId());
            	psInsert2.setInt(2, model.getpIds().get(i));
            	psInsert2.setInt(3, model.getpAmounts().get(i));
            	psInsert2.executeUpdate();
            }
            
            psUpdate = con.prepareStatement(updateQuery);
			psUpdate.setFloat(1, model.getClientTotalBill());
			psUpdate.setFloat(2, model.getClientPaidBill());
			psUpdate.setFloat(3, model.getClientArrear());
			psUpdate.setFloat(4, model.getClientPayRate());
			psUpdate.executeUpdate();
			con.commit();
			model.setSaleNotice("Successful Transaction !");
			
			
		} catch (SQLException e) {
			model.setSaleNotice("Error in Transaction !");
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("RollbackError");
				e1.printStackTrace();
			}
			System.out.println("Sale invoice transaction failed..."+e.getMessage());
		}finally {
			if(psInsert!=null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(psInsert2!=null) {
				try {
					psInsert2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(psUpdate!=null) {
				try {
					psUpdate.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(psPayment!=null) {
				try {
					psPayment.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}//ends



	@Override
	public SaleModel getClientAccountState(SaleModel model) {
		con = DBC.getInstance().getConnection();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT c_arrear, c_paid, c_total_bill FROM client WHERE c_id='"+model.getClientId()+"'";
		
		try {
			st=con.createStatement();
			rs = st.executeQuery(query);
			if(rs.next()) {
				model.setClientArrear(rs.getFloat(1));
				model.setClientPaidBill(rs.getFloat(2));
				model.setClientTotalBill(rs.getFloat(3));
			}
		} catch (SQLException e) {
			System.out.println("Getting client's current billing info error."+e.getMessage());
		}finally {
			
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return model;
	}
	
	
	
	
	
	
	

}
