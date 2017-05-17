package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Items;

import com.duo.DBHelper;

/**
 * ��Ʒ��ҵ���߼���
 * 
 * @author Administrator
 * 
 */
public class ItemsDao {
	/**
	 * ��ȡ������Ʒ��Ϣ
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

				list.add(items);// ����Ʒ��Ϣ��ӵ�������

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// �ͷ�������
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
			stmt.setInt(1, id);//ָ������
			rs = stmt.executeQuery();
			if (rs.next()) {

				Items items = new Items();

				items.setId(rs.getInt("id"));
				items.setName(rs.getString("name"));
				items.setCity(rs.getString("city"));
				items.setPrice(rs.getInt("price"));
				items.setNumber(rs.getInt("number"));
				items.setPicture(rs.getString("picture"));

				return items;// ����Ʒ��Ϣ��ӵ�������
			} else {
				return null;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// �ͷ�������
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
			//��Ʒ��Ϣ��������5��ȡ����ǰ5�����
			if(arr.length>=5){
				for(int i=arr.length-1;i>=arr.length-count;i--){
					itemlist.add(getItemsById(Integer.parseInt(arr[i])));					
				}
			}
			//��Ʒ��Ϣ����С��5����ֱ�ӵ����ӡ���
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
