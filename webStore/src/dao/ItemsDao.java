package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Items;

import com.duo.DBHelper;

/**
 * 商品的业务逻辑类
 * 
 * @author Administrator
 * 
 */
public class ItemsDao {
	/**
	 * 获取所有商品信息
	 * @return
	 */
	public ArrayList<Items> getAllItems() {

		ArrayList<Items> list = new ArrayList<Items>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConn();
			String sql = "select * from items";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Items items = new Items();

				items.setId(rs.getInt("id"));
				items.setName(rs.getString("name"));
				items.setCity(rs.getString("city"));
				items.setPrice(rs.getInt("price"));
				items.setNumber(rs.getInt("number"));
				items.setPicture(rs.getString("picture"));

				list.add(items);// 将商品信息添加到集合中

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConn();
			String sql = "select * from items where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);//指定参数
			rs = stmt.executeQuery();
			if (rs.next()) {

				Items items = new Items();

				items.setId(rs.getInt("id"));
				items.setName(rs.getString("name"));
				items.setCity(rs.getString("city"));
				items.setPrice(rs.getInt("price"));
				items.setNumber(rs.getInt("number"));
				items.setPicture(rs.getString("picture"));

				return items;// 将商品信息添加到集合中
			} else {
				return null;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public ArrayList<Items> getItemsList(String list){
		ArrayList<Items> itemlist=new ArrayList<Items>();
		int count=5;
		if(list!=null&&list.length()>0){
			
			String[] arr=list.split(",");
			//商品信息条数大于5，取最新前5条输出
			if(arr.length>=5){
				for(int i=arr.length-1;i>=arr.length-count;i--){
					itemlist.add(getItemsById(Integer.parseInt(arr[i])));					
				}
			}
			//商品信息条数小于5条，直接倒序打印输出
			else {
				for(int i=arr.length-1;i>=0;i--){
					itemlist.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}
			return itemlist;
	    }
		else {
			return null;
			
		}
		
	}
}
