package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ���ﳵ��
 * @author Administrator
 *
 */
public class Cart {

	/**
	 * ���ﳵ����
	 */
	private HashMap<Items, Integer> goods;//���ﳵ����Ʒ
	
	private double totalPrice;//���ﳵ���ܼ۸�

	/*
	 * ���췽��
	 */
	public Cart() {
		
		goods=new HashMap<Items, Integer>();
		totalPrice=0.0;		
	}
	
	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * ���ﳵ�����Ʒ
	 */
	public boolean addGoods(Items item,Integer number){
		
		if(goods.containsKey(item))
		{
			goods.put(item, goods.get(item)+number);
		}
		else
		{
			goods.put(item, number);	
		}
		calTotalPrice();
		return true;
		
	}
	/**
	 * �ӹ��ﳵ�Ƴ���Ʒ
	 */
	public boolean removeGoods(Items item){

		goods.remove(item);
		calTotalPrice();
        return true;
	}
	/**
	 * ���㹺�ﳵ���ܽ��
	 */
	public double calTotalPrice(){
		
		double sum=0.0;
		Set<Items> keys=goods.keySet();//��ȡ��Ʒ�� ��
		Iterator<Items> it =keys.iterator();//ͨ������ȡ������
		
		while(it.hasNext()){
			
			Items items= it.next();//������Ʒ������
			sum +=items.getPrice() * goods.get(items);
		}
		this.setTotalPrice(sum)	;
		return this.getTotalPrice();	
	}
	
	public static void main(String[] args) {
		
		Items items1=new Items(1,"Ь��","����",200,300,"002.jpg");
		Items items2=new Items(2,"����","����",300,300,"001.jpg");
		Items items3=new Items(1,"Ь��","����",200,300,"002.jpg");
		Cart cart =new Cart();
		cart.addGoods(items1,2);
		cart.addGoods(items2,3);
		cart.addGoods(items3,3);
		//�ٴ������Ʒ
//		cart.addGoods(items1,1);
//		cart.removeGoods(items2);
		
	    Set<Entry<Items, Integer>> Entries= cart.getGoods().entrySet();//��ȡ���ϵļ�
	    for (Entry<Items, Integer> entry : Entries) {
	    	System.out.println(entry);
		}
		
		System.out.println("�ܽ��"+cart.calTotalPrice());
		
	}
}
